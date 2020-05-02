package wasteed.project.gamejamproject.map;

public class SeqBotPlayer extends UserInterface implements Player {

    private Tower tower;

    SeqBotPlayer() {
        this.tower = null;
    }

    @Override
    public void setTower(Tower tower) {
        this.tower = tower;
    }

    @Override
    public void makeMove(Move move) {
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); ++j) {
                if (!tower.belongs(i, j)) {
                    tower.join(i, j);
                    return;
                }
            }
        }
    }

}
