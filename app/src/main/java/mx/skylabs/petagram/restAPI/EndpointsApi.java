package mx.skylabs.petagram.restAPI;

import mx.skylabs.petagram.restAPI.model.MascotaResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sky on 13/05/17.
 */

public interface EndpointsApi {

    /*
    @GET("group/{id}/users")
    Call<List<User>> groupList(@Path("id") int groupId);
    */

    @GET(Constants.GET_RECENT_MEDIA)
    Call<MascotaResponse> getRecentMedia();

    @GET(Constants.GET_RECENT_MEDIA_FROM_NIEBLA)
    Call<MascotaResponse> getRecentMediaFromNiebla();

    @GET(Constants.GET_FOLLOWERS)
    Call<MascotaResponse> getFollowersId();

    @GET(Constants.USERS + "{userId}/" + Constants.GET_RECENT_MEDIA_FROM)
    Call<MascotaResponse> getRecentMediaFrom(@Path("userId") String userId);


}
