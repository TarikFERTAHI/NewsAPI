package ma.enset.NewsAPI.Service;

import ma.enset.NewsAPI.Model.Everything;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("Everything")
    Call<Everything> getEverything(@Query("q") String key,
                                   @Query("from") String date,
                                   @Query("apiKey") String apiKey
    );

}
