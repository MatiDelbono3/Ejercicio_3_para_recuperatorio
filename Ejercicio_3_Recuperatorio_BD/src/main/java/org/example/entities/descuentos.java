package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "descuentos")
public class descuentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "porcentaje_descuento", nullable = false)
    private String porcentaje_descuento;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="producto_id" )
    private productos producto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPorcentaje_descuento() {
        return porcentaje_descuento;
    }

    public void setPorcentaje_descuento(String porcentaje_descuento) {
        this.porcentaje_descuento = porcentaje_descuento;
    }

    public productos getProducto() {
        return producto;
    }

    public void setProducto(productos producto) {
        this.producto = producto;
    }
}
