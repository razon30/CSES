import java.util.*;

public class NewTest1 {

    public static void main(String[] args) {

        //= new int[][]

        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        int[][] rectangles = new int[n][];

        for (int i = 0; i < n; i++) {

            int[] rect = new int[4];

            rect[0] = input.nextInt();
            rect[1] = input.nextInt();
            rect[2] = input.nextInt();
            rect[3] = input.nextInt();

            rectangles[i] = rect;

        }

        NewTest1 rectanlgeFromLeetCOdeSegmentTree = new NewTest1();
        rectanlgeFromLeetCOdeSegmentTree.rectangleArea(rectangles);

    }

    public void rectangleArea(int[][] rectangles) {
        int OPEN = 0, CLOSE = 1;
        int[][] events = new int[rectangles.length * 2][];
        int t = 0;
        for (int[] rec : rectangles) {
            events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
            events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
        }

        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> active = new ArrayList();
        int cur_y = events[0][0];
        long ans = 0;
        for (int[] event : events) {
            int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];

            // Calculate query
            long query = 0;
            int cur = -1;
            for (int[] xs : active) {
                cur = Math.max(cur, xs[0]);
                query += Math.max(xs[1] - cur, 0);
                cur = Math.max(cur, xs[1]);
            }

            ans += query * (y - cur_y);

            if (typ == OPEN) {
                active.add(new int[]{x1, x2});
                Collections.sort(active, (a, b) -> Integer.compare(a[0], b[0]));
            } else {
                for (int i = 0; i < active.size(); ++i)
                    if (active.get(i)[0] == x1 && active.get(i)[1] == x2) {
                        active.remove(i);
                        break;
                    }
            }

            cur_y = y;
        }

        ans %= 1_000_000_007;
        System.out.println(ans + "");
        //return (int) ans;
    }

}
