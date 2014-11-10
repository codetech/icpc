import java.util.*;

/**
 * Solves 2014-2015 Problem #9, "Anagram Pyramids".
 *
 * @author Jackson Ray Hamilton
 * @author Kyle San Clemente
 */
public class Main {

    /**
     * Concatenates a char onto the end of an array.
     */
    public static char[] concat(char[] first, char second) {
        char[] result = Arrays.copyOf(first, first.length + 1);
        result[result.length - 1] = second;
        return result;
    }

    /**
     * Concatenates two arrays into one.
     */
    public static char[] concat(char[] first, char[] second) {
        char[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    /**
     * Removes a range of indices from an array, returning a smaller array
     * without those indices.
     */
    public static char[] splice(char[] array, int start, int length) {
        return concat(Arrays.copyOfRange(array, 0, start),
                      Arrays.copyOfRange(array, start + length, array.length));
    }

    /**
     * If one argument is a random assortment of letters and the other argument
     * is a word, this function determines if the random assortment is an
     * anagram of the word.
     *
     * Warning: Has the side effect of changing the input arrays. We do this
     * because creating new char arrays for each input could be imperformant for
     * millions of words.
     */
    public static boolean areAnagrams(char[] a, char[] b) {
        // Fast check.
        if (a.length != b.length) {
            return false;
        }

        // Has side effect of changing the array.
        Arrays.sort(a);
        Arrays.sort(b);

        // All same letters in same quantities => possible to have rearranged
        // letters => anagram.
        return Arrays.equals(a, b);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while (s.hasNext()) {

            HashMap<Integer, ArrayList<char[]>> dict = new HashMap<>();

            // Build a dictionary.
            int numLines1 = s.nextInt();
            for (int i = 0; i < numLines1; i++) {
                String word = s.next();
                int length = word.length();
                if (!dict.containsKey(length)) {
                    dict.put(length, new ArrayList<char[]>());
                }
                dict.get(length).add(word.toCharArray());
            }

            // Process tops and bottoms of pyramids and determine if there are
            // pyramids for those tops and bottoms.
            int numLines2 = s.nextInt();
            for (int i = 0; i < numLines2; i++) {

                char[]
                    top = s.next().toCharArray(),
                    bottom = s.next().toCharArray(),
                    difference = Arrays.copyOf(bottom, bottom.length);

                // Difference must be sorted so that a binary search is
                // possible.
                Arrays.sort(difference);

                // Subtract all letters in top from bottom to get the
                // difference. (If top=AB and bottom=ABC, then difference=C.)
                for (int j = 0; j < top.length; j++) {
                    char letter = top[j];
                    int index = Arrays.binarySearch(difference, letter);
                    difference = splice(difference, index, 1);
                }

                char[]
                    // Last biggest word found in the pyramid.
                    lastFound = top;

                int
                    // Number of items found in the pyramid.
                    numFound = 0,

                    // Total number of items that, if found, constitute a
                    // pyramid.
                    range = bottom.length - top.length - 1;

                while (numFound < range) {
                    boolean found = false;

                    // Try all combinations of letters against all words in the
                    // dictionary of the same length.
                    for (int j = 0; j < difference.length; j++) {
                        char letter = difference[j];
                        char[] letters = concat(lastFound, letter);
                        for (char[] word : dict.get(letters.length)) {
                            if (areAnagrams(letters, word)) {
                                found = true;
                                difference = splice(difference, j, 1);
                                lastFound = word;
                                numFound++;
                                break;
                            }
                        }
                    }

                    // Not found means there is no anagram pyramid possible.
                    if (!found) {
                        System.out.println("no");
                        break;
                    }

                    if (numFound == range) {
                        System.out.println("yes");
                    }
                }

            }
        }
    }
}
