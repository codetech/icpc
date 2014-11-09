import java.io.Console;
import java.util.Iterator;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;

public class Main {

    private static Console console = System.console();
    
    private static List<String> getLetters(String regex) {
	String input = console.readLine();
        List<String> list = Arrays.asList(input.split(regex));
	// Special case where empty string will be included as the
	// first match.
	if (regex.equals("")) {
	    list = list.subList(1, list.size());
	}
	return list;
    }
    
    public static void main(String[] args) {
	// A-Z
	List<String> alphabet = new ArrayList<>(27);
	for (int i = 0; i < 26; i += 1) {
	    alphabet.add(new String(new int[]{i + 65}, 0, 1));
	}
	alphabet.add("_");

	// Get all point values.
	List<String> inputs = new ArrayList<>(27);
	while (inputs.size() < 27) {
	    inputs.addAll(getLetters(" "));
	}

	// Populate the letter-value lookup table.
	Iterator<String> alphabetIterator = alphabet.listIterator();
	Iterator<String> inputsIterator = inputs.listIterator();
	HashMap<String, Integer> letterValues = new HashMap<>(27);
	while (alphabetIterator.hasNext()) {
	    String letter = alphabetIterator.next();
	    String input = inputsIterator.next();
	    letterValues.put(letter, Integer.parseInt(input));
	}

	// Game loop.
	while (true) {
	    // Get user input.
	    List<String> descriptors = getLetters("");
	    List<String> letters = getLetters("");

	    // Calculate points.
	    Iterator<String> descriptorsIterator = descriptors.listIterator();
	    Iterator<String> lettersIterator = letters.listIterator();
	    int totalPoints = 0;
	    List<Integer> multipliers = new ArrayList<>();
	    while (descriptorsIterator.hasNext()) {
		String descriptor = descriptorsIterator.next();
		String letter = lettersIterator.next();
		int points = letterValues.get(letter);

		// Apply bonuses according to tile descriptors.
		switch (descriptor) {
		case "2":
		    points *= 2;
		    break;
		case "3":
		    points *= 3;
		    break;
		case "d":
		    multipliers.add(2); // Defer.
		    break;
		case "t":
		    multipliers.add(3); // Defer.
		    break;
		}
		totalPoints += points;
	    }
	    
	    // Apply deferred multipliers.
	    for (Integer multiplier : multipliers) {
		totalPoints *= multiplier;
	    }

	    // Points are bounded to 10^6 - 1 per instructions.
	    totalPoints = Math.min(totalPoints, (int) Math.pow(10, 6) - 1);
	    
	    System.out.println(totalPoints);
	}
    }
}
