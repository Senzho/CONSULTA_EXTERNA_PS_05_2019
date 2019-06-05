/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Victor Javier
 */
@Entity
@Table(name = "personal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p")
    , @NamedQuery(name = "Personal.findByPerId", query = "SELECT p FROM Personal p WHERE p.personalPK.perId = :perId")
    , @NamedQuery(name = "Personal.findByPerNombres", query = "SELECT p FROM Personal p WHERE p.perNombres = :perNombres")
    , @NamedQuery(name = "Personal.findByPerApellidos", query = "SELECT p FROM Personal p WHERE p.perApellidos = :perApellidos")
    , @NamedQuery(name = "Personal.findByPerFechaNac", query = "SELECT p FROM Personal p WHERE p.perFechaNac = :perFechaNac")
    , @NamedQuery(name = "Personal.findByPerNumPersonal", query = "SELECT p FROM Personal p WHERE p.perNumPersonal = :perNumPersonal")
    , @NamedQuery(name = "Personal.findByPerNumTelefono", query = "SELECT p FROM Personal p WHERE p.perNumTelefono = :perNumTelefono")
    , @NamedQuery(name = "Personal.findByPerSexo", query = "SELECT p FROM Personal p WHERE p.perSexo = :perSexo")
    , @NamedQuery(name = "Personal.findByPrRfc", query = "SELECT p FROM Personal p WHERE p.prRfc = :prRfc")
    , @NamedQuery(name = "Personal.findByPerTurno", query = "SELECT p FROM Personal p WHERE p.perTurno = :perTurno")
    , @NamedQuery(name = "Personal.findByUsuariosUsuId", query = "SELECT p FROM Personal p WHERE p.personalPK.usuariosUsuId = :usuariosUsuId")
    , @NamedQuery(name = "Personal.findByPerEstado", query = "SELECT p FROM Personal p WHERE p.perEstado = :perEstado")})
public class Personal implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonalPK personalPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "per_nombres")
    private String perNombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "per_apellidos")
    private String perApellidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "per_fecha_nac")
    @Temporal(TemporalType.DATE)
    private Date perFechaNac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "per_num_personal")
    private String perNumPersonal;
    @Size(max = 15)
    @Column(name = "per_num_telefono")
    private String perNumTelefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "per_sexo")
    private Character perSexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "pr_rfc")
    private String prRfc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "per_turno")
    private String perTurno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "per_estado")
    private short perEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personal")
    private Collection<Cita> citaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personal")
    private Collection<Consulta> consultaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personal")
    private Collection<Registro> registroCollection;
    @JoinColumn(name = "usuarios_usu_id", referencedColumnName = "usu_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Personal() {
    }

    public Personal(PersonalPK personalPK) {
        this.personalPK = personalPK;
    }

    public Personal(PersonalPK personalPK, String perNombres, String perApellidos, Date perFechaNac, String perNumPersonal, Character perSexo, String prRfc, String perTurno, short perEstado) {
        this.personalPK = personalPK;
        this.perNombres = perNombres;
        this.perApellidos = perApellidos;
        this.perFechaNac = perFechaNac;
        this.perNumPersonal = perNumPersonal;
        this.perSexo = perSexo;
        this.prRfc = prRfc;
        this.perTurno = perTurno;
        this.perEstado = perEstado;
    }

    public Personal(int perId, int usuariosUsuId) {
        this.personalPK = new PersonalPK(perId, usuariosUsuId);
    }

    public PersonalPK getPersonalPK() {
        return personalPK;
    }

    public void setPersonalPK(PersonalPK personalPK) {
        this.personalPK = personalPK;
    }

    public String getPerNombres() {
        return perNombres;
    }

    public void setPerNombres(String perNombres) {
        this.perNombres = perNombres;
    }

    public String getPerApellidos() {
        return perApellidos;
    }

    public void setPerApellidos(String perApellidos) {
        this.perApellidos = perApellidos;
    }

    public Date getPerFechaNac() {
        return perFechaNac;
    }

    public void setPerFechaNac(Date perFechaNac) {
        this.perFechaNac = perFechaNac;
    }

    public String getPerNumPersonal() {
        return perNumPersonal;
    }

    public void setPerNumPersonal(String perNumPersonal) {
        this.perNumPersonal = perNumPersonal;
    }

    public String getPerNumTelefono() {
        return perNumTelefono;
    }

    public void setPerNumTelefono(String perNumTelefono) {
        this.perNumTelefono = perNumTelefono;
    }

    public Character getPerSexo() {
        return perSexo;
    }

    public void setPerSexo(Character perSexo) {
        this.perSexo = perSexo;
    }

    public String getPrRfc() {
        return prRfc;
    }

    public void setPrRfc(String prRfc) {
        this.prRfc = prRfc;
    }

    public String getPerTurno() {
        return perTurno;
    }

    public void setPerTurno(String perTurno) {
        this.perTurno = perTurno;
    }

    public short getPerEstado() {
        return perEstado;
    }

    public void setPerEstado(short perEstado) {
        this.perEstado = perEstado;
    }

    @XmlTransient
    public Collection<Cita> getCitaCollection() {
        return citaCollection;
    }

    public void setCitaCollection(Collection<Cita> citaCollection) {
        this.citaCollection = citaCollection;
    }

    @XmlTransient
    public Collection<Consulta> getConsultaCollection() {
        return consultaCollection;
    }

    public void setConsultaCollection(Collection<Consulta> consultaCollection) {
        this.consultaCollection = consultaCollection;
    }

    @XmlTransient
    public Collection<Registro> getRegistroCollection() {
        return registroCollection;
    }

    public void setRegistroCollection(Collection<Registro> registroCollection) {
        this.registroCollection = registroCollection;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personalPK != null ? personalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personal)) {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.personalPK == null && other.personalPK != null) || (this.personalPK != null && !this.personalPK.equals(other.personalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Personal[ personalPK=" + personalPK + " ]";
    }
    
}
