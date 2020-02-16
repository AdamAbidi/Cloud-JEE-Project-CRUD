/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesEtud.service;

import entitiesEtud.Etudiant;
import entitiesEtud.Personne;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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

/**
 *
 * @author User
 */
@Stateless
@Path("entitiesetud.personne")
public class PersonneFacadeREST extends AbstractFacade<Personne> {

    @PersistenceContext(unitName = "ProjetCloudPU")
    private EntityManager em;

    public PersonneFacadeREST() {
        super(Personne.class);
    }
public static void inserer(Personne entity,HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException  {
         PrintWriter out = response.getWriter(); 
            out.println("<html><body><b>Successfully Inserted"
                        + "</b></body></html>"); 
      
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
//here sonoo is database name, root is username and password  
Statement stmt=con.createStatement();  
 

                out.println("<html><body><b>Successfully Inserted"+entity.getNom()+"  "+"  "+entity.getPrenom()+ "</b></body></html>");  

PreparedStatement st = con.prepareStatement("INSERT INTO `personne`(`id`, `nom`, `prenom`) VALUES (?,?,?)"); 
                st.setInt(1, entity.getId());
                st.setString(2, entity.getNom());
                st.setString(3, entity.getPrenom());
                
                st.executeUpdate(); 
                ResultSet rs=stmt.executeQuery("select * from etudiant"); 
                while(rs.next())  
out.println("<html><body><b>Successfully Inserted"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+ "</b></body></html>");  
con.close();  

 
    }

public static int getFinalID(HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException  {
        PrintWriter out = response.getWriter(); 
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        Statement stmt=con.createStatement();  
        
        ResultSet rs=stmt.executeQuery("select * from personne order by id desc"); 
        rs.next();
        out.println("<html><body><b>Successfully Inserted"+rs.getInt(1)+"</b></body></html>");  
        return rs.getInt(1) ;  
    }


     public static void update(HttpServletResponse response,int id,String nom , String prenom) throws SQLException, IOException, ClassNotFoundException  {
        PrintWriter out = response.getWriter(); 
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        Statement stmt=con.createStatement();  
        ResultSet rs = null ;
        
        if (!nom.isEmpty()){
            PreparedStatement st = con.prepareStatement("UPDATE personne SET nom = ? WHERE id = ?"); 
                st.setString(1, nom);
                st.setInt(2, id);
                st.executeUpdate(); 
            
        }
        if (!prenom.isEmpty()){
                PreparedStatement st = con.prepareStatement("UPDATE personne SET prenom = ? WHERE id = ?"); 
                st.setString(1, prenom);
                st.setInt(2, id);
                st.executeUpdate();         }
        
        
        con.close();
    }
     
     public static void delete(HttpServletResponse response,Integer id) throws SQLException, IOException, ClassNotFoundException  {
        PrintWriter out = response.getWriter(); 
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        Statement stmt=con.createStatement();  
        ResultSet rs = null ;
        
         if (id != null){
            PreparedStatement st = con.prepareStatement("DELETE FROM personne WHERE id = ?"); 
                st.setInt(1, id);
               
                st.executeUpdate(); 
        }
        con.close();
    }
     
     
     
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Personne entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Personne entity) {
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
    public Personne find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Personne> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Personne> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
