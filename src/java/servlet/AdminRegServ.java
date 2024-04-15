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

/**
 *
 * @author Tisha Verma
 */
public class AdminRegServ extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminRegServ</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminRegServ at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            String redirectURL = "admin.html"; // Replace with the actual HTML page URL
        response.sendRedirect(redirectURL);
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Connection con = null;
        Statement stmt = null;
        String aun="", aem="", apas="", acpas="";
        aun=request.getParameter("adregisterusername");
        aem=request.getParameter("aemail");
        apas = request.getParameter("adregisterpassword");
        acpas = request.getParameter("adconfirmpassword");
        String redirectURL = "admin.html"; 
            response.sendRedirect(redirectURL);
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/mastanproject?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
            stmt = con.createStatement();
            
            stmt.executeUpdate("insert into adminregister values('"+aun+"', '"+aem+"', '"+apas+"','"+acpas+"')");
            out.print("<h1> Your account is created successfully!!</h>");
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
