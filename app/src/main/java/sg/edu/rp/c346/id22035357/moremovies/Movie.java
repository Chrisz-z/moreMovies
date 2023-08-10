package sg.edu.rp.c346.id22035357.moremovies;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Movie implements Parcelable {
    private int id;
    private String title;
    private String genre;
    private int years;
    private String rating;


    public Movie(int id, String title, String genre, int years, String rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.years = years;
        this.rating = rating;
    }

    protected Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        genre = in.readString();
        years = in.readInt();
        rating = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYears() {
        return years;
    }

    public String getRating() {
        return rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String Genre) {
        this.genre = Genre;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @NonNull
    @Override
    public String toString() {
        return id + "\n" + title + "\n" + genre + "\n" + years + "\n" + rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(genre);
        dest.writeInt(years);
        dest.writeString(rating);
    }
}