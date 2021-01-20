package com.example.movierating;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ListActivity;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Bundle;

public class ReviewList extends ListActivity {
    private ListView mRatingList;
    private List<String> mReviewData = new ArrayList<String>();
    private MovieRatingDataHelper mDataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);
        mRatingList = (ListView)findViewById(android.R.id.list);
        this.mDataHelper = new MovieRatingDataHelper(this);
        mReviewData.addAll(this.mDataHelper.selectName());
        ArrayAdapter<String> mReviewList = new
                ArrayAdapter<String>(this, R.layout.listentry, mReviewData);
        setListAdapter(mReviewList);
        mRatingList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int
                    arg2, long arg3) {
                // TODO Auto-generated method stub
                /* Create an intent to invoke the Display Review activity */
                Intent mReviewListIntent = new
                        Intent(ReviewList.this, DisplayReview.class);
                Bundle mBundle = new Bundle();
                mBundle.putInt("id", arg2);
                mReviewListIntent.putExtras(mBundle);
                startActivity(mReviewListIntent);
            }
        });
    }
}