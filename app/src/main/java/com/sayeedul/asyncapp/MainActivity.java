package com.sayeedul.asyncapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button load,check,change;
    ImageView photo;
    ProgressBar prog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load=(Button)findViewById(R.id.loadBTN);
        check = (Button)findViewById(R.id.checkBTN);
        photo =(ImageView)findViewById(R.id.imageView);
        prog = (ProgressBar)findViewById(R.id.progressBar);
        change=(Button)findViewById(R.id.changeBTN);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "I AM WORKING & AVAILABLE IN UI THREAD", Toast.LENGTH_SHORT).show();
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadIconTask().execute(R.drawable.srk);
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int  n = rand.nextInt(4);
                switch(n)
                {
                    case 0:  new ChangeIconTask().execute(R.drawable.srk1); break;
                    case 1:  new ChangeIconTask().execute(R.drawable.srk2); break;
                    case 2:  new ChangeIconTask().execute(R.drawable.srk3); break;
                    case 3:  new ChangeIconTask().execute(R.drawable.srk4); break;
                }
            }
        });
    }


    class LoadIconTask extends AsyncTask<Integer,Integer,Bitmap>
    {
        @Override
        protected Bitmap doInBackground(Integer... integers) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),integers[0]);
            for(int i=0;i<10;i++)
            {
               try {
                   Thread.sleep(1000); publishProgress(i*25);
               }
               catch(InterruptedException e)
               {
                   e.printStackTrace();
               }
            }

            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prog.setVisibility(prog.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            prog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            prog.setVisibility(prog.INVISIBLE);
            photo.setImageBitmap(bitmap);
        }
    }


    class ChangeIconTask extends AsyncTask<Integer,Integer,Bitmap>
    {
        @Override
        protected Bitmap doInBackground(Integer... integers) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),integers[0]);
            for(int i=0;i<10;i++)
            {
                try {
                    Thread.sleep(1000); publishProgress(i*25);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prog.setVisibility(prog.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            prog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            prog.setVisibility(prog.INVISIBLE);
            photo.setImageBitmap(bitmap);
        }
    }

}
