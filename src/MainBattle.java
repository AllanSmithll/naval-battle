import models.NavalBattle;

public class MainBattle {
    public static void main(String[] args) throws Exception {
        System.out.println("Bem-vindo à Batalha Naval!");

        NavalBattle jogo = new NavalBattle();
        jogo.play();
    }
}
