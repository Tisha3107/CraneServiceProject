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
import java.sql.*;

public class InsertDataServ extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertDataServ</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertDataServ at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
             String redirectURL = "BookingForm.html"; // Replace with the actual HTML page URL
        response.sendRedirect(redirectURL);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        PrintWriter out = response.getWriter();
        Connection con = null;
        Statement stmt = null;
        String n="", p="";
        n=request.getParameter("username");
        p=request.getParameter("password");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/mastanproject?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
            stmt = con.createStatement();
            
            stmt.executeUpdate("insert into login values('"+n+"', '"+p+"')");
            String redirectURL = "BookingForm.html"; 
            response.sendRedirect(redirectURL);
        }
        catch (Exception e) {
        e.printStackTrace();
        
    } finally {
        // Close the database connection and statement in a finally block
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
