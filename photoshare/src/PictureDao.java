package photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A data access object (DAO) to handle picture objects
 *
 * @author G. Zervas <cs460tf@bu.edu>
 */
public class PictureDao {
  private static final String LOAD_PICTURE_STMT = "SELECT " +
      "\"caption\", \"imgdata\", \"thumbdata\", \"size\", \"content_type\" FROM Pictures WHERE \"picture_id\" = ?";

  private static final String SAVE_PICTURE_STMT = "INSERT INTO " +
      "pictures (\"caption\", \"imgdata\", \"thumbdata\", \"size\", \"content_type\", \"owner_id\") VALUES (?, ?, ?, ?, ?, ?) RETURNING picture_id";

  private static final String SAVE_ALBUM_CONTAINS_STMT = "INSERT INTO " + 
  	  "album_contains (album_id, picture_id) VALUES (?, ?)";

  private static final String ALL_PICTURE_IDS_STMT = "SELECT \"picture_id\" FROM Pictures ORDER BY \"picture_id\" DESC";

  private static final String GET_USERS_PICTURES_STMT = "SELECT picture_id FROM pictures WHERE owner_id = ?";

  private static final String GET_PICTURES_OWNER_STMT = "SELECT owner_id FROM pictures WHERE picture_id = ?";

  //deletes picture
  private static final String DELETE_PIC_STMT = "DELETE FROM pictures WHERE picture_id = ?";

  private static final String DELETE_PIC_FROM_ALBUM_STMT = "DELETE FROM album_contains WHERE picture_id = ?";

  //deletes a comment on a picture
  private static final String DELETE_COMMENT_STMT = "DELETE FROM comments WHERE picture_id = ?";

  public Picture load(int id) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		Picture picture = null;
    try {
			conn = DbConnection.getConnection();
			stmt = conn.prepareStatement(LOAD_PICTURE_STMT);
      stmt.setInt(1, id);
			rs = stmt.executeQuery();
      if (rs.next()) {
        picture = new Picture();
        picture.setId(id);
        picture.setCaption(rs.getString(1));
        picture.setData(rs.getBytes(2));
        picture.setThumbdata(rs.getBytes(3));
        picture.setSize(rs.getLong(4));
        picture.setContentType(rs.getString(5));
      }

			rs.close();
			rs = null;
		
			stmt.close();
			stmt = null;
			
			conn.close();
			conn = null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { ; }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { ; }
				stmt = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (SQLException e) { ; }
				conn = null;
			}
		}

		return picture;
	}

	public void save(Picture picture, int album_id, int owner_id) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int pic_id = 0;
		try {
			conn = DbConnection.getConnection();
			stmt = conn.prepareStatement(SAVE_PICTURE_STMT);
			stmt.setString(1, picture.getCaption());
			stmt.setBytes(2, picture.getData());
			stmt.setBytes(3, picture.getThumbdata());
			stmt.setLong(4, picture.getSize());
			stmt.setString(5, picture.getContentType());
			stmt.setInt(6, owner_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				pic_id = rs.getInt(1);// must get the generated pic id that was just stored in the database
			}

			stmt.close();
			stmt = null;

			stmt = conn.prepareStatement(SAVE_ALBUM_CONTAINS_STMT);
			stmt.setInt(1, album_id);
			stmt.setInt(2, pic_id); // picture.getId() does not work because the id is generated via sequence and hasn't finished ( i guess) 
			stmt.executeUpdate();

			stmt.close();
			stmt = null;

			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { ; }
				stmt = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (SQLException e) { ; }
				conn = null;
			}
		}
	}

	public List<Integer> allPicturesIds() {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		List<Integer> picturesIds = new ArrayList<Integer>();
		try {
			conn = DbConnection.getConnection();
			stmt = conn.prepareStatement(ALL_PICTURE_IDS_STMT);
			rs = stmt.executeQuery();
			while (rs.next()) {
				picturesIds.add(rs.getInt(1));
			}

			rs.close();
			rs = null;

			stmt.close();
			stmt = null;

			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { ; }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { ; }
				stmt = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (SQLException e) { ; }
				conn = null;
			}
		}

		return picturesIds;
	}

	
	public List<Integer> usersPicturesIds(int user_id) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		List<Integer> picturesIds = new ArrayList<Integer>();
		try {
			conn = DbConnection.getConnection();
			stmt = conn.prepareStatement(GET_USERS_PICTURES_STMT);
			stmt.setInt(1, user_id);
			rs = stmt.executeQuery();


			while (rs.next()) {
				picturesIds.add(rs.getInt(1));
			}

			rs.close();
			rs = null;

			stmt.close();
			stmt = null;

			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { ; }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { ; }
				stmt = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (SQLException e) { ; }
				conn = null;
			}
		}

		return picturesIds;
	}

	public void delete(int picture_id) {
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = DbConnection.getConnection();
			stmt = conn.prepareStatement(DELETE_COMMENT_STMT);
			stmt.setInt(1, picture_id);
			stmt.executeUpdate();
			stmt.close();
			
			stmt = conn.prepareStatement(DELETE_PIC_FROM_ALBUM_STMT);
			stmt.setInt(1, picture_id);
			stmt.executeUpdate();
			stmt.close();

			stmt = conn.prepareStatement(DELETE_PIC_STMT);
			stmt.setInt(1, picture_id);
			stmt.executeUpdate();
			stmt.close();
			stmt = null;
			
			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { ; }
				stmt = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (SQLException e) { ; }
				conn = null;
			}
		}
	}

	public int getPicsOwner(int picture_id) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = DbConnection.getConnection();
			stmt = conn.prepareStatement(GET_PICTURES_OWNER_STMT);
			stmt.setInt(1, picture_id);
			rs = stmt.executeQuery();
			rs.next();
			int owner_id = rs.getInt(1);
			
			return owner_id;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { ; }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { ; }
				stmt = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (SQLException e) { ; }
				conn = null;
			}
		}
	}
	
}
