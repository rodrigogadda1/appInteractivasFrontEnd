package com.tp0.appintercativas.gestorreclamos.UserManagement.Auxiliares;

import com.tp0.appintercativas.gestorreclamos.UserManagement.data.Estado;

public class GeneradorEstadosObjects {

    static public Estado getEstadoObject(String estadoName){
        Estado estado= new Estado();
        switch (estadoName) {
            case "Abierto":
                estado.setId_estado(1);
                estado.setDescripcion("Abierto");
                break;
            case "Cerrado":
                estado.setId_estado(2);
                estado.setDescripcion("Cerrado");
                break;
            case "Inspeccionando":
                estado.setId_estado(3);
                estado.setDescripcion("Inspeccionando");
                break;
            case "En Reparacion":
                estado.setId_estado(4);
                estado.setDescripcion("En Reparacion");
                break;
            case "Cancelado":
                estado.setId_estado(5);
                estado.setDescripcion("Cancelado");
                break;
        }
        return estado;
    }

}
