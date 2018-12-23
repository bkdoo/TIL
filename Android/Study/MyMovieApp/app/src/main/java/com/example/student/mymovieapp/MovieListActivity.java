package com.example.student.mymovieapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.student.mymovieapp.adapter.ListViewAdapter;
import com.example.student.mymovieapp.adapter.ListViewAdapter2;
import com.example.student.mymovieapp.form.ListViewItem;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MovieListActivity extends AppCompatActivity {

    ListView lvMovie;

    //자료를 배열로 만들어서 ListView에 전달
    ListViewAdapter listViewAdapter;

    ArrayList<ListViewItem> arrayList;

    final String[] arrMovie = {"블랙펜서", "궁합", "리틀포레스트", "월요일이 사라졌다"};

    public static final int NUMBER_MOVIE = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        lvMovie = (ListView) findViewById(R.id.lvMovie);


        arrayList = new ArrayList<>();
        arrayList.add(new ListViewItem("블랙팬서", "2018. 03", R.drawable.black));
        arrayList.add(new ListViewItem("궁합", "2018. 06", R.drawable.match));
        arrayList.add(new ListViewItem("리틀 포레스트", "2018. 09", R.drawable.little));
        arrayList.add(new ListViewItem("월요일이 사라졌다", "2018. 12", R.drawable.monday));


        String url = "http://70.12.110.63:3000";
        HashMap<String, String> movieMap;
        MyHttpTask[] myHttpTask = new MyHttpTask[NUMBER_MOVIE];

        for (int i = 0; i < NUMBER_MOVIE; i++) {
            movieMap = new HashMap<>();
            movieMap.put("number", String.valueOf(i + 1));
            myHttpTask[i] = new MyHttpTask(url, movieMap);
            myHttpTask[i].execute();

        }

        listViewAdapter = new ListViewAdapter(MovieListActivity.this, R.layout.listview_item, arrayList);
        lvMovie.setAdapter(listViewAdapter);


        /**
         * 첫번째 매개변수 : 액티비티 정보 (context 객체)
         * 두번째 매개변수 : 리스트뷰 항목의 레이아웃(안드로이드 제공 : simple_list_item_1)
         * 표시할 데이터들
         */
//        ListViewAdapter listViewAdapter = new ListViewAdapter(
//                MovieListActivity.this, R.layout.listview_item, arrayList);
//        lvMovie.setAdapter(listViewAdapter);


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
                    StringBuffer sb = new StringBuffer();

                    String line;


                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }


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
            try {
                JSONObject root = new JSONObject(s);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            arrayList.add(new ListViewItem(map.get("title"), map.get("date"), 0));

            listViewAdapter.notifyDataSetChanged();
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
        for (int i = 0; i < jsonArray.length() - 1; i++) {
            try {
                sb.append(String.valueOf(jsonArray.get(i)));
                sb.append(", ");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            sb.append(jsonArray.get(jsonArray.length() - 1));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
