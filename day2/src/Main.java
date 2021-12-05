import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(part1());
        System.out.println(part2());
    }

    public static int part1() {
        try (Scanner sc = new Scanner(new File("day2/src/data.txt"))) {
            int horizontal = 0;
            int depth = 0;
            String[] step;
            while (sc.hasNextLine()) {
                step = sc.nextLine().split(" ");
                if (step[0].equals("forward")) horizontal += Integer.parseInt(step[1]);
                if (step[0].equals("down")) depth += Integer.parseInt(step[1]);
                if (step[0].equals("up")) depth -= Integer.parseInt(step[1]);
            }
            return horizontal * depth;
        } catch (Exception ignored) {
        }
        return 0;
    }

    public static int part2() {
        try (Scanner sc = new Scanner(new File("day2/src/data.txt"))) {
            int horizontal = 0;
            int depth = 0;
            int aim = 0;
            String[] step;
            while (sc.hasNextLine()) {
                step = sc.nextLine().split(" ");
                if (step[0].equals("forward")) {
                    horizontal += Integer.parseInt(step[1]);
                    depth += aim * Integer.parseInt(step[1]);
                }
                if (step[0].equals("down")) aim += Integer.parseInt(step[1]);
                if (step[0].equals("up")) aim -= Integer.parseInt(step[1]);
            }
            return horizontal * depth;
        } catch (Exception ignored) {
        }
        return 0;
    }
}
