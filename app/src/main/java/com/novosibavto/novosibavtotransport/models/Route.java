package com.novosibavto.novosibavtotransport.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Route {

	@SerializedName("data")
	private List<Data> data;

	public List<Data> getData() {
		return data;
	}

	public class Data {
		@SerializedName("id_route")
		private String id_route;
		@SerializedName("title")
		private String title;
		@SerializedName("direction")
		private int direction;
		@SerializedName("trassa")
		private List<Trassa> trassa;

		public String getId_route() {
			return id_route;
		}

		public String getTitle() {
			return title;
		}

		public int getDirection() {
			return direction;
		}

		public List<Trassa> getTrassa() {
			return trassa;
		}
	}

	public class Trassa {
		@SerializedName("id_stop")
		private String id_stop;
		@SerializedName("name_stop")
		private String name_stop;
		@SerializedName("len")
		private String len;
		@SerializedName("lat")
		private String lat;
		@SerializedName("lng")
		private String lng;

		public String getId_stop() {
			return id_stop;
		}

		public String getName_stop() {
			return name_stop;
		}

		public String getLen() {
			return len;
		}

		public String getLat() {
			return lat;
		}

		public String getLng() {
			return lng;
		}

	}
}
