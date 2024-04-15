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

public class Contact extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Contact</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Contact at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            String redirectURL = "contact.html"; // Replace with the actual HTML page URL
            response.sendRedirect(redirectURL);
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Connection con = null;
        Statement stmt = null;
        String n1="", sub1="", em1="", pn1="", q1="";
        n1=request.getParameter("w3lName");
        sub1=request.getParameter("w3lSubject");
        em1 = request.getParameter("w3lSender");
        pn1 = request.getParameter("w3lPhone");
        q1 = request.getParameter("w3lMessage");
        
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/mastanproject?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
            stmt = con.createStatement();
            
            stmt.executeUpdate("insert into contactuser values('"+n1+"', '"+sub1+"', '"+em1+"','"+pn1+"', '"+q1+"')");
            out.print("<h1> Your details is recorded successfully!!</h>");
            String redirectURL = "contact.html"; 
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
