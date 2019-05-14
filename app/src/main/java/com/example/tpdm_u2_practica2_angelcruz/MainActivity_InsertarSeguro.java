package com.example.tpdm_u2_practica2_angelcruz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity_InsertarSeguro extends AppCompatActivity {
    EditText descripcion, fecha, tipo;
    Button insertar;
    Spinner telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__insertar_seguro);

        descripcion=findViewById(R.id.txt_DescripcionSeguro);
        fecha=findViewById(R.id.txt_FechaSeguro);
        tipo=findViewById(R.id.txt_TipoSeguro);
        telefono=findViewById(R.id.cmb_TelefonoPropietarioSeguro);
        insertar=findViewById(R.id.btn_InsetarSeguro);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Seguro seguro=new Seguro(MainActivity_InsertarSeguro.this);
                if (seguro.insertar(new Seguro(1,descripcion.getText().toString(),fecha.getText().toString(),tipo.getText().toString(),telefono.getSelectedItem().toString().split("-")[0]))){
                    Toast.makeText(MainActivity_InsertarSeguro.this,"Seguro insertado exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(MainActivity_InsertarSeguro.this,MainActivity_PrincipalSeguro.class);
                    startActivity(i);
                    finish();
                }//if
            }//onClick
        });
    }//onCreate

    protected void onStart(){
        super.onStart();
        Propietario propietario=new Propietario(MainActivity_InsertarSeguro.this);
        Propietario propietarios[]=propietario.consultar();
        if (propietarios!=null){
            String d[]=new String[propietarios.length];
            for (int i=0;i<d.length;i++){
                d[i]=propietarios[i].getTelefonoPropietario()+" - "+propietarios[i].getNombrePropietario();
            }//for
            ArrayAdapter<String> adaptador=new ArrayAdapter<String>(MainActivity_InsertarSeguro.this,android.R.layout.simple_list_item_1,d);
            telefono.setAdapter(adaptador);
        }//if
        else{
            Toast.makeText(this, "No existen propietarios registrados, favor de registrarlos", Toast.LENGTH_LONG).show();
            Intent i=new Intent(MainActivity_InsertarSeguro.this,MainActivity_Principal.class);
            startActivity(i);
            finish();
        }//else
    }//onStart
}//class