/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.entidades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Victor Javier
 */
@Entity
@Table(name = "recetas_medicamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecetasMedicamentos.findAll", query = "SELECT r FROM RecetasMedicamentos r")
    , @NamedQuery(name = "RecetasMedicamentos.findByRecmedId", query = "SELECT r FROM RecetasMedicamentos r WHERE r.recmedId = :recmedId")})
public class RecetasMedicamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recmed_id")
    private Integer recmedId;
    @JoinColumn(name = "recmed_codigo", referencedColumnName = "med_codigo")
    @ManyToOne(optional = false)
    private Medicamento recmedCodigo;
    @JoinColumn(name = "recmed_folio", referencedColumnName = "rec_folio")
    @ManyToOne(optional = false)
    private Recetas recmedFolio;

    public RecetasMedicamentos() {
    }

    public RecetasMedicamentos(Integer recmedId) {
        this.recmedId = recmedId;
    }

    public Integer getRecmedId() {
        return recmedId;
    }

    public void setRecmedId(Integer recmedId) {
        this.recmedId = recmedId;
    }

    public Medicamento getRecmedCodigo() {
        return recmedCodigo;
    }

    public void setRecmedCodigo(Medicamento recmedCodigo) {
        this.recmedCodigo = recmedCodigo;
    }

    public Recetas getRecmedFolio() {
        return recmedFolio;
    }

    public void setRecmedFolio(Recetas recmedFolio) {
        this.recmedFolio = recmedFolio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recmedId != null ? recmedId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecetasMedicamentos)) {
            return false;
        }
        RecetasMedicamentos other = (RecetasMedicamentos) object;
        if ((this.recmedId == null && other.recmedId != null) || (this.recmedId != null && !this.recmedId.equals(other.recmedId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.entidades.RecetasMedicamentos[ recmedId=" + recmedId + " ]";
    }
    
}
