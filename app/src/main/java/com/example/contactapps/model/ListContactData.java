package com.example.contactapps.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ListContactData implements Parcelable {

	@SerializedName("id")
	private String id;

	@SerializedName("firstName")
	private String firstName;

	@SerializedName("lastName")
	private String lastName;

	@SerializedName("age")
	private int age;

	@SerializedName("photo")
	private String photo;

	public ListContactData(String id, String firstName, String lastName, int age, String photo) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.photo = photo;
	}

	protected ListContactData(Parcel in) {
		firstName = in.readString();
		lastName = in.readString();
		photo = in.readString();
		id = in.readString();
		age = in.readInt();
	}

	public static final Creator<ListContactData> CREATOR = new Creator<ListContactData>() {
		@Override
		public ListContactData createFromParcel(Parcel in) {
			return new ListContactData(in);
		}

		@Override
		public ListContactData[] newArray(int size) {
			return new ListContactData[size];
		}
	};

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setPhoto(String photo){
		this.photo = photo;
	}

	public String getPhoto(){
		return photo;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setAge(int age){
		this.age = age;
	}

	public int getAge(){
		return age;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(firstName);
		dest.writeString(lastName);
		dest.writeString(photo);
		dest.writeString(id);
		dest.writeInt(age);
	}
}