package NguyenDinhLuan_51800994;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TinhServer{
	public static void running() throws IOException {
		TinhServer frame = new TinhServer();
	}
	
	public TinhServer() throws IOException {
		// Tao mot doi tuong serverScoket
		ServerSocket ss = new ServerSocket(1202);
		while(true){
			// Phuong thuc accept() chap nhan yeu cau den socket
			Socket socket = ss.accept();
			// DataInputStream cho phep ung dung doc du lieu dau vao
			DataInputStream io = new DataInputStream(socket.getInputStream());
			// DataOutputStream ghi du lieu ma co the doc tu DataInputStream
			DataOutputStream os = new DataOutputStream(socket.getOutputStream());
			String pheptinh  = io.readUTF().trim();
			double soa = io.readDouble();
			double sob = io.readDouble();
			double result = 1;
			// Neu click vao button nao thi lam phep tinh do
			if(pheptinh.equals("Cong")) 
				 result= soa + sob;
			else if(pheptinh.equals("Tru")) 
				result= soa - sob;
			else if(pheptinh.equals("Nhan")) 
				result= soa * sob;
			else if(pheptinh.equals("Chia")) 
				result= soa / sob;
			os.writeDouble(result);
		}
	}
	
}