package com.readmessages;

import java.io.*;
import java.util.*;

public class FileRdr {	
	
  public List<String> getMessagesFromFile(String fileName){
		List<String> msgList = new ArrayList<String>();
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
			System.out.println("No new message to process.");
		}
		finally {
			return msgList;
		}
	}
}
