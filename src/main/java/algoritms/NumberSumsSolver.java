package algoritms;

import java.util.*;

import static algoritms.ArraySum.*;

/**
 * There is a game "Number Sums".
 * <p> The goal is to make the sum of numbers in each row and column be equal to the clues on the side of the board.
 * <p> Play desk looks like
 * <pre>
 *       13  22   8  11   5  11   5
 *  15|   8   7   6   8   7   9   8 |
 *  11|   3   1   8   3   3   7   4 |
 *   6|   2   5   1   7   1   8   6 |
 *  10|   8   1   2   6   4   2   2 |
 *  14|   5   9   1   8   5   9   7 |
 *  14|   4   5   9   6   4   3   5 |
 *   5|   9   5   9   1   1   8   3 |
 *  </pre>
 * <p> And the result is
 * <pre>
 *       13  22   8  11   5  11   5
 *  15|   -   7   -   8   -   -   - |
 *  11|   -   -   8   3   -   -   - |
 *   6|   -   5   -   -   1   -   - |
 *  10|   8   -   -   -   -   2   - |
 *  14|   5   -   -   -   -   9   - |
 *  14|   -   5   -   -   4   -   5 |
 *   5|   -   5   -   -   -   -   - |
 *   </pre>
 */
public class NumberSumsSolver {
    static boolean resolved = false;
    static int iteratorCount = 0;


    public static void main(String[] args) {
        List<Integer> colSum = List.of(24, 10, 14, 13, 16, 11, 2, 23);
        List<Integer> rowSum = List.of(22, 13, 3, 23, 18, 11, 11, 12);
        List<List<Integer>> playDesk = List.of(
                List.of(9, 9, 3, 8, 7, 6, 4, 7),
                List.of(3, 3, 8, 5, 1, 2, 5, 2),
                List.of(3, 4, 9, 7, 1, 6, 2, 9),
                List.of(3, 2, 9, 8, 2, 8, 2, 4),
                List.of(9, 1, 5, 6, 5, 9, 8, 7),
                List.of(5, 2, 5, 9, 3, 3, 3, 1),
                List.of(8, 8, 4, 5, 1, 3, 4, 3),
                List.of(8, 2, 4, 8, 5, 4, 8, 2)
        );

        printPlayDesk(colSum, rowSum, playDesk);
        List<List<Integer>> solvedDesk = solve(colSum, rowSum, playDesk);
        System.out.println("======== Yahoo ======");
        System.out.printf("The solution took [%,d] iterations\n", iteratorCount);
        printPlayDesk(colSum, rowSum, solvedDesk);
    }

    private static List<List<Integer>> solve(List<Integer> columnsSums, List<Integer> rowSums, List<List<Integer>> playDesk) {
        Map<Integer, List<List<Integer>>> resultsStorage = calculatePossibleRows(rowSums, playDesk);
        List<List<Integer>> lists = buildForResolve(0, new ArrayList<>(), columnsSums, resultsStorage);
        if (resolved) {
            return lists;
        } else {
            System.out.println("This game has no solutions :(");
            return unsolvedTemplate(playDesk);
        }
    }

    private static List<List<Integer>> unsolvedTemplate(List<List<Integer>> playDesk) {
        List<List<Integer>> template = new ArrayList<>();
        for (List<Integer> list : playDesk) {
            template.add(createEmptyResultsList(list.size()));
        }
        return template;
    }

    static Map<Integer, List<List<Integer>>> calculatePossibleRows(List<Integer> rowSums, List<List<Integer>> playDesk) {
        Map<Integer, List<List<Integer>>> resultsStorage = new HashMap<>();
        for (int row = 0; row < playDesk.size(); row++) {
            List<List<Integer>> results = possibleResults(rowSums.get(row), playDesk.get(row), 0, playDesk.get(row).size() - 1);
            resultsStorage.put(row, results);
        }
        return resultsStorage;
    }

    static List<List<Integer>> buildForResolve(int row, List<List<Integer>> desk, List<Integer> columnsSums, Map<Integer, List<List<Integer>>> resultsStorage) {
        Iterator<List<Integer>> rowIterator = resultsStorage.get(row).iterator();
        while (rowIterator.hasNext() && !resolved) {
            List<Integer> iteratorNext = rowIterator.next();
            List<List<Integer>> localDesk = setRowToDesk(row, iteratorNext, desk);
            if (row < resultsStorage.keySet().size() - 1) {
                buildForResolve(row + 1, localDesk, columnsSums, resultsStorage);
            } else {
                resolved = allColumnsResolved(columnsSums, desk);
                if (resolved) return desk;
            }
        }
        return desk;
    }

    static List<List<Integer>> setRowToDesk(int rowIndex, List<Integer> row, List<List<Integer>> desk) {
        if (desk.size() <= rowIndex) {
            desk.add(row);
        } else {
            desk.set(rowIndex, row);
        }
        return desk;
    }


    static boolean allColumnsResolved(List<Integer> colSum, List<List<Integer>> desk) {
        for (int col = 0; col < colSum.size(); col++) {
            iteratorCount++;
            int colIndex = col;
            int actColSum = desk.stream().mapToInt(el -> el.get(colIndex)).sum();
            if (actColSum != colSum.get(colIndex)) {
                return false;
            }
        }
        return true;
    }


    static void printPlayDesk(List<Integer> columnsSums, List<Integer> rowSums, List<List<Integer>> desk) {
        System.out.print("    ");
        columnsSums.forEach(el -> System.out.printf("%4s", el));
        System.out.println();
        for (int i = 0; i < columnsSums.size(); i++) {
            System.out.printf("%3s|", rowSums.get(i));
            desk.get(i).forEach(el -> {
                if (el == 0) {
                    System.out.print("   -");
                } else {
                    System.out.printf("%4s", el);
                }
            });
            System.out.println(" |");
        }
    }
}
