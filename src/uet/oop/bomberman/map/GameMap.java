package uet.oop.bomberman.map;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.bomber.Bomber;
import uet.oop.bomberman.entities.character.enemy.*;
import uet.oop.bomberman.entities.tile.DestroyBrick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.entities.tile.normal.Grass;
import uet.oop.bomberman.entities.tile.normal.Portal;
import uet.oop.bomberman.entities.tile.normal.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class GameMap {
    private static int gameLevel = 1;
    private static char[][] map;
    private static int heightMap;
    private static int widthMap;

    public static int getGameLevel() {
        return gameLevel;
    }

    public static void setGameLevel(int gameLevel) {
        GameMap.gameLevel = gameLevel;
    }

    public static int getHeightMap() {
        return heightMap;
    }

    public static int getWidthMap(){
        return widthMap;
    }

    /*public static void createMap() throws IOException
    {
        System.out.println("__");
        //cập nhật level rồi tạo
        createMap(getGameLevel());
    }*/
    public static void initMap() throws IOException
    {
        Game.entityList = new ArrayList<>();
        Game.stillObjects = new ArrayList<>();
        Game.LayeredEntity = new HashMap<>();
        Game.bombList = new ArrayList<>();
        Game.NUMBER_OF_BOMBS = 1;
        Game.isNextLv = false;
        Game.isPlayerDead = false;
        Game.isGetItem = false;
        Game.isExplosion = false;
        Game.isEnemyDead = false;
        map = new char[getHeightMap()][getWidthMap()];
        createMap(getGameLevel());
    }

    public static void clear() throws IOException
    {
        Game.entityList.clear();
        Game.stillObjects.clear();
        Game.LayeredEntity.clear();
        Game.bombList.clear();
        Game.NUMBER_OF_BOMBS = 1;
        map = new char[getHeightMap()][getWidthMap()];
    }
    public static void fileLoad(int gameLevel) throws IOException
    {
        String path = String.format("res/levels/level%d.txt", gameLevel);
        FileReader filePath = new FileReader(path);
        Scanner reader = new Scanner(filePath);

        reader.nextInt();
        heightMap = reader.nextInt();
        widthMap = reader.nextInt();

        map = new char[heightMap][widthMap];
        reader.nextLine();

        for (int i = 0; i < heightMap; i++) {
            String line = reader.nextLine();
            for (int j = 0; j < widthMap; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        reader.close();
        filePath.close();

    }

    public static void createMap(int gameLevel) throws IOException
    {
        fileLoad(gameLevel);
        for(int i = 0; i<heightMap;i++)
        {
            for(int j = 0; j<widthMap;j++)
            {
                char c = map[i][j];
                Entity obj;
                Stack<Entity> layer = new Stack<>();
                switch (c)
                {
                    case '#':
                        obj = new Wall(j, i, Sprite.wall.getFxImage());
                        Game.stillObjects.add(obj);
                        break;
                    case '*':
                        layer.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        layer.add(new DestroyBrick(j, i, Sprite.brick.getFxImage()));
                        Game.LayeredEntity.put(Game.generateKey(j, i), layer);
                        Game.stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        break;
                    case 'p':
                        Bomber bomber = new Bomber(j, i, Sprite.player_right.getFxImage());
                        obj = new Grass(j, i, Sprite.grass.getFxImage());
                        Game.entityList.add(bomber);
                        Game.stillObjects.add(obj);
                        break;
                    case 'f':
                        layer.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        layer.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                        layer.add(new DestroyBrick(j, i, Sprite.brick.getFxImage()));
                        Game.LayeredEntity.put(Game.generateKey(j, i), layer);
                        break;
                    case 'b':
                        layer.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        layer.add(new BombItem(j, i, Sprite.powerup_bombpass.getFxImage()));
                        layer.add(new DestroyBrick(j, i, Sprite.brick.getFxImage()));
                        Game.LayeredEntity.put(Game.generateKey(j, i), layer);
                        break;
                    case 's':
                        layer.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        layer.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                        layer.add(new DestroyBrick(j, i, Sprite.brick.getFxImage()));
                        Game.LayeredEntity.put(Game.generateKey(j, i), layer);
                        break;
                    case 'x':
                        layer.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        layer.add(new Portal(j, i, Sprite.portal.getFxImage()));
                        layer.add(new DestroyBrick(j, i, Sprite.brick.getFxImage()));
                        Game.LayeredEntity.put(Game.generateKey(j, i), layer);
                        break;
                    case '1':
                        Balloom balloom = new Balloom(j, i, Sprite.oneal_right1.getFxImage());
                        obj = new Grass(j, i, Sprite.grass.getFxImage());
                        Game.entityList.add(balloom);
                        Game.stillObjects.add(obj);
                        break;
                    case '2':
                        Oneal oneal = new Oneal(j, i, Sprite.oneal_right1.getFxImage());
                        obj = new Grass(j, i, Sprite.grass.getFxImage());
                        Game.entityList.add(oneal);
                        Game.stillObjects.add(obj);
                        break;
                    case '3':
                        Doll doll = new Doll(j, i, Sprite.doll_right1.getFxImage());
                        obj = new Grass(j, i, Sprite.grass.getFxImage());
                        Game.entityList.add(doll);
                        Game.stillObjects.add(obj);
                        break;
                    case '4':
                        Minvo minvo = new Minvo(j, i, Sprite.minvo_right1.getFxImage());
                        obj = new Grass(j, i, Sprite.grass.getFxImage());
                        Game.entityList.add(minvo);
                        Game.stillObjects.add(obj);
                        break;
                    case '5':
                        Ghost ghost = new Ghost(j, i, Sprite.ghost.getFxImage());
                        obj = new Grass(j, i, Sprite.grass.getFxImage());
                        Game.entityList.add(ghost);
                        Game.stillObjects.add(obj);
                        break;
                    default:
                        obj = new Grass(j, i, Sprite.grass.getFxImage());
                        Game.stillObjects.add(obj);
                        break;
                }
            }
        }
    }
    public static char[][] getMap() {
        return map;
    }
}
