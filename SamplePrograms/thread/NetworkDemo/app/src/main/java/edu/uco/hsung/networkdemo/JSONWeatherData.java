package edu.uco.hsung.networkdemo;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONWeatherData {

    private static final String TAG = "JSONWeatherData";

    /**
     * Take the String representing the complete forecast in JSON Format and
     * pull out the data we need to construct the Strings needed for the wireframes.
     * <p/>
     * Fortunately parsing is easy:  constructor takes the JSON string and converts it
     * into an Object hierarchy for us.
     */
    public static ArrayList<String> getData(String forecastJsonStr)
            throws JSONException {

        // Now we have a String representing the complete forecast in JSON Format.
        // Fortunately parsing is easy:  constructor takes the JSON string and converts it
        // into an Object hierarchy for us.

        try {
            JSONObject forecastJson = new JSONObject(forecastJsonStr);

            // Each day's forecast info is an element of the "list" array.
            JSONArray weatherArray = forecastJson.getJSONArray("list");

            JSONObject cityJson = forecastJson.getJSONObject("city");
            String cityName = cityJson.getString("name"); // city name

            JSONObject cityCoord = cityJson.getJSONObject("coord"); // coordinate
            double cityLat = cityCoord.getDouble("lat"); //latitude
            double cityLon = cityCoord.getDouble("lon"); // longitude

            // OWM returns daily forecasts based upon the local time of the city that is being
            // asked for, which means that we need to know the GMT offset to translate this data
            // properly.

            // Since this data is also sent in-order and the first day is always the
            // current day, we're going to take advantage of that to get a nice
            // normalized UTC date for all of our weather.

            ArrayList<String> result = new ArrayList<>();
            result.add(cityName + "(" + cityLon + ", " + cityLat + ")");

            for (int i = 0; i < weatherArray.length(); i++) {

                // Get the JSON object representing the day
                JSONObject dayForecast = weatherArray.getJSONObject(i);

                int humidity = dayForecast.getInt("humidity");
                double windSpeed = dayForecast.getDouble("speed");
                JSONObject weatherObject =
                        dayForecast.getJSONArray("weather").getJSONObject(0);
                String description = weatherObject.getString("description");

                // Temperatures are in a child object called "temp".
                JSONObject temperatureObject = dayForecast.getJSONObject("temp");
                double high = temperatureObject.getDouble("max"); // max temperature
                double low = temperatureObject.getDouble("min"); // min temperature

                result.add("Day" + (i + 1) + ": " +description+
                        " high=" + high +
                        " low=" + low
                        + " humidity=" + humidity
                        + " windspeed=" + windSpeed);
            }

            return result;

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        return null;
    }
}
