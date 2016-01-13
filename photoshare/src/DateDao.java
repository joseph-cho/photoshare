package photoshare;

/* Haohan Zhu */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class DateDao {
    
  private static final String CHECK_DATE = "SELECT " +
    "COUNT(*) FROM dates WHERE d = ?";

  private static final String NEW_DATE = "INSERT INTO " +
      "dates (d) VALUES (?)";

  public boolean create(Date date) {
    PreparedStatement stmt = null;
    Connection conn = null;
    ResultSet rs = null;
    try {
      conn = DbConnection.getConnection();
        
        stmt = conn.prepareStatement(CHECK_DATE);
        stmt.setDate(1, date);
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
        
      stmt = conn.prepareStatement(NEW_DATE);
      stmt.setDate(1, date);
      
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
}
