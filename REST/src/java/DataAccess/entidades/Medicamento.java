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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Victor Javier
 */
@Entity
@Table(name = "medicamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicamento.findAll", query = "SELECT m FROM Medicamento m")
    , @NamedQuery(name = "Medicamento.findByMedCodigo", query = "SELECT m FROM Medicamento m WHERE m.medCodigo = :medCodigo")
    , @NamedQuery(name = "Medicamento.findByMedFechaCaducidad", query = "SELECT m FROM Medicamento m WHERE m.medFechaCaducidad = :medFechaCaducidad")
    , @NamedQuery(name = "Medicamento.findByMedGramaje", query = "SELECT m FROM Medicamento m WHERE m.medGramaje = :medGramaje")
    , @NamedQuery(name = "Medicamento.findByMedNombre", query = "SELECT m FROM Medicamento m WHERE m.medNombre = :medNombre")})
public class Medicamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "med_codigo")
    private Integer medCodigo;
    @Column(name = "med_fecha_caducidad")
    @Temporal(TemporalType.DATE)
    private Date medFechaCaducidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "med_gramaje")
    private Float medGramaje;
    @Size(max = 70)
    @Column(name = "med_nombre")
    private String medNombre;
    @JoinTable(name = "recetas_medicamentos", joinColumns = {
        @JoinColumn(name = "recmed_codigo", referencedColumnName = "med_codigo")}, inverseJoinColumns = {
        @JoinColumn(name = "recmed_folio", referencedColumnName = "rec_folio")})
    @ManyToMany
    private Collection<Receta> recetaCollection;

    public Medicamento() {
    }

    public Medicamento(Integer medCodigo) {
        this.medCodigo = medCodigo;
    }

    public Integer getMedCodigo() {
        return medCodigo;
    }

    public void setMedCodigo(Integer medCodigo) {
        this.medCodigo = medCodigo;
    }

    public Date getMedFechaCaducidad() {
        return medFechaCaducidad;
    }

    public void setMedFechaCaducidad(Date medFechaCaducidad) {
        this.medFechaCaducidad = medFechaCaducidad;
    }

    public Float getMedGramaje() {
        return medGramaje;
    }

    public void setMedGramaje(Float medGramaje) {
        this.medGramaje = medGramaje;
    }

    public String getMedNombre() {
        return medNombre;
    }

    public void setMedNombre(String medNombre) {
        this.medNombre = medNombre;
    }

    @XmlTransient
    public Collection<Receta> getRecetaCollection() {
        return recetaCollection;
    }

    public void setRecetaCollection(Collection<Receta> recetaCollection) {
        this.recetaCollection = recetaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medCodigo != null ? medCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicamento)) {
            return false;
        }
        Medicamento other = (Medicamento) object;
        if ((this.medCodigo == null && other.medCodigo != null) || (this.medCodigo != null && !this.medCodigo.equals(other.medCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Medicamento[ medCodigo=" + medCodigo + " ]";
    }
    
}
