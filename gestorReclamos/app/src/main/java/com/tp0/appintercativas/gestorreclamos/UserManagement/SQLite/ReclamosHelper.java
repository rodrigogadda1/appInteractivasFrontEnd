package com.tp0.appintercativas.gestorreclamos.UserManagement.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ReclamosHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ReclamosSQLite.db";

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

    public long saveReclamo(Reclamo_SQLLite reclamo){
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(ReclamoContract.ReclamosEntry.TABLE_NAME, null, reclamo.toContentValues());
    }

    public int deleteRowsOfAdministrado (int idAdministrado){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(ReclamoContract.ReclamosEntry.TABLE_NAME,ReclamoContract.ReclamosEntry.ID_ADMINISTRADO+"=?",new String[] {String.valueOf(idAdministrado)});
    }

    public int deleteRowById (long nro_reclamo){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(ReclamoContract.ReclamosEntry.TABLE_NAME,ReclamoContract.ReclamosEntry.ID+"=?",new String[] {String.valueOf(nro_reclamo)});
    }

    public List<Reclamo_SQLLite> getReclamosSQLite(){
        List<Reclamo_SQLLite> reclamoss = new ArrayList<Reclamo_SQLLite>();
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
                aux.setId_reclamo(cursor.getInt(cursor.getColumnIndex("id")));

                if (cursor.getString(cursor.getColumnIndex("nombre")) != ""){
                    aux.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                }

                if (cursor.getString(cursor.getColumnIndex("username")) != ""){
                    aux.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                }

                aux.setId_edificio(cursor.getInt(cursor.getColumnIndex("id_edificio")));
                aux.setId_especialidad(cursor.getInt(cursor.getColumnIndex("id_especialidad")));
                aux.setId_estado(cursor.getInt(cursor.getColumnIndex("id_estado")));

                if (cursor.getInt(cursor.getColumnIndex("id_agrupador")) != 0) {
                    aux.setId_agrupador(cursor.getInt(cursor.getColumnIndex("id_agrupador")));
                }

                if (cursor.getString(cursor.getColumnIndex("descripcion")) != ""){
                    aux.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                }

                aux.setId_administrado(cursor.getInt(cursor.getColumnIndex("id_administrado")));

                if (cursor.getInt(cursor.getColumnIndex("id_unidad")) != 0) {
                    aux.setId_unidad(cursor.getInt(cursor.getColumnIndex("id_unidad")));
                }

                if (cursor.getInt(cursor.getColumnIndex("id_espacio_comun")) != 0){
                    aux.setId_espacioComun(cursor.getInt(cursor.getColumnIndex("id_espacio_comun")));
                }

                List<String> fotos = new ArrayList<String>();
                if (cursor.getString(cursor.getColumnIndex("foto1")) != null){
                    fotos.add(cursor.getString(cursor.getColumnIndex("foto1")));
                }
                if (cursor.getString(cursor.getColumnIndex("foto2")) != null){
                    fotos.add(cursor.getString(cursor.getColumnIndex("foto2")));
                }
                if (cursor.getString(cursor.getColumnIndex("foto3")) != null){
                    fotos.add(cursor.getString(cursor.getColumnIndex("foto3")));
                }
                if (cursor.getString(cursor.getColumnIndex("foto4")) != null){
                    fotos.add(cursor.getString(cursor.getColumnIndex("foto4")));
                }
                if (cursor.getString(cursor.getColumnIndex("foto5")) != null){
                    fotos.add(cursor.getString(cursor.getColumnIndex("foto5")));
                }
                if (cursor.getString(cursor.getColumnIndex("foto6")) != null){
                    fotos.add(cursor.getString(cursor.getColumnIndex("foto6")));
                }
                if (cursor.getString(cursor.getColumnIndex("foto7")) != null){
                    fotos.add(cursor.getString(cursor.getColumnIndex("foto7")));
                }
                if (fotos.size() > 0){
                    aux.setFotos(fotos);
                }

                reclamoss.add(aux);
            } while (cursor.moveToNext());
        }
        return reclamoss;
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
            aux.setId_reclamo(cursor.getInt(cursor.getColumnIndex("id")));

            if (cursor.getString(cursor.getColumnIndex("nombre")) != null){
                aux.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
            }

            if (cursor.getString(cursor.getColumnIndex("username")) != null){
                aux.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            }

            aux.setId_edificio(cursor.getInt(cursor.getColumnIndex("id_edificio")));
            aux.setId_especialidad(cursor.getInt(cursor.getColumnIndex("id_especialidad")));
            aux.setId_estado(cursor.getInt(cursor.getColumnIndex("id_estado")));

            if (cursor.getInt(cursor.getColumnIndex("id_agrupador")) != 0) {
                aux.setId_agrupador(cursor.getInt(cursor.getColumnIndex("id_agrupador")));
            }

            if (cursor.getString(cursor.getColumnIndex("descripcion")) != null){
                aux.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
            }

            aux.setId_administrado(cursor.getInt(cursor.getColumnIndex("id_administrado")));

            if (cursor.getInt(cursor.getColumnIndex("id_unidad")) != 0) {
                aux.setId_unidad(cursor.getInt(cursor.getColumnIndex("id_unidad")));
            }

            if (cursor.getInt(cursor.getColumnIndex("id_espacio_comun")) != 0){
                aux.setId_espacioComun(cursor.getInt(cursor.getColumnIndex("id_espacio_comun")));
            }

            List<String> fotos = new ArrayList<String>();
            if (cursor.getString(cursor.getColumnIndex("foto1")) != null){
                fotos.add(cursor.getString(cursor.getColumnIndex("foto1")));
            }
            if (cursor.getString(cursor.getColumnIndex("foto2")) != null){
                fotos.add(cursor.getString(cursor.getColumnIndex("foto2")));
            }
            if (cursor.getString(cursor.getColumnIndex("foto3")) != null){
                fotos.add(cursor.getString(cursor.getColumnIndex("foto3")));
            }
            if (cursor.getString(cursor.getColumnIndex("foto4")) != null){
                fotos.add(cursor.getString(cursor.getColumnIndex("foto4")));
            }
            if (cursor.getString(cursor.getColumnIndex("foto5")) != null){
                fotos.add(cursor.getString(cursor.getColumnIndex("foto5")));
            }
            if (cursor.getString(cursor.getColumnIndex("foto6")) != null){
                fotos.add(cursor.getString(cursor.getColumnIndex("foto6")));
            }
            if (cursor.getString(cursor.getColumnIndex("foto7")) != null){
                fotos.add(cursor.getString(cursor.getColumnIndex("foto7")));
            }
            if (fotos.size() > 0){
                aux.setFotos(fotos);
            }
        }
        return aux;
    }


    public List<Reclamo_SQLLite> getReclamosSQLiteByAdminitradoId(long administrado_id){
        List<Reclamo_SQLLite> reclamoss = new ArrayList<Reclamo_SQLLite>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(ReclamoContract.ReclamosEntry.TABLE_NAME,
                null,
                ReclamoContract.ReclamosEntry.ID_ADMINISTRADO + " = ? ",
                new String[] {String.valueOf(administrado_id)},
                null,
                null,
                null,
                null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Reclamo_SQLLite aux = new Reclamo_SQLLite();
                aux.setId_reclamo(cursor.getInt(cursor.getColumnIndex("id")));

                if (cursor.getString(cursor.getColumnIndex("nombre")) != ""){
                    aux.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                }

                if (cursor.getString(cursor.getColumnIndex("username")) != ""){
                    aux.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                }

                aux.setId_edificio(cursor.getInt(cursor.getColumnIndex("id_edificio")));
                aux.setId_especialidad(cursor.getInt(cursor.getColumnIndex("id_especialidad")));
                aux.setId_estado(cursor.getInt(cursor.getColumnIndex("id_estado")));

                if (cursor.getInt(cursor.getColumnIndex("id_agrupador")) != 0) {
                    aux.setId_agrupador(cursor.getInt(cursor.getColumnIndex("id_agrupador")));
                }

                if (cursor.getString(cursor.getColumnIndex("descripcion")) != ""){
                    aux.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                }

                aux.setId_administrado(cursor.getInt(cursor.getColumnIndex("id_administrado")));

                if (cursor.getInt(cursor.getColumnIndex("id_unidad")) != 0) {
                    aux.setId_unidad(cursor.getInt(cursor.getColumnIndex("id_unidad")));
                }

                if (cursor.getInt(cursor.getColumnIndex("id_espacio_comun")) != 0){
                    aux.setId_espacioComun(cursor.getInt(cursor.getColumnIndex("id_espacio_comun")));
                }

                List<String> fotos = new ArrayList<String>();
                if (cursor.getString(cursor.getColumnIndex("foto1")) != null){
                    fotos.add(cursor.getString(cursor.getColumnIndex("foto1")));
                }
                if (cursor.getString(cursor.getColumnIndex("foto2")) != null){
                    fotos.add(cursor.getString(cursor.getColumnIndex("foto2")));
                }
                if (cursor.getString(cursor.getColumnIndex("foto3")) != null){
                    fotos.add(cursor.getString(cursor.getColumnIndex("foto3")));
                }
                if (cursor.getString(cursor.getColumnIndex("foto4")) != null){
                    fotos.add(cursor.getString(cursor.getColumnIndex("foto4")));
                }
                if (cursor.getString(cursor.getColumnIndex("foto5")) != null){
                    fotos.add(cursor.getString(cursor.getColumnIndex("foto5")));
                }
                if (cursor.getString(cursor.getColumnIndex("foto6")) != null){
                    fotos.add(cursor.getString(cursor.getColumnIndex("foto6")));
                }
                if (cursor.getString(cursor.getColumnIndex("foto7")) != null){
                    fotos.add(cursor.getString(cursor.getColumnIndex("foto7")));
                }
                if (fotos.size() > 0){
                    aux.setFotos(fotos);
                }

                reclamoss.add(aux);
            } while (cursor.moveToNext());
        }
        return reclamoss;
    }

}

