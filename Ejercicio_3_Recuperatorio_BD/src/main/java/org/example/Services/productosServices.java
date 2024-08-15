package org.example.Services;

import org.example.DTO.producto;
import org.example.connections.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class productosServices {
public List<producto> ObtenerProductosConYSinDescuento(){
    Session session= HibernateUtil.getSession();
    List<producto>ProductosConYSinDescuento=new ArrayList<>();
    try {
        session.beginTransaction();
        String Sql1="SELECT p.id, p.nombre, p.precio, COALESCE(SUM(d.porcentaje_descuento), 0) AS total_descuento,\n" +
                "       p.precio - (p.precio * (COALESCE(SUM(d.porcentaje_descuento), 0) / 100)) AS precio_con_descuento\n" +
                "FROM productos p\n" +
                "LEFT JOIN descuentos d ON p.id = d.producto_id\n" +
                "GROUP BY p.id, p.nombre, p.precio\n" +
                "ORDER BY precio_con_descuento - p.precio DESC";
        Query<producto>Consulta1=session.createQuery(Sql1, producto.class);
        ProductosConYSinDescuento=Consulta1.getResultList();
        session.getTransaction().commit();
        return ProductosConYSinDescuento;
    }finally {
        session.close();
    }
}
public List<producto>ObtenerProductosSinDescuento(){
    Session session=HibernateUtil.getSession();
    List<producto>ProductosSinDescuento=new ArrayList<>();
    try {
        session.beginTransaction();
        String Sql2="SELECT p.id, p.nombre, p.precio\n" +
                "FROM productos p\n" +
                "LEFT JOIN descuentos d ON p.id = d.producto_id\n" +
                "WHERE d.producto_id IS NULL";
        Query<producto>Consulta2=session.createQuery(Sql2, producto.class);
        ProductosSinDescuento=Consulta2.getResultList();
        session.getTransaction().commit();
        return ProductosSinDescuento;
    }finally {
        session.close();
    }
}
public List<producto>ObtenerProductosConMasDeUnDescuento(){
    Session session=HibernateUtil.getSession();
    List<producto>ProductosConMasDeUnDescuento=new ArrayList<>();
    try {
        session.beginTransaction();
        String Sql3="use negocio;\n" +
                "SELECT p.id, p.nombre, p.precio\n" +
                "FROM productos p\n" +
                "JOIN descuentos d ON p.id = d.producto_id\n" +
                "GROUP BY p.id, p.nombre, p.precio\n" +
                "HAVING COUNT(d.id) > 1";
        Query<producto>Consulta3=session.createQuery(Sql3, producto.class);
        ProductosConMasDeUnDescuento=Consulta3.getResultList();
        return ProductosConMasDeUnDescuento;
    }finally {
      session.close();
    }
}
}
