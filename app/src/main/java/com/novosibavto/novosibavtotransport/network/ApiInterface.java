package com.novosibavto.novosibavtotransport.network;

import com.novosibavto.novosibavtotransport.models.Marsh;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface {

	@GET(NskgortransUrls.Route.BASE)
	Observable<Marsh> getAllMarsh(@Query("v") String apiVersion, @Query("key") String key, @Query("format") String format);
}
