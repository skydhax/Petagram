package mx.skylabs.petagram.restAPI;

import mx.skylabs.petagram.restAPI.model.MascotaResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sky on 13/05/17.
 */

public interface EndpointsApi {

    //getRecentMedia();

    @GET(Constants.GET_RECENT_MEDIA)
    Call<MascotaResponse> getRecentMedia();


}
