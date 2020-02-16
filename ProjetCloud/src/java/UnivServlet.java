/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entitiesEtud.Personne;
import entitiesEtud.service.EtudiantFacadeREST;
import entitiesEtud.service.PersonneFacadeREST;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import universitaires_entities.Universitaires;
import universitaires_entities.service.UniversitairesFacadeREST;

/**
 *
 * @author User
 */
public class UnivServlet extends HttpServlet {

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
            out.println("<title>Servlet UnivServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UnivServlet at " + request.getContextPath() + "</h1>");
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
        
        String ch1 = "AjoutFormUniv" ;
        String ch2 = "ModifFormUniv" ;
        String ch3 = "UpdateFormUniv" ;
        String ch4 = "SuppFormUniv" ;
        String ch5 = "DelFormUniv";
        String form_name = getFormName(request,response);
        if (form_name.equals(ch1)){
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            Integer age = Integer.parseInt(request.getParameter("age"));
            String classe = request.getParameter("classe");
            String grade = request.getParameter("grade");
            float abs = Float.parseFloat(request.getParameter("abs"));
            verif(nom,prenom,age,classe,abs,request,response);
            int id = 0 ;
            try {
                id=PersonneFacadeREST.getFinalID(response)+1;
                Universitaires univ = new Universitaires(id, classe, grade , abs) ;
                Personne pers = new Personne(id,  nom,  prenom) ;
                PersonneFacadeREST.inserer(pers,response);
                UniversitairesFacadeREST.inserer(univ,response);
                RequestDispatcher rd = null;
                rd = getServletContext().getRequestDispatcher("/AjouterUniv.jsp");
                String msg="<html><body><div class='alert success' align='center'><b></b>"+
                    "<span class='closebtn'>&times;</span>"+
                    "<strong>Bien!</strong> Universitaire ajouté</div></body></html>";
                request.setAttribute("AjoutProf", msg);
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
                rd = getServletContext().getRequestDispatcher("/ModifierUniv.jsp");
                
                List<String> l = new ArrayList<String>();
                l = UniversitairesFacadeREST.Afficher(response, nom, prenom, classe, tri) ;
                
                request.setAttribute("UnivSearch",l);
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
            String grade = request.getParameter("grade");
            Float abs = Float.parseFloat(request.getParameter("abs"));
           
            RequestDispatcher rd = null;
                  

            try {
                rd = getServletContext().getRequestDispatcher("/ModifierUniv.jsp");
                 PersonneFacadeREST.update(response,id,nom,prenom);
             //    EtudiantFacadeREST.update(response,id,classe,abs,moy);
             UniversitairesFacadeREST.update(response, id, classe, grade, abs);
                String msg="<html><body><div class='alert success' align='center'><b></b>"+
                    "<span class='closebtn'>&times;</span>"+
                    "<strong>Bien!</strong> Universitaire modifié</div></body></html>";
                request.setAttribute("ModifUniv", msg);
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
                rd = getServletContext().getRequestDispatcher("/SuppUniv.jsp");
                
                List<String> l = new ArrayList<String>();
                l = UniversitairesFacadeREST.Afficher(response, nom, prenom, classe, tri) ;
                
                request.setAttribute("UnivSearch",l);
                rd.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
         
         
         if (form_name.equals(ch5)){
            Integer id = Math.round(Float.parseFloat(request.getParameter("IdDelUniv")));
                
            RequestDispatcher rd = null;
            
            try {
                rd = getServletContext().getRequestDispatcher("/SuppUniv.jsp");
                UniversitairesFacadeREST.delete(response, id);
                PersonneFacadeREST.delete(response, id);
                String msg="<html><body><div class='alert success' align='center'><b></b>"+
                    "<span class='closebtn'>&times;</span>"+
                    "<strong>Bien!</strong> Universitaire supprimé</div></body></html>";
                request.setAttribute("UnivSupp",msg);
                rd.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EtudServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
    
        processRequest(request, response);
    }
     
     
     
 String getFormName(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Enumeration<String> parameterNames = request.getParameterNames();
        PrintWriter out = response.getWriter();
        String ch1 = "AjoutFormUniv" ;
        String ch2 = "ModifFormUniv" ;
        String ch3 = "UpdateFormUniv" ;
        String ch4 = "SuppFormUniv" ;
        String ch5 = "DelFormUniv";
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.equals(ch1)){return ch1 ;}
            if (paramName.equals(ch2)){return ch2 ;}
            if (paramName.equals(ch3)){return ch3 ;}
            if (paramName.equals(ch4)){return ch4 ;}
            if (paramName.equals(ch5)){return ch5 ;}
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                if (paramValue.equals(ch1)){return ch1 ;}
                if (paramValue.equals(ch2)){return ch2 ;}
                if (paramValue.equals(ch3)){return ch3 ;}
                if (paramValue.equals(ch4)){return ch4 ;}
                if (paramValue.equals(ch5)){return ch5 ;}
            }
        }
        return " " ;
    }
    void verif(String nom , String prenom,Integer age ,String classe , float abs , HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
       
        
        if (age <18 ){
            RequestDispatcher rd = null;
            rd = getServletContext().getRequestDispatcher("/AjoutEtudJSP.jsp");
            String msg="<html><body><b>Verifier l'age du l'etudiant</b></body></html>";
            request.setAttribute("resultat", msg);
            rd.forward(request, response);
        }
    }
   

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
