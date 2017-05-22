package mx.skylabs.petagram.presentador;

/**
 * Created by sky on 08/06/16.
 */
public interface IRecyclerViewFragmentPresenter {

    public void obtenerMascotasBaseDatos();
    void getRecentMedia();
    void getRecentMediaFromNiebla();
    void getFollowersId();
    public void mostrarMascotasRV();
}
