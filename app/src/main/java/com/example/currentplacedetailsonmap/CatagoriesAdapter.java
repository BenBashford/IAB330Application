package com.example.currentplacedetailsonmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currentplacedetailsonmap.DB.Catagory;

import java.util.ArrayList;
public class CatagoriesAdapter extends RecyclerView.Adapter<CatagoriesAdapter.CatagoryViewHolder> {

    private ArrayList<Catagory> CatagoryList;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public CatagoriesAdapter(ArrayList<Catagory> CatagoryList) {
        this.CatagoryList = CatagoryList;
    }

    @NonNull
    @Override
    public CatagoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_catagory, parent, false);
        return new CatagoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CatagoryViewHolder holder, int position) {
        Catagory catagory = CatagoryList.get(position);
        holder.CatagoryName.setText(catagory.getName());
        // Bind other vehicle data as needed
    }

    @Override
    public int getItemCount() {
        return CatagoryList.size();
    }

    public class CatagoryViewHolder extends RecyclerView.ViewHolder {
        TextView CatagoryName;
        public CatagoryViewHolder(@NonNull View itemView) {
            super(itemView);
            CatagoryName = itemView.findViewById(R.id.CatagoryName);

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