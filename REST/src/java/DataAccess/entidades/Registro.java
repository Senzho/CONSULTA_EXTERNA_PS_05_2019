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
import javax.persistence.JoinColumns;
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
@Table(name = "registros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registro.findAll", query = "SELECT r FROM Registro r")
    , @NamedQuery(name = "Registro.findByRegId", query = "SELECT r FROM Registro r WHERE r.registroPK.regId = :regId")
    , @NamedQuery(name = "Registro.findByRegHoraEntrada", query = "SELECT r FROM Registro r WHERE r.regHoraEntrada = :regHoraEntrada")
    , @NamedQuery(name = "Registro.findByRegHoraSalida", query = "SELECT r FROM Registro r WHERE r.regHoraSalida = :regHoraSalida")
    , @NamedQuery(name = "Registro.findByRegLugarEstadia", query = "SELECT r FROM Registro r WHERE r.regLugarEstadia = :regLugarEstadia")
    , @NamedQuery(name = "Registro.findByPersonalPerId", query = "SELECT r FROM Registro r WHERE r.registroPK.personalPerId = :personalPerId")
    , @NamedQuery(name = "Registro.findByPersonalUsuariosUsuId", query = "SELECT r FROM Registro r WHERE r.registroPK.personalUsuariosUsuId = :personalUsuariosUsuId")})
public class Registro implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistroPK registroPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reg_hora_entrada")
    @Temporal(TemporalType.TIME)
    private Date regHoraEntrada;
    @Column(name = "reg_hora_salida")
    @Temporal(TemporalType.TIME)
    private Date regHoraSalida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "reg_lugar_estadia")
    private String regLugarEstadia;
    @JoinColumns({
        @JoinColumn(name = "personal_per_id", referencedColumnName = "per_id", insertable = false, updatable = false)
        , @JoinColumn(name = "personal_usuarios_usu_id", referencedColumnName = "usuarios_usu_id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Personal personal;

    public Registro() {
    }

    public Registro(RegistroPK registroPK) {
        this.registroPK = registroPK;
    }

    public Registro(RegistroPK registroPK, Date regHoraEntrada, String regLugarEstadia) {
        this.registroPK = registroPK;
        this.regHoraEntrada = regHoraEntrada;
        this.regLugarEstadia = regLugarEstadia;
    }

    public Registro(int regId, int personalPerId, int personalUsuariosUsuId) {
        this.registroPK = new RegistroPK(regId, personalPerId, personalUsuariosUsuId);
    }

    public RegistroPK getRegistroPK() {
        return registroPK;
    }

    public void setRegistroPK(RegistroPK registroPK) {
        this.registroPK = registroPK;
    }

    public Date getRegHoraEntrada() {
        return regHoraEntrada;
    }

    public void setRegHoraEntrada(Date regHoraEntrada) {
        this.regHoraEntrada = regHoraEntrada;
    }

    public Date getRegHoraSalida() {
        return regHoraSalida;
    }

    public void setRegHoraSalida(Date regHoraSalida) {
        this.regHoraSalida = regHoraSalida;
    }

    public String getRegLugarEstadia() {
        return regLugarEstadia;
    }

    public void setRegLugarEstadia(String regLugarEstadia) {
        this.regLugarEstadia = regLugarEstadia;
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
        hash += (registroPK != null ? registroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registro)) {
            return false;
        }
        Registro other = (Registro) object;
        if ((this.registroPK == null && other.registroPK != null) || (this.registroPK != null && !this.registroPK.equals(other.registroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Registro[ registroPK=" + registroPK + " ]";
    }
    
}
