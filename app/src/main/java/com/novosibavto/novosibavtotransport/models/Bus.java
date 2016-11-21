package com.novosibavto.novosibavtotransport.models;

import java.util.List;

public class Bus {

	protected List<MarshData> data;

	private static Bus sBus;

	public static Bus getBus() {
		if (sBus == null) {
			sBus = new Bus();
		}
		return sBus;
	}

	public void setData(List<MarshData> data) {
		this.data = data;
	}

	public List<MarshData> getData() {
		return data;
	}
}
