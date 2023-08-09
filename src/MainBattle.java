import javax.swing.JOptionPane;

import models.*;

public class MainBattle {
    public static void main(String[] args) {
        NavalBattle game = new NavalBattle();

        String player1Name = JOptionPane.showInputDialog("Nome do Player 1:");
        String player2Name = JOptionPane.showInputDialog("Nome do Player 2:");

        try {
            game.play(player1Name, player2Name);
        } catch (NavalBattleException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
