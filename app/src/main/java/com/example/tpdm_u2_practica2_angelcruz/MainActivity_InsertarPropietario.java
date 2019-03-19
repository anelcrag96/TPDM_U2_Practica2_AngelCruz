package com.example.tpdm_u2_practica2_angelcruz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity_InsertarPropietario extends AppCompatActivity {
    EditText nombre, domicilio, telefono, fecha;
    Button insertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__insertar_propietario);

        nombre=findViewById(R.id.txtNombrePropietario);
        domicilio=findViewById(R.id.txtDomicilioPropietario);
        telefono=findViewById(R.id.txtTelefonoPropietario);
        fecha=findViewById(R.id.txtFechaPropietario);
        insertar=findViewById(R.id.btnInsetarPropietario
        );

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario propietario = new Propietario(MainActivity_InsertarPropietario.this);
                if (propietario.insertar(new Propietario(1,nombre.getText().toString(), domicilio.getText().toString(), telefono.getText().toString(), fecha.getText().toString()))) {
                    Toast.makeText(MainActivity_InsertarPropietario.this, "Propietario insertado exitosamente", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity_InsertarPropietario.this, MainActivity_PrincipalPropietario.class);
                    startActivity(i);
                    finish();
                }//if
                else {
                    Toast.makeText(MainActivity_InsertarPropietario.this, "No se pudo insertar", Toast.LENGTH_LONG).show();
                }//else
            }//onClick
        });
    }//onCreate
}//class
