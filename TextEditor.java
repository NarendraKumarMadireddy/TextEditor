import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {

    JTextArea textArea;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem newMenuItem, openMenuItem, saveMenuItem, exitMenuItem;

    public TextEditor() {
        setTitle("Text Editor");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        newMenuItem = new JMenuItem("New");
        openMenuItem = new JMenuItem("Open");
        saveMenuItem = new JMenuItem("Save");
        exitMenuItem = new JMenuItem("Exit");

        newMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newMenuItem) {
            textArea.setText("");
        } else if (e.getSource() == openMenuItem) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fileChooser.getSelectedFile();
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                    reader.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        } else if (e.getSource() == saveMenuItem) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fileChooser.getSelectedFile();
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(textArea.getText());
                    writer.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        } else if (e.getSource() == exitMenuItem) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.setVisible(true);
    }
}
