package com.example.contactapps.model;

import com.google.gson.annotations.SerializedName;

public class ContactResponse{

	@SerializedName("data")
	private ContactData data;

	@SerializedName("message")
	private String message;

	public ContactData getData() {
		return data;
	}

	public void setData(ContactData data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}