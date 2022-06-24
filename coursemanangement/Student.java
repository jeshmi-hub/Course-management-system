    package coursemanangement;
    /*Values are taken from the user input than the information is displayed after the values are taken. Then enroll method is called where modules are
    taken from user input and if the student is in level 6 and above the students are allowed to choose from four modules and for that Hashset is used.
    than all the  modules you  have entered are displayed. Method for infoStudent runs where student information is stored in a file
     */

    //importing all the packages needed for the codes
    import java.io.File;
    import java.io.FileNotFoundException;
    import java.io.FileOutputStream;
    import java.io.PrintWriter;
    import java.util.ArrayList;
    import java.util.HashSet;
    import java.util.Scanner;

        public class Student {
            //variables for Student registration
            private String firstName;
            private String lastName;
            private int gradeYear;
            private int studentId;
            private String course;
            private String modules = null;//default value for modules

            //Constructor for Student so that it can be easier to access the variables initialised in this class from another class
            public Student(String stufirstName,String stulastName,int stugradeYear,int stuId) {
                this.firstName = stufirstName;
                this.lastName = stulastName;
                this.gradeYear = stugradeYear;
                this.studentId = stuId;
            }
            //method that takes student information
            public Student() {
                Scanner in = new Scanner(System.in);
                System.out.print("Enter name: ");
                this.firstName = in.nextLine();

                System.out.print("Enter surname: ");
                this.lastName = in.nextLine();

                System.out.print("Enter course you want to enroll:");
                this.course = in.nextLine();

                System.out.println("1- level4\n2- level5\n3- level6");
                this.gradeYear = in.nextInt();
            }

            //method that takes student level and enrolls them in a course with specific modules
            public void enroll() {
                System.out.println("The course you enrolled: " + course);
                Scanner in = new Scanner(System.in);
                int level;
                System.out.println("Enter the level you're in:");
                level = in.nextInt();
                //the do while loop breaks only if capital Q is entered on the console
                do {
                    System.out.println("Enter the module name according to your enrolled course:");
                    String module = in.nextLine();
                    if (!module.equals("Q")) {
                        modules = modules + "\n" + module;
                        if (level >= 6) {//if level is typed 6 on the console the following messages are displayed
                            System.out.println("Since you're in level 6 and higher than level 6 you can choose two modules:");
                            String[] BSC = {"Software Engineering", "Android development", "Web designing", "Data Analysis"};
                            String[] BSCIT = {"Concurrency", "Python", "OOP", "Graphic designing"};
                            String[] BIM = {"Finance", "Economics", "Linear Algebra", "C"};
                            HashSet<String> select = new HashSet<>();//the hashset collection helps to choose the two optional modules
                            if (course.equals("BSC")) { //this system works for only BSC,BIM,BSCIT
                                for (String B : BSC) {
                                    System.out.println(B);
                                }
                            } else if (course.equals("BSCIT")) {
                                for (String BS : BSCIT) {
                                    System.out.println(BS);
                                }
                            } else if (course.equals("BIM")) {
                                for (String BM : BIM) {
                                    System.out.println(BM);
                                }
                            } else {
                                System.out.println("The course you have entered is not available.");
                            }
                            if (course.equals("BSC")) {//The modules from the above array can be selected and add in the modules for the level 6 student
                                System.out.println("Enter the optional modules you want to take from the above options:");
                                select.add("Android development");
                                select.add("Web designing");
                            } else if (course.equals("BSCIT")) {
                                System.out.println("Enter the optional modules you want to take from the above options:");
                                select.add("Concurrency");
                                select.add("Python");
                            } else if (course.equals("BIM")) {
                                System.out.println("Enter the optional modules you want to take from the above options");
                                select.add("Finance");
                                select.add("C");
                            } else {
                                System.out.println("The course you have entered is not available.");
                            }
                            //the selected modules by the students gets displayed in the [array form]
                            System.out.println("The modules you have selected are:" + select + "\n" + "Other two modules are mandatory");

                        } else {//if the student level less than level 6 this message gets displayed
                            System.out.println("The modules in your level are mandatory");
                        }
                    } else {
                        break;//Entering Q breaks the loop so please do break it after entering 4 module names

                    }
                }while(1!=0);
                System.out.println("Enrolled in: " + modules);//all the entered modules in the console gets stored and displayed altogether
           }

           //method that stores the student information and writes them in a file name Student_info.txt
           public void infoStudent()throws FileNotFoundException{
                String course; //course variables checks if the entered value is available in ths system or not
                Scanner sc=new Scanner(System.in);
               System.out.print("Enter the course you're enrolled:");
                course=sc.nextLine();
                String[] modules=new String[4];//array takes four module values from the user in the console
                System.out.println("Modules that you need to take:");
                if(course.equals("BSC")){
                    System.out.println("BSC Modules:");
                    for(int i=0;i<modules.length;i++){
                        modules[i]=sc.nextLine();
                    }
                }else if(course.equals("BSCIT")){
                    System.out.println("BSCIT Modules:");
                    for(int i=0;i<modules.length;i++){
                        modules[i]=sc.nextLine();
                    }
                }
                else if(course.equals("BIM")){
                    System.out.println("BIM Modules:");
                    for(int i=0;i<modules.length;i++){
                        modules[i]=sc.nextLine();
                    }
                }
                else{
                    System.out.println("There is no such course available.");
                }
                //file handling codes for storing student information
               FileOutputStream fileOut = new FileOutputStream("Student_info.txt",true);
               PrintWriter write = new PrintWriter(fileOut);//the information of ths students gets displayed in the following form
               write.println("Student registration information:");
               write.println(firstName+" "+lastName+" "+course);
               write.println("The modules you need to study:");
               for(String module: modules){
                   write.println(module);
               }
               write.close();//close the written file after the task is done

           }

            /*The view methods allows the student to check which instructor is responsible for teaching what module
            the tutor.txt file is opened in read mode so the values present in that file is displayed in the console for the students*/
          public void view() throws FileNotFoundException{
                System.out.println("Instructor assigned for each modules=");
                File obj=new File("tutor.txt");
                Scanner in=new Scanner(obj);

                while(in.hasNextLine()){
                    System.out.println(in.nextLine());
                }
            }

           //all the necessary methods for this class is called here in the main method
            public static void main(String[] args) throws FileNotFoundException {
                Student st=new Student();//object for the student class
                //the object so formed allows the user to call all the methods written within the class
                st.enroll();
                st.infoStudent();
                st.view();
            }
        }


