package com.ab.jdbcsample.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ab.jdbcsample.dao.BookDAO;
import com.ab.jdbcsample.model.Book;

public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_EDIT = "/EditBook.jsp";
	private static final String PAGE_LIST = "/ListBook.jsp";
	private static final String ACTION_VALUE = "action";
	private static final String ACTION_DELETE = "delete";
	private static final String ACTION_EDIT = "edit";
	private static final String ACTION_LIST = "list";
	private static final String ATTRIBUTE_ITEM = "item";
	private static final String ATTRIBUTE_LIST = "list";
	private static final String PARAMETER_ENTITY_ID = "id";

	private BookDAO dao;

	public BookController() {
		super();
		dao = BookDAO.getInstance();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter(ACTION_VALUE);

		switch (action) {
		case ACTION_DELETE:
			int itemId = Integer.parseInt(request
					.getParameter(PARAMETER_ENTITY_ID));
			dao.delete(itemId);
			forward = PAGE_LIST;
			request.setAttribute(ATTRIBUTE_LIST, dao.selectAll());
			break;

		case ACTION_EDIT:
			forward = PAGE_EDIT;
			int userId = Integer.parseInt(request
					.getParameter(PARAMETER_ENTITY_ID));
			Book item = dao.select(userId);
			request.setAttribute(ATTRIBUTE_ITEM, item);
			break;

		case ACTION_LIST:
			forward = PAGE_LIST;
			request.setAttribute(ATTRIBUTE_LIST, dao.selectAll());
			break;

		default:
			forward = PAGE_EDIT;
			break;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Book item = new Book();
		item.setName(request.getParameter("name"));
		item.setAuthor(request.getParameter("author"));
		try {
			Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(request
					.getParameter("date"));
			item.setDate(dob);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String userid = request.getParameter(PARAMETER_ENTITY_ID);
		if (userid == null || userid.isEmpty()) {
			dao.insert(item);
		} else {
			item.setId(Long.parseLong(userid));
			dao.update(item);
		}
		RequestDispatcher view = request.getRequestDispatcher(PAGE_LIST);
		request.setAttribute(ATTRIBUTE_LIST, dao.selectAll());
		view.forward(request, response);
	}
}