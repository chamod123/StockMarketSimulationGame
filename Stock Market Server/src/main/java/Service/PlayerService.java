package Service;

import Model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerService {
    private final static List<Player> players = new ArrayList<>();


    public static Player getPlayer(Long id) {
        for (Player player : players) {
            if(Long.valueOf(player.getId()).equals(id)){
                return player;
            }
        }
        return null;
    }

    public static void createPlayer(Player player) {
        player.setId(players.size()+1);
        players.add(player);
    }

    public static void updatePlayer(Player player) {
        for (Player player1 : players) {
            if(player.getId()==player1.getId()){
//                player1.setName(player.getName());
                player1.setSecondName(player.getSecondName());
                player1.setEmail(player.getEmail());
                player1.setUserName(player.getUserName());
                player1.setPassword(player.getPassword());
            }
        }
    }

    public List<Player> getPlayers(){
        return players;
    }

    public static int loginPlayer(String userName, String password) {
        for (Player player : players) {
           if(player.getUserName().equals(userName)
                   && password.equals(player.getPassword())){
               return player.getId();
           }
        }
        return 0;
    }
}
