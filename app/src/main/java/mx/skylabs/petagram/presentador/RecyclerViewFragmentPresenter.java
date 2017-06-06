package mx.skylabs.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.util.ArrayList;

import mx.skylabs.petagram.db.ConstructorMascotas;
import mx.skylabs.petagram.fragments.IRecyclerViewFragmentView;
import mx.skylabs.petagram.fragments.RecyclerViewFragment;
import mx.skylabs.petagram.pojo.Mascota;
import mx.skylabs.petagram.restAPI.EndpointsApi;
import mx.skylabs.petagram.restAPI.adapter.RestAPIAdapter;
import mx.skylabs.petagram.restAPI.model.MascotaResponse;
import mx.skylabs.petagram.restAPI.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by sky on 08/06/16.
 */
public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private Context context;
    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private ConstructorMascotas constructorMascotas;
    //private ArrayList<FotoMascota> timeline = new ArrayList<>();
    private ArrayList<Mascota> mascotas = new ArrayList<>();
    private ArrayList<String> followersId = new ArrayList<>();

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context){
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        //obtenerMascotasBaseDatos();
        //getRecentMedia();
        //getRecentMediaFromNiebla();
        getFollowersId();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.inicializarListaMascotas();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        //iRecyclerViewFragmentView.generarLinearLayoutVertical();
        iRecyclerViewFragmentView.generarGridLayout();
    }

    @Override
    public void getRecentMedia() {
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        // preparando el modelo para deserializar
        Gson gsonMediaRecent = restAPIAdapter.construyeGsonDeserializadorParaMediaRecent();
        // establecemos la conexión
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        // ejecutamos el request para obtener todas las fotos del usuario (todo el recent media)
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMedia();
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Error en la conexión al servidor, intente más tarde", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void getRecentMediaFromNiebla() {
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        Gson gsonMediaRecent = restAPIAdapter.construyeGsonDeserializadorParaMediaRecent();
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMediaFromNiebla();
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();

                mascotas = mascotaResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Error en la conexión al servidor, intente más tarde", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void getFollowersId() {
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        Gson gsonFollowersId = restAPIAdapter.construyendoGsonDeserializadorParaFollowers();
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionRestApiInstagram(gsonFollowersId);
        Call<MascotaResponse> followersResponseCall = endpointsApi.getFollowersId();
        followersResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                followersId = mascotaResponse.getFollowersId();
                getRecentMediaFrom("0000");
                /*
                for (int i = 0; i < followersId.size(); i++) {
                    String id = followersId.get(i);

                }*/

            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Error en la conexión al servidor, intente más tarde", Toast.LENGTH_LONG).show();
            }


        });
    }


    /*
    * @Override
    public void getTimeline(){
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Gson gsonFollow = restApiAdapter.gsonFollowsDeserialize();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonFollow);
        Call<MascotasResponse> mascotasResponseCall = endpointsApi.getFollowsUser();
        mascotasResponseCall.enqueue(new Callback<MascotasResponse>() {
            @Override
            public void onResponse(Call<MascotasResponse> call, Response<MascotasResponse> response) {

                MascotasResponse mascotasResponse = response.body();

                mascotas = mascotasResponse.getMascotas();

                if ((mascotas.size() == 0)) {
                    Toast.makeText(context, "No existe usuarios seguidos.", Toast.LENGTH_LONG).show();
                    Log.d("FOLLOW_USER", "Usuario no existe");

                } else if (mascotas.size() > 0) {
                    Log.d("SINCRO", "1");
                    getMediaTimeline();
                    Log.d("SINCRO", "2");
                } else {

                }
            }

            @Override
            public void onFailure(Call<MascotasResponse> call, Throwable t) {
                Toast.makeText(context, "¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());

            }
        });

    }


    public void getMediaTimeline() {
        Log.d("SINCRO", "1.1");
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonFollow = restApiAdapter.gsonMediaDeserialize();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonFollow);
        for (final Mascota mascota: mascotas) {

            Call<FotosLikeResponse> fotosLikeResponseCall = endpointsApi.getRecentMedia(mascota.getId());

            fotosLikeResponseCall.enqueue(new Callback<FotosLikeResponse>() {
                @Override
                public void onResponse(Call<FotosLikeResponse> call, Response<FotosLikeResponse> response) {

                    FotosLikeResponse fotosLikeResponse = response.body();
                    ArrayList<FotoLike> fotosLike = fotosLikeResponse.getFotosLike();
                    Log.d("SINCRO", "1.1.1");

                    for (FotoLike fotolike : fotosLike) {

                        timeline.add(new FotoMascota(fotolike.getId(),
                                                     mascota.getId(),
                                                     mascota.getNombre(),
                                                     fotolike.getFoto(),
                                                     fotolike.getLike(),
                                                     fotolike.getTime()
                        ));
                    }
                    ++countFollows;
                    if (countFollows == mascotas.size()) {

                        Collections.sort(timeline, new Comparator<FotoMascota>() {
                            @Override
                            public int compare(FotoMascota m1, FotoMascota m2) {
                                return new Long(m2.getTime()).compareTo(new Long(m1.getTime()));
                            }
                        });

                        mostrarTimeLineRV();

                    }
                }

                @Override
                public void onFailure(Call<FotosLikeResponse> call, Throwable t) {
                    Toast.makeText(context, "¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                    Log.e("FALLO LA CONEXION", t.toString());

                }
            });
        }
    }
    * */

    private void getRecentMediaFrom(String userId) {
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        Gson gsonMediaRecent = restAPIAdapter.construyeGsonDeserializadorParaMediaRecent();
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        for (final String followerId: followersId) {
            Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMediaFrom(followerId);
            mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
                @Override
                public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                    MascotaResponse mascotaResponse = response.body();
                    ArrayList<Mascota> m = mascotaResponse.getMascotas();
                    for (Mascota mas:m) {
                        Log.e("MASCOTA",mas.getFullName());
                        Log.e("MASCOTA",mas.getPhotoUrl());
                        mascotas.add(mas);
                    }
                    if (mascotas.size() > 0) {
                        mostrarMascotasRV();
                    }
                }

                @Override
                public void onFailure(Call<MascotaResponse> call, Throwable t) {
                    Toast.makeText(context, "Error en la conexión al servidor, intente más tarde", Toast.LENGTH_LONG).show();
                }
            });
        }



    }

    /*
    private void registrarToken() {
        String token = FirebaseInstanceId.getInstance().getToken();

        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionRestApiPetagram();
        Call<UsuarioResponse> usuarioResponseCall = endpointsApi.registrarTokenId(token);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("ID_FIREBASE",usuarioResponse.getId());
                Log.d("TOKEN_FIREBASE",usuarioResponse.getToken());

            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });

    }
        */

}
