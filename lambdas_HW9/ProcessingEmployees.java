package hw8;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessingEmployees
{
   public static void main(String[] args)
   {
      // initialize array of Employees
      Employee[] employees = {
         new Employee("Jason", "Red", 5000, "IT"),
         new Employee("Ashley", "Green", 7600, "IT"),
         new Employee("Matthew", "Indigo", 3587.5, "Sales"),
         new Employee("James", "Indigo", 4700.77, "Marketing"),
         new Employee("Luke", "Indigo", 6200, "IT"),
         new Employee("Jason", "Blue", 3200, "Sales"),
         new Employee("Wendy", "Brown", 4236.4, "Marketing")};

      // get List view of the Employees
      List<Employee> list = Arrays.asList(employees);

      // display all Employees
      System.out.println("Complete Employee list:");
      list.stream().forEach(System.out::println);
      
      // Predicate that returns true for salaries in the range $4000-$6000
      Predicate<Employee> fourToSixThousand = 
         e -> (e.getSalary() >= 4000 && e.getSalary() <= 6000);

      // Display Employees with salaries in the range $4000-$6000
      // sorted into ascending order by salary
      System.out.printf(
         "%nEmployees earning $4000-$6000 per month sorted by salary:%n");
      list.stream()
          .filter(fourToSixThousand)
          .sorted(Comparator.comparing(Employee::getSalary))
          .forEach(System.out::println);

      // Display first Employee with salary in the range $4000-$6000
      System.out.printf("%nFirst employee who earns $4000-$6000:%n%s%n",
         list.stream()
             .filter(fourToSixThousand)
             .findFirst()
             .get());

      // Functions for getting first and last names from an Employee
      Function<Employee, String> byFirstName = Employee::getFirstName;
      Function<Employee, String> byLastName = Employee::getLastName;

      // Comparator for comparing Employees by first name then last name
      Comparator<Employee> lastThenFirst = 
         Comparator.comparing(byLastName).thenComparing(byFirstName);

      // sort employees by last name, then first name 
      System.out.printf(
         "%nEmployees in ascending order by last name then first:%n");
      list.stream()
          .sorted(lastThenFirst)
          .forEach(System.out::println);

      // sort employees in descending order by last name, then first name
      System.out.printf(
         "%nEmployees in descending order by last name then first:%n");
      list.stream()
          .sorted(lastThenFirst.reversed())
          .forEach(System.out::println);

      // display unique employee last names sorted
      System.out.printf("%nUnique employee last names:%n");
      list.stream()
          .map(Employee::getLastName)
          .distinct()
          .sorted()
          .forEach(System.out::println);

      // display only first and last names
      System.out.printf(
         "%nEmployee names in order by last name then first name:%n"); 
      list.stream()
          .sorted(lastThenFirst)
          .map(Employee::getName)
          .forEach(System.out::println);

      // group Employees by department
      System.out.printf("%nEmployees by department:%n"); 
      Map<String, List<Employee>> groupedByDepartment =
         list.stream()
             .collect(Collectors.groupingBy(Employee::getDepartment));
      groupedByDepartment.forEach(
         (department, employeesInDepartment) -> 
         {
            System.out.println(department);
            employeesInDepartment.forEach(
               employee -> System.out.printf("   %s%n", employee));
         }
      );

      // count number of Employees in each department
      System.out.printf("%nCount of Employees by department:%n"); 
      Map<String, Long> employeeCountByDepartment =
         list.stream()
             .collect(Collectors.groupingBy(Employee::getDepartment, 
                TreeMap::new, Collectors.counting()));
      employeeCountByDepartment.forEach(
         (department, count) -> System.out.printf(
            "%s has %d employee(s)%n", department, count));

      // sum of Employee salaries with DoubleStream sum method
      System.out.printf(
         "%nSum of Employees' salaries (via sum method): %.2f%n",
         list.stream()
             .mapToDouble(Employee::getSalary)
             .sum());

      // calculate sum of Employee salaries with Stream reduce method
      System.out.printf(
         "Sum of Employees' salaries (via reduce method): %.2f%n",
         list.stream()
             .mapToDouble(Employee::getSalary)
             .reduce(0, (value1, value2) -> value1 + value2));  

      // average of Employee salaries with DoubleStream average method
      System.out.printf("Average of Employees' salaries: %.2f%n",
         list.stream()
             .mapToDouble(Employee::getSalary)
             .average()
             .getAsDouble());      
      //)  Count the number of last names that begin with the letter  ‘B’.  Print out this number.
      
      Predicate<Employee> startWith =
    		 e->(e.getLastName().substring(0,1).equals("B"));
      
      System.out.printf("Count the number of last names that begin with the letter ‘B’: %d.\n", 
    	 list.stream().filter(startWith).count()
    	 );
      System.out.println("Print out all of the Employee objects whose last name begins with the letter  ‘B’");
      list.stream().filter(startWith).forEach(System.out::println);
      
      System.out.println("change to all captial letters");
      list.stream()
      .filter(startWith)
      .forEach(value->System.out.println(value.toString().toUpperCase()));

      /*method 2
      System.out.printf("change to all captial letters : %s%n",
      list.stream()
      .filter(startWith)
      .map(value->value.toString().toUpperCase())
      .collect(Collectors.toList()));
      */
      
      System.out.println("change to all captial letters 2");
      list.stream()
      .forEach(Employee.consumers);

      System.out.printf("whose last name begins with the letter  ‘I’:%s\n",
    	list.stream()
    	.filter(n->n.getLastName().startsWith("I"))
    	.map(m->m.getLastName())
    	.distinct()
    	.sorted()
    	.collect(Collectors.toList())
    		  );
      
      //6
      System.out.printf("average of all the salaries:%.2f\n",
    		  list.stream()
    	    	.mapToDouble(Employee::getSalary)
    	    	.average().getAsDouble()
      );
      
      //7
      System.out.println("the total salary of all employees:  "+
    		  list.stream()
  	    	.mapToDouble(Employee::getSalary)
  	    	.reduce((x,y)->x+y).getAsDouble()
      );
      
      //8
      System.out.println("first names of all the employees:  "
      		+ list.stream()
      		.map(x->x.getFirstName())
      		.collect(Collectors.toList())
    		  );
      
      //9
      System.out.println("find, and later print out, the total of all salaries: "
      		+ list.stream()
      		.mapToDouble(Employee::getSalary)
      		.reduce((x,y)->x+y).getAsDouble()
      		);
      
      //10
    //  Stream<Integer> echoes =  Stream.iterate(0, n -> n + 2);
      System.out.println("an infinite stream of even numbers :  "+
    		  Stream.iterate(0, n -> n + 2).limit(20).collect(Collectors.toList()));
     // echoes.forEach(System.out::println);
      
      //b-3
      System.out.println("contain the character c, and that do not contain the character d: "+
    		  countWords(list.stream()
    		  .map(e->e.getFirstName())
    		  .collect(Collectors.toList()),'s','n',list.size()
    		  ));
   } // end main

   public static int countWords(List<String> words, char c, char d, int len)
   {
	   return
	   (int) words.stream()
	   .filter(e->e.contains(Character.toString(c)))
	   .filter(e->!e.contains(Character.toString(d)))
	   .count();
   }
} // end class ProcessingEmployees

