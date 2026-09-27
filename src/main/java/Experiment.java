import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Experiment {

    private static final int[] SIZES = {
            100, 500, 1000, 5000, 10000
    };

    private static final String[] INPUT_TYPES = {
            "random", "sorted", "reverse", "duplicates"
    };

    public static void runExperiments() {

        createResultsDirectory();

        try (PrintWriter writer = new PrintWriter(
                new FileWriter("results/results.csv"))) {

            writer.println(
                    "algorithm,inputType,size,timeNs,recursionDepth,comparisons"
            );

            for (String inputType : INPUT_TYPES) {

                for (int size : SIZES) {
                    int[] baseArray = generateArray(size, inputType);

                    runMergeSort(baseArray, inputType, size, writer);

                    runQuickSort(baseArray, inputType, size,writer);

                    runDeterministicSelect(baseArray, inputType, size, writer);

                    runClosestPair(inputType, size, writer);
                }
            }

            System.out.println("Experiments completed.");

            System.out.println("Results saved to results/results.csv");

        } catch (IOException e) {

            System.out.println("Error writing results: " + e.getMessage());
        }
    }

    private static void runMergeSort(int[] baseArray, String inputType, int size, PrintWriter writer) {

        int[] array = baseArray.clone();

        MergeSorter sorter = new MergeSorter();

        long start = System.nanoTime();

        sorter.sort(array);

        long end = System.nanoTime();

        long time = end - start;

        writer.println(
                "MergeSort," + inputType + "," + size + "," + time + ","
                        + sorter.getMaxRecursionDepth() + "," + sorter.getComparisons());
    }

    private static void runQuickSort(int[] baseArray, String inputType, int size, PrintWriter writer) {

        int[] array = baseArray.clone();

        QuickSorter sorter = new QuickSorter();

        long start = System.nanoTime();

        sorter.sort(array);

        long end = System.nanoTime();

        long time = end - start;

        writer.println(
                "QuickSort," + inputType + "," + size + "," + time + ","
                        + sorter.getMaxRecursionDepth() + "," + sorter.getComparisons());
    }

    private static void runDeterministicSelect(int[] baseArray, String inputType, int size, PrintWriter writer) {

        int[] array = baseArray.clone();

        DeterministicSelector selector = new DeterministicSelector();

        int k = size / 2;

        long start = System.nanoTime();

        selector.select(array, k);

        long end = System.nanoTime();

        long time = end - start;

        writer.println(
                "DeterministicSelect," + inputType + "," + size + "," + time + ","
                        + selector.getMaxRecursionDepth() + "," + selector.getComparisons());
    }


    private static void runClosestPair(String inputType, int size, PrintWriter writer) {

        Point[] points = generatePoints(size, inputType);

        ClosestPairSolver solver = new ClosestPairSolver();

        long start = System.nanoTime();

        solver.findClosestDistance(points);

        long end = System.nanoTime();

        long time = end - start;

        writer.println(
                "ClosestPair," + inputType + "," + size + "," + time + ","
                        + solver.getMaxRecursionDepth() + "," + solver.getComparisons());
    }

    private static Point[] generatePoints(int size, String type) {

        Point[] points = new Point[size];

        Random random = new Random(42);

        for (int i = 0; i < size; i++) {

            double x;
            double y;

            switch (type) {

                case "random":
                    x = random.nextDouble() * size;
                    y = random.nextDouble() * size;
                    break;

                case "sorted":
                    x = i;
                    y = i;
                    break;

                case "reverse":
                    x = size - i;
                    y = i;
                    break;

                case "duplicates":
                    x = random.nextInt(10);
                    y = random.nextInt(10);
                    break;

                default:
                    throw new IllegalArgumentException("Unknown input type: " + type);
            }

            points[i] = new Point(x, y);
        }
        return points;
    }

    private static int[] generateArray(int size, String type) {

        int[] array = new int[size];

        Random random = new Random(42);

        switch (type) {

            case "random":

                for (int i = 0; i < size; i++) {
                    array[i] = random.nextInt(size * 10);
                }

                break;

            case "sorted":

                for (int i = 0; i < size; i++) {
                    array[i] = i;
                }

                break;

            case "reverse":

                for (int i = 0; i < size; i++) {
                    array[i] = size - i;
                }

                break;

            case "duplicates":

                for (int i = 0; i < size; i++) {
                    array[i] = random.nextInt(10);
                }

                break;

            default:

                throw new IllegalArgumentException(
                        "Unknown input type: " + type
                );
        }

        return array;
    }

    private static void createResultsDirectory() {

        File directory = new File("results");

        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}