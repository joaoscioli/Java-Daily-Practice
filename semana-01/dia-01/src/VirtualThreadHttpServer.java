import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class VirtualThreadHttpServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080);
             var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            System.out.println("Servidor TCP iniciado na porta 8080...");
            while (true) {
                try {
                    var clientSocket = serverSocket.accept();
                    executor.submit(() -> {
                        try (clientSocket;
                             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                            // Ler a requisição HTTP
                            String line;
                            while ((line = in.readLine()) != null && !line.isEmpty()) {
                                System.out.println(line); // Para depuração
                            }

                            // Enviar a resposta HTTP
                            out.print("HTTP/1.1 200 OK\r\n");
                            out.print("Content-Length: 13\r\n");
                            out.print("Content-Type: text/plain\r\n");
                            out.print("\r\n");
                            out.print("Hello, World!");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}