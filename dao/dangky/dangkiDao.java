package dao.dangky;
import dao.DatabaseConnection;
//import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
public class dangkiDao {
    public boolean Dangki(dangki DK) throws SQLException{
        //***
        //String hashedPassword = BCrypt.hashpw(dk.getPassWord(), BCrypt.gensalt());
        String query = "insert into dangki (username,password,fullname,email,sdt) values(?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1,DK.getUserName());
            pstmt.setString(2,DK.getPassWord());
            pstmt.setString(3,DK.getFullName());
            pstmt.setString(4, DK.getEmail());
            pstmt.setString(5, DK.getSDT());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Xử lý lỗi cụ thể, ví dụ: username hoặc email đã tồn tại
            if (e.getErrorCode() == 2627 || e.getErrorCode() == 2601) { // Lỗi vi phạm UNIQUE
                throw new SQLException("Username hoặc email đã tồn tại.");
            }
            throw e; // Ném lại các lỗi khác
        }
    }


    public int dangNhap(dangki DK) throws SQLException {
        String query = "select user_id from dangki where username = ? and password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, DK.getUserName());
            pstmt.setString(2,DK.getPassWord());
            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("user_id"); // Trả về id của người dùng
                }
                return -1;
            }
        }
    }


    public boolean thayDoiThongTin(int userId, String fullname, String email, String sdt, String password) throws SQLException {
        String query = "UPDATE dangki SET fullname = ?, email = ?, sdt = ?, password = ? WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, fullname);
            pstmt.setString(2, email);
            pstmt.setString(3, sdt);
            pstmt.setString(4, password);
            pstmt.setInt(5, userId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627 || e.getErrorCode() == 2601) {
                throw new SQLException("Email đã tồn tại.");
            }
            throw e;
        }
    }
}