package com.message.model;

import java.io.*;

/**
 * This is a bean to hold the Sale related data.
 */
public class SaleRecord implements Serializable {

	private String productType;
	private long productValue;
	private int saleCount;
	private long adjustmentAmount;
	private String adjustmentType;
	private String currency;
		
	public SaleRecord() {
	}

	/**
	 * Sets the product type passed.
	 * @param productType of type {@link String}
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * Gets the product value.
	 * @return long
	 */
	public long getProductValue() {
		return productValue;
	}

	/**
	 *  Sets the product value passed.
	 * @param productValue of type {@link Long}
	 */
	public void setProductValue(long productValue) {
		this.productValue = productValue;
	}

	/**
	 * Gets the Sale count.
	 * @return int
	 */
	public int getSaleCount() {
		return saleCount;
	}

	/**
	 * Sets the sale count passed.
	 * @param saleCount of type int
	 */
	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}

	/**
	 * Gets the adjustment amount.
	 * @return long
	 */
	public long getAdjustmentAmount() {
		return adjustmentAmount;
	}

	/**
	 * Sets the adjustment amount.
	 * @param adjustmentAmount of type {@link Long}
	 */
	public void setAdjustmentAmount(long adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
	}

	/**
	 * Gets the adjustment type.
	 * @return String
	 */
	public String getAdjustmentType() {
		return adjustmentType;
	}

	/**
	 * Sets the adjustment type.
	 * @param adjustmentType of type {@link String}
	 */
	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	/**
	 * Gets the currency.
	 * @return of type {@link String}
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Sets the currency.
	 * @param currency of type {@link String}
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
