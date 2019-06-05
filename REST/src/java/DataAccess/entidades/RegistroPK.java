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
public class RegistroPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "reg_id")
    private int regId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "personal_per_id")
    private int personalPerId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "personal_usuarios_usu_id")
    private int personalUsuariosUsuId;

    public RegistroPK() {
    }

    public RegistroPK(int regId, int personalPerId, int personalUsuariosUsuId) {
        this.regId = regId;
        this.personalPerId = personalPerId;
        this.personalUsuariosUsuId = personalUsuariosUsuId;
    }

    public int getRegId() {
        return regId;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public int getPersonalPerId() {
        return personalPerId;
    }

    public void setPersonalPerId(int personalPerId) {
        this.personalPerId = personalPerId;
    }

    public int getPersonalUsuariosUsuId() {
        return personalUsuariosUsuId;
    }

    public void setPersonalUsuariosUsuId(int personalUsuariosUsuId) {
        this.personalUsuariosUsuId = personalUsuariosUsuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) regId;
        hash += (int) personalPerId;
        hash += (int) personalUsuariosUsuId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroPK)) {
            return false;
        }
        RegistroPK other = (RegistroPK) object;
        if (this.regId != other.regId) {
            return false;
        }
        if (this.personalPerId != other.personalPerId) {
            return false;
        }
        if (this.personalUsuariosUsuId != other.personalUsuariosUsuId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.RegistroPK[ regId=" + regId + ", personalPerId=" + personalPerId + ", personalUsuariosUsuId=" + personalUsuariosUsuId + " ]";
    }
    
}
