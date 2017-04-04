import java.util.List;

public class SaleRecord implements Serializable {
	
	private int totalCount ;
	private long totalValue;
	private List<String> adjustmentsList;
		
	public SaleRecord(int totalCount, long totalValue) {
		this.totalCount = totalCount;
		this.totalValue = totalValue;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public long getTotalValue() {
		return totalValue;
	}
	
	public void setTotalValue(long totalValue) {
		this.totalValue = totalValue;
	}

	public List<String> getAdjustmentsList() {
		if(adjustmentsList==null){
			adjustmentsList = new ArrayList<String>();
		}	
		return adjustmentsList;
	}
}
