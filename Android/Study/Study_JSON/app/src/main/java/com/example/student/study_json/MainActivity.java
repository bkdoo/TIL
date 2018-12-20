package com.example.student.study_json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tv_show, tv_show2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_show = (TextView) findViewById(R.id.tv_show);
        tv_show2 = (TextView) findViewById(R.id.tv_show2);

        String json =
                "{" +
                        "\"user\": \"gildong\"," +
                        "\"color\": [\"red\", \"green\", \"blue\"]" +
                        "}";

        String json2 = "{" +
                "\"weather\": [{" +
                "\"id\": \"721\"," +
                "\"main\":\"Haze\"," +
                "\"description\":\"haze\"," +
                "\"icon\":\"50n\"" +
                "}]," +
                "\"main\":{" +
                "\"temp\" : \"10.14\"," +
                "\"pressure\":\"1020\"," +
                "\"humidity\":\"37\"," +
                "\"temp_min\":\"6\"," +
                "\"temp_max\":\"13\"" +
                "}," +
                "\"id\":\"18392\"," +
                "\"name\":\"Seoul\"," +
                "\"cod\":\"200\"" +
                "}";


        try {
            JSONObject root = new JSONObject(json);
            String user_name = root.getString("user");
            JSONArray color = root.getJSONArray("color");
            String color1 = color.getString(0);
            String color2 = color.getString(1);
            String color3 = color.getString(2);

            String result = "name : " + user_name + "\ncolor1 : " + color1
                    + "\ncolor2 : " + color2 + "\ncolor3 : " + color3;

            tv_show.setText(result);

            JSONObject root2 = new JSONObject(json2);
            JSONArray weather = root2.getJSONArray("weather");
            String weather_id = weather.getJSONObject(0).getString("id");
            String weather_main = weather.getJSONObject(0).getString("main");
            String weather_description = weather.getJSONObject(0).getString("description");
            String weather_icon = weather.getJSONObject(0).getString("icon");

            JSONObject main = root2.getJSONObject("main");
            String main_temp = main.getString("temp");
            String main_pressure = main.getString("pressure");
            String main_humidity = main.getString("humidity");
            String main_temp_min = main.getString("temp_min");
            String main_temp_max = main.getString("temp_max");


            String id = root2.getString("id");
            String name = root2.getString("name");
            String cod = root2.getString("cod");

            StringBuilder sb = new StringBuilder();
            sb.append("weather_id : ")
                    .append(weather_id)
                    .append("\nweather_main : ")
                    .append(weather_main)
                    .append("\nweather_description : ")
                    .append(weather_description)
                    .append("\nweather_icon : ")
                    .append(weather_icon)
                    .append("\n\nmain_temp : ")
                    .append(main_temp)
                    .append("\nmain_pressure : ")
                    .append(main_pressure)
                    .append("\nmain_humidity : ")
                    .append(main_humidity)
                    .append("\nmain_temp_min : ")
                    .append(main_temp_min)
                    .append("\nmain_temp_max : ")
                    .append(main_temp_max)
                    .append("\n\nid : ")
                    .append(id)
                    .append("\nname : ")
                    .append(name)
                    .append("\ncod : ")
                    .append(cod);

            tv_show2.setText(sb.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
