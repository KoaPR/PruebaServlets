import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/holamundo")
public class HolaMundoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter writer = null;
		
		try{
			writer = resp.getWriter();
			writer.println("<html>");
			writer.println(
					  "<head>"
					+ "<title>Prueba Servet</title>"
					+ "<meta charset=\"UTF-8\">"
					+ "</head>"
					+ "<body>"
					+ "<h1>Hola mundo!</h1>"
					+ "</body>"
					+ "</html>"
					
			);
		}finally {
			if(writer != null) {
				writer.close();
			}
		}
	}
}
