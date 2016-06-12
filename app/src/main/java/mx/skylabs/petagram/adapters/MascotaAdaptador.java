package mx.skylabs.petagram.adapters;

import android.app.Activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import mx.skylabs.petagram.db.ConstructorMascotas;
import mx.skylabs.petagram.pojo.Mascota;
import mx.skylabs.petagram.R;


public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{

    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);

        mascotaViewHolder.imgMascota.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombreCardView.setText(mascota.getNombre());
        mascotaViewHolder.tvRanking.setText( String.valueOf(mascota.getRanking()) );
        //mascotaViewHolder.tvRankingCardView.setText( String.valueOf(mascota.getRanking()) );

        mascotaViewHolder.btnRanking.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Toast.makeText(activity, mascota.getNombre(), Toast.LENGTH_SHORT).show();
                //mascota.setRanking(5);

                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity); // la activity representa nuestro contexto
                constructorMascotas.ponerRankingMascota(mascota);

                mascotaViewHolder.tvRanking.setText(String.valueOf(constructorMascotas.obtenerRankingMascota(mascota)));


            }
        });

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }


    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgMascota;
        private ImageButton btnRanking;
        private TextView tvNombreCardView;
        private ImageView imgRankingActual;
        private TextView tvRanking;
        //private TextView tvRankingCardView;

        public MascotaViewHolder(View itemView){
            super(itemView);

            btnRanking = (ImageButton) itemView.findViewById(R.id.btnRanking);
            imgMascota = (ImageView) itemView.findViewById(R.id.imgMascota);
            tvNombreCardView = (TextView) itemView.findViewById(R.id.tvNombreCardView);
            //tvRankingCardView = (TextView) itemView.findViewById(R.id.tvRankingCardView);
            imgRankingActual = (ImageView) itemView.findViewById(R.id.imgRankingActual);
            tvRanking = (TextView) itemView.findViewById(R.id.tvRanking);
        }
    }


}
