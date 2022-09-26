package kr.sharenshare.serial;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerExecutable implements Runnable {

    private final ServerSocket serverSocket;
    private final IMUSet imuSet;
    private Socket socket;

    public ServerExecutable(IMUSet imuSet) {

        this.imuSet = imuSet;

        try {
            this.serverSocket = new ServerSocket(7002);
        } catch (IOException e) {
            throw new RuntimeException("Failed create server socket");
        }

    }


    @Override
    public void run() {
        while (true) {
            System.out.println("Accept Ready");
            try {
                this.socket = this.serverSocket.accept();

                BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
                while (socket.isConnected()) {
                    Thread.sleep(20);

                    String message = imuSet.printQuerternions();
                    bos.write(message.getBytes(StandardCharsets.UTF_8));
                    bos.flush();
                }

            } catch (Exception e) {
                System.out.println("Socket Closed");
            }
        }
    }

}
