package com.example.tpdm_u2_practica2_angelcruz;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class Propietario {
    private BaseDatos base;
    private int idPropietario;
    private String nombrePropietario;
    private String domicilioPropietario;
    private String telefonoPropietario;
    private String fechaPropietario;
    protected String error;

    public Propietario(Activity activity){base = new BaseDatos(activity, "ASEGURADORA_ANEL",null,1); }

    public Propietario(int idP, String nombreP, String domicilioP, String telefonoP, String fechaP){
        this.idPropietario=idP;
        this.nombrePropietario = nombreP;
        this.domicilioPropietario = domicilioP;
        this.telefonoPropietario = telefonoP;
        this.fechaPropietario = fechaP;
    }

    public boolean insertar(Propietario propietario) {
        try {
            SQLiteDatabase transaccionInsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("NOMBRE", propietario.getNombrePropietario());
            datos.put("DOMICILIO", propietario.getDomicilioPropietario());
            datos.put("TELEFONO", propietario.getTelefonoPropietario());
            datos.put("FECHA", propietario.getFechaPropietario());

            long resultado = transaccionInsertar.insert("PROPIETARIO", "IDPROPIETARIO", datos);
            transaccionInsertar.close();
            if (resultado == -1) {
                return false; //no se pudo hacer el insert
            }
        } catch (SQLiteException e) {
            error = e.getMessage();
            Log.e("Error SQL ",e.getMessage());
            return false;
        }
        return true;
    }

    public boolean eliminar(Propietario propietario) {
        try {
            SQLiteDatabase transaccionEliminar = base.getWritableDatabase();
            long resultado = transaccionEliminar.delete("PROPIETARIO", "NOMBRE=?", new String[]{"" + propietario.getNombrePropietario()});
            transaccionEliminar.close();

            if (resultado < 0) {
                return false;//no se elimino nada
            }
        } catch (SQLiteException e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean actualizar(Propietario propietario){
        try{
            SQLiteDatabase transaccionActualizar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            //datos.put("IDPROPIETARIO", propietario.getIdPropietario());
            datos.put("NOMBRE", propietario.getNombrePropietario());
            datos.put("DOMICILIO", propietario.getDomicilioPropietario());
            datos.put("TELEFONO", propietario.getTelefonoPropietario());
            datos.put("FECHA", propietario.getFechaPropietario());

            String dato[]={propietario.getIdPropietario()+""};
            long resultado = transaccionActualizar.update("PROPIETARIO",datos,"NOMBRE=?",dato);
            transaccionActualizar.close();

            if(resultado<0){
                return false; //no se actualizo nada
            }
        }catch(SQLiteException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Propietario[] consultar(){
        try{
            SQLiteDatabase db=base.getReadableDatabase();
            Cursor c=db.rawQuery("SELECT * FROM PROPIETARIO",null);
            if (c.moveToFirst()) {
                Propietario[] propietarios = new Propietario[c.getCount()];
                int posicion = 0;
                do {
                    propietarios[posicion] = new Propietario(c.getInt(0), c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                    posicion++;
                } while (c.moveToNext());
                return propietarios;
            }
        }catch(SQLiteException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Propietario consultar(String dato){
        try{
            SQLiteDatabase db=base.getReadableDatabase();
            Cursor c=db.rawQuery("SELECT * FROM PROPIETARIO WHERE NOMBRE=?",new String[]{dato.toString()});
            if (c.moveToFirst()) {
                Propietario propietario = new Propietario(c.getInt(0), c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                return propietario;
            }
        }catch(SQLiteException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int id) {
        this.idPropietario = id;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombre(String nombre) {
        this.nombrePropietario = nombre;
    }

    public String getDomicilioPropietario() {
        return domicilioPropietario;
    }

    public void setDomicilioPropietario(String domicilio) {
        this.domicilioPropietario = domicilio;
    }

    public String getTelefonoPropietario() {
        return telefonoPropietario;
    }

    public void setTelefonoPropietario(String telefono) {
        this.telefonoPropietario = telefono;
    }

    public String getFechaPropietario() {
        return fechaPropietario;
    }

    public void setFechaPropietario(String fecha) {
        this.fechaPropietario = fecha;
    }
}
