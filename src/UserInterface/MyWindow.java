package UserInterface;

import Utils.ExecutionEnvironment;
import org.omg.CORBA.Environment;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Ionita Cosmin on 12/26/2015.
 */
public class MyWindow extends JFrame implements KeyListener{

    JScrollPane scroll;
    JPanel container = new JPanel();
    JPanel userEntry = new JPanel();

    private boolean sizable = false;
    private String promptMessage = "guest@/home: ";
    private int containerHeight = 2;

    JLabel label = new JLabel(promptMessage);
    JTextArea input = new JTextArea();

    public MyWindow(String title) {
        super(title);
        initializeFrame();

        initialConfigurations();

        addNewPrompt();

        this.add(scroll);
        show();
        pack();
    }

    private void initialConfigurations() {
        scroll = new JScrollPane(container);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        container.setLayout(null);

        //container.setBackground(Color.blue);
        scroll.setBackground(Color.black);
    }

    private void setPrompt() {
        label = new JLabel(promptMessage);
        label.setFont(new Font("Courier New", Font.BOLD, 14));
        label.setForeground(Color.blue);
        label.setPreferredSize(label.getPreferredSize());
    }

    private void setInput() {
        input.setEnabled(false);
        input = new JTextArea();

        input.setDisabledTextColor(new Color(252, 142, 0));
        input.setForeground(new Color(252, 142, 0));

        input.setBackground(Color.black);
        input.setFont(new Font("Courier New", Font.BOLD, 14));
        input.addKeyListener(this);
        input.setPreferredSize(new Dimension(780 - label.getPreferredSize().width, 25));
    }

    private void setUserEntry() {
        userEntry = new JPanel();

        userEntry.add(label);
        userEntry.add(input);
    }

    private void setContainer() {
        userEntry.setBackground(Color.black);

        userEntry.setBounds(2, containerHeight, 800, 30);

        containerHeight += 30;

        container.setPreferredSize(new Dimension(800, containerHeight));

        container.add(userEntry);
    }

    private void updateUI() {
        container.revalidate();
        scroll.revalidate();

        container.revalidate();

        if(sizable) {
            this.setSize(this.getWidth() + 1, this.getHeight());
            sizable = false;
        }
        else {
            this.setSize(this.getWidth() - 1, this.getHeight());
            sizable = true;
        }

        maintainScrollDown();

        input.requestFocusInWindow();
    }

    private void maintainScrollDown() {
        int height = (int)container.getPreferredSize().getHeight();

        Rectangle rect = new Rectangle(0,height,10,10);
        container.scrollRectToVisible(rect);
    }

    private void addNewPrompt() {
        setPrompt();
        setInput();
        setUserEntry();
        setContainer();
        updateUI();
    }

    private void setOutput() {

        JTextArea output = new JTextArea(ExecutionEnvironment.showResult());
        output.setColumns(800);

        output.setFont(new Font("Courier New", Font.BOLD, 14));
        output.setForeground(new Color(40, 35, 255));

        output.setBounds(2, containerHeight, 800, output.getPreferredSize().height);

        containerHeight += output.getPreferredSize().getHeight();

        container.setPreferredSize(new Dimension(800, containerHeight));

        container.add(output);

        updateUI();
    }

    private void updatePromptPath(String path) {
        promptMessage = promptMessage.split("@")[0] + "@" + path + ": ";
    }

    private void updatePromptUser(String user) {
        promptMessage = user + "@" + promptMessage.split("@")[1];
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_ENTER) {

            ExecutionEnvironment.invoke(input.getText().replace("\n", ""));

            if(ExecutionEnvironment.hasOutput()) {
                setOutput();

                ExecutionEnvironment.flushOutput();
            }

            if(ExecutionEnvironment.wasCdCommand()) {
                updatePromptPath(ExecutionEnvironment.getCurrentFileSystemPath());
            }

            if(ExecutionEnvironment.wasLoginCommand()) {
                updatePromptUser(ExecutionEnvironment.getCurrentUser());
            }

            addNewPrompt();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void initializeFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(850, 400));
        this.getContentPane().setBackground(Color.RED );
    }

    public static void main(String args[]) {
        MyWindow terminal = new MyWindow("Application");
    }
}
