package lab5_hw4;

import java.text.NumberFormat;
import java.util.*;

class DateRange
{
	private  Calendar startDate;
	private  Calendar endDate;
	
	public DateRange(Calendar startDate, Calendar endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Calendar getStartDate() {
		return startDate;
	}
	public Calendar getEndDate() {
		return endDate;
	}
	boolean isInRange(Date d)
	{
		if(d.before(endDate.getTime()) && d.after(startDate.getTime()))
		{
			return true;
		}
		return false;
	}
	public String toString()
	{
		return startDate.getTime().toString()+"-"+endDate.getTime().toString();
	}
	
	public static int getFirstDayOfMonth(Date d)
	{
		return 1;
	}
	
	public static int getLastDayOfMonth(Date d)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int t = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return t;
	}
}

class Paycheck
{
	private double frossPay;
	private double FICA;
	private double State;
	private double Local;
	private double Medicare;
	private double Social;
	private DateRange payPeriod;
	
	public Paycheck(double frossPay, double fICA, double state, double local,
			double medicare, double social, DateRange payPeriod) {
		this.frossPay = frossPay;
		FICA = fICA;
		State = state;
		Local = local;
		Medicare = medicare;
		Social = social;
		this.payPeriod = payPeriod;
	}
	public void print()
	{
		NumberFormat currencyFormatA = NumberFormat  		   
                .getCurrencyInstance(Locale.US);  
		System.out.println("=====================Pay Check=====================");
		System.out.println("Gross Pay: "+currencyFormatA.format(frossPay)+"\nFICA: %"+FICA*100+"\nState: %"+State
				+"\nLocal: %"+Local*100+"\nMedicare: %"+Medicare*100+"\nSocial :%"+Social*100+"\n"
				+payPeriod.toString()+"\nNet Pay: "+currencyFormatA.format(getNetPay()));
	}
	public double getNetPay()
	{
		return frossPay-FICA*frossPay-State*frossPay-Local*frossPay-Medicare*frossPay-Social*frossPay;
	}
}

abstract class Employee
{
	protected String empId;
	
	Paycheck calcCompensation(int month,int year)
	{
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		to.set(year, month, 1);
		DateRange rg = new DateRange(from,to);
		
		return new Paycheck(calcGrossPay(rg),0.23,0.05,0.01,0.03,0.075,rg);
	}
	abstract double  calcGrossPay(DateRange range);

	
	void print()
	{
		System.out.println("Employee ID: "+empId);
	}
	
}

class HourlyEmployee extends Employee
{
	private double hourlyWage;
	private double hourPerMonth;
	
	public HourlyEmployee(double hourlyWage, double hourPerWeek) {
		this.hourlyWage = hourlyWage;
		this.hourPerMonth = hourPerWeek;
	}



	@Override
	double calcGrossPay(DateRange range) {
		// TODO Auto-generated method stub
		int months = Math.abs(range.getEndDate().get(Calendar.MONTH)-range.getStartDate().get(Calendar.MONTH));
		
		double fabsMonthPer = Math.abs((DateRange.getLastDayOfMonth(range.getStartDate().getTime())
				-range.getStartDate().get(Calendar.DAY_OF_MONTH))/DateRange.getLastDayOfMonth(range.getStartDate().getTime()));
	
		double labsMonthPer = Math.abs((DateRange.getLastDayOfMonth(range.getEndDate().getTime())
				-range.getEndDate().get(Calendar.DAY_OF_MONTH))/DateRange.getLastDayOfMonth(range.getEndDate().getTime()));
		
		return hourlyWage*hourPerMonth*(months+fabsMonthPer+labsMonthPer);
	}
	
}


class SalariedEmployee extends Employee
{
	private double salary;
	
	public SalariedEmployee(double salary) {
		this.salary = salary;
	}

	@Override
	double calcGrossPay(DateRange range) {
		int months = Math.abs(range.getEndDate().get(Calendar.MONTH)-range.getStartDate().get(Calendar.MONTH));
		
		double fabsMonthPer = Math.abs((DateRange.getLastDayOfMonth(range.getStartDate().getTime())
				-range.getStartDate().get(Calendar.DAY_OF_MONTH))/DateRange.getLastDayOfMonth(range.getStartDate().getTime()));
	
		double labsMonthPer = Math.abs((DateRange.getLastDayOfMonth(range.getEndDate().getTime())
				-range.getEndDate().get(Calendar.DAY_OF_MONTH))/DateRange.getLastDayOfMonth(range.getEndDate().getTime()));

		return salary * (months+fabsMonthPer+labsMonthPer);
	}
	
}

class Order
{
	private String orderNo;
	private String orderDate;
	private String orderAmount;
	public Order(String orderNo, String orderDate, String orderAmount) {
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.orderAmount = orderAmount;
	}
	
}
class CommissionedEmployee extends Employee
{
	private List<Order> commission;
	private double baseSalary;
	
	public CommissionedEmployee(double baseSalary) {
		this.baseSalary = baseSalary;
		commission = new ArrayList<Order>();
		Order ot = new Order("1","20150101","1");
		this.addOrder(ot);
		this.addOrder(ot);
		this.addOrder(ot);
	}

	public void addOrder(Order d)
	{
		commission.add(d);
	}


	@Override
	double calcGrossPay(DateRange range) {
		// TODO Auto-generated method stub
		int months = Math.abs(range.getEndDate().get(Calendar.MONTH)-range.getStartDate().get(Calendar.MONTH));
		
		double fabsMonthPer = Math.abs((DateRange.getLastDayOfMonth(range.getStartDate().getTime())
				-range.getStartDate().get(Calendar.DAY_OF_MONTH))/DateRange.getLastDayOfMonth(range.getStartDate().getTime()));
	
		double labsMonthPer = Math.abs((DateRange.getLastDayOfMonth(range.getEndDate().getTime())
				-range.getEndDate().get(Calendar.DAY_OF_MONTH))/DateRange.getLastDayOfMonth(range.getEndDate().getTime()));
		
		return baseSalary*(months+fabsMonthPer+labsMonthPer) + commission.size()*15;
	}
	
}
public class lab5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar from = Calendar.getInstance();
		from.set(2015, 6, 1);
		Calendar to = Calendar.getInstance();
		Employee e1 = new HourlyEmployee(1000,2);
		Employee e2 = new SalariedEmployee(1000);
		Employee e3 = new CommissionedEmployee(400);

		
		e1.calcCompensation(2015,1).print();
		e2.calcCompensation(2015,1).print();
		e3.calcCompensation(2015,1).print();
	}

}
