package part1;
import java.io.File;
import java.util.Scanner;

public class Measurements {
    public static int getIncreases() {
        try (Scanner sc = new Scanner(new File("day1/src/data.txt"))){
            int prev = sc.nextInt();
            int current;
            int count = 0;
            while (sc.hasNextLine()) {
                current = sc.nextInt();
                if (current > prev) count++;
                prev = current;
            }
            return count;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }


}
