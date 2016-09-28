package br.android.cericatto.twitterapitest;

import android.app.Activity;
import android.os.AsyncTask;

import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

/**
 * TwitterTimelineAsyncTask.java.
 *
 * @author Rodrigo Cericatto
 * @since Sep 27, 2016
 */
public class TwitterTimelineAsyncTask extends AsyncTask<Object, Void, TweetTimelineListAdapter> {

    //--------------------------------------------------
    // Async Task
    //--------------------------------------------------

    @Override
    protected TweetTimelineListAdapter doInBackground(Object... params) {
        Activity activity = (Activity) params[0];
        String keyword = (String)params[1];

        SearchTimeline searchTimeline = new SearchTimeline.Builder().query(keyword).build();
        TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(activity)
            .setTimeline(searchTimeline).build();
        return adapter;
    }
}