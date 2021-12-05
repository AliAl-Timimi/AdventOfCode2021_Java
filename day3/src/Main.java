import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        System.out.println(first());
        System.out.println(second());
    }

    public static int first() {
        try (Scanner sc = new Scanner(new File("day3/src/data.txt"))) {
            List<String> binaries = new ArrayList<>();
            while (sc.hasNextLine()) {
                binaries.add(sc.nextLine());
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < binaries.get(0).length(); i++) {
                int zeros = 0;
                int ones = 0;
                for (String binary : binaries)
                    if (binary.charAt(i) == '1') ones++;else zeros++;
                if (ones > zeros) sb.append("1");else sb.append("0");
            }
            int gamma = Integer.parseInt(sb.toString(), 2);
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < sb.length(); i++) {
                if (sb.charAt(i) == '1') sb2.append('0');
                else sb2.append('1');
            }
            int epsilon = Integer.parseInt(sb2.toString(), 2);
            return epsilon * gamma;
        } catch (Exception ignored) {}
        return 0;
    }

    public static int second() {
        try (Scanner sc = new Scanner(new File("day3/src/data.txt"))) {
            List<String> binaries = new ArrayList<>();
            while (sc.hasNextLine())
                binaries.add(sc.nextLine());
            List<String> oxygen = new ArrayList<>(binaries);
            List<String> co2 = new ArrayList<>(binaries);
            int i = 0;
            while(oxygen.size()>1) {
                int zeros = 0;
                int ones = 0;
                for (String s : oxygen)
                    if (s.charAt(i) == '1') ones++; else zeros++;
                char pop;
                if (ones >= zeros) pop = '0';else pop = '1';
                for (int j = 0; j < oxygen.size(); )
                    if (oxygen.get(j).charAt(i) == pop) oxygen.remove(j);else j++;
                i++;
            }
            i = 0;
            while(co2.size()>1) {
                int zeros = 0;
                int ones = 0;
                for (String s : co2)
                    if (s.charAt(i) == '1') ones++;else zeros++;
                char pop;
                if (ones >= zeros) pop = '1';
                else pop = '0';
                for (int j = 0; j < co2.size(); )
                    if (co2.get(j).charAt(i) == pop) co2.remove(j);else j++;
                i++;
            }
            int oxygenInt = Integer.parseInt(oxygen.get(0),2);
            int co2Int = Integer.parseInt(co2.get(0),2);
            return  oxygenInt * co2Int;
        } catch (Exception ignored) {}
        return 0;
    }
}