package NguyenDinhLuan_51800994;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;

public class ChatClient extends JFrame {
    private JPanel contentPane, panel;
    private JLabel tenten, diachiip, portport;
    private JTextField ten, ipaddress, getport;
    private JButton connect;
    
    BufferedReader bufferedReader = null;
    DataOutputStream dataOut = null;
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		    @Override
		    public void run() {
				try {
					ChatClient frame = new ChatClient();
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
    public ChatClient() {
    	setTitle("Client");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel chatclient = new JLabel("Chat Client");
		chatclient.setForeground(Color.WHITE);
		chatclient.setBounds(247, 17, 109, 26);
		chatclient.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(chatclient);
		
		JLabel tenten = new JLabel("Name:");
	    tenten.setForeground(Color.WHITE);
	    tenten.setBounds(25, 49, 55, 39);
	    tenten.setFont(new Font("Times New Roman", Font.BOLD, 18));
	    contentPane.add(tenten);
	    
	    ten = new JTextField();
	    ten.setBounds(78, 54, 109, 31);
	    ten.setForeground(Color.BLACK);
	    ten.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	    ten.setColumns(10);
	    contentPane.add(ten);
	    
	    JLabel diachiip = new JLabel("IP:");
	    diachiip.setBounds(197, 49, 26, 39);
	    diachiip.setForeground(Color.WHITE);
	    diachiip.setFont(new Font("Times New Roman", Font.BOLD, 18));
	    contentPane.add(diachiip);
	    
	    ipaddress = new JTextField();
	    ipaddress.setBounds(226, 53, 109, 33);
	    ipaddress.setForeground(Color.BLACK);
	    ipaddress.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	    ipaddress.setColumns(10);
	    contentPane.add(ipaddress);
	    
	    JLabel portport = new JLabel("Port:");
	    portport.setForeground(Color.WHITE);
	    portport.setBounds(345, 49, 45, 39);
	    portport.setFont(new Font("Times New Roman", Font.BOLD, 18));
	    contentPane.add(portport);
	    
	    getport = new JTextField();
	    getport.setBounds(389, 50, 109, 38);
	    getport.setForeground(Color.BLACK);
	    getport.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	    getport.setColumns(10);
	    contentPane.add(getport);
	    
	    panel = new JPanel();
	    panel.setBounds(35, 98, 579, 328);
	    panel.setBackground(Color.WHITE);
	    panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
	    panel.setLayout(new GridLayout(1, 1, 1, 1));
	    contentPane.add(panel);
	    
	    JButton connect = new JButton("");
	    connect.setIcon(new ImageIcon(ChatClient.class.getResource("/NguyenDinhLuan_51800994/Windows-Turn-Off-icon.png")));
	    connect.setBounds(526, 49, 66, 39);
	    connect.setBackground(Color.BLACK);
	    connect.setForeground(Color.BLACK);
	    connect.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
	    connect.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    contentPane.add(connect);
	    
	    JLabel anh = new JLabel("");
	    anh.setIcon(new ImageIcon(ChatClient.class.getResource("/NguyenDinhLuan_51800994/image.jpg")));
	    anh.setBounds(0, 0, 644, 442);
	    contentPane.add(anh);
	    
	    connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
					// Lay ip tu getText
					String ip = ipaddress.getText();
					// Lay port tu getport
					int port = Integer.parseInt(getport.getText());
					// Lay name tu ten
					String name = ten.getText();
					// Tao socket bang ip va port
					Socket socket = new Socket(ip, port);
					// Neu dau vao name va socket khong co, thong bao loi
					if (name.isEmpty()) throw new Exception("Empty Name");
					if (socket == null) throw new Exception("Null Socket");
					// Tao UI
					panel.removeAll();
					ChatUI UI = new ChatUI(socket, name, "Server");
					panel.add(UI);
					panel.updateUI();
					// Cho UI chay de kiem tra tin nhan den va di
					Thread t = new Thread(UI);
					t.start();
					// Thong bao chay thanh cong
					JOptionPane.showMessageDialog(contentPane, "Connect success", "Connected",
					                JOptionPane.INFORMATION_MESSAGE);
			    } catch (Exception e) {
			    	//Thong bao loi
					JOptionPane.showMessageDialog(contentPane,
					"Error while connect, please check details try again!\nDetails: " + e,
					"Error while connect", JOptionPane.ERROR_MESSAGE);
			    }
			}
	    });
    }
    public class ChatUI extends JPanel implements Runnable {
        private JTextArea Text;
        private JButton send;
        private JTextArea Message;
        Socket socket = null;
        String sender;
        String receiver;
        BufferedReader bufferedReader = null;
        DataOutputStream dataOut = null;
        
        public void run() {
    		while (true) {
    		    try {
    				if (socket != null) {
    				    String msg = "";
    				    while ((msg = bufferedReader.readLine()) != null) {
    					// Co tin nhan den thi them vao
    					Message.append(msg + "\n");
    				    }
    				}
    		    } catch (Exception e) {}
    		}
        }
        public ChatUI(Socket s, String sender, String receiver) {
        	Text = new JTextArea();
    	    Text.setForeground(Color.BLACK);
    	    Text.setBounds(10, 286, 434, 35);
    	    Text.setFont(new Font("Times New Roman", Font.PLAIN, 15));
    	    add(Text);
    	    
    	    Message = new JTextArea();
    	    Message.setForeground(Color.BLACK);
    	    Message.setEditable(false);
    	    Message.setBounds(10, 11, 549, 257);
    	    Message.setFont(new Font("Times New Roman", Font.PLAIN, 15));
    	    add(Message);
    	    JScrollPane scrollPane = new JScrollPane(Message);
    		scrollPane.setBounds(10, 11, 549, 257);
    		add(scrollPane);
    	    
    	    JButton send = new JButton("Send");
    	    send.setBounds(454, 284, 104, 42);
    	    send.setBackground(Color.LIGHT_GRAY);
    	    send.setForeground(Color.BLACK);
    	    send.setFont(new Font("Times New Roman", Font.BOLD, 20));
    	    add(send);
    	    send.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent arg0) {
    			    if (Text.getText().isEmpty()) return;
    			    try {
    			    	dataOut.writeBytes(sender + ": " + Text.getText() + "\n");
    			    	dataOut.flush();
    					Message.append(sender + ": " + Text.getText() + "\n");
    					Text.setText("");
    			    } catch (Exception e) {
    			    	Text.setText("Error while sendding messeger");
    			    }
    			}
    	    });
    		socket = s;
    		this.sender = sender;
    		this.receiver = receiver;
    		try {
    			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    		    dataOut = new DataOutputStream(socket.getOutputStream());
    		    setLayout(null);
    		    (new Thread(this)).start();
    		} catch (Exception e) {
    			Text.setText("Error while create Main Panel");
    		}
        }    
    }
}