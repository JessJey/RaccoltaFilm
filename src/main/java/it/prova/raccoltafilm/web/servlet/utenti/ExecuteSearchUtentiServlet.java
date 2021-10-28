package it.prova.raccoltafilm.web.servlet.utenti;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Utente;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/admin/ExecuteSearchUtentiServlet")
public class ExecuteSearchUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteSearchUtentiServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String usernameParam = request.getParameter("username");
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String dataDiCreazioneParam = request.getParameter("dateCreated");

		Utente example = new Utente(usernameParam, nomeParam, cognomeParam,
				UtilityForm.parseDateArrivoFromString(dataDiCreazioneParam));
		
		try {
			request.setAttribute("utenti_list_attribute",
					MyServiceFactory.getUtenteServiceInstance().findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/utenti/search.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/utenti/list.jsp").forward(request, response);
	}

	}

