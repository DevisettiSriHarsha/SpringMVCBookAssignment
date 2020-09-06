package org.cg.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cg.dao.ImplDao;
import org.cg.model.Book;
import org.cg.model.Library;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class MainController {
	
	ImplDao dao = new ImplDao();
	
	@RequestMapping("/add")
	protected void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String libname = request.getParameter("library");
		Library library = dao.getLibrary(libname);

		Book book = new Book();

		book.setLibrary(library);
		book.setBookId(Integer.parseInt(request.getParameter("bid")));
		book.setBookName(request.getParameter("bname"));
		book.setAuthor(request.getParameter("auth"));
		book.setPublisher(request.getParameter("publ"));

		dao.addBook(book);
		out.println("Book added");
	}
	
	
	
	@RequestMapping("/delete")
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		dao.deleteBook(Integer.parseInt(request.getParameter("delbookid")));
		out.println("Book Deleted");
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	
	
	@RequestMapping("/search")
	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		Book book = dao.searchBook(Integer.parseInt(request.getParameter("getbid")));
		if(book!=null)
		{
			out.println("Book Id : "+book.getBookId());
			out.println("Book Name : "+book.getBookName());
			out.println("Author of Book : "+book.getAuthor());
			out.println("Publisher of Book : "+book.getPublisher());
		}
		else {
			out.println("enter valid book");
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	
	
	@RequestMapping("/update")
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		int bookId = Integer.parseInt(request.getParameter("getbid"));
		Book book=dao.searchBook(bookId);

		String updatedBookName = request.getParameter("updateBName");
		String updatedAuth = request.getParameter("updateAuth");
		String updatedPub = request.getParameter("updatePub");

		out.println("<html>");
		out.println("<body>");

		out.println("Before Update");
		out.print("<br>");
		out.print("<br>");
		out.println("Book Name : " + book.getBookName());
		out.print("<br>");
		out.println("Book Author : " + book.getAuthor());
		out.print("<br>");
		out.println("Book Publisher Name : " + book.getPublisher());
		out.print("<br>");
		Book updateBook = dao.updateBookDetails(bookId, updatedBookName, updatedAuth, updatedPub);
		out.println("Book Name : " + updateBook.getBookName());
		out.print("<br>");
		out.println("Book Author : " + updateBook.getAuthor());
		out.print("<br>");
		out.println("Book Publisher Name : " + updateBook.getPublisher());
		out.print("<br>");

		out.println("</body>");
		out.println("</html>");
	}


}
