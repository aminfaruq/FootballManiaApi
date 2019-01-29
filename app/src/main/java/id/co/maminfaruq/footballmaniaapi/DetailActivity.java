package id.co.maminfaruq.footballmaniaapi;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.maminfaruq.footballmaniaapi.model.Constans;
import id.co.maminfaruq.footballmaniaapi.model.DataResponse;
import id.co.maminfaruq.footballmaniaapi.model.TeamsResponse;
import id.co.maminfaruq.footballmaniaapi.network.ApiClient;
import id.co.maminfaruq.footballmaniaapi.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {


    @BindView(R.id.imgStadium)
    ImageView imgStadium;
    @BindView(R.id.myToolbar)
    Toolbar myToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tvTglTerbit)
    TextView tvTglTerbit;
    @BindView(R.id.imgClub)
    ImageView imgClub;
    @BindView(R.id.tvContentBerita)
    TextView tvContentBerita;

    private Bundle bundle;
    private List<DataResponse>dataResponseList;
    private ApiInterface apiInterface;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        bundle = getIntent().getExtras();

       if (bundle != null){
            id = Integer.valueOf(bundle.getString(Constans.id));
        }

        dataResponseList = new ArrayList<>();

        getData();
    }

    private void getData() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TeamsResponse>call = apiInterface.getDetailResponse(id);

        call.enqueue(new Callback<TeamsResponse>() {
            @Override
            public void onResponse(Call<TeamsResponse> call, Response<TeamsResponse> response) {
                TeamsResponse teamsResponse = response.body();
                dataResponseList = teamsResponse.getData();
                Log.e("debug sukses",response.message());

                Glide.with(DetailActivity.this).load(dataResponseList.get(0).getStrStadiumThumb()).into(imgStadium);
                tvContentBerita.setText(dataResponseList.get(0).getStrDescriptionEN());
                Glide.with(DetailActivity.this).load(dataResponseList.get(0).getStrTeamBadge()).into(imgClub);
                tvTglTerbit.setText(dataResponseList.get(0).getIntFormedYear());

                setTitle(dataResponseList.get(0).getStrTeam());

            }

            @Override
            public void onFailure(Call<TeamsResponse> call, Throwable t) {
                Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("debug Gagal",t.getMessage());

            }
        });


    }
}
