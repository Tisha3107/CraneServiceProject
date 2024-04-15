/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class UserRegServ extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserRegServ</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserRegServ at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            String redirectURL = "BookingForm.html"; // Replace with the actual HTML page URL
            response.sendRedirect(redirectURL);
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Connection con = null;
        Statement stmt = null;
        String un="", em="", pas="", cpas="";
        un=request.getParameter("registerusername");
        em=request.getParameter("uemail");
        pas = request.getParameter("registerpassword");
        cpas = request.getParameter("confirmpassword");
        
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/mastanproject?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
            stmt = con.createStatement();
            
            stmt.executeUpdate("insert into userregister values('"+un+"', '"+em+"', '"+pas+"','"+cpas+"')");
            out.print("<h1> Your account is created successfully!!</h>");
            String redirectURL = "BookingForm.html"; 
            response.sendRedirect(redirectURL);
        }
        catch(Exception e){
            out.println("Sorry!! Try again Later." + e);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
