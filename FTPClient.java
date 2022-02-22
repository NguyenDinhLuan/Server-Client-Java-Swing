package NguyenDinhLuan_51800994;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FTPClient extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	static Socket socket;
	static InputStream inputStream ;
	static OutputStream outputStream;
	String str;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Tao socket vs localhost và port
					socket = new Socket("127.0.0.1", 1202);
					FTPClient frame = new FTPClient();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Connection refused: connect");
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FTPClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FTP Client");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(149, 22, 198, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("File:");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 72, 51, 21);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(67, 68, 300, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Xay dung mot JFileChooser tro toi thu muc mac dinh cua nguoi dung
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(btnNewButton);
				// Ghi duong dan vao str hien ra man hinh
				str = fileChooser.getSelectedFile().getAbsolutePath();
				textField.setText(str);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setBounds(389, 68, 107, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Send");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// File cho phep lam viec voi cac file tep
				File file = new File(str);
				// Phuong thuc de tao mang byte tu luong dau vao
				byte [] b = new byte[16*1024];
				try {
					// Luong InputStream cho phep tu doc va phan tich du lieu file
					inputStream = new FileInputStream(file);
					// Tra ve mot luong OutputStream tren do co the ghi du lieu de truyen toi server
					outputStream = socket.getOutputStream();
					int count ;
					while ((count  = inputStream.read(b))>0) {
						// Ghi file
						outputStream.write(b, 0, count);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_1.setBounds(177, 138, 90, 35);
		contentPane.add(btnNewButton_1);
	}
}
