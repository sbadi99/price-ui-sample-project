package com.goodrx.android.api;

import com.goodrx.android.model.PriceResponse;
import com.goodrx.android.util.Coordinates;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * This class provides the the end-point to fetch the drugs price list
 */
public interface RetrofitApiClient {
  @GET("/mobile-api/v3/price/amoxicillin?form=capsule&dosage=500mg&quantity=30&drug_object=1")
  Single<PriceResponse> getPrice(@Query("set_location") Integer zip,
      @Query("set_coords") Coordinates latLong);
}
