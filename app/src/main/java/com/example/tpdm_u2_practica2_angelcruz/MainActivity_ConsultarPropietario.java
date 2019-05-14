package com.example.tpdm_u2_practica2_angelcruz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity_ConsultarPropietario extends AppCompatActivity {
    EditText telefonoPropietario, nombrePropietario, domicilioPropietario, fechaPropietario, descripcionSeguro, fechaSeguro, tipoSeguro;
    Button actualizar, eliminar, regresar;
    Propietario propietario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__consultar_propietario);

        telefonoPropietario=findViewById(R.id.txt__TelefonoPropietario);
        telefonoPropietario.setEnabled(false);
        nombrePropietario=findViewById(R.id.txt__NombrePropietario);
        domicilioPropietario=findViewById(R.id.txt__DomicilioPropietario);
        fechaPropietario=findViewById(R.id.txt__FechaPropietario);
        descripcionSeguro=findViewById(R.id.txt__DescripcionSeguro);
        fechaSeguro=findViewById(R.id.txt__FechaSeguro);
        tipoSeguro=findViewById(R.id.txt__TipoSeguro);
        actualizar=findViewById(R.id.btn__ActualizarPropietario);
        eliminar=findViewById(R.id.btn__EliminarPropietario);
        regresar=findViewById(R.id.btn__RegresarPropietario);

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario p=new Propietario(MainActivity_ConsultarPropietario.this);
                if (p.actualizar(new Propietario(telefonoPropietario.getText().toString(), nombrePropietario.getText().toString(), domicilioPropietario.getText().toString(), fechaPropietario.getText().toString()))){
                    Toast.makeText(MainActivity_ConsultarPropietario.this,"Propietario actualizado exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(MainActivity_ConsultarPropietario.this,MainActivity_PrincipalPropietario.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(MainActivity_ConsultarPropietario.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario prop=new Propietario(MainActivity_ConsultarPropietario.this);
                if (prop.eliminar(propietario)){
                    Toast.makeText(MainActivity_ConsultarPropietario.this,"Propietario eliminado exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(MainActivity_ConsultarPropietario.this,MainActivity_PrincipalPropietario.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(MainActivity_ConsultarPropietario.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity_ConsultarPropietario.this,MainActivity_PrincipalPropietario.class);
                startActivity(i);
                finish();
            }
        });
    }

    protected void onStart(){
        super.onStart();
        Bundle extras=getIntent().getExtras();
        if (extras != null) {
            propietario=new Propietario(extras.getString("telefono"),extras.getString("nombre"),extras.getString("domicilio"),extras.getString("fecha"));
            telefonoPropietario.setText(propietario.getTelefonoPropietario());
            nombrePropietario.setText(propietario.getNombrePropietario());
            domicilioPropietario.setText(propietario.getDomicilioPropietario());
            fechaPropietario.setText(propietario.getFechaPropietario());
        }//if
    }//onStart

}
