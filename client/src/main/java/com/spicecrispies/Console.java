package com.spicecrispies;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The main client class for this assignment. It uses the RestClient and WebClient classes to execute calls
 * to the Albums and Artists APIs. It will receive the required parameters to the respective API method,
 * perform the call, and report the result as is.
 */

public class Console {
    private static final WebClient artistClient = new WebClient();
    private static final RestClient albumClient = new RestClient();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userCommand = "";
        System.out.println("---------- Welcome to the RESTful Music client! ----------");
        do {
            try {
                userCommand = in.nextLine();
                switch(userCommand) {
                    case("help"):
                        help();
                        break;
                    case("quit"):
                        break;
                    default:
                        processCommand(userCommand);
                        break;
                }
            }
            catch (NoSuchElementException e) {
                System.out.println("No command found. Please try again.");
            }
        } while(!userCommand.equals("quit"));
        in.close();
    }

    /**
     * Provides a list of commands and arguments to the user.
     */
    public static void help() {
        System.out.println("listArtists");
        System.out.println("getArtistDetails [Nickname]");
        System.out.println("addArtist [Nickname] [First Name] [Last Name] [Bio]");
        System.out.println("updateArtist [Nickname] [First Name] [Last Name] [Bio]");
        System.out.println("deleteArtist [Nickname]");
        System.out.println("listAlbums");
        System.out.println("getAlbumDetails [ISRC]");
        System.out.println("addAlbum [ISRC] [Title] [Description] [Year] [Artist Nickname]");
        System.out.println("updateAlbum [ISRC] [Title] [Description] [Year] [Artist Nickname]");
        System.out.println("deleteAlbum [ISRC]");
        System.out.println("quit");
    }

    /**
     * Processes a line of input from the user. If it is valid, then it will call the appropriate method.
     * @param completeCommand A complete command given by the user
     */
    public static void processCommand(String completeCommand) {
        StringTokenizer commandProcessor = new StringTokenizer(completeCommand);
        String command = commandProcessor.nextToken();
        try {
            switch (command) {
                case ("listArtists"):
                    System.out.print(artistClient.listArtists());
                    break;
                case ("getArtistDetails"):
                    System.out.print(artistClient.getArtistDetails(commandProcessor.nextToken()));
                    break;
                case ("addArtist"):
                    System.out.print(artistClient.addArtist(commandProcessor.nextToken(), commandProcessor.nextToken(),
                            commandProcessor.nextToken(), commandProcessor.nextToken()));
                    break;
                case ("updateArtist"):
                    System.out.print(artistClient.updateArtist(commandProcessor.nextToken(), commandProcessor.nextToken(),
                            commandProcessor.nextToken(), commandProcessor.nextToken()));
                    break;
                case ("deleteArtist"):
                    System.out.print(artistClient.deleteArtist(commandProcessor.nextToken()));
                    break;
                case ("listAlbums"):
                    System.out.print(albumClient.listAlbums());
                    break;
                case ("getAlbumDetails"):
                    System.out.print(albumClient.getAlbumDetails(commandProcessor.nextToken()));
                    break;
                case ("addAlbum"):
                    System.out.print(albumClient.addAlbum(commandProcessor.nextToken(), commandProcessor.nextToken(),
                            commandProcessor.nextToken(), Integer.parseInt(commandProcessor.nextToken()),
                            commandProcessor.nextToken()));
                    break;
                case ("updateAlbum"):
                    System.out.print(albumClient.updateAlbum(commandProcessor.nextToken(), commandProcessor.nextToken(),
                            commandProcessor.nextToken(), Integer.parseInt(commandProcessor.nextToken()),
                            commandProcessor.nextToken()));
                    break;
                case ("deleteAlbum"):
                    System.out.print(albumClient.deleteAlbum(commandProcessor.nextToken()));
                    break;
                default:
                    System.out.println("Command not recognized. Please try again.");
                    break;
            }
        }
        catch (NoSuchElementException e) {
            System.out.println("Too few arguments. Please try again.");
        }
    }
}
