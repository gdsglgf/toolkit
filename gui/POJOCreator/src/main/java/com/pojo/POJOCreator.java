package com.pojo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class POJOCreator {
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	/**
	 * read all lines in file
	 * @param filename file path
	 * @return
	 */
	private static List<String> readLines(String filename) {
		List<String> lines = new ArrayList<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line = null;
			while ((line = in.readLine()) != null) {
				lines.add(line);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lines;
	}

	/**
	 * create empty file
	 * @param filename file path
	 */
	private static void createFile(String filename) {
		File file = new File(filename);
		File parent = file.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void parsePackageAndClassName(POJO pojo, String s) {
		int i = s.lastIndexOf('.');
		if (i != -1) {
			pojo.setPackageName(s.substring(0, i));
			pojo.setClassName(s.substring(i + 1));
		} else {
			pojo.setClassName(s);
		}
	}
	
	public static POJO load(List<String> lines) {
		POJO pojo = new POJO();

		String line1 = lines.get(0);
		parsePackageAndClassName(pojo, line1);

		pojo.setFilename(line1.replace('.', '/') + ".java");

		List<Variable> variables = new ArrayList<Variable>();
		int size = lines.size();
		for (int i = 1; i < size; i++) {
			String[] kv = lines.get(i).split("=");
			if (kv.length == 2) {
				variables.add(new Variable(kv[1], kv[0]));
			}
		}
		pojo.setVariables(variables);

		return pojo;
	}

	public static POJO load(String filename) {
		List<String> lines = readLines(filename);
		return load(lines);
	}

	public static String toCamelString(String s) {
		String a = s.substring(0, 1).toUpperCase();
		String b = s.substring(1);
		return a + b;
	}

	public static String getter(Variable v) {
		String type = v.getType();
		String getName = "get";
		if (type.toLowerCase().equals("boolean")) {
			getName = "is";
		}
		return String.format("\tpublic %s %s%s() {%n\t\treturn %s;%n\t}%n",
				type, getName, toCamelString(v.getIdentifier()),
				v.getIdentifier());
	}

	public static String setter(Variable v) {
		return String
				.format("\tpublic void set%1$s(%2$s %3$s) {%n\t\tthis.%3$s = %3$s%n\t}%n",
						toCamelString(v.getIdentifier()), v.getType(),
						v.getIdentifier());
	}
	
	public static Set<String> parseTypeUnit(List<Variable> variables) {
		Set<String> types = new HashSet<String>();
		for (Variable v : variables) {
			String[] units = v.getType().split("<|,\\s*|>");
			for (String u : units) {
				types.add(u);
			}
		}
		return types;
	}

	public static String imports(List<Variable> variables) {
		Set<String> units = parseTypeUnit(variables);
		List<String> types = new ArrayList<String>();
		for (String u : units) {
			String t = Types.getType(u);
			if (t != null) {
				types.add(t);
			}
		}

		StringBuilder buf = new StringBuilder();
		if (types.size() > 0) {
			// sort types
			types.sort(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			
			for (String t : types) {
				buf.append(String.format("import %s;%n", t));
			}
			buf.append(LINE_SEPARATOR);
		}

		return buf.toString();
	}

	public static String toPOJOString(POJO pojo) {
		StringBuilder buf = new StringBuilder();
		String pk = pojo.getPackageName();
		if (pk != null) {
			buf.append(String.format("package %s;%n%n", pk));
		}

		buf.append(imports(pojo.getVariables()));

		buf.append(String.format("public class %s {%n", pojo.getClassName()));
		for (Variable v : pojo.getVariables()) {
			buf.append(String.format("\tprivate %s %s;%n", v.getType(),
					v.getIdentifier()));
		}
		buf.append(LINE_SEPARATOR);
		for (Variable v : pojo.getVariables()) {
			buf.append(getter(v));
			buf.append(setter(v));
		}
		buf.append(String.format("}%n"));
		return buf.toString();
	}

	public static void main(String[] args) {
		System.out.println(toPOJOString(load("data/example.txt")));
		
		String type = "Map<String,HashMap<String, List<T, R>>>";
		System.out.println(Arrays.toString(type.split("<|,\\s*|>")));
	}
}