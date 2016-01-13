package photoshare;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.sql.Date;

/**
 * A bean that handles album data
 */
public class TagBean {
  private int tag_id = -1;
  private int picture_id = -1;
  private String tag = "";
  private int tagCount = 0;
  
  public void setTagId(int tag_id) {
    this.tag_id = tag_id;
  }

  public int getTagId() {
    return tag_id;
  }

  public void setPictureId(int picture_id) {
    this.picture_id = picture_id;
  }

  public int getPictureId() {
    return picture_id;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getTag() {
    return tag;
  }

  public void setTagCount(int tagCount) {
    this.tagCount = tagCount;
  }

  public int getTagCount() {
    return tagCount;
  }

}
