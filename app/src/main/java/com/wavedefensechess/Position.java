package com.wavedefensechess;

import java.util.ArrayList;
import java.util.List;

public class Position
{

    int x;
    int y;

    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Position> getPathTo(Position end)
    {
        List<Position> path = new ArrayList<>();

        int x = this.x;
        int y = this.y;

        int x2 = end.getX();
        int y2 = end.getY();

        int dx = Math.abs(x2 - x);
        int dy = Math.abs(y2 - y);

        int sx = x < x2 ? 1 : -1;
        int sy = y < y2 ? 1 : -1;

        int err = dx - dy;

        while (true)
        {
            path.add(new Position(x, y));

            if (x == x2 && y == y2)
            {
                break;
            }

            int e2 = 2 * err;

            if (e2 > -dy)
            {
                err -= dy;
                x += sx;
            }

            if (e2 < dx)
            {
                err += dx;
                y += sy;
            }
        }

        return path;
    }

}
