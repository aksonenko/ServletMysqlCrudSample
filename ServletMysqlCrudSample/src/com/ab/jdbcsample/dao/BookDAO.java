package com.ab.jdbcsample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ab.jdbcsample.model.Book;

public class BookDAO {
	private static String SQL_INSERT = "INSERT INTO books (name, author, date) VALUE (?, ?, ?); ";
	private static String SQL_SELECT = "SELECT * FROM books;";
	private static String SQL_DELETE = "delete from books;";
	private static String SQL_SELECT_BY_ID = "SELECT * FROM books where id = ?;";
	private static String SQL_DELETE_BY_ID = "delete from books where id = ?;";
	private static final String FIELD_ID = "id";
	private static final String FIELD_NAME = "name";
	private static final String FIELD_AUTHOR = "author";
	private static final String FIELD_DATE = "date";
	private static BookDAO BOOK_DAO = null;

	private BookDAO() {

	}

	public static BookDAO getInstance() {
		if (BOOK_DAO == null) {
			BOOK_DAO = new BookDAO();
		}
		return BOOK_DAO;
	}

	public boolean delete(Book item) {
		return delete(item.getId());
	}

	public boolean delete(long id) {
		Connection con = null;
		try {
			con = MySqlConnection.getConnection();
			PreparedStatement st = con.prepareStatement(SQL_DELETE_BY_ID);
			st.setLong(1, id);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			close(con);
		}
	}

	public boolean deleteAll() {
		Connection con = null;
		try {
			con = MySqlConnection.getConnection();
			PreparedStatement st = con.prepareStatement(SQL_DELETE);
			int n = st.executeUpdate();
			if (n == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			close(con);
		}
	}

	public List<Book> selectAll() {
		Connection con = null;
		List<Book> list = new ArrayList<Book>();
		try {
			con = MySqlConnection.getConnection();
			Statement st = con.createStatement();
			ResultSet result = st.executeQuery(SQL_SELECT);
			while (result.next()) {
				list.add(extractBook(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		} finally {
			close(con);
		}
		return list;
	}

	public Book select(int id) {
		Connection con = null;
		Book item = null;
		try {
			con = MySqlConnection.getConnection();
			PreparedStatement st = con.prepareStatement(SQL_SELECT_BY_ID);
			st.setInt(1, id);
			ResultSet result = st.executeQuery();
			if (result.next()) {
				item = extractBook(result);
			}
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return item;
		} finally {
			close(con);
		}
	}

	/**
	 * Method insert onject into database
	 * 
	 * @param item
	 *            - the book will be inserted
	 * @return true if successfully inserted
	 */
	public boolean insert(Book item) {
		return insert(item.getName(), item.getAuthor(), item.getDate());
	}

	public boolean insert(String name, String author, Date date) {
		Connection con = null;
		try {
			con = MySqlConnection.getConnection();
			PreparedStatement st = con.prepareStatement(SQL_INSERT);
			st.setString(1, name);
			st.setString(2, author);
			st.setLong(3, date.getTime());
			int n = st.executeUpdate();
			if (n == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			close(con);
		}
	}

	private void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private Book extractBook(ResultSet rs) throws SQLException {
		Book item = new Book();
		item.setId(rs.getLong(FIELD_ID));
		item.setName(rs.getString(FIELD_NAME));
		item.setAuthor(rs.getString(FIELD_AUTHOR));
		item.setDate(new Date(rs.getLong(FIELD_DATE)));
		return item;
	}
}
