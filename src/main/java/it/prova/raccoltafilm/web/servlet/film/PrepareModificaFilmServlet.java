package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.service.MyServiceFactory;

@WebServlet("/PrepareModificaFilmServlet")
public class PrepareModificaFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareModificaFilmServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long idFilmInput = Long.parseLong(request.getParameter("idFilm"));

		try {
			// metto un bean 'vuoto' in request perché per la pagina risulta necessario
			request.setAttribute("modifica_film_attr",
					MyServiceFactory.getFilmServiceInstance().caricaSingoloElemento(idFilmInput));
			// questo mi serve per la select di registi in pagina
			request.setAttribute("registi_list_attribute",
					MyServiceFactory.getRegistaServiceInstance().listAllElements());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/film/modifica.jsp").forward(request, response);
	}

}
