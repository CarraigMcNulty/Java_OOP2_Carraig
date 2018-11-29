import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Calculator extends JFrame  {
    String[] operator = {"Sin", "Cos", "Tan", "Log", "(",")", "x\u207F", "x\u00B2", "c", "π","√", "ce", "-", "x", "\u00F7", "+"};

    JTextArea resultField = new JTextArea();

    GridBagConstraints tf;
    GridBagConstraints c;

    JPanel calcuLayout;

    calculatorButton equalsButton;
    calculatorButton dotButton;
    JTextArea historyField;

    public Calculator() {


        resultField.setPreferredSize(new Dimension(150, 75));
        resultField.setBackground(Color.white);
        resultField.setEditable(false);
        resultField.setFont(new Font("Monospaced", Font.PLAIN, 30));//create JTextarea set: Size,Color,not editable


        //create constraints tf and set width=10 y index = 1 and set width
        tf = new GridBagConstraints();

        tf.gridx = 1;
        tf.gridy = 1;
        tf.fill = GridBagConstraints.BOTH;
        tf.gridwidth = 4;

        calcuLayout = new JPanel();
        calcuLayout.setLayout(new GridBagLayout());
        calcuLayout.setSize(new Dimension(100, 300));

        c = new GridBagConstraints();

        c.gridy = 0;
        c.gridx = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 5;
        c.fill = GridBagConstraints.BOTH;

        calcuLayout.add(resultField, tf);


        c.gridwidth = 1;
        equalsButton = new calculatorButton();
        dotButton = new calculatorButton();

        String clearText = ".";
        String equal = "=";


        c.gridx = 1;
        c.gridy = tf.gridy + 7;
        dotButton.setText(clearText);
        calcuLayout.add(dotButton, c);

        dotButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e)
            {
                resultField.append(dotButton.getText());
            }
        });

        c.gridx = 3;

        equalsButton.setText(equal);
        calcuLayout.add(equalsButton, c);



        c.gridy = tf.gridy + 4;
        c.gridx = 3;

        for (int j = 9; j >= 0; j--) {
            calculatorButton numberButton = new calculatorButton();

            numberButton.setBackground(Color.lightGray);
            numberButton.setText(Integer.toString(j));

            c.fill = GridBagConstraints.BOTH;

            if (c.gridx == 0) {
                c.gridy += 1;
                c.gridx = 3;

            }

            if (j == 0) {
                c.gridx -= 1;
            }

            calcuLayout.add(numberButton, c);

            c.gridx = c.gridx - 1;

            numberButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    resultField.append(numberButton.getText());

                }
            });
        }


        //Allows operators to print to JtextArea
        c.gridy = tf.gridy + 1;
        c.gridx = 0;

        for (int i = 0; i < operator.length; i++) {

            calculatorButton operatorButton = new calculatorButton();

            operatorButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent event) {

                    resultField.append(operatorButton.getText());

                    if (operatorButton.getText().equals("ce"))
                    {
                        resultField.setText("");
                    }

                    else if (operatorButton.getText().equals("c"))
                    {
                        if (resultField.getText().length() > 1)
                        {
                            String tempString = resultField.getText().substring(0, resultField.getText().length() - 2);
                            resultField.setText(tempString);
                        }
                    }
                }
            });

            operatorButton.setText(operator[i]);
            c.fill = GridBagConstraints.BOTH;

            if (c.gridx >= 4 && c.gridy >= 4) {
                c.gridy += 1;
                c.gridx = 4;

            } else if (c.gridx == 4) {
                c.gridy += 1;
                c.gridx = 1;

            } else {

                c.gridx = c.gridx + 1;
            }



            calcuLayout.add(operatorButton, c);
        }

        calcuLayout.setVisible(true);

        setContentPane(calcuLayout);


        JLabel history = new JLabel("History");
        history.setAlignmentX(JLabel.CENTER_ALIGNMENT);



        historyField = new JTextArea("History");

        c.gridx = 10;
        c.gridy = 1;
        c.gridheight = 20;
        c.weighty=2;
        c.fill = GridBagConstraints.VERTICAL;

        historyField.setOpaque(false);

        calcuLayout.add(historyField,c);

        c.gridheight = 1;

        historyField.setEditable(false);

        setSize(640, 840);
        //setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       //pack();
        setVisible(true);
    }


    public class calculatorButton extends JButton
    {
        calculatorButton()
        {
            this.setPreferredSize(new Dimension(90, 90));
            this.setFont(new Font("Monospaced", Font.PLAIN, 20));
        }
    }

    public boolean isValidEquation() {

        char[] result = resultField.getText().toCharArray();
        int decimalCounter = 0;
        for (int i =0;i<result.length;i++)
        {

            if ((result[0] == ('.') || Character.isDigit(result[0]) || result[0] == ('-') || result[0] == ('√')) && Character.isDigit(result[result.length-1]))
            {
                if(((result[i] == '+' ||result[i] == 'x'||result[i] == '\u00F7') && (result[i-1] == '-'|| result[i-1] == '+' ||result[i-1]  == 'x'||result[i-1]  == '\u00F7')) )  //{"Sin", "Cos", "Tan", "Log", "x\u207F","(", ")", "x\u00B2", "c", "π","√", "ce", "-", "x", "\u00F7", "+"};
                {
//                        if((result[i] == 'C' && result[i+1] == 'o' &&  result[i+2]== 's' || result[i] == 'S' && result[i+1]== 'i' && result[i+2 ]== 'n')|| result[i] == 'T' && result[i+1]== 'a' &&result[i+2]== 'n')
//                        {
//
//                        }
                    return false;
                }
                else if(result[i] == '-' && i!=0 && (result[i-1] == '+' ||result[i-1]  == 'x'||result[i-1]  == '\u00F7' || result[i-1] == '-')){
                    return false;
                }

                else if(result[i] =='√' && result.length>1 && !Character.isDigit(result[i+1]) && result[i+1] !='-')
                {
                    return false;
                }


                else if (result[i] == '.')
                {
                    decimalCounter++;


                    if (decimalCounter > 1)
                    {
                        return false;
                    }

                }

            }
            else{
                return false;
            }


            if (result[i] == '+' || result[i] == '-' || result[i] == 'x' || result[i] == '\u00F7'|| result[i] == ('√'))
            {
                decimalCounter = 0;
            }


        }
        return true;
    }


    public ArrayList<Equation> open() {
        ArrayList<Equation> saveEquation = new ArrayList<Equation>();
        try{
            ObjectInputStream is;
            is = new ObjectInputStream(new FileInputStream("CalculatorHistory.dat"));
            saveEquation  = (ArrayList<Equation>) is.readObject();
            is.close();

            for(Equation e: saveEquation)
            {
                historyField.append(e.toString());
            }


        }

        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"FileNotFound: didn't work");
            e.printStackTrace();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"IOException: didn't work");
            e.printStackTrace();
        }

        return saveEquation;
    }
}

