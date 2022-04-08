package dkit.oop.BusinessObjects;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dkit.oop.DTOs.Player;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Client
{
    public static void main(String[] args)
    {
        Client client = new Client();
        client.start();
    }

    public void start()
    {
        Scanner in = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort() );

            System.out.println("Client message: The Client is running and has connected to the server");

            System.out.println("Please enter command:\n" +
                    "DisplayById id - Display player by ID (change 'id' to id number) \n" +
                    "DisplayAll - Display all players\n" +
                    "AddPlayer - Add new player to database\n" +
                    "DeleteById id - Delete player with the given ID (change 'id' to id number)\n" +
                    "Exit - Exit application\n");
            String command = in.nextLine();

            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

            socketWriter.println(command);

            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply

            Gson gsonParser = Converters.registerLocalDate(new GsonBuilder()).create();

            boolean continueLooping = true;

            while (continueLooping)
            {
                if (command.startsWith("DisplayById"))
                {
                    String playerJson = socketReader.nextLine();

                    Player player = gsonParser.fromJson(playerJson, Player.class);

                    if(player != null) {
                        System.out.println("Client message: Response from server - player found");
                        System.out.printf("%-7s%-40s%-30s%-18s%-14s%-20s%-17s\n", "ID", "Name", "Nationality", "Date of Birth", "Height", "Sector", "World Rank");
                        System.out.println("====   ====================================    ==========================    ==============    ==========    ================    ============");
                        System.out.printf("%-7s%-40s%-30s%-18s%-14s%-20s%-17s\n",
                                player.getId(),
                                player.getName(),
                                player.getNationality(),
                                player.getDateOfBirth(),
                                player.getHeight() + " m",
                                player.getSector(),
                                player.getWorldRank());
                    }
                    else {
                        System.out.println("Client message: Response from server - player not found");
                    }

                }
                else if (command.startsWith("DisplayAll"))
                {
                    String playersJson = socketReader.nextLine();

                    Type playerListType = new TypeToken<ArrayList<Player>>(){}.getType();

                    List<Player> list = gsonParser.fromJson(playersJson, playerListType);

                    System.out.println("Client message: Response from server - display all players:");

                    System.out.printf("%-40s%-30s%-18s%-14s%-20s%-17s\n", "Name", "Nationality", "Date of Birth", "Height", "Sector", "World Rank");
                    System.out.println("====================================    ==========================    ==============    ==========    ================    ============");

                    for(Player p : list) {
                        System.out.printf("%-40s%-30s%-18s%-14s%-20s%-17s\n",
                                p.getName(),
                                p.getNationality(),
                                p.getDateOfBirth(),
                                p.getHeight() + " m",
                                p.getSector(),
                                p.getWorldRank());
                    }
                }
                else if (command.startsWith("Exit"))
                {
                    continueLooping = false;
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \"" + input + "\"");
                }
                else
                {
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \"" + input + "\"");

                }

                if (!command.startsWith("Exit"))
                {
                    System.out.println("\nPlease enter command:\n" +
                            "DisplayById id - Display player by ID (change id to id number) \n" +
                            "DisplayAll - Display all players\n" +
                            "\n" +
                            "\n" +
                            "Exit - Exit application\n");
                    command = in.nextLine();
                    socketWriter.println(command);
                }

            }

            socketWriter.close();
            socketReader.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }
    }
}
