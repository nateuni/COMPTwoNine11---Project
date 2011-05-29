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
 * @author Team Stump
 */

public class HumanPlayer extends Player {

	public HumanPlayer(int referenceNumber) {
        super(referenceNumber);
        setUpUser();
    }
	
	public HumanPlayer(int referenceNumber, String name, String token) {
		super(referenceNumber);
        super.setName(name);
        super.setToken(token);
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
	 * Read one line from user input
	 * @return the read line as a String
	 */
    protected String getFromUser() {
        try {
            while (true) {
                BufferedReader userReader =
                        new BufferedReader(new InputStreamReader(System.in));
                String fromUser = userReader.readLine();
                if (fromUser.trim().length() > 0) { //never returns an empty string
                    return fromUser;
                } else {
                	System.out.println("Sorry empty strings are not valid input");
					System.out.print("Please try again: ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

