import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiceGame {
    
    public static void main(String[] args) {
        String inputFileName = args[0];
        String outputFileName = args[1];

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             FileWriter writer = new FileWriter(outputFileName)) {

            int numPlayers = Integer.parseInt(reader.readLine());

            String[] playerNames = reader.readLine().split(",");
            List<Player> players = new ArrayList<>();

            for (String name : playerNames) {
                players.add(new Player(name));
            }

            int currentPlayerIndex = 0;

            String line;
            
            while (players.size() > 1 && (line = reader.readLine()) != null) {
                Player currentPlayer = players.get(currentPlayerIndex);
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();

                String[] dice = line.split("-");
                int dice1 = Integer.parseInt(dice[0]);
                int dice2 = Integer.parseInt(dice[1]);

                if (dice1 == 1 && dice2 == 1) {
                    writer.write(currentPlayer.getName() + " threw 1-1. Game over " + currentPlayer.getName() + "!\n");
                    players.remove(currentPlayer);

                } else if (dice1 == 0 && dice2 == 0) {
                    writer.write(currentPlayer.getName() + " skipped the turn and " + currentPlayer.getName() + "’s score is " + currentPlayer.getScore() + ".\n");
                
                } else {
                    currentPlayer.rollDice(dice1, dice2, writer);
                }

            }

            Player winner = players.get(0);
            writer.write(winner.getName() + " is the winner of the game with the score of " + winner.getScore() + ". Congratulations " + winner.getName() + "!");
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}