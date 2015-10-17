package com.example.yhuang.scavengerhunt.Utils;

import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by yhuang on 10/15/2015.
 */

public class S3video extends AsyncTask<Void,Void,String> {
    @Override
    protected String doInBackground(Void... params) {
        AmazonS3Client s3Client = new AmazonS3Client();
        GetObjectRequest getRequest = new GetObjectRequest("olin-mobile-proto", "MVI_3145.3GP");
        S3Object getResponse = s3Client.getObject(getRequest);
        InputStream myObjectBytes = getResponse.getObjectContent();
        String url = myObjectBytes.toString();
        Log.d("URL: ", url);

        return url;
    }
}

