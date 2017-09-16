package com.example.admin.servicestesting;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etfirstnum,etsecondnum;
    TextView tvans;
    MyService mySv;
    ServiceConnection serviceconn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mySv = ((MyService.LocalBinder)service).getService();
            Log.v("Current in","onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v("Current in","onServiceConnected");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etfirstnum = (EditText)findViewById(R.id.etfirstnum);
        etsecondnum= (EditText)findViewById(R.id.etsecondnum);
        tvans = (TextView)findViewById(R.id.tvans);
        //Create intent and add extras to intent
        //set bindservice
        Intent intent = new Intent(this,MyService.class);
        this.bindService(intent,serviceconn, Context.BIND_AUTO_CREATE);
        //this.startService(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //this.unbindService(serviceconn);
    }
    public void showAnswer(View view){
        Intent intent = new Intent(this,MyService.class);
        intent.putExtra("firstNum",etfirstnum.getText().toString());
        intent.putExtra("secondNum",etsecondnum.getText().toString());
        tvans.setText(String.valueOf(mySv.SumOperator(intent)));
    }
    public void startServices(View view) {

        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("firstNum",etfirstnum.getText().toString());
        intent.putExtra("secondNum",etsecondnum.getText().toString());
        //intent.putExtra("key3","3");
        startService(intent);
    }

    // Method to stop the service
    public void stopService(View view) {
        stopService(new Intent(this, MyService.class));
    }
}
