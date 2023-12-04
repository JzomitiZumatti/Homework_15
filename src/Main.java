import java.io.*;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.runServer();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
