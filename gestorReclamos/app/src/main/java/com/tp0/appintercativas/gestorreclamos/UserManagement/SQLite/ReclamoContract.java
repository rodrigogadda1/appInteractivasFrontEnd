package com.tp0.appintercativas.gestorreclamos.UserManagement.SQLite;

import android.provider.BaseColumns;

import java.sql.Blob;
import java.util.List;

public class ReclamoContract {

    public static Object ClubesEntry;

    /**
     * Clase interna para la definición de la tabla.
     * */

    public static abstract class ReclamosEntry implements BaseColumns{
        //BaseColumns se agrega para agregar una columna _ID extra (recomendado)
        public static final String TABLE_NAME = "reclamos";
        //Definición de los campos de la tabla
        public static final String ID = "id";
        public static final String NOMBRE = "nombre";
        public static final String USERNAME = "username";
        public static final String ID_EDIFICIO = "id_edificio";
        public static final String ID_ESPECIALIDAD = "id_especialidad";
        public static final String ID_ESTADO = "id_estado";
        public static final String ID_AGRUPADOR = "id_agrupador";
        public static final String DESCRIPCION = "descripcion";
        public static final String ID_ADMINISTRADO = "id_administrado";
        public static final String ID_UNIDAD = "id_unidad";
        public static final String ID_ESPACIO_COMUN = "id_espacio_comun";
        //aca se va a guardar la ubicacion de las fotos
        public static final String FOTO_1 = "foto1";
        public static final String FOTO_2 = "foto2";
        public static final String FOTO_3 = "foto3";
        public static final String FOTO_4 = "foto4";
        public static final String FOTO_5 = "foto5";
        public static final String FOTO_6 = "foto6";
        public static final String FOTO_7 = "foto7";
    }
}
