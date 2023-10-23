package com.example.currentplacedetailsonmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.net.URLEncoder;

public class LocationSearchActivity extends AppCompatActivity {
    private JSONArray candidates = new JSONArray();
    private  RecyclerView recyclerView;
    private LocationAdapter locationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_search);
        EditText etAddress = findViewById(R.id.et_address);
        Button btnOk = findViewById(R.id.btn_ok);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        locationAdapter = new LocationAdapter();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = etAddress.getText().toString();
                if (TextUtils.isEmpty(address)){
                    Toast.makeText(LocationSearchActivity.this,"please input address",Toast.LENGTH_SHORT).show();
                    return;
                }
                RequestParams requestParams = new RequestParams("https://maps.googleapis.com/maps/api/place/findplacefromtext/json" +
                        "?fields=formatted_address,name,geometry" +
                        "&inputtype=textquery" +
                        "&key=" + getString(R.string.geo_api_key) + "&input=" + URLEncoder.encode(address));
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("TAG2222",result);
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            candidates = jsonObject.optJSONArray("candidates");
                            recyclerView.setAdapter(locationAdapter);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e("TAG222",ex.getMessage());
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }
        });

    }

    public class LocationHolder extends RecyclerView.ViewHolder{

        private View itemView;
        private  TextView tvItem;
        public LocationHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvItem = itemView.findViewById(R.id.tv_item);
        }

        public void bindData(int position) {
            JSONObject jsonObject = candidates.optJSONObject(position);
            tvItem.setText(jsonObject.optString("formatted_address"));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("address",jsonObject.toString());
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }
    }

    public class LocationAdapter extends RecyclerView.Adapter<LocationHolder>{

        @NonNull
        @Override
        public LocationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new LocationHolder(View.inflate(parent.getContext(),R.layout.item_location,null));
        }

        @Override
        public void onBindViewHolder(@NonNull LocationHolder holder, int position) {
            holder.bindData(position);
        }

        @Override
        public int getItemCount() {
            return candidates==null?0:candidates.length();
        }
    }
}