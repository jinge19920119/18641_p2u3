package com.quizscores.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.quizscores.R;
import com.quizscores.model.Student;
import com.quizscores.util.DatabaseConnector;

import java.io.FileDescriptor;
import java.io.PrintWriter;


public class MainActivity extends Activity {

    private EditText numEditText;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private int num;
    private double score1;
    private double score2;
    private double score3;
    private double score4;
    private double score5;

    private Button b1;
    private Button b2;
    private Button b3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String[] cars= {"1111","2222","3333","11","1","1","1","1","1","1","1","1","1","1"};

//        ListView list= (ListView) findViewById(android.R.id.list);
//        ArrayAdapter<String > adapter= new ArrayAdapter<String>(getListView().getContext(),android.R.layout.simple_list_item_1,cars);
//        getListView().setAdapter(adapter);

        numEditText= (EditText) findViewById(R.id.numEditText);
        editText1= (EditText) findViewById(R.id.editText1);
        editText2= (EditText) findViewById(R.id.editText2);
        editText3= (EditText) findViewById(R.id.editText3);
        editText4= (EditText) findViewById(R.id.editText4);
        editText5= (EditText) findViewById(R.id.editText5);


    }



    public void buttonOnClickNext(View v){
        num= Integer.parseInt(numEditText.getText().toString());
        score1= Double.parseDouble(editText1.getText().toString());
        score2= Double.parseDouble(editText2.getText().toString());
        score3= Double.parseDouble(editText3.getText().toString());
        score4= Double.parseDouble(editText4.getText().toString());
        score5= Double.parseDouble(editText5.getText().toString());
        double[] scores={score1,score2,score3,score4,score5};
        if(isException(num,scores)){
            Student st= new Student(num,scores);
            DatabaseConnector dc= new DatabaseConnector(this);
            dc.insertValues(st);
            Toast.makeText(this, " Save successfully, continue!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, " Input error! Type again!", Toast.LENGTH_LONG).show();
        }
    }


    public void buttonOnClickSave(View v) {

        Fragment1 fg1= new Fragment1();
        FragmentTransaction ft= getFragmentManager().beginTransaction();
        ft.replace(R.id.frag, fg1);
        ft.commit();

    }
    public void buttonOnClickShow(View v){
        Fragment2 fg2= new Fragment2();
        FragmentTransaction ft= getFragmentManager().beginTransaction();
        ft.replace(R.id.frag, fg2);
        ft.commit();
    }

    public void buttonOnClickClear(View v){
        DatabaseConnector db= new DatabaseConnector(this);
        db.clear();
    }
    /*
    see if all the input are in the correct range
     */
    private boolean isException(int num, double[] scores){
        if(num>9999)
            return false;
        for(int i=0;i<scores.length;i++){
            if(scores[i]<0 || scores[i]>100)
                return false;
        }
        return true;
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
