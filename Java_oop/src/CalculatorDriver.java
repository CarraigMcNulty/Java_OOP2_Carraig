import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class CalculatorDriver {

   static ArrayList<Equation> saveEquation = new ArrayList<>();
    public static void main(String[] args) {

        Calculator calculator  = new Calculator();

        saveEquation = calculator.open();  //returns array list into save equation

        calculator.equalsButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e)
            {
                if (calculator.isValidEquation()) {

                    Equation equation = new Equation(calculator.resultField.getText());

                    if (saveEquation.size() == 5)
                    {
                        saveEquation.remove(0);

                    }
                    saveEquation.add(equation);

                    calculator.resultField.setText(equation.getResultAsString());

                    calculator.historyField.append(equation.toString());

                    save();


                }

                else if (!calculator.isValidEquation())
                {
                    calculator.resultField.setText("Error");
                }
            }
        });





    }

    public static void save(){
        try {
            ObjectOutputStream os;
            os = new ObjectOutputStream(new FileOutputStream("CalculatorHistory.dat"));
            os.writeObject(saveEquation);
            os.close();
        }

        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"FileNotFound: Save  didn't work");
            e.printStackTrace();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"IOException: Save didn't work");
            e.printStackTrace();
        }
    }

   }
