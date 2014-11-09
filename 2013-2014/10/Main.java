import java.util.HashMap;

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
    
    public static void main(String[] args) {
	for (int n = 3; n <= 160; n++) {
	    long best = 0;
	    int bestStructs = 0;
	    for (int structs = 1; structs < 80; structs += 1) {
		if (structs > n / structs) {
		    break;
		}
		long result = distribute(n, structs);
		if (result > best) {
		    best = result;
		    bestStructs = structs;
		}
	    }
	    System.out.println("For n=" + n +
			       ", structs=" + bestStructs +
			       ", result=" + best);
	}
    }
}
