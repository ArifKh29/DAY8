package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILENAME = "file.txt";
    Button btnBuat, btnHapus, btnUbah, btnBaca;
    TextView txtIsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBuat = findViewById(R.id.btnBuat);
        btnHapus = findViewById(R.id.btnHapus);
        btnBaca = findViewById(R.id.btnBaca);
        btnUbah = findViewById(R.id.btnUbah);
        txtIsi = findViewById(R.id.txtIsi);

        btnBuat.setOnClickListener(this);
        btnHapus.setOnClickListener(this);
        btnBaca.setOnClickListener(this);
        btnUbah.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        jalankanPerintah(view.getId());
    }

    void jalankanPerintah(int id){
        switch(id){
            case R.id.btnBuat:
                buatFile();
                break;
            case R.id.btnHapus:
                hapusfile();
                break;
            case R.id.btnBaca:
                bacaFile();
                break;
            case R.id.btnUbah:
                ubahFile();
                break;
        }
    }

    void ubahFile(){
        String ubah = "ini adalah update dari data file";
        File file = new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void bacaFile(){
        File lok = getFilesDir();
        File file = new File(lok, FILENAME);

        if(file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();
                while (line!=null){
                    text.append(line);
                    line = bufferedReader.readLine();

                }

                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            txtIsi.setText(text.toString());
        }
    }

    void buatFile(){
        String isiFile = "Ini adalah isi file nya";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;

        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void hapusfile(){
        File file = new File(getFilesDir(), FILENAME);
        if(file.exists()){
            file.delete();
        }
    }
}
