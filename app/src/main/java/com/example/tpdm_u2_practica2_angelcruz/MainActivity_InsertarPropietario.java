package com.example.tpdm_u2_practica2_angelcruz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity_InsertarPropietario extends AppCompatActivity {
    EditText telefono, nombre, domicilio, fecha;
    Button insertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__insertar_propietario);

        telefono=findViewById(R.id.txt_TelefonoPropietario);
        nombre=findViewById(R.id.txt_NombrePropietario);
        domicilio=findViewById(R.id.txt_DomicilioPropietario);
        fecha=findViewById(R.id.txt_FechaPropietario);
        insertar=findViewById(R.id.btn_InsetarPropietario);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario propietario = new Propietario(MainActivity_InsertarPropietario.this);
                if (propietario.insertar(new Propietario(telefono.getText().toString(),nombre.getText().toString(), domicilio.getText().toString(), fecha.getText().toString()))){
                    Toast.makeText(MainActivity_InsertarPropietario.this, "Se inserto a "+nombre.getText().toString()+" exitosamente", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity_InsertarPropietario.this, MainActivity_PrincipalPropietario.class);
                    startActivity(i);
                    finish();
                }//if
                else {
                    Toast.makeText(MainActivity_InsertarPropietario.this, "No se pudo insertar a "+nombre.getText().toString(), Toast.LENGTH_LONG).show();
                }//else
            }//onClick
        });
    }//onCreate
}//class
