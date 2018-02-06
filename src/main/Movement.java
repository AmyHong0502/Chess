package main;

public interface Movement {
    int[][] moveable(int x, int y);

    int[][] catchable(int x, int y);
}
