package com.ab.jdbcsample.xml;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.ab.jdbcsample.model.Book;
import com.ab.jdbcsample.util.DateTimeHelper;

public class DOMReader {

	public static Element parseXML(String f) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(f);
		Element root = document.getDocumentElement();
		return root;
	}

	public static ArrayList<Book> getTeachers(String s) throws Exception {
		ArrayList<Book> list = new ArrayList<Book>();
		NodeList nodeList = parseXML(s).getElementsByTagName(Book.ITEM);
		if (nodeList.getLength() == 0) {
			return null;
		}
		Long id;
		String name;
		String author;
		String date;
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element teacher = (Element) nodeList.item(i);
			id = Long.parseLong(teacher.getElementsByTagName(Book.FIELD_ID)
					.item(0).getFirstChild().getNodeValue().toString());
			name = teacher.getElementsByTagName(Book.FIELD_NAME).item(0)
					.getFirstChild().getNodeValue();
			author = teacher.getElementsByTagName(Book.FIELD_AUTHOR).item(0)
					.getFirstChild().getNodeValue();
			date = teacher.getElementsByTagName(Book.FIELD_DATE).item(0)
					.getFirstChild().getNodeValue();
			list.add(new Book(id, name, author, DateTimeHelper
					.getSimpleDate(date)));
		}
		return list;
	}

	public static void main(String[] args) throws Exception {
		ArrayList<Book> t = getTeachers("books.xml");
		for (int i = 0; i < t.size(); i++) {
			System.out.println(t.get(i));
		}
	}
}
