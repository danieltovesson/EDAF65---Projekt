
public class GameLogic {

	private String inputClient1;
	private String inputClient2;
	private String resClient1 = "";
	private String resClient2 = "";

	public GameLogic(String inputClient1, String inputClient2) {
		this.inputClient1 = inputClient1;
		this.inputClient2 = inputClient2;
	}

	public void checkWinner() {

		/**
		 * Takes user1's input and compares it to user2's, then checks if it's a draw.
		 * if it's draw - print out draw to server and binds message to result-string.
		 */
		if (inputClient1.equals(inputClient2)) {
			resClient_1 = "Draw";
			resClient_2 = "Draw";
			System.out.println("It's a draw.");
		}
		/**
		 * Takes user1's input aswell as user2's. If it's rock, respectivly scissor 
		 * user1 wins. It then outputs "Player one wins" to server and binds message to result-string.
		 */
		else if (inputClient1.equals("R") && inputClient2.equals("S")) {
			resClient1 = "You win";
			resClient2 = "You lose";
			System.out.println("Player one wins.");

		}
		/**
		 * Takes user1's input aswell as user2's. If it's scissor, respectivly rock
		 * user2 wins. It then outputs "Player two wins" to server and binds message to result-string.
		 */
		else if (inputClient1.equals("S") && inputClient2.equals("R")) {
			resClient1 = "You lose";
			resClient2 = "You win";
			System.out.println("Player two wins.");
		}
		/**
		 * Takes user1's input aswell as user2's. If it's rock, respectivly paper 
		 * user2 wins. It then outputs "Player two wins" to server and binds message to result-string.
		 */
		else if (inputClient1.equals("R") && inputClient2.equals("P")) {
			resClient1 = "You lose";
			resClient2 = "You win";
			System.out.println("Player two wins.");
		}
		/**
		 * Takes user1's input aswell as user2's. If it's paper, respectivly rock 
		 * user1 wins. It then outputs "Player one wins" to server and binds message to result-string.
		 */
		else if (inputClient1.equals("P") && inputClient2.equals("R")) {
			resClient1 = "You win";
			resClient2 = "You lose";
			System.out.println("Player one wins.");
		}
		/**
		 * Takes user1's input aswell as user2's. If it's scissor, respectivly paper 
		 * user1 wins. It then outputs "Player one wins" to server and binds message to result-string.
		 */
		else if (inputClient1.equals("S") && inputClient2.equals("P")) {
			resClient1 = "You win";
			resClient2 = "You lose";
			System.out.println("Player one wins.");
		}
		/**
		 * Takes user1's input aswell as user2's. If it's rock, respectivly scissor 
		 * user2 wins. It then outputs "Player two wins" to server and binds message to result-string.
		 */
		else if (inputClient1.equals("P") && inputClient2.equals("S")) {
			resClient1 = "You lose";
			resClient2 = "You win";
			System.out.println("Player two wins.");
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
