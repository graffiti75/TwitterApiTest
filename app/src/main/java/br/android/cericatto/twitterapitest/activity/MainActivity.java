package br.android.cericatto.twitterapitest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import br.android.cericatto.twitterapitest.R;
import br.android.cericatto.twitterapitest.global.Globals;
import br.android.cericatto.twitterapitest.global.Utils;
import io.fabric.sdk.android.Fabric;

/**
 * MainActivity.java.
 *
 * @author Rodrigo Cericatto
 * @since Sep 26, 2016
 */
public class MainActivity extends AppCompatActivity {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    /**
     * Context.
     */

    private Activity mActivity = MainActivity.this;

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTwitterApi();
        initToolbar(false);
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    private void initTwitterApi() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(Globals.TWITTER_KEY, Globals.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
    }

    private void initToolbar(boolean homeEnabled) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(homeEnabled);
            getSupportActionBar().setTitle(R.string.activity_main__title);
        }
    }

    public void embeddedTimeline(View view) {
        // Checks keyword.
        EditText editText = (EditText)findViewById(R.id.activity_main__edit_text);
        String keyword = editText.getText().toString();
        if (Utils.isEmpty(keyword)) {
            Toast.makeText(mActivity, R.string.activity_main__empty_keyword, Toast.LENGTH_LONG).show();
        } else {
            // Calls Timeline.
            Utils.startActivityExtras(mActivity, TimelineActivity.class, Globals.KEYWORD_EXTRA, keyword);
        }
    }
}