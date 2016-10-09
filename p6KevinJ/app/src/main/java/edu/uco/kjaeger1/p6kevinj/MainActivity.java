package edu.uco.kjaeger1.p6kevinj;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class MainActivity extends Activity {
    private TextView tempTextView, detailsTextView, windTextView;
    private EditText cityQueryName;
    private Button getWeatherButton, showOnMapButton;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tempTextView = (TextView) findViewById(R.id.temp_textView);
        detailsTextView = (TextView) findViewById(R.id.details_textView);
        windTextView = (TextView) findViewById(R.id.wind_textView);
        cityQueryName = (EditText) findViewById(R.id.city_editText);
        showOnMapButton = (Button) findViewById(R.id.showMap_button);
        showOnMapButton.setVisibility(View.INVISIBLE);

        getWeatherButton = (Button) findViewById(R.id.getWeather_button);
        getWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpGetTask().execute(cityQueryName.getText().toString());
            }
        });
    }

    private class HttpGetTask extends AsyncTask<String, Void, HashMap<String,String>> {

        private static final String TAG = "HttpGetTask";

        // Construct the URL for the OpenWeatherMap query
        // Possible parameters are avaiable at OWM's forecast API page, at
        // http://openweathermap.org/API#forecast
        final String FORECAST_BASE_URL =
                "http://api.openweathermap.org/data/2.5/weather?";

        @Override
        protected HashMap<String,String> doInBackground(String... params) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            InputStream in = null;
            HttpURLConnection httpUrlConnection = null;
            HashMap<String,String> resultArray = null;
            try {
                Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                        .appendQueryParameter("q", params[0] + ",us") // city
                        .appendQueryParameter("mode", "json") // json format as result
                        .appendQueryParameter("units", "imperial") // imperial units
                        .appendQueryParameter("APPID", "b365c2e13355ec3e1cb7698843c7fdae")
                        .build();

                URL url = new URL(builtUri.toString());
                httpUrlConnection = (HttpURLConnection) url.openConnection();
                in = new BufferedInputStream(
                        httpUrlConnection.getInputStream());
                String data = readStream(in);
                resultArray = JSONWeatherData.getData(data);

            } catch (MalformedURLException exception) {
                Log.e(TAG, "MalformedURLException");
            } catch (IOException exception) {
                Log.e(TAG, "IOException");
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage(), e);
                e.printStackTrace();
            } finally {
                if (null != httpUrlConnection) {
                    httpUrlConnection.disconnect();
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (final IOException e) {
                        Log.e(TAG, "Error closing stream", e);
                    }
                }
            }

            return resultArray;
        }

        @Override
        protected void onPostExecute(final HashMap<String,String> result) {
            if (result == null || result.size() == 0 || !result.get("City").equalsIgnoreCase(cityQueryName.getText().toString())) {

                Toast.makeText(MainActivity.this,
                        "Invalid weather data. Possibly a wrong query",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            tempTextView.setText("Temp: " + result.get("Temp") + (char) 0x00B0 + "F");
            detailsTextView.setText("Details: " + result.get("Details"));
            double windMPH = Double.parseDouble(result.get("Wind")) * 0.681818;
            int windMPHInt = (int) windMPH;
            String windMPHString = Integer.toString(windMPHInt);
            windTextView.setText("Wind: " + windMPHString + " mph");
            showOnMapButton.setVisibility(View.VISIBLE);
            showOnMapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    intent.putExtra("CityWeatherHashMap", result);
                    startActivity(intent);
                }
            });

        }

        private String readStream(InputStream in) {
            BufferedReader reader = null;
            StringBuffer data = new StringBuffer("");
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    data.append(line);
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException");
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return data.toString();
        }
    }
}
