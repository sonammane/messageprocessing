package com.service;

import java.util.*;
import com.model.SaleRecord;
import com.uireport.ReportGenerator;
import com.readmessages.FileRdr;

public class MessageService {

	Map<String, List<SaleRecord>> salesMap = new HashMap<String, List<SaleRecord>>();
	int msgCount = 0; 		
	int pauseAtTenMsg = 0;

	public void processMessage() {
		FileRdr reader = new FileRdr();
		List<String> msgList = reader.getMessagesFromFile("C:\\Users\\Abhimanyu Taunk\\Documents\\FirstJavaApp\\messageprocessing\\messageprocessing\\src\\main\\resources\\MessageSender.txt");
		if(msgList.size()==msgCount){
			System.out.println("No message to process.");
		}
		while (msgList.size() != msgCount) {
			String msg = msgList.get(msgCount);
			msgCount++;
			pauseAtTenMsg++;

			ReportGenerator reportGenerator = new ReportGenerator();
			if (pauseAtTenMsg > 10) {
				reportGenerator.logReportAfterTenMsg(salesMap);
				pauseAtTenMsg = 0; 			
			}

			if (msgCount > 50) {
				reportGenerator.logReportAfterFiftyMsg(salesMap);
				System.exit(0);
			}
      
			String[] msgArr = msg.split(" ");
			recordSales(msgArr);
		}
	}

	private void recordSales(String[] msgArr){
		if(msgArr.length>=3) {
			// eg.; "Add 20p apples"
			if ("Add".equalsIgnoreCase(msgArr[0]) || "Subtract".equalsIgnoreCase(msgArr[0]) ||
					"Multiply".equalsIgnoreCase(msgArr[0])) {
				SaleRecord saleRecord = new SaleRecord();
				saleRecord.setProductType(msgArr[2]);
				saleRecord.setProductValue(0);
				saleRecord.setCurrency(msgArr[1].substring( msgArr[1].length() - 1));
				saleRecord.setSaleCount(0);
				saleRecord.setAdjustmentType(msgArr[0]);
				saleRecord.setAdjustmentAmount(Long.parseLong(msgArr[1].substring(0, msgArr[1].length() - 1)));
				addToSalesMap(msgArr[2],saleRecord);
			} else {
				//apple at 10 p
				//20 sales of apple at 10p each
				char c = msgArr[0].charAt(0);
				if (Character.isDigit(c)) {
					SaleRecord saleRecord = new SaleRecord();
					saleRecord.setProductType(msgArr[3]);
					saleRecord.setProductValue(Long.parseLong(msgArr[5].substring(0, msgArr[5].length() - 1)));
					saleRecord.setCurrency(msgArr[5].substring( msgArr[5].length() - 1));
					saleRecord.setSaleCount(Integer.parseInt(msgArr[0]));
					saleRecord.setAdjustmentType(null);
					saleRecord.setAdjustmentAmount(0);
					addToSalesMap(msgArr[3],saleRecord);
				} else if (Character.isLetter(c)) {
					SaleRecord saleRecord = new SaleRecord();
					saleRecord.setProductType(msgArr[0]);
					saleRecord.setProductValue(Long.parseLong(msgArr[2].substring(0, msgArr[2].length() - 1)));
					saleRecord.setCurrency(msgArr[2].substring( msgArr[2].length() - 1));
					saleRecord.setSaleCount(1);
					saleRecord.setAdjustmentType(null);
					saleRecord.setAdjustmentAmount(0);
					addToSalesMap(msgArr[0],saleRecord);
				}
			}
		}
	}

	private void addToSalesMap(String productType, SaleRecord saleRecord){
		if(!salesMap.containsKey(productType)){
			List<SaleRecord>  saleRecordList = new ArrayList<>();
			saleRecordList.add(saleRecord);
			salesMap.put(productType,saleRecordList);
		}else {
			salesMap.get(productType).add(saleRecord);
		}
	}
}
