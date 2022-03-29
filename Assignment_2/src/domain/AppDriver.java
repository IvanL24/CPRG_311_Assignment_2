package domain;

import java.io.*;

import java.util.NoSuchElementException;

import exceptions.EmptyQueueException;

public class AppDriver {

public static <E> void main(String[] args) {
		
		try {
			String file = args[0];
			Parser<String> parser = new Parser<String>();
			parser.ImportXMLFile("res/" + file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch(EmptyQueueException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
