import java.util.Scanner; 

public class CompoundInflation {
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Please enter your monthly savings rate: ");
		String input = scnr.nextLine();
		
		double monthlySavings = Double.parseDouble(input);
		
		double inflationRate = (1 - 0.009);
		int months = 6;
		
		double totalSaved = 0;
		totalSaved += monthlySavings;
		for (int i = 0; i < months; i++) {
			totalSaved *= inflationRate;
		}
		
		int equivalentValue = (int) Math.round(totalSaved);
		
		System.out.println("If you save $" + (int) monthlySavings + 
				" per month with 0.9% monthly inflation," + " after 6 months, your account will hold an amount equivalent to $" + equivalentValue + " today." );
		
		
	}

}
