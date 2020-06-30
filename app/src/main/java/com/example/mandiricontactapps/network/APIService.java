package com.example.mandiricontactapps.network;

import com.example.mandiricontactapps.model.ContactResponse;
import com.example.mandiricontactapps.model.ListContactResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("contact")
    Call<ListContactResponse> getContactList();

    @DELETE("contact/{id}")
    Call<String> deleteContact(
            @Path("id") String id
    );

    @GET("contact/{id}")
    Call<ContactResponse> getContactDetail(
            @Path("id") String id
    );

    @POST("contact")
    @FormUrlEncoded
    Call<String> insertContact(
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("age") int age,
            @Field("photo") String photo
    );

    @PUT("contact/{id}")
    @FormUrlEncoded
    Call<ContactResponse> updateContact(
            @Path("id") String id,
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("age") int age,
            @Field("photo") String photo
    );
}
