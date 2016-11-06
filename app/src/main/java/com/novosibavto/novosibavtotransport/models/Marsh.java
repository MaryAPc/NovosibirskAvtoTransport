package com.novosibavto.novosibavtotransport.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Marsh {

	@SerializedName("data")
	private List<MarshData> data;

	public List<MarshData> getData() {
		return data;
	}
}
