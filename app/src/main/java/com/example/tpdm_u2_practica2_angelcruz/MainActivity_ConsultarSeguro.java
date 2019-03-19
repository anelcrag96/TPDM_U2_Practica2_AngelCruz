package com.example.tpdm_u2_practica2_angelcruz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity_ConsultarSeguro extends AppCompatActivity {
    EditText descripcion, fecha, tipo, telefono;
    Button actualizar, eliminar, regresar;
    Seguro seguro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__consultar_seguro);

        descripcion=findViewById(R.id.txtDescripcionSeguro);
        fecha=findViewById(R.id.txtFechaSeguro);
        tipo=findViewById(R.id.txtTipoSeguro);
        telefono=findViewById(R.id.txtTelefonoSeguro);
        actualizar=findViewById(R.id.btnActualizarSeguro);
        eliminar=findViewById(R.id.btnEliminarSeguro);
        regresar=findViewById(R.id.btnRegresarSeguros);

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Seguro seg=new Seguro (MainActivity_ConsultarSeguro.this);
                if (seg.modificar(new Seguro(seguro.getIdseguro(),descripcion.getText().toString(),fecha.getText().toString(),tipo.getText().toString(),telefono.getText().toString()))){
                    Toast.makeText(MainActivity_ConsultarSeguro.this,"Seguro actualizado exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(MainActivity_ConsultarSeguro.this,MainActivity_PrincipalSeguro.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(MainActivity_ConsultarSeguro.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Seguro s=new Seguro(MainActivity_ConsultarSeguro.this);
                if (s.eliminar(seguro)){
                    Toast.makeText(MainActivity_ConsultarSeguro.this,"Eliminado exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(MainActivity_ConsultarSeguro.this,MainActivity_PrincipalSeguro.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(MainActivity_ConsultarSeguro.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity_ConsultarSeguro.this,MainActivity_PrincipalSeguro.class);
                startActivity(i);
                finish();
            }
        });
    }
}
