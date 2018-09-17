package com.model;

import java.util.*;
import java.io.*;

public class SaleRecord implements Serializable {

	private String productType;
	private long productValue;
	private int saleCount;
	private long adjustmentAmount;
	private String adjustmentType;
	private String currency;
		
	public SaleRecord() {
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public long getProductValue() {
		return productValue;
	}

	public void setProductValue(long productValue) {
		this.productValue = productValue;
	}

	public int getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}

	public long getAdjustmentAmount() {
		return adjustmentAmount;
	}

	public void setAdjustmentAmount(long adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
	}

	public String getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
