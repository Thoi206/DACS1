package GiaoDienApp;

import utils.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FrameSearchEvent extends JFrame {
    private JPanel panel1;
    private JRadioButton TenLichTrinhRadioButton;
    private JRadioButton NgayDIenRaLichRadioButton;
    private JTextField textField1;
    private JButton TimButton;
    private JButton HuyButton;
    private JButton ChonNgayBatDauButton;
    private JButton ChonNgayKetThucButton;

    public JPanel getPanel1() {
        return panel1;
    }

    public FrameSearchEvent() {
        // Load icon
        URL urlIconNotepad = FrameTrangChu.class.getResource("IconNotepad.png");
        if (urlIconNotepad != null) {
            Image img = Toolkit.getDefaultToolkit().getImage(urlIconNotepad);
            this.setIconImage(img);
        } else {
            System.out.println("Không tìm thấy file IconNotepad.png");
        }



        // Thiết lập GUI
        setTitle("Tìm sự kiện");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300); // Hoặc pack();
        setLocationRelativeTo(null); // Center
        // Xóa setVisible(true) để tránh hiển thị cửa sổ riêng biệt khi khởi tạo
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrameSearchEvent frame = new FrameSearchEvent();
            frame.setVisible(true);
        });
    }
}