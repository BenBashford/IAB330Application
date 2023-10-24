package com.example.navigationprototype;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationprototype.DB.Service;
import com.example.navigationprototype.R;

import java.util.ArrayList;
public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder> {

    private ArrayList<Service> ServiceList;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ServicesAdapter(ArrayList<Service> ServiceList) {
        this.ServiceList = ServiceList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service, parent, false);
        return new ServiceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service service = ServiceList.get(position);
        holder.ServiceName.setText(service.getName());
        holder.ServiceDescription.setText(service.getDescription());
        // Bind other vehicle data as needed
    }

    @Override
    public int getItemCount() {
        return ServiceList.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView ServiceName;
        TextView ServiceDescription;
        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            ServiceName = itemView.findViewById(R.id.ServiceName);
            ServiceDescription = itemView.findViewById(R.id.ServiceDescription);

            itemView.setOnClickListener(v -> {
                if (mListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(position);
                    }
                }
            });

        }
    }
}