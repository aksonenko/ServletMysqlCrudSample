package com.ab.jdbcsample.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.ab.jdbcsample.model.Book;
import com.ab.jdbcsample.util.DateTimeHelper;

public class STAXReader {

	public List<Book> readConfig(String fileName) {
		try {
			return tryReadConfig(fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Book> tryReadConfig(String fileName)
			throws FileNotFoundException, XMLStreamException {
		List<Book> items = new ArrayList<Book>();

		// First create a new XMLInputFactory
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();

		// Setup a new eventReader
		InputStream in = new FileInputStream(fileName);
		XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

		// Read the XML document
		Book item = null;
		while (eventReader.hasNext()) {
			XMLEvent event = eventReader.nextEvent();

			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				// If we have a item element we create a new item
				if (startElement.getName().getLocalPart() == (Book.ITEM)) {
					item = new Book();
				}
				if (event.isStartElement()) {
					if (event.asStartElement().getName().getLocalPart()
							.equals(Book.FIELD_ID)) {
						event = eventReader.nextEvent();
						item.setId(new Long(event.asCharacters().getData()
								.toString()));
						continue;
					}
				}
				if (event.asStartElement().getName().getLocalPart()
						.equals(Book.FIELD_NAME)) {
					event = eventReader.nextEvent();
					item.setName(event.asCharacters().getData());
					continue;
				}
				if (event.asStartElement().getName().getLocalPart()
						.equals(Book.FIELD_AUTHOR)) {
					event = eventReader.nextEvent();
					item.setAuthor(event.asCharacters().getData());
					continue;
				}
				if (event.asStartElement().getName().getLocalPart()
						.equals(Book.FIELD_DATE)) {
					event = eventReader.nextEvent();
					item.setDate(DateTimeHelper.getSimpleDate(event
							.asCharacters().getData()));
					continue;
				}
			}
			if (event.isEndElement()) {
				EndElement endElement = event.asEndElement();
				if (endElement.getName().getLocalPart() == (Book.ITEM)) {
					items.add(item);
				}
			}
		}
		return items;
	}

	public static void main(String[] args) throws FileNotFoundException,
			XMLStreamException {
		STAXReader read = new STAXReader();
		List<Book> readConfig = read.readConfig("books.xml");
		for (Book item : readConfig) {
			System.out.println(item);
		}
	}
}
