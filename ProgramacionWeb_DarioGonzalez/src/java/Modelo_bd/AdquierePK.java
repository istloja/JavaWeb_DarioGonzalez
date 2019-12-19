/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_bd;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Dax
 */
@Embeddable
public class AdquierePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "usuario_cedula")
    private String usuarioCedula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "libro_codigo")
    private int libroCodigo;

    public AdquierePK() {
    }

    public AdquierePK(String usuarioCedula, int libroCodigo) {
        this.usuarioCedula = usuarioCedula;
        this.libroCodigo = libroCodigo;
    }

    public String getUsuarioCedula() {
        return usuarioCedula;
    }

    public void setUsuarioCedula(String usuarioCedula) {
        this.usuarioCedula = usuarioCedula;
    }

    public int getLibroCodigo() {
        return libroCodigo;
    }

    public void setLibroCodigo(int libroCodigo) {
        this.libroCodigo = libroCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioCedula != null ? usuarioCedula.hashCode() : 0);
        hash += (int) libroCodigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdquierePK)) {
            return false;
        }
        AdquierePK other = (AdquierePK) object;
        if ((this.usuarioCedula == null && other.usuarioCedula != null) || (this.usuarioCedula != null && !this.usuarioCedula.equals(other.usuarioCedula))) {
            return false;
        }
        if (this.libroCodigo != other.libroCodigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo_bd.AdquierePK[ usuarioCedula=" + usuarioCedula + ", libroCodigo=" + libroCodigo + " ]";
    }
    
}
