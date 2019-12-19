/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_bd;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dax
 */
@Entity
@Table(name = "adquiere")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adquiere.findAll", query = "SELECT a FROM Adquiere a")
    , @NamedQuery(name = "Adquiere.findByUsuarioCedula", query = "SELECT a FROM Adquiere a WHERE a.adquierePK.usuarioCedula = :usuarioCedula")
    , @NamedQuery(name = "Adquiere.findByLibroCodigo", query = "SELECT a FROM Adquiere a WHERE a.adquierePK.libroCodigo = :libroCodigo")
    , @NamedQuery(name = "Adquiere.findByCantidad", query = "SELECT a FROM Adquiere a WHERE a.cantidad = :cantidad")})
public class Adquiere implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AdquierePK adquierePK;
    @Column(name = "cantidad")
    private Integer cantidad;
    @JoinColumn(name = "libro_codigo", referencedColumnName = "codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Libro libro;
    @JoinColumn(name = "usuario_cedula", referencedColumnName = "cedula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Adquiere() {
    }

    public Adquiere(AdquierePK adquierePK) {
        this.adquierePK = adquierePK;
    }

    public Adquiere(String usuarioCedula, int libroCodigo) {
        this.adquierePK = new AdquierePK(usuarioCedula, libroCodigo);
    }

    public AdquierePK getAdquierePK() {
        return adquierePK;
    }

    public void setAdquierePK(AdquierePK adquierePK) {
        this.adquierePK = adquierePK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adquierePK != null ? adquierePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adquiere)) {
            return false;
        }
        Adquiere other = (Adquiere) object;
        if ((this.adquierePK == null && other.adquierePK != null) || (this.adquierePK != null && !this.adquierePK.equals(other.adquierePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo_bd.Adquiere[ adquierePK=" + adquierePK + " ]";
    }
    
}
