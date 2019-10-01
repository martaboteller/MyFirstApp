package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.model.Constants;
import com.example.myfirstapp.model.Frase;
import com.example.myfirstapp.model.FraseDao;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button mConsellsButton;
    private Button mFrasesButton;
    private TextView mTextFrases;
    private List<Frase> mFrasesFetes;
    private List<Frase> mCitesCelebres;
    private Random random;
    private Frase mFrase;
    private ImageView imageMore;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (mFrase != null)  {
            savedInstanceState.putInt(Constants.FRASE_KEY, mFrase.getId());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relating layout with created instances
        mConsellsButton = findViewById(R.id.button_consells);
        mFrasesButton = findViewById((R.id.button_frases));
        mTextFrases = findViewById(R.id.textFrases);
        imageMore = findViewById(R.id.imageMore);
        imageMore.setVisibility(View.INVISIBLE); //hiding button "more"


        /*Creating a toast that shows a message when pressing the buttons
        mConsellsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                Toast.makeText(MainActivity.this, "Has premut el botó de consells", Toast.LENGTH_SHORT).show();
            }
        });

        mFrasesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Toast.makeText(MainActivity.this, "Has premut el botó de les frases", Toast.LENGTH_SHORT).show();
            }

        });*/


        //Getting the phrase's list
        final FraseDao fraseDao = new FraseDao();
        mFrasesFetes = fraseDao.getFrases();
        mCitesCelebres = fraseDao.getConsells();
        random = new Random();


        //Let's always save an state, for example with the first id = 1
        if (savedInstanceState != null) {
            mFrase = fraseDao.getFraseById(savedInstanceState.getInt(Constants.FRASE_KEY, 1));
            //fraseDao.setFraseText(mTextFrases, mFrase.getText());
        }

        //Creating a toast that will show a message and at the same time will write down the phrase on the TextView field
        //Saving the text into the mFrase variable
        mConsellsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Has premut el botó dels consells", Toast.LENGTH_SHORT).show();
                mFrase = mCitesCelebres.get(random.nextInt((mCitesCelebres.size())));
                fraseDao.setFraseText(mTextFrases, mFrase.getText());
                imageMore.setVisibility(View.VISIBLE);
            }
        });

        mFrasesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Has premut el botó de les frases fetes", Toast.LENGTH_SHORT).show();
                mFrase = mFrasesFetes.get(random.nextInt((mFrasesFetes.size())));
                fraseDao.setFraseText(mTextFrases, mFrase.getText());
                imageMore.setVisibility(View.VISIBLE);
            }
        });


        //Let's call the second activity with:
        //public Intent (Context packageContext, class <?> class)
        imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(MainActivity.this, FraseDetailActivity.class);
                if (mFrase != null) {
                    //Passing the Id in order to recover all the phrase info
                    intent.putExtra(Constants.EXTRA_INTENT_FRASE_DETAIL, mFrase.getId());
                    startActivity(intent);

                }
            }

        });

        //This listener pointed to the TextView field
        /*mTextFrases.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent intent = new Intent(MainActivity.this, FraseDetailActivity.class);
                //Passing the Id in order to recover all the phrase info
                intent.putExtra(Constants.EXTRA_INTENT_FRASE_DETAIL, mFrase.getId());
                startActivity(intent);
            }


        });*/


    }




}
