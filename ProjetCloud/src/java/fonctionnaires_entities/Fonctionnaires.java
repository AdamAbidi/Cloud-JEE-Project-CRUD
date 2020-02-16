/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fonctionnaires_entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "fonctionnaires")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fonctionnaires.findAll", query = "SELECT f FROM Fonctionnaires f"),
    @NamedQuery(name = "Fonctionnaires.findByIdFonc", query = "SELECT f FROM Fonctionnaires f WHERE f.idFonc = :idFonc"),
    @NamedQuery(name = "Fonctionnaires.findByFonction", query = "SELECT f FROM Fonctionnaires f WHERE f.fonction = :fonction")})
public class Fonctionnaires implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_fonc")
    private Integer idFonc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "fonction")
    private String fonction;

    public Fonctionnaires() {
    }

    public Fonctionnaires(Integer idFonc) {
        this.idFonc = idFonc;
    }

    public Fonctionnaires(Integer idFonc, String fonction) {
        this.idFonc = idFonc;
        this.fonction = fonction;
    }

    public Integer getIdFonc() {
        return idFonc;
    }

    public void setIdFonc(Integer idFonc) {
        this.idFonc = idFonc;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFonc != null ? idFonc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fonctionnaires)) {
            return false;
        }
        Fonctionnaires other = (Fonctionnaires) object;
        if ((this.idFonc == null && other.idFonc != null) || (this.idFonc != null && !this.idFonc.equals(other.idFonc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fonctionnaires_entities.Fonctionnaires[ idFonc=" + idFonc + " ]";
    }
    
}
