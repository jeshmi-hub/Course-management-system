    package coursemanangement;

    //importing necessary packages
    import java.io.File;
    import java.io.FileNotFoundException;
    import java.io.FileOutputStream;
    import java.io.PrintWriter;
    import java.util.ArrayList;
    import java.util.Scanner;

    public class Instructor {
        //variable initialization for instructor class
        private String firstName;
        private String lastName;
        private String num;
        private String email;
        private String students;
        ArrayList<Student> st=new ArrayList<>();

        //user input for the required variables
        public Instructor(){
            Scanner sc=new Scanner(System.in);

            System.out.print("Enter name: ");
            this.firstName=sc.nextLine();

            System.out.print("Enter last name: ");
            this.lastName=sc.nextLine();

            System.out.print("Enter email: ");
            this.email=sc.nextLine();

            System.out.print("Enter number: ");
             num=sc.nextLine();
            if(num.length()==10){
                System.out.println("Your information is: "+firstName+" "+lastName+" "+email+" " + num);
            }else{
                System.out.println("Invalid number");
            }

        }

        //constructor
        public Instructor(String firstName,String lastName, String email){
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        //this method allows instructor to view the modules they are assigned by the course administrator
        public void assignedModule() throws FileNotFoundException {
            System.out.println("The modules you will be teaching are:");
            File mod=new File("Teachers_assigned_modules.txt");
            Scanner sc=new Scanner(mod);
            while(sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }

        }

        //this method allows the instructor to view the student that are enrolled in there modules
        public void viewStudent()throws FileNotFoundException{
            System.out.println("Students in your module");
            File student = new File("Student_info.txt");
            Scanner sc = new Scanner(student);
            while (sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }

        }

        //this method allows instructor to assign marks to the students
        public void assignMarks(String stufirstName,String stulastName,int stugradeYear,int stuId)throws FileNotFoundException{
            st.add(new Student(stufirstName,stulastName,stugradeYear,stuId));
            Scanner in=new Scanner(System.in);
            String[] module=new String[4];
            System.out.println("Modules:");
            for(int i=0;i<4;i++){
                module[i]=in.nextLine();
            }
            float[] marks=new float[4];
            System.out.println("Marks obtained in the modules: ");
            for(int i=0;i<4;i++){
                marks[i]=in.nextFloat();
            }
            FileOutputStream fileout = new FileOutputStream("Students_marks.txt",true);
            PrintWriter prints = new PrintWriter(fileout);
            prints.println("Marks obtained by "+stufirstName+" "+stulastName+" "+"in each modules are:");
            for(int i=0;i<4;i++){
                prints.println(module[i] + " " + "=" + marks[i]);
            }
            prints.close();
        }

        //this method is useful for the students to know the modules along with there module instructor
        public void viewInstructor()throws FileNotFoundException{
            Scanner in = new Scanner(System.in);
            String course;
            System.out.println("Enter course name:");
            course = in.nextLine();
            String[] BSCmodules={"Machine learning","C++","Java","Data Science"};
            String[] BSCITmodules={"C","Django","Graphic Designing","Numerical methods and concurrency"};
            String[] BIMmodules ={"Digital Logic","Business studies","Web development","Networking"};
            String[] BSCteacher ={"Mr.Manjil Shrestha","Mr.Paulo Ceolho","Mr.John Green","Mr.Jay Asher"};
            String[] BSCITteacher={"Miss.Rupi Kaur","Miss.Sage Ruth","Miss.Sana Mario","Miss.Lia Tanchingo"};
            String[] BIMteacher={"Miss.Anacostia Piper","Mr.Prerak Manandhar","Mr.Kim Seokjin","Miss.Lisa Manoban"};

            FileOutputStream fileout = new FileOutputStream("tutor.txt",true);
            PrintWriter prints = new PrintWriter(fileout);
            prints.println("Teachers of each modules:");
            for(int i=0;i<4;i++){
                if(course.equals("BSC")){
                    prints.println(BSCmodules[i]+":"+BSCteacher[i]);
                }
                else if(course.equals("BSCIT")){
                    prints.println(BSCITmodules[i]+":"+BSCITteacher[i]);
                }
                else if(course.equals("BIM")){
                    prints.println(BIMmodules[i]+":"+BIMteacher[i]);
                }
                else{
                    System.out.println("The course you are trying to enter isn't available");
                }
            }
            prints.close();
        }

        public static void main(String[] args)throws FileNotFoundException {
            Instructor ins=new Instructor();
            ins.viewInstructor();
            ins.viewStudent();
            ins.assignMarks("Honey","Shrestha",2,2058989);
            ins.assignedModule();
        }

    }
