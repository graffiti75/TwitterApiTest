package br.android.cericatto.twitterapitest.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.android.cericatto.twitterapitest.R;
import br.android.cericatto.twitterapitest.fragment.TimelineFragment;
import br.android.cericatto.twitterapitest.global.Globals;
import br.android.cericatto.twitterapitest.global.Navigation;

/**
 * MainActivity.java.
 *
 * @author Rodrigo Cericatto
 * @since Sep 26, 2016
 */
public class TimelineActivity extends AppCompatActivity {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    /**
     * Keyword.
     */

    private String mKeyword;

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        initToolbar(true);
        getExtras();
        callFragment();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Navigation.animate(this, Navigation.Animation.BACK);
    }

    //--------------------------------------------------
    // Menu
    //--------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Integer id = menuItem.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return false;
    }

    //--------------------------------------------------
    // Private Methods
    //--------------------------------------------------

    private void initToolbar(boolean homeEnabled) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(homeEnabled);
            toolbar.setTitle(R.string.activity_timeline__title);
        }
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mKeyword = extras.getString(Globals.KEYWORD_EXTRA);
        }
    }

    private void callFragment() {
        Bundle arguments = new Bundle();
        arguments.putString(Globals.KEYWORD_EXTRA, mKeyword);

        TimelineFragment detailFragment = new TimelineFragment();
        detailFragment.setArguments(arguments);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.id_activity_timeline__container, detailFragment, Globals.TIMELINE_FRAGMENT_TAG);
        fragmentTransaction.commit();
    }
}