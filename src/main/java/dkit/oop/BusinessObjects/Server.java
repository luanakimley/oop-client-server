package dkit.oop.BusinessObjects;

import dkit.oop.DAOs.MySqlPlayerDAO;
import dkit.oop.Exceptions.DAOException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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
