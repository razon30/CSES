import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class RectanlgeUsingSegmentTree {

    public static void main(String[] args) {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n;

        int[][] events;
        Set<Integer> Xvals = new HashSet();
        int t = 0;

        try {
            n = Integer.parseInt(input.readLine());
            events = new int[n * 2][];

            for (int i = 0; i < n; i++) {

                String[] rec;

                String str = input.readLine();
                rec = str.split(" ");

                events[t++] = new int[]{Integer.parseInt(rec[1]), 1,Integer.parseInt(rec[0]), Integer.parseInt(rec[2])};
                events[t++] = new int[]{Integer.parseInt(rec[3]), -1, Integer.parseInt(rec[0]), Integer.parseInt(rec[2])};
                Xvals.add(Integer.parseInt(rec[0]));
                Xvals.add(Integer.parseInt(rec[2]));

            }

            RectanlgeUsingSegmentTree rectanlgeUsingSegmentTree = new RectanlgeUsingSegmentTree();
            rectanlgeUsingSegmentTree.rectangleArea(events, Xvals);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void  rectangleArea(int[][] events, Set<Integer> Xvals) {

        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));

        Integer[] X = Xvals.toArray(new Integer[0]);
        Arrays.sort(X);
        Map<Integer, Integer> Xi = new HashMap();
        for (int i = 0; i < X.length; ++i)
            Xi.put(X[i], i);

        Node active = new Node(0, X.length - 1, X);
        long ans = 0;
        long cur_x_sum = 0;
        int cur_y = events[0][0];

        for (int[] event: events) {
            int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];
            ans += cur_x_sum * (y - cur_y);
            cur_x_sum = active.update(Xi.get(x1), Xi.get(x2), typ);
            cur_y = y;
        }

        System.out.println(ans+"");
    }
}

class Node {
    int start, end;
    Integer[] X;
    Node left, right;
    int count;
    long total;

    public Node(int start, int end, Integer[] X) {
        this.start = start;
        this.end = end;
        this.X = X;
        left = null;
        right = null;
        count = 0;
        total = 0;
    }

    public int getRangeMid() {
        return start + (end - start) / 2;
    }

    public Node getLeft() {
        if (left == null) left = new Node(start, getRangeMid(), X);
        return left;
    }

    public Node getRight() {
        if (right == null) right = new Node(getRangeMid(), end, X);
        return right;
    }

    public long update(int i, int j, int val) {
        if (i >= j) return 0;
        if (start == i && end == j) {
            count += val;
        } else {
            getLeft().update(i, Math.min(getRangeMid(), j), val);
            getRight().update(Math.max(getRangeMid(), i), j, val);
        }

        if (count > 0) total = X[end] - X[start];
        else total = getLeft().total + getRight().total;

        return total;
    }
}
