package com.example.collo.kinemax;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    List<YoutubeVideos> YoutubeVideoList;

    public  VideoAdapter(){

    }

    public VideoAdapter(List<YoutubeVideos> youtubeVideoList) {
        YoutubeVideoList = youtubeVideoList;
    }

    @NonNull
    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.video_view, parent, false);


        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.VideoViewHolder holder, int position) {
        holder.videoweb.loadData(YoutubeVideoList.get(position).getVideoUrl(),"text/html", "utf-8");
    }

    @Override
    public int getItemCount() {
        return YoutubeVideoList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{
        WebView videoweb;
        public VideoViewHolder(View itemView){
            super (itemView);

            videoweb = (WebView) itemView.findViewById(R.id.webview);

            videoweb.getSettings().setJavaScriptEnabled(true);
            videoweb.setWebChromeClient(new WebChromeClient(){


            });
        }
    }

}
