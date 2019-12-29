import game.engine.Board;
import game.engine.Player;
import game.engine.tools.GameRules;
import game.engine.tools.HTTPStatus;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ILikeTrainsServer {

    private static SimpleDateFormat formatter = new SimpleDateFormat("'['yyyy-MM-dd HH:mm:ss']' ");
    private static BufferedReader reader;
    private static PrintWriter writer;
    private static Set<Player> players = new HashSet<>();
    private static Set<Board> boards = new HashSet<>();

    public static void main(String[] args) {

        String hostName = "localhost";
        int port = 8080;
        System.out.println(getCurrentDateAndTime() + "Uruchamianie serwera: hostname = " + hostName + ", port = " + port);

        try (ServerSocket serverSocket = new ServerSocket()) {

            serverSocket.bind(new InetSocketAddress(hostName, port));
            System.out.println(getCurrentDateAndTime() + "Uruchomiono, oczekiwanie na połączenia.");

            try (Socket socket = serverSocket.accept()) {

                boards.add(new Board(1L, "testBoard", 1));


                InetAddress clientAddress = socket.getInetAddress();
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

                while (socket.isConnected()) {
                    String[] message = getResponse();
                    switch (message[0]) {
                        case "CREATE-PLAYER":
                            createPlayer(message);
                            break;
                        case "READ-BOARDS":
                            System.out.println(getCurrentDateAndTime() + "[ " + clientAddress.toString().substring(1)
                                    + " ] [ READ-BOARDS ]");
                            readBoards();
                            break;
                        case "JOIN-BOARD":
                            writer.println(501);
                            writer.flush();
                            break;
                        case "111":
                            socket.shutdownOutput();
                            socket.shutdownInput();
                            break;
                        default:
                            System.out.println("Brak implementacji");
                            writer.println(501);
                            writer.flush();
                            break;
                    }
                }
                System.out.println("socked is not connected");













                String clientType = reader.readLine();
                Board board;
                switch (clientType.substring(0, 3)) {

                    case "CON":
                        System.out.println(getCurrentDateAndTime() + "Połączono z: " +
                                clientAddress.toString().substring(1) + ". Typ clienta: console, login: " +
                                clientType.substring(clientType.indexOf("-") + 1));
                        writer.println("100");
                        writer.flush();

                        while (true) {

                            writer.println("Wpisz 1 by utworzyć nowy stół lub 2 by dołączyć do już istniejącego.");
                            writer.flush();
                            String clientLine = reader.readLine();
                            System.out.println(getCurrentDateAndTime() + "[ " + clientType + " ]\n" + clientLine);

                        }
                    default:
                        writer.println("111");
                        socket.shutdownOutput();
                        socket.shutdownInput();
                        break;
                }

                String clientLine = reader.readLine();
                System.out.println("clientLine " + clientLine);
                System.out.println("AFTER SWICH");
                while (!clientLine.equals("111")) {
                    writer.println("501");
                    writer.flush();

                    clientLine = reader.readLine();
                    System.out.println("clientLine = " + clientLine);
                }
                writer.println("111");
                writer.flush();
                socket.shutdownOutput();
                socket.shutdownInput();
            }
            catch (IOException ioe) {
                System.out.println(getCurrentDateAndTime() + "Wystąpił wyjątek obsługi klienta: "
                        + ioe.getLocalizedMessage());
                ioe.printStackTrace();
            }
        }
        catch (BindException be) {
            System.out.println(getCurrentDateAndTime() + "Nie udało się zbindować serwera");
            be.printStackTrace();
        } catch (IOException ioe) {
            System.err.println(getCurrentDateAndTime() + "Błąd I/O");
            ioe.printStackTrace();
        }
    }

    private static void readBoards() {
        if (boards.size() == 0) {
            System.out.println("Brak dostępnych stolików.");
            writer.println(404);
            writer.flush();
        } else {
            System.out.print("Przesyłanie dostępnych stolików... ");
            writer.println(200);
            for (Board board : boards) {
                writer.println(board.getInfo());
            }
            writer.println(200);
            writer.flush();
            System.out.println("OK");
        }
    }

    private static void createPlayer(String[] message) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(getCurrentDateAndTime() + "[ CREATE-PLAYER ]\n");
        int playersSizeBeforeAddingPlayer = players.size();
        Player player = new Player(message[1]);
        players.add(player);
        if (players.size() > playersSizeBeforeAddingPlayer) {
            sendResponse(HTTPStatus.CREATED);
            stringBuilder.append(player.getLogin() + " " + HTTPStatus.CREATED.getMessage());
            System.out.println(stringBuilder.toString());
        } else {
            sendResponse(HTTPStatus.CONFLICT);
            stringBuilder.append(player.getLogin() + " " + HTTPStatus.CONFLICT.getMessage());
            System.out.println(stringBuilder.toString());
        }
    }

    private static void sendResponse(HTTPStatus httpStatus) {
        writer.println(httpStatus.getCode());
        writer.flush();
    }

    private static String[] getResponse() throws IOException {

        List<String> list = new ArrayList<>();
        String response = reader.readLine();
        while (!response.equals("END")) {
            list.add(response);
            response = reader.readLine();
        }
        String[] itemsArray = new String[list.size()];
        return list.toArray(itemsArray);
    }

    public static String getCurrentDateAndTime() {
        return formatter.format(new Date(System.currentTimeMillis()));
    }
}
