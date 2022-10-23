package uet.oop.bomberman.showScore;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Score {
    private final List<Integer> highScore;
    private int score = 0;

    public Score() throws IOException {
        highScore = new ArrayList<>();
        getScoreFromFile();
    }

    private void getScoreFromFile() throws IOException {
        String address = "res/showScore/high.txt";
        FileReader fileReader = new FileReader(address);
        Scanner reader = new Scanner(fileReader);
        while (reader.hasNext()) {
            Integer score = Integer.parseInt(reader.nextLine());
            highScore.add(score);
        }
        fileReader.close();

    }

    private void showScoreToFile() throws IOException {
        String address = "res/showScore/high.txt";

        FileWriter fileWriter = new FileWriter(address);

        for (int i = 0; i < 6; i++) {
            fileWriter.write(String.valueOf(highScore.get(i)));
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    public int getScore() {
        return score;
    }

    public List<Integer> getHighScore() {
        return highScore;
    }

    public void killEnemy() {
        score += 100;
    }

    public void endGame() throws IOException {
        highScore.add(getScore());
        Collections.sort(highScore);
        Collections.reverse(highScore);
        showScoreToFile();
        score = 0;
    }

}
