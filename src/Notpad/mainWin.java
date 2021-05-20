package Notpad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class mainWin extends JFrame implements ActionListener {
    //JPanel jPanel;
    MenuBar mb;
    MenuItem open = new MenuItem("open");
    MenuItem save = new MenuItem("save");
    MenuItem saveas = new MenuItem("save as");
    MenuItem exit = new MenuItem("exit");
    Menu m1 = new Menu("file");

    JFileChooser jfc;//文件选择器
    JTextArea text = new JTextArea(30,30);
    public mainWin(){
        this.setTitle("记事本");
        this.setSize(1000,800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        jfc = new JFileChooser();

        open.addActionListener(this);
        saveas.addActionListener(this);

        mb = new MenuBar();
        m1.add(open);
        m1.add(save);
        m1.add(saveas);
        m1.addSeparator();
        m1.add(exit);
        mb.add(m1);
        this.add(text);
        this.setMenuBar(mb);

    }
    void openFile(){
        if (jfc.showOpenDialog(null) == 0){
            File chooseFile = jfc.getSelectedFile();
            try {
                FileReader fr = new FileReader(chooseFile);
                char[] buf = new char[1024];
                int len = 0;
                while ((len = fr.read(buf,0,buf.length))!=-1){
                   // System.out.println(new String(buf,0,len));
                    text.append(new String(buf,0,len));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    void saveAsFile(){
        if (jfc.showSaveDialog(null )==0){
            File chooseFile = jfc.getSelectedFile();
            try {
                FileWriter fw = new FileWriter(chooseFile);
                fw.write(text.getText());
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("open")){
            openFile();
        }
        else if (e.getActionCommand().equals("save as")){
            saveAsFile();
        }
    }
}
