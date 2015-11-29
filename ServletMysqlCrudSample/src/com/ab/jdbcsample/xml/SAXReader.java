package com.ab.jdbcsample.xml;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ab.jdbcsample.model.Book;
import com.ab.jdbcsample.util.DateTimeHelper;

public class SAXReader extends DefaultHandler {

	private boolean isId;
	private boolean isName;
	private boolean isAuthor;
	private boolean isDate;
	private ArrayList<Book> list;
	private Book item;

	public SAXReader() {
		isId = false;
		isName = false;
		isAuthor = false;
		isDate = false;
		list = new ArrayList<Book>();
		item = new Book();
	}

	public ArrayList<Book> getList() {
		return list;
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase(Book.FIELD_ID)) {
			isId = true;
		}
		if (qName.equalsIgnoreCase(Book.FIELD_NAME)) {
			isName = true;
		}
		if (qName.equalsIgnoreCase(Book.FIELD_AUTHOR)) {
			isAuthor = true;
		}
		if (qName.equalsIgnoreCase(Book.FIELD_DATE)) {
			isDate = true;
		}
	}

	public void endElement(String uri, String localName, String qName) {
	}

	public void characters(char ch[], int start, int length) {
		Long id = 0l;
		String name = "";
		String author = "";
		String date = "";

		if (isId) {
			id = Long.valueOf(new String(ch, start, length));
			item.setId(id);
			isId = false;
		}
		if (isName) {
			name = new String(ch, start, length);
			item.setName(name);
			isName = false;
		}
		if (isAuthor) {
			author = new String(ch, start, length);
			item.setAuthor(author);
			isAuthor = false;
		}
		if (isDate) {
			date = new String(ch, start, length);
			item.setDate(DateTimeHelper.getSimpleDate(date));

			list.add(item);
			isDate = false;
			item = new Book();
		}
	}

	public static void main(String[] args) throws Exception {
		SAXReader p = new SAXReader();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		saxParser.parse("books.xml", p);

		ArrayList<Book> t = p.getList();
		for (int i = 0; i < t.size(); i++) {
			System.out.println(t.get(i));
		}
	}
}
