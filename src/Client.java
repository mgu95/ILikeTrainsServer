import game.engine.Board;
import game.engine.Player;
import game.engine.tools.HTTPStatus;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Client {

    private static SimpleDateFormat formatter = new SimpleDateFormat("'['yyyy-MM-dd HH:mm:ss']' ");
    private static BufferedReader reader;
    private static PrintWriter writer;
    private static Scanner scanner = new Scanner(System.in);
    private static Player player = null;
    private static Board board;

    public static void main(String[] args) {

        String hostName = "localhost";
        int port = 8080;
        System.out.println(getCurrentDateAndTime() + "Uruchamianie cienta: hostname = " + hostName + ", port = " + port);

        try(Socket socket = new Socket()) {

            socket.connect(new InetSocketAddress(hostName, port));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            while (player == null) {
                login();
            }
            Set<String> tables = readBoards();
            System.out.print("Aby rozpocząć ");
            while (board == null) {
                System.out.println("wybierz:\n" +
                        "1 - Aby dołączyć do istniejącego stolika.\n" +
                        "2 - Aby stworzyć stolik.");
                String choice = scanner.nextLine();
                if (choice.equals("1")) {
                    writer.println("JOIN-BOARD");
                    writer.flush();
                    String msg = reader.readLine();
                    if (msg.equals("100")) {
                        System.out.println("Dostępne stoły:");
                        for (String str : tables) {
                            System.out.println(str);
                        }
                        System.out.println("Wpisz numer id stołu, aby kontynuować:");
                        choice = scanner.nextLine();
                        writer.println(choice);
                        writer.flush();
                        msg = reader.readLine();
                        if (msg.equals("200")) {
                            // OK
                        } else if (msg.equals("405")) {
                            System.out.println("Nie możesz dołączyć do tego stołu.");
                        } else if (msg.equals("404")) {
                            System.out.println("Nie znaleziono stołu");
                        }
                    } else if (msg.equals("405")) {
                        System.out.println("Nie możesz dołączyć do stołu jeżeli żaden nie istnieje.");
                    } else if (msg.equals("501")) {
                        System.out.println("Brak funkcjonalności po stronie serwera.");
                    }
                } else if (choice.equals("2")) {
                    System.out.print("Funkcja niedostępna, ");
                } else {
                    System.out.print("Niepoprawny wybór, ");
                }
            }






            for (String str : tables) {
                System.out.println(str);
            }




            System.out.println("END");








        } catch (ConnectException ce) {
            System.out.println(getCurrentDateAndTime() + "Błąd połączenia klienta");
            ce.printStackTrace();
        } catch (IOException ioe) {
            System.out.println(getCurrentDateAndTime() + "Błąd wartswy IO");
            ioe.printStackTrace();
        }
    }

    private static Set<String> readBoards() throws IOException {

        writer.println("READ-BOARDS");
        writer.flush();
        Set<String> tables = new HashSet<>();
        System.out.print(getCurrentDateAndTime() + "Pobiranie dostępnych stołów... ");
        String msg = reader.readLine();
        if (msg.equals("404")) {
            System.out.println(getCurrentDateAndTime() + "OK");
        } else if (msg.equals("200")) {
            tables.clear();
            msg = reader.readLine();
            while (!msg.equals("200")) {
                tables.add(msg);
                msg = reader.readLine();
            }
            System.out.println("OK");
        }

        return tables;
    }

    private static void login() throws IOException {

        System.out.println("Aby kontynuować wpisz login:");
        String login = scanner.nextLine();
        sendResponse("CREATE-PLAYER", login);
        HTTPStatus httpStatus = HTTPStatus.getValue(Integer.parseInt(reader.readLine()));
        if (httpStatus == HTTPStatus.CREATED) {
            player = new Player(login);
        }
        System.out.println(httpStatus.toString());
    }

    private static void sendResponse(String... responses) {

        if (responses.length > 1) {
            for (String str : responses) {
                writer.println(str);
            }
        } else {
            writer.println(responses[0]);
        }
        writer.println("END");
        writer.flush();
    }

    public static String getCurrentDateAndTime() {
        return formatter.format(new Date(System.currentTimeMillis()));
    }
}
