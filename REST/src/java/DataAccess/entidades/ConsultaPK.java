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
public class ConsultaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "con_id")
    private int conId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "con_folio_receta")
    private int conFolioReceta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "con_id_personal")
    private int conIdPersonal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "con_seguro_paciente")
    private int conSeguroPaciente;

    public ConsultaPK() {
    }

    public ConsultaPK(int conId, int conFolioReceta, int conIdPersonal, int conSeguroPaciente) {
        this.conId = conId;
        this.conFolioReceta = conFolioReceta;
        this.conIdPersonal = conIdPersonal;
        this.conSeguroPaciente = conSeguroPaciente;
    }

    public int getConId() {
        return conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public int getConFolioReceta() {
        return conFolioReceta;
    }

    public void setConFolioReceta(int conFolioReceta) {
        this.conFolioReceta = conFolioReceta;
    }

    public int getConIdPersonal() {
        return conIdPersonal;
    }

    public void setConIdPersonal(int conIdPersonal) {
        this.conIdPersonal = conIdPersonal;
    }

    public int getConSeguroPaciente() {
        return conSeguroPaciente;
    }

    public void setConSeguroPaciente(int conSeguroPaciente) {
        this.conSeguroPaciente = conSeguroPaciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) conId;
        hash += (int) conFolioReceta;
        hash += (int) conIdPersonal;
        hash += (int) conSeguroPaciente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultaPK)) {
            return false;
        }
        ConsultaPK other = (ConsultaPK) object;
        if (this.conId != other.conId) {
            return false;
        }
        if (this.conFolioReceta != other.conFolioReceta) {
            return false;
        }
        if (this.conIdPersonal != other.conIdPersonal) {
            return false;
        }
        if (this.conSeguroPaciente != other.conSeguroPaciente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.ConsultaPK[ conId=" + conId + ", conFolioReceta=" + conFolioReceta + ", conIdPersonal=" + conIdPersonal + ", conSeguroPaciente=" + conSeguroPaciente + " ]";
    }
    
}
