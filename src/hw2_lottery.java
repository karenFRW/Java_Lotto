import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Rectangle;
import javax.swing.JScrollBar;


public class hw2_lottery extends JFrame {

	private JPanel contentPane;
	private JPanel pnP;
	private JButton btnRnd;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btnS;
	
	JButton[] buttons= new JButton[49];
	String btnName;
	ArrayList<Integer> numsList= new ArrayList<Integer>();  //list for all numbers
	
	//for win
	Set<Integer> open = new HashSet<Integer>();
	List<Integer> openSortList = new ArrayList<Integer>(open); 
	ArrayList<Integer> openTemp = new ArrayList<Integer>();
	Integer int7th;
	byte hit=0;
	byte hitS=0;
	long count = 0L;
	long money =0L;
	String msg="";
	String mainMsg="";
	final long prize = 498477230;	//預設獎金一億;頭獎至肆獎之實際中獎金額係由總獎金扣除伍獎至普獎後之餘額
	long wonPrize=0L;

	//for selected
	TreeSet<Integer> selectedNums = new TreeSet<Integer>(); 
	List<Integer> selList = new ArrayList<Integer>();
	
	//for random
	ArrayList<Integer> nums = new ArrayList<Integer>();		
	int r;
	Set<Integer> set = new HashSet<Integer>();
	List<Integer> sortedList = new ArrayList<Integer>(set); 
	
	//for sets
	List<Integer> allSetsList = new ArrayList<Integer>();	
	int listCount;
	String listElm;
	String delBrackets;
	String[] afSplitListElm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hw2_lottery frame = new hw2_lottery();
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
	public hw2_lottery() {
		//Main//
		super("You Are The Next Billionaire");
		setIconImage(Toolkit.getDefaultToolkit().getImage(hw2_lottery.class.getResource("/resources/money.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);		//(x,y,width,height)
		contentPane = new JPanel();
		
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);		
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(10, 40, 500, 160);
		contentPane.add(panel);
		
		//JPanel pnP for all buttons//
		pnP = new JPanel();
		panel.add(pnP);
		pnP.setBackground(new Color(0, 0, 51));
		pnP.setLayout(new GridLayout(5,10));
				
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.BLACK);
		panel_1.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		panel_1.setBounds(10, 10, 500, 30);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 4, 5, 0));
		
		JLabel lbl1 = new JLabel("Color depends:");
		lbl1.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl1.setForeground(Color.BLACK);
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_1.add(lbl1);
		
		JLabel lbl2 = new JLabel("Choosen");
		lbl2.setForeground(Color.WHITE);
		lbl2.setOpaque(true);
		lbl2.setBackground(Color.pink);
		lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl2.setFont(new Font("Consolas", Font.BOLD, 20));
		panel_1.add(lbl2);
		
		JLabel lbl3 = new JLabel("Random");
		lbl3.setForeground(Color.WHITE);
		lbl3.setOpaque(true);
		lbl3.setBackground(new Color(102, 205, 170));
		lbl3.setFont(new Font("Consolas", Font.BOLD, 20));
		lbl3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lbl3);
		
		JLabel lbl4 = new JLabel("Result");
		lbl4.setForeground(Color.MAGENTA);
		lbl4.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl4.setOpaque(true);
		lbl4.setBackground(Color.BLACK);
		lbl4.setFont(new Font("Consolas", Font.BOLD, 20));
		lbl4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lbl4);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 323, 270, 228);
		contentPane.add(scrollPane_1);
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		JList list = new JList();
		list.setFont(new Font("Consolas", Font.PLAIN, 18));
		list.setModel(listModel);
		scrollPane_1.setViewportView(list);
		
		JLabel lblNewLabel = new JLabel("YOUR CHANCE");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 298, 270, 20);
		contentPane.add(lblNewLabel);
		

		JButton btn1 = new JButton("");
		btn1.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn1.setFont(new Font("Consolas", Font.BOLD, 20));
		btn1.setBackground(new Color(65, 105, 225));
		btn1.setBounds(10, 200, 60, 60);
		contentPane.add(btn1);
		
		btn2 = new JButton("");
		btn2.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn2.setFont(new Font("Consolas", Font.PLAIN, 24));
		btn2.setBackground(new Color(65, 105, 225));
		btn2.setBounds(80, 200, 60, 60);
		contentPane.add(btn2);
		
		btn3 = new JButton("");
		btn3.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn3.setFont(new Font("Consolas", Font.PLAIN, 24));
		btn3.setBackground(new Color(65, 105, 225));
		btn3.setBounds(150, 200, 60, 60);
		contentPane.add(btn3);
		
		btn4 = new JButton("");
		btn4.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn4.setFont(new Font("Consolas", Font.PLAIN, 24));
		btn4.setBackground(new Color(65, 105, 225));
		btn4.setBounds(220, 200, 60, 60);
		contentPane.add(btn4);
		
		btn5 = new JButton("");
		btn5.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn5.setFont(new Font("Consolas", Font.PLAIN, 24));
		btn5.setBackground(new Color(65, 105, 225));
		btn5.setBounds(290, 200, 60, 60);
		contentPane.add(btn5);
		
		btn6 = new JButton("");
		btn6.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn6.setFont(new Font("Consolas", Font.PLAIN, 24));
		btn6.setBackground(new Color(65, 105, 225));
		btn6.setBounds(360, 200, 60, 60);
		contentPane.add(btn6);
		
		btnS = new JButton("");
		btnS.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnS.setFont(new Font("Co24olas", Font.PLAIN, 24));
		btnS.setBackground(new Color(102, 255, 153));
		btnS.setBounds(450, 200, 60, 60);
		contentPane.add(btnS);			
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 204));
		scrollPane.setBounds(10, 591, 270, -257);
		contentPane.add(scrollPane);		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(440, 323, 420, 228);
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(8, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.GRAY, 1, true));
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 8, 5, 1));
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Consolas", Font.BOLD, 14));
		label.setOpaque(true);
		label.setBackground(Color.ORANGE);
		panel_4.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setFont(new Font("Consolas", Font.BOLD, 14));
		label_1.setOpaque(true);
		label_1.setBackground(Color.ORANGE);
		panel_4.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setFont(new Font("Consolas", Font.BOLD, 14));
		label_2.setOpaque(true);
		label_2.setBackground(Color.ORANGE);
		panel_4.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setOpaque(true);
		label_3.setBackground(Color.WHITE);
		label_3.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_4.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setOpaque(true);
		label_4.setBackground(Color.WHITE);
		label_4.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_4.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setOpaque(true);
		label_5.setBackground(Color.WHITE);
		label_5.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_4.add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setOpaque(true);
		label_6.setBackground(Color.WHITE);
		label_6.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_4.add(label_6);
		
		JLabel lblOrg = new JLabel("$400");
		lblOrg.setHorizontalTextPosition(SwingConstants.CENTER);
		lblOrg.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrg.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_4.add(lblOrg);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.GRAY, 1, true));
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 8, 5, 1));
		
		JLabel lbl1_1 = new JLabel("");
		lbl1_1.setBounds(new Rectangle(0, 0, 20, 20));
		lbl1_1.setOpaque(true);
		lbl1_1.setBackground(Color.ORANGE);
		lbl1_1.setFont(new Font("Consolas", Font.BOLD, 14));
		lbl1_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(lbl1_1);
		
		JLabel lbl1_2 = new JLabel("");
		lbl1_2.setBounds(new Rectangle(0, 0, 20, 20));
		lbl1_2.setOpaque(true);
		lbl1_2.setBackground(Color.ORANGE);
		lbl1_2.setFont(new Font("Consolas", Font.BOLD, 14));
		lbl1_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(lbl1_2);
		
		JLabel lbl1_3 = new JLabel("");
		lbl1_3.setOpaque(true);
		lbl1_3.setBackground(Color.WHITE);
		lbl1_3.setBounds(new Rectangle(0, 0, 20, 20));
		lbl1_3.setFont(new Font("Consolas", Font.BOLD, 14));
		lbl1_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(lbl1_3);
		
		JLabel lbl1_4 = new JLabel("");
		lbl1_4.setOpaque(true);
		lbl1_4.setBackground(Color.WHITE);
		lbl1_4.setBounds(new Rectangle(0, 0, 20, 20));
		lbl1_4.setFont(new Font("Consolas", Font.BOLD, 14));
		lbl1_4.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(lbl1_4);
		
		JLabel lbl1_5 = new JLabel("");
		lbl1_5.setOpaque(true);
		lbl1_5.setBackground(Color.WHITE);
		lbl1_5.setBounds(new Rectangle(0, 0, 20, 20));
		lbl1_5.setFont(new Font("Consolas", Font.BOLD, 14));
		lbl1_5.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(lbl1_5);
		
		JLabel lbl1_6 = new JLabel("");
		lbl1_6.setOpaque(true);
		lbl1_6.setBackground(Color.WHITE);
		lbl1_6.setFont(new Font("Consolas", Font.BOLD, 14));
		lbl1_6.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(lbl1_6);
		
		JLabel lbl1_7 = new JLabel("");
		lbl1_7.setBackground(Color.RED);
		lbl1_7.setOpaque(true);
		lbl1_7.setBounds(new Rectangle(0, 0, 20, 20));
		lbl1_7.setFont(new Font("Consolas", Font.BOLD, 14));
		lbl1_7.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(lbl1_7);
		
		JLabel lblPrize7 = new JLabel("$400");
		lblPrize7.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrize7.setFont(new Font("Consolas", Font.BOLD, 14));
		lblPrize7.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(lblPrize7);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(Color.GRAY, 1, true));
		panel_2.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 8, 5, 1));
		
		JLabel label_8 = new JLabel("");
		label_8.setBackground(Color.ORANGE);
		label_8.setFont(new Font("Consolas", Font.BOLD, 14));
		label_8.setOpaque(true);
		panel_5.add(label_8);
		
		JLabel label_9 = new JLabel("");
		label_9.setBackground(Color.ORANGE);
		label_9.setFont(new Font("Consolas", Font.BOLD, 14));
		label_9.setOpaque(true);
		panel_5.add(label_9);
		
		JLabel label_10 = new JLabel("");
		label_10.setBackground(Color.ORANGE);
		label_10.setFont(new Font("Consolas", Font.BOLD, 14));
		label_10.setOpaque(true);
		panel_5.add(label_10);
		
		JLabel label_11 = new JLabel("");
		label_11.setOpaque(true);
		label_11.setBackground(Color.WHITE);
		label_11.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_5.add(label_11);
		
		JLabel label_12 = new JLabel("");
		label_12.setOpaque(true);
		label_12.setBackground(Color.WHITE);
		label_12.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_5.add(label_12);
		
		JLabel label_13 = new JLabel("");
		label_13.setOpaque(true);
		label_13.setBackground(Color.WHITE);
		label_13.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_5.add(label_13);
		
		JLabel label_14 = new JLabel("");
		label_14.setBackground(Color.RED);
		label_14.setFont(new Font("Consolas", Font.BOLD, 14));
		label_14.setOpaque(true);
		panel_5.add(label_14);
		
		JLabel lblPrize6 = new JLabel("$1000");
		lblPrize6.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPrize6.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrize6.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_5.add(lblPrize6);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(Color.GRAY, 1, true));
		panel_2.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 8, 5, 1));
		
		JLabel label_16 = new JLabel("");
		label_16.setBackground(Color.ORANGE);
		label_16.setFont(new Font("Consolas", Font.BOLD, 14));
		label_16.setOpaque(true);
		panel_6.add(label_16);
		
		JLabel label_17 = new JLabel("");
		label_17.setBackground(Color.ORANGE);
		label_17.setFont(new Font("Consolas", Font.BOLD, 14));
		label_17.setOpaque(true);
		panel_6.add(label_17);
		
		JLabel label_18 = new JLabel("");
		label_18.setBackground(Color.ORANGE);
		label_18.setFont(new Font("Consolas", Font.BOLD, 14));
		label_18.setOpaque(true);
		panel_6.add(label_18);
		
		JLabel label_19 = new JLabel("");
		label_19.setBackground(Color.ORANGE);
		label_19.setFont(new Font("Consolas", Font.BOLD, 14));
		label_19.setOpaque(true);
		panel_6.add(label_19);
		
		JLabel label_20 = new JLabel("");
		label_20.setOpaque(true);
		label_20.setBackground(Color.WHITE);
		label_20.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_6.add(label_20);
		
		JLabel label_21 = new JLabel("");
		label_21.setOpaque(true);
		label_21.setBackground(Color.WHITE);
		label_21.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_6.add(label_21);
		
		JLabel label_22 = new JLabel("");
		label_22.setOpaque(true);
		label_22.setBackground(Color.WHITE);
		label_22.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_6.add(label_22);
		
		JLabel lblPrize5 = new JLabel("$2000");
		lblPrize5.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPrize5.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrize5.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_6.add(lblPrize5);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(Color.GRAY, 1, true));
		panel_2.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 8, 5, 1));
		
		JLabel label_24 = new JLabel("");
		label_24.setBackground(Color.ORANGE);
		label_24.setFont(new Font("Consolas", Font.BOLD, 14));
		label_24.setOpaque(true);
		panel_7.add(label_24);
		
		JLabel label_25 = new JLabel("");
		label_25.setBackground(Color.ORANGE);
		label_25.setFont(new Font("Consolas", Font.BOLD, 14));
		label_25.setOpaque(true);
		panel_7.add(label_25);
		
		JLabel label_26 = new JLabel("");
		label_26.setBackground(Color.ORANGE);
		label_26.setFont(new Font("Consolas", Font.BOLD, 14));
		label_26.setOpaque(true);
		panel_7.add(label_26);
		
		JLabel label_27 = new JLabel("");
		label_27.setBackground(Color.ORANGE);
		label_27.setFont(new Font("Consolas", Font.BOLD, 14));
		label_27.setOpaque(true);
		panel_7.add(label_27);
		
		JLabel label_28 = new JLabel("");
		label_28.setOpaque(true);
		label_28.setBackground(Color.WHITE);
		label_28.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_7.add(label_28);
		
		JLabel label_29 = new JLabel("");
		label_29.setOpaque(true);
		label_29.setBackground(Color.WHITE);
		label_29.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_7.add(label_29);
		
		JLabel label_30 = new JLabel("");
		label_30.setBackground(Color.RED);
		label_30.setFont(new Font("Consolas", Font.BOLD, 14));
		label_30.setOpaque(true);
		panel_7.add(label_30);
		
		JLabel lblPrize4 = new JLabel("4.5%");
		lblPrize4.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPrize4.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrize4.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_7.add(lblPrize4);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(Color.GRAY, 1, true));
		panel_2.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 8, 5, 1));
		
		JLabel label_32 = new JLabel("");
		label_32.setBackground(Color.ORANGE);
		label_32.setFont(new Font("Consolas", Font.BOLD, 14));
		label_32.setOpaque(true);
		panel_8.add(label_32);
		
		JLabel label_33 = new JLabel("");
		label_33.setBackground(Color.ORANGE);
		label_33.setFont(new Font("Consolas", Font.BOLD, 14));
		label_33.setOpaque(true);
		panel_8.add(label_33);
		
		JLabel label_34 = new JLabel("");
		label_34.setBackground(Color.ORANGE);
		label_34.setFont(new Font("Consolas", Font.BOLD, 14));
		label_34.setOpaque(true);
		panel_8.add(label_34);
		
		JLabel label_35 = new JLabel("");
		label_35.setBackground(Color.ORANGE);
		label_35.setFont(new Font("Consolas", Font.BOLD, 14));
		label_35.setOpaque(true);
		panel_8.add(label_35);
		
		JLabel label_36 = new JLabel("");
		label_36.setBackground(Color.ORANGE);
		label_36.setFont(new Font("Consolas", Font.BOLD, 14));
		label_36.setOpaque(true);
		panel_8.add(label_36);
		
		JLabel label_37 = new JLabel("");
		label_37.setOpaque(true);
		label_37.setBackground(Color.WHITE);
		label_37.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_8.add(label_37);
		
		JLabel label_38 = new JLabel("");
		label_38.setOpaque(true);
		label_38.setBackground(Color.WHITE);
		label_38.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_8.add(label_38);
		
		JLabel lblPrize3 = new JLabel("7 %");
		lblPrize3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPrize3.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrize3.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_8.add(lblPrize3);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(Color.GRAY, 1, true));
		panel_2.add(panel_9);
		panel_9.setLayout(new GridLayout(1, 8, 5, 1));
		
		JLabel label_40 = new JLabel("");
		label_40.setFont(new Font("Consolas", Font.BOLD, 14));
		label_40.setBackground(Color.ORANGE);
		label_40.setOpaque(true);
		panel_9.add(label_40);
		
		JLabel label_41 = new JLabel("");
		label_41.setFont(new Font("Consolas", Font.BOLD, 14));
		label_41.setBackground(Color.ORANGE);
		label_41.setOpaque(true);
		panel_9.add(label_41);
		
		JLabel label_42 = new JLabel("");
		label_42.setFont(new Font("Consolas", Font.BOLD, 14));
		label_42.setBackground(Color.ORANGE);
		label_42.setOpaque(true);
		panel_9.add(label_42);
		
		JLabel label_43 = new JLabel("");
		label_43.setFont(new Font("Consolas", Font.BOLD, 14));
		label_43.setBackground(Color.ORANGE);
		label_43.setOpaque(true);
		panel_9.add(label_43);
		
		JLabel label_44 = new JLabel("");
		label_44.setFont(new Font("Consolas", Font.BOLD, 14));
		label_44.setBackground(Color.ORANGE);
		label_44.setOpaque(true);
		panel_9.add(label_44);
		
		JLabel label_45 = new JLabel("");
		label_45.setOpaque(true);
		label_45.setBackground(Color.WHITE);
		label_45.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_9.add(label_45);
		
		JLabel label_46 = new JLabel("");
		label_46.setFont(new Font("Consolas", Font.BOLD, 14));
		label_46.setBackground(Color.RED);
		label_46.setOpaque(true);
		panel_9.add(label_46);
		
		JLabel lblPrize2 = new JLabel("6.5%");
		lblPrize2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPrize2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrize2.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_9.add(lblPrize2);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(Color.GRAY, 1, true));
		panel_2.add(panel_10);
		panel_10.setLayout(new GridLayout(1, 8, 5, 1));
		
		JLabel label_48 = new JLabel("");
		label_48.setFont(new Font("Consolas", Font.BOLD, 14));
		label_48.setBackground(Color.ORANGE);
		label_48.setOpaque(true);
		panel_10.add(label_48);
		
		JLabel label_49 = new JLabel("");
		label_49.setFont(new Font("Consolas", Font.BOLD, 14));
		label_49.setBackground(Color.ORANGE);
		label_49.setOpaque(true);
		panel_10.add(label_49);
		
		JLabel label_50 = new JLabel("");
		label_50.setFont(new Font("Consolas", Font.BOLD, 14));
		label_50.setBackground(Color.ORANGE);
		label_50.setOpaque(true);
		panel_10.add(label_50);
		
		JLabel label_51 = new JLabel("");
		label_51.setFont(new Font("Consolas", Font.BOLD, 14));
		label_51.setBackground(Color.ORANGE);
		label_51.setOpaque(true);
		panel_10.add(label_51);
		
		JLabel label_52 = new JLabel("");
		label_52.setFont(new Font("Consolas", Font.BOLD, 14));
		label_52.setBackground(Color.ORANGE);
		label_52.setOpaque(true);
		panel_10.add(label_52);
		
		JLabel label_53 = new JLabel("");
		label_53.setFont(new Font("Consolas", Font.BOLD, 14));
		label_53.setBackground(Color.ORANGE);
		label_53.setOpaque(true);
		panel_10.add(label_53);
		
		JLabel label_54 = new JLabel("");
		label_54.setOpaque(true);
		label_54.setBackground(Color.WHITE);
		label_54.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_10.add(label_54);
		
		JLabel lblPrize1 = new JLabel("82%");
		lblPrize1.setToolTipText("");
		lblPrize1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPrize1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrize1.setFont(new Font("Consolas", Font.BOLD, 14));
		panel_10.add(lblPrize1);
		
		JLabel lblMainBalls = new JLabel("Main Numbers");
		lblMainBalls.setBorder(new LineBorder(Color.GRAY, 1, true));
		lblMainBalls.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainBalls.setForeground(Color.WHITE);
		lblMainBalls.setFont(new Font("Consolas", Font.BOLD, 15));
		lblMainBalls.setBounds(440, 300, 300, 20);
		contentPane.add(lblMainBalls);
		
		JLabel lblSpecial = new JLabel("Special");
		lblSpecial.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpecial.setForeground(Color.WHITE);
		lblSpecial.setFont(new Font("Consolas", Font.BOLD, 15));
		lblSpecial.setBorder(new LineBorder(Color.GRAY, 1, true));
		lblSpecial.setBounds(740, 300, 60, 20);
		contentPane.add(lblSpecial);
		
		JLabel lblJackpot = new JLabel("Jackpot");
		lblJackpot.setHorizontalAlignment(SwingConstants.CENTER);
		lblJackpot.setForeground(Color.WHITE);
		lblJackpot.setFont(new Font("Consolas", Font.BOLD, 15));
		lblJackpot.setBorder(new LineBorder(Color.GRAY, 1, true));
		lblJackpot.setBounds(800, 300, 60, 20);
		contentPane.add(lblJackpot);			
		
		JPanel panel_11 = new JPanel();
		panel_11.setBounds(440, 560, 420, 40);
		contentPane.add(panel_11);
		panel_11.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Prize");
		lblNewLabel_1.setIconTextGap(2);
		lblNewLabel_1.setIcon(new ImageIcon(hw2_lottery.class.getResource("/resources/money.png")));
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(5, 5, 100, 30);
		panel_11.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setText("$" + String.format("%,d", prize));
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 20));
		lblNewLabel_2.setBounds(105, 5, 310, 30);
		panel_11.add(lblNewLabel_2);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new LineBorder(Color.GRAY, 1, true));
		panel_12.setOpaque(false);
		panel_12.setBounds(290, 323, 140, 228);
		contentPane.add(panel_12);
		panel_12.setLayout(new GridLayout(4, 1, 5, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Tickets");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Consolas", Font.BOLD, 20));
		panel_12.add(lblNewLabel_3);
		
		JLabel lblCount = new JLabel("");
		lblCount.setOpaque(true);
		lblCount.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblCount.setForeground(Color.BLACK);
		lblCount.setFont(new Font("Consolas", Font.BOLD, 20));
		panel_12.add(lblCount);
		
		JLabel lblNewLabel_5 = new JLabel("Spent");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Consolas", Font.BOLD, 20));
		panel_12.add(lblNewLabel_5);
		
		JLabel lblMoney = new JLabel("");
		lblMoney.setOpaque(true);
		lblMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMoney.setForeground(Color.BLACK);
		lblMoney.setFont(new Font("Consolas", Font.BOLD, 20));
		panel_12.add(lblMoney);
		
		JLabel lblWon = new JLabel("Won");
		lblWon.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblWon.setIcon(new ImageIcon(hw2_lottery.class.getResource("/resources/star.png")));
		lblWon.setHorizontalAlignment(SwingConstants.CENTER);
		lblWon.setForeground(Color.WHITE);
		lblWon.setFont(new Font("Consolas", Font.BOLD, 20));
		lblWon.setAlignmentX(0.5f);
		lblWon.setBounds(10, 560, 130, 40);
		contentPane.add(lblWon);
		
		JLabel lblGet = new JLabel("");
		lblGet.setOpaque(true);
		lblGet.setHorizontalTextPosition(SwingConstants.CENTER);
		lblGet.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGet.setFont(new Font("Consolas", Font.BOLD, 20));
		lblGet.setBounds(130, 561, 150, 40);
		contentPane.add(lblGet);
		
		//Create all lotto's number (1-49) and addActionListener into JPanel pnP//	
		for(int i =0; i<49;i++) {
			btnName = "btn" +String.valueOf(i+1);
			buttons[i] = new JButton(btnName);	
			buttons[i].setText(Integer.toString(i+1));
			numsList.add(Integer.valueOf(i)+1);
			buttons[i].setSize(50, 50);		
			buttons[i].setFont( new Font( "Consolas", Font.BOLD, 14 ));
			buttons[i].setForeground(Color.white);
			buttons[i].setBackground(Color.black);
			buttons[i].addActionListener(new ActionListener() 
			{

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					
					JButton btn = new JButton();
					btn = (JButton) e.getSource();

					if( sortedList.isEmpty() )
					{
						
						if(btn.getBackground()==Color.pink) 
						{
							btn.setBackground(Color.black);
							JOptionPane.showMessageDialog(null, "Oops...\nNow You don't like { " + btn.getText() + " }");
							selectedNums.remove(Integer.valueOf(btn.getText()));
							selList.remove(Integer.valueOf(btn.getText()));
						}
						else if(btn.getBackground()==Color.black)
						{
							if(selectedNums.size()<6)
							{
								if(selectedNums.size()==5) 
								{
									JOptionPane.showMessageDialog(null, "Reminder : \nYou can ONLY choose 6 Numbers");
								}
								btn.setBackground(Color.pink);
								selectedNums.add(Integer.valueOf(btn.getText()));
								selList.add(Integer.valueOf(btn.getText()));
								
								JOptionPane.showMessageDialog(null, "Dear....\nYou just pick up { " + btn.getText() + " }");
							}
						}						
					}
					else
					{
						if(btn.getBackground() != Color.black)
						{
							btn.setBackground(new Color(102, 205, 170));
						}
						else
						{
							btn.setBackground(Color.black);
						}
					}
					
//					System.out.println(btn.getText());		//★☆★☆
//					System.out.println(selectedNums);		//★☆★☆
//					System.out.println(selList);
				}
			});
			
			pnP.add(buttons[i]);	//add all buttons into pnP			
		}
		
		//Create and set up a Button to get numbers randomly//
		btnRnd = new JButton("");
		btnRnd.setToolTipText("\u96FB\u8166\u9078\u865F (\u6E05\u9664\u5DF2\u9078\u865F\u78BC)\r\nQuick Picker");
		btnRnd.setIcon(new ImageIcon(hw2_lottery.class.getResource("/resources/random.png")));
		btnRnd.setForeground(new Color(0, 0, 255));
		btnRnd.setFont(new Font("Consolas", Font.BOLD, 16));
		btnRnd.setBackground(new Color(0, 153, 204));
		btnRnd.setBounds(530, 10, 60, 60);
		btnRnd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
					set.clear();
					sortedList.clear();
					
					for(int i =0; i<buttons.length;i++)
					{
						buttons[i].setBackground(Color.black);
					}
					
			        do {
			            r  = (int) (Math.random()* numsList.size())+1;
			            if(set.add(r))
			            { 						//若加入成功的話,則會回傳true
			            	int t = r-1;		//因為按鈕是從零開始,所以-1			            	
			            	buttons[t].setBackground(new Color(102, 205, 170));
			            }
			        } while (set.size() != 6);
			        
			        sortedList.addAll(set);		  //加入List
			        Collections.sort(sortedList); //排序

//			        System.out.println(set);			//★☆★☆
//			        System.out.println(sortedList);		//★☆★☆
				}
				catch(java.lang.ArrayIndexOutOfBoundsException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnRnd);
		
		JButton btnClean = new JButton("");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					selectedNums.clear();		//clean choosen numbers
					sortedList.clear();			//clean sorted randomize set
					set.clear();				//clean randomize set 
					listModel.clear();
					count=0L;
					money=0L;
					
					lblGet.setText("");					
					btn1.setText("");
			        btn2.setText("");
			        btn3.setText("");
			        btn4.setText("");
			        btn5.setText("");
			        btn6.setText("");
			        btnS.setText("");
			        
			        			        
					for(int i=0; i<buttons.length;i++) 
					{
						buttons[i].setBackground(Color.black);
						buttons[i].setForeground(Color.white);
					}
				}
				catch(ArrayIndexOutOfBoundsException aiobe){
					aiobe.printStackTrace();
				}
				
//				System.out.print(buttons.length+1);		//★☆★☆
//				System.out.print(selectedNums);			//★☆★☆
			}
		});
		btnClean.setIcon(new ImageIcon(hw2_lottery.class.getResource("/resources/clean.png")));
		btnClean.setToolTipText("\u5168\u90E8\u6E05\u9664\r\nClean up!");
		btnClean.setForeground(Color.BLUE);
		btnClean.setFont(new Font("Consolas", Font.BOLD, 16));
		btnClean.setBackground(new Color(0, 153, 204));
		btnClean.setBounds(530, 110, 60, 60);
		contentPane.add(btnClean);
		
		JButton btnBuy = new JButton("");
		btnBuy.setIcon(new ImageIcon(hw2_lottery.class.getResource("/resources/buy.png")));
		btnBuy.setToolTipText("\u6295\u6CE8\r\nClick and Buy");
		btnBuy.setForeground(Color.BLUE);
		btnBuy.setFont(new Font("Consolas", Font.BOLD, 16));
		btnBuy.setBackground(new Color(0, 153, 204));
		btnBuy.setBounds(530, 200, 60, 60);
		btnBuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!selList.isEmpty())
				{
					if(selList.size()<5)
					{
						JOptionPane.showMessageDialog(null, "hey....you could choose more!");
					}
					else
					{
						Collections.sort(selList);
						listModel.addElement(sortedList.toString());
						count++;
					}					
				}
				else if(!sortedList.isEmpty())
				{
					
					listModel.addElement(sortedList.toString());
					count++;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "hey....I found nothing!");					
				}
				lblCount.setText(String.valueOf(count));
				lblMoney.setText(String.valueOf(count*50));
			}
			
		});
		contentPane.add(btnBuy);
		
		JButton btnOpen = new JButton("");
		btnOpen.setFocusable(false);
		btnOpen.setOpaque(false);
		btnOpen.setIcon(new ImageIcon(hw2_lottery.class.getResource("/resources/open.png")));
		btnOpen.setToolTipText("\u958B\u734E\u5695\uFF5E\r\nWinner Numbers are ~~~~");
		btnOpen.setForeground(Color.BLUE);
		btnOpen.setFont(new Font("Consolas", Font.BOLD, 16));
		btnOpen.setBackground(new Color(102, 205, 170));
		btnOpen.setBounds(620, 10, 250, 250);
		btnOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//開獎
				wonPrize=0L;
				try
				{
					open.clear();
					openSortList.clear();
					openTemp.clear();
							
					for(int i =0; i<buttons.length;i++)
					{
						buttons[i].setForeground(Color.white);
					}
					
			        do {
			            r  = (int) (Math.random()* numsList.size())+1;
			            if(open.add(r))					//Set<Integer> Open;
			            { 								//若加入成功的話,則會回傳true
			            	int t = r-1;				//因為按鈕是從零開始所以-1
			            	buttons[t].setForeground(Color.magenta);
			            }
			            
			        } while (open.size() != 7);			       
			        
			        
			        openTemp.addAll(open);				//從集合複製到陣列
			        int7th = (Integer) openTemp.get(6);	//從陣列找到最後開獎的號碼,為特別號
			        openTemp.remove(int7th);			//將特別號從陣列移除
			        openSortList.addAll(openTemp);		//將陣列複製至集合
			        Collections.sort(openSortList);		//將集合排敘
			        
			        btn1.setText( openSortList.get(0).toString() );
			        btn2.setText( openSortList.get(1).toString() );
			        btn3.setText( openSortList.get(2).toString() );
			        btn4.setText( openSortList.get(3).toString() );
			        btn5.setText( openSortList.get(4).toString() );
			        btn6.setText( openSortList.get(5).toString() );
			        btnS.setText( int7th.toString() );			        
			        
//			        System.out.println(open);				//★☆★☆
//			        System.out.println(openTemp);			//★☆★☆
//			        System.out.println(int7th);				//★☆★☆
//			        System.out.println(openSortList);		//★☆★☆
			        msg = "\t"+"Winner===" + openSortList.toString() + "\t" +"=== Special:" + int7th + "\n";
				}
				catch(java.lang.ArrayIndexOutOfBoundsException e1)
				{
					e1.printStackTrace();
				}
				
				//對獎
				
				if(listModel.getSize()>0)
				{
					int o;
					int a;
					int b;
					
					for(int listCount =0; listCount< listModel.getSize();listCount++)
					{
						listElm = listModel.get(listCount);
						delBrackets = listElm.replaceAll("\\[", "").replaceAll("\\]","");
						afSplitListElm = delBrackets.split(", ");
						msg += "\n";
						msg += "Your (" + String.format("%2d", listCount+1) + ")=== " + delBrackets + "    " ;
						hit =0;
						hitS =0;

						for(int j=0; j< afSplitListElm.length;j++)
						{
							for(int c=0;c<openTemp.size();c++)
							{
								o = openTemp.get(c);
								b = Integer.valueOf(afSplitListElm[j]);
								
								if( o == b)
								{
									hit = (byte) (hit + 1);										
								}									
							}
							
							a = Integer.valueOf(afSplitListElm[j]);
							if(a == int7th)
							{
								hitS = 1;
							}																												
						}

						switch(hit)
						{
							case 1:
								if(hitS == 1)
								{
									msg = msg + " Yeah, Match 1 Number, 1 Special! But you haven't won the prize\n";
								}else
								{
									msg = msg + " Yeah, Match 1 Number! But you haven't won the prize\n";
								}
								break;
							case 2:
								if(hitS == 1)
								{
									msg = msg + " Yeah, Match 2 Numbers, 1 Special! You won $400\n";
									wonPrize += 400;
								}else
								{
									msg = msg + " Yeah, Match 2 Numbers! But you haven't won the prize\n";
								}
								break;
							case 3:
								if(hitS == 1)
								{
									msg = msg + " Yeah, Match 3 Numbers, 1 Special! You won $1000\n";
									wonPrize += 1000;
								}else
								{
									msg = msg + " Yeah, Match 3 Numbers! Congratulation! You won $400\n";
								}
								
								break;
							case 4:
								if(hitS == 1)
								{
									double w4 = prize * 0.045;
									wonPrize += w4;
									msg = msg + " Yeah, Match 4 Numbers, 1 Special! You won $" + w4 + "\n";	
								}else
								{
									msg = msg + " Yeah, Match 4 Numbers! Congratulation! You won the $2000\n";
								}																
								break;
							case 5:
								if(hitS == 1)
								{
									double w2 = prize * 0.065;
									wonPrize += w2;
									msg = msg + " Yeah, Match 5 Numbers, 1 Special! You won $" + w2 + "\n";
								}else
								{
									double w7 = prize * 0.045;
									wonPrize += w7;
									msg = msg + " Yeah, Match 5 Numbers! Congratulation! You won the $" + w7 + "\n";
								}
								
								break;
							case 6:
								msg = msg + " Amazing!!! Match 6 Numbers! You won!\n";								
								break;
							default:
								msg = msg + " Oops... Try one more time.\n";								
						}
					}
					JOptionPane.showMessageDialog(null, msg);
					lblGet.setText(String.valueOf(wonPrize));					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You haven't buy any ticket.");
				}						
				
			}
			
		});
		contentPane.add(btnOpen);
				
	}
}
