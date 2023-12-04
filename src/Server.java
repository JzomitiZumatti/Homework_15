import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {
    private final int PORT = 8081;

    public void runServer() throws IOException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        try (ServerSocket serverSocket = new ServerSocket(PORT); Socket clientSocket = serverSocket.accept();
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            System.out.println("Server started on port " + PORT);
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            writeMessage(bufferedWriter, Messages.GREETINGS);

            String message = bufferedReader.readLine();

            if (containsForbiddenCharacters(message)) {
                writeMessage(bufferedWriter, Messages.QUESTION);
                message = bufferedReader.readLine();

                if (validateAnswer(message)) {
                    writeMessage(bufferedWriter, LocalDateTime.now().format(dateFormatter) + "\n" + Messages.FAREWELL);
                } else {
                    clientSocket.close();
                }
            } else {
                writeMessage(bufferedWriter, Messages.FAREWELL);
            }
        }
    }

    private boolean containsForbiddenCharacters(String line) {
        return line.contains("ы") || line.contains("ё") || line.contains("э") || line.contains("ъ");
    }

    private boolean validateAnswer(String answer) {
        return "bread".equals(answer) || "хліб".equals(answer);
    }

    private void writeMessage(BufferedWriter writer, Object message) throws IOException {
        writer.write(message.toString());
        writer.newLine();
        writer.flush();
    }
}
