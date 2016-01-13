package photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A data access object (DAO) to handle the Users table
 *
 * @author G. Zervas <cs460tf@bu.edu>
 */
public class NewUserDao {
  private static final String CHECK_EMAIL_STMT = "SELECT " +
      "COUNT(*) FROM Users WHERE email = ?";

  private static final String NEW_USER_STMT = "INSERT INTO " +
      "Users (email, password, firstname, lastname, " + 
      "date_of_birth, gender, hometown) " + 
      "VALUES (?, ?, ?, ?, TO_DATE(?,'MMDDYYYY'), ?, ?)";

  private static final String GET_USER_ID_STMT = "SELECT " +
      "user_id FROM Users WHERE email = ?";

  private static final String GET_USERS_FRIENDS_STMT = "SELECT " + 
      "user_id_2 FROM friends WHERE user_id_1 = ?";

  private static final String GET_FRIENDS_INFO_STMT = "SELECT firstname, " +
      "lastname, email FROM users WHERE user_id = ?";

  private static final String GET_ALL_OTHER_USERS_STMT = "SELECT firstname, " +
      "lastname, email, user_id FROM users";

  private static final String MAKE_FRIEND_STMT = "INSERT INTO friends " +
      "(user_id_1, user_id_2) VALUES (?, ?)";

  private static final String DELETE_FRIEND_STMT = "DELETE FROM friends " + 
      "WHERE user_id_1 = ? AND user_id_2 = ?";

  private static final String GET_USERS_NAME_STMT = "SELECT firstname, lastname " +
      "FROM users WHERE user_id = ?";

  private static final String GET_LIKERS_STMT = "SELECT owner_id FROM comments " +
      "WHERE comment_text = 'addLike' AND picture_id = ?";

  public boolean create(String email, String password, String firstname, String lastname, String dob, String gender, String hometown) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(CHECK_EMAIL_STMT);
      stmt.setString(1, email);
      rs = stmt.executeQuery();
      if (!rs.next()) {
        // Theoretically this can't happen, but just in case...
        return false;
      }
      int result = rs.getInt(1);
      if (result > 0) {
        // This email is already in use
        return false; 
      }
      
      try { stmt.close(); }
      catch (Exception e) { }

      stmt = conn.prepareStatement(NEW_USER_STMT);
      stmt.setString(1, email);
      stmt.setString(2, password);
      stmt.setString(3, firstname);
      stmt.setString(4, lastname);
      stmt.setString(5, dob);
      stmt.setString(6, gender);
      stmt.setString(7, hometown);
      
      stmt.executeUpdate();

      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {
      if (rs != null) {
        try { rs.close(); }
        catch (SQLException e) { ; }
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

  public int getUserId(String email) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_USER_ID_STMT);
      stmt.setString(1, email);
      rs = stmt.executeQuery();

      rs.next();
      int user_id = rs.getInt(1);

      return user_id;

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {
      if (rs != null) {
        try { rs.close(); }
        catch (SQLException e) { ; }
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

  public List<NewUserBean> getFriendsOfUser(int user_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_USERS_FRIENDS_STMT);
      stmt.setInt(1, user_id);
      rs = stmt.executeQuery(); 

      List<NewUserBean> friends = new ArrayList<NewUserBean>();

      while (rs.next()) { 
        PreparedStatement stmt2 = conn.prepareStatement(GET_FRIENDS_INFO_STMT);
        int friendsId = rs.getInt(1);
        stmt2.setInt(1, friendsId);
        ResultSet friendInfo = stmt2.executeQuery();

        while (friendInfo.next()) { //while there are still friends left
          NewUserBean friend = new NewUserBean();
          friend.setFirstname(friendInfo.getString(1));
          friend.setLastname(friendInfo.getString(2));
          friend.setEmail(friendInfo.getString(3));

          friends.add(friend);
        }
      }

      return friends;
      
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

  //gets all users except the user specified 
  public List<NewUserBean> getAllUsers(int user_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_ALL_OTHER_USERS_STMT);
      rs = stmt.executeQuery(); 

      List<NewUserBean> others = new ArrayList<NewUserBean>();

      //add all users to list except the one specified
      while (rs.next()) { 
        if (rs.getInt(4) != user_id) {
          NewUserBean user = new NewUserBean();
          user.setFirstname(rs.getString(1));
          user.setLastname(rs.getString(2));
          user.setEmail(rs.getString(3));

          others.add(user);
        }

      }

      return others;
      
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

  public void addFriend(int user_id, int friend_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(MAKE_FRIEND_STMT);

      stmt.setInt(1, user_id);
      stmt.setInt(2, friend_id);

      stmt.executeUpdate(); 
      
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

  public void deleteFriend(int user_id_1, int user_id_2) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(DELETE_FRIEND_STMT);

      stmt.setInt(1, user_id_1);
      stmt.setInt(2, user_id_2);

      stmt.executeUpdate(); 
      
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

  public String getUsersName(int user_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_USERS_NAME_STMT);
      stmt.setInt(1, user_id);
      rs = stmt.executeQuery();

      rs.next();
      String firstName = rs.getString(1);
      String lastName = rs.getString(2);

      String name = firstName + " " + lastName;

      return name;

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {
      if (rs != null) {
        try { rs.close(); }
        catch (SQLException e) { ; }
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

  public List<Integer> getLikers(int picture_id) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
      stmt = conn.prepareStatement(GET_LIKERS_STMT);
      stmt.setInt(1, picture_id);
      rs = stmt.executeQuery();

      List<Integer> likers = new ArrayList<Integer>();
      while(rs.next()) {
        likers.add(rs.getInt(1));
      }

      return likers;

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {
      if (rs != null) {
        try { rs.close(); }
        catch (SQLException e) { ; }
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
