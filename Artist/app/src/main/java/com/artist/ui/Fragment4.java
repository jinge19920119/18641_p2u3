package com.artist.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.artist.R;

import com.artist.util.DatabaseConnector;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment4 extends Fragment {

    private EditText emailText;
    private Button saveButton;
    private Button sendButton;
    private Button showButton;
    private ListView emailList;
    private String email;

    public Fragment4() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_fragment4, container, false);
        emailText= (EditText) view.findViewById(R.id.emailEditText);
        saveButton= (Button) view.findViewById(R.id.saveButton);
        sendButton= (Button) view.findViewById(R.id.sendButton);
        showButton= (Button) view.findViewById(R.id.showButton);


        /*
        method used when clicking the send button
         */
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email= emailText.getText().toString();
                if(email.length()==0){
                    Toast.makeText(v.getContext(),"Error, please type again!", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Email From Mi Yang!");
                intent.putExtra(Intent.EXTRA_TEXT, "Do you like her?");
                intent.setType("message/rfc822");
                startActivity(intent);
            }
        });

        /*
        method used when clicking the save button
         */
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email= emailText.getText().toString();
                DatabaseConnector databaseConnector= new DatabaseConnector(v.getContext());
                databaseConnector.open();
                databaseConnector.insert(email);
                Toast.makeText(v.getContext(),"Save to database successfully!", Toast.LENGTH_SHORT).show();
                databaseConnector.close();
            }
        });

        /*
        method used when clicking the show button
         */
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseConnector databaseConnector= new DatabaseConnector(v.getContext());
                databaseConnector.open();
                Cursor cursor= databaseConnector.getAll();
                cursor.moveToFirst();
                int num=0;
                do{
                    num++;
                } while (cursor.moveToNext());
                String[] list= new String[num];
                int i=0;
                cursor.moveToFirst();
                do{
                    list[i]= cursor.getString(cursor.getColumnIndex("address"));
                    i++;
                } while(cursor.moveToNext());
                cursor.close();
                databaseConnector.close();
                emailList= (ListView) view.findViewById(R.id.emailListView);
                ArrayAdapter<String> adapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list);
                emailList.setAdapter(adapter);
            }
        });

        return view;
    }



}
