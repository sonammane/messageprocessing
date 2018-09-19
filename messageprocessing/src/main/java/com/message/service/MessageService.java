package com.message.service;

import java.util.*;

import com.message.constants.ApplicationConstants;
import com.message.model.SaleRecord;
import com.message.ui.ReportGenerator;
import com.message.io.FileRdr;

/**
 * This class handles the business logic of message processing.
 */
public class MessageService {

	Map<String, List<SaleRecord>> salesMap = new HashMap<String, List<SaleRecord>>();
	int msgCount = 0; 		
	int afterTenthMsg = 0;

	/**
	 * This method does the message processing.
	 * @param fileName of type {@link String}
	 */
	public void processMessage(String fileName) {
		FileRdr reader = new FileRdr();
		List<String> msgList = reader.getMessagesFromFile(fileName);
		while (msgList.size() != msgCount) {
			String msg = msgList.get(msgCount);
			msgCount++;
			afterTenthMsg++;

			ReportGenerator reportGenerator = new ReportGenerator();
			if (afterTenthMsg > 10) {
				reportGenerator.logReportAfterTenMsg(salesMap);
				afterTenthMsg = 0;
			}

			if (msgCount > 50) {
				reportGenerator.logReportAfterFiftyMsg(salesMap);
				break;
			}
      
			String[] msgArr = msg.split(" ");
			recordSales(msgArr);
		}
	}

	/**
	 * This method stores each message received into Sale Record bean and sales map.
	 * @param msgArr of type {@link String[]}
	 */
	private void recordSales(String[] msgArr){
		if(msgArr.length>=3) {
			if (ApplicationConstants.ADD_ADJUSTMENT.equalsIgnoreCase(msgArr[0]) ||
					ApplicationConstants.SUBTRACT_ADJUSTMENT.equalsIgnoreCase(msgArr[0]) ||
					ApplicationConstants.MULTIPLY_ADJUSTMENT.equalsIgnoreCase(msgArr[0])) {
				handleMessageTypeThree(msgArr);
			} else {
				char c = msgArr[0].charAt(0);
				if (Character.isDigit(c)) {
					handleMessageTypeTwo(msgArr);
				} else if (Character.isLetter(c)) {
					handleMessageTypeOne(msgArr);
				}
			}
		}
	}

	/**
	 * This method stores message type one into Sale Record bean.
	 * eg. "apple at 10 p"
	 * @param msgArr of type {@link String[]}
	 */
	private void handleMessageTypeOne(String[] msgArr){
		SaleRecord saleRecord = new SaleRecord();
		saleRecord.setProductType(msgArr[0]);
		saleRecord.setProductValue(Long.parseLong(msgArr[2].substring(0, msgArr[2].length() - 1)));
		saleRecord.setCurrency(msgArr[2].substring( msgArr[2].length() - 1));
		saleRecord.setSaleCount(1);
		saleRecord.setAdjustmentType(null);
		saleRecord.setAdjustmentAmount(0);
		addToSalesMap(msgArr[0],saleRecord);
	}

	/**
	 * This method stores message type two into Sale Record bean.
	 * eg. "20 sales of apple at 10p each"
	 * @param msgArr of type {@link String[]}
	 */
	private void handleMessageTypeTwo(String[] msgArr){
		SaleRecord saleRecord = new SaleRecord();
		saleRecord.setProductType(msgArr[3]);
		saleRecord.setProductValue(Long.parseLong(msgArr[5].substring(0, msgArr[5].length() - 1)));
		saleRecord.setCurrency(msgArr[5].substring( msgArr[5].length() - 1));
		saleRecord.setSaleCount(Integer.parseInt(msgArr[0]));
		saleRecord.setAdjustmentType(null);
		saleRecord.setAdjustmentAmount(0);
		addToSalesMap(msgArr[3],saleRecord);
	}

	/**
	 * This method stores message type three into Sale Record bean.
	 * eg. "Add 20p apples" ; "Subtract 20p apples" ; "Multiply 20p apples"
	 * @param msgArr of type {@link String[]}
	 */
	private void handleMessageTypeThree(String[] msgArr){
		SaleRecord saleRecord = new SaleRecord();
		saleRecord.setProductType(msgArr[2]);
		saleRecord.setProductValue(0);
		saleRecord.setCurrency(msgArr[1].substring( msgArr[1].length() - 1));
		saleRecord.setSaleCount(0);
		saleRecord.setAdjustmentType(msgArr[0]);
		saleRecord.setAdjustmentAmount(Long.parseLong(msgArr[1].substring(0, msgArr[1].length() - 1)));
		addToSalesMap(msgArr[2],saleRecord);
	}

	/**
	 * This method adds each SaleRecord to salesMap
	 * @param productType of type {@link String}
	 * @param saleRecord of type {@link SaleRecord}
	 */
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
