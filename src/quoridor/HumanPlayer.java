/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quoridor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author elysewise
 */
public class HumanPlayer extends Player {

    private static final int PLACE_WALL = 1;
    private static final int MOVE_PLAYER = 0;
    private static final int INVALID_INPUT = -1;

    public HumanPlayer(int referenceNumber) {
        super(referenceNumber);
        setUpUser();
    }

    /**
     *Prompt user for player's name and set the name and token fields 
     */
    public void setUpUser() {
        System.out.print("Enter player name: ");
        this.setName(getFromUser());
        System.out.print("Enter token (max 3 chars): ");
        setToken(getFromUser());
    }

    /**
     *Prompt user for a move. Prompt again if the entered move could not be interpreted.
     *Return Move object representing the player's requested move
     */
    @Override
    public Move getMove() {
        Move move = null;
        System.out.println("Current Player: " + this.getName());
        System.out.println(">");
        System.out.print("enter move: ");
        String command = getFromUser();
        while (move == null) {
             move = handleInput(command);
        }
        return move;
    }

    private Move handleInput(String command) {
        try {
            if (checkInput(command) == MOVE_PLAYER) {
                ///
            } else if (checkInput(command) == PLACE_WALL) {
                ///
            }
        } catch (Exception e) {
            //all exceptions would be from trying to read incorrect formatting on user input
            return null;
        }
        return null;
    }

    // returns 0 for a movement move, 1 for a wall move and -1 for an invalid request.
    private int checkInput(String command) {
        if (command.substring(0, 1).equals("V") || command.substring(0, 1).equals("H")) {
            return PLACE_WALL;
        } else if (command.length() == 2) {
            return MOVE_PLAYER;
        }
        return INVALID_INPUT;
    }
    
    
    /**
	 * Read one line from user input
	 * @return the read line as a String
	 */
    protected String getFromUser() {
        try {
            while (true) {
                BufferedReader userReader =
                        new BufferedReader(new InputStreamReader(System.in));
                String fromUser = userReader.readLine();
                if (!fromUser.isEmpty()) { //never returns an empty string
                    return fromUser;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

