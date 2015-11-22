package com.ab.jdbcsample.model;

import java.util.Date;

public class Book implements Comparable<Book> {
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
		super();
		this.name = name;
		this.author = author;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", autor=" + author + "]";
	}

	@Override
	public int compareTo(Book o) {
		return this.name.compareTo(o.getName());
	}
}
