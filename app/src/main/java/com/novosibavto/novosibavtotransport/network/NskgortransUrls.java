package com.novosibavto.novosibavtotransport.network;

@SuppressWarnings("ALL")
public class NskgortransUrls {

	static final String BASE_URL = "https://api.nskgortrans.ru";

	public static class Route {
		static final String BASE = "/route/list/";
		static final String BASE_TRASSA = "/trassa/list/";
		static final String ALL_LIST = BASE + "all";
		static final String IDS = BASE_TRASSA + "ids/";
	}
}
