package com.tp0.appintercativas.gestorreclamos.UserManagement.Auxiliares;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetodosDeVerificacion {

    public static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    public static boolean isMail(String cadena){
        final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }

    public static boolean isJustLetterAndSpaces(String cadena){
        return cadena.matches("[a-zA-Z\\s\'\"]+");
    }

}
