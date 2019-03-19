package com.example.tpdm_u2_practica2_angelcruz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity_ConsultarPropietario extends AppCompatActivity {
    EditText idPropietario, nombre, domicilio, telefono, fechaPropietario, descripcion, fechaSeguro, tipo;
    Button actualizar, eliminar, regresar;
    Propietario propietario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__consultar_propietario);

        nombre=findViewById(R.id.txtNombre);
        domicilio=findViewById(R.id.txtDomicilio);
        telefono=findViewById(R.id.txtTelefonoPropietario);
        fechaPropietario=findViewById(R.id.txtFechaPropietario);
        descripcion=findViewById(R.id.txtDescripcion);
        fechaSeguro=findViewById(R.id.txtFechaSeguro);
        tipo=findViewById(R.id.txtTipo);
        actualizar=findViewById(R.id.btnActualizarPropietario);
        eliminar=findViewById(R.id.btnEliminarPropietario);
        regresar=findViewById(R.id.btnRegresarPropietario);

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario p=new Propietario(MainActivity_ConsultarPropietario.this);
                if (p.eliminar(propietario)){
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

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Propietario propietario=new Propietario(MainActivity_ConsultarPropietario.this);
                if (propietario.actualizar(new Propietario(1,telefono.getText().toString(),nombre.getText().toString(),domicilio.getText().toString(),fechaSeguro.getText().toString()))){
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
            propietario=new Propietario(1,extras.getString("nombre"),extras.getString("domicilio"),extras.getString("telefono"),extras.getString("fecha"));
            nombre.setText(propietario.getNombre());
            domicilio.setText(propietario.getDomicilio());
            fechaSeguro.setText(propietario.getFecha());
            telefono.setText(propietario.getTelefono());
        }
    }

}
