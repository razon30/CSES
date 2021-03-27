import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class GridPaths extends PrintWriter {

    CustomScanner sc = new CustomScanner(System.in);
    int n, m;
    int ans = 0;
    double mod = 1e9+7;
    int[][] grid;

    public GridPaths() {
        super(System.out);
    }

    public static void main(String[] args) {

        GridPaths gridPaths = new GridPaths();
        gridPaths.main();
        gridPaths.flush();

    }

    public void main() {

        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n+1][n+1];

        for (int i=0; i<m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            grid[x][y] = 1;
        }

        travelToNext(1, 1, true);
        ans = (int) (ans % mod);
        println(ans+"");

    }

    public void travelToNext(int x, int y, boolean isFromTop){

        if (x==n && y==n){
            //println("end");
            ans += 1;
            return;
        }

        if (grid[x][y] == 1){
            return;
        }

        if (x < n && y == n){
            //println(x+" "+y+" "+"Going Right2");
            travelToNext(x+1, y, false);
        }else if (x == n && y < n){
           // println(x+" "+y+" "+"Going Down2");
            travelToNext(x, y+1, true);
        }else {
          //  println(x+" "+y+" "+"Going Right3");
            travelToNext(x+1, y, false);

            //y = y+1;
          //  println(x+" "+y+" "+"Going Down3");
            travelToNext(x, y+1, true);
        }

    }

    static class CustomScanner {

        CustomScanner(InputStream iSteam) {
            this.iSteam = iSteam;
        }
        InputStream iSteam;
        int bufferPointer, bytes;
        byte[] bufferSize = new byte[1 << 15];

        byte getInputtedChar() {
            if (bufferPointer >= bytes) {
                bufferPointer = 0;
                try {
                    bytes = iSteam.read(bufferSize);
                } catch (IOException e) {
                    bytes = 0;
                }
                if (bytes <= 0) return -1;
            }
            return bufferSize[bufferPointer++];
        }

        int nextInt() {
            byte inputChar = 0;
            while (inputChar <= 32) inputChar = getInputtedChar();
            boolean neg = (inputChar == '-');
            if (neg) inputChar = getInputtedChar();
            int value = 0;
            while (inputChar > 32) {
                value = value * 10 + inputChar - '0';
                inputChar = getInputtedChar();
            }
            return neg ? -value : value;
        }
    }

}
