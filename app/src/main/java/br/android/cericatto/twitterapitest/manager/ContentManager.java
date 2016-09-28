package br.android.cericatto.twitterapitest.manager;

import java.util.ArrayList;
import java.util.List;

/**
 * ContentManager.java class.
 *
 * @author Rodrigo Cericatto
 * @since September 28, 2016
 */
public class ContentManager {

    //----------------------------------------------
    // Statics
    //----------------------------------------------

    private static ContentManager sInstance = null;

    //----------------------------------------------
    // Attributes
    //----------------------------------------------

    private List<String> mHistoryList = new ArrayList<>();

    //----------------------------------------------
    // Constructor
    //----------------------------------------------

    /**
     * Public constructor.
     */
    public ContentManager() {}

    /**
     * @return The singleton instance of ContentManager.
     */
    public static ContentManager getInstance() {
        if (sInstance == null) {
            sInstance = new ContentManager();
        }
        return sInstance;
    }

    //----------------------------------------------
    // Methods
    //----------------------------------------------

    public List<String> getHistoryList() {
        return mHistoryList;
    }

    public void addToHistoryList(String keyword) {
        mHistoryList.add(keyword);
    }
}