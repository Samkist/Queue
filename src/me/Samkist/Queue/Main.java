package me.Samkist.Queue;


import BreezySwing.GBFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends GBFrame {

    private static JFrame frame = new Main();
    private JTextField inputField = addTextField("", 1,1 ,2 ,1);
    private JButton inputButton = addButton("Input", 2, 1, 1, 1);
    private JButton resetButton = addButton("Reset", 2, 2, 1,1);
    private JButton exitButton = addButton("Exit", 3, 1, 2, 1);
    private JTextArea outputField = addTextArea("Numbers: \n", 6,0,2,2);

    public static void main(String[] args) {
        frame.setSize(400, 400);
        frame.setTitle("Radix Sort");
        frame.setVisible(true);
    }

    public Main() {
        outputField.setEditable(false);
    }

    private void updateField(ArrayList<String> nums) {
        outputField.setText("Numbers: \n");
        if(nums.size() == 0) return;
        StringBuffer buf = new StringBuffer();
        nums.forEach(n -> buf.append(n + ", "));
        outputField.append(buf.toString().substring(0, buf.toString().length()-2));
    }


    public void buttonClicked(JButton jButton) {
        if(jButton.equals(inputButton)) {
            Radix radix = new Radix();
            ArrayList<String> nums = new ArrayList<>(Arrays.asList(inputField.getText()
                    .replaceAll(" ", "").split(",")));
            radix.sort(nums);
            updateField(nums);
            inputField.grabFocus();
        }
        if(jButton.equals(resetButton)) {
            inputField.setText("");
            updateField(new ArrayList<>());
            inputField.grabFocus();
        }
        if(jButton.equals(exitButton)) {
            System.exit(0);
        }
    }
}
