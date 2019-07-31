package Service;

import Model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerService {
    private final static List<Player> players = new ArrayList<>();

    static {
        players.add(new Player(1, "Chamod"));
        players.add(new Player(2, "Indika"));
        players.add(new Player(3, "Melani"));
        players.add(new Player(4, "Nipuni"));
        players.add(new Player(5, "Dilaka"));
        players.add(new Player(6, "Niroshima"));
    }

    public Player getPlayer(Long id) {
        for (Player player : players) {
            if(Long.valueOf(player.getId()).equals(id)){
                return player;
            }
        }
        return null;
//        return players.stream()
//                .filter(player ->  Long.valueOf(player.getId())
//                        .equals(id))
//                .findFirst();
    }

    public void createPlayer(Player player) {
        player.setId(players.size()+1);
        players.add(player);
    }

    public List<Player> getPlayers(){
        return players;
    }

    public Boolean loginPlayer(Long id, String password) {
        for (Player player : players) {
           if(Long.valueOf(player.getId()).equals(id) && password.equals(player.getPassword())){
               return true;
           }
        }
        return false;
    }
}
