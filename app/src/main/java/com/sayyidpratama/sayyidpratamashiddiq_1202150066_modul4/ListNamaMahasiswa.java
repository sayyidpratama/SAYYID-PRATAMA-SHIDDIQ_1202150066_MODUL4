package com.sayyidpratama.sayyidpratamashiddiq_1202150066_modul4;

/**
 * Created by SP-SHOCK on 3/18/2018.
 */

import android.annotation.SuppressLint;

import android.app.Activity;

import android.app.ProgressDialog;

import android.content.DialogInterface;

import android.content.pm.ActivityInfo;

import android.content.res.Configuration;

import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.support.v7.widget.Toolbar;

import android.view.View;

import android.widget.ArrayAdapter;

import android.widget.Button;

import android.widget.ListView;

import android.widget.ProgressBar;



import java.util.ArrayList;

import java.lang.String;

import static android.view.View.GONE;



import android.annotation.SuppressLint;

import android.app.Activity;

import android.app.ProgressDialog;

import android.content.DialogInterface;

import android.content.pm.ActivityInfo;

import android.content.res.Configuration;

import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.support.v7.widget.Toolbar;

import android.view.View;

import android.widget.ArrayAdapter;

import android.widget.Button;

import android.widget.ListView;

import android.widget.ProgressBar;



import java.util.ArrayList;



public class ListNamaMahasiswa extends AppCompatActivity {

    //    Deklarasi variabel

        private ListView mListView;

        private ProgressBar mProgressBar;

    private String[] mNamaMahasiswa = {

            "Shiddiq", "Vian", "Bejo", "Sylvi", "Hana", "Ranti", "Deby", "Ranes", "Aprianil", "Dimas"
    };

    private ItemListView itemListView;

    private Button mBtnFind;



    FragmentNama fragmentMhs;

    Activity activity;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listnamamahasiswa);

//        referensi id

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);

        mListView = (ListView) findViewById(R.id.ListNama);

        mBtnFind = (Button) findViewById(R.id.BtnCari);



        mListView.setVisibility(GONE);



        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));



        mBtnFind.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                itemListView = new ItemListView(activity);

                itemListView.execute();

            }

        });

        if (savedInstanceState == null){

            fragmentMhs = new FragmentNama();

            getSupportFragmentManager().beginTransaction().add(fragmentMhs,"task").commit();

        }else{ //activity created for subscquent time

            fragmentMhs = (FragmentNama) getSupportFragmentManager().findFragmentByTag("task");

        }



        if (fragmentMhs != null){

            if (fragmentMhs.itemListView != null && fragmentMhs.itemListView.getStatus() == AsyncTask.Status.RUNNING){

                // progressBar.setVisibility(View.VISIBLE);

            }

        }

    }



    class ItemListView extends AsyncTask<Void, String, Void>{



        private Activity activity;



        public ItemListView(Activity activity){

            this.activity = activity;

        }



        private ArrayAdapter<String> mAdapter;



        private int count = 1;

        ProgressDialog mProgressDialog = new ProgressDialog(ListNamaMahasiswa.this);

        public void onAttach(Activity activity){this.activity = activity;}

        public void onDetach(){activity = null;}



        @Override



        protected void onPreExecute(){

            mAdapter = (ArrayAdapter<String>)mListView.getAdapter();



//      Progress Dialog

            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            mProgressDialog.setTitle("Loading Name");

            mProgressDialog.setMessage("Please Wait");

            mProgressDialog.setCancelable(false);

            mProgressDialog.setProgress(0);



//      Ketika button cancel di klik

            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {

                @Override

                public void onClick(DialogInterface dialogInterface, int i) {

                    itemListView.cancel(true);

                    mProgressBar.setVisibility(View.VISIBLE);

                    dialogInterface.dismiss();

                }

            });

            mProgressDialog.show();

        }



        @Override

        protected Void doInBackground(Void... params) {

            for(String item: mNamaMahasiswa){

                publishProgress(item);

                try{

                    Thread.sleep(100);

                }catch(Exception e){

                    e.printStackTrace();

                }

                if (isCancelled()){

                    itemListView.cancel(true);

                }

            }

            return null;

        }

        @Override

        protected void onProgressUpdate(String... values) {

            mAdapter.add(values[0]);



            Integer current_status = (int) ((count/(float)mNamaMahasiswa.length)*100);

            mProgressBar.setProgress(current_status);



            //set progress only working for horizontal loading

            mProgressDialog.setProgress(current_status);



            //set message will not working when using horizontal loading

            mProgressDialog.setMessage(String.valueOf(current_status+"%"));

            count++;

        }

        @Override

        protected void onPostExecute(Void aVoid){

            mProgressBar.setVisibility(GONE);



            // remove progress dialog

            mProgressDialog.dismiss();

            mListView.setVisibility(View.VISIBLE);

        }

    }

}
