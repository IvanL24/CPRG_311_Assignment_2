package domain;

import java.io.*;

import java.util.NoSuchElementException;

public class AppDriver {

public static <E> void main(String[] args) {
		
		try {
			String file = args[0];
			Parser<E> parser = new Parser<E>();
			parser.loadXMLFile("res/" + file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		
	}

}
