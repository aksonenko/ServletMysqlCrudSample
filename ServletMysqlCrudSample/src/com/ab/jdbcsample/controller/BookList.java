package com.ab.jdbcsample.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.ab.jdbcsample.dao.BookDAO;
import com.ab.jdbcsample.model.Book;

public class BookList {
	private static final String SEPARATOR = "\r\n";
	private static final String DEFAULT_FILENAME = "books.txt";
	private List<Book> books;

	public BookList() {
		books = new ArrayList<Book>();
	}

	public void add(Book book) {
		books.add(book);
	}

	public void remove(Book book) {
		books.remove(book);
	}

	public void remove(int index) {
		books.remove(index);
	}

	@Override
	public String toString() {
		return books.toString();
	}

	public void save() {
		save(DEFAULT_FILENAME);
	}

	public void save(String filename) {
		File file = new File(filename);
		FileWriter writer = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			writer = new FileWriter(file);
			writer.write(String.valueOf(books.size()));
			for (Book b : books) {
				writer.write(SEPARATOR);
				writer.write(b.getName());
				writer.write(SEPARATOR);
				writer.write(b.getAuthor());
				writer.write(SEPARATOR);
				writer.write(b.getDate().toString());
			}
			writer.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void read() {
		read(DEFAULT_FILENAME);
	}

	public void read(String filename) {
		File file = new File(filename);
		FileReader fr;
		BufferedReader reader = null;
		try {
			fr = new FileReader(file);
			reader = new BufferedReader(fr);
			int numBooks = Integer.valueOf(reader.readLine());
			for (int i = 0; i < numBooks; i++) {
				String name = reader.readLine();
				String author = reader.readLine();
				String date = reader.readLine();
				Book b = new Book(name, author, new Date());
				books.add(b);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void sortByName() {
		Collections.sort(books);
	}

	public void sortByDate() {
		Collections.sort(books, new Comparator<Book>() {
			@Override
			public int compare(Book o1, Book o2) {
				return o1.getAuthor().compareToIgnoreCase(o2.getAuthor());
			}
		});
	}

	public void saveToDb() {
		BookDAO.getInstance().deleteAll();
		for (Book book : books) {
			BookDAO.getInstance().insert(book);
		}
	}

	public void loadFromDb() {
		books.addAll(BookDAO.getInstance().selectAll());
	}
}
