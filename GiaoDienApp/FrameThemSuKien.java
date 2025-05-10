package GiaoDienApp;

import com.toedter.calendar.JCalendar;
import utils.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FrameThemSuKien extends JFrame {
    private JPanel panel1;
    private JTextField TextField;
    private JTextField textField2;
    private JTextField textField3;
    private JTextArea TextArea1;
    private JSpinner Spinner1;
    private JSpinner Spinner2;
    private JSpinner Spinner3;
    private JComboBox ComboBox1;
    private JComboBox ComboBox2;
    private JComboBox ComboBox3;

    private JSpinner SpinnerPhut1;  // phút bắt đầu
    private JSpinner SpinnerPhut2;  // phút kết thúc

    private JTextField txtNgayBatDau;
    private JTextField txtNgayKetThuc;
    private JButton btnChonNgayBatDau;
    private JButton btnChonNgayKetThuc;
    private JButton ThemButton;
    private JButton HuyButton;
    private JSpinner SpinnerPhut3;

    public JPanel getPanel1() {
        return panel1;
    }

    public FrameThemSuKien() {
        setTitle("Thêm Sự Kiện");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Thiết lập ảnh nền - sử dụng phương thức đúng
        UIUtils.applyBackground(this, panel1, "background.jpg");

        //load icon
        URL urlIconNotepad = FrameTrangChu.class.getResource("IconNotepad.png");
        if (urlIconNotepad != null) {
            Image img = Toolkit.getDefaultToolkit().getImage(urlIconNotepad);
            this.setIconImage(img);
        } else {
            System.out.println("Không tìm thấy file IconNotepad.png");
        }

        btnChonNgayBatDau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCalendar jCalendar = new JCalendar();
                int option = JOptionPane.showConfirmDialog(null, jCalendar, "Chọn ngày", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    Calendar cal = jCalendar.getCalendar();
                    if (cal != null) {
                        Date selectedDate = cal.getTime();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        txtNgayBatDau.setText(sdf.format(selectedDate));
                    } else {
                        JOptionPane.showMessageDialog(null, "Không có ngày nào được chọn.");
                    }
                }
            }
        });

        btnChonNgayKetThuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCalendar jCalendar = new JCalendar();
                int option = JOptionPane.showConfirmDialog(null, jCalendar, "Chọn ngày", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    Calendar cal = jCalendar.getCalendar();
                    if (cal != null) {
                        Date selectedDate = cal.getTime();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        txtNgayKetThuc.setText(sdf.format(selectedDate));
                    } else {
                        JOptionPane.showMessageDialog(null, "Không có ngày nào được chọn.");
                    }
                }
            }
        });

        // Spinner giờ (0–23)
        SpinnerNumberModel gioBatDauModel = new SpinnerNumberModel(0, 0, 23, 1);
        SpinnerNumberModel gioKetThucModel = new SpinnerNumberModel(0, 0, 23, 1);
        Spinner1.setModel(gioBatDauModel);
        Spinner2.setModel(gioKetThucModel);

        // Spinner phút (0–59)
        SpinnerNumberModel phutBatDauModel = new SpinnerNumberModel(0, 0, 59, 1);
        SpinnerNumberModel phutKetThucModel = new SpinnerNumberModel(0, 0, 59, 1);
        SpinnerPhut1.setModel(phutBatDauModel);
        SpinnerPhut2.setModel(phutKetThucModel);
    }
    // sự kiện nút thêm sự kiện

    
    // sự kiện nút hủy


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrameThemSuKien().setVisible(true));
    }
}