package id.co.maminfaruq.footballmaniaapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.maminfaruq.footballmaniaapi.DetailActivity;
import id.co.maminfaruq.footballmaniaapi.R;
import id.co.maminfaruq.footballmaniaapi.model.Constans;
import id.co.maminfaruq.footballmaniaapi.model.DataResponse;
import id.co.maminfaruq.footballmaniaapi.model.TeamsResponse;

public class AdapterApi extends RecyclerView.Adapter<AdapterApi.ViewHolder> {

    private Context context;
    private final List<DataResponse> dataResponseList;
    private Bundle bundle;


    public AdapterApi(Context context, List<DataResponse> dataResponseList) {
        this.context = context;
        this.dataResponseList = dataResponseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final DataResponse dataResponse = dataResponseList.get(i);

        viewHolder.clubName.setText(dataResponse.getStrTeam());

        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image_black_24dp);


        Glide.with(context).load(dataResponse.getStrTeamBadge()).apply(options).into(viewHolder.imgTeam);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putString(Constans.id,dataResponse.getIdTeam());
                context.startActivity(new Intent(context, DetailActivity.class).putExtras(bundle));
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataResponseList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgTeam)
        ImageView imgTeam;
        @BindView(R.id.club_name)
        TextView clubName;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
