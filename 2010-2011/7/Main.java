import java.io.*;
import java.util.*;

/**
 * Solves 2010-2011 Problem #7, "Zombie Blast!".
 *
 * @author Jackson Ray Hamilton
 */
class Main {

  /**
   * Representation of one cell on a map.
   */
  static class Cell {
    int x, y;
    String type;
    Cell(int x, int y, String type) {
      this.x = x;
      this.y = y;
      this.type = type;
    }
    boolean isMine() {
      return type.equals("M") || type.equals("B");
    }
    boolean isZombie() {
      return type.equals("Z") || type.equals("B");
    }
  }

  /**
   * @return Distance between `from` and `to`.
   */
  static double distanceBetween(Cell from, Cell to) {
    int a = Math.abs(to.x - from.x);
    int b = Math.abs(to.y - from.y);
    double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    return c;
  }

  static class GameMap {

    List<Cell> cells = new ArrayList<>();
    List<Cell> mineCells = new ArrayList<>();
    List<Cell> zombieCells = new ArrayList<>();
    int width;

    /**
     * Build up this map as terminal input comes in.
     */
    void addCells(List<String> toAdd) {
      width = toAdd.size();
      for (String type : toAdd) {
        int index = cells.size();
        int x = index % width;
        int y = index / width;
        Cell cell = new Cell(x, y, type);
        cells.add(cell);
        if (cell.isMine()) {
          mineCells.add(cell);
        }
        if (cell.isZombie()) {
          zombieCells.add(cell);
        }
      }
    }

    List<Double> getZombieDistancesFrom(Cell from) {
      List<Double> distances = new ArrayList<>();
      for (Cell to : zombieCells) {
        distances.add(distanceBetween(from, to));
      }
      return distances;
    }

    /**
     * @return Set of radii sufficient for some mine to blast some
     * zombie, sorted from least to greatest.
     */
    Set<Double> getBlastRadii() {
      Set<Double> radii = new TreeSet<>();
      for (Cell from : mineCells) {
        radii.addAll(getZombieDistancesFrom(from));
      }
      return radii;
    }

    /**
     * Confirm that, for each zombie, there is at least one mine that
     * can blast said zombie with argument radius.
     */
    boolean doesIntersectZombies(double radius) {
      TO: for (Cell to : zombieCells) {
        for (Cell from : mineCells) {
          if (radius >= distanceBetween(from, to)) {
            continue TO;
          }
        }
        return false;
      }
      return true;
    }

    /**
     * For this map, complete the requirement of finding the smallest
     * radius sufficient to blast all the zombies.
     */
    double getSmallestRadius() {
      Set<Double> radii = getBlastRadii();
      for (double radius : radii) {
        if (doesIntersectZombies(radius)) {
          return radius;
        }
      }
      return -1;
    }

  }

  static List<String> lineToLetters(String line) {
    String[] chars1 = line.split("");
    // Exclude leading space.
    String[] chars2 =
      Arrays.copyOfRange(chars1, 1, chars1.length);
    List<String> chars3 = Arrays.asList(chars2);
    return chars3;
  }

  static void read(Scanner s) {
    List<Double> results = new ArrayList<>();
    GameMap map = new GameMap();
    while (s.hasNext()) {
      String line = s.nextLine();
      if (line.length() == 0) {
        results.add(map.getSmallestRadius());
        map = new GameMap();
      } else {
        map.addCells(lineToLetters(line));
      }
    }
    results.add(map.getSmallestRadius());
    for (double result : results) {
      System.out.printf("%.2f\n", result);
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
