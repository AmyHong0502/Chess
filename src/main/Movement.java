package main;

public interface Movement {
    int[][] movable(final int x, final int y);

    int[][] capturable(final int x, final int y);
}
