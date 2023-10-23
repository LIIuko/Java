import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.Executors;


//public class Server {
//    static int hitCount = 0;
//
//    public static void main(String[] args) throws Exception {
//        ServerSocket serverSocket = new ServerSocket(7777, 10000);
//        while (true) {
//            Socket defaultSocket = serverSocket.accept();
//            new Thread(new ServerSlave(defaultSocket)).start();
//            System.out.println("Size is :" + hitCount);
//        }
//    }
//}
//
//class ServerSlave implements Runnable {
//    Socket clientSocket;
//
//    public ServerSlave(Socket socket) {
//        clientSocket = socket;
//        Server.hitCount++;
//    }
//
//    public void run() {
//
//        try {
//            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
//            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
//            System.out.println(inputStream.readUTF());
//            outputStream.writeUTF("Thank you for contacting the web server");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                clientSocket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}


// Первый пробный сервер


//public class Server {
//
//    private final static int PORT = 50001;
//
//    public static void main(String[] args) throws IOException {
//        ServerSocket ss = new ServerSocket(PORT);
//        Socket s = ss.accept();
//
//        System.out.println("Client connected!");
//        InputStreamReader in = new InputStreamReader(s.getInputStream());
//        BufferedReader br = new BufferedReader(in);
//        String str = br.readLine();
//        System.out.println("Client send: " + str);
//    }
//}



///////////////////////////////////////////



public class Server {
    public static void main(String[] args) throws
            Exception {
        try (var listener = new ServerSocket(59898))
        {
            System.out.println("Сервер запущен...");
            var pool = Executors.newFixedThreadPool(20);
            while (true) {
                pool.execute(new Capitalizer(listener.accept()));
            }
        }
    }
    private static class Capitalizer implements Runnable {
        private Socket socket;
        private static List<String> buffer = new ArrayList<>();
        Capitalizer(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            System.out.println("Подключение: " + socket);
            try {
                var in = new Scanner(socket.getInputStream());
                var out = new PrintWriter(socket.getOutputStream(), true);
                while (in.hasNextLine()) {
                    buffer.add(in.nextLine());
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            out.println(buffer);
                            buffer.clear();
                        }
                    }, 5000);
                }


            } catch (Exception e) {
                System.out.println("Ошибка:" + socket);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {

                }
                System.out.println("Closed: " + socket);
            }
        }
    }
}