package com.example.sergi.soaptemp;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sergi.soaptemp.WS.TempConvertSoap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_REQUEST_URL = "http://www.w3schools.com/webservices/tempconvert.asmx";
    private static final String NAMESPACE = "http://www.w3schools.com/webservices/";
    private static final String SOAP_ACTION = "http://www.w3schools.com/webservices/FahrenheitToCelsius";
    private static final String METHOD = "FahrenheitToCelsius";

    TempConvertSoap service;

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edt = (EditText)findViewById(R.id.value_to_convert);
        Button btn = (Button)findViewById(R.id.convert);
        Button btnEasyWSDL = (Button)findViewById(R.id.convertEasyWSDL);
        txt = (TextView)findViewById(R.id.answer);

        service = new TempConvertSoap();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt.length() > 0) {
                    new getCelsius().execute(edt.getText().toString());
                } else {
                    txt.setText("Fahrenheit value can not be empty.");
                }
            }
        });

        btnEasyWSDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt.length() > 0) {
                    new getCelsiusUsingEasyWSDL().execute(edt.getText().toString());
                } else {
                    txt.setText("Fahrenheit value can not be empty.");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getCelsiusConversion(String fValue) {
        // Request
        SoapObject request = new SoapObject(NAMESPACE, METHOD);
        request.addProperty("Fahrenheit", fValue);

        // Envolope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        // Transport
        HttpTransportSE transport = new HttpTransportSE(MAIN_REQUEST_URL);

        // Call
        try {
            transport.call(SOAP_ACTION, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        // Result
        SoapPrimitive result = null;
        try {
            result = (SoapPrimitive) envelope.getResponse();
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }

        Log.i("Result", result.toString());

        return result.toString();
    }

    private class getCelsius extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... text) {
            return getCelsiusConversion(text[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            txt.setText(result);
        }

    }

    public class getCelsiusUsingEasyWSDL extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... text) {
            String result = "";
            try {
                result = service.FahrenheitToCelsius(text[0]);
                Log.i("WSDL", "Using EasyWSDL");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            txt.setText(result);
        }
    }
}
