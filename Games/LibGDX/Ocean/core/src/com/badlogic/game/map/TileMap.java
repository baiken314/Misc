package com.badlogic.game.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class TileMap {

    public Tile[][] map;

    // map dimensions in pixels and tiles
    public int width;
    public int height;
    public int cols;
    public int rows;

    // camera positioning
    public int xMax;
    public int yMax;
    public float xOff;
    public float yOff;

    public int tileSize;
    public int scale;

    public TileMap(String mapFileName, int tileSize, String hexColor, int scale) {

        this.tileSize = tileSize;
        this.scale = scale;

        xOff = 0;
        yOff = 0;

        try {

            File mapFile = new File(mapFileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(mapFile));
            String delimiter = "\\s+";

            cols = Integer.parseInt(bufferedReader.readLine());
            width = cols * tileSize;
            rows = Integer.parseInt(bufferedReader.readLine());
            height = rows * tileSize;

            map = new Tile[rows][cols];

            for (int row = 0; row < rows; row++) {
                String[] types = bufferedReader.readLine().split(delimiter);
                for (int col = 0; col < cols; col++) {
                    int type = Integer.parseInt(types[col]);
                    map[row][col] = new Tile(type, col * tileSize, row * tileSize, tileSize);
                    int r = Integer.valueOf(hexColor.substring(0, 2), 16) + (int)(Math.random() * 48 - 24);
                    int g = Integer.valueOf(hexColor.substring(2, 4), 16) + (int)(Math.random() * 48 - 24);
                    int b = Integer.valueOf(hexColor.substring(4, 6), 16) + (int)(Math.random() * 48 - 24);
                    if (r < 0) r = 0; if (r > 255) r = 255;
                    if (g < 0) g = 0; if (g > 255) g = 255;
                    if (b < 0) b = 0; if (b > 255) b = 255;
                    String newHexColor = Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
                    while (newHexColor.length() < 6) {
                        newHexColor = "0" + newHexColor;
                    }
                    map[row][col].sprite.setColor(Color.valueOf(newHexColor));
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void add(int[][] room) {

        for (int row = 0; row < rows; row++) {
            Tile[] newRow = Arrays.copyOf(map[row], cols + room[row].length);
            for (int col = cols; col < cols + room[row].length; col++) {
                newRow[col] = new Tile(room[row][col - cols], col * tileSize, row * tileSize, tileSize);
            }
            map[row] = newRow;
        }

        cols += room[0].length;

    }

    public Tile getTileByPixels(float x, float y) {
        try {
            return map[(int) y / tileSize][(int) x / tileSize];
        }
        catch (Exception e) {
            return map[0][0];
        }
    }

    public void update() {

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                map[row][col].update(xOff, yOff, scale);
            }
        }

    }

    public void draw(SpriteBatch batch) {

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                map[row][col].draw(batch, scale);
            }
        }

    }

}
