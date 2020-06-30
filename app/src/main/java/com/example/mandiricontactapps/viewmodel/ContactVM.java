package com.example.mandiricontactapps.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mandiricontactapps.model.ContactData;
import com.example.mandiricontactapps.model.ContactResponse;
import com.example.mandiricontactapps.model.ListContactResponse;
import com.example.mandiricontactapps.network.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactVM extends ViewModel {
    private API api;
    private final MutableLiveData<ListContactResponse> listContactResponse = new MutableLiveData<>();
    private final MutableLiveData<ContactResponse> contactResponse = new MutableLiveData<>();
    private final MutableLiveData<ContactResponse> updateResponse = new MutableLiveData<>();
    private final MutableLiveData<String> insertResponse = new MutableLiveData<>();
    private final MutableLiveData<String> deleteResponse = new MutableLiveData<>();
    public LiveData<ListContactResponse> getListContact() { return listContactResponse; }
    public LiveData<ContactResponse> getContactDetail() { return contactResponse; }
    public LiveData<String> getInsertResponse() { return insertResponse; }
    public LiveData<ContactResponse> getUpdateResponse() { return updateResponse; }
    public LiveData<String> getDeleteResponse() { return deleteResponse; }

    public ContactVM() {
        api = new API();
    }

    public void fetchContactList() {
        Call<ListContactResponse> listContactCall = api.getInstance().getContactList();
        listContactCall.enqueue(new Callback<ListContactResponse>() {
            @Override
            public void onResponse(Call<ListContactResponse> call, Response<ListContactResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    listContactResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ListContactResponse> call, Throwable t) {

            }
        });
    }

    public void fetchContactDetail(String id) {
        Call<ContactResponse> contactResponseCall = api.getInstance().getContactDetail(id);
        contactResponseCall.enqueue(new Callback<ContactResponse>() {
            @Override
            public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    contactResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ContactResponse> call, Throwable t) {

            }
        });
    }

    public void insertContact(ContactData contactData) {
        Call<String> insertContactCall = api.getInstance().insertContact(
                contactData.getFirstName(),
                contactData.getLastName(),
                contactData.getAge(),
                contactData.getPhoto());
        insertContactCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful() && response.body() != null) {
                    insertResponse.setValue("SUCCESS");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void updateContact(ContactData contactData) {
        Call<ContactResponse> updateContactCall = api.getInstance().updateContact(
                contactData.getId(),
                contactData.getFirstName(),
                contactData.getLastName(),
                contactData.getAge(),
                contactData.getPhoto());
        updateContactCall.enqueue(new Callback<ContactResponse>() {
            @Override
            public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    updateResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ContactResponse> call, Throwable t) {

            }
        });
    }

    public void deleteContact(String id) {
        Call<String> deleteContactCall = api.getInstance().deleteContact(id);
        Log.i("wedew", id);
        deleteContactCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
               if(response.isSuccessful() && response.body() != null) {
                    deleteResponse.setValue("SUCCESS");
               } else {
                   deleteResponse.setValue("FAILED");
               }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                deleteResponse.setValue("FAILED");
            }
        });
    }
}
