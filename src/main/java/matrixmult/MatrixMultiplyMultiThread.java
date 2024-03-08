package matrixmult;

import java.util.ArrayList;
import java.util.List;

public class MatrixMultiplyMultiThread implements MatrixMultiply {

  @Override
  public int[][] multiply(int[][] matrixA, int[][] matrixB) throws InterruptedException {
    if(matrixA[0].length != matrixB.length){
      throw new ArithmeticException("Wrong matrix sizes");
    }
    List<Thread> threads = new ArrayList<>();
    int[][] result = new int[matrixA[0].length][matrixB.length];
    final int N_CPU = Runtime.getRuntime().availableProcessors();
    int numRows = matrixA.length;
    int chunkSize = numRows / N_CPU;

    for (int i = 0; i < N_CPU; i++) {
      int startRow = i * chunkSize;
      int endRow = (i == N_CPU - 1) ? numRows : startRow + chunkSize;
      Thread thread = new Thread(() -> {

        int numCols = matrixB[0].length;
        for (int row = startRow; row < endRow; row++) {
          for (int col = 0; col < numCols; col++) {
            for (int sum = 0; sum < matrixB.length; sum++) {
              result[row][col] += matrixA[row][sum] * matrixB[sum][col];
            }
          }
        }
      });

      threads.add(thread);
      thread.start();
    }

    for (Thread thread : threads) {
      thread.join();
    }
    return result;
  }
}

