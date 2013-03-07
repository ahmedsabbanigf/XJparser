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

package xml;

		
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

public class Converter {
	
	String input;
	String output;
	
	private final String getFileAsString(String path)
			throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(path);
		String inputStreamString = new Scanner(fis, "UTF-8")
				.useDelimiter("\\A").next();

		return inputStreamString;
	}
	
	public final String convert(final String path, final boolean typeHints)
			throws FileNotFoundException {
		input = getFileAsString(path);

		XMLSerializer serializer = new XMLSerializer();
		JSON json = serializer.read( input );
		output = json.toString(2);

		return output;
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
