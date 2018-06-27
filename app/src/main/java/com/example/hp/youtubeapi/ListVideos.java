package com.example.hp.youtubeapi;

import android.content.res.Resources;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListVideos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<Video> list;
    TextView name,views,likes,duration,channel,date;
    public static final String TAG = "ll";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_videos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        name  =(TextView)findViewById(R.id.name) ;
        views = (TextView)findViewById(R.id.views1);
        likes = (TextView)findViewById(R.id.likes1);
        duration = (TextView)findViewById(R.id.duration1);
        channel  = (TextView)findViewById(R.id.channel1);
        date = (TextView)findViewById(R.id.date1) ;

        list = new ArrayList<>();
        adapter = new Adapter(this,list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.music).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    private void prepareAlbums(){

        Task task;
        task = new Task();
        task.execute("3VW9Cecwr7s");
        task = new Task();
        task.execute("7KfhSftf1p0");
        task = new Task();
        task.execute("g_tBZSs83eg");
        task = new Task();
        task.execute("M3zYpoAaewk");
        task = new Task();
        task.execute("3VW9Cecwr7s");
        task = new Task();
        task.execute("7KfhSftf1p0");
        task = new Task();
        task.execute("g_tBZSs83eg");
        task = new Task();
        task.execute("M3zYpoAaewk");

    }

    public class Task extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... str) {
            String url1 = "https://www.googleapis.com/youtube/v3/videos?id="+str[0]+"&key=AIzaSyCjcIu3lFJR7_lvdTAB553_ZXFcLg3NmcY&part=snippet,contentDetails,statistics";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url1).build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            final json1 j = gson.fromJson(result,json1.class);
            ListVideos.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    name.setText(String.valueOf(j.getItems().get(0).getSnippet().getTitle()));
                    likes.setText(String.valueOf(j.getItems().get(0).getStatistics().getLikeCount()));
                    views.setText(String.valueOf(j.getItems().get(0).getStatistics().getViewCount()));
                    duration.setText(String.valueOf(j.getItems().get(0).getContentDetails().getDuration()));
                    date.setText(String.valueOf(j.getItems().get(0).getSnippet().getPublishedAt()));
                    channel.setText(String.valueOf(j.getItems().get(0).getSnippet().getChannelName()));
                }
            });
            return str[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String name2 = String.valueOf(name.getText());
            String likes2 = String.valueOf(likes.getText());
            String views2 = String.valueOf(views.getText());
            String date2 = String.valueOf(date.getText());
            String duration2 = String.valueOf(duration.getText());
            String channel2 = String.valueOf(channel.getText());
            Log.d(TAG, "onPostExecute: "+duration2);
            String h=null,m=null,sec=null;
            for(int i=0;i<duration2.length();i++) {
                Log.d(TAG, "onPostExecute1: " + duration2);
                if (duration2.charAt(i) == 'H') {
                    if (Character.isDigit(duration2.charAt(i - 2))) {
                        h = duration2.substring(i - 2, i);
                    } else {
                        h = duration2.substring(i - 1, i);
                    }

                } else if (duration2.charAt(i) == 'M') {
                    if (Character.isDigit(duration2.charAt(i - 2))) {
                        m = duration2.substring(i - 2, i);
                        Log.d(TAG, "onPostExecute2: " + m);

                    } else {
                        m = duration2.substring(i - 1, i);
                        Log.d(TAG, "onPostExecute2: " + m);

                    }

                } else if (duration2.charAt(i) == 'S') {
                    if (Character.isDigit(duration2.charAt(i - 2))) {
                        sec = duration2.substring(i - 2, i);
                    } else {
                        sec = duration2.substring(i - 1, i);
                    }

                }
            }
                if(h==null && m==null)
                {
                    duration2 = "0:"+sec;
                }
                else if(h==null)
                {
                    duration2 = m+":"+sec;
                }
                else
                {
                    duration2 = h+":"+m+":"+sec;
                }

                date2 = date2.substring(0,10);

            Video v = new Video(s,name2,likes2,views2,duration2,channel2,date2);
            list.add(v);
            adapter.notifyDataSetChanged();

        }

    }
}
