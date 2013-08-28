package com.rj.cachedemo.model;

import java.io.Serializable;
import java.util.Date;

public class Trade implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final long uid;
	private String description;
	private double payNotional;
	private double recNotional;
	private Date maturity;
	private Date expiry;
	private String productGroup;
	private String summary;

	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPayNotional() {
		return payNotional;
	}
	public void setPayNotional(double payNotional) {
		this.payNotional = payNotional;
	}
	public double getRecNotional() {
		return recNotional;
	}
	public void setRecNotional(double recNotional) {
		this.recNotional = recNotional;
	}
	public Date getMaturity() {
		return maturity;
	}
	public void setMaturity(Date maturity) {
		this.maturity = maturity;
	}
	public Date getExpiry() {
		return expiry;
	}
	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}
	public String getProductGroup() {
		return productGroup;
	}
	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Trade(long uid)
	{
		this.uid = uid;
	}
	public long getUid() {
		return uid;
	}
	@Override
	public String toString() {
		return "Trade [uid=" + uid + "]";
	}

	
}
