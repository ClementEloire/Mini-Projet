/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modele.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexis Jalabert
 */
@WebServlet(name = "ServletPanier", urlPatterns = {"/ServletPanier"})
public class ServletPanier extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        PanierClient panier = (PanierClient)request.getSession().getAttribute("Panier");
        List<ProduitPanier> prodListe = panier.getProduitPanier();
        request.setAttribute("Produit", prodListe);
        String action = request.getParameter("action");
        switch(action) {
            case "Valider":
                dao.creationCom(panier);
                Client client = (Client)request.getSession().getAttribute("Client");
                request.getSession().setAttribute("Panier", new PanierClient(client));
                request.getRequestDispatcher("ServletClient").forward(request, response);
                break;
            
            case "Supprimer":
                String ref = request.getParameter("Ref");
                int index = 0;
                for(int i = 0 ; i < prodListe.size() ; i++) {
                    if(Integer.parseInt(ref) == prodListe.get(i).getRef()) {
                         index = i;
                    }
                }
                panier.deleteProduitPanier(index);
                request.getSession().setAttribute("Panier", panier);
                request.getRequestDispatcher("protect/panierClient.jsp").forward(request, response);
                break;
                
            case "Modifier":
                break;
                
            default:
                request.getRequestDispatcher("protect/panierClient.jsp").forward(request, response);
                break;
            
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
        } catch (Exception ex) {
            Logger.getLogger(ServletPanier.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(ServletPanier.class.getName()).log(Level.SEVERE, null, ex);
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
