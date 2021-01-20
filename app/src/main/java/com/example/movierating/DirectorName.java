package com.example.movierating;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.os.Bundle;

public class DirectorName extends AppCompatActivity {

    private AutoCompleteTextView mDirectorName;
    private Button mFinishButton;
    static final String[] mDirectorNameData = new String[] {
            "David Lynch","Martin Scorsese","Joel and Ethan Coen",
            "Steven Soderbergh","Terrence Malick","Abbas Kiarostami",
            "Errol Morris","Hayao Miyazaki","David Cronenberg",
            "Terence Davies","Lukas Moodysson","Lynne Ramsay",
            "Bela Tarr","Wong Kar-wai", "Pedro Almodovar",
            "Todd Haynes","Quentin Tarantino","Tsai Ming-Liang",
            "Aki Kaurismaki","Michael Winterbottom","Paul Thomas Anderson",
            "Michael Haneke","Walter Salles","Alexander Payne",
            "Spike Jonze","Aleksandr Sokurov","Ang Lee",
            "Michael Moore","Wes Anderson","Takeshi Kitano",
            "Richard Linklater","Gaspar No�","Pavel Pawlikowski",
            "David O Russell","Larry and Andy Wachowski",
            "Samira Makhmalbaf", "Lars von Trier","Takashi Mike",
            "David Fincher","Gus Van Sant"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_director_name);
        mDirectorName = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        mFinishButton = (Button) findViewById(R.id.button1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, mDirectorNameData);
        mDirectorName.setAdapter(adapter);
        mFinishButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent mIntent = new Intent(DirectorName.this, MainActivity.class);
                /* Create a new bundle object */
                Bundle mBundle = new Bundle();
                mBundle.putString("key",
                        mDirectorName.getText().toString());
                mIntent.putExtras(mBundle);
                setResult(Activity.RESULT_OK, mIntent);
                finish();
            }
        });
    }
}