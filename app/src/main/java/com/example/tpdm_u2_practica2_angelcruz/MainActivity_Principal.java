package com.example.tpdm_u2_practica2_angelcruz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity_Principal extends AppCompatActivity {
    Button propietario, seguro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_principal);

        propietario=findViewById(R.id.btnPropietarios);
        seguro=findViewById(R.id.btnSeguros);

        propietario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity_Principal.this,MainActivity_PrincipalPropietario.class);
                startActivity(i);
                finish();
            }
        });

        seguro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity_Principal.this,MainActivity_PrincipalSeguro.class);
                startActivity(i);
                finish();
            }
        });
    }
}
