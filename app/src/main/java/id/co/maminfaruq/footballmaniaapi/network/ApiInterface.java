package id.co.maminfaruq.footballmaniaapi.network;

import id.co.maminfaruq.footballmaniaapi.model.DataResponse;
import id.co.maminfaruq.footballmaniaapi.model.TeamsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/api/v1/json/1/search_all_teams.php")
    Call<TeamsResponse>getDataResponse(
            @Query("s") String s,
            @Query("c") String c

    );

    @GET("/api/v1/json/1/lookupteam.php")
    Call<TeamsResponse>getDetailResponse(
            @Query("id") int id
    );

}
