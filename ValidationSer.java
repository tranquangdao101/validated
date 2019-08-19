package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class ValidationSer
 */
@WebServlet("/validation")
public class ValidationSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ValidationSer() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ArrayList al = new ArrayList();
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String sno1 = req.getParameter("validpro_sno");
        String sname = req.getParameter("validpro_sname");
        String smarks1 = req.getParameter("validpro_smarks");
        
        int sno = 0;
        float smarks = 0;
        if ((sno1 == null) || (sno1.equals(""))) {
            al.add("PROVIDE STUDENT NUMBER...");
        } else {
            try {
                sno = Integer.parseInt("sno1");
            } catch (NumberFormatException nfe) {
                al.add("PROVIDE int DATA IN STUDENT NUMBER...");
            }
        }
        if ((sname == null) || (sname.equals(""))) {
            al.add("PROVIDE STUDENT NAME...");
        }
        if ((smarks1 == null) || (smarks1.equals(""))) {
            al.add("PROVIDE STUDENT MARKS...");
        } else {
            try {
                smarks = Float.parseFloat("smarks1");
            } catch (NumberFormatException nfe) {
                al.add("PROVIDE float DATA IN STUDENT MARKS...");
            }
        }
        if (al.size() != 0) {
            pw.println(al);
        } else {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:Hanuman", "scott", "tiger");
                PreparedStatement ps = con.prepareStatement("insert into Student values (?,?,?)");
                ps.setInt(1, sno);
                ps.setString(2, sname);
                ps.setFloat(3, smarks);
                int i = ps.executeUpdate();
                if (i > 0) {
                    pw.println("RECORD INSERTED...");
                } else {
                    pw.println("RECORD NOT INSERTED...");
                }
                con.close();
            } catch (Exception e) {
                res.sendError(503, "PROBLEM IN DATABASE...");
            }
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);

        String foo = request.getParameter("foo");
        String bar = request.getParameter("bar");

        if (foo == null || foo.trim().isEmpty()) {
            messages.put("foo", "Please enter this field");
        }

        if (bar == null || bar.trim().isEmpty()) {
            messages.put("bar", "Please enter this field");
        }

        // At end, forward request to JSP page for display:
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
