package ServerHttp;

import java.io.Serializable;

public interface ServerMessages {
    class RequesServer implements Serializable {}

    class Server implements Serializable {
        public String message;
        public Server(String message) {this.message=message;}
    }
}
