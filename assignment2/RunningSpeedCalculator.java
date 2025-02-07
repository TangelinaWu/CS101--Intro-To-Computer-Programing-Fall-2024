import java.util.Scanner ;

public class RunningSpeedCalculator {
	public static void main(String[] args) {
		double kmPerMile = 1.609344;
		
		Scanner scnr = new Scanner(System.in);
		System.out.println("How many kilometers did you run?");
		String input = scnr.nextLine();
		
		double kilometers = Double.parseDouble(input);
		double miles = kilometers / kmPerMile;

		
		
		Scanner scnr2 = new Scanner(System.in);
		System.out.println("How many minutes did it take you?");
		String input2 = scnr2.nextLine();
		double minutes = Double.parseDouble(input2);
		
		double hours = minutes / 60;

		double averageSpeed = miles / hours;
		
		System.out.println("Your average speed was "+ averageSpeed + " miles per hour .");
		
	}

}
