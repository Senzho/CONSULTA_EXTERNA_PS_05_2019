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
public class CitaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "cit_id")
    private int citId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cit_id_personal")
    private int citIdPersonal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cit_num_seguro_paciente")
    private int citNumSeguroPaciente;

    public CitaPK() {
    }

    public CitaPK(int citId, int citIdPersonal, int citNumSeguroPaciente) {
        this.citId = citId;
        this.citIdPersonal = citIdPersonal;
        this.citNumSeguroPaciente = citNumSeguroPaciente;
    }

    public int getCitId() {
        return citId;
    }

    public void setCitId(int citId) {
        this.citId = citId;
    }

    public int getCitIdPersonal() {
        return citIdPersonal;
    }

    public void setCitIdPersonal(int citIdPersonal) {
        this.citIdPersonal = citIdPersonal;
    }

    public int getCitNumSeguroPaciente() {
        return citNumSeguroPaciente;
    }

    public void setCitNumSeguroPaciente(int citNumSeguroPaciente) {
        this.citNumSeguroPaciente = citNumSeguroPaciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) citId;
        hash += (int) citIdPersonal;
        hash += (int) citNumSeguroPaciente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CitaPK)) {
            return false;
        }
        CitaPK other = (CitaPK) object;
        if (this.citId != other.citId) {
            return false;
        }
        if (this.citIdPersonal != other.citIdPersonal) {
            return false;
        }
        if (this.citNumSeguroPaciente != other.citNumSeguroPaciente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.CitaPK[ citId=" + citId + ", citIdPersonal=" + citIdPersonal + ", citNumSeguroPaciente=" + citNumSeguroPaciente + " ]";
    }
    
}
