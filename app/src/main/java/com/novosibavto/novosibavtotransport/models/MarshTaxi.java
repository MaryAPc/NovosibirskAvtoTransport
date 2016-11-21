package com.novosibavto.novosibavtotransport.models;

import java.util.List;

public class MarshTaxi {
	protected List<MarshData> data;

	private static MarshTaxi sMarshTaxi;

	public static MarshTaxi getMarshTaxi() {
		if (sMarshTaxi == null) {
			sMarshTaxi = new MarshTaxi();
		}
		return sMarshTaxi;
	}

	public void setData(List<MarshData> data) {
		this.data = data;
	}

	public List<MarshData> getData() {
		return data;
	}
}
