package com.project.web.dto;

import com.project.domain.PassengerType;

public class VerifyRequestDTO {

	String type;
	String image;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public void setType(PassengerType type){
		this.type = type.toString();
	}
	
}
