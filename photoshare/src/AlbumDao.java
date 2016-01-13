package photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 * A data access object (DAO) to handle the Albums table
 *
 */
public class AlbumDao {
  //private static final String CHECK_NAME_STMT = "SELECT " +
      //"COUNT(*) FROM Albums WHERE name = ? AND album_user_id = ?";
 
  //create new album
  private static final String NEW_ALBUMS_STMT = "INSERT INTO " +
      "albums (name, owner_id, date_created) VALUES (?, ?, NOW())";

  //get pictures in the album
  private static final String GET_ALBUM_PICS_STMT = "SELECT picture_id " +
      "FROM album_contains WHERE album_id = ?";

  //get a user's albums
  private static final String GET_USERS_ALBUMS_STMT = "SELECT album_id, " +
      "owner_id, name, date_created FROM albums WHERE owner_id = ?";

  private static final String DEL_ALBUM_CONTAINS_STMT = "DELETE FROM " +
      "album_contains WHERE album_id = ?";

  //delete an album
  private static final String DEL_ALBUMS_STMT = "DELETE FROM " +
      "albums WHERE album_id = ?";

  private static final String DEL_FROM_PICTURES_STMT = "DELETE FROM " +
      "pictures WHERE picture_id = ?";

  private static final String DEL_FROM_PICCOMMENTS_STMT = "DELETE FROM " +
      "comments WHERE picture_id = ?";

  private static final String GET_ALL_ALBUMS_STMT = "SELECT album_id, " +
      "name, owner_id, date_created FROM albums ORDER BY date_created DESC"; 


  public boolean create(String name, int owner_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(NEW_ALBUMS_STMT);
      stmt.setString(1, name);
      stmt.setInt(2, owner_id);
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

  public List<Integer> getPicsOfAlbum(int album_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_ALBUM_PICS_STMT);
      stmt.setInt(1, album_id);
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

  public List<AlbumBean> getAlbumsOfUser(int owner_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_USERS_ALBUMS_STMT);
      stmt.setInt(1, owner_id);
      rs = stmt.executeQuery(); 

      List<AlbumBean> albums = new ArrayList<AlbumBean>();

      while (rs.next()) { 
        AlbumBean album = new AlbumBean();
        album.setAlbumId(rs.getInt(1));
        album.setOwnerId(rs.getInt(2));
        album.setName(rs.getString(3));
        album.setDateCreated(rs.getDate(4));

        albums.add(album);
      }

      return albums;
      
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

  public void deleteAlbum(int album_id) {
    PreparedStatement stmt = null;
    PreparedStatement stmt2 = null;
    Connection conn = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(DEL_ALBUM_CONTAINS_STMT);
      
      List<Integer> albumsPics = new ArrayList<Integer>();
      albumsPics = getPicsOfAlbum(album_id);

      stmt.setInt(1, album_id);
      stmt.executeUpdate();
      stmt.close();

      stmt = conn.prepareStatement(DEL_FROM_PICCOMMENTS_STMT);
      stmt2 = conn.prepareStatement(DEL_FROM_PICTURES_STMT);
      for (Integer picId : albumsPics) {
        stmt.setInt(1, picId);
        stmt.executeUpdate();
        stmt2.setInt(1, picId);
        stmt2.executeUpdate();
      }

      stmt.close();
      stmt2.close();

      stmt = conn.prepareStatement(DEL_ALBUMS_STMT);
      stmt.setInt(1, album_id);
      stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {
      if (stmt != null) {
        try { stmt.close(); } catch (SQLException e) { ; }
        stmt = null;
      }
      if (stmt2 != null) {
        try { stmt2.close(); } catch (SQLException e) { ; }
        stmt2 = null;
      }
      if (conn != null) {
        try { conn.close(); } catch (SQLException e) { ; }
        conn = null;
      }
    }
  }

  public List<AlbumBean> getAllAlbums() {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_ALL_ALBUMS_STMT);
      rs = stmt.executeQuery(); 

      List<AlbumBean> albums = new ArrayList<AlbumBean>();
      while (rs.next()) { 
        AlbumBean al = new AlbumBean();
        al.setAlbumId(rs.getInt(1));
        al.setName(rs.getString(2));
        al.setOwnerId(rs.getInt(3));
        al.setDateCreated(rs.getDate(4));

        albums.add(al);
      }

      return albums;
      
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
