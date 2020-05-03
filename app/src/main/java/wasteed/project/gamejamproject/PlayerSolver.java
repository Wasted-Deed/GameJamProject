package wasteed.project.gamejamproject;

import java.util.ArrayList;
import java.util.Collections;

import wasteed.project.gamejamproject.map.Board;
import wasteed.project.gamejamproject.map.Cell;
import wasteed.project.gamejamproject.map.ClientBoard;
import wasteed.project.gamejamproject.map.Move;
import wasteed.project.gamejamproject.map.MoveType;
import wasteed.project.gamejamproject.map.Pair;
import wasteed.project.gamejamproject.map.Tower;

public class PlayerSolver {

    private ArrayList<Player> players;
    ClientBoard board;

    PlayerSolver(ArrayList<Player> players, ClientBoard board) {
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
            ArrayList<Pair> dd = new ArrayList<>();
            dd.add(new Pair(1, 0));
            dd.add(new Pair(-1, 0));
            dd.add(new Pair(0, 1));
            dd.add(new Pair(0, -1));
            boolean isEnd = false;
            for (int j = 0; j < dd.size(); ++j) {
                int x = xs + dd.get(j).getX();
                int y = ys + dd.get(j).getY();
                Move move = new Move(x, y, MoveType.Fight);
                if (board.isValid(move) && board.getTower(x, y) != tower) {
                    if (board.getTower(x, y) == null) {
                        Move movef = new Move(x, y, MoveType.Take);
                        board.makeMove(movef);
                    } else {
                        Player opponent = board.getTower(x, y).getOwner();
                        if (opponent.getArmy() < player.getArmy()) {
                            board.makeMove(move);
                            player.setArmy(player.getArmy() - opponent.getArmy());
                            isEnd = true;
                            break;
                        }
                    }
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
