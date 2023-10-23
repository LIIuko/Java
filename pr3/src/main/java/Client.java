import java.io.*;
import java.net.Socket;
import java.util.Scanner;


//public class Client {
//    static int excepCount=0;
//    public static void main(String[] args) throws Exception {
//        for (int i = 0; i < 5; i++) {
//            new Thread(new Worker("" + i)).start();
//        }
//        Thread.sleep(10000);
//        System.out.println( Client.excepCount);
//    }
//}
//
//
//class Worker implements Runnable {
//    String clientName;
//
//    public Worker(String name) {
//        clientName = name;
//    }
//
//    public void run() {
//        System.out.println("Process started for : " + clientName);
//        Socket socket = null;
//        try {
//            socket = new Socket("127.0.0.1", 7777);
//            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
//            outputStream.writeUTF("Hello socket. Client number " + clientName + "here");
//            InputStream inFromServer = socket.getInputStream();
//            DataInputStream in = new DataInputStream(inFromServer);
//            System.out.println("Server says " + in.readUTF());
//            System.out.println("Closing socket");
//        } catch (IOException e) {
//            Client.excepCount++;
//            e.printStackTrace();
//        }finally{
//            try {
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}


// Первый пробный клиент

//public class Client {
//
//    private final static int PORT = 50001;
//
//    public static void main(String[] args) throws IOException {
//        Socket s = new Socket("localhost", PORT);
//
//        PrintWriter pr = new PrintWriter(s.getOutputStream());
//        pr.println("Hello");
//        pr.flush();
//    }
//}



/////////////////////////////////////////////////////////




public class Client {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Передайте IP-адрес сервера в качестве единственного аргумента командной строки");
            return;
        }
        try (var socket = new Socket(args[0], 59898)) {
            System.out.println("Введите строки текста, затем Ctrl + D или Ctrl + C, чтобы выйти");
            var scanner = new Scanner(System.in);

            var in = new Scanner(socket.getInputStream());

            var out = new PrintWriter(socket.getOutputStream(), true);
            while (scanner.hasNextLine()) {
                out.println(scanner.nextLine());
                System.out.println(in.nextLine());
            }
        }
    }
}