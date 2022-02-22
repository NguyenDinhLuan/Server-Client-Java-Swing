package NguyenDinhLuan_51800994;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class CreateServerClient extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateServerClient frame = new CreateServerClient();
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
	public CreateServerClient() {
		setTitle("Start Server/Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuItem createserver = new JMenuItem("Start Create Server");
		createserver.setIcon(new ImageIcon(CreateServerClient.class.getResource("/NguyenDinhLuan_51800994/Start-icon.png")));
		createserver.setBackground(Color.WHITE);
		createserver.setForeground(Color.BLACK);
		createserver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatServer frame = new ChatServer();
				frame.setVisible(true);
			}
		});
		createserver.setFont(new Font("Times New Roman", Font.BOLD, 20));
		createserver.setHorizontalAlignment(SwingConstants.CENTER);
		createserver.setBounds(36, 42, 355, 74);
		contentPane.add(createserver);
		
		JMenuItem creatclient = new JMenuItem("Start Create Client");
		creatclient.setIcon(new ImageIcon(CreateServerClient.class.getResource("/NguyenDinhLuan_51800994/Start-icon.png")));
		creatclient.setBackground(Color.WHITE);
		creatclient.setForeground(Color.BLACK);
		creatclient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatClient frame = new ChatClient();
			    frame.setVisible(true);
			}
		});
		creatclient.setFont(new Font("Times New Roman", Font.BOLD, 20));
		creatclient.setHorizontalAlignment(SwingConstants.CENTER);
		creatclient.setBounds(36, 142, 355, 74);
		contentPane.add(creatclient);
		
		JLabel anh = new JLabel("");
		anh.setBackground(Color.WHITE);
		anh.setIcon(new ImageIcon(CreateServerClient.class.getResource("/NguyenDinhLuan_51800994/image.jpg")));
		anh.setBounds(0, 0, 436, 263);
		contentPane.add(anh);
	}

}
