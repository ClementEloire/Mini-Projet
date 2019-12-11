/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.DAO;
import Modele.DataSourceFactory;
import Modele.Produit;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Clément Eloire
 */
@WebServlet(name = "ServletModifAdmin", urlPatterns = {"/ServletModifAdmin"})
public class ServletModifAdmin extends HttpServlet {

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
            throws ServletException, IOException, SQLException{
        
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        String cat =  request.getParameter("Categorie");
        if(cat == null)
        {
            cat= String.valueOf(dao.listeDeCategorie().get(0).getCode());
        } 
        List<Produit> listeProduit =dao.listeDeProduit(Integer.valueOf(cat)) ;
        request.setAttribute("Categories", dao.listeDeCategorie());
        request.setAttribute("Produits" , listeProduit);
        request.getRequestDispatcher("modifAdmin.jsp").forward(request, response);
        
        String idRef = request.getParameter("idProduit");
        String action = request.getParameter("action");
        if(null != action) {
            switch(action) {
                case "X":
                    dao.deleteProduit(Integer.valueOf(idRef));
                    request.getRequestDispatcher("modifAdmin.jsp").forward(request, response);
                    break;
                case "Modifier":
                    break;
                case "Ajouter":
                    
                    break;
                default:
                    
            }
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
        try {
            processRequest(request, response);
        }catch (SQLException ex) {
            Logger.getLogger(ServletVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        }catch (SQLException ex) {
            Logger.getLogger(ServletVisiteur.class.getName()).log(Level.SEVERE, null, ex);
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
