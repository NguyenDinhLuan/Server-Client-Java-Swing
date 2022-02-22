package NguyenDinhLuan_51800994;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class ChatServer extends JFrame implements Runnable {
    private JPanel contentPane;
    private JLabel enterport;
    private JTextField getPort;
    private JTabbedPane Text;
    private JButton Start;
    ServerSocket serverSocket = null;
    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		    @Override
		    public void run() {
				try {
					ChatServer frame = new ChatServer();
				    frame.setVisible(true);
				} catch (Exception e) {
				    e.printStackTrace();
				}
		    }
		});
    }
    public ChatServer() {
    	// thisServer=this hien tai
    	ChatServer thisServer = this;
		setTitle("Server");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel enterport = new JLabel("Enter Port Number of Server:");
	    enterport.setForeground(Color.WHITE);
	    enterport.setFont(new Font("Times New Roman", Font.BOLD, 17));
	    enterport.setBounds(29, 17, 225, 46);
	    contentPane.add(enterport);
	    
	    getPort = new JTextField();
	    getPort.setForeground(Color.BLACK);
	    getPort.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	    getPort.setBounds(264, 29, 100, 24);
	    getPort.setColumns(10);
	    contentPane.add(getPort);
	    
	    Text = new JTabbedPane(JTabbedPane.TOP);
	    Text.setForeground(Color.BLACK);
	    Text.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	    Text.setBorder(null);
	    Text.setBounds(10, 85, 575, 358);
	    contentPane.add(Text);
	    
	    JButton Start = new JButton("");
	    Start.setIcon(new ImageIcon(ChatServer.class.getResource("/NguyenDinhLuan_51800994/Windows-Turn-Off-icon.png")));
	    Start.setForeground(Color.BLACK);
	    Start.setBackground(Color.BLACK);
	    Start.setBorder(new LineBorder(Color.LIGHT_GRAY));
	    Start.setFont(new Font("Times New Roman", Font.BOLD, 18));
	    Start.setBounds(427, 17, 73, 46);
	    contentPane.add(Start);
	    
	    JLabel anh = new JLabel("");
	    anh.setIcon(new ImageIcon(ChatServer.class.getResource("/NguyenDinhLuan_51800994/image.jpg")));
	    anh.setBounds(0, 0, 595, 455);
	    contentPane.add(anh);
	    
	    Start.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	// port mac dinh la 1202, co the thay doi
				int port = 1202;
				try {
				// Lay port dau vao tu getPort
					port = Integer.parseInt(getPort.getText());
				} catch (Exception ex) {
					// Thong bao khi khong the ket noi duoc
					JOptionPane.showMessageDialog(contentPane,
						   "Can't start at this port, program will use the default port=2020\nDetails: " + ex,
						   "Error while read Port", JOptionPane.ERROR_MESSAGE);
				}
				try {
					// Tao mot doi tuong serverScoket
					serverSocket = new ServerSocket(port);
					// Thong bao server dang chay tren port nao
					JOptionPane.showMessageDialog(contentPane, "Server is running at port: " + port, "Started server",
						        JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					// Thong bao loi
					JOptionPane.showMessageDialog(contentPane, "Details: " + ex, "Start server error",
						       JOptionPane.ERROR_MESSAGE);
				}
				// Chay Server class hien tai de kiem tra cac luong ket noi vao server sau nay
				Thread t = new Thread(thisServer);
				t.start();
			}
		});
    }
    public void run() {
		while (true)
		    try {
		    	// Phuong thuc accept() chap nhan yeu cau den socket
			Socket socket = serverSocket.accept();
				if (socket != null) {
				    // Lay ten vua nhan tin cho server
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				    String Name = br.readLine();
				    Name = Name.substring(0, Name.indexOf(":"));
				    // Tao UI va cho no hien vao TabbedPane
				    ChatUI UI = new ChatUI(socket, "Server", Name);
				    Text.add(Name, UI);
				    UI.updateUI();
				    Thread t = new Thread(UI);
				    t.start();
				}
		    } catch (Exception ex) {ex.printStackTrace();}
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
    					// Khong duoc gui khi ko co tin nhan
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