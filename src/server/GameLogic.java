
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

		if (inputClient1.equals(inputClient2)) {
			resClient_1 = "Draw";
			resClient_2 = "Draw";
			System.out.println("It's a draw.");
		}

		else if (inputClient1.equals("R") && inputClient2.equals("S")) {
			resClient1 = "You win";
			resClient2 = "You lose";
			System.out.println("Player one wins.");

		}

		else if (inputClient1.equals("S") && inputClient2.equals("R")) {
			resClient1 = "You lose";
			resClient2 = "You win";
			System.out.println("Player two wins.");
		}

		else if (inputClient1.equals("R") && inputClient2.equals("P")) {
			resClient1 = "You lose";
			resClient2 = "You win";
			System.out.println("Player two wins.");
		}

		else if (inputClient1.equals("P") && inputClient2.equals("R")) {
			resClient1 = "You win";
			resClient2 = "You lose";
			System.out.println("Player one wins.");
		}

		else if (inputClient1.equals("S") && inputClient2.equals("P")) {
			resClient1 = "You win";
			resClient2 = "You lose";
			System.out.println("Player one wins.");
		}

		else if (inputClient1.equals("P") && inputClient2.equals("S")) {
			resClient1 = "You lose";
			resClient2 = "You win";
			System.out.println("Player two wins.");
		}

	}

	public String client1_Msg() {
		return resClient1;
	}

	public String client2_Msg() {
		return resClient2;
	}

}
