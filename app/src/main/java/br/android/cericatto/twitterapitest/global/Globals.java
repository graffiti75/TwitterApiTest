package br.android.cericatto.twitterapitest.global;

/**
 * Globals.java.
 *
 * @author Rodrigo Cericatto
 * @since Sep 27, 2016
 */
public class Globals {

    //--------------------------------------------------
    // App Constants
    //--------------------------------------------------

    /**
     * Log tag.
     */

    public static final String TAG = "TwitterApiTest";

    /**
     * Twitter Keys.
     */

    public static final String TWITTER_KEY = "vE0bGLQs6vb3cgsdUVPsS6BTP";
    public static final String TWITTER_SECRET = "1qsVO9tPwbvfIHmySMhjMqyMRqsgXI2ASPQ9Kr6zsrWxlSbfv6";

    /**
     * Http Constants.
     */

    public static final String BASE_URL = "https://api.twitter.com/";
    public static final String URL_AUTHENTICATION = BASE_URL + "/oauth2/token";
    public static final String URL_TRENDING ="https://api.twitter.com/1.1/trends/place.json?id=1";

    public static final String APPLICATION_WWW_FORM = "application/x-www-form-urlencoded;charset=UTF-8";
    public static final String APPLICATION_JSON = "application/json";
    public static final String CLIENT_CREDENTIALS = "grant_type=client_credentials";

    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";

    /**
     * Json Keys.
     */

    public static final String PREFIX = "Basic ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "Content-Type";

    public static final String TOKEN_TYPE = "token_type";
    public static final String ACCESS_TOKEN = "access_token";

    public static final String NAME = "name";
    public static final String TRENDS = "trends";

    /**
     * Intent Extras.
     */

    public static final String KEYWORD_EXTRA = "keyword_extra";

    /**
     * Fragment Tags.
     */

    public static final String TIMELINE_FRAGMENT_TAG = "timeline_fragment";
}