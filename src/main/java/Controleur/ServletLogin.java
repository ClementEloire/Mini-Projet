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
import Modele.Client;
import Modele.DAO;
import Modele.DataSourceFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;


/**
 *
 * @author pedago
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {

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
        if(null != action) {
            switch(action) {
                case "login":
                    checkLogin(request, request.getParameter("loginParam"), request.getParameter("passwordParam"));
                    break;
                case "logout":
                    doLogout(request);
                    break;
            }
        }
        
        String userName = findUserInSession(request);
        String jspView;
        if(userName == null) {
            jspView = "login.jsp";
        } else {
            jspView = "ServletClient";
        }
        request.getRequestDispatcher(jspView).forward(request, response);
        
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
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

    private String findUserInSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session == null) ? null : (String) session.getAttribute("userName");
    }
    
    private Client checkLogin(HttpServletRequest request, String login, String password) throws Exception {
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        Client client = dao.loginClient(login, password);
        if(client != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userName", client.getCode());
            request.setAttribute("errorMessage","Trouvé !");
        } else {
            request.setAttribute("errorMessage", "Login/Password incorrect");
        }
        
        return client;
    }
    
    private void doLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
    }

}
