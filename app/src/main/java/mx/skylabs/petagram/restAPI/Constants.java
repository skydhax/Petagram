package mx.skylabs.petagram.restAPI;

/**
 * Created by sky on 13/05/17.
 */

public final class Constants {

    //https://api.instagram.com/v1/users/self/?access_token=ACCESS-TOKEN
    //https://api.instagram.com/v1/users/self/?access_token=5431565715.24cdecb.458c5881c7dd4858a8a93a4e02d62884
    //tokens:
    private static final String ACCESS_TOKEN = "5431565715.24cdecb.458c5881c7dd4858a8a93a4e02d62884";

    private static final String VERSION = "v1/";
    public static final String BASE_URL = "https://api.instagram.com/" + VERSION;
    public static final String USERS = "users/";
    private static final String SELF = "self/";
    private static final String MEDIA_RECENT = "media/recent/";
    // https://api.instagram.com/v1/users/self/media/recent/?access_token=5431565715.24cdecb.458c5881c7dd4858a8a93a4e02d62884

    public static final String GET_MY_PROFILE = USERS + SELF + "?access_token=" + ACCESS_TOKEN;
    public static final String GET_RECENT_MEDIA = USERS + SELF + MEDIA_RECENT + "?access_token=" + ACCESS_TOKEN;

    //                                                      users/5472428849/media/recent/?access_token=5431565715.24cdecb.458c5881c7dd4858a8a93a4e02d62884
    public static final String GET_RECENT_MEDIA_FROM_NIEBLA = USERS + "5472428849/" + MEDIA_RECENT + "?access_token=" + ACCESS_TOKEN;

    //                                          users/self/follows?access_token=5431565715.24cdecb.458c5881c7dd4858a8a93a4e02d62884
    public static final String GET_FOLLOWERS = USERS + SELF + "follows?access_token=" + ACCESS_TOKEN;

    //                               users/5472428849/  media/recent/?access_token=5431565715.24cdecb.458c5881c7dd4858a8a93a4e02d62884
    public static final String GET_RECENT_MEDIA_FROM = MEDIA_RECENT + "?access_token=" + ACCESS_TOKEN;




}
