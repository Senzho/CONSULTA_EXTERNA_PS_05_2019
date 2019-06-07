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
import javax.persistence.ManyToMany;
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
@Table(name = "recetas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recetas.findAll", query = "SELECT r FROM Recetas r")
    , @NamedQuery(name = "Recetas.findByRecFolio", query = "SELECT r FROM Recetas r WHERE r.recFolio = :recFolio")
    , @NamedQuery(name = "Recetas.findByRecFechaVencimiento", query = "SELECT r FROM Recetas r WHERE r.recFechaVencimiento = :recFechaVencimiento")
    , @NamedQuery(name = "Recetas.findByRecFechaCreacion", query = "SELECT r FROM Recetas r WHERE r.recFechaCreacion = :recFechaCreacion")})
public class Recetas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rec_folio")
    private Integer recFolio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rec_fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date recFechaVencimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rec_fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date recFechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "rec_instrucciones")
    private String recInstrucciones;
    @ManyToMany(mappedBy = "recetasCollection")
    private Collection<Medicamento> medicamentoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conFolioReceta")
    private Collection<Consultas> consultasCollection;

    public Recetas() {
    }

    public Recetas(Integer recFolio) {
        this.recFolio = recFolio;
    }

    public Recetas(Integer recFolio, Date recFechaVencimiento, Date recFechaCreacion, String recInstrucciones) {
        this.recFolio = recFolio;
        this.recFechaVencimiento = recFechaVencimiento;
        this.recFechaCreacion = recFechaCreacion;
        this.recInstrucciones = recInstrucciones;
    }

    public Integer getRecFolio() {
        return recFolio;
    }

    public void setRecFolio(Integer recFolio) {
        this.recFolio = recFolio;
    }

    public Date getRecFechaVencimiento() {
        return recFechaVencimiento;
    }

    public void setRecFechaVencimiento(Date recFechaVencimiento) {
        this.recFechaVencimiento = recFechaVencimiento;
    }

    public Date getRecFechaCreacion() {
        return recFechaCreacion;
    }

    public void setRecFechaCreacion(Date recFechaCreacion) {
        this.recFechaCreacion = recFechaCreacion;
    }

    public String getRecInstrucciones() {
        return recInstrucciones;
    }

    public void setRecInstrucciones(String recInstrucciones) {
        this.recInstrucciones = recInstrucciones;
    }

    @XmlTransient
    public Collection<Medicamento> getMedicamentoCollection() {
        return medicamentoCollection;
    }

    public void setMedicamentoCollection(Collection<Medicamento> medicamentoCollection) {
        this.medicamentoCollection = medicamentoCollection;
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
        hash += (recFolio != null ? recFolio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recetas)) {
            return false;
        }
        Recetas other = (Recetas) object;
        if ((this.recFolio == null && other.recFolio != null) || (this.recFolio != null && !this.recFolio.equals(other.recFolio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.entidades.Recetas[ recFolio=" + recFolio + " ]";
    }
    
}
