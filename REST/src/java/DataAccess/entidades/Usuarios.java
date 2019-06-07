/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Victor Javier
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
    , @NamedQuery(name = "Usuarios.findByUsuId", query = "SELECT u FROM Usuarios u WHERE u.usuId = :usuId")
    , @NamedQuery(name = "Usuarios.findByUsuNombre", query = "SELECT u FROM Usuarios u WHERE u.usuNombre = :usuNombre")
    , @NamedQuery(name = "Usuarios.findByUsuContrasena", query = "SELECT u FROM Usuarios u WHERE u.usuContrasena = :usuContrasena")
    , @NamedQuery(name = "Usuarios.findByUsuRol", query = "SELECT u FROM Usuarios u WHERE u.usuRol = :usuRol")
    , @NamedQuery(name = "Usuarios.findByUsuNombreAndUsuContrasena", query = "SELECT u FROM Usuarios u WHERE u.usuNombre = :usuNombre AND u.usuContrasena = :usuContrasena")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usu_id")
    private Integer usuId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "usu_nombre")
    private String usuNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "usu_contrasena")
    private String usuContrasena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usu_rol")
    private String usuRol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosUsuId")
    private Collection<Personal> personalCollection;

    public Usuarios() {
    }

    public Usuarios(Integer usuId) {
        this.usuId = usuId;
    }

    public Usuarios(Integer usuId, String usuNombre, String usuContrasena, String usuRol) {
        this.usuId = usuId;
        this.usuNombre = usuNombre;
        this.usuContrasena = usuContrasena;
        this.usuRol = usuRol;
    }
    
    public Usuarios(JSONObject jObjeto) throws JSONException {
        try {
            this.usuId = jObjeto.getInt("usuId");
        } catch(JSONException excepcion) {
            //Logger
        }
        this.usuNombre = jObjeto.getString("usuNombre");
        this.usuContrasena = jObjeto.getString("usuContrasena");
        this.usuRol = jObjeto.getString("usuRol");
    }

    public Integer getUsuId() {
        return usuId;
    }

    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuContrasena() {
        return usuContrasena;
    }

    public void setUsuContrasena(String usuContrasena) {
        this.usuContrasena = usuContrasena;
    }

    public String getUsuRol() {
        return usuRol;
    }

    public void setUsuRol(String usuRol) {
        this.usuRol = usuRol;
    }

    @XmlTransient
    public Collection<Personal> getPersonalCollection() {
        return personalCollection;
    }

    public void setPersonalCollection(Collection<Personal> personalCollection) {
        this.personalCollection = personalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.entidades.Usuarios[ usuId=" + usuId + " ]";
    }
    
}
