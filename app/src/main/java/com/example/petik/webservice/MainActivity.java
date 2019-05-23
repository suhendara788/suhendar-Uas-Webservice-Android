package com.example.petik.webservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText txtinput;
    private RequestQueue mQueue;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue = Volley.newRequestQueue(this);
        txtinput = findViewById(R.id.et_1);
        Button tombolJSOn = findViewById(R.id.btnJSON);
        tv1 = (TextView) findViewById(R.id.hasil_1);
        tv2 = (TextView) findViewById(R.id.hasil_2);
        tv3 = (TextView) findViewById(R.id.hasil_3);
        tv4 = (TextView) findViewById(R.id.hasil_4);
        tv5 = (TextView) findViewById(R.id.hasil_5);
        tv6 = (TextView) findViewById(R.id.hasil_6);

        tombolJSOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a;
                a = txtinput.getText().toString();

                uraiJSON(a);
            }

            private void uraiJSON(final String a) {
                String url ="http://papaside.com/data.php";

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {


                            JSONObject cuaca = response.getJSONObject(Integer.parseInt(a));

                            String kota = cuaca.getString("kota");
                            String siang = cuaca.getString("siang");
                            String malam = cuaca.getString("malam");
                            String dini_hari = cuaca.getString("dini_hari");
                            String suhu = cuaca.getString("suhu");
                            String kelembapan = cuaca.getString("kelembapan");

                            tv1.setText(kota);
                            tv2.setText(siang);
                            tv3.setText(malam);
                            tv4.setText(dini_hari);
                            tv5.setText(suhu);
                            tv6.setText(kelembapan);






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                    }

                });
                mQueue.add(request);

            }
        });

    }
}
