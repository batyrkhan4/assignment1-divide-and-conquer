import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        testMergeSort();
        testQuickSort();
        testDeterministicSelect();
        testClosestPair();
        Experiment.runExperiments();
    }
    private static void testMergeSort() {
        System.out.println("========== MERGE SORT ==========");

        int[] numbers = {5, 2, 8, 1, 3, 7, 4, 6};

        MergeSorter sorter = new MergeSorter();

        System.out.println("Before:");
        System.out.println(Arrays.toString(numbers));

        sorter.sort(numbers);

        System.out.println("After:");
        System.out.println(Arrays.toString(numbers));

        System.out.println("Comparisons: " + sorter.getComparisons());
        System.out.println("Max recursion depth: " + sorter.getMaxRecursionDepth());

        System.out.println();
    }

    private static void testQuickSort() {
        System.out.println("========== QUICK SORT ==========");

        int[] numbers = {5, 2, 8, 1, 3, 7, 4, 6};

        QuickSorter sorter = new QuickSorter();

        System.out.println("Before:");
        System.out.println(Arrays.toString(numbers));

        sorter.sort(numbers);

        System.out.println("After:");
        System.out.println(Arrays.toString(numbers));

        System.out.println("Comparisons: " + sorter.getComparisons());
        System.out.println("Max recursion depth: " + sorter.getMaxRecursionDepth());

        System.out.println();
    }

    private static void testDeterministicSelect() {

        System.out.println("========== DETERMINISTIC SELECT ==========");

        int[] numbers = {7, 2, 9, 1, 5, 3, 8};

        DeterministicSelector selector =
                new DeterministicSelector();

        int k = 3;

        System.out.println("Array:");
        System.out.println(Arrays.toString(numbers));

        System.out.println("k = " + k);

        int result = selector.select(numbers, k);

        System.out.println("k-th element: " + result);
        System.out.println(
                "Comparisons: " + selector.getComparisons()
        );
        System.out.println(
                "Max recursion depth: "
                        + selector.getMaxRecursionDepth()
        );

        System.out.println();
    }

    private static void testClosestPair() {

        System.out.println("========== CLOSEST PAIR ==========");

        Point[] points = {
                new Point(0, 0),
                new Point(5, 5),
                new Point(1, 1),
                new Point(10, 10),
                new Point(2, 2)
        };

        ClosestPairSolver solver =
                new ClosestPairSolver();

        double result =
                solver.findClosestDistance(points);

        System.out.println("Points:");

        for (Point point : points) {
            System.out.println(point);
        }

        System.out.println(
                "Closest distance: " + result
        );

        System.out.println(
                "Comparisons: " + solver.getComparisons()
        );

        System.out.println(
                "Max recursion depth: "
                        + solver.getMaxRecursionDepth()
        );

        System.out.println();
    }

}