package com.ipinyou.entity.selfadvertiser;

public class OrderInfo {
	private String adname;
	private String orname;
	private String orderbudget;
	private String contractNo;
	private String KPI1;
	private String KPI2;
	private String converpoint;
	public String getConverpoint() {
		return converpoint;
	}
	public void setConverpoint(String converpoint) {
		this.converpoint = converpoint;
	}
	public String getKPI1() {
		return KPI1;
	}
	public void setKPI1(String kPI1) {
		KPI1 = kPI1;
	}
	public String getKPI2() {
		return KPI2;
	}
	public void setKPI2(String kPI2) {
		KPI2 = kPI2;
	}
	
	public String getAdname() {
		return adname;
	}
	public void setAdname(String adname) {
		this.adname = adname;
	}
	public String getOrname() {
		return orname;
	}
	public void setOrname(String orname) {
		this.orname = orname;
	}
	public String getOrderbudget() {
		return orderbudget;
	}
	public void setOrderbudget(String orderbudget) {
		this.orderbudget = orderbudget;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

}
