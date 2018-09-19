package com.message.io;

import com.message.constants.ApplicationConstants;

import java.io.*;
import java.util.*;

/**
 * This class deals with reading the MessageSender.txt file and handling
 * appropriate messages to client if the file is not found or file name is null or empty string is passed into it.
 */
public class FileRdr {

	/**
	 * This methods reads the messages from the MessageSender.txt .
 	 * @param fileName of type {@link String}, it is file path of Message Interface used. here MessageSender.txt file.
	 * @return
	 */
  public List<String> getMessagesFromFile(String fileName){
		List<String> msgList = new ArrayList<String>();

		if(fileName==null || fileName.isEmpty()){
			System.out.print(ApplicationConstants.DEFAULT_MESSAGE);
			return msgList;
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			while (line != null) {
				msgList.add(line);
				line = br.readLine();
			}
			br.close();
		} 
		catch(Exception e){
			System.out.print(ApplicationConstants.DEFAULT_MESSAGE);
		}
	  return msgList;
	}
}
