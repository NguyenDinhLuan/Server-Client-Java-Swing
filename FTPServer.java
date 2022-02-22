package NguyenDinhLuan_51800994;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.zip.InflaterInputStream;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class FTPServer extends JFrame {

	private JPanel contentPane;
	static InputStream inputStream ;
	static OutputStream outputStream;
	static ServerSocket serverSocket;
	static JLabel accept;
	static JLabel file;
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FTPServer frame = new FTPServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			// Tao mot doi tuong serverScoket
			serverSocket = new ServerSocket(1202);
			// Phuong thuc accept() chap nhan yeu cau den socket
			Socket socket = serverSocket.accept();
			// Ket noi thanh cong thi hien len
			accept.setText("Client connected");
			accept.setForeground(Color.BLACK);
			// Luong InputStream cho phep tu doc va phan tich du lieu 
			inputStream = socket.getInputStream();
			// Tra ve mot luong OutputStream tren do co the ghi du lieu de truyen toi server.
			outputStream = new FileOutputStream("test.txt");
			// Phuong thuc de tao mang byte tu luong dau vao
			byte [] b = new byte[16*1024];
			int count;
			while ((count = inputStream.read(b)) >0) {
				// Ghi file
				outputStream.write(b, 0, count);
				// Hien khi da nhan duoc file gui
				file.setText("File recieved!");
				file.setForeground(Color.BLACK);
			}
		} catch (IOException e) {}
	}

	/**
	 * Create the application.
	 */
	public FTPServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ftpserver = new JLabel("FTP Server");
		ftpserver.setForeground(Color.BLACK);
		ftpserver.setFont(new Font("Times New Roman", Font.BOLD, 50));
		ftpserver.setBounds(123, 10, 278, 98);
		contentPane.add(ftpserver);
		
		JLabel clientconnect = new JLabel("Connect from client: ");
		clientconnect.setForeground(Color.BLACK);
		clientconnect.setFont(new Font("Times New Roman", Font.BOLD, 20));
		clientconnect.setBounds(14, 118, 208, 39);
		contentPane.add(clientconnect);
		
		JLabel recievefile = new JLabel("Recieve file from client : ");
		recievefile.setForeground(Color.BLACK);
		recievefile.setFont(new Font("Times New Roman", Font.BOLD, 20));
		recievefile.setBounds(14, 167, 230, 33);
		contentPane.add(recievefile);
		JOptionPane.showMessageDialog(null, "Server is running!");
		
		accept = new JLabel("Client is not connected");
		accept.setForeground(Color.GRAY);
		accept.setFont(new Font("Times New Roman", Font.BOLD, 18));
		accept.setBounds(254, 118, 200, 39);
		contentPane.add(accept);
		
		file = new JLabel("File is not recieved");
		file.setForeground(Color.GRAY);
		file.setFont(new Font("Times New Roman", Font.BOLD, 18));
		file.setBounds(254, 167, 221, 33);
		contentPane.add(file);
	}
}
