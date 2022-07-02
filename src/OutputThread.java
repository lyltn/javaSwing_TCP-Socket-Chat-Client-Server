import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketAddress;

import javax.swing.JTextArea;

public class OutputThread extends Thread{
	Socket socket;
	JTextArea txt;
	BufferedReader bf;
	String sender, receiver;
	
	public OutputThread() {
		// TODO Auto-generated constructor stub
	}

	public OutputThread(Socket socket, JTextArea txtMessages, String sender, String receiver) {
		
		this.socket = socket;
		this.txt = txtMessages;
		this.sender = sender;
		this.receiver = receiver;
		
		try {
			bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (true) {
			try {
				if(socket != null) {
					String msg ="";
					if((msg = bf.readLine()) != null && msg.length() >0)
						txt.append("\n"+receiver+": "+msg);
				}
				sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
}
