package com.example.contactapps.view;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.contactapps.viewmodel.ContactVM;
import com.example.mandiricontactapps.R;
import com.example.contactapps.model.ContactData;
import com.example.contactapps.model.ContactResponse;
import com.example.contactapps.model.ListContactData;
import com.example.contactapps.viewmodelfactory.ContactVMF;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailContactActivity extends AppCompatActivity {
    public final static String CONTACT_DATA = "contact_data";
    public final static String STATE_TYPE = "state_type";
    public final static String ADD_STATE = "add_state";
    public final static String DETAIL_STATE = "detail_state";

    private ListContactData dataList;
    private ContactData contactData;
    private String state;
    private ContactVM viewModel;

    @BindView(R.id.et_firstName)
    EditText etFirstName;
    @BindView(R.id.et_lastName)
    EditText etLastName;
    @BindView(R.id.et_age)
    EditText etAge;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.iv_userProfile)
    ImageView ivUserProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        dataList = intent.getParcelableExtra(CONTACT_DATA);
        state = intent.getStringExtra(STATE_TYPE);
        initVM();
        initContentViews();
        initLiveData();
    }

    public void initVM() {
        ContactVMF contactVMF = new ContactVMF(this);
        viewModel = ViewModelProviders.of(this, contactVMF).get(ContactVM.class);
    }

    public void initContentViews() {
        HeaderFragment headerFragment = (HeaderFragment) getSupportFragmentManager()
                .findFragmentById(R.id.f_header);
        if(headerFragment != null) {
            headerFragment.hideRightIcon();
        }
        if (state != null) {
            if (DETAIL_STATE.equalsIgnoreCase(state)) {
                setDetailStateContent();
            } else {
                setAddStateContent();
            }
        } else {
            setAddStateContent();
        }
    }

    public void setDetailStateContent() {
        if (dataList != null) {
            if (dataList.getId() != null) {
                if (!dataList.getId().isEmpty()) {
                    viewModel.fetchContactDetail(dataList.getId());
                }
            }
        }
    }

    public void setAddStateContent() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMarginStart(convertedMarginDPtoPX());
        layoutParams.setMarginEnd(convertedMarginDPtoPX());
        btnDelete.setVisibility(View.GONE);
        btnSave.setText(getString(R.string.button_add));
        btnSave.setLayoutParams(layoutParams);
    }

    public void initLiveData() {
        viewModel.getContactDetail().observe(this, new Observer<ContactResponse>() {
            @Override
            public void onChanged(ContactResponse contactResponse) {
                if (contactResponse != null) {
                    setContentData(contactResponse.getData());
                }
            }
        });

        viewModel.getInsertResponse().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    if ("SUCCESS".equalsIgnoreCase(s)) {
                        Toast.makeText(
                                DetailContactActivity.this,
                                "Data has been Added",
                                Toast.LENGTH_SHORT
                        ).show();
                        Intent intent = new Intent(DetailContactActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(
                                DetailContactActivity.this,
                                "Something went wrong",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                } else {
                    Toast.makeText(
                            DetailContactActivity.this,
                            "Something went wrong",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        viewModel.getUpdateResponse().observe(this, new Observer<ContactResponse>() {
            @Override
            public void onChanged(ContactResponse contactResponse) {
                if (contactResponse != null) {
                    Toast.makeText(
                            DetailContactActivity.this,
                            "Data has been Saved",
                            Toast.LENGTH_SHORT
                    ).show();
                    Intent intent = new Intent(DetailContactActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(
                            DetailContactActivity.this,
                            "Something went wrong",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        viewModel.getDeleteResponse().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    if ("SUCCESS".equalsIgnoreCase(s)) {
                        Toast.makeText(
                                DetailContactActivity.this,
                                "Data has been Deleted",
                                Toast.LENGTH_SHORT
                        ).show();
                        Intent intent = new Intent(DetailContactActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(
                                DetailContactActivity.this,
                                "Something went wrong",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                } else {
                    Toast.makeText(
                            DetailContactActivity.this,
                            "Something went wrong",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }

    public void setContentData(ContactData contactData) {
        if (contactData != null) {
            if (contactData.getFirstName() != null) {
                etFirstName.setText(contactData.getFirstName());
            }
            if (contactData.getLastName() != null) {
                etLastName.setText(contactData.getLastName());
            }
            etAge.setText(String.valueOf(contactData.getAge()));
            if (contactData.getPhoto() != null) {
                if (!contactData.getPhoto().isEmpty() && !"n/a".equalsIgnoreCase(contactData.getPhoto())) {
                    String imageUrl = contactData.getPhoto().replace("http", "https");
                    Glide.with(this)
                            .load(imageUrl)
                            .error(R.drawable.ic_user)
                            .circleCrop()
                            .into(ivUserProfile);
                }
            }
        }
    }

    public void getContentData() {
        contactData = new ContactData();
        contactData.setFirstName(etFirstName.getText().toString());
        contactData.setLastName(etLastName.getText().toString());
        contactData.setAge(Integer.parseInt(etAge.getText().toString()));
        if (dataList != null) {
            contactData.setPhoto(dataList.getPhoto());
            if (dataList.getId() != null) {
                contactData.setId(dataList.getId());
            }
        } else {
            contactData.setPhoto("N/A");
        }
    }

    public void saveButtonOnClick() {
        getContentData();
        if (state != null) {
            if (DETAIL_STATE.equalsIgnoreCase(state)) {
                viewModel.updateContact(contactData);
            } else {
                viewModel.insertContact(contactData);
            }
        }
    }

    private int convertedMarginDPtoPX() {
        Resources r = getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                getResources().getDimension(R.dimen._10sdp),
                r.getDisplayMetrics()
        );
    }

    @OnClick({R.id.btn_save, R.id.btn_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                saveButtonOnClick();
                break;
            case R.id.btn_delete:
                viewModel.deleteContact(dataList.getId());
                break;
        }
    }
}
