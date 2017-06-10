package mx.skylabs.petagram.restAPI;

import mx.skylabs.petagram.restAPI.model.LikeResponse;
import mx.skylabs.petagram.restAPI.model.MascotaResponse;
import mx.skylabs.petagram.restAPI.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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


    @GET(Constants.GET_SEARCH_USER)
    Call<MascotaResponse> getUser(@Query("q") String q, @Query("access_token") String access_token);

    /*
    @GET(Constants.GET_SEARCH_USER + "{userName}" + Constants.PLUS_ACCESS_TOKEN)
    Call<MascotaResponse> getUser(@Path("userName") String userName);
    */

    @FormUrlEncoded
    @POST(Constants.MEDIA + "{media_id}/likes")
    Call<LikeResponse> darLike(@Path("media_id") String media_id, @Field("access_token") String access_token);


    @FormUrlEncoded
    @POST(Constants.NOTIFICATIONS_POST_REGISTER)
    Call<UsuarioResponse> registrarUsuario(@Field("id_dispositivo") String idDispositivo, @Field("id_usuario_instagram") String idUsuarioInstagram);



    /*
id_foto_instagram
id_usuario_instagram
id_dispositivo
*/

    @FormUrlEncoded
    @POST(Constants.DAR_LIKE)
    Call<UsuarioResponse> darLikePetagram(@Field("idFotoInstagram") String idFotoInstagram,
                                          @Field("idUsuarioInstagram") String idUsuarioInstagram,
                                          @Field("idDispositivo") String idDispositivo);





}
