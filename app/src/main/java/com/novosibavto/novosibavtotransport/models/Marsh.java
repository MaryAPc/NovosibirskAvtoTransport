package com.novosibavto.novosibavtotransport.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Marsh {

	@SerializedName("data")
	protected List<MarshData> data;

	private static Marsh sMarsh;

	public static Marsh getMarsh() {
		if (sMarsh == null) {
			sMarsh = new Marsh();
		}
		return sMarsh;
	}

	public void setData(List<MarshData> data) {
		this.data = data;
	}

	public List<MarshData> getData() {
		return data;
	}
}
