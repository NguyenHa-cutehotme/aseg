import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

public class demoase {
    private JButton button1;
    public JPanel jpanel;
    private JTextField textField1;

    public demoase() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    String t = chooser.getSelectedFile().getAbsolutePath();
                    String replaceString = t.replace("\\", "/");
                    System.out.println("You chose to open this file: " +
                           replaceString);
                    FileInputStream fin = null;
                    try {
                        fin = new FileInputStream(replaceString);
                        int i = 0;
                        while ((i = fin.read()) != -1) {
                            System.out.print((char) i);
                        }
                        fin.close();
                    } catch (Exception e1) {
                        System.out.println(e1);
                    } finally {
                        try {
                            fin.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                }
            }

        });
    }


}
