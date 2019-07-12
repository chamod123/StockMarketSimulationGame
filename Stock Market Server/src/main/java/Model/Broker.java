package Model;

public class Broker {
    private Long id;
    private String name;

    public Broker() {
        this.name = "";
        this.id = null;
    }

    public Broker(Long id, String name) {
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
