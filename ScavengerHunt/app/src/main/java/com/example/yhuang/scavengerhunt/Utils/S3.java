package com.example.yhuang.scavengerhunt.Utils;

import android.os.AsyncTask;
import android.util.Log;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import java.io.File;

public class S3 {

    public static class UploadImage extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... params) {

            String filename = params[0];
            String uuid = params[1];

            AmazonS3Client s3Client = new AmazonS3Client();
            File fileToUpload = new File(filename);
            //(Replace "MY-BUCKET" with your S3 bucket name, and "MY-OBJECT-KEY" with whatever you would            like to name the file in S3)
            PutObjectRequest putRequest = new PutObjectRequest("olin-mobile-proto", uuid,
                    fileToUpload);
            PutObjectResult putResponse = s3Client.putObject(putRequest);

            Log.d("Result: ", putResponse.toString());

//            GetObjectRequest getRequest = new GetObjectRequest("olin-mobile-proto", uuid);
//            S3Object getResponse = s3Client.getObject(getRequest);
//            InputStream myObjectBytes = getResponse.getObjectContent();
//
//            // Do what you want with the object
//
//            try {
//                myObjectBytes.close();
//            } catch (IOException err) {
//                err.printStackTrace();
//            }

            return null;
        }
    }
}
