package com.example.mandiricontactapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mandiricontactapps.R;
import com.example.mandiricontactapps.model.ListContactData;
import com.example.mandiricontactapps.model.ListContactResponse;
import com.example.mandiricontactapps.viewmodel.ContactVM;
import com.example.mandiricontactapps.viewmodelfactory.ContactVMF;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private List<ListContactData> dataList;
    private ContactVM viewModel;

    @BindView(R.id.rv_contact)
    RecyclerView rvContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        initVM();
        initLiveData();
    }

    public void initViews() {
        HeaderFragment headerFragment = (HeaderFragment) getSupportFragmentManager()
                .findFragmentById(R.id.f_header);
        if(headerFragment != null) {
            if(headerFragment.getView() != null) {
                headerFragment.getView().findViewById(R.id.iv_headerRightIcon)
                        .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, DetailContactActivity.class);
                        intent.putExtra(DetailContactActivity.STATE_TYPE, DetailContactActivity.ADD_STATE);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    public void initVM() {
        ContactVMF viewModelFactory = new ContactVMF(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ContactVM.class);
        viewModel.fetchContactList();
    }

    public void initLiveData() {
        viewModel.getListContact().observe(this, new Observer<ListContactResponse>() {
            @Override
            public void onChanged(ListContactResponse listContactResponse) {
                dataList = listContactResponse.getData();
                setRecyclerView();
            }
        });
    }

    private void setRecyclerView() {
        ContactAdapter contactAdapter = new ContactAdapter(this, dataList);
        rvContact.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvContact.setAdapter(contactAdapter);
    }
}
