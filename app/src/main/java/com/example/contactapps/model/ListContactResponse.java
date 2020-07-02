package com.example.contactapps.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListContactResponse implements Parcelable {

	@SerializedName("data")
	private List<ListContactData> data;

	@SerializedName("message")
	private String message;

	protected ListContactResponse(Parcel in) {
		data = in.createTypedArrayList(ListContactData.CREATOR);
		message = in.readString();
	}

	public static final Creator<ListContactResponse> CREATOR = new Creator<ListContactResponse>() {
		@Override
		public ListContactResponse createFromParcel(Parcel in) {
			return new ListContactResponse(in);
		}

		@Override
		public ListContactResponse[] newArray(int size) {
			return new ListContactResponse[size];
		}
	};

	public void setData(List<ListContactData> data){
		this.data = data;
	}

	public List<ListContactData> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeTypedList(data);
		dest.writeString(message);
	}
}