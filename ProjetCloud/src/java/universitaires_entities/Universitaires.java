/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universitaires_entities;

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
@Table(name = "universitaires")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Universitaires.findAll", query = "SELECT u FROM Universitaires u"),
    @NamedQuery(name = "Universitaires.findByIdProf", query = "SELECT u FROM Universitaires u WHERE u.idProf = :idProf"),
    @NamedQuery(name = "Universitaires.findByClasse", query = "SELECT u FROM Universitaires u WHERE u.classe = :classe"),
    @NamedQuery(name = "Universitaires.findByGrade", query = "SELECT u FROM Universitaires u WHERE u.grade = :grade"),
    @NamedQuery(name = "Universitaires.findByAbsenteisme", query = "SELECT u FROM Universitaires u WHERE u.absenteisme = :absenteisme")})
public class Universitaires implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_prof")
    private Integer idProf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "classe")
    private String classe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "grade")
    private String grade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "absenteisme")
    private float absenteisme;

    public Universitaires() {
    }

    public Universitaires(Integer idProf) {
        this.idProf = idProf;
    }

    public Universitaires(Integer idProf, String classe, String grade, float absenteisme) {
        this.idProf = idProf;
        this.classe = classe;
        this.grade = grade;
        this.absenteisme = absenteisme;
    }

    public Integer getIdProf() {
        return idProf;
    }

    public void setIdProf(Integer idProf) {
        this.idProf = idProf;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public float getAbsenteisme() {
        return absenteisme;
    }

    public void setAbsenteisme(float absenteisme) {
        this.absenteisme = absenteisme;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProf != null ? idProf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Universitaires)) {
            return false;
        }
        Universitaires other = (Universitaires) object;
        if ((this.idProf == null && other.idProf != null) || (this.idProf != null && !this.idProf.equals(other.idProf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "universitaires_entities.Universitaires[ idProf=" + idProf + " ]";
    }
    
}
