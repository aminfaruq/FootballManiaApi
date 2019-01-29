package id.co.maminfaruq.footballmaniaapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.maminfaruq.footballmaniaapi.adapter.AdapterApi;
import id.co.maminfaruq.footballmaniaapi.model.Constans;
import id.co.maminfaruq.footballmaniaapi.model.DataResponse;
import id.co.maminfaruq.footballmaniaapi.model.TeamsResponse;
import id.co.maminfaruq.footballmaniaapi.network.ApiClient;
import id.co.maminfaruq.footballmaniaapi.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvBola)
    RecyclerView rvBola;

    private List<DataResponse>dataResponseList;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        dataResponseList = new ArrayList<>();

        getData();
    }

    private void getData() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TeamsResponse>call = apiInterface.getDataResponse(Constans.s,Constans.c);

        call.enqueue(new Callback<TeamsResponse>() {
            @Override
            public void onResponse(Call<TeamsResponse> call, Response<TeamsResponse> response) {
                TeamsResponse teamsResponse = response.body();
                dataResponseList = teamsResponse.getData();

                rvBola.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                rvBola.setAdapter(new AdapterApi(MainActivity.this,dataResponseList));


            }

            @Override
            public void onFailure(Call<TeamsResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
