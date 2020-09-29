package com.example.demo;

import java.util.*;
import java.util.stream.IntStream;

public class Shape {
    private final Matrix data;
    private final int x;
    private final int y;
    private final int rollState;


    public Shape(int x, int y, int[][] source) {
        this.x = x;
        this.y = y;
        int[][] arr = new int[4][4];
        int length = source.length;
        IntStream.range(0, length).forEach(i ->
                IntStream.range(0, length).forEach(j -> arr[i][j] = source[i][j]));
        if (length < 4) {
            IntStream.range(0, 4).forEach(i -> arr[i][3] = 0);
            IntStream.range(0, 4).forEach(j -> arr[3][j] = 0);
        }
        data = new Matrix(arr);
        rollState = getRollState();
    }

    public Shape(Shape shape) {
        this(shape.x, shape.y, shape.data.values);
    }

    private Shape(int x, int y, int rollState, Matrix data) {
        this.x = x;
        this.y = y;
        this.rollState = rollState;
        this.data = data;
    }

    public Shape moveLeft() {
        return new Shape(x - 1, y, rollState, data);
    }

    public Shape moveRight() {
        return new Shape(x + 1, y, rollState, data);
    }

    public Shape moveDown() {
        return new Shape(x, y + 1, rollState, data);
    }

    public Shape roll() {
        List<Matrix> cList = CACHE.get(this.data);
        int index = cList.indexOf(this.data);
        index = ++index % cList.size();
        return new Shape(x, y, index, cList.get(index));
    }

    private int getRollState() {
        return CACHE.get(data).indexOf(data);
    }


    private static final Map<Matrix, List<Matrix>> CACHE = new HashMap<>() {
        {
            int[][] ii = {{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}};
            int[][] ii2 = {{0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}};
            List<Matrix> ilist = Arrays.asList(new Matrix(ii), new Matrix(ii2));
            ilist.forEach(m -> put(m, ilist));

            int[][] ll = {{0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
            int[][] ll2 = {{0, 0, 0, 0}, {1, 1, 1, 0}, {1, 0, 0, 0}, {0, 0, 0, 0}};
            int[][] ll3 = {{1, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}};
            int[][] ll4 = {{0, 0, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
            List<Matrix> llist = Arrays.asList(new Matrix(ll), new Matrix(ll2), new Matrix(ll3), new Matrix(ll4));
            llist.forEach(m -> put(m, llist));

            int[][] jj = {{0, 1, 0, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 0, 0}};
            int[][] jj2 = {{1, 0, 0, 0}, {1, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
            int[][] jj3 = {{0, 1, 1, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}};
            int[][] jj4 = {{0, 0, 0, 0}, {1, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}};
            List<Matrix> jlist = Arrays.asList(new Matrix(jj), new Matrix(jj2), new Matrix(jj3), new Matrix(jj4));
            jlist.forEach(m -> put(m, jlist));

            int[][] oo = {{1, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
            List<Matrix> olist = Collections.singletonList(new Matrix(oo));
            olist.forEach(m -> put(m, olist));

            int[][] tt = {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
            int[][] tt2 = {{0, 1, 0, 0}, {0, 1, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}};
            int[][] tt3 = {{0, 0, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}};
            int[][] tt4 = {{0, 1, 0, 0}, {1, 1, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}};
            List<Matrix> tlist = Arrays.asList(new Matrix(tt), new Matrix(tt2), new Matrix(tt3), new Matrix(tt4));
            tlist.forEach(m -> put(m, tlist));

            int[][] nn = {{0, 1, 0, 0}, {1, 1, 0, 0}, {1, 0, 0, 0}, {0, 0, 0, 0}};
            int[][] nn2 = {{1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
            List<Matrix> nlist = Arrays.asList(new Matrix(nn), new Matrix(nn2));
            nlist.forEach(m -> put(m, nlist));

            int[][] zz = {{1, 0, 0, 0}, {1, 1, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}};
            int[][] zz2 = {{0, 1, 1, 0}, {1, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
            List<Matrix> zlist = Arrays.asList(new Matrix(zz), new Matrix(zz2));
            zlist.forEach(m -> put(m, zlist));
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return x == shape.x &&
                y == shape.y &&
                rollState == shape.rollState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, rollState);
    }

    @Override
    public String toString() {
        return "(x,y)=(" + x + "," + y + ")\n" + data;
    }
}
