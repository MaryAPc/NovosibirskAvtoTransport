package com.novosibavto.novosibavtotransport.models;

import java.util.List;

public class Trolley {
	protected List<MarshData> data;

	private static Trolley sTrolley;

	public static Trolley getTrolley() {
		if (sTrolley == null) {
			sTrolley = new Trolley();
		}
		return sTrolley;
	}

	public void setData(List<MarshData> data) {
		this.data = data;
	}

	public List<MarshData> getData() {
		return data;
	}
}
