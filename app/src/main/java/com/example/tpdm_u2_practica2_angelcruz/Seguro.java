package com.example.tpdm_u2_practica2_angelcruz;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Seguro {
    private BaseDatos base;
    private int idSeguro;
    private String descripcionSeguro;
    private String fechaSeguro;
    private String tipoSeguro;
    private String telefonoSeguro;
    protected String error;

    public Seguro(Activity activity){
        base = new BaseDatos(activity, "ASEGURADORA",null,1);
    }

    public Seguro(int idS, String descripcionS, String fechaS, String tipoS, String telefonoS){
        this.idSeguro = idS;
        this.descripcionSeguro = descripcionS;
        this.fechaSeguro = fechaS;
        this.tipoSeguro = tipoS;
        this.telefonoSeguro = telefonoS;
    }

    public boolean insertar(Seguro seguro) {
        try {
            SQLiteDatabase transaccionInsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION", seguro.getDescripcionSeguro());
            datos.put("FECHA", seguro.getFechaSeguro());
            datos.put("TIPO", seguro.getTipoSeguro());
            datos.put("TELEFONO",seguro.getTelefonoSeguro());

            long resultado = transaccionInsertar.insert("SEGURO", "IDSEGURO", datos);
            transaccionInsertar.close();
            if (resultado < 0) {
                return false; //no se pudo hacer el insert
            }//if
        }//try
        catch (SQLiteException e) {
            error = e.getMessage();
            return false;
        }//catch
        return true;
    }//insertar

    public boolean eliminar(Seguro seguro) {
        try {
            SQLiteDatabase transaccionEliminar = base.getWritableDatabase();
            long resultado = transaccionEliminar.delete("SEGURO", "IDSEGURO=?", new String[]{"" + seguro.getIdSeguro()});
            transaccionEliminar.close();

            if (resultado == 0) {
                return false;//no se elimino nada
            }//if
        }//catch
        catch (SQLiteException e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean actualizar(Seguro seguro){
        try{
            SQLiteDatabase transaccionActualizar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION", seguro.getDescripcionSeguro());
            datos.put("FECHA", seguro.getFechaSeguro());
            datos.put("TIPO", seguro.getTipoSeguro());
            datos.put("TELEFONO",seguro.getTelefonoSeguro());
            String dato[]={seguro.getIdSeguro()+""};
            long resultado = transaccionActualizar.update("SEGURO",datos,"IDSEGURO=?",dato);
            transaccionActualizar.close();

            if(resultado<0){
                return false; //no se actualizo nada
            }//if
        }//try
        catch(SQLiteException e){
            e.printStackTrace();
            return false;
        }//catch
        return true;
    }//actualizar

    public Seguro[] consultar(){
        try{
            SQLiteDatabase db=base.getReadableDatabase();
            Cursor c=db.rawQuery("SELECT * FROM SEGURO",null);
            if (c.moveToFirst()) {
                Seguro[] seguros = new Seguro[c.getCount()];
                int posicion = 0;
                do {
                    seguros[posicion] = new Seguro(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                    posicion++;
                }//do
                while (c.moveToNext());
                return seguros;
            }//if
        }//try
        catch(SQLiteException e){
            e.printStackTrace();
            return null;
        }//catch
        return null;
    }//consultar

    public int getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(int id) {
        this.idSeguro = id;
    }

    public String getDescripcionSeguro() {
        return descripcionSeguro;
    }

    public void setDescripcionSeguro(String descripcion) {
        this.descripcionSeguro = descripcion;
    }

    public String getFechaSeguro() {
        return fechaSeguro;
    }

    public void setFechaSeguro(String fecha) {
        this.fechaSeguro = fecha;
    }

    public String getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(String tipo) {
        this.tipoSeguro = tipo;
    }

    public String getTelefonoSeguro() {
        return telefonoSeguro;
    }

    public void setTelefonoSeguro(String telefono) {
        this.telefonoSeguro = telefono;
    }
}//class
