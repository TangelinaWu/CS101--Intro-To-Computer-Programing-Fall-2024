
public class PopulationProjector {

	public static void main(String[] args) {
		int currentPop = 334300850;//people
		//all in seconds
		int year = 365 * 24 * 60 * 60;
		int birth = 9; 
		int death = 10;
		int immigrant = 32;
		//positive
		int birthRate = year / birth;
		int moveRate = year / immigrant;
		//negative
		int deathRate = year / death;
		
		System.out.println("Here are the projected population numbers for the next five years:");
		
		int years = 9;
		int newPop = currentPop; 
		
		for (int i = 4 ; i< years; i++) {
			newPop += (birthRate + moveRate - deathRate);		
			System.out.println("- Year 202" + i + ": " + newPop);
		}
			
			
	}

}
