package edu.upc.eetac.dsa.okupainfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mCasalButton = (Button) findViewById(R.id.Casals);
        mCasalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, CasalsListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button mEventButton = (Button) findViewById(R.id.Events);
        mEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(MainActivity.this, EventsListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button CrearCasal = (Button) findViewById(R.id.CrearCasal);
        CrearCasal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, CasalCreateActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button CrearEvento = (Button) findViewById(R.id.CrearEvento);
        CrearEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, EventCreateActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
