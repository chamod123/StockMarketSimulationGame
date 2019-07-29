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

    public Optional<Player> getPlayer(Long id) {
        return players.stream()
                .filter(player ->  Long.valueOf(player.getId())
                        .equals(id))
                .findFirst();
    }

    public void createPlayer(Player player) {
        player.setId(players.size()+1);
        players.add(player);
    }

    public List<Player> getPlayers(){
        return players;
    }
}
