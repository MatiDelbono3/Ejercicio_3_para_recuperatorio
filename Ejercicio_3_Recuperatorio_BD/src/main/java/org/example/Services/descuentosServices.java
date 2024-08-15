package org.example.Services;

import org.example.DTO.producto;
import org.example.connections.HibernateUtil;
import org.hibernate.Session;

public class descuentosServices {
    public void CrearDescuentosParaUnProducto(producto producto){
        Session session= HibernateUtil.getSession();
        try{
            session.beginTransaction();
            session.save(producto);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
