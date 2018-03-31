package deb.phd.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;

public class Parser {

	public static void main(String[] args) {
		PostsReader reader = new PostsReader("Files/Posts.xml");
		try {
			reader.readFromXML();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
