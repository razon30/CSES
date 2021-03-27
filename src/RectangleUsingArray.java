import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RectangleUsingArray {


    public static void main(String[] args) {

        List<Integer> xArray = new ArrayList<Integer>();
        List<Integer> yArray = new ArrayList<Integer>();

        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        for (int i = 0; i < n; i++) {

            int x1 = input.nextInt();
            int x2 = input.nextInt();
            int y1 = input.nextInt();
            int y2 = input.nextInt();

            xArray.add(x1);
            xArray.add(x2);
            yArray.add(y1);
            yArray.add(y2);

        }

        xArray.sort(Integer::compareTo);
        yArray.sort(Integer::compareTo);

        for (int i=0; i< xArray.size() - 1; i++){
           // int xInterval =
        }

    }

}
