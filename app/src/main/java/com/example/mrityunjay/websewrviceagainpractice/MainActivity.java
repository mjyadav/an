package com.example.mrityunjay.websewrviceagainpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.mrityunjay.websewrviceagainpractice.Network.CallAddr;
import com.example.mrityunjay.websewrviceagainpractice.Network.NetworkStatus;
import com.example.mrityunjay.websewrviceagainpractice.Network.OnWebServiceResult;
import com.example.mrityunjay.websewrviceagainpractice.Utils.CommonUtilities;
import com.example.mrityunjay.websewrviceagainpractice.adapter.DataAdapter;
import com.example.mrityunjay.websewrviceagainpractice.model.DataHandler;

import com.squareup.okhttp.FormEncodingBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnWebServiceResult{

    String url=" http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";
    List<DataHandler> model= new ArrayList<>();
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= (RecyclerView)findViewById(R.id.recycler);
        hitRequest();
    }

    private void hitRequest(){
        FormEncodingBuilder parameters= new FormEncodingBuilder();
        parameters.add("page" , "1");
        if(NetworkStatus.getInstance(this).isConnectedToInternet()) {
            CallAddr call = new CallAddr(this, url,parameters,CommonUtilities.SERVICE_TYPE.GET_DATA, this);
            call.execute();
        }else {
            Toast.makeText(this, "No Network!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type) {
        Log.e("res", "type= "+ type+ " res= "+ result);
        try {
            JSONObject obj= new JSONObject(result);
            JSONArray arr= obj.getJSONArray("cast");
            for(int i=0; i<arr.length(); i++) {
                JSONObject jobj= arr.getJSONObject(i);
                DataHandler handler = new DataHandler();
                handler.setId(jobj.getInt("id"));
                handler.setTemp(jobj.getInt("temp"));
                handler.setName(jobj.getString("name"));
                handler.setMain(jobj.getString("main"));
                model.add(handler);
            }
            DataAdapter adapter= new DataAdapter(this, model);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }catch (Exception e){
            e.printStackTrace();
        }
    }}
