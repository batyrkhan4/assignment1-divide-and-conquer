import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmTest {

    @Test
    void testMergeSort() {

        int[] array = {5, 2, 8, 1, 3, 7, 4, 6};

        int[] expected = array.clone();
        Arrays.sort(expected);

        MergeSorter sorter = new MergeSorter();
        sorter.sort(array);

        assertArrayEquals(expected, array);
    }

    @Test
    void testQuickSort() {

        int[] array = {5, 2, 8, 1, 3, 7, 4, 6};

        int[] expected = array.clone();
        Arrays.sort(expected);

        QuickSorter sorter = new QuickSorter();
        sorter.sort(array);

        assertArrayEquals(expected, array);
    }

    @Test
    void testSortingWithDuplicates() {

        int[] array = {5, 2, 5, 1, 2, 5, 3, 1};

        int[] expected = array.clone();
        Arrays.sort(expected);

        MergeSorter mergeSorter = new MergeSorter();

        QuickSorter quickSorter = new QuickSorter();

        int[] mergeArray = array.clone();
        int[] quickArray = array.clone();

        mergeSorter.sort(mergeArray);
        quickSorter.sort(quickArray);

        assertArrayEquals(expected, mergeArray);
        assertArrayEquals(expected, quickArray);
    }

    @Test
    void testEmptyArray() {

        int[] array = {};

        MergeSorter mergeSorter = new MergeSorter();

        QuickSorter quickSorter = new QuickSorter();

        mergeSorter.sort(array);
        quickSorter.sort(array);

        assertArrayEquals(new int[]{}, array);
    }

    @Test
    void testSingleElement() {

        int[] array = {42};

        MergeSorter mergeSorter = new MergeSorter();

        QuickSorter quickSorter = new QuickSorter();

        mergeSorter.sort(array);
        assertEquals(42, array[0]);

        quickSorter.sort(array);
        assertEquals(42, array[0]);
    }

    @Test
    void testDeterministicSelect() {

        Random random = new Random(42);

        DeterministicSelector selector = new DeterministicSelector();

        for (int test = 0; test < 100; test++) {

            int size = 1 + random.nextInt(100);

            int[] array = new int[size];

            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(1000);
            }

            int k = random.nextInt(size);

            int[] expected = array.clone();

            Arrays.sort(expected);

            int actual =
                    selector.select(array.clone(), k);

            assertEquals(expected[k], actual);
        }
    }

    @Test
    void testClosestPair() {

        Point[] points = {
                new Point(0, 0),
                new Point(5, 5),
                new Point(1, 1),
                new Point(10, 10),
                new Point(2, 2)
        };

        ClosestPairSolver solver = new ClosestPairSolver();

        double actual = solver.findClosestDistance(points);

        double expected = Math.sqrt(2);

        assertEquals(expected, actual, 0.000001);
    }

    @Test
    void testClosestPairAgainstBruteForce() {

        Random random = new Random(42);

        for (int test = 0; test < 20; test++) {
            int size = 2 + random.nextInt(50);
            Point[] points = new Point[size];

            for (int i = 0; i < size; i++) {
                double x = random.nextDouble() * 100;
                double y = random.nextDouble() * 100;

                points[i] = new Point(x, y);
            }

            ClosestPairSolver solver = new ClosestPairSolver();

            double actual = solver.findClosestDistance(points);

            double expected =
                    bruteForceClosestPair(points);

            assertEquals(expected, actual, 0.000001);
        }
    }
    private double bruteForceClosestPair(Point[] points) {

        double minimum = Double.POSITIVE_INFINITY;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {

                double dx = points[i].getX() - points[j].getX();

                double dy = points[i].getY() - points[j].getY();

                double distance = Math.sqrt(dx * dx + dy * dy);

                minimum = Math.min(minimum, distance);
            }
        }

        return minimum;
    }
}