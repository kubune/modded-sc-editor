package dev.donutquine.editor.layout.menubar.menus;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import dev.donutquine.editor.Editor;

public class DarkModeMenu extends JButton {
    private boolean isDarkMode = false;

    public DarkModeMenu(Editor editor) {
        super("☾");
        this.setBorder(
            BorderFactory.createEmptyBorder(4, 15, 6, 15)
        );
        this.setFont(this.getFont().deriveFont(20f));
        this.setFocusPainted(false);
        this.setFocusable(false);
        this.setOpaque(false);
        this.setContentAreaFilled(true);
        this.setBorderPainted(false);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setMnemonic(KeyEvent.VK_H);

        this.addChangeListener(e -> {
            if (this.getModel().isRollover()) {
                this.setOpaque(true);
                if (this.getText().equals("☾"))
                    this.setBackground(new Color(0, 0, 0, 25));
                else {
                    this.setBackground(new Color(255, 255, 255, 25));
                }
            } else {
                this.setOpaque(false);
                this.setBackground(new Color(0, 0, 0, 0));
            }
        });

        this.addActionListener((event) -> {
            if (this.isDarkMode) {
                editor.setLightMode();
                this.isDarkMode = false;
                this.setText("☾");
            } else {
                editor.setDarkMode();
                this.isDarkMode = true;
                this.setText("☀");
            }
        });
    }
}
