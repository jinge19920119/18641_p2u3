package com.quizscores.ui;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.quizscores.R;
import com.quizscores.util.DatabaseConnector;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {


    private ListView listView;
    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_fragment1, container, false);
        DatabaseConnector db= new DatabaseConnector(getActivity());
        db.open();
        Cursor cursor= db.getAll();
        cursor.moveToFirst();
        int i=0;
         do{
             i++;
         } while (cursor.moveToNext());
        StringBuilder[] cars= new StringBuilder[i];
        for(int j=0;j<i;j++){
            cars[j]= new StringBuilder();
        }
        i=0;
        if(cursor.moveToFirst()){
            do {
                cars[i].append("num =");
                cars[i].append(cursor.getString(cursor.getColumnIndex("num")));
                cars[i].append(" quiz1 :");
                cars[i].append(cursor.getString(cursor.getColumnIndex("quiz1")));
                cars[i].append(" quiz2 :");
                cars[i].append(cursor.getString(cursor.getColumnIndex("quiz2")));
                cars[i].append(" quiz3 :");
                cars[i].append(cursor.getString(cursor.getColumnIndex("quiz3")));
                cars[i].append(" quiz4 :");
                cars[i].append(cursor.getString(cursor.getColumnIndex("quiz4")));
                cars[i].append(" quiz5 :");
                cars[i].append(cursor.getString(cursor.getColumnIndex("quiz5")));
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        String[] results= new String[cars.length];
        for(int j=0;j<cars.length;j++){
            results[j]= cars[j].toString();
        }
        listView= (ListView) view.findViewById(R.id.listView1);
        ArrayAdapter<String > adapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,results);
        listView.setAdapter(adapter);
        return view;
    }



}
