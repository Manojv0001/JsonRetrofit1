package com.example.manoj_pc.jsonretrofit1;

import android.app.ProgressDialog;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.manoj_pc.jsonretrofit1.adapter.DataAdapter;
import com.example.manoj_pc.jsonretrofit1.model.Customer;
import com.example.manoj_pc.jsonretrofit1.model.DemoResponse;
import com.example.manoj_pc.jsonretrofit1.model.JsonArrayItem;
import com.example.manoj_pc.jsonretrofit1.network.ApiClientMain;
import com.example.manoj_pc.jsonretrofit1.utils.Constants;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DataAdapter mAdapter;

    private List<Customer> mAndroidArrayList = new ArrayList<>();;
    private ProgressDialog progress;
    List<Customer> customerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = new ProgressDialog(MainActivity.this);
        initView();
        initData();
    }

    private void initData() {
        callWebservice();
        bindDataToRecyclerView();
    }

    private void bindDataToRecyclerView() {

    }
    private void callWebservice() {
//        Log.d(MainActivity.class.getSimpleName(),"CheckJsonFormat:"+strJson);
        progress.setIndeterminate(true);
        progress.setMessage("Please wait...");
        progress.show();
        Call<DemoResponse> call= ApiClientMain.getApiClient().register();
        call.enqueue(new retrofit2.Callback<DemoResponse>() {
            @Override
            public void onResponse(Call<DemoResponse> call, Response<DemoResponse> response) {
                if (progress.isShowing())
                    progress.dismiss();
                DemoResponse resp = response.body();
                if (resp != null) {
                    List<JsonArrayItem> jsonArrayItemList = resp.getJsonArray();

                    for(int i=0;i<jsonArrayItemList.size();i++){
                        String jsonResponse = new Gson().toJson(jsonArrayItemList.get(i));
                        Log.d(MainActivity.class.getSimpleName(),"Checkresponse:"+jsonResponse);
                        try {
                            JSONObject jsonObject = new JSONObject(jsonResponse);
//                        JSONArray jsonArray = jsonObject.getJSONArray("jsonArray");
//                        for(int i=0;i<jsonArray.length();i++){
//                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String customerage = jsonObject.optString("CustomerAge");
                            String customerbirth = jsonObject.optString("CustomerBirth");
                            String customercity = jsonObject.optString("CustomerCity");
                            String customerid = jsonObject.getString("CustomerId");
                            String customername = jsonObject.getString("CustomerName");



                            JSONObject jsonObject2 = jsonObject.getJSONObject("EmpEducation");
                            String graduation = jsonObject2.optString("graduation");
                            String interschool = jsonObject2.optString("interschool");
                            String undergraduate = jsonObject2 .optString("undergradution");
                            String postgraduate = jsonObject2 .optString("postgraduation");
                            JSONObject jsonObject3 = jsonObject.getJSONObject("WorkInfo");
                            String empdepartment = jsonObject3.optString("empdepartment");
                            String empid = jsonObject3.optString("empid");
                            String empsalary = jsonObject3.optString("empsalary");
                            String empstatus = jsonObject3.optString("empstatus");
                            String emphobbie = jsonObject3.optString("emphobbie");
                            Customer customer = new Customer();
                            if(customerid!=null){
                                customer.setCustomerid(customerid);
                            }else{
                                customer.setCustomerid(Constants.EMPTY);
                            }

                            if(customername!=null){
                                customer.setCustomername(customername);
                            }else{
                                customer.setCustomername(Constants.EMPTY);
                            }

                            if(customerbirth!=null){
                                customer.setCustomerbirth(customerbirth);
                            }else{
                                customer.setCustomerbirth(Constants.EMPTY);

                            }

                            if(customerage!=null){
                                customer.setCustomerage(customerage);
                            }else{
                                customer.setCustomerage(Constants.EMPTY);
                            }

                            if(customercity!=null){
                                customer.setCustomercity(customercity);
                            }else{
                                customer.setCustomercity(Constants.EMPTY);
                            }

                            if(graduation!=null){
                                customer.setGraduation(graduation);
                            }else{
                                customer.setGraduation(Constants.EMPTY);
                            }
                            if(interschool!=null){
                                customer.setInterschool(interschool);
                            }else{
                                customer.setInterschool(Constants.EMPTY);
                            }

                            if(undergraduate!=null){
                                customer.setUndergradution(undergraduate);
                            }else{
                                customer.setUndergradution(Constants.EMPTY);
                            }

                            if(postgraduate!=null){
                                customer.setPostgraduation(postgraduate);
                            }else {
                                customer.setPostgraduation(Constants.EMPTY);
                            }

                            if(empdepartment!=null){
                                customer.setEmpdepartment(empdepartment);
                            }else {
                                customer.setEmpdepartment(Constants.EMPTY);
                            }

                            if(empid!=null){
                                customer.setEmpid(empid);
                            }else {
                                customer.setEmpid(Constants.EMPTY);
                            }

                            if(empsalary!=null){
                                customer.setEmpsalary(empsalary);
                            }else {
                                customer.setEmpsalary(Constants.EMPTY);
                            }

                            if(empstatus!=null){
                                customer.setEmpstatus(empstatus);
                            }else {
                                customer.setEmpstatus(Constants.EMPTY);
                            }

                            if(emphobbie!=null){
                                customer.setEmphobbie(emphobbie);
                            }else {
                                customer.setEmphobbie(Constants.EMPTY);
                            }
                            customerList.add(customer);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mAdapter = new DataAdapter(customerList);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<DemoResponse> call, Throwable t) {
                if (progress.isShowing())
                    progress.dismiss();
                Toast.makeText(MainActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

