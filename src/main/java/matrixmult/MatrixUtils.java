package matrixmult;

import java.util.Random;
import java.util.function.Function;

public class MatrixUtils {
  public static int[][] calculate(MatrixMultiply matrixMultiply) {
    int[][] result;
    int[][] matrixA = fillMatrixWithRandomValues(1000, 1000);
    int[][] matrixB = fillMatrixWithRandomValues(1000, 100);

    try {
      result = matrixMultiply.multiply(matrixA, matrixB);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  public static int[][] fillMatrixWithRandomValues(int size, int range) {
    int[][] matrix = new int[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        Random random = new Random();
        matrix[i][j] = random.nextInt(range);
      }
    }
    return matrix;
  }

  public static long calculateOperationTime(Function<MatrixMultiply, int[][]> methodFunction, MatrixMultiply matrixMultiply){
    long startTime = System.nanoTime();
    methodFunction.apply(matrixMultiply);
    long endTime = System.nanoTime();
    return endTime - startTime;
  }

  /**
   * This method was not called from code.
   * Yoa are free to test it and apply
   * it output defined matrix
   * */
  public static void printMatrix(int[][] result) {
    for (int[] row : result) {
      for (int element : row) {
        System.out.print(element + " ");
      }
      System.out.println();
    }
  }
}
