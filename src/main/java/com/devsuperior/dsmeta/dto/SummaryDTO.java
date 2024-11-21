package com.devsuperior.dsmeta.dto;

public class SummaryDTO {
	private String sellerName;
	private double total;

	public SummaryDTO(String sellerName, double total) {
		super();
		this.sellerName = sellerName;
		this.total = total;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
