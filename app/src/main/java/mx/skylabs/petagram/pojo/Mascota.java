package mx.skylabs.petagram.pojo;

/**
 * Created by sky on 26/05/16.
 */
public class Mascota {

    private String id;
    private String full_name;
    private String photo_url;
    private int ranking;
    private String media_id;


    public Mascota(String id, String full_name, String photo_url,  int ranking, int foto){
        this.id = id;
        this.full_name = full_name;
        this.photo_url = photo_url;
        this.ranking = ranking;
    }

    public Mascota(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    public String getPhotoUrl() {
        return photo_url;
    }

    public void setPhotoUrl(String photo_url) {
        this.photo_url = photo_url;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getMediaId() {
        return media_id;
    }

    public void setMediaId(String media_id) {
        this.media_id = media_id;
    }
}
