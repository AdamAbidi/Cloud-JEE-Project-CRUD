/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesEtud.service;
import java.sql.*;  

import entitiesEtud.Etudiant;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
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
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author User
 */
@Stateless
@Path("entitiesetud.etudiant")
//@Path("/userInfo")
public class EtudiantFacadeREST extends AbstractFacade<Etudiant> {

     @PersistenceContext(unitName = "ProjetCloudPU")
    public EntityManager em;
    
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
    
    public EtudiantFacadeREST() {
        super(Etudiant.class);
        //this.em =new EntityManager("jdbc:mysql://localhost/ao_test", "user", "password");
        
    }

     public static void inserer(Etudiant entity,HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException  {
        PrintWriter out = response.getWriter(); 
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        Statement stmt=con.createStatement();  
        PreparedStatement st = con.prepareStatement("INSERT INTO `etudiant`(`id_etudiant`, `classe`, `moyenne_annuelle`, `absenteisme`)  values(?, ?,?, ?)"); 
        st.setInt(1, entity.getIdEtudiant());
        st.setString(2, entity.getClasse());
        st.setFloat(3, entity.getMoyenneAnnuelle());
        st.setFloat(4, entity.getAbsenteisme());
        st.executeUpdate(); 
        ResultSet rs=stmt.executeQuery("select * from etudiant"); 
        while(rs.next())  
            out.println("<html><body><b>Successfully Inserted"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+ "</b></body></html>");  
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
        rs=stmt.executeQuery("select id_etudiant , nom , prenom , classe , moyenne_annuelle , absenteisme from etudiant , personne where id_etudiant = id and nom = "+
                  nom+" and prenom = "+prenom+ " and classe = "+classe+" group by id,nom,classe order by "+tri);
        
         
         
      // rs=stmt.executeQuery("select classe from etudiant");
     
               List<String> l = new ArrayList<String>();

        while(rs.next()) {
            l.add(String.valueOf(rs.getFloat(1)));
            l.add(rs.getString(2));
            l.add(rs.getString(3));
            l.add(rs.getString(4));
            l.add(String.valueOf(rs.getFloat(5)));
            l.add(String.valueOf(rs.getFloat(6)));}
            
        con.close();
    
        
        return l ;
        
    }
     
    
    public static void update(HttpServletResponse response,int id , String classe , Float abs , Float moy) throws SQLException, IOException, ClassNotFoundException  {
        PrintWriter out = response.getWriter(); 
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        Statement stmt=con.createStatement();  
        ResultSet rs = null ;
        if (!classe.isEmpty()){
            PreparedStatement st = con.prepareStatement("UPDATE etudiant SET classe = ? WHERE id_etudiant = ?"); 
                st.setString(1, classe);
                st.setInt(2, id);
                st.executeUpdate(); 
        }
        
        if (abs != null){
            PreparedStatement st = con.prepareStatement("UPDATE etudiant SET absenteisme = ? WHERE id_etudiant = ?"); 
                st.setFloat(1, abs);
                st.setInt(2, id);
                st.executeUpdate(); 
        }
        if (moy != null){
            PreparedStatement st = con.prepareStatement("UPDATE etudiant SET moyenne_annuelle = ? WHERE id_etudiant = ?"); 
                st.setFloat(1, moy);
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
            PreparedStatement st = con.prepareStatement("DELETE FROM etudiant WHERE id_etudiant = ?"); 
                st.setInt(1, id);
               
                st.executeUpdate(); 
        }
        con.close();
    }
     
     

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Etudiant entity) {
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
    public Etudiant find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Etudiant> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Etudiant> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    @POST
    @Consumes(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Asynchronous
    public void create(@Suspended final AsyncResponse asyncResponse, final Etudiant entity) throws SQLException, ClassNotFoundException {
        doCreate(entity);
        asyncResponse.resume(javax.ws.rs.core.Response.ok().build());
    }

    public void doCreate(Etudiant entity) throws SQLException ,ClassNotFoundException  {
        Connection con = conn() ;
        /* Création de l'objet gérant les requêtes */
        

                PreparedStatement st = con.prepareStatement("insert into etudiant values(?, ?,?, ?)"); 
                st.setInt(1, entity.getIdEtudiant());
                st.setString(2, entity.getClasse());
                st.setFloat(3, entity.getMoyenneAnnuelle());
                st.setFloat(4, entity.getAbsenteisme());
                st.executeUpdate(); 
                st.close(); 
                con.close();
                
            
    }
   
    
}
