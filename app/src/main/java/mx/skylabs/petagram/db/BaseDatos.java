package mx.skylabs.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import mx.skylabs.petagram.pojo.Mascota;

/**
 * Created by sky on 09/06/16.
 */
public class BaseDatos extends SQLiteOpenHelper {


    //TODO - rawQuery se usa para ejecutar queries que devuelvan un valor
    //TODO - excecQuery se usa para queries que no devuelven valor

    private Context context;

    public BaseDatos(Context context) {

        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    // TODO 0 - idMascota
    // TODO 1 - nombre
    // TODO 2 - ranking
    // TODO 3 - foto

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE mascota( idMascota INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, ranking INTEGER, foto INTEGER);";

        /*
        String queryCrearTablaRankingMascota = "CREATE TABLE ranking( idRanking INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                                        "idMascota INTEGER, ranking INTEGER, FOREIGN KEY(idMascota) REFERENCES mascota (idMascota) )";
        */

        db.execSQL(queryCrearTablaMascota);
        //db.execSQL(queryCrearTablaRankingMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST mascota");
        //db.execSQL("DROP TABLE IF EXIST ranking");
    }


    public ArrayList<Mascota> obtenerTodasLasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM mascota";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        while( registros.moveToNext() ){
            Mascota mascotaAcual = new Mascota();

            //mascotaAcual.setIdMascota(registros.getInt(0));
            //mascotaAcual.setNombre(registros.getString(1));
            //mascotaAcual.setRanking(registros.getInt(2));
            //mascotaAcual.setFoto(registros.getInt(3));

            mascotas.add(mascotaAcual);
        }

        db.close();

        return mascotas;
    }



    public void insertarMascota(ContentValues contentValues){

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("mascota",null,contentValues);
        db.close();
    }

    /*
    public void insertarRankingMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("ranking", null, contentValues );
        db.close();

    }
    */

    public void ponerRankingMascota(ContentValues contentValues, String idMascota){
        SQLiteDatabase db = this.getWritableDatabase();

        db.update("mascota",contentValues,"idMascota="+idMascota,null);
    }

    public int obtenerRankingDeMascota(Mascota mascota){
        int ranking = 0;

        String query = "SELECT ranking FROM mascota WHERE idMascota="+ 1; //mascota.getIdMascota();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        // es un if en lugar de un while, ya que solo se recuperará un valor
        if ( registros.moveToNext() ){
            ranking = registros.getInt(0);
        }
        db.close();


        return ranking;
    }


    public ArrayList<Mascota> obtenerUltimasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        //String query = "SELECT * FROM mascota ORDER BY idMascota DESC LIMIT 5";
        String query = "select * from mascota WHERE ranking>=1 ORDER BY ranking DESC LIMIT 5;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        while( registros.moveToNext() ){
            Mascota mascotaAcual = new Mascota();

            //mascotaAcual.setIdMascota(registros.getInt(0));
            //mascotaAcual.setNombre(registros.getString(1));
            mascotaAcual.setRanking(registros.getInt(2));
            //mascotaAcual.setFoto(registros.getInt(3));





            /* anteriormente deshechado


            String queryRanking = "SELECT COUNT(ranking) as rank FROM ranking WHERE idMascota = " + mascotaAcual.getIdMascota();
            Cursor registrosRanking = db.rawQuery(queryRanking, null);
            if( registrosRanking.moveToNext() ){
                mascotaAcual.setRanking(registrosRanking.getInt(0));
            }else {
                mascotaAcual.setRanking(0);
            }
            */

            mascotas.add(mascotaAcual);
        }

        db.close();

        return mascotas;
    }
}
