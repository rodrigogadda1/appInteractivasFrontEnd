package com.tp0.appintercativas.gestorreclamos.UserManagement.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ReclamosHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Reclamos.db";

    public ReclamosHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crea la tabla de clubes si no existe
        final String CREATE_RECLAMOS = "CREATE TABLE " + ReclamoContract.ReclamosEntry.TABLE_NAME + "("
            + ReclamoContract.ReclamosEntry._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + ReclamoContract.ReclamosEntry.ID + " TEXT NOT NULL,  "
            + ReclamoContract.ReclamosEntry.NOMBRE + " TEXT, "
            + ReclamoContract.ReclamosEntry.USERNAME + " TEXT, "
            + ReclamoContract.ReclamosEntry.ID_EDIFICIO + " TEXT, "
            + ReclamoContract.ReclamosEntry.ID_ESPECIALIDAD + " TEXT, "
            + ReclamoContract.ReclamosEntry.ID_ESTADO + " TEXT, "
            + ReclamoContract.ReclamosEntry.ID_AGRUPADOR + " TEXT, "
            + ReclamoContract.ReclamosEntry.DESCRIPCION + " TEXT, "
            + ReclamoContract.ReclamosEntry.ID_ADMINISTRADO + " TEXT, "
            + ReclamoContract.ReclamosEntry.ID_UNIDAD + " TEXT, "
            + ReclamoContract.ReclamosEntry.ID_ESPACIO_COMUN + " TEXT, "
            + ReclamoContract.ReclamosEntry.FOTO_1 + " TEXT, "
            + ReclamoContract.ReclamosEntry.FOTO_2 + " TEXT, "
            + ReclamoContract.ReclamosEntry.FOTO_3 + " TEXT, "
            + ReclamoContract.ReclamosEntry.FOTO_4 + " TEXT, "
            + ReclamoContract.ReclamosEntry.FOTO_5 + " TEXT, "
            + ReclamoContract.ReclamosEntry.FOTO_6 + " TEXT, "
            + ReclamoContract.ReclamosEntry.FOTO_7 + " TEXT )";
        db.execSQL(CREATE_RECLAMOS);
        //Crea las otras tablas de jugadores
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long saveClub(Reclamo_SQLLite reclamo){
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(ReclamoContract.ReclamosEntry.TABLE_NAME, null, reclamo.toContentValues());
    }

    public List<Reclamo_SQLLite> getReclamosSQLite(){
        List<Reclamo_SQLLite> clubes = new ArrayList<Reclamo_SQLLite>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(ReclamoContract.ReclamosEntry.TABLE_NAME,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Reclamo_SQLLite aux = new Reclamo_SQLLite();
                aux.setIdClub(cursor.getInt(cursor.getColumnIndex("idClub")));
                aux.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                aux.setNroZona(cursor.getInt(cursor.getColumnIndex("nroZona")));
                clubes.add(aux);
            }while (cursor.moveToNext());
        }
        return clubes;
    }

    public Reclamo_SQLLite getClubById(int idReclamo){
        Reclamo_SQLLite aux = new Reclamo_SQLLite();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(ReclamoContract.ReclamosEntry.TABLE_NAME,
                        null,
                        ReclamoContract.ReclamosEntry.ID + " = ? ",
                        new String[] {String.valueOf(idReclamo)},
                        null,
                        null,
                        null,
                        null);
        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            aux.setIdClub(cursor.getInt(cursor.getColumnIndex("id_Club")));
            aux.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
            aux.setNroZona(cursor.getInt(cursor.getColumnIndex("nroZona")));
        }
        return aux;
    }
}

