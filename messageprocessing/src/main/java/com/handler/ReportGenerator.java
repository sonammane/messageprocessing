package com.handler;

import java.util.*;
import com.model.SaleRecord;

public class ReportGenerator {

    public void logReportAfterTenMsg(Map<String, SaleRecord> salesMap) {
		System.out.println("***Report after every 10th message***");
    for (Map.Entry<String, SaleRecord> entry : salesMap.entrySet()) {
			System.out.println("Number of sales for "+entry.getKey()+" is " +entry.getValue().getTotalCount()+ 
          " and its total value is "+entry.getValue().getTotalValue());
			System.out.println("-----*****-----");
		}	
	}

	public void logReportAfterFiftyMsg(Map<String, SaleRecord> salesMap) {
    System.out.println("Cannot accept new message.Threshold of 50 messages is reached.Application is paused.");
		System.out.println("***Report after every 50th message***");
    for (Map.Entry<String, SaleRecord> entry : salesMap.entrySet()) {
			System.out.println("Adjustments made to each sale of" + entry.getKey());
			if(entry.getValue().getAdjustmentsList().size()>0){
				for(String adjustments :entry.getValue().getAdjustmentsList()){
					System.out.println(adjustments);
				}
			}
			System.out.println("----------");
			System.out.println(entry.getKey()+"total sales count: "+ entry.getValue().getTotalCount());
			System.out.println(entry.getKey()+"total sales value: "+ entry.getValue().getTotalValue());
			System.out.println("-----*****-----");
		}
	}
}