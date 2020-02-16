/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entitiesEtud.Etudiant;
import entitiesEtud.Personne;
import entitiesEtud.service.EtudiantFacadeREST;
import static entitiesEtud.service.EtudiantFacadeREST.conn;

import entitiesEtud.service.PersonneFacadeREST;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet("/EtudServlet")
public class EtudServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EtudServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EtudServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    @Override
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String ch1 = "AjoutForm" ;
        String ch2 = "ModifForm" ;
        String ch3 = "UpdateForm" ;
        String ch4 = "SuppForm" ;
        String ch5 = "DelForm";
        String ch6 = "StatEtudSOAPForm";
        
        String form_name = getFormName(request,response);
        if (form_name.equals(ch1)){
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            Integer age = Integer.parseInt(request.getParameter("age"));
            String classe = request.getParameter("classe");
            float abs = Float.parseFloat(request.getParameter("abs"));
            float moy = Float.parseFloat(request.getParameter("moy"));
            verif(nom,prenom,age,classe,abs,moy,request,response);
            int id = 0 ;
            try {
                id=PersonneFacadeREST.getFinalID(response)+1;
                Etudiant student = new Etudiant(id, classe, moy , abs) ;
                Personne pers = new Personne(id,  nom,  prenom) ;
                PersonneFacadeREST.inserer(pers,response);
                EtudiantFacadeREST.inserer(student,response);
                RequestDispatcher rd = null;
                rd = getServletContext().getRequestDispatcher("/AjoutEtudJSP.jsp");
                String msg="<html><body><div class='alert success' align='center'><b></b>"+
                    "<span class='closebtn'>&times;</span>"+
                    "<strong>Bien!</strong> Etudiant ajouté</div></body></html>";
                request.setAttribute("resultat", msg);
                rd.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (form_name.equals(ch2)){
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String classe = request.getParameter("classe");
            String tri = request.getParameter("tri");
            RequestDispatcher rd = null;
          
              
            try {
                rd = getServletContext().getRequestDispatcher("/ModifierJSP.jsp");
                
                List<String> l = new ArrayList<String>();
                l =  EtudiantFacadeREST.Afficher(response,nom,prenom,classe,tri);
                request.setAttribute("resultat",l);
                rd.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
        
         if (form_name.equals(ch3)){
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            int id = (int) request.getSession().getAttribute("userId");
            String classe = request.getParameter("classe");
            Float abs = Float.parseFloat(request.getParameter("abs"));
            Float moy = Float.parseFloat(request.getParameter("moy"));
            RequestDispatcher rd = null;
                  

            try {
                rd = getServletContext().getRequestDispatcher("/ModifierJSP.jsp");
                 PersonneFacadeREST.update(response,id,nom,prenom);
                 EtudiantFacadeREST.update(response,id,classe,abs,moy);
                String msg="<html><body><div class='alert success' align='center'><b></b>"+
                    "<span class='closebtn'>&times;</span>"+
                    "<strong>Bien!</strong> Etudiant modifié</div></body></html>";
                request.setAttribute("resultat2", msg);
                rd.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
         
         if (form_name.equals(ch4)){
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String classe = request.getParameter("classe");
            String tri = request.getParameter("tri");
            RequestDispatcher rd = null;
          
              
            try {
                rd = getServletContext().getRequestDispatcher("/SuppEtudJSP.jsp");
                
                List<String> l = new ArrayList<String>();
                l =  EtudiantFacadeREST.Afficher(response,nom,prenom,classe,tri);
                request.setAttribute("resultat",l);
                rd.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
         
         
         if (form_name.equals(ch5)){
            Integer id = Math.round(Float.parseFloat(request.getParameter("IdDel")));
                
            RequestDispatcher rd = null;
            
            try {
                rd = getServletContext().getRequestDispatcher("/SuppEtudJSP.jsp");
                
                List<String> l = new ArrayList<String>();
                EtudiantFacadeREST.delete(response, id);
                PersonneFacadeREST.delete(response, id);
                String msg="<html><body><div class='alert success' align='center'><b></b>"+
                    "<span class='closebtn'>&times;</span>"+
                    "<strong>Bien!</strong> Etudiant modifié</div></body></html>";
                request.setAttribute("resultat3",msg);
                rd.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
         
         
         if (form_name.equals(ch6)){
            RequestDispatcher rd = null;
            String tri = request.getParameter("tri");
            com.me.SOAPEtud.ServicesSOAPEtud service = new com.me.SOAPEtud.ServicesSOAPEtud() ;
            String msg = "" ;
            List<String> l = new ArrayList<String>();
            if(tri.isEmpty()){
                msg="<html><body><div class='alert warning' align='center'><b></b>"+
                    "<span class='closebtn'>&times;</span>"+
                    "<strong>Bien!</strong> Veuillez choisir un service</div></body></html>";
                request.setAttribute("msg",msg);
                
            }
            
            else {
                String cas1 = "moy_max" ;
                
                if (tri.equals(cas1)){
                    try {
                        l=service.Max_Moyenne();
                    } catch (SQLException ex) {
                        Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                String cas2 = "admis" ;
                if (tri.equals(cas2)){
                    try {
                        l = service.Liste_admis();
                    } catch (SQLException ex) {
                        Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                String cas3 = "moy_avg" ;
                if (tri.equals(cas3)){
                    try {
                        l = service.moyenne_classe();
                    } catch (SQLException ex) {
                        Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                String cas4 = "Liste_admis" ;
                if (tri.equals(cas4)){
                    try {
                        l = service.nbr_admis();
                    } catch (SQLException ex) {
                        Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                String cas5 = "abs_max" ;
                if (tri.equals(cas5)){
                    try {
                        l = service.Abs_max();
                    } catch (SQLException ex) {
                        Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                String cas6 = "abs_min" ;
                if (tri.equals(cas6)){
                    try {
                        l = service.Abs_min();
                    } catch (SQLException ex) {
                        Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
            }
            request.setAttribute("ResultService",l);
            rd = getServletContext().getRequestDispatcher("/StatEtud.jsp");
            rd.forward(request, response);
              
           
        }
    
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    String getFormName(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Enumeration<String> parameterNames = request.getParameterNames();
        PrintWriter out = response.getWriter();
        String ch1 = "AjoutForm" ;
        String ch2 = "ModifForm" ;
        String ch3 = "UpdateForm" ;
        String ch4 = "SuppForm" ;
        String ch5 = "DelForm";
        String ch6 = "StatEtudSOAPForm";
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.equals(ch1)){return ch1 ;}
            if (paramName.equals(ch2)){return ch2 ;}
            if (paramName.equals(ch3)){return ch3 ;}
            if (paramName.equals(ch4)){return ch4 ;}
            if (paramName.equals(ch5)){return ch5 ;}
            if (paramName.equals(ch6)){return ch6 ;}
            
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                if (paramValue.equals(ch1)){return ch1 ;}
                if (paramValue.equals(ch2)){return ch2 ;}
                if (paramValue.equals(ch3)){return ch3 ;}
                if (paramValue.equals(ch4)){return ch4 ;}
                if (paramValue.equals(ch5)){return ch5 ;}
                if (paramValue.equals(ch6)){return ch6 ;}
            }
        }
        return " " ;
    }
    
    void verif(String nom , String prenom,Integer age ,String classe , float abs , float  moy,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if (nom.isEmpty() || prenom.isEmpty() || classe.isEmpty() ){
            RequestDispatcher rd = null;
            rd = getServletContext().getRequestDispatcher("/AjoutEtudJSP.jsp");
            String msg="<html><body><div class='alert warning' align='center'><b></b>"+
                    "<span class='closebtn'>&times;</span>"+
                    "<strong>Warning!</strong> Verifier le nom , le prenom ou la classe</div></body></html>";
            request.setAttribute("resultat", msg);
            rd.forward(request, response);
        }
        if (abs<0 || abs >100 ){
            RequestDispatcher rd = null;
            rd = getServletContext().getRequestDispatcher("/AjoutEtudJSP.jsp");
            String msg="<html><body><div class='alert warning' align='center'><b></b>"+
                    "<span class='closebtn'>&times;</span>"+
                    "<strong>Warning!</strong> Verifier le pourcentage de l'absenteisme</div></body></html>";
            request.setAttribute("resultat", msg);
            rd.forward(request, response);
        }
        if (moy<0 || moy >20 ){
            RequestDispatcher rd = null;
            rd = getServletContext().getRequestDispatcher("/AjoutEtudJSP.jsp");
           
            String msg="<html><body><div class='alert warning' align='center'><b></b>"+
                    "<span class='closebtn'>&times;</span>"+
                    "<strong>Warning!</strong> Verifier la moyenne annuelle</div></body></html>";
            request.setAttribute("resultat", msg);
            rd.forward(request, response);
        }
        
        if (age <18 ){
            RequestDispatcher rd = null;
            rd = getServletContext().getRequestDispatcher("/AjoutEtudJSP.jsp");
            String msg="<html><body><b>Verifier l'age du l'etudiant</b></body></html>";
            request.setAttribute("resultat", msg);
            rd.forward(request, response);
        }
    }
    
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
