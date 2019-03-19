package com.example.tpdm_u2_practica2_angelcruz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity_PrincipalPropietario extends AppCompatActivity {
    ListView lista_p;
    Propietario[] lista_propietarios;
    Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__principal_propietario);

        lista_p=findViewById(R.id.ListPropietario);
        regresar=findViewById(R.id.btnRegresarPropietario);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity_PrincipalPropietario.this,MainActivity_Principal.class);
                startActivity(i);
                finish();
            }
        });

        lista_p.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(MainActivity_PrincipalPropietario.this);
                alerta.setTitle("Alerta")
                        .setMessage("¿Desea modificar/eliminar el seguro seleccionado?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent datos=new Intent(MainActivity_PrincipalPropietario.this,MainActivity_ConsultarPropietario.class);
                                datos.putExtra("id",lista_propietarios[position].getIdPropietario());
                                datos.putExtra("nombre",lista_propietarios[position].getNombre());
                                datos.putExtra("dom",lista_propietarios[position].getDomicilio());
                                datos.putExtra("tel",lista_propietarios[position].getTelefono());
                                datos.putExtra("fecha",lista_propietarios[position].getFecha());
                                startActivity(datos);
                                finish();
                            }
                        })
                        .setNegativeButton("NO",null).show();
            }
        });
    }

    protected void onStart() {
        super.onStart();//se ejecuta cuando se ve la pantalla
        Propietario propietario = new Propietario(this);
        Propietario vector[] = propietario.consultar();
        lista_propietarios=vector;
        String[] telefono = null;

        if(vector==null){
            telefono = new String[1];
            telefono[0]= "No hay propietarios capturados";
            lista_p.setEnabled(false);
        }else {
            telefono= new String[vector.length];
            for (int i=0; i<vector.length; i++){
                Propietario temp = vector[i];
                telefono[i] = temp.getTelefono();
            }
            lista_p.setEnabled(true);
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,telefono);
        lista_p.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.insertar:
                Intent insertar = new Intent(this,MainActivity_InsertarPropietario.class);
                startActivity(insertar);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
