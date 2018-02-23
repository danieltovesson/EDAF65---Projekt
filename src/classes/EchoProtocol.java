package classes;

public class EchoProtocol {

	/**
	 * Process the input
	 * 
	 * @param input
	 *            the input to process
	 * @return the processed input
	 */
	public String processInput(String input) {
		if (input.startsWith("newClient ")) {
			input = input.substring(10);
		} else if (input.startsWith("connectTo ")) {
			input = input.substring(10);
		} else if (input.startsWith("disconnectFrom ")) {
			input = input.substring(15);
		} else if (input.startsWith("list ")) {
			input = input.substring(5);
		} else if (input.startsWith("msg ")) {
			input = input.substring(4);
		}
		return input;
	}
}
