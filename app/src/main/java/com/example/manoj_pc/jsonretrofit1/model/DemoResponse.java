package com.example.manoj_pc.jsonretrofit1.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DemoResponse {

	@SerializedName("jsonArray")
	private List<JsonArrayItem> jsonArray = new ArrayList<>();

	public void setJsonArray(List<JsonArrayItem> jsonArray){
		this.jsonArray = jsonArray;
	}

	public List<JsonArrayItem> getJsonArray(){
		return jsonArray;
	}

	@Override
 	public String toString(){
		return 
			"DemoResponse{" + 
			"jsonArray = '" + jsonArray + '\'' + 
			"}";
		}
}