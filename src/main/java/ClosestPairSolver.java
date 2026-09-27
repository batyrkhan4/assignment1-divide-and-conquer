import java.util.Arrays;
import java.util.Comparator;

public class ClosestPairSolver {

    private int comparisons;
    private int maxRecursionDepth;

    public double findClosestDistance(Point[] points) {

        comparisons = 0;
        maxRecursionDepth = 0;

        if (points == null || points.length < 2) {
            return Double.POSITIVE_INFINITY;
        }

        Point[] pointsByX = points.clone();

        Arrays.sort(pointsByX, Comparator.comparingDouble(Point::getX));

        return closestPair(pointsByX, 0, pointsByX.length - 1, 1);
    }

    private double closestPair(Point[] points, int left, int right, int currentDepth) {

        maxRecursionDepth = Math.max(maxRecursionDepth, currentDepth);

        int size = right - left + 1;

        if (size <= 3) {
            return bruteForce(points, left, right);
        }

        int middle = left + (right - left) / 2;

        double leftDistance = closestPair(points, left, middle, currentDepth + 1);

        double rightDistance = closestPair(points, middle + 1, right, currentDepth + 1);

        double delta = Math.min(leftDistance, rightDistance);

        double middleX = points[middle].getX();

        Point[] strip = new Point[size];
        int stripSize = 0;

        for (int i = left; i <= right; i++) {

            comparisons++;

            if (Math.abs(points[i].getX() - middleX) < delta) {
                strip[stripSize++] = points[i];
            }
        }

        Arrays.sort(strip, 0, stripSize, Comparator.comparingDouble(Point::getY));

        double stripDistance = delta;

        for (int i = 0; i < stripSize; i++) {
            for (int j = i + 1; j < stripSize && strip[j].getY() - strip[i].getY() < stripDistance; j++) {
                comparisons++;

                double distance = distance(strip[i], strip[j]);
                stripDistance = Math.min(stripDistance, distance);
            }
        }

        return Math.min(delta, stripDistance);
    }

    private double bruteForce(Point[] points, int left, int right) {

        double minimum = Double.POSITIVE_INFINITY;

        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                comparisons++;
                minimum = Math.min(minimum, distance(points[i], points[j])
                );
            }
        }

        return minimum;
    }

    private double distance(Point first, Point second) {

        double dx = first.getX() - second.getX();
        double dy = first.getY() - second.getY();

        return Math.sqrt(
                dx * dx + dy * dy
        );
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }
}