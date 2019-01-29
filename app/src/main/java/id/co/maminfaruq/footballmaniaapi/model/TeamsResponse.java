package id.co.maminfaruq.footballmaniaapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamsResponse {

    @SerializedName("teams")
    private List<DataResponse>data;

    public List<DataResponse> getData() {
        return data;
    }

    public void setData(List<DataResponse> data) {
        this.data = data;
    }
}
