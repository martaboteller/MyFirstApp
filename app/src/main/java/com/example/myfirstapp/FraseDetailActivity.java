package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myfirstapp.model.Constants;
import com.example.myfirstapp.model.Frase;
import com.example.myfirstapp.model.FraseDao;

public class FraseDetailActivity extends AppCompatActivity {

    private TextView mTextId, mTextFrase, mTextAutor;
    private int fraseId;
    private Frase frase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frase_detail);


        //Associem cada recurs
        mTextId = findViewById(R.id.textId);
        mTextFrase = findViewById(R.id.textFrase);
        mTextAutor = findViewById(R.id.textAutor);

        //Recuperem l'Id i la frase
        fraseId = getIntent().getIntExtra(Constants.EXTRA_INTENT_FRASE_DETAIL,1);
        FraseDao fraseDao = new FraseDao();
        frase = fraseDao.getFraseById(fraseId);

        //Posem la informacio a la 2a activity
        mTextId.setText(String.valueOf(frase.getId()));
        mTextFrase.setText((frase.getText()));
        mTextAutor.setText(frase.getAuthor());

    }
}
