package lab2;

import java.io.*;                 // for I/O
import java.lang.Integer;  
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


/**
 * This is a skeleton class that you can use for guidance in starting the lab.
 *
 * You should update any comments,
 * variable names, etc to follow the class coding conventions.
 */


abstract class Person
{
	public String Name;
	public String Phone;
	public int Age;
	public Person(String name, String phone, int age) {
		Name = name;
		Phone = phone;
		Age = age;
	}
	
	public double getSalary() {
		return 0;
	}
	public abstract String toString();
	public abstract int getTotalUnits();
	
}

class Faculty extends Person
{
	private double Salary;
	private static int NumberOfFaculty = 0;
	
	public Faculty(String name, String phone, int age, double salary) {
		super(name, phone, age);
		Salary = salary;
		NumberOfFaculty ++;
		teachingCourses = new ArrayList<Course>();
	}
	ArrayList<Course> teachingCourses;
	public int getTotalUnits()
	{
		int tunit = 0;
		for(Course c:teachingCourses)
		{
			tunit += c.getUnits();
		}
		return tunit;
		//return teachingCourses.stream().mapToInt(Course::getUnits).sum();
	}
	public double getSalary() {
		return Salary;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Faculty Name: "+Name+" Phone: "+Phone+" Age: "+Age+" Salary: "+Salary;
	}
	public static int getNumberOfFaculty() {
		return NumberOfFaculty;
	}
}

class Student extends Person
{
	private double GPA;

	public Student(String name, String phone, int age, double gPA) {
		super(name, phone, age);
		GPA = gPA;
		takingCourses = new ArrayList<Course>();
	}
	
	ArrayList<Course> takingCourses;
	public int getTotalUnits()
	{
		int tunit = 0;
		for(Course c:takingCourses)
		{
			tunit += c.getUnits();
		}
		return tunit;
		}
	public double getSalary() {
		return 0;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Student Name: "+Name+" Phone: "+Phone+" Age: "+Age+" GPA: "+GPA;

	}
	public String getName() {
		return Name;
	}
}

class Staff extends Person
{
	private double Salary;

	public Staff(String name, String phone, int age, double salary) {
		super(name, phone, age);
		Salary = salary;
	}

	public double getSalary() {
		return Salary;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Staff Name: "+Name+" Phone: "+Phone+" Age: "+Age+" Salary: "+Salary;
	}

	@Override
	public int getTotalUnits() {
		// TODO Auto-generated method stub
		//not getting units
		return 0;
	}	
}

class StaffStudent extends Staff
{
	private double GPA;
	private Date StartDate;
	public StaffStudent(String name, String phone, int age, double salary,
			double gPA, Date startDate) {
		super(name, phone, age, salary);
		GPA = gPA;
		StartDate = startDate;
	}
	public double getGPA() {
		return GPA;
	}
	public Date getStartDate() {
		return StartDate;
	}
	
	
}

class Course
{
	private String Number;
	private String Title;
	private int Units;
	private Faculty faculty;
	
	public Course(String number, String title, int units, Faculty faculty) {
		Number = number;
		Title = title;
		Units = units;
		this.faculty = faculty;
	}

	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public int getUnits() {
		return Units;
	}
	public void setUnits(int units) {
		Units = units;
	}
	
}

class Department
{
	String Name;
	static ArrayList<Person> persons;
	
	
	public Department(String name) {
		Name = name;
		persons = new ArrayList<Person>();
	}

	public void addFaculty(Faculty p)
	{
		persons.add(p);
	}
	public void addStudent(Student p)
	{
		persons.add(p);
	}
	public void addStaff(Staff p)
	{
		persons.add(p);
	}

	double getTotalSalary()
	{
		double tsalary = 0;
		for(Person p: persons)
		{
			if(p instanceof Student)
			{
				//not calculate salary
			}
			else
			{
				tsalary += p.getSalary();
			}
		}
		return tsalary;
	}

	void unitsPerFaculty()
	{
		int tunit = 0;
		for(Person p:persons)
		{
			if(p instanceof Faculty)
			{
				tunit += p.getTotalUnits();
			}
		}
		System.out.println("units per faculty is: "+ tunit/Faculty.getNumberOfFaculty());
	}

	public void showAllMembers() {
		// TODO Auto-generated method stub
		for(Person p:persons)
		{
			System.out.println(p);
		}
	}
}

public class DepartmentApplication
   {
   public static void main(String[] args) throws IOException
      {
      Department dept = new Department("ComputerScience");
      
      //  The following commented out code will help you 
      //  create the objects that you need.
      
      
      
		// Create faculty objects
		Faculty frankMoore = new Faculty("Frank Moore","472-5921",43,10000);	
		Faculty samHoward = new Faculty("Sam Howard","472-7222",55,9500);
		Faculty johnDoodle = new Faculty("John Doodle","472-6190",39,8600);
		dept.addFaculty(frankMoore);
		dept.addFaculty(samHoward);
		dept.addFaculty(johnDoodle);


		// Create student objects
		Student johnDoe = new Student("John Doe","472-1121",22,4.0);
		Student maryJones = new Student("Mary Jones","472-7322",32,3.80);
		Student leeJohnson = new Student("Lee Johnson","472-6009",19,3.65);
		dept.addStudent(johnDoe);
		dept.addStudent(maryJones);
		dept.addStudent(leeJohnson);
		

		// Create staff objects
		Staff frankGore = new Staff("Frank Gore","472-3321",33,4050);
		Staff adamDavis = new Staff("Adam Davis","472-7552",50,5500);
		Staff davidHeck = new Staff("David Heck","472-8890",29,3600);
		dept.addStaff(frankGore);
		dept.addStaff(adamDavis);
		dept.addStaff(davidHeck);

		// Create course objects	
		Course cs201 = new Course("cs201","programming",4, johnDoodle);
		Course cs360 = new Course("cs360","database",3, samHoward);
		Course cs404 = new Course("cs404","compiler",4, johnDoodle);
		Course cs240 = new Course("cs240","datastructure",2, johnDoodle);
		Course cs301 = new Course("cs301","Software engg",3, samHoward);
		Course cs450 = new Course("cs450","Advanced architecture",5,frankMoore);
		
		johnDoodle.teachingCourses.add(cs201);
		samHoward.teachingCourses.add(cs360);
		johnDoodle.teachingCourses.add(cs404);
		johnDoodle.teachingCourses.add(cs240);
		samHoward.teachingCourses.add(cs301);
		frankMoore.teachingCourses.add(cs450);
		
		johnDoe.takingCourses.add(cs201);
		johnDoe.takingCourses.add(cs360);
		johnDoe.takingCourses.add(cs404);
		johnDoe.takingCourses.add(cs301);
		maryJones.takingCourses.add(cs201);
		maryJones.takingCourses.add(cs404);
		maryJones.takingCourses.add(cs450);
		leeJohnson.takingCourses.add(cs201);
		leeJohnson.takingCourses.add(cs360);
		leeJohnson.takingCourses.add(cs240);
		leeJohnson.takingCourses.add(cs450);

		
		/*
		 * The above course objects will go inside either
		 * a faculty object, or a student object.  Not all of the course
		 *  objects go into the same object.
		 * 
		 *  You would have code that looks something like :
		 *  frankMoore.addCourse(cs450);
		 * 
		 *  The addCourse method would have to be written in
		 *  the faculty class.  Something similar would be done 
		 *  for students.
		 */
      
      /********************************************************/
      
      
      double totsalary = 0;

      while(true)
         {
         putText("Enter first letter of ");
         putText("getTotalSalary, showAllMembers, unitsPerFaculty or quit : ");
         int choice = getChar();
         switch(choice)
            {
            case 'g':
               totsalary=dept.getTotalSalary();
               putText("Total sum of all salaries is:");
               putText(String.valueOf(totsalary)+"\n");              
               break;
            case 's':
               dept.showAllMembers();
               break;
            case 'u':
               dept.unitsPerFaculty();
               break;
            case 'q': return;
            default:
               putText("Invalid entry\n");
            }  // end switch
         putText("Enter first letter of ");
         putText("Frank Moore, Sam Howard, John Doodle or quit : ");
         int choice2 = getChar();
         switch(choice2)
            {
            case 'f':
               findStudents(frankMoore);       
               break;
            case 's':
            	 findStudents(samHoward);
               break;
            case 'j':
            	 findStudents(johnDoodle);
               break;
            case 'q': return;
            default:
               putText("Invalid entry\n");
            }  // end switch
         }  // end while
      }  // end main()
   private static void findStudents(Faculty falculty) {
	   // TODO Auto-generated method stub
	  // HashSet stu = new HashSet<Student>();
	   for(Course c: falculty.teachingCourses)
	   {
		   findStudentByCourse(c);
		   
	   }
	   
   }
   private static void findStudentByCourse(Course c) {
	   // TODO Auto-generated method stub
	  // List<Student> stu = new ArrayList();
	   for(Person p:Department.persons)
	   {
		   if(p instanceof Student)
		   {
			   if(((Student) p).takingCourses.contains(c))
			   {
				  System.out.println(((Student) p).getName());
			   }
		   }
		   
	   }
	  // return stu;
   }
// -------------------------------------------------------------
   public static void putText(String s) //writes string s to the screen
      {
      System.out.println(s);
      }
// -------------------------------------------------------------
   public static String getString() throws IOException  //reads a string from the keyboard input
      {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      String s = br.readLine();
      return s;
      }
// -------------------------------------------------------------
   public static char getChar() throws IOException //reads a character from the keyboard input
      {
      String s = getString();
      return s.charAt(0);
      }

//-------------------------------------------------------------
   public static int getInt() throws IOException // reads an integers from the keyboard input
      {
      String s = getString();
      return Integer.parseInt(s);
      }
// -------------------------------------------------------------
   }  // end class 



