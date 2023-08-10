package sg.edu.rp.c346.id22035357.moremovies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class EditMovies extends AppCompatActivity {

    EditText etMovieID,etTitle, etGenre,etYear ;
    Spinner spnRating;
    Button btnUpdate,btnDelete,btnCancel;
    TextView tvID;
    ArrayAdapter<CharSequence> adapter;


    private void update(Intent i) {
        Movie obj = i.getParcelableExtra("movie");
        etMovieID.setText(String.valueOf(obj.getId()));
        etTitle.setText(obj.getTitle());
        etGenre.setText(obj.getGenre());
        etYear.setText(String.valueOf(obj.getYears()));
        etMovieID.setEnabled(false);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mybuilder = new AlertDialog.Builder(EditMovies.this);
                mybuilder.setTitle("Danger");
                mybuilder.setMessage("Are you sure you want to delete the Movie " + obj.getTitle());
                mybuilder.setCancelable(false);


                mybuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(EditMovies.this);
                        dbh.deleteMovie(obj.getId());

                        finish();
                    }
                });

                mybuilder.setNegativeButton("CANCEL", null);
                AlertDialog myDialog = mybuilder.create();
                myDialog.show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditMovies.this);
                obj.setTitle(etTitle.getText().toString());
                obj.setGenre(etGenre.getText().toString());
                obj.setYears(Integer.parseInt(etYear.getText().toString()));
                obj.setRating((spnRating.getSelectedItem().toString()));
                dbh.updateMovie(obj);
                dbh.close();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mybuilder = new AlertDialog.Builder(EditMovies.this);
                mybuilder.setTitle("Danger");
                mybuilder.setMessage("Are you sure you want to discard the changes");
                mybuilder.setCancelable(false);


                mybuilder.setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(EditMovies.this);
                        finish();

                    }
                });

                mybuilder.setNegativeButton("DO NOT DISCARD", null);
                AlertDialog myDialog = mybuilder.create();
                myDialog.show();
            }

        });
    }

    private void insert(){
        tvID.setVisibility(View.GONE);
        etMovieID.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
        btnUpdate.setText("Insert");

        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(EditMovies.this);

                // Insert a song
                db.insertMovies(etTitle.getText().toString(), etGenre.getText().toString(), Integer.parseInt(etYear.getText().toString())
                        , spnRating.getSelectedItem().toString());
                Intent intent = new Intent(EditMovies.this, MovieList.class);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        etMovieID = findViewById(R.id.etmovieID);
        etTitle = findViewById(R.id.etMovie);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        spnRating = findViewById(R.id.spnRating);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        tvID = findViewById(R.id.tvID);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.movie_ratings, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRating.setAdapter(adapter);


        Intent i = getIntent();
        if (i != null) {
            if (i.hasExtra("movie")) {
                update(i);


            }else{
                insert();
            }
        }
    }
}