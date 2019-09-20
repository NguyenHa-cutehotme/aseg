import javax.swing.*;

public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new demoase().jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
