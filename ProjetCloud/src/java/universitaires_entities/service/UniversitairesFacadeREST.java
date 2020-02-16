/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universitaires_entities.service;

import entitiesEtud.service.EtudiantFacadeREST;
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
@Path("universitaires_entities.universitaires")
public class UniversitairesFacadeREST extends AbstractFacade<Universitaires> {

    @PersistenceContext(unitName = "ProjetCloudPU")
    private EntityManager em;

    public UniversitairesFacadeREST() {
        super(Universitaires.class);
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
    public static void inserer(Universitaires entity,HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException  {
        PrintWriter out = response.getWriter(); 
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        Statement stmt=con.createStatement();  
        PreparedStatement st = con.prepareStatement("INSERT INTO `universitaires`(`id_prof`, `classe`, `grade`, `absenteisme`)  values(?, ?,?, ?)"); 
        st.setInt(1, entity.getIdProf());
        st.setString(2, entity.getClasse());
        st.setString(3, entity.getGrade());
        st.setFloat(4, entity.getAbsenteisme());
        st.executeUpdate(); 
        
        con.close();  
    }
    
      public static List<String> Afficher(HttpServletResponse response,String nom,String prenom,String classe,String tri) throws SQLException, IOException, ClassNotFoundException  {
        PrintWriter out = response.getWriter(); 
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        Statement stmt=con.createStatement();  
        
        
        ResultSet rs = null ;
        if (tri.isEmpty()){tri="true";}
        if (classe.isEmpty()){classe="FALSE";}
        else {classe = "'"+classe+"'" ;}
        if (nom.isEmpty()){nom="FALSE";}
        else {nom = "'"+nom+"'" ;}
        if (prenom.isEmpty()){prenom="FALSE";}
        else {prenom = "'"+prenom+"'" ;}
        rs=stmt.executeQuery("select id_prof , nom , prenom , classe , grade , absenteisme from universitaires , personne where id_prof = id and nom = "+
                  nom+" and prenom = "+prenom+ " and classe = "+classe+" group by id,nom,classe order by "+tri);
        
        List<String> l = new ArrayList<String>();

        while(rs.next()) {
            l.add(String.valueOf(rs.getFloat(1)));
            l.add(rs.getString(2));
            l.add(rs.getString(3));
            l.add(rs.getString(4));
            l.add(rs.getString(5));
            l.add(String.valueOf(rs.getFloat(6)));
        }
            
        con.close();
    
        
        return l ;
        
    }
     
      
       public static void update(HttpServletResponse response,int id , String classe ,String grade, Float abs ) throws SQLException, IOException, ClassNotFoundException  {
        PrintWriter out = response.getWriter(); 
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        Statement stmt=con.createStatement();  
        ResultSet rs = null ;
        if (!classe.isEmpty()){
            PreparedStatement st = con.prepareStatement("UPDATE universitaires SET classe = ? WHERE id_prof = ?"); 
                st.setString(1, classe);
                st.setInt(2, id);
                st.executeUpdate(); 
        }
        
        if (abs != null){
            PreparedStatement st = con.prepareStatement("UPDATE universitaires SET absenteisme = ? WHERE id_prof = ?"); 
                st.setFloat(1, abs);
                st.setInt(2, id);
                st.executeUpdate(); 
        }
        
        if (!grade.isEmpty()){
            PreparedStatement st = con.prepareStatement("UPDATE universitaires SET grade = ? WHERE id_prof = ?"); 
                st.setString(1, grade);
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
            PreparedStatement st = con.prepareStatement("DELETE FROM universitaires WHERE id_prof = ?"); 
                st.setInt(1, id);
               
                st.executeUpdate(); 
        }
        con.close();
    }
    
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Universitaires entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Universitaires entity) {
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
    public Universitaires find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Universitaires> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Universitaires> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
