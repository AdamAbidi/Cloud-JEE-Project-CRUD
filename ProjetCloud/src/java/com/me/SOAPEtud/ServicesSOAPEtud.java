/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.SOAPEtud;

import entitiesEtud.service.EtudiantFacadeREST;
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
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author User
 */
@WebService(serviceName = "ServicesSOAPEtud")
public class ServicesSOAPEtud {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     * @param chaine
     * @return 
     */
    
    
    @WebMethod(operationName = "Max_Moyenne")
    public List<String> Max_Moyenne() throws SQLException {
        //TODO write your implementation code here:
        try {  
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EtudiantFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/projetcloud","root","");  
        con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        java.util.List<String> l = new ArrayList<String>();
        
        Statement stmt=con.createStatement();
        ResultSet rs = null ;
        rs=stmt.executeQuery("select nom , prenom,classe,max(moyenne_annuelle) from etudiant,personne where id = id_etudiant group by classe");
       while(rs.next()) {
            
            l.add(rs.getString(1));
            l.add(rs.getString(2));
            l.add(rs.getString(3));
            l.add(String.valueOf(rs.getFloat(4)));
        }
        con.close();
        return l;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "nbr_admis")
    public List<String> Liste_admis() throws SQLException {
        try {  
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EtudiantFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/projetcloud","root","");  
        con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        java.util.List<String> l = new ArrayList<String>();
        Statement stmt=con.createStatement();
        ResultSet rs = null ;
        rs=stmt.executeQuery("select nom , prenom,classe,moyenne_annuelle from etudiant,personne where id = id_etudiant and moyenne_annuelle >= 10 group by id,classe");
       while(rs.next()) {
            
            l.add(rs.getString(1));
            l.add(rs.getString(2));
            l.add(rs.getString(3));
            l.add(String.valueOf(rs.getFloat(4)));
        }
        con.close();
        return l;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "moyenne_classe")
    public List<String> moyenne_classe() throws SQLException {
        try {  
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EtudiantFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/projetcloud","root","");  
        con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        java.util.List<String> l = new ArrayList<String>();
        Statement stmt=con.createStatement();
        ResultSet rs = null ;
        rs=stmt.executeQuery("select classe,avg(moyenne_annuelle) from etudiant,personne where id = id_etudiant group by classe");
       while(rs.next()) {
            
            l.add("");
            l.add("");
            l.add(rs.getString(1));
            l.add(String.valueOf(rs.getFloat(2)));
        }
        con.close();
        return l;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "nbr_admis_1")
    @RequestWrapper(className = "com.nbr_admis_1")
    @ResponseWrapper(className = "com.nbr_admis_1Response")
    public List<String> nbr_admis() throws SQLException {
        try {  
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EtudiantFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/projetcloud","root","");  
        con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        java.util.List<String> l = new ArrayList<String>();
        Statement stmt=con.createStatement();
        ResultSet rs = null ;
        rs=stmt.executeQuery("select classe,count(*) from etudiant,personne where id = id_etudiant and moyenne_annuelle >= 10 group by classe");
       while(rs.next()) {
            
            l.add("");
            l.add("");
            l.add(rs.getString(1));
            l.add(String.valueOf(rs.getInt(2)));
        }
        con.close();
        return l;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Abs_max")
    public List<String> Abs_max() throws SQLException {
        try {  
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EtudiantFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/projetcloud","root","");  
        con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        java.util.List<String> l = new ArrayList<String>();
        Statement stmt=con.createStatement();
        ResultSet rs = null ;
        rs=stmt.executeQuery("select classe,max(absenteisme) from etudiant,personne where id = id_etudiant group by classe");
       while(rs.next()) {
            
            l.add("");
            l.add("");
            l.add(rs.getString(1));
            l.add(String.valueOf(rs.getInt(2)+"%"));
        }
        con.close();
        return l;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Abs_min")
    public List<String> Abs_min() throws SQLException {
        try {  
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EtudiantFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/projetcloud","root","");  
        con=DriverManager.getConnection(  "jdbc:mysql://localhost/projetcloud","root","");  
        java.util.List<String> l = new ArrayList<String>();
        Statement stmt=con.createStatement();
        ResultSet rs = null ;
        rs=stmt.executeQuery("select classe,min(absenteisme) from etudiant,personne where id = id_etudiant group by classe");
       while(rs.next()) {
            
            l.add("");
            l.add("");
            l.add(rs.getString(1));
            l.add(String.valueOf(rs.getInt(2)+"%"));
        }
        con.close();
        return l;
    }
    
}
