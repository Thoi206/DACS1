package GiaoDienApp;

import dao.ScheduleDao;
import model.Schedule;
import utils.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class FrameSuKienCuaToi extends JPanel {
    private JPanel panel1;
    private JList list1;
    private JLabel LichSuLabel;
    private JButton TimSuKienButton;
    private int userId;
    private DefaultListModel<String> eventListModel;
    private List<Schedule> schedules;

    public JPanel getPanel1() {
        return panel1;
    }

    public FrameSuKienCuaToi(int userId) {
        this.userId = userId;

        // Load icon (không cần vì là JPanel)
        URL urlIconNotepad = FrameSuKienCuaToi.class.getResource("IconNotepad.png");
        if (urlIconNotepad != null) {
            Image img = Toolkit.getDefaultToolkit().getImage(urlIconNotepad);
        } else {
            System.out.println("Không tìm thấy file IconNotepad.png");
        }

        // Thiết lập panel1
        setLayout(new BorderLayout());
        add(panel1, BorderLayout.CENTER);

        // Thiết lập ảnh nền - truyền null cho tham số đầu tiên
        UIUtils.applyBackground((FrameSuKienCuaToi)null, panel1, "background.jpg");

        // Khởi tạo JList và model
        eventListModel = FrameTrangChu.danhSachSuKien;
        list1.setModel(eventListModel);

        // Sự kiện cho nút Tìm Sự Kiện
        TimSuKienButton.addActionListener(e -> {
            Container parent = getParent();
            while (!(parent instanceof JTabbedPane) && parent != null) {
                parent = parent.getParent();
            }
            if (parent instanceof JTabbedPane) {
                ((JTabbedPane) parent).setSelectedIndex(3); // Chuyển đến tab Tìm Sự Kiện
            }
        });

        // Tải danh sách sự kiện ban đầu
        loadSchedules();
    }

    public void loadSchedules() {
        eventListModel.clear();
        ScheduleDao dao = new ScheduleDao();
        try {
            schedules = dao.getAllSchedule(userId);
            if (schedules.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn chưa có sự kiện nào!",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (Schedule schedule : schedules) {
                    eventListModel.addElement(schedule.getTitle() + " (" + schedule.getStartTime() + ")");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải sự kiện: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
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