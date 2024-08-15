package org.example.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "productos")
public class productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private double precio;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<descuentos>descuentos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<org.example.entities.descuentos> getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(List<org.example.entities.descuentos> descuentos) {
        this.descuentos = descuentos;
    }
}
