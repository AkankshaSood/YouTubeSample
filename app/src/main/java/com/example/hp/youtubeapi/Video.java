package com.example.hp.youtubeapi;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hp on 6/20/2018.
 */

public class Video {

    String name;
    String id;
    String imgURL;
    String views;
    String likes;
    String duration;
    String channel;
    String date;

    public Video(String id,String names,String likes,String views,String duration,String channel,String date)
    {
        this.id = id;
        this.name = names;
        this.likes = likes;
        this.views = views;
        imgURL = "https://img.youtube.com/vi/"+id+"/0.jpg";
        this.duration = duration;
        this.channel = channel;
        this.date = date;
    }



    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLikes() {
        return likes;
    }

    public String getViews() {
        return views;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getDuration() {
        return duration;
    }

    public String getDate() {
        return date;
    }

    public String getChannel() {
        return channel;
    }

    public String makeNetworkCall(String url) {
        final String[] n = new String[1];

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            final String[] n1 = new String[1];
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                String result = response.body().string();
                Gson gson = new Gson();
                final json1 j = gson.fromJson(result,json1.class);
//                    MainActivity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
                n1[0] = String.valueOf(j.getItems().get(0).getSnippet().getTitle());

                //l= String.valueOf(j.getItems().get(0).getStatistics().getLikeCount());


                //v = String.valueOf(j.getItems().get(0).getStatistics().getViewCount());


//

//                            body = String.valueOf(j.getItems().get(0).getStatistics().getCommentCount());
//                            views.setText(body);
                //}
                // });
            }
        });

        return n[0];

    }
}
