import java.util.Arrays;

public class Aspect implements Comparable<Aspect>{
	private String name; //the name of the aspect
	private Aspect[] recipe; //the aspects needed to combine to discover this one
	public String getName() {return name;}
	public Aspect[] getRecipe() {return recipe;}
	
	/* returns an alphabetized, comma separated list of these aspects
	 * used in crafting
	 * Aspect array --> crafting code
	 * crafting code is checked against all aspect/idea's crafting codes
	 * if there is a match, that thing is made
	 */
	public static String getCraftingCode(Aspect[] aspects) {
		Aspect[] sorted = aspects;
		Arrays.sort(sorted);
		String output = "";
		for(int i = 0; i < sorted.length - 1; i++) {
			output += sorted[i];
			output += ",";
		}
		output += sorted[sorted.length - 1];
		return output;
	}
	
	public int compareTo(Aspect a) {
		return name.compareTo(a.getName());
	}
}
