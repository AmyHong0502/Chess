package me.amyhong;

public interface Movement {

    int[][] movable();

    int[][] capturable();

    int[][] searchPath(final int destColumnIndex, final int destRowIndex);
}
