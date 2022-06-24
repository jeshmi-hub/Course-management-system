package coursemanangement;

//importing necessary packages for the code.
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

//The classes Courses and Modules are made to perform polymorphism by using the method name addRemove
class Courses{
    public void addRemove(){
        ArrayList<String> list1=new ArrayList<>();
        list1.add("BSC");
        list1.add("BSCIT");
        list1.add("BIM");
        list1.add("BBS");

        list1.remove("BBS");
        System.out.println("The list of courses available: "+list1);
    }
}

class Modules extends Courses{
    public void addRemove(){
        ArrayList<String> BSCmodule=new ArrayList<>();
        BSCmodule.add("Machine Learning");
        BSCmodule.add("Data Science");

        ArrayList<String>BSCITmodule = new ArrayList<>();
        BSCITmodule.add("C");
        BSCITmodule.add("C++");

        ArrayList<String>BIMmodule = new ArrayList<>();
        BIMmodule.add("Business");
        BIMmodule.add("Digital Logic");

        BSCmodule.remove("Data Science");
        System.out.println("Modules in each course for first semester:"+ BSCmodule +" "+ BSCITmodule +" "+ BIMmodule);
    }
}

// Abstract class for add and remove instructor
abstract  class addInstructor{
    public void add(String firstName,String lastName,String email){
        ArrayList<Instructor> addInstruct = new ArrayList<>();
        System.out.println("Please enter the name and email of the instructor you want to add:");
        addInstruct.add(new Instructor(firstName,lastName,email));


    }
    // To remove one specific instructor this abstract class is made
    public abstract void remove(String firstName,String lastName,String email);

}

//Every instructor have there own abilities and difference. This method allows us to remove the instructor by passing the 3 parameters by using arrayList
class removeInstructor extends addInstructor{
    public  void remove(String firstName,String lastName,String email){
        ArrayList<Instructor> removeInstruct = new ArrayList<>();
        System.out.println("Enter the first name,last name and email of the instructor you want to remove");
        removeInstruct.remove(new Instructor(firstName,lastName,email));

    }
}

//This class allows us to cancel the courses and modules that we no longer want
class Cancel {
    public String course;
    public String module;

    public void cancelCourse(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the course name you want to cancel:");
        course = input.nextLine();
        System.out.println(course+" is canceled for now.It might get available later.");
    }

    public void cancelModule(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the module name you want to cancel:");
        module = input.nextLine();
        System.out.println(module+" is cancel for now.It might get available later.");
    }
}

//The course administrator class is extending the gui i.e. ReportCard
public class Administrator extends ReportCard {
    //variables initialization for course administrator
    private String firstName;
    private String lastName;
    private String email;
    private String number;
    ArrayList<Student> student = new ArrayList<>();
    ArrayList<Instructor> teach = new ArrayList<>();

    public Administrator() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter first name: ");
        this.firstName = in.nextLine();

        System.out.print("Enter last name: ");
        this.lastName = in.nextLine();

        System.out.print("Enter your number: ");
        number = in.nextLine();
        if (number.length()==10) {
            System.out.println("your number is: " + number);
        } else {
            System.out.println("Invalid number");
        }
        System.out.print("Enter email: ");
        this.email = in.nextLine();

        System.out.println("Administrator information:"+firstName+" "+lastName+" "+number+" "+email);
    }

    //method for changing course name using an array. Once you input a name of the course it changes accordingly
    public void changeCourseName() {
        String[] cName = {"BCA", "BSCIT", "BIM", "BBS"};
        System.out.println("Enter the name of the course you want to change:");
        for (int i = 0; i < cName.length; i++) {
            if (cName.equals("BBS")) {
                cName[i] = "BBA";
            }
            System.out.println(cName[i]);
        }
    }

    //method for changing course name using an array. Once you input a name of the course it changes accordingly
    public void changeModuleName() {
        String[] mName = {"Machine Learning", "Data Structures", "Business", "Data Science"};
        System.out.println("Enter the name of the module you want to change:");
        for (int i = 0; i < mName.length; i++) {
            if (mName[i].equals("Machine Learning")) {
                mName[i] = "Python";
            }
            System.out.println(mName[i]);
        }
    }

    //method to allow the administrator to assign the module to a specific instructor
    public void assignModules(String firstName,String lastName,String email) throws FileNotFoundException {
        teach.add(new Instructor(firstName,lastName,email));
        Scanner in = new Scanner(System.in);
        String[] module = new String[4];
        System.out.println("Assign modules to the instructors:");
        for (int i = 0; i < 4; i++) {
            module[i] = in.nextLine();
        }
        FileOutputStream fileout = new FileOutputStream("Teachers_assigned_modules.txt", true);
        PrintWriter prints = new PrintWriter(fileout);
        prints.println("Modules taught by: " + "" + firstName + " " + lastName + " " + "are: ");
        for (int i = 0; i < 4; i++) {
            prints.println(module[i]);
        }
        prints.close();
    }

    //method to view the marks given by an instructor to a particular student
    public void recieveMarks() throws FileNotFoundException {
        System.out.println("Marks obtained by the students:");
        File file = new File("Students_marks.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }

    }

    //method to grade a student according to their marks to decide if they progressed to the next level or not
    public void assignRemarks(String studentfirstName, String studentlastName, int studentgradeyear, int studentID) throws FileNotFoundException {
        student.add(new Student(studentfirstName, studentlastName, studentgradeyear, studentID));
        Scanner in = new Scanner(System.in);
        String[] module = new String[4];
        float[] marks = new float[4];
        String[] grade = new String[4];
        System.out.println("Report card of the student:");
        File file = new File("Result.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
        System.out.println("Assign modules and marks from Result.txt");
        System.out.println("Module:");
        for (int i = 0; i < 4; i++) {
            module[i] = in.nextLine();
        }
        System.out.println("Marks:");
        for(int i=0;i<4;i++){
            marks[i] = in.nextFloat();
            System.out.println(module[i]+":"+marks[i]);
        }
        System.out.println("Assign grades according to the marks:");
        FileOutputStream fos = new FileOutputStream("Remarks.txt",true);
        PrintWriter print = new PrintWriter(fos);
        print.println(studentfirstName + " " + studentlastName+" " + " sem:"+ studentgradeyear +" "+"level:"+ studentID);
        for (int i = 0; i < 4; i++) {
            if (marks[i] >= 70) {
                System.out.println(grade[i] = "A");
            } else if (marks[i] <= 69 && marks[i] >= 60) {
                System.out.println(grade[i] = "B");
            } else if (marks[i] <= 59 && marks[i] >= 40) {
                System.out.println(grade[i] = "C");
            } else {
                System.out.println(grade[i] = "D");
            }
            print.println( module[i] + ":" + marks[i] + ":" + grade[i]);
        }

        for (int i = 0; i < 4; i++) {
            if (grade[i].equals("D")) {
                print.println("Sorry!! you've failed the module so you didn't progress to the next level.Please prepare for the resit");
            } else {
                print.println("Congratulations!! You have progressed to the next level.");
            }
            print.close();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        Administrator admin = new Administrator();
        Courses course = new Courses();
        course.addRemove();
        Modules module = new Modules();
        module.addRemove();
        Cancel cancel = new Cancel();
        cancel.cancelCourse();
        cancel.cancelModule();
        removeInstructor re = new removeInstructor();
        re.add("Bishal", "Shrestha", "bishal@gmail.com");
        re.add("Deepson","Shrestha","deepson@gmail.com");
        re.remove("Sage","Manandhar","Sagemanandhar@gamil.com");
        admin.changeCourseName();
        admin.changeModuleName();
        admin.assignModules("Shyam","Karki","ramkarki@gmail.com");
        admin.recieveMarks();
        admin.assignRemarks("Srashta","Acharya",3,335446);


    }
}



