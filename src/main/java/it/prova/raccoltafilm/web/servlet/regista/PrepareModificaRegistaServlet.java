package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.service.MyServiceFactory;


@WebServlet("/PrepareModificaRegistaServlet")
public class PrepareModificaRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareModificaRegistaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long idRegistaInput = Long.parseLong(request.getParameter("idRegista"));

		try {
			// metto un bean 'vuoto' in request perché per la pagina risulta necessario
			request.setAttribute("update_regista_attr",
					MyServiceFactory.getRegistaServiceInstance().caricaSingoloElemento(idRegistaInput));
			// questo mi serve per la select di registi in pagina
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/regista/update.jsp").forward(request, response);
	}

}