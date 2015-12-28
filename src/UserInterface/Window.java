package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Ionita Cosmin on 12/26/2015.
 */
public class Window extends JFrame implements KeyListener{

    JScrollPane scroll;
    JPanel container = new JPanel();
    JPanel userEntry = new JPanel();

    private int containerHeight = 32;

    JLabel label = new JLabel("label_text");
    JTextArea input = new JTextArea();

    public Window(String title) {
        super(title);
        initializeFrame();

        scroll = new JScrollPane(container);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        label.setPreferredSize(label.getPreferredSize());
        input.setPreferredSize(new Dimension(700, 25));

        input.addKeyListener(this);

        userEntry.add(label);
        userEntry.add(input);

        userEntry.setPreferredSize(userEntry.getPreferredSize());

        container.setPreferredSize(new Dimension(800, containerHeight));
        containerHeight += 32;

        container.setBackground(Color.BLACK);
        container.setLayout(new GridLayout(0, 1));
        container.add(userEntry);

        container.validate();

        this.add(scroll);
        show();
        pack();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        label = new JLabel("label_text");
        input = new JTextArea();

        input.addKeyListener(this);

        label.setPreferredSize(label.getPreferredSize());
        input.setPreferredSize(new Dimension(700, 25));

        userEntry.add(label);
        userEntry.add(input);


        container.add(userEntry);
        container.setPreferredSize(new Dimension(800, containerHeight));
        containerHeight += 32;

        container.validate();

        scroll.validate();

        JScrollBar vertical = scroll.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());

        input.requestFocusInWindow();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void initializeFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 400));
        this.getContentPane().setBackground(Color.RED );
    }

    public static void main(String args[]) {
        Window terminal = new Window("Application");
    }
}
