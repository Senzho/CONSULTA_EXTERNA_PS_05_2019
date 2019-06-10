/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.entidades;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Victor Javier
 */
@Entity
@Table(name = "consultas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consultas.findAll", query = "SELECT c FROM Consultas c")
    , @NamedQuery(name = "Consultas.findByConId", query = "SELECT c FROM Consultas c WHERE c.conId = :conId")
    , @NamedQuery(name = "Consultas.findByConEstatura", query = "SELECT c FROM Consultas c WHERE c.conEstatura = :conEstatura")
    , @NamedQuery(name = "Consultas.findByConFecha", query = "SELECT c FROM Consultas c WHERE c.conFecha = :conFecha")
    , @NamedQuery(name = "Consultas.findByConHoraFin", query = "SELECT c FROM Consultas c WHERE c.conHoraFin = :conHoraFin")
    , @NamedQuery(name = "Consultas.findByConHoraInicio", query = "SELECT c FROM Consultas c WHERE c.conHoraInicio = :conHoraInicio")
    , @NamedQuery(name = "Consultas.findByConPeso", query = "SELECT c FROM Consultas c WHERE c.conPeso = :conPeso")
    , @NamedQuery(name = "Consultas.findByConPresion", query = "SELECT c FROM Consultas c WHERE c.conPresion = :conPresion")
    , @NamedQuery(name = "Consultas.findByConTemperatura", query = "SELECT c FROM Consultas c WHERE c.conTemperatura = :conTemperatura")
    , @NamedQuery(name = "Consultas.findByPaciente", query = "SELECT c FROM Consultas c WHERE c.conSeguroPaciente.pacNumSeguro = :pacNumSeguro")})
public class Consultas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "con_id")
    private Integer conId;
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
    @JoinColumn(name = "con_pr_rfc", referencedColumnName = "pr_rfc")
    @ManyToOne(optional = false)
    private Personal conPrRfc;
    @JoinColumn(name = "con_seguro_paciente", referencedColumnName = "pac_num_seguro")
    @ManyToOne(optional = false)
    private Pacientes conSeguroPaciente;
    @JoinColumn(name = "con_folio_receta", referencedColumnName = "rec_folio")
    @ManyToOne(optional = false)
    private Recetas conFolioReceta;

    public Consultas() {
    }

    public Consultas(Integer conId) {
        this.conId = conId;
    }

    public Consultas(Integer conId, String conDiagnostico, float conEstatura, Date conFecha, Date conHoraFin, Date conHoraInicio, float conPeso, String conPresion, float conTemperatura) {
        this.conId = conId;
        this.conDiagnostico = conDiagnostico;
        this.conEstatura = conEstatura;
        this.conFecha = conFecha;
        this.conHoraFin = conHoraFin;
        this.conHoraInicio = conHoraInicio;
        this.conPeso = conPeso;
        this.conPresion = conPresion;
        this.conTemperatura = conTemperatura;
    }
    
    public Consultas(JSONObject jObjeto) throws JSONException {
        this.conDiagnostico = jObjeto.getString("conDiagnostico");
        this.conEstatura = (float) jObjeto.getDouble("conEstatura");
        try {
            this.conFecha = new SimpleDateFormat("yyyy-MM-dd").parse(jObjeto.getString("conFecha"));
            this.conHoraInicio = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jObjeto.getString("conHoraInicio"));
            this.conHoraFin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jObjeto.getString("conHoraFin"));
        } catch (ParseException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conPeso = (float) jObjeto.getDouble("conPeso");
        this.conPresion = jObjeto.getString("conPresion");
        this.conTemperatura = (float) jObjeto.getDouble("conTemperatura");
    }

    public Integer getConId() {
        return conId;
    }

    public void setConId(Integer conId) {
        this.conId = conId;
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

    public Personal getConPrRfc() {
        return conPrRfc;
    }

    public void setConPrRfc(Personal conPrRfc) {
        this.conPrRfc = conPrRfc;
    }

    public Pacientes getConSeguroPaciente() {
        return conSeguroPaciente;
    }

    public void setConSeguroPaciente(Pacientes conSeguroPaciente) {
        this.conSeguroPaciente = conSeguroPaciente;
    }

    public Recetas getConFolioReceta() {
        return conFolioReceta;
    }

    public void setConFolioReceta(Recetas conFolioReceta) {
        this.conFolioReceta = conFolioReceta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conId != null ? conId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultas)) {
            return false;
        }
        Consultas other = (Consultas) object;
        if ((this.conId == null && other.conId != null) || (this.conId != null && !this.conId.equals(other.conId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.entidades.Consultas[ conId=" + conId + " ]";
    }
    
}
