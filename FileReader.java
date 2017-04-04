import java.io.*;
import java.util.*;

public class FileReader {	
	
  public List<String> getMessagesFromFile(String fileName){
		List<String> msgList = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(fileName);
		DataInputStream dis = new DataInputStream(fis);
		BufferedReader br = new BufferedReader(new InputStreamReader(dis));
		String strLine = br.readLine();	
		while (strLine != null) {
			msgList.add(strLine);
			strLine = br.readLine();
		}		
		fis.close();
		dis.close();	
		return msgList;
	}
}
