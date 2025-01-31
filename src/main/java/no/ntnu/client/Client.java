package no.ntnu.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;

public class Client {
  public static final int TCP_PORT = 1238;

  private Socket socket;
  private BufferedReader reader;
  private PrintWriter writer;

  private boolean running;

  public static void main(String[] args) {
    Client client = new Client();
    client.establishConnection();
  }

  public Client() {
    this.running = false;
  }

  public void establishConnection() {
    if (!running) {
      try {
        this.socket = new Socket("localhost", TCP_PORT);
      } catch (IOException e) {
        System.out.println("Failed to connect to the server: " + e.getMessage());
      } try {
        this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.writer = new PrintWriter(this.socket.getOutputStream(), true);
      } catch (IOException e) {
        System.out.println("Failed to create Writer and Reader: " + e.getMessage());
        throw new IllegalArgumentException("Could not create writer and reader");
      }
    }
  }

  public void send(char operation, double x, double y) {
    this.writer.println(operation + "-" + x + "-" + y);
  }

  public void receive() throws IOException {
    double result = Double.parseDouble(this.reader.readLine());
    System.out.println("The result of the arithmetic operation is: " + result);
  }
}