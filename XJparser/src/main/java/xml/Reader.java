package xml;

import java.io.File;
import java.util.List;

import org.jdom2.input.SAXBuilder;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Reader {

	private static Document document;
	private static Element racine;

	public Reader(String path) {
		build(path);
		try {
			System.out.println(convertElem(racine).toString(2));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void build(String path) {
		SAXBuilder sxb = new SAXBuilder();
		try {
			document = sxb.build(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		racine = document.getRootElement();
	}

	private JSONObject convertElem(Element root) {
		JSONObject obj = new JSONObject();
		JSONArray list = new JSONArray();
		List<Element> children = root.getChildren();
		
		if (root.hasAttributes()) {
			convertAttributes(root.getAttributes(),obj);
		}
		if (!children.isEmpty()) {
			for (Element child : children) {
				list.put(convertElem(child));
			}
			try {
				obj.put(children.get(1).getName(), list);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return obj;
	}

	private void printAttribute(final Attribute attrib) {
		System.out.println(attrib.getName() + " " + attrib.getValue());
	}
	
	private void convertAttributes(final List<Attribute> attributes, JSONObject obj){
		try {
			for (Attribute attrib : attributes) {
				obj.put(attrib.getName(),attrib.getValue());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
