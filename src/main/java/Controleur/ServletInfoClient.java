/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedago
 */
@WebServlet(name = "ServletInfoClient", urlPatterns = {"/ServletInfoClient"})
public class ServletInfoClient extends HttpServlet {

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
        
        String action = request.getParameter("action");
        
        switch(action) {
            case "update":
                DAO dao = new DAO(DataSourceFactory.getDataSource());
                String code = request.getParameter("CodeClient");
                String contact = request.getParameter("Contact");
                String societe = request.getParameter("Societe");
                String fonction = request.getParameter("Fonction");
                String adresse = request.getParameter("Adresse");
                String ville = request.getParameter("Ville");
                String region = request.getParameter("Region");
                String codepostal = request.getParameter("CodePostal");
                String pays = request.getParameter("Pays");
                String tel = request.getParameter("Tel");
                String fax = request.getParameter("Fax");
                Client client = new Client(code, societe, contact, fonction, adresse, ville, region, codepostal, pays, tel, fax);
                int rs = dao.updateClient(client); 
                client = dao.infoClient(code);
                request.getSession().setAttribute("Client", client);
                
                break;
        }
        
        request.getRequestDispatcher("protect/infoClient.jsp").forward(request, response);
        
        
        
        
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
        request.getRequestDispatcher("protect/infoClient.jsp").forward(request, response);
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
            Logger.getLogger(ServletInfoClient.class.getName()).log(Level.SEVERE, null, ex);
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
