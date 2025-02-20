package no.ntnu.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadedServer extends Server {
  private ClientHandler clientHandler;

  public static void main(String[] args) {
    SingleThreadedServer singleThreadedServer = new SingleThreadedServer();
  }

  public SingleThreadedServer() {
    try (ServerSocket serverSocket = new ServerSocket(TCP_PORT)) {
      isRunning = true;
      while (isRunning) {
        Socket socket = serverSocket.accept();
        this.clientHandler = new ClientHandler(socket);
        this.clientHandler.run();
      }
    } catch (IOException e) {
      System.out.println("Could not start the server.");
      e.printStackTrace();
    }
  }
}
