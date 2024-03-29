package matrixmult;

public class MatrixMultiplySingleThread implements MatrixMultiply {
    public int[][] multiply(int[][] matrixA, int[][] matrixB) {
        int n = matrixA.length;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return result;
    }
}

