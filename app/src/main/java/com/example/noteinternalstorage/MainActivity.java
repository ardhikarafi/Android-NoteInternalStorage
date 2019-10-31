package com.example.noteinternalstorage;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class MainActivity extends AppCompatActivity {
    EditText txtEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEditor = (EditText)findViewById(R.id.editNotes);
    }

    public void saveclicked(View view) {
        try{
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput("STORETEXT",0));
            out.write(txtEditor.getText().toString());
            out.close();
            Toast.makeText(this,"The contents are saved in the file.",Toast.LENGTH_LONG).show();
        }catch (Throwable t){
            Toast.makeText(this,"Exception:" +t.toString(),Toast.LENGTH_LONG).show();
        }
    }
    public void read(View view) {
        try{
            InputStream in = openFileInput("STORETEXT");
            if(in != null){
                InputStreamReader tmp = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(tmp);
                String str;
                StringBuilder buf = new StringBuilder();
                while ((str = reader.readLine()) != null){
                    buf.append(str+"n");
                }
                in.close();
                txtEditor.setText(buf.toString());
            }
        }
        catch (java.io.FileNotFoundException e){
        }catch (Throwable t){
            Toast.makeText(this,"Exception: "+t.toString(), Toast.LENGTH_LONG).show();
        }
    }
    public void savetosd(View view) {
        try{
            File myfile = new File("/sdcard/mysdfile.txt");
            myfile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myfile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(txtEditor.getText());
            myOutWriter.close();
            fOut.close();
            Toast.makeText(getBaseContext(),"Done Writing SD 'mysdFIle.txt'",Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(getBaseContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}

