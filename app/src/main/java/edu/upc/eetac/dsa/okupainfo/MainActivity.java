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

                startActivity(new Intent(MainActivity.this, CasalsListActivity.class));
            }
        });

        Button mEventButton = (Button) findViewById(R.id.Events);
        mCasalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, EventsListActivity.class));
            }
        });

        Button mUsersButton = (Button) findViewById(R.id.Users);
        mCasalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, UsersListActivity.class));
            }
        });
    }


}
