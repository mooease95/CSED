/**
 * A JTextField with placeholder. 
 * I have used some code found on StackOverflow for this class. It was wound here: http://stackoverflow.com/questions/16213836/java-swing-jtextfield-set-placeholder
 */
 
import java.awt.*;

import javax.swing.*;
import javax.swing.text.Document;

@SuppressWarnings("serial")
public class JTextFieldWithPlaceholder extends JTextField {

    public static void main(final String[] args) {
        final JTextFieldWithPlaceholder tf = new JTextFieldWithPlaceholder("");
        tf.setColumns(20);
        tf.setPlaceholder("All your base are belong to us!");
        final Font f = tf.getFont();
        tf.setFont(new Font(f.getName(), f.getStyle(), 30));
        JOptionPane.showMessageDialog(null, tf);
    }

    private String placeholder;

    public JTextFieldWithPlaceholder() {
    }

    public JTextFieldWithPlaceholder(
        final Document pDoc,
        final String pText,
        final int pColumns)
    {
        super(pDoc, pText, pColumns);
    }

    public JTextFieldWithPlaceholder(final int pColumns) {
        super(pColumns);
    }

    public JTextFieldWithPlaceholder(final String pText) {
        super(pText);
    }

    public JTextFieldWithPlaceholder(final String pText, final int pColumns) {
        super(pText, pColumns);
    }
	
    public JTextFieldWithPlaceholder(
        final Document pDoc,
        final String pText,
        final int pColumns,
		String placeholder)
    {
        super(pDoc, pText, pColumns);
		setPlaceholder(placeholder);
    }

    public JTextFieldWithPlaceholder(final int pColumns, String placeholder) {
        super(pColumns);
		setPlaceholder(placeholder);
    }

    public JTextFieldWithPlaceholder(final String pText, String placeholder) {
        super(pText);
		setPlaceholder(placeholder);
    }

    public JTextFieldWithPlaceholder(final String pText, final int pColumns, String placeholder) {
        super(pText, pColumns);
		setPlaceholder(placeholder);
    }


    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
            .getMaxAscent() + getInsets().top);
    }

    public String getPlaceholder() {
        return placeholder;
    }
	
    public void setPlaceholder(final String s) {
        placeholder = s;
    }

}