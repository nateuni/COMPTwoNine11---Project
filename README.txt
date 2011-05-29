QUORIDOR   README  SUBMISSION			30/05/2011

GROUP: STUMP
--Edward McLean   3188294
--Nathan Sturgess 3335686
--Elyse Wise      3308940

TUTOR: Tim Cerexhe
LAB: Mon 18:00-20:00 Oboe




------------------------TO RUN GAME-------------------------

To play game from console: run the file "Quoridor.class"


-----------------------TO PLAY GAME-------------------------


--GAME SETUP--


	Once running, you will be prompted with this menu:
		
		Welcome to Quoridor!
	
		Please select your choice: 
		1 - Play Human vs Human Game
		2 - Play Human vs AI Game
		3 - Play AI vs AI Game
		4 - Load previously saved game
		5 - Quit
		Enter selection:

	Enter the number which corresponds to your choice.

	
	- 1 2 3 - MAKING A NEW GAME:
 
	If you enter options 1, 2 or 3 a new quoridor board will be 
	created for you to play on with your specified combination
	of human/AI players.
	
	Human Player Setup:
	If a new human player needs to be made, you will be prompted 
	for the players name. Enter any name that you wish. 
	You will also be prompted to enter a token for the player. 
	Only the first 3 characters that you enter will be used to 
	set the token.
	
	AI Player Setup:
	If a new  AI player needs to be made, you will be asked to 
	choose the player's level of difficulty as such:

		Select difficulty level for player 2:
		1 - Easy
		2 - Medium
		3 - Hard
		
	Enter the number which corresponds to your choice. An AI 
	Player will then be constructed with a set default token 
	(X or O).


	- 4 - LOAD A PREVIOUSLY SAVED GAME:

	If you enter option 4, you will be prompted to enter the name
	of the game file which you wish to load. Enter the name 
	(excluding the .qdr file extension).
	If you enter an invalid file name, you will be prompted again 
	for a valid name.
	If you enter a valid file name, the game will be loaded and 
	game play will resume.


	- 5 - QUIT

	If selected, you will receive a message indicating that the 
	game is shutting down and the game will exit. 
	


--GAME PLAY--

	When it is a human player's turn, the player will be prompted 
	for input as such:
	
		harry's Turn
		Enter Move: 
	
	If an invalid move is entered, the move will not be executed 
	and the player will be prompted again.
	
	To move to a new space: enter the coordinates of the space you 
	wish to move to ( eg. e4). The input is not case sensitive.
	
	To place a wall: 
	Enter the coordinates of the wall (eg. e5H ) where coordinates 
	correspond to the top-left space which will be affected by 
	this wall, and V and H stand for vertical and horizontal 
	orientation, respectively.
	
	To save the current game: 
	type "save" . You will be prompted to enter a filename for 
	your game.
	
	To quit the current game: type "quit" . Any unsaved changes 
	will be lost.
	
	To unde a move: type "undo" . The last two moves that where 
	played will be undone.
	
	To redo a move: type "redo" . The last two moves that where 
	undone will be played again.
	undo/redo can be applied multiple times - provided there are 
	moves to undo/redo.



-----------------CHANGES TO REVISED DESIGN------------------

--GAME CLASSES--

	We made the Game class abstract, and made two new game 
	implementation classes which extend Game: 

	- Validator Game (for playing a game with preset moves).

	- Console Game (for playing a game which requires moves to 
	  be entered from console for human players.

--AI PLAYER CLASSES--

	We implemented the following player classes (to represent 
	different levels of difficulty in AI play). All of these 
	classes extend the abstrac Player class.

	- RandomAIPlayer - which plays random (valid) moves - 
	  used for implementation of easy AI player.

	- NoLookAIPlayer - which evaluates the optimal move to play 
	  without looking ahead - used for implementation of medium 
	  AI Player.

	- MultipleLookAIPlayer - which evaluates the optimal move to 
          play with multiple looks ahead - used for implementation 
          of har AI Player.

--DEAD PLAYER CLASSES--

	We implemented a DeadPlayer class which asks for no setup 
	routine from the console - this is used to allow the class 
	ValidatorGame to play a game without being held up by 
	prompts from the console.
	A Dead player is created with name and token to be "X" or "O"
	depending on whether it is player 1 or 2.

--FACTORY METHOD--

	We implemented a factory class which implements the following methods:
	
	- makeGame(String presetMoves)which constructs a ValidatorGame 
	  if preset moves are not null, and a console game if preset moves are
 	  null. This method ensures that the game implementation used will suit
          the requirements of retrieving and playing moves (i.e. either from 
          preset strings or from console). It also supports dynamic binding - 
          allowing any class to call makeGame() and retrieve the game object 
          that it requires.  
	
	- makeBoard(int type) which constructs a board and creates the 
	  appropriate types of players (human / AI) for the given game type. 
	  The type number corresponds to the number entered from the console 
          when creating a new game - and type 0 is used for validator games.
	
	- makeAIPlayer(int playerNumber) constructs an AI Player to suit the 
	  level of difficulty entered from the console.


-----------------PROJECT REQUIREMENTS------------------


----------------GAME SCORING FUNCTION------------------
