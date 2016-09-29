package br.android.cericatto.twitterapitest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.android.cericatto.twitterapitest.R;
import br.android.cericatto.twitterapitest.adapter.HistoryAdapter;
import br.android.cericatto.twitterapitest.api.TrendsService;
import br.android.cericatto.twitterapitest.global.Globals;
import br.android.cericatto.twitterapitest.global.Navigation;
import br.android.cericatto.twitterapitest.global.Utils;
import br.android.cericatto.twitterapitest.tasks.TrendsAsyncTask;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * TrendsActivity.java.
 *
 * @author Rodrigo Cericatto
 * @since Sep 28, 2016
 */
public class TrendsActivity extends AppCompatActivity {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    /**
     * Context.
     */

    private Activity mActivity = TrendsActivity.this;

    //--------------------------------------------------
    // Activity Life Cycle
    //--------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends);

        initToolbar(true);
        getTrendTopics();
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
    // Main Methods
    //--------------------------------------------------

    private void initToolbar(boolean homeEnabled) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(homeEnabled);
            getSupportActionBar().setTitle(R.string.activity_trends__title);
        }
    }

    private void getTrendTopics() {
        TrendsAsyncTask task = new TrendsAsyncTask() {
            @Override
            protected void onPostExecute(RequestInterceptor requestInterceptor) {
                getTrendsFromAPI(requestInterceptor);
            }
        };
        task.execute();
    }

    //--------------------------------------------------
    // List Methods
    //--------------------------------------------------

    private void setRecyclerView(List<String> list) {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.id_activity_trends__recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        HistoryAdapter adapter = new HistoryAdapter(mActivity, list);
        if (list != null && list.size() >= 1) {
            recyclerView.setAdapter(adapter);
        }
    }

    private List<String> getTrendList(String json) {
        JSONArray jsonArray;
        List<String> list = new ArrayList<>();
        try {
            jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray trendsJsonArray = jsonObject.getJSONArray(Globals.TRENDS);
            for (int i = 0; i < trendsJsonArray.length(); i++) {
                JSONObject trendsJsonObject = trendsJsonArray.getJSONObject(i);
                String name = Utils.parseString(trendsJsonObject, Globals.NAME);
                list.add(name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //--------------------------------------------------
    // Retrofit Methods
    //--------------------------------------------------

    private void getTrendsFromAPI(RequestInterceptor requestInterceptor) {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Globals.BASE_URL)
            .setRequestInterceptor(requestInterceptor).build();
        TrendsService service = restAdapter.create(TrendsService.class);
        service.getTrends(new Callback<Response>() {
            @Override
            public void failure(RetrofitError error) {
                Log.e(Globals.TAG, "Trends error: " + Log.getStackTraceString(error));
            }

            @Override
            public void success(Response result, Response response) {
                String json = new String(((TypedByteArray) response.getBody()).getBytes());
                List<String> list = getTrendList(json);
                setRecyclerView(list);
            }
        });
    }
}