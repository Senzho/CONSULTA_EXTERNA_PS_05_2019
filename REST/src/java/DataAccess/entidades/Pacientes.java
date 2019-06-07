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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "pacientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pacientes.findAll", query = "SELECT p FROM Pacientes p")
    , @NamedQuery(name = "Pacientes.findByPacNumSeguro", query = "SELECT p FROM Pacientes p WHERE p.pacNumSeguro = :pacNumSeguro")
    , @NamedQuery(name = "Pacientes.findByPacNombres", query = "SELECT p FROM Pacientes p WHERE p.pacNombres = :pacNombres")
    , @NamedQuery(name = "Pacientes.findByPacApellidos", query = "SELECT p FROM Pacientes p WHERE p.pacApellidos = :pacApellidos")
    , @NamedQuery(name = "Pacientes.findByPacFechaNac", query = "SELECT p FROM Pacientes p WHERE p.pacFechaNac = :pacFechaNac")
    , @NamedQuery(name = "Pacientes.findByPacSexo", query = "SELECT p FROM Pacientes p WHERE p.pacSexo = :pacSexo")
    , @NamedQuery(name = "Pacientes.findByPacNumTelefono", query = "SELECT p FROM Pacientes p WHERE p.pacNumTelefono = :pacNumTelefono")})
public class Pacientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pac_num_seguro")
    private Integer pacNumSeguro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "pac_nombres")
    private String pacNombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "pac_apellidos")
    private String pacApellidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pac_fecha_nac")
    @Temporal(TemporalType.DATE)
    private Date pacFechaNac;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "pac_alergias")
    private String pacAlergias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pac_sexo")
    private Character pacSexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "pac_num_telefono")
    private String pacNumTelefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "citNumSeguroPaciente")
    private Collection<Citas> citasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conSeguroPaciente")
    private Collection<Consultas> consultasCollection;

    public Pacientes() {
    }

    public Pacientes(Integer pacNumSeguro) {
        this.pacNumSeguro = pacNumSeguro;
    }

    public Pacientes(Integer pacNumSeguro, String pacNombres, String pacApellidos, Date pacFechaNac, String pacAlergias, Character pacSexo, String pacNumTelefono) {
        this.pacNumSeguro = pacNumSeguro;
        this.pacNombres = pacNombres;
        this.pacApellidos = pacApellidos;
        this.pacFechaNac = pacFechaNac;
        this.pacAlergias = pacAlergias;
        this.pacSexo = pacSexo;
        this.pacNumTelefono = pacNumTelefono;
    }

    public Integer getPacNumSeguro() {
        return pacNumSeguro;
    }

    public void setPacNumSeguro(Integer pacNumSeguro) {
        this.pacNumSeguro = pacNumSeguro;
    }

    public String getPacNombres() {
        return pacNombres;
    }

    public void setPacNombres(String pacNombres) {
        this.pacNombres = pacNombres;
    }

    public String getPacApellidos() {
        return pacApellidos;
    }

    public void setPacApellidos(String pacApellidos) {
        this.pacApellidos = pacApellidos;
    }

    public Date getPacFechaNac() {
        return pacFechaNac;
    }

    public void setPacFechaNac(Date pacFechaNac) {
        this.pacFechaNac = pacFechaNac;
    }

    public String getPacAlergias() {
        return pacAlergias;
    }

    public void setPacAlergias(String pacAlergias) {
        this.pacAlergias = pacAlergias;
    }

    public Character getPacSexo() {
        return pacSexo;
    }

    public void setPacSexo(Character pacSexo) {
        this.pacSexo = pacSexo;
    }

    public String getPacNumTelefono() {
        return pacNumTelefono;
    }

    public void setPacNumTelefono(String pacNumTelefono) {
        this.pacNumTelefono = pacNumTelefono;
    }

    @XmlTransient
    public Collection<Citas> getCitasCollection() {
        return citasCollection;
    }

    public void setCitasCollection(Collection<Citas> citasCollection) {
        this.citasCollection = citasCollection;
    }

    @XmlTransient
    public Collection<Consultas> getConsultasCollection() {
        return consultasCollection;
    }

    public void setConsultasCollection(Collection<Consultas> consultasCollection) {
        this.consultasCollection = consultasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pacNumSeguro != null ? pacNumSeguro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pacientes)) {
            return false;
        }
        Pacientes other = (Pacientes) object;
        if ((this.pacNumSeguro == null && other.pacNumSeguro != null) || (this.pacNumSeguro != null && !this.pacNumSeguro.equals(other.pacNumSeguro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.entidades.Pacientes[ pacNumSeguro=" + pacNumSeguro + " ]";
    }
    
}
