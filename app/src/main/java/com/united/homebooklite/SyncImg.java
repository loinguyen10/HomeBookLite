package com.united.homebooklite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.united.homebooklite.api.Demo11Interface;

import java.io.InputStream;
import java.net.URL;

public class SyncImg extends AsyncTask<String, Void, Bitmap> {
    private Demo11Interface demo11Interface;
    private Context context;

    public SyncImg(Demo11Interface demo11Interface, Context c){
        this.demo11Interface = demo11Interface;
        this.context = c;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        try{
            return BitmapFactory.decodeStream((InputStream)new URL(strings[0]).getContent());
        }catch (Exception e){
            e.printStackTrace();
            Log.e("Async_Task", "Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmap!=null) demo11Interface.onLoadBitmap(bitmap);
        else demo11Interface.onError();
    }

}
