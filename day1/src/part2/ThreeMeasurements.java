package part2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ThreeMeasurements {
    public static int getIncreases() {
        try (Scanner sc = new Scanner(new File("day1/src/data.txt"))){
            List<Integer> list = new ArrayList<>();
            while (sc.hasNextLine()) {
                list.add(sc.nextInt());
            }
            int count = 0;
            for (int i = 3; i < list.size();i++) {
                int sum1 = list.get(i-3) + list.get(i-2) + list.get(i-1);
                int sum2 = list.get(i-2) + list.get(i-1) + list.get(i);
                if (sum1 < sum2) count++;
            }
            return count;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
