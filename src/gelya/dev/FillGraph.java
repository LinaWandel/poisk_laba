package gelya.dev;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FillGraph {

  public static final int FINAL = 9;
  public static final int MAX_NUMBER = 9;
  public static final int START = 1;
  static Map<Integer, List<Integer>> graph;
  static List<Boolean> closed;
  static ArrayDeque<Integer> open;
  static Map<Integer, List<Integer>> path;

  public static void main(String[] args) {
    graph = new HashMap<>();
    graph.putAll(Map.of(
        1, List.of(2, 3, 4, 5),
        2, List.of(5, 6),
        4, List.of(7, 9)
    ));

    closed = new ArrayList<>(Arrays.asList(new Boolean[MAX_NUMBER + 1]));
    Collections.fill(closed, Boolean.FALSE);

    open = new ArrayDeque<>();
    path = new HashMap<>();
    for (int i = 0; i < MAX_NUMBER + 1; i++) {
      path.put(i, List.of(1));
    }

    if (searchWidth(START, FINAL)) {
      Collections.reverse(path.get(FINAL));
      System.out.println("width: found\npath: " + path.get(FINAL));
    } else {
      System.out.println("width: not found");
    }

    Collections.fill(closed, Boolean.FALSE);
    for (int i = 0; i < MAX_NUMBER + 1; i++) {
      path.put(i, List.of(1));
    }

    if (searchDeep(START, FINAL)) {
      Collections.reverse(path.get(FINAL));
      System.out.println("width: found\npath: " + path.get(FINAL));
    } else {
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
          addPositionItemInPathCollection(pos, item);
        }
      }
    }

    while (open.size() != 0) {
      if (searchWidth(open.removeFirst(), found)) {
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
          addPositionItemInPathCollection(pos, item);
        }
      }
    }

    while (open.size() != 0) {
      if (searchDeep(open.removeFirst(), found)) {
        return true;
      }
    }

    return false;
  }

  private static void addPositionItemInPathCollection(int pos, Integer item) {
    List<Integer> list = new ArrayList<>();
    list.add(item);
    path.put(item, list);
    path.get(item).addAll(path.get(pos));
  }
}
