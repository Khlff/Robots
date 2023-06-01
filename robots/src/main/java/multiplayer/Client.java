package multiplayer;

import gui.MVC.RobotModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8888;
    private final RobotModel myRobot;
    private RobotModel receivedRobot;
    private final String name;

    public Client(RobotModel myRobot, String name) {
        this.myRobot = myRobot;
        this.name = name;
    }

    public void run() {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {

            output.writeUTF(name);

            Thread readThread = new Thread(() -> {
                try {
                    while (true) {
                        Object object = null;
                        try {
                            object = input.readObject();
                            receivedRobot = (RobotModel) object;
                            System.out.println(receivedRobot.getSize());
                            System.out.println("Получен робот от " + name);
                        } catch (Exception ignored) {
                            System.out.println(object);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            readThread.start();

            Thread writeThread = new Thread(() -> {
                while (true) {
                    // Чтобы избежать перегрузки сети, добавим задержку в 100 миллисекунд между отправкой сообщений
                    try {
                        output.writeObject(myRobot);
                        output.flush();
                        Thread.sleep(100);
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            writeThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RobotModel getReceivedRobot() {
        return receivedRobot;
    }
}