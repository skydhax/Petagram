package mx.skylabs.petagram.db;


//TODO - TAMBIÉN LLAMDO INTERACTOR

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mx.skylabs.petagram.R;
import mx.skylabs.petagram.pojo.Mascota;

public class ConstructorMascotas {
    private Context context;
    //private Activity activity;

    public ConstructorMascotas(Context context){
        this.context = context;
        //this.activity = activity;
    }


    public ArrayList<Mascota> inicializarListaMascotas(){

        BaseDatos db = new BaseDatos(context);
        insertarMascotas(db);

        return db.obtenerTodasLasMascotas();
    }

    public void insertarMascotas(BaseDatos db){

        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre","Bengalito");
        contentValues.put("ranking",0);
        contentValues.put("foto",R.drawable.tiger1);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put("nombre","Bengalito DOS");
        contentValues.put("ranking",0);
        contentValues.put("foto",R.drawable.tiger1);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put("nombre","Bengalito TRES");
        contentValues.put("ranking",0);
        contentValues.put("foto",R.drawable.tiger1);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put("nombre","Bengalito CUATRO");
        contentValues.put("ranking",0);
        contentValues.put("foto",R.drawable.tiger1);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put("nombre","Bengalito CINCO");
        contentValues.put("ranking",0);
        contentValues.put("foto",R.drawable.tiger1);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put("nombre","Milky");
        contentValues.put("ranking",0);
        contentValues.put("foto",R.drawable.cat1);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put("nombre","Scar");
        contentValues.put("ranking",0);
        contentValues.put("foto",R.drawable.tiger2);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put("nombre","Johnny");
        contentValues.put("ranking",0);
        contentValues.put("foto",R.drawable.cat2);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put("nombre","Grumpy");
        contentValues.put("ranking",0);
        contentValues.put("foto",R.drawable.grumpycat);
        db.insertarMascota(contentValues);

    }


    /*
    public void darRankingMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);

        ContentValues contentValues = new ContentValues();
        contentValues.put("idMascota",mascota.getIdMascota());
        contentValues.put("ranking",1); //dando ranking de 1 a la mascota

        db.insertarRankingMascota(contentValues);

    }
    */

    public void ponerRankingMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);

        int nuevoRanking = obtenerRankingMascota(mascota);
        nuevoRanking++;

        ContentValues contentValues = new ContentValues();
        contentValues.put("ranking",nuevoRanking);

        db.ponerRankingMascota(contentValues,mascota.getId());
    }


    public int obtenerRankingMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return  db.obtenerRankingDeMascota(mascota);
    }


    public ArrayList<Mascota> obtenerUltimasMascotas(){
        BaseDatos db = new BaseDatos(context);
        return  db.obtenerUltimasMascotas();
    }




}
