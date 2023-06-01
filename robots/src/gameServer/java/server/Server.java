package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static final int PORT = 8888;
    private static final Map<String, ObjectOutputStream> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler extends Thread {
        private final Socket clientSocket;
        private String clientName = "";

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                 ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream())) {

                // Прочитать имя клиента и добавить его в Map
                clientName = input.readUTF();
                clients.put(clientName, output);
                System.out.println("Клиент " + clientName + " подключился.");

                // Цикл чтения сообщений от клиента и отправки их всем остальным клиентам
                while (true) {
                    try {
                        Object message = input.readObject();
                        System.out.println("Получено сообщение от клиента " + clientName + ": " + message);

                        // Отправить сообщение всем остальным клиентам
                        for (ObjectOutputStream recipientOutput : clients.values()) {
                            if (recipientOutput != output) {
                                recipientOutput.writeObject(message);
                                recipientOutput.flush();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("Выключаю сервер...");
                // Удалить клиента из Map
                clients.remove(clientName);
                try {
                    clientSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}