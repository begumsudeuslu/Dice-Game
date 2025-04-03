import java.io.FileWriter;
import java.io.IOException;

public class Player   {
    private String name;
    private int score;

    public Player(String name)  {
        this.name = name;
        this.score =0;
    }
    
    public int getScore()  {
        return score;
    }

    public String getName()  {
        return name;
    }

    public void rollDice(int dice1, int dice2, FileWriter writer)  throws IOException {
        if (dice1 ==1 || dice2 == 1) {
            writer.write(name + " threw " + dice1 + "-" + dice2 + " and " + name + "’s score is " + score + ".\n");
        } else {
            score += (dice1 + dice2);
            writer.write(name + " threw " + dice1 + "-" + dice2 + " and " + name + "’s score is " + score + ".\n") ;
        }
            
    }
}