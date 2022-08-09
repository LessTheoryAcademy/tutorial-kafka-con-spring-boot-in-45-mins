package com.lta.springkafkacommons;

import java.util.List;

public class NewOrder {

	private String customerName;
	private List<Long> productIds;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public List<Long> getProductIds() {
		return productIds;
	}
	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}
	
	@Override
	public String toString() {
		
		String productIdsString = "";
		
		for(Long id : productIds) {
			
			productIdsString += " - " + id;
		}
		
		return "customer name : " + this.customerName + " products : " + productIdsString; 
	}
}