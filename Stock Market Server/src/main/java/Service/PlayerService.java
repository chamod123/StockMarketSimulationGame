package Service;

import Model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerService {
    private final static List<Player> players = new ArrayList<>();
    static {
        players.add(new Player(1, "Chamod","wijesena","chamod@gmail.com","chamod","1234","1234567890"));
        players.add(new Player(2, "Indika","wijesena","chamod@gmail.com","chamod","1234","1234567890"));
        players.add(new Player(3, "Melani","wijesena","chamod@gmail.com","chamod","1234","1234567890"));
        players.add(new Player(4, "Nipuni","wijesena","chamod@gmail.com","chamod","1234","1234567890"));
        players.add(new Player(5, "Dilaka","wijesena","chamod@gmail.com","chamod","1234","1234567890"));
        players.add(new Player(6, "Niroshima","wijesena","chamod@gmail.com","chamod","1234","1234567890"));
    }

    public static Player getPlayer(Long id) {
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

    public static void createPlayer(Player player) {
        player.setId(players.size()+1);
        players.add(player);
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
