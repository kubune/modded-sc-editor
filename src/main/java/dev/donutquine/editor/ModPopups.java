package dev.donutquine.editor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.formdev.flatlaf.FlatClientProperties;

import dev.donutquine.renderer.impl.swf.objects.TextField;
import dev.donutquine.swf.textfields.TextFieldOriginal;
public class ModPopups {
    public static void showTextFieldDetailsPopup(Editor editor, TextField textField) {
        JFrame parent = editor.getWindow().getFrame();
        System.out.println("Showing TextField details popup for TextField ID: " + textField.getId());
        JLabel titleLabel = new JLabel("TextField (#" + textField.getId() + ") details");
        titleLabel.putClientProperty(FlatClientProperties.STYLE_CLASS, "h1");

        JLabel propertiesLabel = new JLabel("PROPERTIES:");
        propertiesLabel.putClientProperty(FlatClientProperties.STYLE_CLASS, "h2");

        JLabel booleanPropertiesLabel = new JLabel("BOOLEAN PROPERTIES:");
        booleanPropertiesLabel.putClientProperty(FlatClientProperties.STYLE_CLASS, "h2");

        TextFieldOriginal original = textField.getOriginal();
        JOptionPane.showMessageDialog(
            parent,
            new Object[]{
                titleLabel,
                "\n",
                propertiesLabel,
                "ID: " + original.getId(),
                "Bounds: " + original.getBounds(),
                "Default Text: " + original.getDefaultText(),
                "Font Name: " + original.getFontName(),
                "Font Size: " + original.getFontSize(),
                "Outline Color: " + "#" + Integer.toHexString(original.getOutlineColor()).toUpperCase(),
                "Text Color: " + "#" + Integer.toHexString(original.getColor()).toUpperCase(),
                "Alignment: " + original.getAlign(),
                "Bend Angle: " + original.getBendAngle(),
                "Another Text: " + original.getAnotherText(),
                "\n\n",
                booleanPropertiesLabel,
                "Is Auto Adjust Font Size: " + original.isAutoAdjustFontSize(),
                "Is Bold: " + original.isBold(),
                "Is Italic: " + original.isItalic(),
                "Is Multiline: " + original.isMultiline(),
                "Is Outline Enabled: " + original.isOutlineEnabled()
            },
            "TextField Details",
            JOptionPane.PLAIN_MESSAGE
        );
    }
}