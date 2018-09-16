package com.processor;

import java.util.*;
import com.model.SaleRecord;
import com.handler.ReportGenerator;
import com.dataprocessor.FileRdr;

public class MessageProcessor {

	List<String> msgList = new ArrayList<>();
	Map<String, SaleRecord> salesMap = new HashMap<String, SaleRecord>();
	ReportGenerator reportGenerator = new ReportGenerator();
	FileRdr reader = new FileRdr();
	int msgCount = 0; 		
	int pauseAtTenMsg = 0;

	public void messageProcessing() {
		msgList = reader.getMessagesFromFile("MessageSender.txt");

		while (msgList.size() != msgCount) {
			String msg = msgList.get(msgCount);
			msgCount++;
			pauseAtTenMsg++;
			
			if (pauseAtTenMsg == 10) {							
				reportGenerator.logReportAfterTenMsg(salesMap);
				pauseAtTenMsg = 0; 			
			}

			if (msgCount == 50) {
				reportGenerator.logReportAfterFiftyMsg(salesMap);
				System.exit(0);
			}
      
			String[] msgArr = msg.split(" ");
			
			if ("Add".equalsIgnoreCase(msgArr[0])) {				
				int cost = Integer.parseInt(msgArr[1].substring(0,msgArr[1].length() - 1));
				SaleRecord sale = salesMap.get(msgArr[2].toUpperCase());
				if (sale != null) {
					sale.setTotalValue(sale.getTotalValue()+ sale.getTotalCount() * cost);
					sale.getAdjustmentsList().add(msgArr[0]+" "+msgArr[1]);	
				}
			} else if ("Subtract".equalsIgnoreCase(msgArr[0])) { 				
					int cost = Integer.parseInt(msgArr[1].substring(0,msgArr[1].length() - 1));
					SaleRecord sale = salesMap.get(msgArr[2].toUpperCase());
					if (sale != null) {
						sale.setTotalValue(sale.getTotalValue()- sale.getTotalCount() * cost);
						sale.getAdjustmentsList().add(msgArr[0]+" "+msgArr[1]);	
				}
			} else if ("Multiply".equalsIgnoreCase(msgArr[0])) {				
				int cost = Integer.parseInt(msgArr[1].substring(0,msgArr[1].length() - 1));
        		SaleRecord sale = salesMap.get(msgArr[2].toUpperCase());
				if (sale != null) {
					sale.setTotalValue(sale.getTotalValue() * sale.getTotalCount() * cost);
					sale.getAdjustmentsList().add(msgArr[0]+" "+msgArr[1]);	
				}
			} else {
				int count = 0;			
				char c = msgArr[0].charAt(0);
				if(Character.isDigit(c)){
						count = Integer.parseInt(msgArr[0]);	
						String name = msgArr[3].toUpperCase();
						int cost = Integer.parseInt(msgArr[5].substring(0,msgArr[5].length() - 1));
            			SaleRecord sale = salesMap.get(name);
            			if (sale != null) {
							sale.setTotalCount(sale.getTotalCount() + count);
							sale.setTotalValue(sale.getTotalValue() + count * cost);
						} else {
							sale = new SaleRecord(count, count * cost);
							salesMap.put(name, sale);
						}
					}else if(Character.isLetter(c)){
						String name = msgArr[0].toUpperCase();
						int cost = Integer.parseInt(msgArr[2].substring(0,msgArr[2].length() - 1));
						SaleRecord sale = salesMap.get(name + "S");					
						if (sale != null) {
							sale.setTotalCount(sale.getTotalCount() + 1);
							sale.setTotalValue(sale.getTotalValue() + cost);
						} else {
							sale = new SaleRecord(1, cost);
							salesMap.put(name + "S", sale);
						}
					}			
			}			
		}
	}	
}
