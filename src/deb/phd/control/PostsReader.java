package deb.phd.control;

import deb.phd.model.Post;
import deb.phd.model.Proof;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostsReader {

	public String file;
	FileInputStream fileInputStream;
	Map<Integer, Post> posts = new HashMap<Integer, Post>();
	Map<Integer, Proof> proofs = new HashMap<Integer, Proof>();

	public static final int LIMITPOSTS = 100000;

	public PostsReader(String file) {
		try {
			this.fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readFromXML() throws XMLStreamException {
		InputStream is = this.fileInputStream;
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLStreamReader reader = null;
		try {
			reader = inputFactory.createXMLStreamReader(is);
			readDocument(reader);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	private void readDocument(XMLStreamReader reader) {
		int i = 0;
		try {
			while (reader.hasNext() && i < LIMITPOSTS) {
				int eventType = reader.next();
				switch (eventType) {
				case XMLStreamReader.START_ELEMENT:
					String elementName = reader.getLocalName();
					if (elementName.equals("row")) {
						readPost(reader);
						i++;
					}
				case XMLStreamReader.END_ELEMENT:
					break;
				}

			}
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		filterProofs();
	}

	private void filterProofs() {
		ArrayList<Post> possibleProofs = new ArrayList<Post>();
		PrintWriter writer;
		try {
			writer = new PrintWriter("proofs.txt", "UTF-8");
			for (Post post : posts.values()) {
				if ((post.getScore()) > 20
						&& (post.getPostTypeId() == 1)
						&& ((post.getBody().contains("proof")) || (post
								.getBody().contains("proving") || (post
								.getBody().contains("prove"))))) {
					if (post.getAcceptedAnswerId() != -1) {
						if (posts.get(post.getAcceptedAnswerId()) != null) {
							writer.println("===============================");
							writer.println("Question: " + post.getBody());
							writer.println(" ");
							writer.println("Answer: "
									+ posts.get(post.getAcceptedAnswerId())
											.getBody());
							writer.println("==============================");

							possibleProofs.add(posts.get(post
									.getAcceptedAnswerId()));
						}
					}
				}

			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readPost(XMLStreamReader reader) {
		int count = reader.getAttributeCount();
		int id = -1;
		int postTypeId = -1;
		int score = 0;
		String tags = null;
		String title = null;
		String body = null;
		int acceptedAnswerId = -1;
		int parentId = -1;
		for (int i = 0; i < count; i++) {
			String attribute = reader.getAttributeName(i).toString();
			switch (attribute) {
			case "Id":
				id = Integer.parseInt(reader.getAttributeValue(i));
				break;
			case "PostTypeId":
				postTypeId = Integer.parseInt(reader.getAttributeValue(i));
				break;
			case "Score":
				score = Integer.parseInt(reader.getAttributeValue(i));
				break;
			case "Tags":
				tags = reader.getAttributeValue(i);
				break;
			case "Title":
				title = reader.getAttributeValue(i);
				break;
			case "Body":
				body = reader.getAttributeValue(i);
				break;
			case "AcceptedAnswerId":
				acceptedAnswerId = Integer
						.parseInt(reader.getAttributeValue(i));
				break;
			case "ParentId":
				parentId = Integer.parseInt(reader.getAttributeValue(i));
				break;
			}
		}
		Post post = new Post(id, postTypeId, acceptedAnswerId, score, body,
				tags, title, parentId);
		posts.put(id, post);
	}
}
