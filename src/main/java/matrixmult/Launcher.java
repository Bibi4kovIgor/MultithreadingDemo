package matrixmult;


import static matrixmult.MatrixUtils.calculateOperationTime;

public class Launcher {
  public static void main(String[] args) throws InterruptedException {
    MatrixMultiplySingleThread matrixMultiplySingleThread = new MatrixMultiplySingleThread();
    MatrixMultiplyMultiThread matrixMultiplyMultiThread = new MatrixMultiplyMultiThread();

    long singleThreadTime = calculateOperationTime(MatrixUtils::calculate, matrixMultiplySingleThread);
    long multiThreadTime =calculateOperationTime(MatrixUtils::calculate, matrixMultiplyMultiThread);

    System.out.println("single thread time is " + singleThreadTime);
    System.out.println("multi thread time is " + multiThreadTime);

    String result = singleThreadTime > multiThreadTime
        ? "Multi thread time won with " + (singleThreadTime - multiThreadTime)
        : "Single thread time won with " + (multiThreadTime - singleThreadTime);

    System.out.println(result);
  }


}
