package com.novosibavto.novosibavtotransport.models;

import java.util.List;

public class Tram {
	protected List<MarshData> data;

	private static Tram sTram;

	public static Tram getTram() {
		if (sTram == null) {
			sTram = new Tram();
		}
		return sTram;
	}

	public void setData(List<MarshData> data) {
		this.data = data;
	}

	public List<MarshData> getData() {
		return data;
	}
}
