package lab3_hw3;

import java.text.NumberFormat;
import java.util.*;

class Carrier
{
	public static enum Company{UPS,US_Mail,FedEx};
	private Company curUse;
	
	public double calculateLowCost(double weight,String zone)
	{
		double minCost = 99999999;
		double curCost = calCost(weight,zone,Company.UPS);
		if(curCost<minCost)
		{
			minCost = curCost;
			curUse = Company.UPS;
		}
		curCost = calCost(weight,zone,Company.US_Mail);
		if(curCost<minCost)
		{
			minCost = curCost;
			curUse = Company.US_Mail;
		}
		curCost = calCost(weight,zone,Company.FedEx);
		if(curCost<minCost)
		{
			minCost = curCost;
			curUse = Company.FedEx;
		}
		return minCost;
	}
	
	private double calCost(double weight,String zone,Company co)
	{
		switch(co)
		{
		case UPS:
			return 0.45*weight;
		case US_Mail:
			if(weight<=1)
				return 1;
			else
				return 0.55*weight;
		case FedEx:
			if(zone.equals("IA") || zone.equals("MT") || zone .equals("OR") || zone .equals("CA"))
				return 0.35*weight;
			else if(zone.equals("TX") || zone.equals("UT"))
				return 0.30*weight;
			else if(zone.equals("FL") || zone.equals("MA") || zone.equals("OH"))
				return 0.55*weight;
			else
				return 0.43*weight;
		}
		assert(true);//never should be here
		return 0;
	}

	public Company getCurUse() {
		return curUse;
	}

}

class Order
{
	private String description;
	private double weight;
	private String zone;
	private double shipcost;
	private double discount;
	private Carrier carrier;
	public Order()
	{
		setDescription();
		setWeight();
		setZone();
		carrier = new Carrier();
		setShipcost();
		discount = 0;
	}
	public Order(String description, double weight, String zone) {
		this.description = description;
		this.weight = weight;
		this.zone = zone;
		carrier = new Carrier();
		setShipcost();
		discount = 0;
	}
	public void setDescription() {
		System.out.println("Type your order description: ");
		Scanner s = new Scanner(System.in);
		this.description = s.nextLine();
	}
	public void setWeight() {
		System.out.println("Type your weight: ");
		Scanner s = new Scanner(System.in);
		this.weight = s.nextDouble();
	}
	public void setZone() {
		System.out.println("Type your zone: ");
		Scanner s = new Scanner(System.in);
		this.zone = s.nextLine();
	}

	public double getShipcost() {
		return shipcost-discount;
	}
	
	public void setShipcost() {
		this.shipcost = carrier.calculateLowCost(weight, zone);
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public Carrier getCarrier() {
		return carrier;
	}
	
	public void print()
	{
		NumberFormat currencyFormatA = NumberFormat  		   
                .getCurrencyInstance(Locale.US);  
		System.out.println(description+"    "+currencyFormatA.format(getShipcost())+"     "+carrier.getCurUse());
	}
	
}

class Customer
{
	private String name;
	private boolean isStudent;
	private int age;
	private List<Order> orderList;
	public Customer(String name, boolean isStudent, int age) {
		this.name = name;
		this.isStudent = isStudent;
		this.age = age;
		orderList = new ArrayList<Order>();
		System.out.println("Customer: "+name+".\n Is student?: "+isStudent+".\n Age: "+age);
	}
	
	public void addOrder(Order order)
	{
		double totalDiscount = 0;
		if(isStudent)
			totalDiscount += order.getShipcost()*0.10;
		if(age>65)
			totalDiscount += order.getShipcost()*0.15;
		
		order.setDiscount(totalDiscount);
		orderList.add(order);
	}
	
	public void printOrderList()
	{
		for(Order or:orderList)
		{
			or.print();
		}
	}
	
	
}
public class hw3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//for level 1
		System.out.println("***********FOR LEVEL 1****************");
		System.out.println("Type how many orders do you have: ");
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		for(int i=0;i<n;i++)
		{
			Order newO = new Order();
			newO.print();
		}
		System.out.println("***********FOR LEVEL 2****************");
		Customer cus1 = new Customer("Tom",true,22);
		cus1.addOrder(new Order());
		cus1.addOrder(new Order());
		cus1.printOrderList();
		Customer cus2 = new Customer("Tim",true,88);
		cus2.addOrder(new Order());
		cus2.printOrderList();
	}
}
