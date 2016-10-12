package nin.app.cado.constant;

/**
 * Created by ninhn on 9/27/2016.
 */

public class ServiceConstant {
    public static final int FLAG_LOAD_MATCH_NEW = 0;
    public static final int FLAG_LOAD_MATCH_REFRESH = 1;
    public static final int FLAG_LOAD_MATCH_SCROLL = 2;


    public static final String BASE_SERVER = "http://cadobongdafree.com/public/api/v1";
    public static final String SERVICE_GET_MATCH_LIVE = BASE_SERVER + "/list";
    public static final String SERVICE_GET_MATCH_RESULT = BASE_SERVER + "/schedule/";
    public static final String SERVICE_GET_MATCH_FIXTURES = BASE_SERVER + "/schedule/";




    public static final String CONDITION_START = "?";
    public static final String CONDITION_AND = "&";

    public static final String CONDITION_PAGE = "page=";
}
