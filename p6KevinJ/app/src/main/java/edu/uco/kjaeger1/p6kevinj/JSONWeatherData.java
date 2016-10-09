package edu.uco.kjaeger1.p6kevinj;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class JSONWeatherData {

    private static final String TAG = "JSONWeatherData";

    /**
     * Take the String representing the complete forecast in JSON Format and
     * pull out the data we need to construct the Strings needed for the wireframes.
     * <p/>
     * Fortunately parsing is easy:  constructor takes the JSON string and converts it
     * into an Object hierarchy for us.
     */
    public static HashMap<String, String> getData(String forecastJsonStr)
            throws JSONException {

        // Now we have a String representing the complete forecast in JSON Format.
        // Fortunately parsing is easy:  constructor takes the JSON string and converts it
        // into an Object hierarchy for us.

        try {
            JSONObject currentForecast = new JSONObject(forecastJsonStr);
            HashMap<String,String> result = new HashMap<>(); // To store all necessary weather info

            String cityName = currentForecast.getString("name"); // city name

            JSONObject cityCoord = currentForecast.getJSONObject("coord"); // coordinate
            double cityLat = cityCoord.getDouble("lat"); //latitude
            double cityLon = cityCoord.getDouble("lon"); // longitude

            result.put("City", cityName);
            result.put("Longitude", Double.toString(cityLon));
            result.put("Latitude", Double.toString(cityLat));

            // OWM returns daily forecasts based upon the local time of the city that is being
            // asked for, which means that we need to know the GMT offset to translate this data
            // properly.

            // Since this data is also sent in-order and the first day is always the
            // current day, we're going to take advantage of that to get a nice
            // normalized UTC date for all of our weather.

            // Get the Weather Details/description
            JSONObject weatherObject =
                    currentForecast.getJSONArray("weather").getJSONObject(0);
            String description = weatherObject.getString("description");
            result.put("Details", description);

            // Get the Temperature
            JSONObject temperatureObject = currentForecast.getJSONObject("main");
            double temperature = temperatureObject.getDouble("temp");
            result.put("Temp", Double.toString(temperature));

            // Get The Wind Speed
            JSONObject windObject = currentForecast.getJSONObject("wind");
            double windSpeed = windObject.getDouble("speed");
            result.put("Wind", Double.toString(windSpeed));

            return result;

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        return null;
    }
}
