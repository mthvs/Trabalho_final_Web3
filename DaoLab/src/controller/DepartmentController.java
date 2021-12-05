package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.DepartmentDao;
import models.Department;


@WebServlet(urlPatterns = {"/DepartmentController" , "/main" , "/insert" , "/listar" , "/select" , "/update" , "/delete", "/procura" })
public class DepartmentController extends HttpServlet {
	Department d = new Department();
	DepartmentDao dao = DaoFactory.creatDepartmentDao();
	
	private static final long serialVersionUID = 1L;
	
	public DepartmentController() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action =  req.getServletPath();
		System.out.println(action);
		if(action.equals("/main")) {
			departamentos(req,resp);
		}else if(action.equals("/insert")) {
			novoDerpartamento(req,resp);
		}else if(action.equals("/listar")) {
			listDerpartamento(req,resp);
		}else if(action.equals("/select")) {
			selecinarDerpartamento(req,resp);
		}else if(action.equals("/update")) {
			editarDerpartamento(req,resp);
		}else if(action.equals("/delete")) {
			deletarDerpartamento(req,resp);
		}else if(action.equals("/procura")) {
			procuraDerpartamento(req,resp);
		}else {
			resp.sendRedirect("index.html");
		}
	}
	
	protected void departamentos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("departamento.jsp");	
		
	}
	
	protected void novoDerpartamento(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		d.setName(req.getParameter("nome"));
		dao.insert(d);
		resp.sendRedirect("main");
	}
	
	protected void listDerpartamento(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("departamentos", dao.findAll());
		RequestDispatcher rd = req.getRequestDispatcher("Lista.jsp");
		rd.forward(req,resp);
		resp.sendRedirect("main");
	}
	
	protected void selecinarDerpartamento(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		d.setId(Integer.parseInt(id));
		req.setAttribute("id", dao.findById(d.getId()).getId());
		req.setAttribute("nome", dao.findById(d.getId()).getName());
		RequestDispatcher rd = req.getRequestDispatcher("editar.jsp");
		rd.forward(req,resp);
				
	}
	
	protected void editarDerpartamento(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		d.setId(Integer.parseInt(req.getParameter("id")));
		d.setName(req.getParameter("nome"));
		dao.update(d);
		resp.sendRedirect("listar");
	}
	
	protected void deletarDerpartamento(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		d.setId(Integer.parseInt(id));
		dao.deleteById(d.getId());
		resp.sendRedirect("listar");
	}
	
	protected void procuraDerpartamento(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int x=0;
		d.setId(Integer.parseInt(req.getParameter("id")));
		ArrayList<Department> list = dao.findAll();
		for(Department d1: list) {
			if(d.getId() == d1.getId()) {
				x++;
			}
		}
		if(x!=0) {
			req.setAttribute("id", dao.findById(d.getId()).getId());
			req.setAttribute("nome", dao.findById(d.getId()).getName());
			RequestDispatcher rd = req.getRequestDispatcher("resultado.jsp");
			rd.forward(req,resp);
		}else {
			resp.sendRedirect("procura.jsp");
				
		}

	}
	
}
