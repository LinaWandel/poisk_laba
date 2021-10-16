package gelya.dev;

import java.util.*;

public class FillGraph {
  static Map<Integer, List<Integer>> graph;
  public static final int FINAL = 0;
  public static final int MAX_NUMBER = 9;
  static List<Boolean> closed;
  static ArrayDeque<Integer> open;

  public static void main(String[] args) {
    graph = new HashMap<>();
    graph.putAll(Map.of(
        1, List.of(2, 3, 5),
        2, List.of(5, 6),
        4, List.of(7, 9)
    ));

    closed = new ArrayList<>(Arrays.asList(new Boolean[MAX_NUMBER + 1]));
    Collections.fill(closed, Boolean.FALSE);

    open = new ArrayDeque<>();

    if (searchWidth(1, FINAL)) {
      System.out.println("width: found");
    }else {
      System.out.println("width: not found");
    }

    Collections.fill(closed, Boolean.FALSE);

    if (searchDeep(1, FINAL)) {
      System.out.println("deep: found");
    }else {
      System.out.println("deep: not found");
    }
  }




  private static boolean searchWidth(int pos, int found) {
    if (pos == found) {
      return true;
    }

    closed.set(pos, true);

    if (graph.containsKey(pos)) {
      for (Integer item : graph.get(pos)) {
        if (closed.get(item) == false) {
          open.addLast(item);
        }
      }
    }


    while (open.size() != 0) {
      open.removeFirst();
      if (open.size() == 0) {
        return false;
      }
      if (searchWidth(open.getFirst(), found)) {
        return true;
      }
    }

    return false;
  }

  private static boolean searchDeep(int pos, int found) {
    if (pos == found) {
      return true;
    }

    closed.set(pos, true);

    if (graph.containsKey(pos)) {
      for (Integer item : graph.get(pos)) {
        if (closed.get(item) == false) {
          open.addFirst(item);
        }
      }
    }

    while (open.size() != 0) {
      open.removeFirst();
      if (open.size() == 0) {
        return false;
      }
      if (searchDeep(open.getFirst(), found)) {
        return true;
      }
    }

    return false;
  }
}
