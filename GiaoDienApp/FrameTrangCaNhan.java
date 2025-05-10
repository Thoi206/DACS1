package GiaoDienApp;

import dao.DatabaseConnection;
import dao.dangky.dangkiDao;
import utils.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.SQLException;

public class FrameTrangCaNhan extends JPanel {
    private JPanel panel1;
    private JTextField textField1; // Họ và tên
    private JComboBox comboBox1; // Giới tính hoặc các tùy chọn khác
    private JTextField textField2; // Email
    private JTextField textField3; // Số điện thoại
    private JButton lưuButton;
    private JButton hủyButton;
    private JPasswordField passwordField1; // Mật khẩu mới
    private JPasswordField passwordField2; // Xác nhận mật khẩu
    private int userId;

    public JPanel getPanel1() {
        return panel1;
    }

    private void initComponents() {
        // Phương thức khởi tạo thành phần nếu cần
    }

    public FrameTrangCaNhan(int userId) {
        this.userId = userId;

        // Load icon (không cần vì là JPanel, nhưng giữ để tương thích với giao diện)
        URL urlIconNotepad = FrameTrangCaNhan.class.getResource("IconNotepad.png");
        if (urlIconNotepad != null) {
            Image img = Toolkit.getDefaultToolkit().getImage(urlIconNotepad);
            // Icon sẽ được sử dụng trong FrameTrangChu
        } else {
            System.out.println("Không tìm thấy file IconNotepad.png");
        }

        // Thiết lập panel1
        setLayout(new BorderLayout());
        add(panel1, BorderLayout.CENTER);

        // Thiết lập ảnh nền - truyền null cho tham số đầu tiên
        UIUtils.applyBackground((FrameTrangCaNhan)null, panel1, "background.jpg");

        // Lấy thông tin người dùng từ dangkiDao
        dangkiDao dao = new dangkiDao();
        String fullName = "", email = "", sdt = "";
        try {
            String query = "SELECT fullname, email, sdt FROM dangki WHERE user_id = ?";
            try (var conn = DatabaseConnection.getConnection();
                 var stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);
                var rs = stmt.executeQuery();
                if (rs.next()) {
                    fullName = rs.getString("fullname");
                    email = rs.getString("email");
                    sdt = rs.getString("sdt");
                }
            }
            textField1.setText(fullName);
            textField2.setText(email);
            textField3.setText(sdt);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải thông tin: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        // Sự kiện cho nút Lưu
        lưuButton.addActionListener(e -> {
            try {
                String newFullName = textField1.getText().trim();
                String newEmail = textField2.getText().trim();
                String newSdt = textField3.getText().trim();
                String newPassword = new String(passwordField1.getPassword()).trim();
                String confirmPassword = new String(passwordField2.getPassword()).trim();
                if (newFullName.isEmpty() || newEmail.isEmpty() || newSdt.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!newPassword.isEmpty() && !newPassword.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp!",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                dao.thayDoiThongTin(userId, newFullName, newEmail, newSdt, newPassword.isEmpty() ? null : newPassword);
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật: " + ex.getMessage(),
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Sự kiện cho nút Hủy
        hủyButton.addActionListener(e -> {
            // Đóng tab hoặc quay lại FrameTrangChu
            Container parent = getParent();
            while (!(parent instanceof JTabbedPane) && parent != null) {
                parent = parent.getParent();
            }
            if (parent instanceof JTabbedPane) {
                ((JTabbedPane) parent).setSelectedIndex(0); // Quay lại tab đầu
            }
        });
    }

    public void setContentPane(JPanel panel) {
        // Phương thức này được thêm vào để tương thích với các phương thức trong UIUtils
        if (panel != null) {
            removeAll();
            add(panel, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }
}