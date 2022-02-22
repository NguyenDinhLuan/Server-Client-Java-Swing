package NguyenDinhLuan_51800994;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TinhClient extends JFrame{

	private JPanel contentPane;
	private JTextField txtLocalhost, textField_1, textField_2, textArea;
	private JButton cong,tru,nhan,chia;
	private static DataInputStream io;
	private static DataOutputStream os;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		    @Override
		    public void run() {
				try {
					TinhClient frame = new TinhClient();
				    frame.setVisible(true);
				} catch (Exception e) {
				    e.printStackTrace();
				}
		    }
		});
	}

	/**
	 * Create the frame.
	 */
	public TinhClient() {
		setTitle("Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 278, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel address = new JLabel("Server Address");
		address.setFont(new Font("Times New Roman", Font.BOLD, 15));
		address.setBounds(24, 26, 110, 21);
		contentPane.add(address);
		
		JLabel soa = new JLabel("So A:");
		soa.setForeground(Color.BLACK);
		soa.setFont(new Font("Times New Roman", Font.BOLD, 15));
		soa.setBounds(24, 75, 45, 13);
		contentPane.add(soa);
		
		JLabel sob = new JLabel("So B:");
		sob.setForeground(Color.BLACK);
		sob.setFont(new Font("Times New Roman", Font.BOLD, 15));
		sob.setBounds(24, 119, 45, 13);
		contentPane.add(sob);
		
		txtLocalhost = new JTextField();
		txtLocalhost.setText("localhost");
		txtLocalhost.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtLocalhost.setBounds(133, 28, 96, 19);
		contentPane.add(txtLocalhost);
		txtLocalhost.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.BLACK);
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_1.setBounds(85, 72, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_2.setBounds(85, 116, 96, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel ketqua = new JLabel("Ket Qua");
		ketqua.setForeground(Color.BLACK);
		ketqua.setFont(new Font("Times New Roman", Font.BOLD, 15));
		ketqua.setBounds(24, 222, 61, 31);
		contentPane.add(ketqua);
		
		JTextArea textArea = new JTextArea();
		textArea.setForeground(Color.BLACK);
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textArea.setEditable(false);
		textArea.setBounds(95, 226, 121, 27);
		contentPane.add(textArea);
		
		JButton cong = new JButton("Cong");
		cong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Lay du lieu cho soa va sob
					double soa=Double.parseDouble(textField_1.getText());
					double sob=Double.parseDouble(textField_2.getText());
					// Tao socket vs localhost và port
					Socket socket=new Socket("localhost",1202);
					// DataInputStream cho phep ung dung doc du lieu dau vao
					DataInputStream io = new DataInputStream(socket.getInputStream());
					// DataOutputStream ghi du lieu ma co the doc tu DataInputStream
					DataOutputStream os = new DataOutputStream(socket.getOutputStream());
					os.writeUTF("Cong");
					os.writeDouble(soa);
					os.writeDouble(sob);
					double ketqua = io.readDouble();
					String screenResult = String.valueOf(ketqua);
					textArea.setText(screenResult);
				}catch (IOException e1) {
					// Thong bao khi chua ket noi server
					JOptionPane.showMessageDialog(null, "Connection refused: connect");
				}catch(NumberFormatException ex) {
					// Thong bao loi tu dong
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		
		cong.setFont(new Font("Times New Roman", Font.BOLD, 15));
		cong.setForeground(Color.BLACK);
		cong.setBounds(49, 155, 85, 21);
		contentPane.add(cong);
		
		JButton tru = new JButton("Tru");
		tru.setForeground(Color.BLACK);
		tru.setFont(new Font("Times New Roman", Font.BOLD, 15));
		tru.setBounds(144, 155, 85, 21);
		contentPane.add(tru);
		tru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Lay du lieu cho soa va sob
					double soa=Double.parseDouble(textField_1.getText());
					double sob=Double.parseDouble(textField_2.getText());
					// Tao socket vs localhost và port
					Socket socket=new Socket("localhost",1202);
					// DataInputStream cho phep ung dung doc du lieu dau vao
					DataInputStream io = new DataInputStream(socket.getInputStream());
					// DataInputStream cho phep ung dung doc du lieu dau vao
					DataOutputStream os = new DataOutputStream(socket.getOutputStream());
					os.writeUTF("Tru");
					os.writeDouble(soa);
					os.writeDouble(sob);
					double ketqua = io.readDouble();
					String screenResult = String.valueOf(ketqua);
					textArea.setText(screenResult);
				}catch (IOException e1) {
					// Thong bao khi chua ket noi server
					JOptionPane.showMessageDialog(null, "Connection refused: connect");
				}catch(NumberFormatException ex) {
					// Thong bao loi tu dong
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		
		JButton nhan = new JButton("Nhan");
		nhan.setForeground(Color.BLACK);
		nhan.setFont(new Font("Times New Roman", Font.BOLD, 15));
		nhan.setBounds(49, 186, 85, 21);
		contentPane.add(nhan);
		nhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Lay du lieu cho soa va sob
					double soa=Double.parseDouble(textField_1.getText());
					double sob=Double.parseDouble(textField_2.getText());
					// Tao socket vs localhost và port
					Socket socket=new Socket("localhost",1202);
					// DataInputStream cho phep ung dung doc du lieu dau vao
					DataInputStream io = new DataInputStream(socket.getInputStream());
					// DataInputStream cho phep ung dung doc du lieu dau vao
					DataOutputStream os = new DataOutputStream(socket.getOutputStream());
					os.writeUTF("Nhan");
					os.writeDouble(soa);
					os.writeDouble(sob);
					double ketqua = io.readDouble();
					String screenResult = String.valueOf(ketqua);
					textArea.setText(screenResult);
				}catch (IOException e1) {
					// Thong bao khi chua ket noi server
					JOptionPane.showMessageDialog(null, "Connection refused: connect");
				}catch(NumberFormatException ex) {
					// Thong bao loi tu dong
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		
		JButton chia = new JButton("Chia");
		chia.setForeground(Color.BLACK);
		chia.setFont(new Font("Times New Roman", Font.BOLD, 15));
		chia.setBounds(144, 186, 85, 21);
		contentPane.add(chia);
		chia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Lay du lieu cho soa va sob
					double soa=Double.parseDouble(textField_1.getText());
					double sob=Double.parseDouble(textField_2.getText());
					// Tao socket vs localhost và port
					Socket socket=new Socket("localhost",1202);
					// DataInputStream cho phep ung dung doc du lieu dau vao
					DataInputStream io = new DataInputStream(socket.getInputStream());
					// DataInputStream cho phep ung dung doc du lieu dau vao
					DataOutputStream os = new DataOutputStream(socket.getOutputStream());
					os.writeUTF("Chia");
					os.writeDouble(soa);
					os.writeDouble(sob);
					double ketqua = io.readDouble();
					String screenResult = String.valueOf(ketqua);
					textArea.setText(screenResult);
				}catch (IOException e1) {
					// Thong bao khi chua ket noi server
					JOptionPane.showMessageDialog(null, "Connection refused: connect");
				}catch(NumberFormatException ex) {
					// Thong bao loi tu dong
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
	}
}