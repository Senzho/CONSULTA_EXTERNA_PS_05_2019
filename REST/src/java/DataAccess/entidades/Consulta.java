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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Victor Javier
 */
@Entity
@Table(name = "consultas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consulta.findAll", query = "SELECT c FROM Consulta c")
    , @NamedQuery(name = "Consulta.findByConId", query = "SELECT c FROM Consulta c WHERE c.consultaPK.conId = :conId")
    , @NamedQuery(name = "Consulta.findByConEstatura", query = "SELECT c FROM Consulta c WHERE c.conEstatura = :conEstatura")
    , @NamedQuery(name = "Consulta.findByConFecha", query = "SELECT c FROM Consulta c WHERE c.conFecha = :conFecha")
    , @NamedQuery(name = "Consulta.findByConHoraFin", query = "SELECT c FROM Consulta c WHERE c.conHoraFin = :conHoraFin")
    , @NamedQuery(name = "Consulta.findByConHoraInicio", query = "SELECT c FROM Consulta c WHERE c.conHoraInicio = :conHoraInicio")
    , @NamedQuery(name = "Consulta.findByConPeso", query = "SELECT c FROM Consulta c WHERE c.conPeso = :conPeso")
    , @NamedQuery(name = "Consulta.findByConPresion", query = "SELECT c FROM Consulta c WHERE c.conPresion = :conPresion")
    , @NamedQuery(name = "Consulta.findByConTemperatura", query = "SELECT c FROM Consulta c WHERE c.conTemperatura = :conTemperatura")
    , @NamedQuery(name = "Consulta.findByConFolioReceta", query = "SELECT c FROM Consulta c WHERE c.consultaPK.conFolioReceta = :conFolioReceta")
    , @NamedQuery(name = "Consulta.findByConIdPersonal", query = "SELECT c FROM Consulta c WHERE c.consultaPK.conIdPersonal = :conIdPersonal")
    , @NamedQuery(name = "Consulta.findByConSeguroPaciente", query = "SELECT c FROM Consulta c WHERE c.consultaPK.conSeguroPaciente = :conSeguroPaciente")})
public class Consulta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConsultaPK consultaPK;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "con_diagnostico")
    private String conDiagnostico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "con_estatura")
    private float conEstatura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "con_fecha")
    @Temporal(TemporalType.DATE)
    private Date conFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "con_hora_fin")
    @Temporal(TemporalType.TIME)
    private Date conHoraFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "con_hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date conHoraInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "con_peso")
    private float conPeso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "con_presion")
    private String conPresion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "con_temperatura")
    private float conTemperatura;
    @JoinColumn(name = "con_seguro_paciente", referencedColumnName = "pac_num_seguro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paciente paciente;
    @JoinColumn(name = "con_id_personal", referencedColumnName = "per_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Personal personal;
    @JoinColumn(name = "con_folio_receta", referencedColumnName = "rec_folio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Receta receta;

    public Consulta() {
    }

    public Consulta(ConsultaPK consultaPK) {
        this.consultaPK = consultaPK;
    }

    public Consulta(ConsultaPK consultaPK, String conDiagnostico, float conEstatura, Date conFecha, Date conHoraFin, Date conHoraInicio, float conPeso, String conPresion, float conTemperatura) {
        this.consultaPK = consultaPK;
        this.conDiagnostico = conDiagnostico;
        this.conEstatura = conEstatura;
        this.conFecha = conFecha;
        this.conHoraFin = conHoraFin;
        this.conHoraInicio = conHoraInicio;
        this.conPeso = conPeso;
        this.conPresion = conPresion;
        this.conTemperatura = conTemperatura;
    }

    public Consulta(int conId, int conFolioReceta, int conIdPersonal, int conSeguroPaciente) {
        this.consultaPK = new ConsultaPK(conId, conFolioReceta, conIdPersonal, conSeguroPaciente);
    }

    public ConsultaPK getConsultaPK() {
        return consultaPK;
    }

    public void setConsultaPK(ConsultaPK consultaPK) {
        this.consultaPK = consultaPK;
    }

    public String getConDiagnostico() {
        return conDiagnostico;
    }

    public void setConDiagnostico(String conDiagnostico) {
        this.conDiagnostico = conDiagnostico;
    }

    public float getConEstatura() {
        return conEstatura;
    }

    public void setConEstatura(float conEstatura) {
        this.conEstatura = conEstatura;
    }

    public Date getConFecha() {
        return conFecha;
    }

    public void setConFecha(Date conFecha) {
        this.conFecha = conFecha;
    }

    public Date getConHoraFin() {
        return conHoraFin;
    }

    public void setConHoraFin(Date conHoraFin) {
        this.conHoraFin = conHoraFin;
    }

    public Date getConHoraInicio() {
        return conHoraInicio;
    }

    public void setConHoraInicio(Date conHoraInicio) {
        this.conHoraInicio = conHoraInicio;
    }

    public float getConPeso() {
        return conPeso;
    }

    public void setConPeso(float conPeso) {
        this.conPeso = conPeso;
    }

    public String getConPresion() {
        return conPresion;
    }

    public void setConPresion(String conPresion) {
        this.conPresion = conPresion;
    }

    public float getConTemperatura() {
        return conTemperatura;
    }

    public void setConTemperatura(float conTemperatura) {
        this.conTemperatura = conTemperatura;
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

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consultaPK != null ? consultaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consulta)) {
            return false;
        }
        Consulta other = (Consulta) object;
        if ((this.consultaPK == null && other.consultaPK != null) || (this.consultaPK != null && !this.consultaPK.equals(other.consultaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Consulta[ consultaPK=" + consultaPK + " ]";
    }
    
}
