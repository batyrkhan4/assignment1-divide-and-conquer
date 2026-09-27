import java.util.Random;

public class QuickSorter {

    private final Random random = new Random();

    private int comparisons;
    private int maxRecursionDepth;

    public void sort(int[] array) {
        comparisons = 0;
        maxRecursionDepth = 0;

        if (array == null || array.length < 2) {
            return;
        }

        quickSort(array, 0, array.length - 1, 1);
    }

    private void quickSort(int[] array, int low, int high, int currentDepth) {

        while (low < high) {

            maxRecursionDepth = Math.max(
                    maxRecursionDepth,
                    currentDepth
            );

            int pivotIndex = low + random.nextInt(high - low + 1);

            int partitionIndex = partition(
                    array,
                    low,
                    high,
                    pivotIndex
            );

            int leftSize = partitionIndex - low;
            int rightSize = high - partitionIndex;

            if (leftSize < rightSize) {

                if (low < partitionIndex - 1) {
                    quickSort(
                            array,
                            low,
                            partitionIndex - 1,
                            currentDepth + 1
                    );
                }


                low = partitionIndex + 1;

            } else {

                if (partitionIndex + 1 < high) {
                    quickSort(
                            array,
                            partitionIndex + 1,
                            high,
                            currentDepth + 1
                    );
                }


                high = partitionIndex - 1;
            }
        }
    }

    private int partition(int[] array, int low, int high, int pivotIndex) {

        int pivot = array[pivotIndex];

        swap(array, pivotIndex, high);

        int storeIndex = low;

        for (int i = low; i < high; i++) {

            comparisons++;

            if (array[i] < pivot) {
                swap(array, i, storeIndex);
                storeIndex++;
            }
        }

        swap(array, storeIndex, high);

        return storeIndex;
    }

    private void swap(int[] array, int first, int second) {

        if (first == second) {
            return;
        }

        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }
}