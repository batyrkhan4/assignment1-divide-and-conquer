# Assignment 1: Divide-and-Conquer Algorithm Analysis

## Overview

This project implements and analyzes four divide-and-conquer algorithms
in Java:

1.  **Merge Sort**
2.  **Quick Sort**
3.  **Deterministic Select (Median-of-Medians)**
4.  **Closest Pair of Points**

The main goal is to compare execution time, recursion depth, and number
of comparisons for different input sizes and input types.

## Project Structure

``` text
assignment1-divide-and-conquer/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── Main.java
│   │       ├── MergeSorter.java
│   │       ├── QuickSorter.java
│   │       ├── DeterministicSelector.java
│   │       ├── ClosestPairSolver.java
│   │       ├── Point.java
│   │       └── Experiment.java
│   └── test/
│       └── java/
│           └── AlgorithmTest.java
├── docs/
│   ├── screenshots/
│   └── plots/
├── results/
│   └── results.csv
├── README.md
├── pom.xml
└── .gitignore
```

## Algorithms and Complexity

### Merge Sort

Merge Sort divides the array into two parts, sorts both parts, and
merges them.

-   Time complexity: **Theta(n log n)**
-   Extra space: **O(n)**
-   Recursion depth: **O(log n)**

Recurrence:

``` text
T(n) = 2T(n/2) + Theta(n)
```

By the Master Theorem:

``` text
T(n) = Theta(n log n)
```

The implementation also uses an insertion-sort cutoff for small
subarrays and one reusable auxiliary buffer.

### Quick Sort

Quick Sort chooses a randomized pivot and partitions the array around
it.

-   Typical time: **O(n log n)**
-   Worst-case time: **O(n\^2)**
-   Expected recursion depth: **O(log n)**
-   Partitioning is performed in-place.

The implementation recursively processes the smaller partition and
iterates over the larger partition to reduce recursion depth.

### Deterministic Select

Deterministic Select finds the k-th smallest element using the
Median-of-Medians method.

The implementation:

-   uses groups of five elements;
-   finds the median of each group;
-   uses the median of those medians as the pivot;
-   partitions the array in-place;
-   recursively continues only in the required part.

The theoretical recurrence is:

``` text
T(n) = T(n/5) + T(7n/10) + Theta(n)
```

Therefore:

``` text
T(n) = Theta(n)
```

### Closest Pair of Points

The Closest Pair algorithm finds the smallest Euclidean distance between
two points.

The implementation:

1.  sorts points by x-coordinate;
2.  divides the points into two halves;
3.  solves both halves recursively;
4.  creates a strip around the middle;
5.  checks points in the strip ordered by y-coordinate.

The recurrence is:

``` text
T(n) = 2T(n/2) + Theta(n)
```

Therefore:

``` text
T(n) = Theta(n log n)
```

## Experimental Setup

The program uses:

``` java
System.nanoTime()
```

to measure execution time.

Input sizes:

``` text
100, 500, 1000, 5000, 10000, 50000
```

Input types:

-   Random
-   Sorted
-   Reverse-sorted
-   Duplicate-heavy

For every experiment, the program records:

-   execution time in nanoseconds;
-   maximum recursion depth;
-   number of comparisons.

The complete results are saved in:

``` text
results/results.csv
```

## Experimental Results

The following tables show averages across the four input types.

### Input size: 10,000

  ------------------------------------------------------------------------------
Algorithm              Average time (ns)  Average recursion            Average
depth        comparisons
  --------------------- ------------------ ------------------ ------------------
ClosestPair                  6,502,625.0               13.0          133,427.8

DeterministicSelect         11,394,075.0               60.5        2,510,257.5

MergeSort                      532,325.0               11.0           88,887.2

QuickSort                      792,825.0                7.0        1,379,202.5
------------------------------------------------------------------------------

### Input size: 50,000

  ------------------------------------------------------------------------------
Algorithm              Average time (ns)  Average recursion            Average
depth        comparisons
  --------------------- ------------------ ------------------ ------------------
ClosestPair                 25,505,775.0               16.0          775,666.2

DeterministicSelect        637,344,225.0              236.2      410,589,792.8

MergeSort                    3,001,950.0               13.0          536,324.5

QuickSort                   15,317,175.0                8.5       31,990,511.2
------------------------------------------------------------------------------

The uploaded results contain **96 experiment records**.

## Recursion Depth Discussion

Recursion depth is the deepest recursive call reached by an algorithm.

For example, for `n = 10000` and random input:

``` text
Deterministic Select: 12
Closest Pair: 13
```

These values are very close.

However, duplicate-heavy input produces a much larger recursion depth
for the current Deterministic Select implementation. The current two-way
partition places values equal to the pivot on one side instead of
grouping equal values separately. This can make the partition
unbalanced.

This is an important observation from the experiment because duplicate
values are part of the required test cases.

The recursion-depth graph is stored in:

``` text
docs/plots/recursion_depth_vs_n.png
```

## Plots

The main execution-time graph is:

``` text
docs/plots/time_vs_n.png
```

The main recursion-depth graph is:

``` text
docs/plots/recursion_depth_vs_n.png
```

Additional graphs for individual input types are also stored in
`docs/plots/`.

## Testing

JUnit tests are included in:

``` text
src/test/java/AlgorithmTest.java
```

The tests check:

-   Merge Sort correctness;
-   Quick Sort correctness;
-   duplicate values;
-   empty arrays;
-   single-element arrays;
-   Deterministic Select against `Arrays.sort()`;
-   Closest Pair against a brute-force solution.

The current test suite contains **8 tests**, and all tests passed.

## How to Build

This is a Maven project.

``` bash
mvn clean test
```

To compile:

``` bash
mvn clean compile
```

## How to Run

Run `Main.java` from IntelliJ IDEA.

`Main.java` first runs small correctness demonstrations and then starts
the experiments.

The experiments create:

``` text
results/results.csv
```

## Discussion

The experiments show that input type can affect algorithm behavior.

Merge Sort has a stable divide-and-conquer structure, so its recursion
depth changes slowly with input size.

Quick Sort uses a randomized pivot. Its recursion behavior can therefore
change between runs.

Closest Pair divides the points into two approximately equal parts, so
its recursion depth grows logarithmically.

Deterministic Select is theoretically designed for linear worst-case
time. However, the current experimental results show a much larger
recursion depth on duplicate-heavy data because of the current two-way
partition. This shows why correct handling of equal values is important.

Execution time and recursion depth are different metrics. Two algorithms
can have similar recursion depths but very different execution times
because the amount of work performed at each recursion level can be
different.

## Reflection

This assignment helped me understand how divide-and-conquer algorithms
work in real programs.

I learned how to measure execution time using `System.nanoTime()`, how
to measure recursion depth, and how to save experimental data in CSV
format.

I also learned that input type is important. Random, sorted,
reverse-sorted, and duplicate-heavy data can produce different results
for the same algorithm.

The experiments helped me connect theoretical complexity with real
program performance.

## Screenshots

Implementation and experiment evidence is stored in:

``` text
docs/screenshots/
```

The screenshots include the experiment run, CSV results, and successful
tests.

## Conclusion

The project implements four divide-and-conquer algorithms and compares
them using different input sizes and input types.

The program measures execution time, maximum recursion depth, and
comparisons. The results are saved in CSV format and visualized with
graphs.

The project also demonstrates that theoretical complexity and practical
performance can differ because of implementation details and input
characteristics.
