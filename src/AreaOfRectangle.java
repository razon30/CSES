import java.io.*;
import java.util.*;

public class AreaOfRectangle extends PrintWriter {

    CustomScanner sc = new CustomScanner(System.in);
    int[] segmentPointsInXAxis, eventCollector;
    static final int inputBuffer = 1000000, bufferIndex = 1 << 21;

    AreaOfRectangle() {
        super(System.out);
    }

    public static void main(String[] $) {
        AreaOfRectangle areaOfRectangle = new AreaOfRectangle();
        areaOfRectangle.main();
        areaOfRectangle.flush();
    }

    void main() {

        int n = sc.nextInt();
        Event[] events = new Event[n * 2];
        int eventCounter = 0;
        for (int i = 0; i < n; i++) {
            int x1 = sc.nextInt() + inputBuffer;
            int y1 = sc.nextInt() + inputBuffer;
            int x2 = sc.nextInt() + inputBuffer;
            int y2 = sc.nextInt() + inputBuffer;
            events[eventCounter++] = new Event(y1, x1, x2, true);
            events[eventCounter++] = new Event(y2, x1, x2, false);
        }
        Arrays.sort(events, Comparator.comparingInt(event -> event.deltaY));
        segmentPointsInXAxis = new int[bufferIndex * 2];
        eventCollector = new int[bufferIndex * 2];
        int y = -1;
        long ans = 0;
        for (int h = 0; h < eventCounter; h++) {
            Event event = events[h];
            if (y != -1)
                ans += (long) (event.deltaY - y) * segmentPointsInXAxis[0];
            y = event.deltaY;
            update(0, 0, bufferIndex, event.leftX, event.rightX, event.addToEvent ? 1 : -1);
        }
        println(ans);
    }

    void update(int segmentIndex, int leftX, int rightX, int eventleft, int eventRight, int adOrRemove) {
        if (eventRight <= leftX || rightX <= eventleft) // Scenario 1: No Intersection
            return;
        int segmentLeftIndex = segmentIndex * 2 + 1, segmentRightIndex = segmentIndex * 2 + 2;
        if (eventleft <= leftX && rightX <= eventRight) // Scenario 2: Examinig points are between event Points in X-Axis (leftX, rightX within eventleft, eventRight)
            eventCollector[segmentIndex] += adOrRemove;
        else {
            // Scenario 3: event points are between Examinig  Points in X-Axis (eventleft, eventRight within leftX, rightX)
            int m = (leftX + rightX) / 2;
            update(segmentLeftIndex, leftX, m, eventleft, eventRight, adOrRemove);
            update(segmentRightIndex, m, rightX, eventleft, eventRight, adOrRemove);
        }
        /* if zz[k] < 0:
                0. Time to conclude the task, because the event is ended ad need to remove.
                1. if right and left is adjacent: Nothing to add because no intersecting event X
                2. Else if, the gap is more than 1, there is possibility that X intersection is between leftX and rightX
            else f zz[k] > 0:
                1. The event is started.
                2. Approach to right by substituting the leftX

         */
        int segmentNode = rightX - leftX == 1 ? 0
                : segmentPointsInXAxis[segmentLeftIndex] + segmentPointsInXAxis[segmentRightIndex];
        segmentPointsInXAxis[segmentIndex] = eventCollector[segmentIndex] > 0 ? rightX - leftX : segmentNode;
    }


    static class CustomScanner {

        CustomScanner(InputStream iSteam) {
            this.iSteam = iSteam;
        }
        InputStream iSteam;
        int bufferPointer, bytes;
        byte[] bufferSize = new byte[1 << 15];

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

    }


    static class Event {
        int deltaY, leftX, rightX;
        boolean addToEvent; // 1 : Event Added, start calculation.      -1: Event Ended, remove from the event list

        Event(int deltaY, int leftX, int rightX, boolean addToEvent) {
            this.deltaY = deltaY;
            this.leftX = leftX;
            this.rightX = rightX;
            this.addToEvent = addToEvent;
        }
    }

}

//Problem
//Challenge: Large Data Input
//Efficiency: Complexity

