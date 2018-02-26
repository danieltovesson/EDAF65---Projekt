package server;

public class GameLogic {

	// Variables
	private String inputClient1;
	private String inputClient2;
	private String resClient1 = "";
	private String resClient2 = "";

	/**
	 * Creates a GameLogic object
	 * 
	 * @param inputClient1
	 *            the input of client 1
	 * @param inputClient2
	 *            the input of client 2
	 */
	public GameLogic(String inputClient1, String inputClient2) {
		this.inputClient1 = inputClient1;
		this.inputClient2 = inputClient2;
	}

	/**
	 * Controls the input-strings sent from clients in order to determine the
	 * winner. The winner is computed and strings corresponding to the correct
	 * receiver is set.
	 */
	public void checkWinner() {

		if (inputClient1.equals(inputClient2)) {

			// Takes user1's input and compares it to user2's, then checks if
			// it's a draw.
			// if it's draw - print out draw to server and binds message to
			// result-string.
			resClient1 = "draw";
			resClient2 = "draw";
			System.out.println("It's a draw.");
		} else if (inputClient1.equals("rock") && inputClient2.equals("scissor")) {
			// Takes user1's input as well as user2's. If it's rock,
			// respectively
			// scissor
			// user1 wins. It then outputs "Player one wins" to server and binds
			// message to result-string.
			resClient1 = "win";
			resClient2 = "lose";
			System.out.println("Player one wins.");
		} else if (inputClient1.equals("scissor") && inputClient2.equals("rock")) {
			// Takes user1's input as well as user2's. If it's scissor,
			// respectively rock
			// user2 wins. It then outputs "Player two wins" to server and binds
			// message to result-string.
			resClient1 = "lose";
			resClient2 = "win";
			System.out.println("Player two wins.");
		} else if (inputClient1.equals("rock") && inputClient2.equals("paper")) {
			// Takes user1's input as well as user2's. If it's rock,
			// respectively
			// paper
			// user2 wins. It then outputs "Player two wins" to server and binds
			// message to result-string.
			resClient1 = "lose";
			resClient2 = "win";
			System.out.println("Player two wins.");
		} else if (inputClient1.equals("paper") && inputClient2.equals("rock")) {
			// Takes user1's input as well as user2's. If it's paper,
			// respectively
			// rock
			// user1 wins. It then outputs "Player one wins" to server and binds
			// message to result-string.
			resClient1 = "win";
			resClient2 = "lose";
			System.out.println("Player one wins.");
		} else if (inputClient1.equals("scissor") && inputClient2.equals("paper")) {
			// Takes user1's input as well as user2's. If it's scissor,
			// respectively paper
			// user1 wins. It then outputs "Player one wins" to server and binds
			// message to result-string.
			resClient1 = "win";
			resClient2 = "lose";
			System.out.println("Player one wins.");
		} else if (inputClient1.equals("paper") && inputClient2.equals("scissor")) {
			// Takes user1's input as well as user2's. If it's rock,
			// respectively
			// scissor
			// user2 wins. It then outputs "Player two wins" to server and binds
			// message to result-string.
			resClient1 = "lose";
			resClient2 = "win";
			System.out.println("Player two wins.");
		} else {
			System.out.println("Could not calculate winner");
		}
	}

	/**
	 * Returns message to client1 if they are the winner or not.
	 */
	public String client1_Msg() {
		return resClient1;
	}

	/**
	 * Returns message to client1 if they are the winner or not.
	 */
	public String client2_Msg() {
		return resClient2;
	}
}
