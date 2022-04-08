package dkit.oop.BusinessObjects;

import com.google.gson.JsonSyntaxException;
import dkit.oop.DAOs.MySqlPlayerDAO;
import dkit.oop.DTOs.Player;
import dkit.oop.DTOs.Sector;
import dkit.oop.Exceptions.DAOException;

import javax.swing.text.PlainDocument;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Scanner;

public class Server
{
    public static void main(String[] args)
    {
        Server server = new Server();
        server.start();
    }

    public void start()
    {
        try
        {
            MySqlPlayerDAO IPlayerDAO = new MySqlPlayerDAO();

            ServerSocket ss = new ServerSocket(8080);

            System.out.println("Server: Server started. Listening for connections on port 8080...");

            int clientNumber = 0;

            while(true)
            {
                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection,
                // and open a new socket to communicate with the client
                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " has connected.");

                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of this server: " + socket.getLocalPort());

                Thread t = new Thread(new ClientHandler(socket, clientNumber, IPlayerDAO)); // create a new ClientHandler for the client,
                t.start();                                                  // and run it in its own thread

                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }

        }
        catch (IOException e)
        {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");
    }

    public class ClientHandler implements Runnable   // each ClientHandler communicates with one Client
    {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        MySqlPlayerDAO IPlayerDAO;
        int clientNumber;

        public ClientHandler(Socket clientSocket, int clientNumber, MySqlPlayerDAO IPlayerDAO)
        {
            try
            {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing

                this.IPlayerDAO = IPlayerDAO;

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            String message;
            Scanner keyboard = new Scanner(System.in);
            try
            {
                while ((message = socketReader.readLine()) != null)
                {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);

                    if (message.startsWith("DisplayById"))
                    {
                        String[] tokens = message.split(" ");
                        int id = Integer.parseInt(tokens[1]);
                        String playerJson = IPlayerDAO.findPlayerByIdJson(id);
                        socketWriter.println(playerJson);
                    }
                    else if (message.startsWith("DisplayAll"))
                    {
                        String playersJson = IPlayerDAO.findAllPlayersJson();
                        socketWriter.println(playersJson);
                    }
                    else if (message.startsWith("DeleteById"))
                    {
                        String[] tokens = message.split(" ");
                        int id = Integer.parseInt(tokens[1]);
                        Player player = IPlayerDAO.findPlayerById(id);

                        if (player != null)
                        {
                            IPlayerDAO.deletePlayerById(id);
                            socketWriter.println("Player with ID " + id  + " deleted");
                        }
                        else
                        {
                            socketWriter.println("Player with ID " + id  + " not found");
                        }
                    }
                    else if(message.startsWith("AddPlayer"))
                    {
                        try
                        {
                            String[] tokens = message.split(" ");
                            String name = tokens[1].replace("&", " ");
                            String nationality = tokens[2];
                            int date = Integer.parseInt(tokens[3]);
                            int month = Integer.parseInt(tokens[4]);
                            int year = Integer.parseInt(tokens[5]);
                            double height = Double.parseDouble(tokens[6]);
                            Sector playerSector = null;
                            switch (tokens[7].toUpperCase())
                            {
                                case "MS":
                                    playerSector = Sector.MENS_SINGLES;
                                    break;
                                case "MD":
                                    playerSector = Sector.MENS_DOUBLE;
                                    break;
                                case "WS":
                                    playerSector = Sector.WOMENS_SINGLE;
                                    break;
                                case "WD":
                                    playerSector = Sector.WOMENS_DOUBLE;
                                    break;
                                case "XD":
                                    playerSector = Sector.MIXED_DOUBLES;
                                    break;
                            }
                            int worldRank = Integer.parseInt(tokens[8]);

                            Player player = new Player(name, nationality, year, month, date, height, playerSector, worldRank);

                            int newPlayerId = IPlayerDAO.addPlayer(player);

                            socketWriter.println(IPlayerDAO.findPlayerByIdJson(newPlayerId));
                        }
                        catch (ArrayIndexOutOfBoundsException | NumberFormatException | JsonSyntaxException | NullPointerException e)
                        {
                            socketWriter.println("Player not added, make sure command matches the requested format!");
                        }
                    }
                    else if (message.startsWith("Exit"))
                    {
                        socketWriter.println("Exiting application, goodbye!");
                    }
                    else
                    {
                        socketWriter.println("I'm sorry I don't understand :(");
                    }
                }

                socket.close();

            } catch (IOException | DAOException ex)
            {
                ex.printStackTrace();
            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }
    }
}
