package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import com.redpacket.Packet;
import com.redpacket.User;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.awt.event.ActionEvent;

public class PacketDemo {

	private JFrame frame;
	private JTextField tf_money;
	private JTextField tf_amount;
	private JTextArea ta;
	private JRadioButton rdbtn_random;
	private JRadioButton rdbtn_equal;
	private User user;
	private Packet bao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PacketDemo window = new PacketDemo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PacketDemo() {
		initialize();
		this.dataRedirection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u7EA2\u5305\u6765\u5566");
		frame.setBounds(100, 100, 450, 327);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		tf_money = new JTextField();
		tf_money.setText("1.00");
		tf_money.setBounds(71, 21, 39, 21);
		panel.add(tf_money);
		tf_money.setColumns(10);

		JLabel label = new JLabel("\u91D1\u989D\uFF1A");
		label.setBounds(29, 24, 39, 15);
		panel.add(label);

		JLabel label_1 = new JLabel("\u6570\u91CF\uFF1A");
		label_1.setBounds(131, 24, 39, 15);
		panel.add(label_1);

		tf_amount = new JTextField();
		tf_amount.setText("1");
		tf_amount.setColumns(10);
		tf_amount.setBounds(172, 21, 39, 21);
		panel.add(tf_amount);

		rdbtn_random = new JRadioButton("\u968F\u673A");
		rdbtn_random.setSelected(true);
		rdbtn_random.setBounds(25, 48, 55, 23);
		// panel.add(rdbtn_random);

		rdbtn_equal = new JRadioButton("\u7B49\u989D");
		rdbtn_equal.setBounds(81, 48, 55, 23);
		// panel.add(rdbtn_equal);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtn_random);
		group.add(rdbtn_equal);
		panel.add(rdbtn_random);
		panel.add(rdbtn_equal);

		JButton btn_give = new JButton("\u53D1\u653E");
		btn_give.addActionListener(new GiveListener());
		btn_give.setBounds(141, 48, 70, 23);
		panel.add(btn_give);

		JButton btn_get = new JButton("\u62A2\u7EA2\u5305");
		btn_get.addActionListener(new GetListener());
		btn_get.setBounds(316, 25, 85, 47);
		panel.add(btn_get);

		ta = new JTextArea();
		ta.setBounds(29, 77, 372, 194);
		panel.add(ta);
	}
	/**
	 * console输出导向
	 * */
	private void dataRedirection() {
		OutputStream textAreaStream = new OutputStream() {
			public void write(int b) throws IOException {
				ta.append(String.valueOf((char) b));
			}

			public void write(byte b[]) throws IOException {
				ta.append(new String(b));
			}

			public void write(byte b[], int off, int len) throws IOException {
				ta.append(new String(b, off, len));
			}
		};
		PrintStream myOut = new PrintStream(textAreaStream);
		System.setOut(myOut);
		//System.setErr(myOut);
	}

	public class GiveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// 测试console输出导向
			// System.out.println("您点击了按钮");
			ta.setText("");
			double totalMoney = Double.parseDouble(tf_money.getText().trim());
			int amount = Integer.parseInt(tf_amount.getText().trim());
			bao = new Packet(totalMoney, amount);
		}
	}

	public class GetListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// 测试console输出导向
			// System.out.println("点击了按钮");
			ta.setText("");
			int m = 0;
			if (rdbtn_random.isSelected()) {
				m = 0;
			}
			if (rdbtn_equal.isSelected()) {
				m = 1;
			}
			user = new User(m, bao);
			for (int i = 0; i < 10; i++) {
				String name = i + "号同学";
				new Thread(user, name).start();
			}
		}
	}
}
