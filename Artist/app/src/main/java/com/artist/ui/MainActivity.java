package com.artist.ui;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.artist.R;


public class MainActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Fragment1 fg1= new Fragment1();
//        FragmentTransaction ft= getFragmentManager().beginTransaction();
//        ft.replace(R.id.relativeLayout1, fg1).commit();

        options();

    }

    private void options(){

        listView= (ListView) findViewById(R.id.listView);
        String[] bars= {"Home","Songs", "Videos", "Mailing"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,bars);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        doCase1();
                        break;
                    case 1:
                        doCase2();
                        break;
                    case 2:
                        doCase3();
                        break;
                    case 3:
                        doCaes4();
                        break;
                    default:
                        break;

                }
            }
        });
    }
    private void doCase1(){
        Fragment1 fg1= new Fragment1();
        FragmentTransaction ft= getFragmentManager().beginTransaction();
        ft.replace(R.id.relativeLayout2,fg1).commit();
    }
    private void doCase2(){
        Fragment2 fg2= new Fragment2();
        FragmentTransaction ft= getFragmentManager().beginTransaction();
        ft.replace(R.id.relativeLayout2,fg2).commit();

    }
    private void doCase3(){
        Fragment3 fg3= new Fragment3();
        FragmentTransaction ft= getFragmentManager().beginTransaction();
        ft.replace(R.id.relativeLayout2,fg3).commit();

    }
    private void doCaes4(){
        Fragment4 fg4= new Fragment4();
        FragmentTransaction ft= getFragmentManager().beginTransaction();
        ft.replace(R.id.relativeLayout2,fg4).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
