package Model;

public class Player {
    private Long id;
    private String name;

    public Player() {
        this.name = "";
        this.id = null;
    }

    public Player(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}

