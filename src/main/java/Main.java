import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[] numbers = {5, 2, 8, 1, 3, 7, 4, 6};

        MergeSorter sorter = new MergeSorter();

        System.out.println("Before:");
        System.out.println(Arrays.toString(numbers));

        sorter.sort(numbers);

        System.out.println("After:");
        System.out.println(Arrays.toString(numbers));

        System.out.println("Comparisons: " + sorter.getComparisons());
        System.out.println("Max recursion depth: "
                + sorter.getMaxRecursionDepth());
    }
}