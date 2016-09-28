package br.android.cericatto.twitterapitest.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.android.cericatto.twitterapitest.R;

/**
 * HistoryAdapter.java.
 *
 * @author Rodrigo Cericatto
 * @since Sep 28, 2016
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private Activity mActivity;
    private List<String> mItems;

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------

    public HistoryAdapter(Activity context, List<String> items) {
        mActivity = context;
        mItems = items;
    }

    //--------------------------------------------------
    // Adapter Methods
    //--------------------------------------------------

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String item = mItems.get(position);
        holder.textView.setText(item);
    }

    @Override
    public int getItemCount() {
        if (mItems != null && mItems.size() > 0) {
            return mItems.size();
        }
        return 0;
    }

    //--------------------------------------------------
    // Other Methods
    //--------------------------------------------------

    public void setFilter(List<String> list) {
        mItems = new ArrayList<>();
        mItems.addAll(list);
        notifyDataSetChanged();
    }

    //--------------------------------------------------
    // View Holder
    //--------------------------------------------------

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView)view.findViewById(R.id.id_adapter_history__text_view);
        }
    }
}