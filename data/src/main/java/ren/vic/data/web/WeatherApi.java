package ren.vic.data.web;

import java.util.Map;

import io.reactivex.Observable;
import ren.vic.data.entity.WeatherResponse;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WeatherApi {

    @GET("now")
    Observable<WeatherResponse> getCurrentWeather(@QueryMap Map<String, String> queryMap);
}
