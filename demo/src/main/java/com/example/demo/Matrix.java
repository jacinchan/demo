package com.example.demo;

import java.util.Arrays;

public class Matrix {
    final int[][] values;
    final int length;

    public Matrix(int[][] values) {
        this.values = values;
        this.length = values.length;
    }

    public Matrix of(int[][] source) {
        int[][] copy = Arrays.stream(source).map(int[]::clone).toArray(int[][]::new);
        return new Matrix(copy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return Arrays.deepEquals(values, matrix.values);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(values);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < values.length; r++) {
            for (int c = 0; c < values.length; c++) {
                sb.append(values[r][c]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
