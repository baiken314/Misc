package com.badlogic.game.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TileRoom {

    public int[][] map;
    public static String[] roomFiles = { "room0.txt", "room1.txt", "test.txt" };

    public int cols;
    public int rows;

    public TileRoom() {

        try {

            String roomFileName = "maps/" + roomFiles[(int)(roomFiles.length * Math.random())];
            File roomFile = new File(roomFileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(roomFile));
            String delimiters = "\\s+";

            cols = Integer.parseInt(bufferedReader.readLine());
            rows = Integer.parseInt(bufferedReader.readLine());

            map = new int[rows][cols];

            for (int row = 0; row < rows; row++) {
                String[] ints = bufferedReader.readLine().split(delimiters);
                for (int col = 0; col < cols; col++) {
                    map[row][col] = Integer.parseInt(ints[col]);
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
