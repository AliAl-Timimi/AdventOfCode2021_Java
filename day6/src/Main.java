
import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(part1(80));
        System.out.println(part1(256));
    }

    private static HashMap<Long, Long> init() {
        try (Scanner sc = new Scanner(new File("day6/src/data.txt"))) {
            String[] state = sc.nextLine().split(",");
            List<Long> start = new ArrayList<>();
            for (String s : state)
                start.add(Long.parseLong(s));
            HashMap<Long ,Long> map = new LinkedHashMap<>();
            for (long i = 0; i <= 8; i++)
                map.put(i, (long) 0);
            for (long i : start)
                map.replace(i, map.get(i)+1);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static HashMap<Long, Long> cycle(HashMap<Long, Long> map) {
        HashMap<Long, Long> map2 = new LinkedHashMap<>();
        for (long i = 0; i <= 8; i++) {
            map2.put(i,(long) 0);
        }
        Long prod;
        for (long i = 0; i <= 8; i++) {
                if (i == 0) {
                    prod = map.get(i);
                    map2.replace((long)8, prod);
                    map2.replace((long)6, prod);
                } else
                    map2.replace(i - 1, map2.get(i-1) + map.get(i));
        }
        return map2;
    }

    private static long part1(int days) {
        HashMap<Long, Long> map = init();
        for (long m = 0; m < days; m++) {
            map = cycle(map);
        }
        long sum = 0;
        for (long i = 0; i < map.size(); i++)
            sum += map.get(i);
        return sum;
    }
}