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
import javax.persistence.Entity;
import javax.persistence.Id;
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
    , @NamedQuery(name = "Personal.findByPerNombres", query = "SELECT p FROM Personal p WHERE p.perNombres = :perNombres")
    , @NamedQuery(name = "Personal.findByPerApellidos", query = "SELECT p FROM Personal p WHERE p.perApellidos = :perApellidos")
    , @NamedQuery(name = "Personal.findByPerFechaNac", query = "SELECT p FROM Personal p WHERE p.perFechaNac = :perFechaNac")
    , @NamedQuery(name = "Personal.findByPerNumPersonal", query = "SELECT p FROM Personal p WHERE p.perNumPersonal = :perNumPersonal")
    , @NamedQuery(name = "Personal.findByPerNumTelefono", query = "SELECT p FROM Personal p WHERE p.perNumTelefono = :perNumTelefono")
    , @NamedQuery(name = "Personal.findByPerSexo", query = "SELECT p FROM Personal p WHERE p.perSexo = :perSexo")
    , @NamedQuery(name = "Personal.findByPrRfc", query = "SELECT p FROM Personal p WHERE p.prRfc = :prRfc")
    , @NamedQuery(name = "Personal.findByPerTurno", query = "SELECT p FROM Personal p WHERE p.perTurno = :perTurno")
    , @NamedQuery(name = "Personal.findByPerEstado", query = "SELECT p FROM Personal p WHERE p.perEstado = :perEstado")})
public class Personal implements Serializable {

    private static final long serialVersionUID = 1L;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "per_num_telefono")
    private String perNumTelefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "per_sexo")
    private Character perSexo;
    @Id
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalUsuariosUsuId")
    private Collection<Registros> registrosCollection;
    @JoinColumn(name = "usuarios_usu_id", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private Usuarios usuariosUsuId;

    public Personal() {
    }

    public Personal(String prRfc) {
        this.prRfc = prRfc;
    }

    public Personal(String prRfc, String perNombres, String perApellidos, Date perFechaNac, String perNumPersonal, String perNumTelefono, Character perSexo, String perTurno, short perEstado) {
        this.prRfc = prRfc;
        this.perNombres = perNombres;
        this.perApellidos = perApellidos;
        this.perFechaNac = perFechaNac;
        this.perNumPersonal = perNumPersonal;
        this.perNumTelefono = perNumTelefono;
        this.perSexo = perSexo;
        this.perTurno = perTurno;
        this.perEstado = perEstado;
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
    public Collection<Registros> getRegistrosCollection() {
        return registrosCollection;
    }

    public void setRegistrosCollection(Collection<Registros> registrosCollection) {
        this.registrosCollection = registrosCollection;
    }

    public Usuarios getUsuariosUsuId() {
        return usuariosUsuId;
    }

    public void setUsuariosUsuId(Usuarios usuariosUsuId) {
        this.usuariosUsuId = usuariosUsuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prRfc != null ? prRfc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personal)) {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.prRfc == null && other.prRfc != null) || (this.prRfc != null && !this.prRfc.equals(other.prRfc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.entidades.Personal[ prRfc=" + prRfc + " ]";
    }
    
}
