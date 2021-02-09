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
    private static WebClient artistClient = new WebClient();
    private static RestClient albumClient = new RestClient();

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
        System.out.println("getAlbumInfo [ISRC]");
        System.out.println("addAlbum [ISRC] [Title] [Artist Nickname] [Year] [Description]");
        System.out.println("updateAlbumInfo [ISRC] [Title] [Artist Nickname] [Year] [Description]");
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
                case ("listAlbums"):
                    System.out.println(albumClient.listAlbums());
                    break;
                case ("getAlbumDetails"):
                    System.out.println("getAlbumDetails called");
                    break;
                case ("addAlbum"):
                    System.out.println(albumClient.addAlbum(commandProcessor.nextToken(), commandProcessor.nextToken(),
                            commandProcessor.nextToken(), Integer.parseInt(commandProcessor.nextToken()),
                            commandProcessor.nextToken()));
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
