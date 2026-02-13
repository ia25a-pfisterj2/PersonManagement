package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Person;
import service.PersonService;

@WebServlet("/persons")
public class Controller extends HttpServlet{
	
	@Override
	public void init() throws ServletException{
		PersonService.insert(new Person("Test1", "Test2", LocalDate.of(2000, 01, 03)));
		PersonService.insert(new Person("Jason", "Pfister", LocalDate.of(2001, 05, 02)));
		PersonService.insert(new Person("Max", "Mustermann", LocalDate.of(2025, 11, 20)));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Person> personen = PersonService.getPersonen();
		
		request.setAttribute("personen", personen);
		request.getRequestDispatcher("listPersons.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		String delete = request.getParameter("delete");
		
		if (delete.equals("delete")) {
			doDelete(request, response);
			return;
		}
		*/
		String delete = request.getParameter("delete");
		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		String geburtsdatum = request.getParameter("geburtsdatum");
		
		LocalDate date = LocalDate.parse(geburtsdatum);
		
		if (delete.equals("transfer")) {
			String id = request.getParameter("id");
			
			request.setAttribute("id", id);
			request.setAttribute("vorname", vorname);
			request.setAttribute("nachname", nachname);
			request.setAttribute("geburtsdatum", date);
			request.getRequestDispatcher("updatePerson.jsp").forward(request, response);
			return;
		}
		
		if (delete.equals("update")) {
			doPut(request, response);
			return;
		}

		Person person = new Person(vorname, nachname, date);
		
		
		PersonService.insert(person);
        
		response.sendRedirect("index.jsp");
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("index");
		
		PersonService.delete(id);
		
		//response.sendRedirect("persons");
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("index");
		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		String geburtsdatum = request.getParameter("geburtsdatum");
		
		LocalDate date = LocalDate.parse(geburtsdatum);
				
		Person person = new Person(vorname, nachname, date);
		
		PersonService.update(id, person);
		
		response.sendRedirect("persons");
		// response.setStatus(HttpServletResponse.SC_OK);
	}
}
