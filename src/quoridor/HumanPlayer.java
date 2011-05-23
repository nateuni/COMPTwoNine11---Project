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

	private static final long serialVersionUID = 1L;

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

