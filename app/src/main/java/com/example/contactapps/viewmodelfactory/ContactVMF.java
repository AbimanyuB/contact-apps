package com.example.contactapps.viewmodelfactory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.contactapps.viewmodel.ContactVM;

public class ContactVMF implements ViewModelProvider.Factory {
    private final Context context;

    public ContactVMF(Context context) { this.context = context; }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ContactVM();
    }
}
