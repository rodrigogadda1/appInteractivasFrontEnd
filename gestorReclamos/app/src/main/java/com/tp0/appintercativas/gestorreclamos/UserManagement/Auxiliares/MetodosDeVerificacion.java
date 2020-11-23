package com.tp0.appintercativas.gestorreclamos.UserManagement.Auxiliares;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetodosDeVerificacion {
    private static final float PREFERRED_WIDTH = 250;
    private static final float PREFERRED_HEIGHT = 250;

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

    public static String bitmapToString (Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b,Base64.DEFAULT);
    }

    public static Bitmap stringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static Bitmap resizeBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = PREFERRED_WIDTH / width;
        float scaleHeight = PREFERRED_HEIGHT / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bitmap, 0, 0, width, height, matrix, false);
        bitmap.recycle();
        return resizedBitmap;
    }

}
