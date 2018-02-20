package server;

public class Mailbox {

	// Variables
	private String user1;
	private String user2;
	private String message;

	/**
	 * Sets a message
	 * 
	 * @param message
	 *            the message
	 */
	public synchronized void setMessage(String user1, String user2, String message) {
		try {
			while (this.message != null) {
				wait();
			}
			this.user1 = user1;
			this.user2 = user2;
			this.message = message;
			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets and removes the message
	 * 
	 * @return the message
	 */
	public synchronized String[] getMessage() {
		try {
			while (message == null) {
				wait();
			}
			String[] temp = new String[3];
			temp[0] = user1;
			temp[1] = user2;
			temp[2] = message;
			user1 = null;
			user2 = null;
			message = null;
			notifyAll();
			return temp;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
