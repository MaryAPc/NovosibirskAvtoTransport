package com.novosibavto.novosibavtotransport.network;

import com.novosibavto.novosibavtotransport.models.Marsh;
import com.novosibavto.novosibavtotransport.models.Route;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface {

	@GET(NskgortransUrls.Route.ALL_LIST)
	Observable<Marsh> getAllMarsh(@Query("v") String apiVersion, @Query("key") String key, @Query("format") String format);

	@GET(NskgortransUrls.Route.IDS + "{id}")
	Observable<Route> getTrassa(@Path("id") String id, @Query("v") String apiVersion, @Query("key") String key, @Query("format") String format);
}
