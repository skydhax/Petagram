package mx.skylabs.petagram;

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
    public void onBindViewHolder(MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);

        mascotaViewHolder.imgMascota.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombreCardView.setText(mascota.getNombre());
        mascotaViewHolder.tvRankingCardView.setText( String.valueOf(mascota.getRanking()) );

        mascotaViewHolder.btnRanking.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Toast.makeText(activity, mascota.getNombre(), Toast.LENGTH_SHORT).show();
                mascota.setRanking(5);

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
        private TextView tvRankingCardView;
        private ImageView imgRankingActual;

        public MascotaViewHolder(View itemView){
            super(itemView);

            btnRanking = (ImageButton) itemView.findViewById(R.id.btnRanking);
            imgMascota = (ImageView) itemView.findViewById(R.id.imgMascota);
            tvNombreCardView = (TextView) itemView.findViewById(R.id.tvNombreCardView);
            tvRankingCardView = (TextView) itemView.findViewById(R.id.tvRankingCardView);
            imgRankingActual = (ImageView) itemView.findViewById(R.id.imgRankingActual);
        }
    }


}
