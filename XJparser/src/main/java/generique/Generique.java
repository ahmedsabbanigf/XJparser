package generique;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Generique {
	
	protected String input;
	protected String output;

	public final String getFileAsString(String path)
			throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(path);
		String inputStreamString = new Scanner(fis, "UTF-8")
				.useDelimiter("\\A").next();

		return inputStreamString;
	}
	
	public final boolean save(String path) {
		FileWriter fstream;
		BufferedWriter out;
		boolean sucess = false;
		try {
			fstream = new FileWriter(path);
			out = new BufferedWriter(fstream);
			out.write(output);
			out.close();
			sucess = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sucess;
	}
}
