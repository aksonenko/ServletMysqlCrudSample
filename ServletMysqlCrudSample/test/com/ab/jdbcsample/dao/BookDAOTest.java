package com.ab.jdbcsample.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.ab.jdbcsample.model.Book;

public class BookDAOTest {

	@Test
	public void testDao() {
		BookDAO.getInstance().deleteAll();
		assertEquals(0, BookDAO.getInstance().selectAll().size());

		Book b = new Book("abc", "bcd", new Date());
		BookDAO.getInstance().insert(b);

		List<Book> books = BookDAO.getInstance().selectAll();
		assertEquals(1, books.size());
		assertEquals(b.getAuthor(), books.get(0).getAuthor());
		assertEquals(b.getName(), books.get(0).getName());
		assertEquals(b.getDate(), books.get(0).getDate());
		BookDAO.getInstance().delete(books.get(0));
		assertEquals(0, BookDAO.getInstance().selectAll().size());

		BookDAO.getInstance().insert(b);
		BookDAO.getInstance().deleteAll();
		assertEquals(0, BookDAO.getInstance().selectAll().size());
	}
}
