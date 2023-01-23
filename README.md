HOW TO RUN THE PROGRAM:
	- Extract the contents of the .zip file, if everything is still compressed.
	- Make sure that "One.jar," the "Assets" folder containing all the images, and the "Sounds" folder containing all the audio are all in the same directory.
	- Open/run the jar file, and the game should open and work properly.
	- If either of the aforementioned folders are missing from the directory of the jar, the game won't work correctly.
	- If any of the names of the .png or .wav files are changed, the game also won't work right.

CONTROLS:
	- Click on a card in your hand to play it
	- If you don't have a card to play, click the "deck" in the middle to draw more cards.
	- If you play a wildcard, click on one of the four colors in the wheel to choose that color.
	- Click the sort button in the lower left to sort your hand.
	- If you have more cards than the screen shows, use the cycle button in the lower left to move your cards around.
	- Close the window to exit the program.

HOW TO PLAY:
	- Cards can only be played if they match the number/symbol or color of the card on the top of the "discard pile." (the card next to the deck)
	- Wildcards and Draw 4 cards let you set their color to whatever you like
	- Skip cards, Reverse cards, Draw 2 cards, and Draw 4 cards will "skip" the opponent's turn, allowing you to play cards consecutively.
	- Draw 2 and Draw 4 cards instantly make the opponent draw cards
	- The goal is have 0 cards in your hand before the opponent does.

THE DECK:
The deck consists of the following:
	- one 0 of each color.
	- two of every number from 1 to 9 of each color.
	- two Skips, Reverses, and Draw 2s of each color
	- four Wildcards and Draw 4s of each color.	
	- 108 total cards.

THE CPU:
The cpu plays like this:
	- If it has a wildcard, it will always play it immediately, choosing whichever color it has the most of at the time.
	- Then, it will look in it's hand for a "skip card" (Skip, Reverse, Draw 2) and play it if it can.
	- Finally, it will look for any other playable card, and play that.


OTHER DETAILS:
	- If the deck runs out of cards during the game, the discard pile is reshuffled into the deck, leaving only the topmost card in the discard pile.
	- As the game goes on, a log in the upper right will fill up and show the last several cards played.
	- Click the button in the bottom left corner to sort your hand by color, and then by number.
	- If you have so many cards your hand goes off the screen, a "cycle" button will appear that lets you move your hand around.

CREDITS:
	- Although the vast majority of the code used in this game was created completely by me, I did need to look up example code for a few things.
	- Here is each class, and what I needed external help to write inside that class.
	- (this information is also written in a comment at the top of each class)

	I wrote all the code in Cpu.java, Deck.java, Main.java, Player.java, Region.java, and User.java on my own.

	Card.java:
		- For this class as well as the other card-game related classes, I referenced a previous project I made involving simulated playing cards,
    		  but I came up with the code for both this and that myself.
	
	Frame.java:
		- I knew how to make an extended JFrame class before this project, but I looked up example code for:
		- SetDefaultCloseOperation, setTitle, and setIconimage
		- I learned how to use them from the Oracle documentation on JFrame: https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html

	Listener.java:
		- I learned how to make an extended MoustListener class through the Oracle documentation: https://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseListener.html
		- I also used this video as an example: https://www.youtube.com/watch?v=bTaJKm43KGs

	Panel.java:
		- I learned about the methods in JPanel from the Oracle Documentation: https://docs.oracle.com/javase/7/docs/api/javax/swing/JPanel.html
		- I learned how to use drawImage and ImageIcon from a youtube video, but I lost the link.
		- As for the images themselves, I drew them myself in a pixel art program.

	Sound.java:
		- When Sean was showing me his final, he showed me how he got a .wav file to play in his program. I used the same methods he did, but the actual code and the way the class works with the other classes was made by me.
		- I recorded all the sound effects myself.
