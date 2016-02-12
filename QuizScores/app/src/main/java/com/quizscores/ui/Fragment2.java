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
import android.widget.EditText;
import android.widget.TextView;

import com.quizscores.R;
import com.quizscores.util.DatabaseConnector;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {

    private TextView highTextView1;
    private TextView highTextView2;
    private TextView highTextView3;
    private TextView highTextView4;
    private TextView highTextView5;
    private TextView lowTextView1;
    private TextView lowTextView2;
    private TextView lowTextView3;
    private TextView lowTextView4;
    private TextView lowTextView5;
    private TextView avgTextView1;
    private TextView avgTextView2;
    private TextView avgTextView3;
    private TextView avgTextView4;
    private TextView avgTextView5;

    public Fragment2() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_fragment2, container, false);
        highTextView1= (TextView) view.findViewById(R.id.highTextView1);
        highTextView2= (TextView) view.findViewById(R.id.highTextView2);
        highTextView3= (TextView) view.findViewById(R.id.highTextView3);
        highTextView4= (TextView) view.findViewById(R.id.highTextView4);
        highTextView5= (TextView) view.findViewById(R.id.highTextView5);
        lowTextView1= (TextView) view.findViewById(R.id.lowTextView1);
        lowTextView2= (TextView) view.findViewById(R.id.lowTextView2);
        lowTextView3= (TextView) view.findViewById(R.id.lowTextView3);
        lowTextView4= (TextView) view.findViewById(R.id.lowTextView4);
        lowTextView5= (TextView) view.findViewById(R.id.lowTextView5);
        avgTextView1= (TextView) view.findViewById(R.id.avgTextView1);
        avgTextView2= (TextView) view.findViewById(R.id.avgTextView2);
        avgTextView3= (TextView) view.findViewById(R.id.avgTextView3);
        avgTextView4= (TextView) view.findViewById(R.id.avgTextView4);
        avgTextView5= (TextView) view.findViewById(R.id.avgTextView5);

        DatabaseConnector db= new DatabaseConnector(getActivity());
        db.open();
        Cursor cursor= db.getAll();
        cursor.moveToFirst();
        int num=0;
        do{
            num++;
        } while (cursor.moveToNext());
        double[][] scores= new double[num][5];
        cursor.moveToFirst();
        for(int i=0;i<num;i++){
            scores[i][0]= Double.parseDouble(cursor.getString(cursor.getColumnIndex("quiz1")));
            scores[i][1]= Double.parseDouble(cursor.getString(cursor.getColumnIndex("quiz2")));
            scores[i][2]= Double.parseDouble(cursor.getString(cursor.getColumnIndex("quiz3")));
            scores[i][3]= Double.parseDouble(cursor.getString(cursor.getColumnIndex("quiz4")));
            scores[i][4]= Double.parseDouble(cursor.getString(cursor.getColumnIndex("quiz5")));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        double[] high= {0,0,0,0,0};
        double[] low={100,100,100,100,100};
        double[] avg= new double[5];
        for(int i=0;i<num;i++){
            for(int j=0;j<5;j++){
                if(scores[i][j]>high[j])
                    high[j]= scores[i][j];
                if(scores[i][j]<low[j])
                    low[j]= scores[i][j];
                avg[j]+= scores[i][j];
            }
        }
        for(int j=0;j<5;j++){
            avg[j]= avg[j]/num;
        }
        highTextView1.setText(((Double) high[0]).toString());
        highTextView2.setText(((Double) high[1]).toString());
        highTextView3.setText(((Double) high[2]).toString());
        highTextView4.setText(((Double) high[3]).toString());
        highTextView5.setText(((Double) high[4]).toString());
        lowTextView1.setText(((Double)low[0]).toString());
        lowTextView2.setText(((Double)low[1]).toString());
        lowTextView3.setText(((Double) low[2]).toString());
        lowTextView4.setText(((Double) low[3]).toString());
        lowTextView5.setText(((Double) low[4]).toString());
        avgTextView1.setText(String.format("%.02f", avg[0]));
        avgTextView2.setText(String.format("%.02f", avg[1]));
        avgTextView3.setText(String.format("%.02f", avg[2]));
        avgTextView4.setText(String.format("%.02f", avg[3]));
        avgTextView5.setText(String.format("%.02f", avg[4]));





        return view;
    }

}
