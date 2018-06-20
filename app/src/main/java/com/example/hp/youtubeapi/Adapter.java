package com.example.hp.youtubeapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by hp on 6/20/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context mContext,context2;
    private List<Video> videoList;
   // private final OnItemClickListener listener;

//    public interface OnItemClickListener {
//        void onItemClick(Video item);
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView title;
        public ImageView thumbnail;
        LinearLayout ParentLayout;
        CardView cv;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            context2 = view.getContext();
            ParentLayout = (LinearLayout)view.findViewById(R.id.parent_layout);
            cv = (CardView)view.findViewById(R.id.video_card);

//
        }

    }


    public Adapter(Context mContext, List<Video> VideoList) {
        this.mContext = mContext;
        this.videoList = VideoList;
        //this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Video video = videoList.get(position);
        holder.title.setText(video.getName());
        //Glide.with(mContext).load(video.getThumbnail()).into(holder.thumbnail);
        new DownLoadImageTask(holder.thumbnail).execute(video.getImgURL());
        //holder.ParentLayout.setOnClickListener(new View.OnClickListener() {
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,MainActivity.class);
                i.putExtra("id",video.getId());
                mContext.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return videoList.size();
    }

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}

