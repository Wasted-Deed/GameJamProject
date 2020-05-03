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

    public String makeMove(Player player) {
        UnitInterface unit = player.getUnitInterface();
        unit.setPeople(unit.getPeople() + 10);
        unit.setArmy(unit.getArmy() + 10);
        unit.setMoney(unit.getMoney() + 10);
        Tower tower = player.getTower();
        ArrayList<Cell> territory = tower.getTerritory();
        Collections.shuffle(territory);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < territory.size(); ++i) {
            int xs = territory.get(i).getX();
            int ys = territory.get(i).getY();
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
                            sb.append(tower.getFLAG() + " takes point(" + x + ", " + y + ")");
                        } else {
                            Player opponent = board.getTower(x, y).getOwner();
                            if (opponent.getUnitInterface().getArmy() < player.getUnitInterface().getArmy()) {
                                tower.makeMove(move);
                                sb.append(tower.getFLAG() + " attacks player " + opponent.getTower().getFLAG() + "point (" + x + ", " + y + ")");
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
        return sb.toString();

    }

    private void dimplomat() {

    }

    private void fighter() {

    }


}
