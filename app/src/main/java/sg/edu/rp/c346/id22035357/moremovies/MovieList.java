package sg.edu.rp.c346.id22035357.moremovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MovieList extends AppCompatActivity {
    ListView lvMovies;
    ToggleButton toggleBtn;
    ArrayList<Movie> movieList;
    CustomAdapter adapter;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        lvMovies = findViewById(R.id.lvMovies);
        toggleBtn = findViewById(R.id.btnToggle);


        DBHelper dbh = new DBHelper(MovieList.this);
        movieList = new ArrayList<>();
        adapter = new CustomAdapter(this, R.layout.row, movieList);
        lvMovies.setAdapter(adapter);
        movieList.addAll(dbh.getMovies());
        adapter.notifyDataSetChanged();


        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie clickedMovie = movieList.get(position);
                Intent intent = new Intent(MovieList.this, EditMovies.class);
                intent.putExtra("movie", clickedMovie);
                startActivity(intent);
            }
        });

        toggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieList.clear();
                if (toggleBtn.isChecked()) {
                    movieList.addAll(dbh.getMovies());
                } else {
                    movieList.addAll(dbh.getMoviesByRating("PG13"));
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        intent = new Intent(MovieList.this, EditMovies.class);
        intent.putExtra("addMovies", true);
        startActivity(intent);
        return true;

    }
    private void refreshListView() {
        DBHelper dbh = new DBHelper(MovieList.this);
        movieList.clear();
        movieList.addAll(dbh.getMovies());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }
}

