import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RectangleUsingTwoDArray {

    int area = 0;
    int n = 0;
    int maxX = 0;
    int maxY = 0;
    int array[][] = new int[maxY][maxY];
    //List<int[]> array = new ArrayList<int[]>();

    public static void main(String[] args) {

        RectangleUsingTwoDArray rect = new RectangleUsingTwoDArray();
        Scanner input = new Scanner(System.in);

        rect.n = input.nextInt();

        for (int i=0; i<rect.n; i++){

            int x1 = input.nextInt();
            int x2 = input.nextInt();
            int y1 = input.nextInt();
            int y2 = input.nextInt();

            if (rect.maxX < x2) rect.maxX = x2;
            if (rect.maxY < y2) rect.maxY = y2;

            rect.updateTheArray(x1, x2, y1, y2);

        }

        for (int i = 0; i<=rect.maxX; i++){
            for (int j = 0; j<=rect.maxY; j++){
                System.out.print(rect.array[i][j]+" ");
            }
            System.out.println();
        }


    }

    private void updateTheArray(int x1, int x2, int y1, int y2) {

        for (int i = 0; i< maxX; i++){

            if (array[i].length < i || array[i].length == 0){
                array[i][0] = 0;
            }

            for (int j = 0; j < maxY; i++) {

                //if

            }

//            if (array.size() < i) {
//                array.add(i, new int[maxY]);
//                return;
//            }
//            for (int j = 0; j < maxY; i++) {
//
//                if (array.get(i).length < j) {
//                    array.get(i)[j] = 0;
//                }else {
//                    array.get(i)[j] += 1;
//                }
//
//            }
        }
    }


}
