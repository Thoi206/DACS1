package utils;

import GiaoDienApp.BackgroundPanel;
import GiaoDienApp.FrameSuKienCuaToi;
import GiaoDienApp.FrameTrangCaNhan;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class UIUtils {
    // Phương thức cho FrameSuKienCuaToi
    public static void applyBackground(FrameSuKienCuaToi frame, JPanel panel, String imageName) {
        if (frame == null) {
            // Xử lý trường hợp null
            applyBackgroundToPanel(panel, imageName);
            return;
        }

        URL url = frame.getClass().getResource(imageName);
        if (url != null) {
            Image bg = Toolkit.getDefaultToolkit().getImage(url);
            BackgroundPanel backgroundPanel = new BackgroundPanel(bg);
            panel.setOpaque(false);
            backgroundPanel.add(panel, BorderLayout.CENTER);
            frame.setContentPane(backgroundPanel);
        } else {
            System.out.println("Không tìm thấy ảnh nền " + imageName);
            frame.setContentPane(panel);
        }
    }

    // Phương thức cho FrameTrangCaNhan
    public static void applyBackground(FrameTrangCaNhan frame, JPanel panel, String imageName) {
        if (frame == null) {
            // Xử lý trường hợp null
            applyBackgroundToPanel(panel, imageName);
            return;
        }

        URL url = frame.getClass().getResource(imageName);
        if (url != null) {
            Image bg = Toolkit.getDefaultToolkit().getImage(url);
            BackgroundPanel backgroundPanel = new BackgroundPanel(bg);
            panel.setOpaque(false);
            backgroundPanel.add(panel, BorderLayout.CENTER);
            frame.setContentPane(backgroundPanel);
        } else {
            System.out.println("Không tìm thấy ảnh nền " + imageName);
            frame.setContentPane(panel);
        }
    }

    // Phương thức cho JFrame
    public static void applyBackground(JFrame frame, JPanel panel, String imageName) {
        if (frame == null) {
            // Xử lý trường hợp null
            applyBackgroundToPanel(panel, imageName);
            return;
        }

        URL url = UIUtils.class.getResource("/" + imageName);
        if (url != null) {
            Image bg = Toolkit.getDefaultToolkit().getImage(url);
            BackgroundPanel backgroundPanel = new BackgroundPanel(bg);
            panel.setOpaque(false);
            backgroundPanel.add(panel, BorderLayout.CENTER);
            frame.setContentPane(backgroundPanel);
        } else {
            System.out.println("Không tìm thấy ảnh nền " + imageName);
            frame.setContentPane(panel);
        }
    }

    // Phương thức hỗ trợ để xử lý chỉ panel không có frame
    private static void applyBackgroundToPanel(JPanel panel, String imageName) {
        if (panel == null) return;

        URL url = UIUtils.class.getResource("/" + imageName);
        if (url != null) {
            try {
                Image bg = Toolkit.getDefaultToolkit().getImage(url);
                panel.setOpaque(false);
                panel.setBackground(new Color(0, 0, 0, 0));

                // Tìm container cha để thêm background panel
                Container parent = panel.getParent();
                if (parent != null) {
                    int index = -1;
                    Component[] components = parent.getComponents();
                    for (int i = 0; i < components.length; i++) {
                        if (components[i] == panel) {
                            index = i;
                            break;
                        }
                    }

                    BackgroundPanel backgroundPanel = new BackgroundPanel(bg);
                    backgroundPanel.setLayout(new BorderLayout());
                    backgroundPanel.add(panel, BorderLayout.CENTER);

                    if (index >= 0) {
                        parent.remove(index);
                        parent.add(backgroundPanel, index);
                    }
                }
            } catch (Exception e) {
                System.out.println("Lỗi khi áp dụng ảnh nền: " + e.getMessage());
            }
        } else {
            System.out.println("Không tìm thấy ảnh nền " + imageName);
        }
    }
}