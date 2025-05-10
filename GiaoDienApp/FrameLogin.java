package GiaoDienApp;

import dao.dangky.dangki;
import dao.dangky.dangkiDao;
import utils.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.SQLException;

public class FrameLogin extends JFrame {
    private JPanel panel1;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JCheckBox NhoMatKhauCheckBox;
    private JRadioButton userRadioButton;
    private JButton QuenMatKhauButton;
    private JButton DangNhapButton;
    private JButton DangKyButton;
    private JPanel panel2;

    public FrameLogin() {
        // Load icon
        URL urlIconNotepad = FrameTrangChu.class.getResource("IconNotepad.png");
        if (urlIconNotepad != null) {
            Image img = Toolkit.getDefaultToolkit().getImage(urlIconNotepad);
            this.setIconImage(img);
        } else {
            System.out.println("Không tìm thấy file IconNotepad.png");
        }

        // Thiết lập ảnh nền
        UIUtils.applyBackground(this, panel1, "background.jpg");

        // Thiết lập GUI
        setTitle("Trang Đăng Nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null); // Center
        setVisible(true);

        // Gắn sự kiện cho nút Đăng nhập
        DangNhapButton.addActionListener(e -> dangNhap());

        // Gắn sự kiện cho nút Đăng ký
        DangKyButton.addActionListener(e -> {
            dispose(); // Đóng FrameLogin
            new FrameDangky(); // Mở FrameDangky
        });

        // Gắn sự kiện cho nút Quên mật khẩu (có thể triển khai sau)
        QuenMatKhauButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Chức năng quên mật khẩu chưa được triển khai!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void dangNhap() {
        // Lấy dữ liệu từ các trường
        String username = textField1.getText().trim();
        String password = new String(passwordField1.getPassword()).trim();

        // Kiểm tra dữ liệu
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tạo đối tượng dangki
        dangki dk = new dangki(username, password, "", "", "");

        // Gọi lớp dangkiDao để đăng nhập
        dangkiDao dao = new dangkiDao();
        try {
            int userId = dao.dangNhap(dk);
            if (userId != -1) {
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công!",
                        "Thành công", JOptionPane.INFORMATION_MESSAGE);
                // Lưu trạng thái "Nhớ mật khẩu" nếu được chọn (có thể triển khai sau)
                if (NhoMatKhauCheckBox.isSelected()) {
                    // Lưu thông tin đăng nhập vào file hoặc cơ sở dữ liệu (chưa triển khai)
                    System.out.println("Lưu thông tin đăng nhập: " + username);
                }
                // Đóng FrameLogin và mở FrameTrangChu
                dispose();
                new FrameTrangChu(1).setVisible(true);


            } else {
                JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng!",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi đăng nhập: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new FrameLogin();
    }
}
