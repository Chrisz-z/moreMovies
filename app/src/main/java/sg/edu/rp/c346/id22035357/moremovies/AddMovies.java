package sg.edu.rp.c346.id22035357.moremovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddMovies extends AppCompatActivity {

    EditText etTitle, etGenre,etYear ;
    Spinner spnRating;
    Button btnInsert,btnCancel;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        etTitle = findViewById(R.id.etMovie);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        spnRating = findViewById(R.id.spnRating);
        btnInsert = findViewById(R.id.btnInsert);
        btnCancel = findViewById(R.id.btnCancel);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.movie_ratings, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRating.setAdapter(adapter);



        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(AddMovies.this);

                // Insert a song
                db.insertMovies(etTitle.getText().toString(), etGenre.getText().toString(), Integer.parseInt(etYear.getText().toString())
                        , spnRating.getSelectedItem().toString());
                Intent intent = new Intent(AddMovies.this, MovieList.class);
                intent.putExtra("refresh", true);
                startActivity(intent);
                finish();
            }


        });

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}