package com.hand.test.TestExam2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;


public class Exam2 extends Thread {

	public static void main(String[] args) {
		new Exam2().start();
	}

	@Override
	public void run() {

		try {

			ServerSocket serverSocket = new ServerSocket(12345);
			while (true) {
				Socket socket = serverSocket.accept();
				JOptionPane.showMessageDialog(null, "链接12345端口");
				
				
				File file = new File("new.pdf");
				InputStream is = new FileInputStream(file);

				
				
				
				childSocket cs = new childSocket(socket);
				cs.setinput(is);
				cs.start();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class  childSocket extends Thread {
	Socket socket;
	InputStream is;
		
	public void setinput(InputStream is){
		this.is = is;
	}

	public childSocket(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		BufferedInputStream bis = new BufferedInputStream(is);
		File file = new File("test.pdf");
		try {
			
			
			
			OutputStream os = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(os);
//			int line;
			byte input[] = new byte[100];
			while(bis.read(input)>0){
				bos.write(input);
			}
			bos.flush();
			
			bos.close();
			os.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
