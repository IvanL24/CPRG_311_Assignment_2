package domain;

import utilities.MyQueue;
import utilities.MyStack;

import java.io.*;
import java.util.NoSuchElementException;

import exceptions.EmptyQueueException;

public class Parser < E > {


    MyQueue < String > parserErrorQueue = null;
    MyStack < String > mainStack = null;
    MyQueue < String > parserExtraQueue = null;

    public Parser() {
        mainStack = new MyStack < String > ();
        parserErrorQueue = new MyQueue < String > ();
        parserExtraQueue = new MyQueue < String > ();
    }

    /**
     * @param File - input path of the file
     * @throws Exception generic type for all potential exceptions 
     */
    public void ImportXMLFile(String file) throws Exception {
        ParseXML(file);
        PrintExceptions();
        System.out.println("Post-Processing: Stack Size: " + mainStack.size());
        System.out.println("Post-Processing: Parser Error Queue Size: " + parserErrorQueue.size());
        System.out.println("Post-Processing: Comparison Queue Size: " + parserExtraQueue.size());
    }

    /**
     * This method will call VerifyMainStack() and VerifyQueues() 
     * @throws EmptyQueueException will throw from internal calls
     */
    private void PrintExceptions() throws EmptyQueueException {
        VerifyMainStack();
        VerifyQueues();
    }

    /**
     * Go through the queues, check if empty, otherwise print exceptions to the screen.
     * @throws EmptyQueueException will be thrown if no exceptions are found
     */
    private void VerifyQueues() throws EmptyQueueException {

        if (!parserExtraQueue.isEmpty() && !parserErrorQueue.isEmpty()) {

            while (parserErrorQueue.iterator().hasNext() && parserExtraQueue.iterator().hasNext()) {

                String exception = parserErrorQueue.dequeue();
                String information = parserExtraQueue.dequeue();

                if (!exception.equals(information)) {
                    System.out.println("XML Parser Violation: " + exception + " " + information);
                }
            }

        }

        if (!parserErrorQueue.isEmpty()) {
            while (!parserErrorQueue.isEmpty()) {
                System.out.println("XML Parser Violation: " + parserErrorQueue.dequeue());
            }
        } else if (!parserExtraQueue.isEmpty()) {
            while (!parserExtraQueue.isEmpty()) {
                System.out.println("XML Parser Violation: " + parserExtraQueue.dequeue());
            }
        }


    }

    /**
     * Unload the mainStack into the parser error queue
     */
    private void VerifyMainStack() {
        while (!mainStack.isEmpty()) {
            parserErrorQueue.enqueue(mainStack.pop());
        }
    }


    /**
     * Check if the start tag does not correspond to the end tag
     * @param line - line to process
     */
    private void CheckTagMismatch(String line) {

        String tagSelection = "";
        String subSelection = "";

        // Iterate through each character in the line
        for (int i = 0; i < line.length(); i++) {
            tagSelection = "";

            boolean process = (i + 1 != line.length());

            if (line.charAt(i) == '<' && process) {


                if (line.charAt(i + 1) == '/') {

                    // Grab last two characters (end tag)
                    subSelection = line.substring(i + 2);

                    for (int j = 0; subSelection.charAt(j) != '>' && j < subSelection.length(); j++) {
                        tagSelection += String.format("%s", subSelection.charAt(j));
                    }

                    // Call for end tag processing (verify against local containers)
                    ProcessEndTag(tagSelection);

                } else {

                    // Grab first two characters (begin tag)
                    subSelection = line.substring(i + 1);

                    for (int j = 0; subSelection.charAt(j) != '>' && j < subSelection.length(); j++) {
                        tagSelection += String.format("%s", subSelection.charAt(j));
                    }

                    // Push to main stack
                    mainStack.push(tagSelection);

                }


            }

        }
    }

    /**
     * Utility function for verifying if the tags are actually mismatching
     * @param tagSelection Substring of the end tag used for verification
     */
    private void ProcessEndTag(String tagSelection) {

        // Check if the main stack is empty - return
        if (mainStack.isEmpty()) {
            parserErrorQueue.enqueue("Main Stack is empty!");
            return;
        }

        // Check if the end tags match (EQUALS), pop off value as it has been marked off as correct - return
        if (mainStack.peek().equals(tagSelection)) {
            mainStack.pop();
            return;
        }

        // Check if the end tags are contained (CONTAINS) 
        if (mainStack.contains(tagSelection)) {

            while (!tagSelection.equals(mainStack.peek())) {
                parserErrorQueue.enqueue("[End Tag Mismatch]: " + mainStack.pop());
            }

            mainStack.pop();
            return;
        }

        parserExtraQueue.enqueue(tagSelection);

    }

    /**
     * Grab inner-value of tag and push to Main Stack
     * @param line Specified line to process
     */
    private void grabStartTag(String line) {

        String tagSelection = line.substring(line.indexOf('<') + 1, line.indexOf('>'));

        if (tagSelection.indexOf(' ') != -1) {
            // Grab value in-between the tags
            tagSelection = tagSelection.substring(
                0,
                tagSelection.indexOf(' ')
            );
        }

        // Push the tag value into the Main Stack
        mainStack.push(tagSelection);
    }

    /**
     * Will import contents from the provided file name and verify through a series of subsequent calls
     * @param xmlName - location of the file to read
     * @throws EmptyQueueException will throw if the queue is empty
     * @throws IOException will throw if the file can't be found or doesn't exist
     */
    public void ParseXML(String xmlName) throws IOException,
    EmptyQueueException {

        FileReader xmlFileReader = new FileReader(xmlName);

        BufferedReader bufferInput = new BufferedReader(
            xmlFileReader
        );

        bufferInput.readLine();
        String fileLine = bufferInput.readLine();


        while (fileLine != null) {


            // Tag counter for tags for each fileLine
            int countOpenTags = 0;
            int countCloseTags = 0;
            boolean processingLine = true;

            fileLine = fileLine.substring(
                fileLine.indexOf('<')
            );

            while (processingLine) {

                for (int i = 0; i < fileLine.length(); i++) {
                    if (fileLine.charAt(i) == '<') {
                        countOpenTags++;
                    } else if (fileLine.charAt(i) == '>') {
                        countCloseTags++;
                    }
                }

                // Constraint: Check if tag operators has corresponding closing tags

                if (countOpenTags != countCloseTags) {

                    parserErrorQueue.enqueue("[Too many tags] " + fileLine);
                    processingLine = false;

                } else {

                    // Get the ending and verify correct closing tag
                    if (fileLine.substring(fileLine.length() - 2, fileLine.length()).equals("/>")) {
                        processingLine = false;
                        continue;
                    }

                    // Check to see if end tag
                    if (fileLine.substring(0, 2).equals("</")) {
                        ProcessEndTag(fileLine.substring(2, fileLine.indexOf('>')));
                        break;
                    }

                    if (fileLine.substring(fileLine.length() - 2, fileLine.length()).equals("/>")) {
                        // Ignore the record
                        processingLine = false;
                        continue;
                    }

                    if (fileLine.substring(0, 2).equals("</")) {
                        ProcessEndTag(fileLine.substring(2, fileLine.indexOf('>')));
                        processingLine = false;
                        continue;
                    }

                    if (countOpenTags > 1 && countCloseTags > 1) {
                        CheckTagMismatch(fileLine);
                    } else {
                        grabStartTag(fileLine);
                    }

                    // Stop checking the line
                    processingLine = false;
                }

            }

            fileLine = bufferInput.readLine();
        }

        // Before returning, make sure the file handle has been closed.
        bufferInput.close();
    }


}