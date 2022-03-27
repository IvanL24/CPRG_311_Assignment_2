package domain;

import utilities.MyQueue;
import utilities.MyStack;

import java.io.*;
import java.util.NoSuchElementException;

public class Parser<E> {
	
	MyStack<String> stack = null;
	MyQueue<String> errorQueue = null;
	MyQueue<String> extraQueue = null;
	
	/**
	 * Constructs a new Parser object
	 */
	public Parser(){
		stack = new MyStack<String>();
		errorQueue = new MyQueue<String>();
		extraQueue = new MyQueue<String>();
	}
	
	/**
	 * A method to load and parse the xml file
	 * @param file - file to read
	 * @throws IOException was thrown if the file does not exist
	 * @throws EmptyQueueException was thrown if the queue is empty
	 */
	public void loadXMLFile(String file) throws IOException, NoSuchElementException {
		parseFile(file);
		printErrors();
		System.out.println("stack size: " + stack.size());
		System.out.println("errorQueue size: " + errorQueue.size());
		System.out.println("extraQueue size: " + extraQueue.size());
	}
	
	/**
	 * A method used to print the errors left in the stack and queue
	 * @throws EmptyQueueException was thrown if the queue is empty
	 */
	private void printErrors() throws NoSuchElementException {
		checkStack();
		checkQueues();
		
	}
	
	/**
	 * A method used to check if the queues are empty or not
	 * If the queue is not empty, errors are reported
	 * @throws EmptyQueueException was thrown if the queue is empty
	 */
	private void checkQueues() throws NoSuchElementException {
		if(!errorQueue.isEmpty() && !extraQueue.isEmpty()) {
			while(!errorQueue.isEmpty() && !extraQueue.isEmpty()) {
				String error = errorQueue.dequeue();
				String extra = extraQueue.dequeue();
				if(!error.equals(extra)) {
					System.out.println("Error: Queues didn't matched. " + error);
					System.out.println("Error: Queues didn't matched. " + extra);
				}
			}
		}
		
		if(!errorQueue.isEmpty()) {
			while(!errorQueue.isEmpty()) {
				System.out.println("Error: ErrorQueue. " + errorQueue.dequeue());
			}
		} else if(!extraQueue.isEmpty()) {
			while(!extraQueue.isEmpty()) {
				System.out.println("Error: ExtraQueue. " + errorQueue.dequeue());
			}
		}
		
	}
	
	/**
	 * A method used to check if the stack is empty or not
	 * If the stack is not empty, errors are added into the queue
	 */
	private void checkStack() {
		if(!stack.isEmpty()) {
			while(!stack.isEmpty()) {
				errorQueue.enqueue(stack.pop());
			}
		}
	}
	
	/**
	 * A method used to parse the file given
	 * @param file - file to read
	 * @throws IOException was thrown if the file does not exist
	 * @throws EmptyQueueException was thrown if the queue is empty
	 */
	private void parseFile(String file) throws IOException, NoSuchElementException {
		
		BufferedReader in = new BufferedReader(new FileReader(file));
		boolean notprocessed;
		
		in.readLine();
		String line = in.readLine();
		
		while(line != null) {
			// count for tags for each line
			int open = 0;
			int close = 0;
			notprocessed = true;
			
			line = line.substring(line.indexOf('<'));
			
			while(notprocessed) {
				for(int i=0; i<line.length(); i++) {
					if(line.charAt(i) == '<') {
						open++;
					} else if(line.charAt(i) == '>') {
						close++;
					}
				}
				
				// check if tag operators has corresponding closing tags
				if(open != close) {
					errorQueue.enqueue("Too many tags. " + line);
					notprocessed = false;
				} else {
					
					if(line.substring(line.length()-2, line.length()).equals("/>")) {
						notprocessed = false;
					} else if(line.substring(0, 2).equals("</")) {
						processEndTag(line.substring(2,line.indexOf('>')));
					} else {
						
						if(line.substring(line.length()-2, line.length()).equals("/>")) {
							//ignore
							notprocessed = false;
						} else if(line.substring(0, 2).equals("</")) {
							processEndTag(line.substring(2,line.indexOf('>')));
						} else {
							if(open > 1 && close > 1) {
								processNotStartEnd(line);
							} else {
								processStartTagMultipleLines(line);
							}					
						}

					}
					notprocessed = false;		
				}
			
			}
			
			line = in.readLine();
		}
		
		in.close();
	}
	
	/**
	 * A method used to process a line with multiple tags found in it
	 * @param line - line to process
	 */
	private void processStartTagMultipleLines(String line) {
		String token = line.substring(line.indexOf('<') + 1, line.indexOf('>'));
		
		if(token.indexOf(' ') > -1) {
			token = token.substring(0, token.indexOf(' '));
		}
		
		stack.push(token);
		
	}
	
	/**
	 * A method used to process the start of the tag
	 * @param line - line to process
	 */
	private void processNotStartEnd(String line) {
		String substring = "";
		String token = "";
		
		for(int i=0; i<line.length(); i++) {
			token = "";
			if(line.charAt(i) == '<') {
				if(i+1 != line.length()) {
					
					if(line.charAt(i+1) == '/') {
						substring = line.substring(i+2);
						for(int j=0; j<substring.length() && substring.charAt(j) != '>'; j++) {
							token += String.format("%s", substring.charAt(j));
						}
						processEndTag(token);
					} else {
						substring = line.substring(i+1);
						for(int j=0; j<substring.length() && substring.charAt(j) != '>'; j++) {
							token += String.format("%s", substring.charAt(j));
						}
						stack.push(token);						
					}
				}
			}
		}
	}
	
	/**
	 * A method used to process the end of the tag
	 * @param token token extracted from a line known as an end tag
	 */
	private void processEndTag(String token) {
		
		if(stack.isEmpty()) {
			errorQueue.enqueue("Stack is empty.");
		} else {
			
			if(stack.peek().equals(token)) {
				stack.pop();
			} else if(stack.contains(token)) {
				while(!token.equals(stack.peek())) {
					errorQueue.enqueue(stack.pop());
				}
				stack.pop();
			} else {
				extraQueue.enqueue(token);
			}
			
		}

	}
	
}


