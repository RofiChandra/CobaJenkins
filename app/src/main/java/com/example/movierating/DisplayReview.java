package com.example.movierating;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.widget.RatingBar;
import android.widget.TextView;
import android.os.Bundle;

public class DisplayReview extends AppCompatActivity {

    private TextView mMovieName;
    private TextView mMovieYear;
    private TextView mMovieDuraton;
    private TextView mMovieReview;
    private TextView mMovieDirector;
    private TextView mMovieStarring;
    private RatingBar mMovieRating;
    private TextView mMovieGenres;
    private String name;
    private String genre;
    private String year;
    private String duration;
    private String review;
    private String starcast;
    private String director;
    private double rating;
    private List<String> mReviewData = new ArrayList<String>();
    private MovieRatingDataHelper mDataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_review);
        mMovieName = (TextView) findViewById(R.id.textView2);
        mMovieGenres = (TextView) findViewById(R.id.textView4);
        mMovieYear = (TextView) findViewById(R.id.textView6);
        mMovieDuraton = (TextView) findViewById(R.id.textView8);
        mMovieRating = (RatingBar) findViewById(R.id.ratingBar1);
        mMovieReview = (TextView) findViewById(R.id.textView10);
        mMovieDirector = (TextView) findViewById(R.id.textView12);
        mMovieStarring = (TextView) findViewById(R.id.textView14);
        Intent mIntent = getIntent();
        Bundle mBundle = mIntent.getExtras();
        int id = mBundle.getInt("id");
        this.mDataHelper = new MovieRatingDataHelper(this);
        mReviewData.addAll(this.mDataHelper.selectById(id+1));
        breakString(mReviewData.toString());
        mMovieName.setText(name);
        mMovieGenres.setText(genre);
        mMovieYear.setText(year);
        mMovieDuraton.setText(duration);
        mMovieRating.setRating((float) rating);
        mMovieReview.setText(review);
        mMovieDirector.setText(director);
        mMovieStarring.setText(starcast);
    }

    public void breakString(String str){
        name=str.substring(1,str.indexOf(";"));
        str= str.substring(name.length()+2, str.length());
        genre = str.substring(0,str.indexOf(";"));
        str= str.substring(genre.length()+1, str.length());
        year = str.substring(0,str.indexOf(";"));
        str= str.substring(year.length()+1, str.length());
        duration = str.substring(0,str.indexOf(";"));
        str= str.substring(duration.length()+1, str.length());
        String str1 = str.substring(0,str.indexOf(";"));
        rating=Double.valueOf(str1);
        str= str.substring(str1.length()+1, str.length());
        review = str.substring(0,str.indexOf(";"));
        str= str.substring(review.length()+1, str.length());
        starcast = str.substring(0,str.indexOf(";"));
        str= str.substring(starcast.length()+1, str.length()-1);
        director = str;
    }
}