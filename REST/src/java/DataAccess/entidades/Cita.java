/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Victor Javier
 */
@Entity
@Table(name = "citas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c")
    , @NamedQuery(name = "Cita.findByCitId", query = "SELECT c FROM Cita c WHERE c.citaPK.citId = :citId")
    , @NamedQuery(name = "Cita.findByCitFechaHoraReserva", query = "SELECT c FROM Cita c WHERE c.citFechaHoraReserva = :citFechaHoraReserva")
    , @NamedQuery(name = "Cita.findByCitEstado", query = "SELECT c FROM Cita c WHERE c.citEstado = :citEstado")
    , @NamedQuery(name = "Cita.findByCitIdPersonal", query = "SELECT c FROM Cita c WHERE c.citaPK.citIdPersonal = :citIdPersonal")
    , @NamedQuery(name = "Cita.findByCitNumSeguroPaciente", query = "SELECT c FROM Cita c WHERE c.citaPK.citNumSeguroPaciente = :citNumSeguroPaciente")})
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CitaPK citaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cit_fecha_hora_reserva")
    @Temporal(TemporalType.TIMESTAMP)
    private Date citFechaHoraReserva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cit_estado")
    private int citEstado;
    @JoinColumn(name = "cit_num_seguro_paciente", referencedColumnName = "pac_num_seguro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paciente paciente;
    @JoinColumn(name = "cit_id_personal", referencedColumnName = "per_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Personal personal;

    public Cita() {
    }

    public Cita(CitaPK citaPK) {
        this.citaPK = citaPK;
    }

    public Cita(CitaPK citaPK, Date citFechaHoraReserva, int citEstado) {
        this.citaPK = citaPK;
        this.citFechaHoraReserva = citFechaHoraReserva;
        this.citEstado = citEstado;
    }

    public Cita(int citId, int citIdPersonal, int citNumSeguroPaciente) {
        this.citaPK = new CitaPK(citId, citIdPersonal, citNumSeguroPaciente);
    }

    public CitaPK getCitaPK() {
        return citaPK;
    }

    public void setCitaPK(CitaPK citaPK) {
        this.citaPK = citaPK;
    }

    public Date getCitFechaHoraReserva() {
        return citFechaHoraReserva;
    }

    public void setCitFechaHoraReserva(Date citFechaHoraReserva) {
        this.citFechaHoraReserva = citFechaHoraReserva;
    }

    public int getCitEstado() {
        return citEstado;
    }

    public void setCitEstado(int citEstado) {
        this.citEstado = citEstado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (citaPK != null ? citaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cita)) {
            return false;
        }
        Cita other = (Cita) object;
        if ((this.citaPK == null && other.citaPK != null) || (this.citaPK != null && !this.citaPK.equals(other.citaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Cita[ citaPK=" + citaPK + " ]";
    }
    
}
