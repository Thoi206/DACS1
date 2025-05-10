package GiaoDienApp;

import dao.ScheduleDao;
import model.Schedule;
import utils.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class FrameTrangChu extends JFrame {
    private JPanel panel1;
    private JButton trangCaNhanButton;
    private JButton suKienCuaToiButton;
    private JButton themSuKienButton;
    private JButton timSuKienButton;
    private JList<String> list1;
    private JTabbedPane tabbedPane;
    private int userId;

    public static DefaultListModel<String> danhSachSuKien = new DefaultListModel<>();

    public FrameTrangChu(int userId) {
        this.userId = userId;

        // Load icon
        URL urlIconNotepad = FrameTrangChu.class.getResource("IconNotepad.png");
        if (urlIconNotepad != null) {
            Image img = Toolkit.getDefaultToolkit().getImage(urlIconNotepad);
            this.setIconImage(img);
        } else {
            System.out.println("Không tìm thấy file IconNotepad.png");
        }

        // Thiết lập GUI
        setTitle("Trang chủ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Tạo JTabbedPane
        tabbedPane = new JTabbedPane();

        // Tạo các panel cho các tab
        JPanel trangCaNhanPanel = new FrameTrangCaNhan(userId);
        JPanel suKienCuaToiPanel = new FrameSuKienCuaToi(userId);

        // Tạo instance của FrameThemSuKien và FrameSearchEvent
        FrameThemSuKien frameThemSuKien = new FrameThemSuKien();
        FrameSearchEvent frameSearchEvent = new FrameSearchEvent();

        // Lấy panel từ các frame
        JPanel themSuKienPanel = frameThemSuKien.getPanel1();
        JPanel timSuKienPanel = frameSearchEvent.getPanel1();

        // Thêm các panel vào JTabbedPane
        tabbedPane.addTab("Trang Cá Nhân", trangCaNhanPanel);
        tabbedPane.addTab("Sự Kiện Của Tôi", suKienCuaToiPanel);
        tabbedPane.addTab("Thêm Sự Kiện", themSuKienPanel);
        tabbedPane.addTab("Tìm Sự Kiện", timSuKienPanel);

        // Thiết lập panel1 làm container chính
        panel1 = new JPanel(new BorderLayout());
        panel1.add(tabbedPane, BorderLayout.CENTER);

        // Tạo panel chứa các nút điều hướng
        JPanel buttonPanel = new JPanel(new FlowLayout());
        trangCaNhanButton = new JButton("Trang Cá Nhân");
        suKienCuaToiButton = new JButton("Sự Kiện Của Tôi");
        themSuKienButton = new JButton("Thêm Sự Kiện");
        timSuKienButton = new JButton("Tìm Sự Kiện");
        buttonPanel.add(trangCaNhanButton);
        buttonPanel.add(suKienCuaToiButton);
        buttonPanel.add(themSuKienButton);
        buttonPanel.add(timSuKienButton);

        // Thêm buttonPanel vào panel1
        panel1.add(buttonPanel, BorderLayout.NORTH);

        // Gắn model cho JList (dùng trong FrameSuKienCuaToi)
        if (list1 != null) {
            list1.setModel(danhSachSuKien);
        }

        // Thiết lập ảnh nền
        UIUtils.applyBackground(this, panel1, "background.jpg");

        // Đăng ký sự kiện cho các nút
        trangCaNhanButton.addActionListener(e -> {
            tabbedPane.setSelectedIndex(0);
            System.out.println("Chuyển đến tab Trang Cá Nhân");
        });

        suKienCuaToiButton.addActionListener(e -> {
            tabbedPane.setSelectedIndex(1);
            System.out.println("Chuyển đến tab Sự Kiện Của Tôi");
            ((FrameSuKienCuaToi) suKienCuaToiPanel).loadSchedules();
        });

        themSuKienButton.addActionListener(e -> {
            tabbedPane.setSelectedIndex(2);
            System.out.println("Chuyển đến tab Thêm Sự Kiện");
        });

        timSuKienButton.addActionListener(e -> {
            tabbedPane.setSelectedIndex(3);
            System.out.println("Chuyển đến tab Tìm Sự Kiện");
        });



        // Thiết lập panel1 làm content pane
        setContentPane(panel1);
    }

    // Hàm main để chạy chương trình
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int userId = 1; // Hoặc lấy userId từ đăng nhập
            FrameTrangChu frame = new FrameTrangChu(userId);
            frame.setVisible(true);
        });
    }
}
