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

public class MainActivity_ConsultarSeguro extends AppCompatActivity {
    EditText descripcion, fecha, tipo;
    Button actualizar, eliminar, regresar;
    Spinner telefono;
    Seguro seguro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__consultar_seguro);

        descripcion=findViewById(R.id.txt__DescripcionSeguro);
        fecha=findViewById(R.id.txt__FechaSeguro);
        tipo=findViewById(R.id.txt__TipoSeguro);
        telefono=findViewById(R.id.cmb__TelefonoPropietarioSeguro);
        actualizar=findViewById(R.id.btn__ActualizarSeguro);
        eliminar=findViewById(R.id.btn__EliminarSeguro);
        regresar=findViewById(R.id.btn__RegresarSeguro);

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Seguro seg=new Seguro (MainActivity_ConsultarSeguro.this);
                if (seg.actualizar(new Seguro(seguro.getIdSeguro(),descripcion.getText().toString(),fecha.getText().toString(),tipo.getText().toString(),telefono.getSelectedItem().toString().split("-")[0]))){
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

    protected void onStart(){
        super.onStart();
        Bundle extras=getIntent().getExtras();
        String tel=extras.getString("telefono");
        seguro=new Seguro(extras.getInt("idseguro"),extras.getString("descripcion"),extras.getString("fecha"),extras.getString("tipo"),tel);
        descripcion.setText(seguro.getDescripcionSeguro());
        tipo.setText(seguro.getTipoSeguro());
        fecha.setText(seguro.getFechaSeguro());

        Propietario propietario=new Propietario(MainActivity_ConsultarSeguro.this);
        Propietario propietarios[]=propietario.consultar();
        if (propietarios!=null){
            String d[]=new String[propietarios.length];
            int posicion=0;
            for (int i=0;i<d.length;i++){
                String dato=propietarios[i].getTelefonoPropietario();
                if (dato.equals(tel)){posicion=i;}
                d[i]=dato+" - "+propietarios[i].getNombrePropietario();
            }//for
            ArrayAdapter<String> adaptador=new ArrayAdapter<String>(MainActivity_ConsultarSeguro.this,android.R.layout.simple_list_item_1,d);
            telefono.setAdapter(adaptador);
            telefono.setSelection(posicion);
        }
    }
}//class
