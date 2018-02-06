package server;

public class Mailbox {

	// Variables
	String message;

	/**
	 * Sets a message
	 * 
	 * @param message
	 *            the message
	 */
	public synchronized void setMessage(String message) {
		try {
			while (this.message != null) {
				wait();
			}
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
	public synchronized String getMessage() {
		try {
			while (message == null) {
				wait();
			}
			String temp = message;
			message = null;
			notifyAll();
			return temp;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
