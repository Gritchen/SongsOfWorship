package com.josephgritchen.songsofworship;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class Tip1Activity extends AppCompatActivity {
    private ArrayList<Music> arrayList;
    private CustomMusicAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip1);



        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        recyclerView =  findViewById(R.id.recyclerViewList);

        arrayList = new ArrayList<>();
        arrayList.add(new Music("001 Veni, veni, Emmanuel", "SONGS OF WORSHIP", R.raw.veni_emmanuel));
        arrayList.add(new Music("002 O come. O come Emmanuel", "SONGS OF WORSHIP", R.raw.o_come_emmanuel));
        arrayList.add(new Music("003 Savior of the nations, come", "SONGS OF WORSHIP", R.raw.savior_of_nations));
        arrayList.add(new Music("004 On Jordan's bank the Baptist's cry", "SONGS OF WORSHIP", R.raw.on_jordans_bank));
        arrayList.add(new Music("005 Lo, he comes", "SONGS OF WORSHIP", R.raw.lo_he_comes));
        arrayList.add(new Music("006 O Word that goest forth on high", "SONGS OF WORSHIP", R.raw.o_word_that_goes_forth));
        arrayList.add(new Music("007 Wake, awake", "SONGS OF WORSHIP", R.raw.wake_awake));
        arrayList.add(new Music("008 Rorate caeli", "SONGS OF WORSHIP", R.raw.rorate_caeli));
        arrayList.add(new Music("009 Conditor alme siderum", "SONGS OF WORSHIP", R.raw.conditor_alme_siderum));
        arrayList.add(new Music("010 Creator of the stars of night", "SONGS OF WORSHIP", R.raw.creator_of_the_stars));



        adapter = new CustomMusicAdapter(this, R.layout.custom_music_row, arrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);

    }



    public void onDestroy() {
        super.onDestroy();
        if( adapter !=null ){
            adapter.release();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to close App?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Tip1Activity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search_view);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return true;
            }
        });



        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.item1:
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Main2Activity.class));
                return true;

            //DO YOUR FUNCTIONALITY HERE




            default:
                return super.onOptionsItemSelected(item);
        }

    }
}