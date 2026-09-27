public class MergeSorter {

    private static final int INSERTION_SORT_THRESHOLD = 16;

    private int comparisons;
    private int maxRecursionDepth;

    public void sort(int[] array) {
        comparisons = 0;
        maxRecursionDepth = 0;

        if (array == null || array.length < 2) {
            return;
        }

        int[] buffer = new int[array.length];

        mergeSort(array, buffer, 0, array.length - 1, 1);
    }

    private void mergeSort(int[] array, int[] buffer, int left, int right, int currentDepth) {

        maxRecursionDepth = Math.max(maxRecursionDepth, currentDepth);

        if (right - left + 1 <= INSERTION_SORT_THRESHOLD) {
            insertionSort(array, left, right);
            return;
        }

        int middle = left + (right - left) / 2;

        mergeSort(array, buffer, left, middle, currentDepth + 1);
        mergeSort(array, buffer, middle + 1, right, currentDepth + 1);

        comparisons++;
        if (array[middle] <= array[middle + 1]) {
            return;
        }

        merge(array, buffer, left, middle, right);
    }

    private void merge(int[] array, int[] buffer, int left, int middle, int right) {

        int i = left;
        int j = middle + 1;
        int k = left;

        while (i <= middle && j <= right) {

            comparisons++;

            if (array[i] <= array[j]) {
                buffer[k++] = array[i++];
            } else {
                buffer[k++] = array[j++];
            }
        }

        while (i <= middle) {
            buffer[k++] = array[i++];
        }

        while (j <= right) {
            buffer[k++] = array[j++];
        }

        for (int index = left; index <= right; index++) {
            array[index] = buffer[index];
        }
    }

    private void insertionSort(int[] array, int left, int right) {

        for (int i = left + 1; i <= right; i++) {

            int key = array[i];
            int j = i - 1;

            while (j >= left) {

                comparisons++;

                if (array[j] <= key) {
                    break;
                }

                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }
}