package entitiesEtud;

import entitiesEtud.Personne;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-31T02:00:22")
@StaticMetamodel(Etudiant.class)
public class Etudiant_ { 

    public static volatile SingularAttribute<Etudiant, String> classe;
    public static volatile SingularAttribute<Etudiant, Float> moyenneAnnuelle;
    public static volatile SingularAttribute<Etudiant, Personne> personne;
    public static volatile SingularAttribute<Etudiant, Float> absenteisme;
    public static volatile SingularAttribute<Etudiant, Integer> idEtudiant;

}