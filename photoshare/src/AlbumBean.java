package photoshare;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.sql.Date;

/**
 * A bean that handles album data
 */
public class AlbumBean {
  private String name = "";
  private int album_id = -1;
  private int owner_id = -1;
  private Date date_created;
  
  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setAlbumId(int album_id) {
    this.album_id = album_id;
  }

  public int getAlbumId() {
    return album_id;
  }

  public void setOwnerId(int owner_id) {
    this.owner_id = owner_id;
  }

  public int getOwnerId() {
    return owner_id;
  }

  public void setDateCreated(Date date_created) {
    this.date_created = date_created;
  }

  public Date getDateCreated() {
    return date_created;
  }

}
