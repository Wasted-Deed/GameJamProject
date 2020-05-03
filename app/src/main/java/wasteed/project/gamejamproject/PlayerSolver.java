package wasteed.project.gamejamproject;

import java.util.ArrayList;
import java.util.Collections;

import wasteed.project.gamejamproject.map.Cell;
import wasteed.project.gamejamproject.map.ClientBoard;
import wasteed.project.gamejamproject.map.Move;
import wasteed.project.gamejamproject.map.MoveType;
import wasteed.project.gamejamproject.map.Pair;
import wasteed.project.gamejamproject.map.Tower;

public class PlayerSolver {

    private ArrayList<Player> players;
    ClientBoard board;

    public PlayerSolver(ArrayList<Player> players, ClientBoard board) {
        this.players = players;
        this.board = board;
    }

    public void makeMove(Player player) {
        Tower tower = player.getTower();
        ArrayList<Cell> territory = tower.getTerritory();
        Collections.shuffle(territory);
        for (int i = 0; i < territory.size(); ++i) {
            int xs = territory.get(i).getX();
            int ys = territory.get(i).getY();
            //ArrayList<Pair> dd = new ArrayList<>();
            /*dd.add(new Pair(1, 0)); dd.add(new Pair(-1, 0));
            dd.add(new Pair(0, 1)); dd.add(new Pair(0, -1));
            dd.add(new Pair(1, 1)); dd.add(new Pair(-1, 1));
            dd.add(new Pair(1, -1)); dd.add(new Pair(-1, -1));*/
            boolean isEnd = false;
            for (int j = -1; j <= 1; ++j) {
                for (int h = -1; h <= 1; ++h) {
                    int x = xs + h;
                    int y = ys + j;
                    Move move = new Move(x, y, MoveType.Fight);
                    if (tower.isValid(x, y) && board.getTower(x, y) != tower) {
                        if (board.getTower(x, y) == null) {
                            Move movef = new Move(x, y, MoveType.Take);
                            tower.makeMove(movef);
                        } else {
                            Player opponent = board.getTower(x, y).getOwner();
                            if (opponent.getUnitInterface().getArmy() < player.getUnitInterface().getArmy()) {
                                tower.makeMove(move);
                                player.getUnitInterface().setArmy(player.getUnitInterface().getArmy() - opponent.getUnitInterface().getArmy());
                            }
                        }
                        isEnd = true;
                        break;
                    }
                }
                if (isEnd) {
                    break;
                }
            }
            if (isEnd) {
                break;
            }
        }


    }

    private void dimplomat() {

    }

    private void fighter() {

    }


}
