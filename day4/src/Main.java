import java.io.File;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        System.out.println(part1());
        System.out.println(part2());
    }

    private static int part1() {
        try (Scanner sc = new Scanner(new File("day4/src/data.txt"))) {
            List<String[][]> boards = new ArrayList<>();
            List<String> order = new ArrayList<>();
            Collections.addAll(order, sc.nextLine().split(","));
            while (sc.hasNextLine()) {
                int i = 0;
                String[][] board = new String[5][5];
                while (i < 5 && sc.hasNextLine()) {
                    String current = sc.nextLine();
                    if (!current.isEmpty()) {
                        for (int j = 0, k = 0; j < 5 * 3; j += 3, k++)
                            board[i][k] = current.substring(j, j + 2).trim();
                        i++;
                    }
                }
                boards.add(board);
            }

            while (order.size() > 0) {
                String i = order.get(0);
                for (String[][] board : boards) {
                    for (int k = 0; k < 5; k++) {
                        for (int l = 0; l < 5; l++) {
                            if (board[k][l].equals(i)) {
                                board[k][l] = "/";
                                if (bingo(board))
                                    return output(board) * Integer.parseInt(i);
                            }
                        }
                    }
                }
                order.remove(0);
            }
        } catch (Exception ignored) {
        }
        return 0;
    }

    private static int part2() {
        try (Scanner sc = new Scanner(new File("day4/src/data.txt"))) {
            List<String[][]> boards = new ArrayList<>();
            List<String> order = new ArrayList<>();
            Collections.addAll(order, sc.nextLine().split(","));
            while (sc.hasNextLine()) {
                int i = 0;
                String[][] board = new String[5][5];
                while (i < 5 && sc.hasNextLine()) {
                    String current = sc.nextLine();
                    if (!current.isEmpty()) {
                        for (int j = 0, k = 0; j < 5 * 3; j += 3, k++)
                            board[i][k] = current.substring(j, j + 2).trim();
                        i++;
                    }
                }
                boards.add(board);
            }

            Map<String[][], String> bingoboards = new LinkedHashMap<>();

            boolean back = false;
            while (order.size() > 0 && boards.size() > 0) {
                String i = order.get(0);
                for (int j = 0; j < boards.size(); j++) {
                    if (back) {
                        j = 0;
                        back = false;
                    }
                    for (int k = 0; k < 5; k++) {
                        for (int l = 0; l < 5; l++) {
                            if (boards.size() != 0) {
                                if (boards.get(j)[k][l].equals(i)) {
                                    boards.get(j)[k][l] = "/";
                                    if (bingo(boards.get(j))) {
                                        bingoboards.put(boards.get(j), i);
                                        boards.remove(boards.get(j));
                                        if (j != 0) j--;
                                        else back = true;
                                    }
                                }
                            }
                        }
                    }
                }
                order.remove(0);
            }
            Object board = bingoboards.keySet().toArray()[bingoboards.size() - 1];
            int lastDrawn = Integer.parseInt(bingoboards.get(board));
            return output((String[][]) board) * lastDrawn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static boolean bingo(String[][] board) {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].equals("/"))
                    count++;
            }
            if (count == 5) return true;
            else count = 0;
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[j][i].equals("/"))
                    count++;
            }
            if (count == 5) return true;
            else count = 0;
        }
        return false;
    }


    private static int output(String[][] board) {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!board[i][j].equals("/"))
                    sum += Integer.parseInt(board[i][j]);
            }
        }
        return sum;
    }

}


