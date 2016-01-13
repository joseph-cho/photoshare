package photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
/**
 * A data access object (DAO) to handle picture objects
 *
 * @author G. Zervas <cs460tf@bu.edu>
 */
public class CommentDao {
  private static final String ADD_COMMENT_STMT = "INSERT INTO " + 
    "comments (owner_id, picture_id, comment_text, date_created) VALUES (?, ?, ?, now())";

  private static final String GET_PICS_COMMENTS_STMT = "SELECT " +
    "owner_id, comment_text, date_created FROM comments WHERE picture_id = ?";

  private static final String COUNT_LIKES_OF_PIC_STMT = "SELECT count(comment_text) " + 
    "FROM comments WHERE comment_text = 'addLike' AND picture_id = ?";

  public boolean create(int owner_id, int picture_id, String comment_text) {
    PreparedStatement stmt = null;
    Connection conn = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(ADD_COMMENT_STMT);
      stmt.setInt(1, owner_id);
      stmt.setInt(2, picture_id);
      stmt.setString(3, comment_text);
      stmt.executeUpdate(); 

      return true;
      
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {
      
      if (stmt != null) {
        try { stmt.close(); }
        catch (SQLException e) { ; }
        stmt = null;
      }
      
      if (conn != null) {
        try { conn.close(); }
        catch (SQLException e) { ; }
        conn = null;
      }
    }
  }

  public List<CommentBean> getCommentsOfPic(int picture_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_PICS_COMMENTS_STMT);
      stmt.setInt(1, picture_id);
      rs = stmt.executeQuery(); 

      List<CommentBean> comments = new ArrayList<CommentBean>();
      while (rs.next()) { 
        CommentBean c = new CommentBean();
        if (!rs.getString(2).equals("addLike")) { //don't display "likes"
          c.setOwnerId(rs.getInt(1));
          c.setCommentText(rs.getString(2));
          c.setDateCreated(rs.getDate(3));

          comments.add(c);
        }
      }

      return comments;
      
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {

      if (rs != null) {
        try { rs.close(); } catch (SQLException e) { ; }
        rs = null;
      }
      
      if (stmt != null) {
        try { stmt.close(); }
        catch (SQLException e) { ; }
        stmt = null;
      }
      
      if (conn != null) {
        try { conn.close(); }
        catch (SQLException e) { ; }
        conn = null;
      }
    }
  }

  public int countLikes(int picture_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(COUNT_LIKES_OF_PIC_STMT);
      stmt.setInt(1, picture_id);
      rs = stmt.executeQuery(); 
      rs.next();

      int numOfLikes = rs.getInt(1);

      return numOfLikes;
      
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {

      if (rs != null) {
        try { rs.close(); } catch (SQLException e) { ; }
        rs = null;
      }
      
      if (stmt != null) {
        try { stmt.close(); }
        catch (SQLException e) { ; }
        stmt = null;
      }
      
      if (conn != null) {
        try { conn.close(); }
        catch (SQLException e) { ; }
        conn = null;
      }
    }
  }
    
}
