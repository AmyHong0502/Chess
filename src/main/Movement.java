package main;

public interface Movement {

    int[][] movable(final int columnIndex, final int rowIndex);

    int[][] capturable(final int columnIndex, final int rowIndex);
}
