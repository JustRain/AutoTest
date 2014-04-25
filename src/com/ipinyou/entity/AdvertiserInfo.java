package com.ipinyou.entity;

public class AdvertiserInfo {
	 public String adname;
	 public String registername;
	 public String advertiserwebsite;
	 public String cellphone;
	 public String contactname;
	 public String email;
	 public String path;
	 public String industrytext;
	 public String servicefeerate; 
	 public String orgcodeno;
	 private String deladname;
	
	public String getAdname() {
		return adname;
	}

	public void setAdname(String adname) {
		this.adname = adname;
	}

	public String getRegistername() {
		return registername;
	}

	public void setRegistername(String registername) {
		this.registername = registername;
	}

	public String getAdvertiserwebsite() {
		return advertiserwebsite;
	}

	public void setAdvertiserwebsite(String advertiserwebsite) {
		this.advertiserwebsite = advertiserwebsite;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIndustrytext() {
		return industrytext;
	}

	public void setIndustrytext(String industrytext) {
		this.industrytext = industrytext;
	}

	public String getServicefeerate() {
		return servicefeerate;
	}

	public void setServicefeerate(String servicefeerate) {
		this.servicefeerate = servicefeerate;
	}

	public String getDeladname() {
		return deladname;
	}

	public void setDeladname(String deladname) {
		this.deladname = deladname;
	}
public AdvertiserInfo(){
	
}
	public AdvertiserInfo(String adname,String registername,String advertiserwebsite,String cellphone,String contactname,String email,String path,String industrytext,String servicefeerate,String orgcodeno){
		this.adname = adname;
		this.registername = registername;
		this.advertiserwebsite = advertiserwebsite;
		this.cellphone = cellphone;
		this.contactname = contactname;
		this.email = email;
		this.path = path;
		this.industrytext = industrytext;
		this.servicefeerate = servicefeerate;
		this.orgcodeno=orgcodeno;
	}

}
