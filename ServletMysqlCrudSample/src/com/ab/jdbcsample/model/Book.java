package com.ab.jdbcsample.model;

import java.util.Date;

import com.ab.jdbcsample.util.DateTimeHelper;

public class Book implements Comparable<Book> {
	public static final String LIST = "books";
	public static final String ITEM = "book";
	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_AUTHOR = "author";
	public static final String FIELD_DATE = "date";

	private Long id;
	private String name;
	private String author;
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String autor) {
		this.author = autor;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Book() {
		super();
	}

	public Book(String name, String author, Date date) {
		this(null, name, author, date);
	}

	public Book(Long id, String name, String author, Date date) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", autor=" + author
				+ ", date=" + DateTimeHelper.getSimpleDateTime(date) + "]";
	}

	@Override
	public int compareTo(Book o) {
		return this.name.compareTo(o.getName());
	}

	public boolean isNew() {
		return id == null;
	}
}
