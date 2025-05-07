import java.io.*;
import java.net.*;

public class NetworkSharing {

    public static void sendProgressToPeer(Trainee trainee, String peerIP, int port) {
        try (Socket socket = new Socket(peerIP, port);
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {

            output.writeObject(trainee);
            System.out.println("Progress sent successfully to " + peerIP);

        } catch (IOException e) {
            System.out.println("Error sending progress: " + e.getMessage());
        }
    }

    public static Trainee receiveProgressFromPeer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket socket = serverSocket.accept();
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {

            Trainee receivedTrainee = (Trainee) input.readObject();
            System.out.println("Progress received successfully from " + socket.getInetAddress().getHostAddress());
            return receivedTrainee;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error receiving progress: " + e.getMessage());
            return null;
        }
    }
}