import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/empleados")



	public class EmployeesServlet extends HttpServlet{
	
	private DataSource pool;
	
	@Override
	public void init() throws ServletException {
		try {
			InitialContext context = new InitialContext();
			pool = (DataSource) context.lookup("java:comp/env/jdbc/employees");

		} catch (NamingException e) {
			throw new ServletException(e);
		}
	}
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Connection conn = null;
			ResultSet result = null;
			resp.setContentType("text/html");
			PrintWriter writer;
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				conn = pool.getConnection();
				Statement sql = conn.createStatement();
				result = sql.executeQuery("select * from employees");
				writer = resp.getWriter();
				writer.println("<html>");
				writer.println(
						  "<head>"
						+ "<title>Prueba Servet</title>"
						+ "<meta charset=\"UTF-8\">"
						+ "</head>"
						+ "<body>"
						+ "<h1>Hola mundo!</h1>"
						+ "<table>");

						while(result.next()) {
							writer.println(""
									+ "<tr>"
									+ "<td>"+result.getInt("emp_no")+"</td>"
									+ "<td>"+result.getDate("birth_date")+"</td>"
									+ "<td>"+result.getString("first_name")+" "+result.getString("second_name")+"</td>"
									+ "<td>"+result.getString("gender")+"</td>"
									+ "<td>"+result.getDate("hire_date")+"</td>"
									+ "</tr>");
						}
				writer.println("</tr>"
						+ "</table>"
						+ "</body>"
						+ "</html>"	
				);
			} catch (ClassNotFoundException | SQLException e) {
				ServletException se = new ServletException(e);
				throw se;
			} finally {
				try {
					if(result != null) {
						result.close();
					}
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					ServletException se = new ServletException(e);
				}
			}
		}  
	}

