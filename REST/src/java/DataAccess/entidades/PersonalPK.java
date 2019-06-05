/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Victor Javier
 */
@Embeddable
public class PersonalPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "per_id")
    private int perId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuarios_usu_id")
    private int usuariosUsuId;

    public PersonalPK() {
    }

    public PersonalPK(int perId, int usuariosUsuId) {
        this.perId = perId;
        this.usuariosUsuId = usuariosUsuId;
    }

    public int getPerId() {
        return perId;
    }

    public void setPerId(int perId) {
        this.perId = perId;
    }

    public int getUsuariosUsuId() {
        return usuariosUsuId;
    }

    public void setUsuariosUsuId(int usuariosUsuId) {
        this.usuariosUsuId = usuariosUsuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) perId;
        hash += (int) usuariosUsuId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonalPK)) {
            return false;
        }
        PersonalPK other = (PersonalPK) object;
        if (this.perId != other.perId) {
            return false;
        }
        if (this.usuariosUsuId != other.usuariosUsuId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.PersonalPK[ perId=" + perId + ", usuariosUsuId=" + usuariosUsuId + " ]";
    }
    
}
