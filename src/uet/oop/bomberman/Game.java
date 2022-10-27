package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import uet.oop.bomberman.ControlKeyboard.Keyboard;
import uet.oop.bomberman.collision.Collision;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.character.bomber.Bomber;
import uet.oop.bomberman.entities.character.enemy.*;
import uet.oop.bomberman.entities.tile.DestroyBrick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.Item;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.entities.tile.normal.Portal;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.map.GameMap;
import uet.oop.bomberman.menu.HelpStage;
import uet.oop.bomberman.menu.MainMenuStage;
import uet.oop.bomberman.menu.PauseStage;
import uet.oop.bomberman.menu.ScoreStage;
import uet.oop.bomberman.showScore.Score;
import uet.oop.bomberman.showScore.ShowInf;
import uet.oop.bomberman.sound.Sound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.*;

public class Game extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 13;
   // public static int WIDTH_BUFFER = 0;
    private GraphicsContext gc;
    private Canvas canvas;

    public static int LENGTH_OF_FLAME = 1;
    public static int NUMBER_OF_BOMBS = 1;
    public static final int SPEED_OF_DOLL=2;
    public static final int SPEED_OF_MINVO = 2;
    public static final int SPEED_OF_ONEAL = 1;

    public static final int SPEED_OF_BALLOOM = 1;

    public static final int TIME_TO_DISAPPEAR = 100;
    public static final int TIME_TO_EXPLOSION_BOMB = 200;

    public static List<MovingEntity> entityList = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static Map<Integer, Stack<Entity>> LayeredEntity = new HashMap<>();
    public static List<Bomb> bombList = new ArrayList<Bomb>();

    private Bomber bomberman;

    public static boolean isEnemyDead = false;
    public static boolean isPlayerDead = false;
    public static boolean isExplosion = false;
    public static boolean isNextLv = false;
    public static boolean isGetItem = false;
    private final TextFlow textFlow = new TextFlow();
    private final Score score = new Score();
    private final ShowInf showinf = new ShowInf();

    private final MainMenuStage mainMenuStage = new MainMenuStage();
    private final PauseStage pauseStage = new PauseStage();
    private final HelpStage helpStage = new HelpStage();
    private final ScoreStage scoreStage = new ScoreStage(score);
    private Sound sound = new Sound();
    public Game() throws IOException {

    }

    @Override
    public void start(Stage stage) throws Exception {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH  , Sprite.SCALED_SIZE * HEIGHT );
        gc = canvas.getGraphicsContext2D();

        Group root = new Group();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setTitle("BOMBERMAN");

        Scene menunuScene = mainMenuStage.create();
        Scene pauseMenuScene = pauseStage.create();
        Scene helpScene = helpStage.create();
        //Scene aboutOptionScene = aboutOption.create();
        Scene scoreOptionScene = scoreStage.create();

        showinf.makeShowScore(root, textFlow);
        //camera = new Camera(0, 0);

        stage.setScene(menunuScene);
        stage.show();

        boolean[] running = {false};
        sound.getBgSound();
        AnimationTimer timer = new AnimationTimer()
        {
            public void handle (long l)
            {
                menunuScene.setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (MainMenuStage.PLAY) {
                            stage.setScene(scene);
                            MainMenuStage.PLAY = false;
                            running[0] = true;
                        }
                        if (MainMenuStage.HELP) {
                            stage.setScene(helpScene);
                        }
                        if (MainMenuStage.SCORE) {
                            scoreStage.updateScore();
                            stage.setScene(scoreOptionScene);
                            MainMenuStage.SCORE = false;
                        }
                    }
                });
                helpScene.setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (HelpStage.HELP_BACK && MainMenuStage.HELP) {
                            stage.setScene(menunuScene);
                            HelpStage.HELP_BACK = false;
                            MainMenuStage.HELP = false;
                        } else if (HelpStage.HELP_BACK && PauseStage.HELP) {
                            stage.setScene(pauseMenuScene);
                            HelpStage.HELP_BACK = false;
                           PauseStage.HELP = false;
                        }
                    }
                });
                scoreOptionScene.setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (ScoreStage.SCORE_BACK) {
                            stage.setScene(menunuScene);
                            ScoreStage.SCORE_BACK = false;
                            MainMenuStage.SCORE = false;
                        }
                    }
                });

                pauseMenuScene.setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (PauseStage.CONTINUE) {
                            running[0] = true;
                            stage.setScene(scene);
                            Keyboard.pause = !Keyboard.pause;
                            PauseStage.CONTINUE = false;
                        }
                        if (PauseStage.HELP) {
                            stage.setScene(helpScene);
                        }
                        if (PauseStage.RESTART) {
                            try {
                                createNewGame();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            running[0] = true;
                            stage.setScene(scene);
                            Keyboard.pause = !Keyboard.pause;
                            PauseStage.RESTART = false;
                        }
                        if (PauseStage.MAIN_MAIN) {
                            try {
                                createNewGame();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            running[0] = false;
                            stage.setScene(menunuScene);
                            Keyboard.pause = !Keyboard.pause;
                            PauseStage.MAIN_MAIN = false;
                        }

                    }
                });

                pauseMenuScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (Keyboard.pause) {
                            running[0] = true;
                            stage.setScene(scene);
                            Keyboard.pause = !Keyboard.pause;
                            PauseStage.CONTINUE = false;
                        }
                    }
                });

                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        Keyboard.setInputKeyEvent(event);
                        if (running[0] && Keyboard.pause) {
                            running[0] = false;
                            stage.setScene(pauseMenuScene);
                        }
                        if (running[0]) {
                            if (Keyboard.space && NUMBER_OF_BOMBS != 0) {
                                try {
                                    createBomb();
                                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                });

                scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        running[0] = !Keyboard.pause;
                        if (running[0]) {
                            Keyboard.setInputKeyEvent(event);
                        }
                    }
                });

                //sound
                if (running[0]) {
                    try {
                        if (isNextLv) sound.getNextLevelSound();
                        if (isEnemyDead) sound.getEnemyDeadSound();
                        if (isPlayerDead) sound.getPlayerDeadSound();
                        if (isGetItem) sound.getItemSound();
                        if (isExplosion) sound.getExplosionSound();
                        update();
                    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                        e.printStackTrace();
                    }
                    render();


                }
            }
        };
        timer.start();
        //sound.getBgSound();
        GameMap.initMap();

    }


    private void createBomb() throws UnsupportedAudioFileException, LineUnavailableException, IOException  {
        sound = new Sound();
        sound.getPutBomSound();
        bomberman = getBomber();
        int tmpX = (bomberman.getX() + Sprite.SCALED_SIZE / 2)/ Sprite.SCALED_SIZE;
        int tmpY = (bomberman.getY() + Sprite.SCALED_SIZE / 2)/ Sprite.SCALED_SIZE;
        Bomb bomb = new Bomb(tmpX, tmpY, Sprite.bomb.getFxImage());
        bombList.add(bomb);
        NUMBER_OF_BOMBS--;
    }

    // Update
    public void update() throws IOException
    {
        entityUpdate();
        bombUpdate();
        itemUpdate();
        portalUpdate();
    }

    private void entityUpdate() throws IOException{
        Iterator<MovingEntity> iterator = Game.entityList.iterator();
        while (iterator.hasNext()) {
            MovingEntity movingEntity = iterator.next();
            if (movingEntity != null && !(movingEntity instanceof Bomber)) {
                movingEntity.update();
                if (((Enemy) movingEntity).isDestroyed()) {
                    iterator.remove();
                    //score.killEnemy();
                    if (movingEntity instanceof Balloom)
                    {
                        score.killBalloom();
                    }
                    if (movingEntity instanceof Oneal)
                    {
                        score.killOneal();
                    }
                    if (movingEntity instanceof Minvo)
                    {
                        score.killMinvo();
                    }
                    if (movingEntity instanceof Doll)
                    {
                        score.killDoll();
                    }
                    showinf.updateScore(score);
                }
            }
        }
       // camera.tick(Objects.requireNonNull(getBomber()));
        if (!getBomber().isAlive()) {
            score.endGame();
            showinf.updateScore(score);
        }
        Objects.requireNonNull(getBomber()).update();
    }


    /**
     * Cập nhật về bom:
     * - Bomb nổ hay chưa
     * Nổ vào brick, bomber, enemy.
     *
     */

    int cnt_time_bomb_remain = 0;
    int cnt_time_enemydead = 0;
    int cnt_time_playerdead = 0;

    public void bombUpdate()
    {
        Iterator<Bomb> bombItr = bombList.iterator();
        while (bombItr.hasNext())
        {
            Bomb bomb = bombItr.next();
            if (bomb != null)
            {
                bomb.update();
                if (!bomb.isDestroyed() && bomb.isExploding())
                { 	cnt_time_bomb_remain++;
                    isExplosion = cnt_time_bomb_remain == 1;
                    for (int i = 0; i<bomb.getFlameList().size();i++)
                    {
                        Flame flame = bomb.getFlameList().get(i);
                        flame.update();

                        //Nếu flame chạm vào bomber hoặc enemy
                        Iterator<MovingEntity> itr = Game.entityList.iterator();
                        MovingEntity current;
                        while (itr.hasNext())
                        {
                            current = itr.next();
                            if (current instanceof Enemy)
                            {
                                if (Collision.CheckCollision(current,flame))
                                {
                                    ((Enemy) current).enemyDie();
                                    cnt_time_enemydead++;
                                    isEnemyDead = cnt_time_enemydead == 1;
                                }
                            }
                            if (current instanceof Bomber)
                            {
                                if (Collision.CheckCollision(current, flame))
                                {
                                    ((Bomber) current).setAlive(false);
                                    cnt_time_playerdead++;
                                    isPlayerDead = cnt_time_playerdead == 1;
                                }
                            }
                        }

                        //Check nếu flame va chạm vào brick

                        int xFlame = flame.getX() / Sprite.SCALED_SIZE;
                        int yFlame = flame.getY() / Sprite.SCALED_SIZE;
                        if (LayeredEntity.containsKey(generateKey(xFlame,yFlame)))
                        {
                            Stack<Entity> tile = LayeredEntity.get(generateKey(xFlame,yFlame));
                            if (tile.peek() instanceof DestroyBrick)
                            {
                                DestroyBrick brick = (DestroyBrick) tile.peek();
                                brick.setExploded(true);
                                brick.update();
                                if (brick.isDestroyed())
                                {
                                    tile.pop();
                                }
                            }
                        }

                    }
                }
                if (bomb.isDestroyed())
                {
                    NUMBER_OF_BOMBS ++ ;
                    bombItr.remove();
                    cnt_time_bomb_remain = 0;
                    cnt_time_enemydead = 0;
                    cnt_time_playerdead = 0;
                }
            }
        }
    }

    //Item update khi va chạm với bomber
    int cnt_time_item1 = 0; //thời gian hiệu lực của item
    int cnt_time_item2 = 0;
    int cnt_time_item3 = 0;
    public void itemUpdate()
    {
        if (!LayeredEntity.isEmpty())
        {
            for (Integer value : getLayeredEntitySet())
            {
                if (LayeredEntity.get(value).peek() instanceof BombItem
                        && Collision.CollisionHasItem(Objects.requireNonNull(getBomber()),
                        LayeredEntity.get(value).peek()))
                {
                    LayeredEntity.get(value).pop();
                    NUMBER_OF_BOMBS ++;
                    BombItem.timeItem = 0;
                    BombItem.pickUp = true;
                }
                if (LayeredEntity.get(value).peek() instanceof FlameItem
                && Collision.CollisionHasItem(Objects.requireNonNull(getBomber()),
                        LayeredEntity.get(value).peek()))
                {
                    Game.LENGTH_OF_FLAME++;
                    LayeredEntity.get(value).pop();
                    FlameItem.timeItem = 0;
                    FlameItem.pickUp = true;
                }

                if (LayeredEntity.get(value).peek() instanceof SpeedItem
                        && Collision.CollisionHasItem(Objects.requireNonNull(getBomber()), LayeredEntity.get(value).peek())) {
                    LayeredEntity.get(value).pop();
                    Bomber.setBomberSpeed(3);
                    SpeedItem.timeItem = 0;
                    SpeedItem.pickUp = true;
                }
            }
            if (FlameItem.pickUp) {
                cnt_time_item1++;
                FlameItem.timeItem++;
                isGetItem = cnt_time_item1 == 1;
                if (FlameItem.timeItem > 2000) {
                    FlameItem.timeItem = 0;
                    Game.LENGTH_OF_FLAME = 1;
                    FlameItem.pickUp = false;
                }
            }
            if (SpeedItem.pickUp) {
                cnt_time_item2++;
                isGetItem = cnt_time_item2 == 1;
                SpeedItem.timeItem++;
                if (SpeedItem.timeItem > 2000) {
                    SpeedItem.timeItem = 0;
                    Bomber.setBomberSpeed(2);
                    SpeedItem.pickUp = false;
                }
            }
            if (BombItem.pickUp) {
                cnt_time_item3++;
                isGetItem = cnt_time_item3 == 1;
                BombItem.timeItem++;
                if (BombItem.timeItem > 2000) {
                    BombItem.timeItem = 0;
                    NUMBER_OF_BOMBS = 1;
                    BombItem.pickUp = false;
                }
                }
        }
    }


    int cnt_time_nextlv = 0;

    //Xử lí cổng portal: nếu chưa ăn đủ enemy thì không thể qua portal và chuyển màn chơi được
    public void portalUpdate() throws IOException
    {
        int count_enemy = 0;
        boolean canNextLevel = false;
        Iterator<MovingEntity> itr = entityList.iterator();
        while (itr.hasNext())
        {
            MovingEntity e = itr.next();
            if (e instanceof Enemy)
            {
                count_enemy++;
            }
        }

        if (count_enemy == 0)
        {
            canNextLevel = false;
            if (!LayeredEntity.isEmpty())
            {
                for (Integer value : getLayeredEntitySet()) {

                    if (LayeredEntity.get(value).peek() instanceof Portal
                            && Collision.CollisionHasItem(Objects.requireNonNull(getBomber()), LayeredEntity.get(value).peek())) {
                        canNextLevel = true;
                        break;
                    }
                }
            }

        if (canNextLevel)
        {
            GameMap.setGameLevel(GameMap.getGameLevel()+1);
            GameMap.clear();
            GameMap.initMap();
            score.endGame();
            showinf.updateScore(score);
        }
    }
}
    public void createNewGame() throws IOException
    {
        GameMap.setGameLevel(GameMap.getGameLevel());
        GameMap.clear();
        GameMap.initMap();
        score.endGame();
        showinf.updateScore(score);

    }

    // ********** RENDER EVERYTHING

    public void render()
    {
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

        for (Entity stillObject : stillObjects)
        {
            stillObject.render(gc);
        }
        if (!LayeredEntity.isEmpty())
        {
            for (Integer value : getLayeredEntitySet())
            {
                LayeredEntity.get(value).peek().render(gc);
            }
        }
        bombRender();

        Iterator<MovingEntity> animatedEntityIterator = entityList.iterator();
        while (animatedEntityIterator.hasNext())
        {
            MovingEntity movingEntity = animatedEntityIterator.next();
            if (movingEntity !=null)
            {
                movingEntity.render(gc);
            }
        }

    }

    //Render bomb and flames while bomb is exploding
    private void bombRender() {
        for (Bomb bomb : bombList) {
            if (bomb != null) {
                if (!bomb.isDestroyed()) {
                    bomb.render(gc);
                }
                if (!bomb.isDestroyed() && bomb.isExploding()) {
                    for (int i = 0; i < bomb.getFlameList().size(); i++) {
                        bomb.getFlameList().get(i).render(gc);
                    }
                }
            }
        }
//
    }

    /**
     * tạo key cho bảng băm LayeredEntity.
     * @return key
     */

    public static int generateKey(int x, int y)
    {
        return x*100 + y;
    }






    // ====================Getter and Setter methods
    public static Set<Integer> getLayeredEntitySet() {
        return Game.LayeredEntity.keySet();
    }
    public static List<Bomb> getBombList() {
        return bombList;
    }
    public static Bomb getBomb()
    {
        if (bombList.isEmpty()) return null;
        return getBombList().get(0);
    }
    public static Enemy getEnemy()
    {
        Iterator<MovingEntity> itr = Game.entityList.iterator();
        MovingEntity current;
        while (itr.hasNext())
        {
            current = itr.next();
            if (current instanceof Enemy)
            {
                return (Enemy) current;
            }
        }
        return null;
    }
    public static Bomber getBomber() {
        Iterator<MovingEntity> itr = Game.entityList.iterator();

        MovingEntity cur;
        while (itr.hasNext()) {
            cur = itr.next();

            if (cur instanceof Bomber) {
                return (Bomber) cur;
            }
        }

        return null;
    }
    public static Item getItem(int _x, int _y) {
        Item cur;
        if (Game.LayeredEntity.containsKey(generateKey(_x, _y))) {
            if (Game.LayeredEntity.get(generateKey(_x, _y)).peek() instanceof Item) {
                cur = (Item) Game.LayeredEntity.get(generateKey(_x, _y)).peek();
                return cur;
            }
        }
        return null;
    }
}
