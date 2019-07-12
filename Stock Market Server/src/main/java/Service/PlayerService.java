package Service;

import Model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerService {
    private final static List<Player> players = new ArrayList<>();

    static {
        players.add(new Player(1l, "Chamod"));
        players.add(new Player(2l, "Indika"));
        players.add(new Player(3l, "Melani"));
        players.add(new Player(4l, "Nipuni"));
        players.add(new Player(5l, "Dilaka"));
        players.add(new Player(6l, "Niroshima"));
    }

    public Optional<Player> getPlayer(Long id) {
        return players.stream()
                .filter(player -> player.getId()
                        .equals(id))
                .findFirst();
    }

    public void createPlayer(Player user) {
        players.add(user);
    }

    public List<Player> getPlayers(){
        return players;
    }
}
