import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/comprobar-fecha")

public class EmpleadosFecha extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fecha = req.getParameter("fecha");
		PrintWriter writer = resp.getWriter();
		
		Connection conn;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/employees","root","practicas");
			Statement sql = conn.createStatement();
			ResultSet result = sql.executeQuery("select * from employees");
			writer.println(
					"<h1>"+fecha+"<h1>"
			);
			writer.print("<table>");
			while(result.next()) {
				writer.print(result.getInt("emp_no"));
				writer.println("<tr><td>"+result.getInt("emp_no")+"</td>");
				writer.println("<td>"+result.getDate("birth_date")+"</td>");
				writer.println("<td>"+result.getString("first_name")+"</td>");
				writer.println("<td>"+result.getString("gender")+"</tr>");
				writer.println("<td>"+result.getDate("hire_date")+"</td></tr>");
				
			}
			writer.println("</tr>"
					+ "</table>"
					+ "</body>"
					+ "</html>"	
			);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}
}
