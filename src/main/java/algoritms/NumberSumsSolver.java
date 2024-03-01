package algoritms;

import java.util.*;

import static algoritms.ArraySum.*;

public class NumberSumsSolver {
    static boolean resolved = false;


    public static void main(String[] args) {
        List<Integer> colSum = List.of(13, 22, 8, 11, 5, 11, 5);
        List<Integer> rowSum = List.of(15, 11, 6, 10, 14, 14, 5);
        List<List<Integer>> playDesk = List.of(
                List.of(8, 7, 6, 8, 7, 9, 8),
                List.of(3, 1, 8, 3, 3, 7, 4),
                List.of(2, 5, 1, 7, 1, 8, 6),
                List.of(8, 1, 2, 6, 4, 2, 2),
                List.of(5, 9, 1, 8, 5, 9, 7),
                List.of(4, 5, 9, 6, 4, 3, 5),
                List.of(9, 5, 9, 1, 1, 8, 3)
        );

        printPlayDesk(colSum, rowSum, playDesk);
        List<List<Integer>> solvedDesk = solve(colSum, rowSum, playDesk);
        System.out.println("======== Yshoooo ======");
        printPlayDesk(colSum, rowSum, solvedDesk);
    }

    private static List<List<Integer>> solve(List<Integer> columnsSums, List<Integer> rowSums, List<List<Integer>> playDesk) {
        Map<Integer, List<List<Integer>>> resultsStorage = calculatePossibleRows(rowSums, playDesk);
        return buildForResolve(0, new ArrayList<>(), columnsSums, resultsStorage);
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
