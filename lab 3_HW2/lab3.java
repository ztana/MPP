package lab3;

import java.text.NumberFormat;
import java.util.*;

class Company
{
	private String name;
	private static Position topExecutive;
	
	public Company(String name) {
		this.name = name;
		departments = new ArrayList<Department>();
	}
	private List<Department> departments;
	void print()
	{
		System.out.println("Company: "+name);
		if(!departments.isEmpty())
		{
			for(Department de:departments)
			{
				System.out.print("  *  ");
				de.print();
			}
		}
	}
	void addDepartment(Department de)
	{
		departments.add(de);
	}
	public static Position getTopExecutive() {
		return topExecutive;
	}
	public void setTopExecutive(Position topExecutive) {
		this.topExecutive = topExecutive;
	}
	public double getSalary()
	{
		double totalSalary = 0;
		for(Department p:departments)
		{
			totalSalary += p.getSalary();
		}
		return totalSalary;
	}
	public void printReportingHierarchy()
	{
		for(Department dep:departments)
		{
			System.out.println("Department: "+dep.getName());
			if(getTopExecutive().equals(dep.getDepartmentHead().getSuperior()))
			{
				dep.printReportingHierarchy();
			}
		}
	}
}

class Department
{
	private String name;
	private String location;
	private List<Position> positions;
	
	public Department(String name, String location) {
		this.name = name;
		this.location = location;
		positions = new ArrayList<Position>();
	}

	Position getDepartmentHead()
	{
		Position ran = positions.get(0);
		while(!ran.getSuperior().equals(Company.getTopExecutive()))
		{
			ran = ran.getSuperior();
		}
		return ran;
	}
	void print()
	{
		System.out.println("Department Name: "+name+". Location: "+location);
		if(!positions.isEmpty())
		{
			for(Position p:positions)
			{
				System.out.print("   ->");
				p.print();
			}
		}
	}
	
	void addPosition(Position p)
	{
		positions.add(p);
	}
	
	public double getSalary()
	{
		double totalSalary = 0;
		for(Position p:positions)
		{
			totalSalary += p.getSalary();
		}
		return totalSalary;
	}
	public void printReportingHierarchy()
	{
		getDepartmentHead().printDownLine();
	}

	public String getName() {
		return name;
	}
}

class Position
{
	private String title;
	private String description;
	private Employee employee;
	private Position Superior;
	private List<Position> Inferior;
	private int Level;

	public Position(String title, String description) {
		this.title = title;
		this.description = description;
		Inferior = new ArrayList<Position>();
		Level = 0;
	}

	public boolean hasSuperior()
	{
		if(this.Superior == null)
			return false;
		else
			return true;
	}
	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public boolean positionOccupaid()
	{
		if(getEmployee() == null)
			return false;
		return true;
	}
	
	public void printDownLine()
	{
		print();
		if(!Inferior.isEmpty())
		{
			//System.out.print("  ");
			for(Position p:Inferior)
			{
				for(int i=0;i<p.Level;i++)
					System.out.print("  ");
				p.printDownLine();
			}
		}
	}


	void print()
	{
		if(!positionOccupaid())
		{
			System.out.println("      -This position is NOT filled.");
		}
		else
		{
			employee.print();
		}
	}
	
	public double getSalary()
	{
		return employee.getSalary();
	}


	public Position getSuperior() {
		return Superior;
	}


	public void setSuperior(Position superior) {
		Superior = superior;
	}
	
	public void addInterior(Position interior) {
		Inferior.add(interior);
	}


	public int getLevel() {
		return Level;
	}


	public void setLevel(int level) {
		Level = level;
	}
}

class Employee
{
	private String employeeId;
	private String firstName;
	private String middleInital;
	private String lastName;
	private String birthDate;
	private String SSN;
	private double salary;
	
	public Employee(String employeeId, String firstName, String middleInital,
			String lastName, String birthDate, String sSN, double salary) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.middleInital = middleInital;
		this.lastName = lastName;
		this.birthDate = birthDate;
		SSN = sSN;
		this.salary = salary;
	}

	public double getSalary() {
		return salary;
	}

	void print()
	{
		System.out.print("      -");
		NumberFormat currencyFormatA = NumberFormat  		   
                .getCurrencyInstance(Locale.US);  
		System.out.println("ID:"+employeeId+" "+firstName+" "+middleInital+" "+lastName+". Birthday: "+birthDate+" SSN: "+SSN+" salary: "+currencyFormatA.format(salary));
	}
}

public class lab3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//init
		Employee Tom = new Employee("001","Tom","TomMiddle","TomLast","01/01/1888","12345678",10000);
		Employee Stan = new Employee("002","Stan","StanMiddle","StanLast","02/02/1889","12345677",10001);
		Employee Sue = new Employee("003","Sue","SueMiddle","SueLast","03/03/1839","12345676",10002);
		Employee Marc = new Employee("004","Marc","MarcMiddle","Pullman","04/04/1849","12345676",10003);
		Employee Bill = new Employee("005","Bill","BillMiddle","BillLast","05/04/1849","12355676",10004);
		Employee Dan = new Employee("006","Dan","DanMiddle","DanLast","05/05/1859","12355976",10005);
		Employee Peter = new Employee("007","Peter","PeterMiddle","PeterLast","06/06/1859","12655976",10006);
		Employee Ron = new Employee("008","Ron","RonMiddle","RonLast","07/07/1759","12615976",10007);
		Employee Gray = new Employee("009","Gray","GrayMiddle","GrayLast","09/07/1559","13615976",10009);
		Employee Anne = new Employee("010","Anne","AnneMiddle","AnneLast","07/01/1059","16615976",10010);
		Employee Lyle = new Employee("011","Lyle","LyleMiddle","LyleLast","08/08/1159","11615976",10011);

		Position marketing1 = new Position("Marketing","marketing");
		Position marketing_head = new Position("Marketing","marketing head");
		Position saler1 = new Position("Sale","sale");
		Position saler2 = new Position("Sale","sale");
		Position saler3 = new Position("Sale","sale");
		Position saler4 = new Position("Sale","sale");
		Position saler5 = new Position("Sale","sale");
		Position saler6 = new Position("Sale","sale");
		Position saler7 = new Position("Sale","sale");
		Position saler_head = new Position("Sale","sale head");
		Position top_executive = new Position("Boss","head executive");
		
		Department SaleDepartment = new Department("Sale Department","Fairfield");
		Department MarketingDepartment = new Department("Marketing Department","Iowa City");
		
		Company com = new Company("Pure Conciousness Inc.");
		
		com.setTopExecutive(top_executive);
		com.addDepartment(SaleDepartment);
		com.addDepartment(MarketingDepartment);
		SaleDepartment.addPosition(saler1);
		SaleDepartment.addPosition(saler2);
		SaleDepartment.addPosition(saler3);
		SaleDepartment.addPosition(saler4);
		SaleDepartment.addPosition(saler5);
		SaleDepartment.addPosition(saler6);
		SaleDepartment.addPosition(saler7);
		SaleDepartment.addPosition(saler_head);
		MarketingDepartment.addPosition(marketing1);
		MarketingDepartment.addPosition(marketing_head);
		saler1.setEmployee(Tom);
		saler2.setEmployee(Sue);
		saler3.setEmployee(Marc);
		saler4.setEmployee(Stan);
		saler5.setEmployee(Bill);
		saler6.setEmployee(Dan);
		saler7.setEmployee(Peter);
		saler_head.setEmployee(Ron);
		marketing1.setEmployee(Gray);
		marketing_head.setEmployee(Anne);
		top_executive.setEmployee(Lyle);
		
		//lab 3.1
		System.out.println("***************FOR LAB 3.1****************");
		com.print();
		//lab 3.2
		System.out.println("***************FOR LAB 3.2****************");
		NumberFormat currencyFormatA = NumberFormat  		   
                 .getCurrencyInstance(Locale.US);  
		System.out.println("#Total salary of the company: "+currencyFormatA.format(com.getSalary()));
		
		//init for lab 3.3
		saler1.setSuperior(saler4);
		saler2.setSuperior(saler4);
		saler3.setSuperior(saler4);
		saler4.addInterior(saler1);
		saler4.addInterior(saler2);
		saler4.addInterior(saler3);
		saler4.setSuperior(saler_head);
		saler5.setSuperior(saler7);
		saler6.setSuperior(saler7);
		saler7.addInterior(saler5);
		saler7.addInterior(saler6);
		saler7.setSuperior(saler_head);
		marketing1.setSuperior(marketing_head);
		marketing_head.addInterior(marketing1);
		saler_head.setSuperior(top_executive);
		saler_head.addInterior(saler4);
		saler_head.addInterior(saler7);
		marketing_head.setSuperior(top_executive);
		top_executive.addInterior(saler_head);
		top_executive.addInterior(marketing_head);
		
		top_executive.setLevel(0);
		marketing_head.setLevel(1);
		saler_head.setLevel(1);
		saler7.setLevel(2);
		saler4.setLevel(2);
		saler1.setLevel(3);
		saler2.setLevel(3);
		saler3.setLevel(3);
		saler5.setLevel(3);
		saler6.setLevel(3);
		marketing1.setLevel(2);
		
		//lab 3.3.1
		System.out.println("**************FOR LAB 3.3.1****************");
		top_executive.printDownLine();
		//lab 3.3.2 & 3.3.3
		System.out.println("***********FOR LAB 3.3.2 & 3.3.3************");
		com.printReportingHierarchy();
	}

}
