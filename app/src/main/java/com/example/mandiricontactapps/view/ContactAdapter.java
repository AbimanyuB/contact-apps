package com.example.mandiricontactapps.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mandiricontactapps.R;
import com.example.mandiricontactapps.model.ListContactData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    private Context context;
    private List<ListContactData> dataList;

    public ContactAdapter(Context context, List<ListContactData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_contact_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(dataList != null) {
            String name = dataList.get(position).getFirstName() + " " +
                          dataList.get(position).getLastName();
            holder.tvName.setText(name);
            if(!dataList.get(position).getPhoto().isEmpty() && !"n/a".equalsIgnoreCase(dataList.get(position).getPhoto())) {
                String imageUrl = dataList.get(position).getPhoto().replace("http", "https");
                Glide.with(context)
                        .load(imageUrl)
                        .error(R.drawable.ic_user)
                        .circleCrop()
                        .into(holder.ivProfile);
            }
            holder.itemView.setOnClickListener((View v) -> {
                Intent intent = new Intent(context, DetailContactActivity.class);
                intent.putExtra(DetailContactActivity.CONTACT_DATA,
                        dataList.get(holder.getAdapterPosition()));
                intent.putExtra(DetailContactActivity.STATE_TYPE, DetailContactActivity.DETAIL_STATE);
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        if(dataList != null) {
            return dataList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_profile)
        ImageView ivProfile;
        @BindView(R.id.tv_name)
        TextView tvName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
