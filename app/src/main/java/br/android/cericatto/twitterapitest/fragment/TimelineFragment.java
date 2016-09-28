package br.android.cericatto.twitterapitest.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

import br.android.cericatto.twitterapitest.R;
import br.android.cericatto.twitterapitest.TwitterTimelineAsyncTask;
import br.android.cericatto.twitterapitest.global.Globals;

/**
 * TimelineFragment.java.
 *
 * @author Rodrigo Cericatto
 * @since Sep 27, 2016
 */
public class TimelineFragment extends ListFragment {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    /**
     * Keyword.
     */

    private String mKeyword;

    //--------------------------------------------------
    // Fragment Life Cycle
    //--------------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_timeline, container, false);
        getExtras();
        embeddedTimeline();

        return root;
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private void getExtras() {
        if (getArguments().containsKey(Globals.KEYWORD_EXTRA)) {
            mKeyword = getArguments().getString(Globals.KEYWORD_EXTRA);
        }
    }

    private void embeddedTimeline() {
        // Gets Twitter info.
        TwitterTimelineAsyncTask task = new TwitterTimelineAsyncTask() {
            @Override
            protected void onPostExecute(TweetTimelineListAdapter adapter) {
                setListAdapter(adapter);
            }
        };
        task.execute(getActivity(), mKeyword);
    }
}