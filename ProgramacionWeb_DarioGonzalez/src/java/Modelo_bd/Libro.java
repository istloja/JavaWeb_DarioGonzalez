/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_bd;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dax
 */
@Entity
@Table(name = "libro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l")
    , @NamedQuery(name = "Libro.findByCodigo", query = "SELECT l FROM Libro l WHERE l.codigo = :codigo")
    , @NamedQuery(name = "Libro.findByNombre", query = "SELECT l FROM Libro l WHERE l.nombre = :nombre")
    , @NamedQuery(name = "Libro.findByDescripcion", query = "SELECT l FROM Libro l WHERE l.descripcion = :descripcion")
    , @NamedQuery(name = "Libro.findByStock", query = "SELECT l FROM Libro l WHERE l.stock = :stock")
    , @NamedQuery(name = "Libro.findByCosto", query = "SELECT l FROM Libro l WHERE l.costo = :costo")})
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock")
    private int stock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private double costo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "libro")
    private Collection<Adquiere> adquiereCollection;
    @JoinColumn(name = "categoria_nombre", referencedColumnName = "nombre")
    @ManyToOne(optional = false)
    private Categoria categoriaNombre;

    public Libro() {
    }

    public Libro(Integer codigo) {
        this.codigo = codigo;
    }

    public Libro(Integer codigo, String nombre, String descripcion, int stock, double costo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.costo = costo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @XmlTransient
    public Collection<Adquiere> getAdquiereCollection() {
        return adquiereCollection;
    }

    public void setAdquiereCollection(Collection<Adquiere> adquiereCollection) {
        this.adquiereCollection = adquiereCollection;
    }

    public Categoria getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(Categoria categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo_bd.Libro[ codigo=" + codigo + " ]";
    }
    
}
