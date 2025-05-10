package GiaoDienApp;

import dao.dangky.dangki;
import dao.dangky.dangkiDao;
import utils.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.SQLException;

public class FrameDangky extends JFrame {
    private JPanel panel1;
    private JTextField textField1; // username
    private JPasswordField passwordField1; // password
    private JPasswordField passwordField2; // confirm password
    private JTextField textField2; // full name
    private JTextField textField3; // email
    private JTextField textField4; // sdt
    private JCheckBox DongYCheckBox;
    private JButton DangKyButton;
    private JButton HuyButton;

    public FrameDangky() {
        // Load icon
        URL urlIconNotepad = FrameTrangChu.class.getResource("IconNotepad.png");
        if (urlIconNotepad != null) {
            Image img = Toolkit.getDefaultToolkit().getImage(urlIconNotepad);
            this.setIconImage(img);
        } else {
            System.out.println("Không tìm thấy file IconNotepad.png");
        }



        // Thiết lập GUI
        setTitle("Trang Đăng Ký");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Center
        setContentPane(panel1);
        setVisible(true);

        // Gắn sự kiện cho nút Đăng ký
        DangKyButton.addActionListener(e -> Dangki());

        // Gắn sự kiện cho nút Hủy
        HuyButton.addActionListener(e -> dispose());
    }

    private void Dangki() {
        // Kiểm tra điều kiện hợp lệ
        if (!isValidInput()) {
            return;
        }

        // Lấy dữ liệu từ các trường
        String username = textField1.getText().trim();
        String password = new String(passwordField1.getPassword()).trim();
        String fullName = textField2.getText().trim();
        String email = textField3.getText().trim();
        String sdt = textField4.getText().trim();

        // Tạo đối tượng dangki
        dangki dk = new dangki(username, password, fullName, email, sdt);

        // Gọi lớp dangkiDao
        dangkiDao dao = new dangkiDao();
        try {
            boolean success = dao.Dangki(dk);
            if (success) {
                JOptionPane.showMessageDialog(this, "Đăng ký thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);

                // Mở FrameLogin
                FrameLogin login = new FrameLogin();
                login.setVisible(true);
                dispose(); // đóng FrameDangky
            } else {
                JOptionPane.showMessageDialog(this, "Đăng ký thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("Username hoặc email đã tồn tại")) {
                JOptionPane.showMessageDialog(this, "Username hoặc email đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi đăng ký: " + errorMessage, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean isValidInput() {
        String username = textField1.getText().trim();
        String password = new String(passwordField1.getPassword()).trim();
        String confirmPassword = new String(passwordField2.getPassword()).trim();
        String fullName = textField2.getText().trim();
        String email = textField3.getText().trim();
        String sdt = textField4.getText().trim();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
                || fullName.isEmpty() || email.isEmpty() || sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!DongYCheckBox.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng đồng ý với các điều khoản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        new FrameDangky();
    }
}
