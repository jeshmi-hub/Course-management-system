    package coursemanangement;
    //it creates a frame for printing report card of students in result.txt plus th JArea.

    //codes for GUI of report card
    //importing all the necessary packages
    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.io.FileNotFoundException;
    import java.io.FileWriter;

    public class ReportCard extends JFrame implements ActionListener {
        //for taking name,student id,grade year, modules, marks, total, gpa,remarks,label9
        JLabel Name,StudentID,GradeYear, Modules, Marks, Total, GPA, Remarks, label9;
        JButton Submit;
        //for typing the required name,studentID,gradeYear,4 modules,4 marks, total, GPA, remarks
        JTextField NameField, StudentIDField, GradeYearField, ModuleField1, ModuleField2, ModuleField3,ModuleField4,
                MarksField1,MarksField2,MarksField3,MarksField4, TotalField, GPAField;
        //to decide whether the student passed or not
        JRadioButton Passed,Failed;
        //it displays the information we entered
        JTextArea Display;
        //it writes the information in a file
        FileWriter printWriter;



        ReportCard(){
            //the codes here are for the framework
            Container a= getContentPane();
            a.setLayout(null);

            Name = new JLabel("Name");
            Name.setBounds(20,50,100,20);
            a.add(Name);

            NameField = new JTextField();
            NameField.setBounds(130,60,100,20);
            a.add(NameField);


            StudentID= new JLabel("StudentID");
            StudentID.setBounds(20,100,100,20);
            a.add(StudentID);

            StudentIDField = new JTextField();
            StudentIDField.setBounds(130,100,100,20);
            a.add(StudentIDField);

            GradeYear= new JLabel("Grade Year");
            GradeYear.setBounds(20,150,100,20);
            a.add(GradeYear);

            GradeYearField = new JTextField();
            GradeYearField.setBounds(130, 140, 100, 20);
            a.add(GradeYearField);

            Modules=new JLabel("Modules");
            Modules.setBounds(20,200,100,20);
            a.add(Modules);

            ModuleField1=new JTextField();
            ModuleField1.setBounds(20,220,150,20);
            a.add(ModuleField1);
            ModuleField2=new JTextField();
            ModuleField2.setBounds(20,250,150,20);
            a.add(ModuleField2);
            ModuleField3=new JTextField();
            ModuleField3.setBounds(20,280,150,20);
            a.add(ModuleField3);
            ModuleField4=new JTextField();
            ModuleField4.setBounds(20,310,150,20);
            a.add(ModuleField4);


            Marks=new JLabel("Marks");
            Marks.setBounds(200,200,100,20);
            a.add(Marks);

            MarksField1=new JTextField();
            MarksField1.setBounds(200,220,100,20);
            a.add(MarksField1);
            MarksField2=new JTextField();
            MarksField2.setBounds(200,250,100,20);
            a.add(MarksField2);
            MarksField3=new JTextField();
            MarksField3.setBounds(200,280,100,20);
            a.add(MarksField3);
            MarksField4=new JTextField();
            MarksField4.setBounds(200,310,100,20);
            a.add(MarksField4);

            Total=new JLabel("Total");
            Total.setBounds(20,360,100,20);
            a.add(Total);

            TotalField=new JTextField();
            TotalField.setBounds(130,360,100,20);
            a.add(TotalField);

            GPA= new JLabel("G.P.A");
            GPA.setBounds(20,410,100,20);
            a.add(GPA);

            GPAField=new JTextField();
            GPAField.setBounds(130,410,100,20);
            a.add(GPAField);

            Remarks= new JLabel("Remarks");
            Remarks.setBounds(20,460,100,20);
            a.add(Remarks);

            Passed=new JRadioButton("Passed");
            Failed=new JRadioButton("Failed");

            Passed.setBounds(130,460,80,20);
            Failed.setBounds(220,460,80,20);
            Passed.setSelected(true);

            a.add(Passed);
            a.add(Failed);


            ButtonGroup Remarks= new ButtonGroup();
            Remarks.add(Passed);
            Remarks.add(Failed);


            Submit= new JButton("Submit");
            Submit.setBounds(150,510,80,20);
            a.add(Submit);

            Submit.addActionListener(this);

            Display= new JTextArea();
            Display.setBounds(400,50,300,300);
            a.add(Display);

            //this makes the framework visible
            setVisible(true);
        }
        public static void main(String[] args)throws FileNotFoundException  {
            ReportCard frame = new ReportCard();
            frame.setTitle("Report Card");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setSize(800,600);
        }


    //this method works for the submit button. On clicking it makes the information visible on the JArea
        public void actionPerformed(ActionEvent a) {
            if (a.getActionCommand() == Submit.getActionCommand()) {
                try {
                    String Name = NameField.getText();
                    String StudentID = StudentIDField.getText();
                    String GradeYear = GradeYearField.getText();
                    String Module1 = ModuleField1.getText();
                    String Module2 = ModuleField2.getText();
                    String Module3 = ModuleField3.getText();
                    String Module4 = ModuleField4.getText();
                    String Marks1 = MarksField1.getText();
                    String Marks2 = MarksField2.getText();
                    String Marks3 = MarksField3.getText();
                    String Marks4 = MarksField4.getText();
                    String TotalMarks = TotalField.getText();
                    String Gpa = GPAField.getText();

                    String Result = "Passed";
                    if (Failed.isSelected()) {
                        Result = "Failed";
                    }
                    Display.setText("Name : " + Name + "\n" + "StudentID:" + StudentID + "\n" + "Grade Year:" + GradeYear + "\n" +
                            Module1 + ":" + Marks1 + "\n" + Module2 + ":" + Marks2 + "\n" + Module3 + ":" + Marks3 + "\n" + Module4 + ":" + Marks4 + "\n" +
                            "TotalMarks:" + TotalMarks + "\n" + "GPA:" + Gpa + "\n" + Result);

                    //it takes the information in a text file
                    printWriter = new FileWriter("Result.txt",true);
                    printWriter.write("\n"+"Name"+":"+NameField.getText());
                    printWriter.write("\n"+"StudentID:"+StudentIDField.getText());
                    printWriter.write("\n"+"Grade Year:"+GradeYearField.getText());
                    printWriter.write("\n"+ModuleField1.getText()+":"+MarksField1.getText());
                    printWriter.write("\n"+ModuleField2.getText()+":"+MarksField2.getText());
                    printWriter.write("\n"+ModuleField3.getText()+":"+MarksField3.getText());
                    printWriter.write("\n"+ModuleField4.getText()+":"+MarksField4.getText());
                    printWriter.write("\n"+"Total Marks:"+TotalField.getText());
                    printWriter.write("\n"+"GPA"+GPAField.getText());
                    printWriter.close();
                    JOptionPane.showMessageDialog(null,"File is successfully created");
                } catch (Exception b) {
                    JOptionPane.showMessageDialog(null, b + "File could not be created");

                }
            }
        }
    }

