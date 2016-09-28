package br.android.cericatto.twitterapitest.global;

import android.app.Activity;

import br.android.cericatto.twitterapitest.R;

/**
 * Navigation.java.
 *
 * @author Rodrigo Cericatto
 * @since September 27, 2016
 */
public class Navigation {

    //--------------------------------------------------
    // Enum
    //--------------------------------------------------

    public enum Animation {
        GO,
        BACK
    }

    //--------------------------------------------------
    // Methods
    //--------------------------------------------------

    public static void animate(Activity activity, Animation animation) {
        if (animation == Animation.GO) {
            activity.overridePendingTransition(R.anim.open_next, R.anim.close_previous);
        } else {
            activity.overridePendingTransition(R.anim.open_previous, R.anim.close_next);
            activity.finish();
        }
    }
}