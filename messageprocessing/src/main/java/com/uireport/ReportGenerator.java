package com.uireport;

import java.util.*;
import com.model.SaleRecord;

public class ReportGenerator {

    public void logReportAfterTenMsg(Map<String,  List<SaleRecord>> salesMap) {
		System.out.println("***Report after every 10th message***");
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
						equalsIgnoreCase("add")){
					totalValue = saleRecord.getAdjustmentAmount()* saleTotal+totalValue;
				}
				if(saleRecord.getAdjustmentType()!=null && saleRecord.getAdjustmentType().
						equalsIgnoreCase("subtract")){
					totalValue = totalValue-saleRecord.getAdjustmentAmount()* saleTotal;
				}
				if(saleRecord.getAdjustmentType()!=null && saleRecord.getAdjustmentType().
						equalsIgnoreCase("multiply")){
					totalValue = saleRecord.getAdjustmentAmount()* saleTotal*totalValue;
				}
			}

			if(totalValue>100){
				totalValue = totalValue/100;
			}
			System.out.println("Number of sales for "+entry.getKey()+" is " +saleTotal+
          	" and its total value is £"+totalValue );
			System.out.println("-----*****-----");
		}	
	}

	public void logReportAfterFiftyMsg(Map<String, List<SaleRecord>> salesMap) {
    	System.out.println("Sorry!!Cannot accept new message.");
		System.out.println("***Report after every 50th message***");
    	for (Map.Entry<String, List<SaleRecord>> entry : salesMap.entrySet()) {
			System.out.println("Adjustments made to each sale of " + entry.getKey());
			List<SaleRecord> saleRecordList = entry.getValue();
			for(SaleRecord saleRecord : saleRecordList){
				if(saleRecord.getAdjustmentType()!=null){
					System.out.println(saleRecord.getAdjustmentType()+ " " +saleRecord.getAdjustmentAmount() +
							saleRecord.getCurrency() + " " +entry.getKey());
				}
			}
			System.out.println("-----*****-----");
		}
	}
}