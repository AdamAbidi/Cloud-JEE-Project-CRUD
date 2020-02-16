/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesEtud;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "etudiant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etudiant.findAll", query = "SELECT e FROM Etudiant e"),
    @NamedQuery(name = "Etudiant.findByIdEtudiant", query = "SELECT e FROM Etudiant e WHERE e.idEtudiant = :idEtudiant"),
    @NamedQuery(name = "Etudiant.findByClasse", query = "SELECT e FROM Etudiant e WHERE e.classe = :classe"),
    @NamedQuery(name = "Etudiant.findByMoyenneAnnuelle", query = "SELECT e FROM Etudiant e WHERE e.moyenneAnnuelle = :moyenneAnnuelle"),
    @NamedQuery(name = "Etudiant.findByAbsenteisme", query = "SELECT e FROM Etudiant e WHERE e.absenteisme = :absenteisme")})
public class Etudiant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_etudiant")
    private Integer idEtudiant;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "classe")
    public String classe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "moyenne_annuelle")
    private float moyenneAnnuelle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "absenteisme")
    private float absenteisme;
    @JoinColumn(name = "id_etudiant", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Personne personne;

    public Etudiant() {
    }

    public Etudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Etudiant(Integer idEtudiant, String classe, float moyenneAnnuelle, float absenteisme) {
        this.idEtudiant = idEtudiant;
        this.classe = classe;
        this.moyenneAnnuelle = moyenneAnnuelle;
        this.absenteisme = absenteisme;
    }

    public Etudiant(int id, String classe, float moy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public float getMoyenneAnnuelle() {
        return moyenneAnnuelle;
    }

    public void setMoyenneAnnuelle(float moyenneAnnuelle) {
        this.moyenneAnnuelle = moyenneAnnuelle;
    }

    public float getAbsenteisme() {
        return absenteisme;
    }

    public void setAbsenteisme(float absenteisme) {
        this.absenteisme = absenteisme;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEtudiant != null ? idEtudiant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etudiant)) {
            return false;
        }
        Etudiant other = (Etudiant) object;
        if ((this.idEtudiant == null && other.idEtudiant != null) || (this.idEtudiant != null && !this.idEtudiant.equals(other.idEtudiant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiesEtud.Etudiant[ idEtudiant=" + idEtudiant + " ]";
    }
    
}
