package UserInterface;

import Utils.ExecutionEnvironment;
import Utils.OutputManager;
import javafx.scene.input.KeyCode;
import org.omg.CORBA.Environment;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * Created by Ionita Cosmin on 12/26/2015.
 */
public class MyWindow extends JFrame implements KeyListener{

    private boolean sizable = false;
    private String promptMessage = "guest@/home: ";
    private String lastCommand = "";
    private int containerHeight = 2;

    JLabel label = new JLabel(promptMessage);
    JTextArea input = new JTextArea();
    JFrame thisForm = this;

    JScrollPane scroll;

    JPanel container = new JPanel();
    JPanel userEntry = new JPanel();

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

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(thisForm,
                        "Do you really want to close this session?", "Closing",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            ExecutionEnvironment.storeLogs();
                            System.exit(0);
                        }
            }
        });

        container.setBackground(Color.black);
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
        input.setPreferredSize(new Dimension(780 - label.getPreferredSize().width, 20));
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

        if(ExecutionEnvironment.isPooOutput()) {
            if(ExecutionEnvironment.wasEchoComand())
                setSpecialEchoOutput();
            if(ExecutionEnvironment.wasUserInfoComand())
                setSpecialUserInfoOutput();
            if(ExecutionEnvironment.wasLsComand())
                setSpecialLsOutput();
        }
        else {
            setNormalOutput();
        }
    }

    private void setSpecialEchoOutput() {
        JOptionPane.showMessageDialog(this, ExecutionEnvironment.showResult());
    }

    private void setSpecialUserInfoOutput() {
        JList list;
        DefaultListModel listModel;

        listModel = new DefaultListModel();

        String output = ExecutionEnvironment.showResult();

        for(String line : output.split("\n")) {
            listModel.addElement(line);
        }

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);

        list.setBounds(2, containerHeight, list.getPreferredSize().width, list.getPreferredSize().height);

        containerHeight += list.getPreferredSize().getHeight();

        container.setPreferredSize(new Dimension(800, containerHeight));
        container.add(list);

        updateUI();
    }

    private void setSpecialLsOutput() {
        String[] columnNames = {"Type", "Name", "Dimension", "Created (date)", "Created (time)", "Permissions"};

        String[] output = ExecutionEnvironment.showResult().split("\n");

        int lines = output.length;

        String[][] data = new String[lines][7];

        for(int i = 0; i<output.length; i++) {
            String[] outputContent = output[i].split(" ");

            data[i][0] = outputContent[0];
            data[i][1] = outputContent[1];
            data[i][2] = outputContent[2];

            if(outputContent[0].equals("File:")) {
                data[i][3] = outputContent[4];
                data[i][4] = outputContent[5];
                data[i][5] = outputContent[6];
            }
            else {
                data[i][3] = outputContent[3];
                data[i][4] = outputContent[4];
                data[i][5] = outputContent[5];
            }
        }

        JTable table = new JTable(data, columnNames);

        table.setBounds(2, containerHeight, table.getPreferredSize().width, table.getPreferredSize().height);
        containerHeight += table.getPreferredSize().getHeight();

        container.setPreferredSize(new Dimension(800, containerHeight));
        container.add(table);

        updateUI();
    }

    private void setNormalOutput() {
        JTextArea output = new JTextArea(ExecutionEnvironment.showResult());

        output.setColumns(800);

        output.setFont(new Font("Courier New", Font.BOLD, 14));
        output.setForeground(new Color(40, 35, 255));
        output.setBackground(Color.black);

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
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            if(input.getText().equals("")) {
                input.setText(lastCommand);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_ENTER) {

            ExecutionEnvironment.invoke(input.getText().replace("\n", ""));
            lastCommand = input.getText().replace("\n", "");


            if(ExecutionEnvironment.hasOutput()) {
                setOutput();

                ExecutionEnvironment.flushOutput();
            }

            if(ExecutionEnvironment.wasCdCommand()) {
                updatePromptPath(ExecutionEnvironment.getCurrentFileSystemPath());
            }

            if(ExecutionEnvironment.wasLoginCommand() || ExecutionEnvironment.wasLogoutComand()) {
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
