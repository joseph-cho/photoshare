package photoshare;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.sql.Date;

/**
 * A bean that handles album data
 */
public class CommentBean {
  private int comment_id = -1;
  private int owner_id = -1;
  private int picture_id = -1;
  private String comment_text = "";
  private Date date_created;
  
  public void setCommentId(int comment_id) {
    this.comment_id = comment_id;
  }

  public int getCommentId() {
    return comment_id;
  }

  public void setOwnerId(int owner_id) {
    this.owner_id = owner_id;
  }

  public int getOwnerId() {
    return owner_id;
  }

  public void setPictureId(int picture_id) {
    this.picture_id = picture_id;
  }

  public int getPictureId() {
    return picture_id;
  }

  public void setCommentText(String comment_text) {
    this.comment_text = comment_text;
  }

  public String getCommentText(){
    return comment_text;
  }

  public void setDateCreated(Date date_created) {
    this.date_created = date_created;
  }

  public Date getDateCreated() {
    return date_created;
  }

}
