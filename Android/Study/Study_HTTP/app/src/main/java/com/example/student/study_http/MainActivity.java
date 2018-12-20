package com.example.student.study_http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    TextView tv_data;
    ImageView iv_poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_data = (TextView) findViewById(R.id.tv_data);
        iv_poster = (ImageView) findViewById(R.id.iv_poster);

        String url = "http://70.12.110.50:3000";
        HashMap<String, String> map = new HashMap<>();
        map.put("number", "5");

        MyHttpTask myHttpTask = new MyHttpTask(url, map);
        myHttpTask.execute();

        String url_img = "http://70.12.110.50:3000/files";

        MyImageHttpTask myImageHttpTask = new MyImageHttpTask(url_img, map);
        myImageHttpTask.execute();

    }

    class MyHttpTask extends AsyncTask<Void, Void, String> {

        String url_str;
        HashMap<String, String> map;

        public MyHttpTask(String url_str, HashMap<String, String> map) {
            super();
            this.url_str = url_str;
            this.map = map;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result = null;
            String post_query = "";
            PrintWriter printWriter = null;
            BufferedReader bufferedReader = null;

            try {
                URL text = new URL(url_str);
                HttpURLConnection http = (HttpURLConnection) text.openConnection();
                http.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
                http.setConnectTimeout(10000);
                http.setReadTimeout(10000);
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                if (map != null && map.size() > 0) {
                    Iterator<String> keys = map.keySet().iterator();

                    boolean first_query_part = true;
                    while (keys.hasNext()) {
                        if (!first_query_part) {
                            post_query += "&";
                        }

                        String key = keys.next();
                        post_query += (key + "=" + URLEncoder.encode(map.get(key), "UTF-8"));

                        first_query_part = false;
                    }

                    //sending to server
                    printWriter = new PrintWriter(new OutputStreamWriter(http.getOutputStream(), "UTF-8"));
                    printWriter.write(post_query);
                    printWriter.flush();

                    //receive from server
                    bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream(), "UTF-8"));
                    StringBuffer stringBuffer = new StringBuffer();
                    String line;


                    while ((line = bufferedReader.readLine()) != null) {
                        JSONObject root = new JSONObject(line);


                        stringBuffer.append("number : ")
                                .append(root.getString("number"))
                                .append("\ntitle : ")
                                .append(root.getString("title"))
                                .append("\ndirector : ")
                                .append(JSONtoArray(root.getJSONArray("director")))
                                .append("\nactor : ")
                                .append(JSONtoArray(root.getJSONArray("actor")))
                                .append("\ncategory : ")
                                .append(JSONtoArray(root.getJSONArray("category")))
                                .append("\nrunningTime : ")
                                .append(root.getString("runningTime"))
                                .append(" min")
                                .append("\nopenDate : ")
                                .append(root.getString("openDate"));
                    }

                    result = stringBuffer.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (printWriter != null) printWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            // do something
            tv_data.setText(s);
            this.cancel(true);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }


    }

    private String JSONtoArray(JSONArray jsonArray) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < jsonArray.length()-1; i++) {
            try {
                sb.append(String.valueOf(jsonArray.get(i)));
                sb.append(", ");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            sb.append(jsonArray.get(jsonArray.length()-1));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    class MyImageHttpTask extends AsyncTask<Void, Void, Bitmap> {


        String url_str;
        HashMap<String, String> map;

        public MyImageHttpTask(String url_str, HashMap<String, String> map) {
            super();

            this.url_str = url_str;
            this.map = map;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            Bitmap result = null;
            String post_query = "";
            PrintWriter printWriter = null;

            try {
                URL text = new URL(url_str);
                HttpURLConnection http = (HttpURLConnection) text.openConnection();
                http.setRequestProperty("Content-type",
                        "application/x-www-form-urlencoded;charset=UTF-8");
                http.setConnectTimeout(10000);
                http.setReadTimeout(10000);
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                if (map != null && map.size() > 0) {

                    Iterator<String> keys = map.keySet().iterator();

                    boolean first_query_part = true;
                    while (keys.hasNext()) {

                        if (!first_query_part) {
                            post_query += "&";
                        }

                        String key = keys.next();
                        post_query += (key + "=" + URLEncoder.encode(map.get(key), "UTF-8"));

                        first_query_part = false;
                    }

                    // sending to server
                    printWriter = new PrintWriter(new OutputStreamWriter(
                            http.getOutputStream(), "UTF-8"));
                    printWriter.write(post_query);
                    printWriter.flush();

                    // receive from server
                    result = BitmapFactory.decodeStream(http.getInputStream());

                }
            } catch (Exception e) {
                e.printStackTrace();
                result = null;
            } finally {
                try {
                    if (printWriter != null) printWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(Bitmap s) {
            // do something
            iv_poster.setImageBitmap(s);
            this.cancel(true);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
