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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "Citas.findAll", query = "SELECT c FROM Citas c")
    , @NamedQuery(name = "Citas.findByCitId", query = "SELECT c FROM Citas c WHERE c.citId = :citId")
    , @NamedQuery(name = "Citas.findByCitFechaHoraReserva", query = "SELECT c FROM Citas c WHERE c.citFechaHoraReserva = :citFechaHoraReserva")
    , @NamedQuery(name = "Citas.findByCitEstado", query = "SELECT c FROM Citas c WHERE c.citEstado = :citEstado")
    , @NamedQuery(name = "Citas.findByCitIdPersonal", query = "SELECT c FROM Citas c WHERE c.citIdPersonal = :citIdPersonal")})
public class Citas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cit_id")
    private Integer citId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cit_fecha_hora_reserva")
    @Temporal(TemporalType.TIMESTAMP)
    private Date citFechaHoraReserva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cit_estado")
    private int citEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cit_id_personal")
    private int citIdPersonal;
    @JoinColumn(name = "cit_num_seguro_paciente", referencedColumnName = "pac_num_seguro")
    @ManyToOne(optional = false)
    private Pacientes citNumSeguroPaciente;

    public Citas() {
    }

    public Citas(Integer citId) {
        this.citId = citId;
    }

    public Citas(Integer citId, Date citFechaHoraReserva, int citEstado, int citIdPersonal) {
        this.citId = citId;
        this.citFechaHoraReserva = citFechaHoraReserva;
        this.citEstado = citEstado;
        this.citIdPersonal = citIdPersonal;
    }

    public Integer getCitId() {
        return citId;
    }

    public void setCitId(Integer citId) {
        this.citId = citId;
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

    public int getCitIdPersonal() {
        return citIdPersonal;
    }

    public void setCitIdPersonal(int citIdPersonal) {
        this.citIdPersonal = citIdPersonal;
    }

    public Pacientes getCitNumSeguroPaciente() {
        return citNumSeguroPaciente;
    }

    public void setCitNumSeguroPaciente(Pacientes citNumSeguroPaciente) {
        this.citNumSeguroPaciente = citNumSeguroPaciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (citId != null ? citId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citas)) {
            return false;
        }
        Citas other = (Citas) object;
        if ((this.citId == null && other.citId != null) || (this.citId != null && !this.citId.equals(other.citId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.entidades.Citas[ citId=" + citId + " ]";
    }
    
}
