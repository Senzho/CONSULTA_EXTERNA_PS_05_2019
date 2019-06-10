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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Victor Javier
 */
@Entity
@Table(name = "sesiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sesiones.findAll", query = "SELECT s FROM Sesiones s")
    , @NamedQuery(name = "Sesiones.findBySesId", query = "SELECT s FROM Sesiones s WHERE s.sesId = :sesId")
    , @NamedQuery(name = "Sesiones.findByToken", query = "SELECT s FROM Sesiones s WHERE s.token = :token")})
public class Sesiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ses_id")
    private Integer sesId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "token")
    private String token;

    public Sesiones() {
    }

    public Sesiones(Integer sesId) {
        this.sesId = sesId;
    }

    public Sesiones(Integer sesId, String token) {
        this.sesId = sesId;
        this.token = token;
    }

    public Integer getSesId() {
        return sesId;
    }

    public void setSesId(Integer sesId) {
        this.sesId = sesId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sesId != null ? sesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sesiones)) {
            return false;
        }
        Sesiones other = (Sesiones) object;
        if ((this.sesId == null && other.sesId != null) || (this.sesId != null && !this.sesId.equals(other.sesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.entidades.Sesiones[ sesId=" + sesId + " ]";
    }
    
}
