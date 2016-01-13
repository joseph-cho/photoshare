package photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A data access object (DAO) to handle tag objects
 *
 * @author G. Zervas <cs460tf@bu.edu>
 */
public class TagDao {
  private static final String CREATE_TAG_STMT = "INSERT INTO " +
    "tags (picture_id, word) VALUES (?, ?)";

  private static final String GET_PICS_TAG_STMT = "SELECT word " +
    "FROM tags WHERE picture_id = ?";

  private static final String GET_PICS_WITH_TAG_STMT = "SELECT picture_id " +
    "FROM tags WHERE word = ?";

  private static final String GET_MOST_POPULAR_TAGS_STMT = "SELECT word, " +
    "COUNT(picture_id) AS tagCount FROM tags GROUP BY word ORDER BY tagCount DESC";
    
  public boolean create(int picture_id, String tag) {
    PreparedStatement stmt = null;
    Connection conn = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(CREATE_TAG_STMT);

      //split the tags by spaces
      List<String> tags = Arrays.asList(tag.split("\\s*,\\s*")); 

      for (String aTag: tags) {
        stmt.setInt(1, picture_id);
        stmt.setString(2, tag);
        stmt.executeUpdate(); 
      }

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

  public List<String> getTagsOfPic(int picture_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_PICS_TAG_STMT);
      stmt.setInt(1, picture_id);
      rs = stmt.executeQuery(); 

      List<String> tags = new ArrayList<String>();
      while (rs.next()) { 
        tags.add(rs.getString(1));
      }

      return tags;
      
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

  public List<Integer> getPicsWithTag(String tag) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_PICS_TAG_STMT);
      stmt.setString(1, tag);
      rs = stmt.executeQuery(); 

      List<Integer> pics = new ArrayList<Integer>();
      while (rs.next()) { 
        pics.add(rs.getInt(1));
      }

      return pics;
      
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

  public List<TagBean> getPopularTags() {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_MOST_POPULAR_TAGS_STMT);
      rs = stmt.executeQuery(); 

      List<TagBean> tags = new ArrayList<TagBean>();
      while (rs.next()) { 
        TagBean t = new TagBean();
        t.setTag(rs.getString(1));
        t.setTagCount(rs.getInt(2));
        tags.add(t);
      }

      return tags;
      
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
