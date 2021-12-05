import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(part1());
        System.out.println(part2());
    }

    public static int part1() {
        try (Scanner sc = new Scanner(new File("day5/src/data.txt"))) {
            List<String[]> lineString = new ArrayList<>();
            while (sc.hasNextLine()) {
                lineString.add(sc.nextLine().replace(" ->", "").split(" "));
            }

            List<Line> lines = new ArrayList<>();
            for (String[] s : lineString) {
                lines.add(
                        new Line(
                                s[0].split(",")[0],
                                s[1].split(",")[0],
                                s[0].split(",")[1],
                                s[1].split(",")[1]));
            }

            int maxX = 0;
            int maxY = 0;

            for (Line l : lines) {
                if (l.getY1() > maxY) maxY = l.getY1();
                else if (l.getY2() > maxY) maxY = l.getY2();
                else if (l.getX1() > maxX) maxX = l.getX1();
                else if (l.getX2() > maxX) maxX = l.getX2();
            }

            int[][] array = new int[maxX + 1][maxY + 1];
            for (int[] row : array)
                Arrays.fill(row, 0);

            for (Line l : lines) {
                int x1 = l.getX1();
                int y1 = l.getY1();

                int x2 = l.getX2();
                int y2 = l.getY2();

                int[] y = new int[2];
                if (y1 > y2) {
                    y[0] = y2;
                    y[1] = y1;
                } else {
                    y[0] = y1;
                    y[1] = y2;
                }

                int[] x = new int[2];
                if (x1 > x2) {
                    x[0] = x2;
                    x[1] = x1;
                } else {
                    x[0] = x1;
                    x[1] = x2;
                }

                if (x1 == x2) {
                    for (int i = y[0]; i <= y[1]; i++)
                        array[x1][i]++;
                } else if (y1 == y2) {
                    for (int i = x[0]; i <= x[1]; i++)
                        array[i][y1]++;
                }
            }
            int count = 0;
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    if (array[i][j] >= 2) count++;
                }
            }
            return count;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int part2() {
        try (Scanner sc = new Scanner(new File("day5/src/data.txt"))) {
            List<String[]> lineString = new ArrayList<>();
            while (sc.hasNextLine()) {
                lineString.add(sc.nextLine().replace(" ->", "").split(" "));
            }

            List<Line> lines = new ArrayList<>();
            for (String[] s : lineString) {
                lines.add(
                        new Line(
                                s[0].split(",")[0],
                                s[1].split(",")[0],
                                s[0].split(",")[1],
                                s[1].split(",")[1]));
            }

            int maxX = 0;
            int maxY = 0;

            for (Line l : lines) {
                if (l.getY1() > maxY) maxY = l.getY1();
                else if (l.getY2() > maxY) maxY = l.getY2();
                else if (l.getX1() > maxX) maxX = l.getX1();
                else if (l.getX2() > maxX) maxX = l.getX2();
            }

            int[][] array = new int[1000][1000];
            for (int[] row : array)
                Arrays.fill(row, 0);

            for (Line l : lines) {
                int x1 = l.getX1();
                int y1 = l.getY1();

                int x2 = l.getX2();
                int y2 = l.getY2();

                int[] y = new int[2];
                if (y1 > y2) {
                    y[0] = y2;
                    y[1] = y1;
                } else {
                    y[0] = y1;
                    y[1] = y2;
                }

                int[] x = new int[2];
                if (x1 > x2) {
                    x[0] = x2;
                    x[1] = x1;
                } else {
                    x[0] = x1;
                    x[1] = x2;
                }

                if (x1 == x2) {
                    for (int i = y[0]; i <= y[1]; i++) {
                        array[i][x1]++;
                    }
                } else if (y1 == y2) {
                    for (int i = x[0]; i <= x[1]; i++) {
                        array[y1][i]++;
                    }
                }
                else {
                    if (x1 < x2 && y1 < y2) {
                        for (int i = x1, j = y1; i <= x2; i++, j++)
                            array[j][i]++;
                    } else if (x1 < x2) {
                        for (int i = x1, j = y1; i <= x2; i++, j--)
                            array[j][i]++;
                    } else if (y1 < y2) {
                        for (int i = x1, j = y1; i >= x2; i--, j++)
                            array[j][i]++;
                    } else {
                        for (int i = x1, j = y1; i >= x2; i--, j--)
                            array[j][i]++;
                    }
                }
            }

            int count = 0;
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    if (array[i][j] >= 2) count++;
                }
            }
            return count;

        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static class Line {
        private int x1;
        private int x2;
        private int y1;
        private int y2;

        public Line(String x1, String x2, String y1, String y2) {
            this.x1 = Integer.parseInt(x1);
            this.x2 = Integer.parseInt(x2);
            this.y1 = Integer.parseInt(y1);
            this.y2 = Integer.parseInt(y2);
        }


        public int getX1() {
            return x1;
        }

        public int getX2() {
            return x2;
        }

        public int getY1() {
            return y1;
        }

        public int getY2() {
            return y2;
        }

        @Override
        public String toString() {
            return String.format("xy = %d %d   xy2 = %d %d", x1, y1, x2, y2);
        }
    }
}
