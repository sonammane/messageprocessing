package com.message.ui;

import java.util.*;

import com.message.constants.ApplicationConstants;
import com.message.model.SaleRecord;

/**
 * This class logs the report after every 10th message and after 50th message.
 */
public class ReportGenerator {

	/**
	 * This method log a report after every 10th message received.
	 * @param salesMap of type {@link Map}
	 */
    public void logReportAfterTenMsg(Map<String,  List<SaleRecord>> salesMap) {
		System.out.println(ApplicationConstants.AFTER_TENTH_MESSAGE);
    	for (Map.Entry<String,  List<SaleRecord>> entry : salesMap.entrySet()) {
    		List<SaleRecord> saleRecordList = entry.getValue();
    		int saleTotal =0;
    		float totalValue = 0;
    		for(SaleRecord saleRecord : saleRecordList){
    			if(saleRecord.getSaleCount()==1){
					totalValue = saleRecord.getProductValue()+totalValue;
				}else if(saleRecord.getSaleCount()>1){
					totalValue = saleRecord.getProductValue()* saleRecord.getSaleCount()+totalValue;
				}
				saleTotal = saleRecord.getSaleCount() + saleTotal;

				if(saleRecord.getAdjustmentType()!=null && saleRecord.getAdjustmentType().
						equalsIgnoreCase(ApplicationConstants.ADD_ADJUSTMENT)){
					totalValue = saleRecord.getAdjustmentAmount()* saleTotal+totalValue;
				}
				if(saleRecord.getAdjustmentType()!=null && saleRecord.getAdjustmentType().
						equalsIgnoreCase(ApplicationConstants.SUBTRACT_ADJUSTMENT)){
					totalValue = totalValue-saleRecord.getAdjustmentAmount()* saleTotal;
				}
				if(saleRecord.getAdjustmentType()!=null && saleRecord.getAdjustmentType().
						equalsIgnoreCase(ApplicationConstants.MULTIPLY_ADJUSTMENT)){
					totalValue = saleRecord.getAdjustmentAmount()* saleTotal*totalValue;
				}
			}

			if(totalValue>100){
				totalValue = totalValue/100;
			}
			System.out.println(ApplicationConstants.NUMBER_OF_SALES_TEXT+entry.getKey().toUpperCase()+
					ApplicationConstants.IS_TEXT +saleTotal+
          	ApplicationConstants.TOTAL_VALUE_TEXT+totalValue );
		}
		System.out.println(ApplicationConstants.END_OF_REPORT_MESSAGE);
		System.out.println(ApplicationConstants.BLANK_MESSAGE);
	}

	/**
	 * This method log a report after 50th message received and indicate that the application is now paused.
	 * @param salesMap of type {@link Map}
	 */
	public void logReportAfterFiftyMsg(Map<String, List<SaleRecord>> salesMap) {
    	System.out.println(ApplicationConstants.SORRY_MESSAGE);
		System.out.println(ApplicationConstants.AFTER_FIFTYTH_MESSAGE);
    	for (Map.Entry<String, List<SaleRecord>> entry : salesMap.entrySet()) {
			System.out.println(ApplicationConstants.ADJUSTMENT_TEXT + entry.getKey().toUpperCase());
			List<SaleRecord> saleRecordList = entry.getValue();
			for(SaleRecord saleRecord : saleRecordList){
				if(saleRecord.getAdjustmentType()!=null){
					System.out.println(adjustmentTypeText(saleRecord.getAdjustmentType())+ " " +saleRecord.getAdjustmentAmount() +
							saleRecord.getCurrency());
				}
			}
		}
		System.out.println(ApplicationConstants.END_OF_REPORT_MESSAGE);
	}

	/**
	 * This method is to identify adjustment type for report text.
	 * @param adjustmentType of type {@link String}
	 * @return String
	 */
	private String adjustmentTypeText(String adjustmentType){
    	if(ApplicationConstants.ADD_ADJUSTMENT.equalsIgnoreCase(adjustmentType)){
    		return ApplicationConstants.ADDED;
		}
		if(ApplicationConstants.SUBTRACT_ADJUSTMENT.equalsIgnoreCase(adjustmentType)){
			return ApplicationConstants.SUBTRACTED;
		}
		if(ApplicationConstants.MULTIPLY_ADJUSTMENT.equalsIgnoreCase(adjustmentType)){
			return ApplicationConstants.MULTIPLIED;
		}
		return adjustmentType;
	}
}