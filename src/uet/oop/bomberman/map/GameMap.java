package uet.oop.bomberman.map;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameMap {
    public void createMap(int level) throws IOException
    {
        String path = String.format("res/levels/Level%d.txt", level);

        Path filePath = Paths.get(path);
        Scanner reader = new Scanner(filePath);
        List<Integer> integers = new ArrayList<>();

        int line = 1;
        int levels = 0, row = 0, col = 0;

        //doc du lieu dong 1 lay ra thong so map
        char[] data = new char[3];
        while (line == 1)
        {
            levels = Integer.parseInt(reader.next());
            row = Integer.parseInt(reader.next());
            col = Integer.parseInt(reader.next());
            line++;
        }

        //doc du lieu n dong tiep theo
        char[][] map = new char[row][col];
        int i = -1;
        while (reader.hasNext())
        {
            String line1 = reader.nextLine();
            for (int j = 0;j<line1.length();j++)
            {
                Entity object;
                if (line1.charAt(j) == '*')
                {
                    System.out.println();
                    object = new Brick(j,i, Sprite.brick.getFxImage());
                }
                else if (line1.charAt(j) == '#')
                {
                    object = new Wall(j,i,Sprite.wall.getFxImage());
                }
                else
                {
                    object = new Grass(j,i,Sprite.grass.getFxImage());
                }
                BombermanGame.stillObjects.add(object);
            }
            i++;
        }
    }
}
