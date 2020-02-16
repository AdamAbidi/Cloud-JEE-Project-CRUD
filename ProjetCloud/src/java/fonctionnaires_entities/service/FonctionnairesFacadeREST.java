/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fonctionnaires_entities.service;

import entitiesEtud.service.EtudiantFacadeREST;
import fonctionnaires_entities.Fonctionnaires;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import universitaires_entities.Universitaires;

/**
 *
 * @author User
 */
@Stateless
@Path("fonctionnaires_entities.fonctionnaires")
public class FonctionnairesFacadeREST extends AbstractFacade<Fonctionnaires> {

    @PersistenceContext(unitName = "ProjetCloudPU")
    private EntityManager em;

    public FonctionnairesFacadeREST() {
        super(Fonctionnaires.class);
    }
 public static Connection conn() throws SQLException{  
  
        try {  
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EtudiantFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/projetcloud","root","");  
        return con ;
//here sonoo is database name, root is username and password  

    }  
    public static void inserer(Fonctionnaires entity,HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException  {
        PrintWriter out = response.getWriter(); 
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        Statement stmt=con.createStatement();  
        PreparedStatement st = con.prepareStatement("INSERT INTO `fonctionnaires`(`id_fonc`, `fonction`)  values(?, ?)"); 
        st.setInt(1, entity.getIdFonc());
        st.setString(2, entity.getFonction());
        
        st.executeUpdate(); 
        
        con.close();  
    }
       public static List<String> Afficher(HttpServletResponse response,String nom,String prenom,String fonction) throws SQLException, IOException, ClassNotFoundException  {
        PrintWriter out = response.getWriter(); 
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        Statement stmt=con.createStatement();  
        
        
        ResultSet rs = null ;
        
        if (fonction.isEmpty()){fonction="FALSE";}
        else {fonction = "'"+fonction+"'" ;}
        if (nom.isEmpty()){nom="FALSE";}
        else {nom = "'"+nom+"'" ;}
        if (prenom.isEmpty()){prenom="FALSE";}
        else {prenom = "'"+prenom+"'" ;}
        rs=stmt.executeQuery("select id_fonc , nom , prenom , fonction  from fonctionnaires , personne where id_fonc = id and nom = "+
                  nom+" and prenom = "+prenom+ " and fonction = "+fonction+" group by id,nom,fonction ");
        
        List<String> l = new ArrayList<String>();

        while(rs.next()) {
            l.add(String.valueOf(rs.getFloat(1)));
            l.add(rs.getString(2));
            l.add(rs.getString(3));
            l.add(rs.getString(4));
            
        }
            
        con.close();
    
        
        return l ;
        
    }
     
    public static void update(HttpServletResponse response,int id , String fonction ) throws SQLException, IOException, ClassNotFoundException  {
        PrintWriter out = response.getWriter(); 
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        Statement stmt=con.createStatement();  
        ResultSet rs = null ;
        if (!fonction.isEmpty()){
            PreparedStatement st = con.prepareStatement("UPDATE fonctionnaires SET fonction = ? WHERE id_fonc = ?"); 
                st.setString(1, fonction);
                st.setInt(2, id);
                st.executeUpdate(); 
        }
        
        
        con.close();
    }
    
    
    public static void delete(HttpServletResponse response,Integer id) throws SQLException, IOException, ClassNotFoundException  {
        PrintWriter out = response.getWriter(); 
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        Statement stmt=con.createStatement();  
        ResultSet rs = null ;
        
         if (id != null){
            PreparedStatement st = con.prepareStatement("DELETE FROM fonctionnaires WHERE id_fonc = ?"); 
                st.setInt(1, id);
                st.executeUpdate(); 
        }
        con.close();
    }
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Fonctionnaires entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Fonctionnaires entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Fonctionnaires find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Fonctionnaires> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Fonctionnaires> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
