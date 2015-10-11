import java.io.*;
import java.util.*;

class Main {

  // Read input and write output to complete the problem.
  static void read(Scanner s) {
    while (s.hasNext()) {
      String line = s.nextLine();
    }
  }

  public static void main(String[] args) throws Exception {
    if (args.length > 0) {
      // In a debugging context it is far easier to specify a file
      // path as a command-line argument.
      String path = args[0];
      read(new Scanner(new File(path)));
    } else {
      // The contest requires we read via stdin.
      read(new Scanner(System.in));
    }
  }

}
