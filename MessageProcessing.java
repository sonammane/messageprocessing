import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageProcessing {

	public static void main(String[] args){
		FileReader reader = new FileReader();
		List<String> msgList = reader.getMessagesFromFile("../MessageSender.txt");
		Map<String, SaleRecord> salesMap = new HashMap<String, SaleRecord>();
	
		int msgCount = 0; 		
		int pauseAtTenMsg = 0; 
							
		while (msgList.size() != msgCount) {
			String msg = msgList.get(msgCount);
			msgCount++;
			pauseAtTenMsg++;
			
			if (pauseAtTenMsg == 10) {							
				logReportAfterTenMsg(salesMap);
				pauseAtTenMsg = 0; 			
			}

			if (msgCount == 50) {
				logReportAfterFiftyMsg(salesMap);
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
							sale = new Sale(1, cost);
							salesMap.put(name + "S", sale);
						}
					}			
			}			
		}
	}

	public void logReportAfterTenMsg(Map<String, SaleRecord> salesMap) {
		System.out.println("***Report after every 10th message***");
    for (Entry<String, SaleRecord> entry : salesMap.entrySet()) {
			System.out.println("Number of sales for "+entry.getKey()+" is " +entry.getValue().getTotalCount()+ 
          " and its total value is "+entry.getValue().getTotalValue());
			System.out.println("-----*****-----");
		}	
	}

	public void logReportAfterFiftyMsg(Map<String, SaleRecord> salesMap) {
    System.out.println("Cannot accept new message.Threshold of 50 messages is reached.Application is paused.");
		System.out.println("***Report after every 50th message***");
    for (Entry<String, SaleRecord> entry : salesMap.entrySet()) {
			System.out.println("Adjustments made to each sale of" + entry.getKey());
			if(entry.getValue().getAdjustmentsList().size>0){
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
