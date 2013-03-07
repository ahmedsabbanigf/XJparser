/*
 * Licensed to the Apache Software Foundation (ASF) under one or
 * more contributor license agreements. See the NOTICE file distributed
 * with this work for additional information regarding copyright
 * ownership. The ASF licenses this file to you under the Apache
 * License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */

/**
 *
 */
package json;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

/**
 * 
 * @author Ben
 */
public class Converter {

	String input;
	String output;

	/**
	 * 
	 * @param path
	 *            relative path of the file to convert.
	 * @return
	 * @throws FileNotFoundException
	 */
	public final String convert(final String path, final boolean typeHints)
			throws FileNotFoundException {
		input = getFileAsString(path);

		XMLSerializer serializer = new XMLSerializer();
		serializer.setTypeHintsEnabled(typeHints);
		JSON serializedInput = JSONSerializer.toJSON(input);
		output = serializer.write(serializedInput);

		return output;
	}

	/**
	 * Extracts the content of a UTF-8 file as a string.
	 * 
	 * @param path
	 *            location of the file
	 * @return the string representing the content of the file in the given path
	 * @throws FileNotFoundException
	 */
	private final String getFileAsString(String path)
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
