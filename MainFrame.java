package NguyenDinhLuan_51800994;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		TinhServer.running();
	}

	/**
	 * C......reate the frame.
	 */
	public MainFrame() {
		setTitle("MainFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuItem Chat = new JMenuItem("");
		Chat.setBackground(Color.WHITE);
		Chat.setHorizontalAlignment(SwingConstants.CENTER);
		Chat.setIcon(new ImageIcon(MainFrame.class.getResource("/NguyenDinhLuan_51800994/Chat-6-icon.png")));
		Chat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateServerClient frame = new CreateServerClient();
				frame.setVisible(true);
				dispose();
			}
		});
		Chat.setBounds(55, 61, 134, 129);
		contentPane.add(Chat);
		
		JMenuItem calculator = new JMenuItem("");
		calculator.setIcon(new ImageIcon(MainFrame.class.getResource("/NguyenDinhLuan_51800994/Calculator-icon.png")));
		calculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TinhClient frame = new TinhClient();
			    frame.setVisible(true);
			    dispose();
				JOptionPane.showMessageDialog(null, "Server Calculator is running!");
			}
		});
		calculator.setBounds(278, 52, 134, 138);
		contentPane.add(calculator);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/NguyenDinhLuan_51800994/image.jpg")));
		lblNewLabel.setBounds(0, 0, 494, 287);
		contentPane.add(lblNewLabel);
	}
}
