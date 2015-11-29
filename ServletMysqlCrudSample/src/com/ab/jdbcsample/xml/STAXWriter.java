package com.ab.jdbcsample.xml;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.ab.jdbcsample.model.Book;
import com.ab.jdbcsample.util.DateTimeHelper;

public class STAXWriter {

	public void saveConfig(List<Book> t, String fileName) {
		try {
			trySaveConfig(t, fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void trySaveConfig(List<Book> t, String fileName)
			throws Exception {
		// Create a XMLOutputFactory
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

		// Create XMLEventWriter
		XMLEventWriter eventWriter = outputFactory
				.createXMLEventWriter(new FileOutputStream(fileName));
		// Create a EventFactory
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		// Create and write Start Tag
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);

		// Create config open tag
		StartElement configStartElement = eventFactory.createStartElement("",
				"", Book.LIST);
		eventWriter.add(configStartElement);
		eventWriter.add(end);
		// Write the different nodes
		if (t.size() > 0) {
			StartElement sElement = eventFactory.createStartElement("", "",
					Book.ITEM);
			EndElement eElement = eventFactory.createEndElement("", "",
					Book.ITEM);
			// eventWriter.add(sElement);
			// eventWriter.add(eventFactory.createDTD("teachers"));
			for (int i = 0; i < t.size(); i++) {
				eventWriter.add(sElement);
				eventWriter.add(end);
				createNode(eventWriter, Book.FIELD_ID,
						String.valueOf(t.get(i).getId()));
				createNode(eventWriter, Book.FIELD_NAME, t.get(i).getName());
				createNode(eventWriter, Book.FIELD_AUTHOR, t.get(i).getAuthor());
				createNode(eventWriter, Book.FIELD_DATE,
						DateTimeHelper.getSimpleDateTime(t.get(i).getDate()));
				eventWriter.add(eElement);
				eventWriter.add(end);
			}
			// eventWriter.add(eventFactory.createDTD("teachers"));
		}

		eventWriter.add(eventFactory.createEndElement("", "", Book.LIST));
		eventWriter.add(end);
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
	}

	private void createNode(XMLEventWriter eventWriter, String name,
			String value) throws XMLStreamException {

		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		// Create Start node
		StartElement sElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(tab);
		eventWriter.add(sElement);
		// Create Content
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		// Create End node
		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
		eventWriter.add(end);
	}

	public static void main(String[] args) {
		STAXReader read = new STAXReader();
		List<Book> readConfig = read.readConfig("books.xml");

		STAXWriter configFile = new STAXWriter();
		configFile.saveConfig(readConfig, "t.xml");
	}
}
