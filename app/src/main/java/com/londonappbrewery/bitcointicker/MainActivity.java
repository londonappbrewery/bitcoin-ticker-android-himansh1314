package com.londonappbrewery.bitcointicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    // Constants:
    // TODO: Create the base URL
    private final String GET_URL = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTC";

    // Member Variables:
    TextView mPriceTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPriceTextView = (TextView) findViewById(R.id.priceLabel);
        Spinner spinner = (Spinner) findViewById(R.id.currency_spinner);

        // Create an ArrayAdapter using the String array and a spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, R.layout.spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Bitcoin",""+parent.getItemAtPosition(position));
                String selectedItem = (String)parent.getItemAtPosition(position);
                Log.d("Bitcoin", ""+selectedItem);
                switch(selectedItem){
                    case "AUD":
                        letsDoSomeNetworking(GET_URL+"AUD");
                        break;
                    case "BRL":
                        letsDoSomeNetworking(GET_URL+"BRL");
                        break;
                    case "CAD":
                        letsDoSomeNetworking(GET_URL+"CAD");
                        break;
                    case "CNY":
                        letsDoSomeNetworking(GET_URL+"CNY");
                        break;
                    case "EUR":
                        letsDoSomeNetworking(GET_URL+"EUR");
                        break;
                    case "GBP":
                        letsDoSomeNetworking(GET_URL+"GBP");
                        break;
                    case "HKD":
                        letsDoSomeNetworking(GET_URL+"HKD");
                        break;
                    case "JPY":
                        letsDoSomeNetworking(GET_URL+"JPY");
                        break;
                    case "PLN":
                        letsDoSomeNetworking(GET_URL+"PLN");
                        break;
                    case "RUB":
                        letsDoSomeNetworking(GET_URL+"RUB");
                        break;
                    case "SEK":
                        letsDoSomeNetworking(GET_URL+"SEK");
                        break;
                    case "USD":
                        letsDoSomeNetworking(GET_URL+"USD");
                        break;
                    case "ZAR":
                        letsDoSomeNetworking(GET_URL+"ZAR");
                        break;
                    case "INR":
                        letsDoSomeNetworking(GET_URL+"INR");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("Bitcoin", "Nothing selected, try again");
            }
        });

        // TODO: Set an OnItemSelected listener on the spinner

    }


    // TODO: complete the letsDoSomeNetworking() method
    private void letsDoSomeNetworking(String url) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.d("Bitcoin", "JSON: " + response.toString());
                BitcoinDataModel dataModel = BitcoinDataModel.fromJSON(response);
                mPriceTextView.setText(dataModel.getValue());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.e("Bitcoin", ""+e.toString());

            }
        });


    }


}
