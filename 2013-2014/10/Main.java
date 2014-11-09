import java.util.*;

/**
 * Solves 2013-2014 Problem #10, "BigClass".
 *
 * @author Jackson Ray Hamilton
 * @author Connor Barnes
 */
public class Main {
    
    public static long distribute(int n, int structs) {
	int number = n / structs;
	int remainder = n % structs;
	long product = 1;
	for (int index = 0; index < structs; index += 1) {
	    int extra = index < remainder ? 1 : 0;
	    product *= number + extra - 2;
	}
	return product;
    }

    public static long getMaxBytes(int n) {
        long best = 0;
        for (int structs = 1; structs < 80; structs += 1) {
            if (structs > n / structs) {
                break;
            }
            long result = distribute(n, structs);
            if (result > best) {
                best = result;
            }
        }
        return best;
    }
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while (s.hasNext()) {
            System.out.println(getMaxBytes(s.nextInt()));
        }
    }
}
