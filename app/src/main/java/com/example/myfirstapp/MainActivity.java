package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(Constants.FRASE_KEY, mFrase.getId());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relacionem les instancies creades amb el layout
        mConsellsButton = findViewById(R.id.button_consells);
        mFrasesButton = findViewById((R.id.button_frases));
        mTextFrases = findViewById(R.id.textFrases);

        /*Creem un toast que mostri un missatge quan presionem els botons
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


        //Obtenim els llistats de frases
        final FraseDao fraseDao = new FraseDao();
        mFrasesFetes = fraseDao.getFrases();
        mCitesCelebres = fraseDao.getConsells();
        random = new Random();


        //Guardem sempre un "estat" amb id per defecte, 1
        if (savedInstanceState != null) {
            mFrase = fraseDao.getFraseById(savedInstanceState.getInt(Constants.FRASE_KEY, 1));
            fraseDao.setFraseText(mTextFrases, mFrase.getText());
        }

        //Creem un toast que mostri un missatge i al mateix temps escrivim el text al TextField
        //I guardem el text a la variable mFrase
        mConsellsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Has premut el botó dels consells", Toast.LENGTH_SHORT).show();
                mFrase = mCitesCelebres.get(random.nextInt((mCitesCelebres.size())));
                fraseDao.setFraseText(mTextFrases, mFrase.getText());
            }
        });

        mFrasesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Has premut el botó de les frases fetes", Toast.LENGTH_SHORT).show();
                mFrase = mFrasesFetes.get(random.nextInt((mFrasesFetes.size())));
                fraseDao.setFraseText(mTextFrases, mFrase.getText());
            }
        });


        //Cridem a la segona Activity
        //public Intent (Context packageContext, class <?> class)
        mTextFrases.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent intent = new Intent(MainActivity.this, FraseDetailActivity.class);
                //Passem també l'Id per poder recuperar la frase
                intent.putExtra(Constants.EXTRA_INTENT_FRASE_DETAIL, mFrase.getId());
                startActivity(intent);
            }
        });


    }




}
