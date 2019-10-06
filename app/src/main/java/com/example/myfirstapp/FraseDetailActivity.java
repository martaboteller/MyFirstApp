package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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


        //Let's associate each source
        mTextId = findViewById(R.id.textId);
        mTextFrase = findViewById(R.id.textFrase);
        mTextAutor = findViewById(R.id.textAutor);

        //Recovering the phrase id
        fraseId = getIntent().getIntExtra(Constants.EXTRA_INTENT_FRASE_DETAIL,1);
        FraseDao fraseDao = new FraseDao();
        frase = fraseDao.getFraseById(fraseId);

        //Let's write down the information into the 2nd activity
        mTextId.setText(String.valueOf(frase.getId()));
        mTextFrase.setText((frase.getText()));
        mTextAutor.setText(frase.getAuthor());



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //Respond to the action bar's Up/Home button
            case android.R.id.home:
                //Going back to home
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
