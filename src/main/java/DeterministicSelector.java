public class DeterministicSelector {

    private int comparisons;
    private int maxRecursionDepth;

    public int select(int[] array, int k) {

        comparisons = 0;
        maxRecursionDepth = 0;

        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }

        if (k < 0 || k >= array.length) {
            throw new IllegalArgumentException("Invalid k");
        }

        return select(array, 0, array.length - 1, k, 1);
    }

    private int select(int[] array, int left, int right, int k, int currentDepth) {

        maxRecursionDepth = Math.max(
                maxRecursionDepth,
                currentDepth
        );

        if (left == right) {
            return array[left];
        }

        int pivot = medianOfMedians(array, left, right);

        int pivotIndex = partition(array, left, right, pivot);

        if (k == pivotIndex) {
            return array[pivotIndex];
        }

        if (k < pivotIndex) {
            return select(array, left, pivotIndex - 1, k, currentDepth + 1);
        }

        return select(array, pivotIndex + 1, right, k, currentDepth + 1
        );
    }

    private int medianOfMedians(int[] array, int left, int right) {

        int size = right - left + 1;

        if (size <= 5) {
            insertionSort(array, left, right);

            return array[left + size / 2];
        }

        int numberOfGroups = (size + 4) / 5;

        for (int i = 0; i < numberOfGroups; i++) {

            int groupLeft = left + i * 5;
            int groupRight = Math.min(groupLeft + 4, right);

            insertionSort(array, groupLeft, groupRight);

            int medianIndex = groupLeft + (groupRight - groupLeft) / 2;

            swap(array, left + i, medianIndex);
        }

        int medianOfMediansIndex = left + numberOfGroups / 2;

        return select(array, left, left + numberOfGroups - 1, medianOfMediansIndex, 1);
    }

    private int partition(int[] array, int left, int right, int pivotValue) {

        int pivotIndex = left;

        for (int i = left; i <= right; i++) {

            comparisons++;

            if (array[i] == pivotValue) {
                pivotIndex = i;
                break;
            }
        }

        swap(array, pivotIndex, right);

        int storeIndex = left;

        for (int i = left; i < right; i++) {

            comparisons++;

            if (array[i] < pivotValue) {
                swap(array, i, storeIndex);
                storeIndex++;
            }
        }

        swap(array, storeIndex, right);

        return storeIndex;
    }

    private void insertionSort(int[] array, int left,int right) {

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