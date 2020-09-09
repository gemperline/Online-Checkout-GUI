import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Icon;

public class DisplayFrame extends JFrame implements ActionListener, KeyListener {
	
	// constant for total charges
	private final double BILL_TOTAL = 24.99;
	long cardNumber = 1L;
	String cardText;
	String nameText;
	private static String fourDigits;
	
	
	private void setLastFour(long num) 
	{
		String str = num+"";
		StringBuilder lastFour = new StringBuilder();
		
		for(int i = 0; i < str.length(); i ++)
		{
			if(i >= (str.length() - 4))
			{
				lastFour.append(str.charAt(i));
			}
		}
		fourDigits = lastFour.toString();
	}
	
	private static String getLastFour() 
	{
		return fourDigits;
	}
	
	// panels
	private JPanel panel= new JPanel();
	private JPanel outerPanel = new JPanel();
	private JPanel paymentWindow = new JPanel();
	private JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel cpRow1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel cpRow2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel cpRow3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel cpRow4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel cpLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel cpLeftTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel cpLeftBottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel cpRight = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel cpRightTop = new JPanel();
	private JPanel cpRightBottom = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JPanel borderTop = new JPanel();
	private JPanel borderLeft = new JPanel();
	private JPanel borderRight = new JPanel();
	private JPanel borderBottom = new JPanel();
	private JPanel confirmPanel = new JPanel();
		JPanel topConfirmPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel midConfirmPanel = new JPanel(new GridLayout(3,1));
		JPanel bottomConfirmPanel = new JPanel();
		JPanel midConfirmPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

	// buttons
	private JRadioButton ccButton = new JRadioButton("Credit Card (Visa, Mastercard)", true);
	private JRadioButton ppButton = new JRadioButton("PayPal", false);
	private ButtonGroup radioButtons = new ButtonGroup();
	private JButton purchase = new JButton("Purchase");
	
	// icons 
	Icon icon = new ImageIcon("D:/Adam/Documents/GitHub/Online-Checkout-GUI/Icons");
	
	// labels
	private JLabel secureLabel = new JLabel(" Don't worry, this small lock icon ensures your transaction is safe.");
	private JLabel cardNumLabel = new JLabel("Card Number (13-16 digits)");
	private JLabel expLabel = new JLabel("Expiration Date");
	private JLabel cvvLabel = new JLabel("CVV2/CVC2");
	private JLabel nameLabel = new JLabel("Full Name on Card");
	private JLabel billingLabel = new JLabel("You will be charged $" + BILL_TOTAL + ".");
	private JLabel slash = new JLabel("/");
	private JLabel thankYou = new JLabel("Thank You!");
	private JLabel confirmText = new JLabel("Your Order has been placed!");
	private JLabel name = new JLabel();
	private JLabel cardNum = new JLabel();

	
	// combo boxes for month and year,
	private String[] months = {"Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	private JComboBox monthBox = new JComboBox(months);
	private String[] years = {"Year", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025","2026","2027"};
	private JComboBox yearBox = new JComboBox(years);
	
	// text fields
	private JTextField cardNumField = new JTextField("", 35);
	private JTextField cvvField= new JTextField("", 10);
	private JTextField nameField = new JTextField("", 35);
	
	// border outlines
	private Border border = new LineBorder(Color.GRAY, 1);
	private Border border2 = new LineBorder(Color.BLACK, 1, true);
	
	// Images and icons
	ImageIcon lock = new ImageIcon("D:/Adam/Documents/GitHub/Online-Checkout-GUI/Icons/lock.png");
	JLabel lockLabel = new JLabel();
	
	ImageIcon masterCard = new ImageIcon("D:/Adam/Documents/GitHub/Online-Checkout-GUI/Icons/mastercard.png");
	JLabel mcLabel = new JLabel();
	
	ImageIcon payPal = new ImageIcon("D:/Adam/Documents/GitHub/Online-Checkout-GUI/Icons/paypal.png");
	JLabel ppLabel = new JLabel();
	
	ImageIcon visa = new ImageIcon("D:/Adam/Documents/GitHub/Online-Checkout-GUI/Icons/visa.png");
	JLabel visaLabel = new JLabel();
	
	ImageIcon xIcon = new ImageIcon("/Users//Adam//Desktop/icons/x-button.png");
	JLabel xLabel = new JLabel();
			
	// Constructor
	public DisplayFrame()
	{
		super("Credit Card Verification");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
//		Image lockImg = lock.getImage().getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH);
//		iconLabel.setIcon(new ImageIcon(lockImg));
//		
		Image lockImg = lock.getImage().getScaledInstance(12,16, java.awt.Image.SCALE_SMOOTH);
		lockLabel.setIcon(new ImageIcon(lockImg));
		
		Image visaImg = visa.getImage().getScaledInstance(50,30, java.awt.Image.SCALE_SMOOTH);
		visaLabel.setIcon(new ImageIcon(visaImg));
		
		Image mcImg = masterCard.getImage().getScaledInstance(50,30, java.awt.Image.SCALE_SMOOTH);
		mcLabel.setIcon(new ImageIcon(mcImg));
		
		Image ppImg = payPal.getImage().getScaledInstance(50,30, java.awt.Image.SCALE_SMOOTH);
		ppLabel.setIcon(new ImageIcon(ppImg));
		

		
		purchase.addActionListener(this);
		purchase.addKeyListener(this);
		
		outerPanel.setLayout(new BorderLayout(10, 10));
		outerPanel.setBackground(Color.WHITE);
		borderLeft.setBackground(Color.WHITE);
		borderRight.setBackground(Color.WHITE);
		borderTop.setBackground(Color.WHITE);
		borderBottom.setBackground(Color.WHITE);
		outerPanel.add(borderTop, BorderLayout.NORTH);
		outerPanel.add(borderLeft, BorderLayout.WEST);
		outerPanel.add(borderRight, BorderLayout.EAST);
		outerPanel.add(borderBottom, BorderLayout.SOUTH);
		outerPanel.add(paymentWindow, BorderLayout.CENTER);
		
		paymentWindow.setLayout(new BorderLayout());
		paymentWindow.add(topPanel, BorderLayout.NORTH);
		paymentWindow.add(centerPanel, BorderLayout.CENTER);
		paymentWindow.add(bottomPanel, BorderLayout.SOUTH);
		paymentWindow.setBorder(border);
		

	// PAYMENT WINDOW
		
		// payment window, top panel
		topPanel.setPreferredSize(new Dimension(270, 28));
		topPanel.setBackground(new Color(102, 178, 255));
		secureLabel.setFont(new Font("Arial", Font.BOLD, 12));
		secureLabel.setForeground(Color.WHITE);
		topPanel.add(lockLabel);
		topPanel.add(secureLabel);
		secureLabel.setVerticalAlignment(JLabel.CENTER);
		
		// payment window, center panel
		centerPanel.setLayout(new GridLayout(4,1));
		centerPanel.setBackground(Color.WHITE);
		
			// center panel, row 1
			centerPanel.add(cpRow1);	
			cpRow1.setBackground(Color.WHITE);
		
				// mutually exclusive buttons
				ccButton.setFont(new Font("Arial", Font.PLAIN, 14));
				ppButton.setFont(new Font("Arial", Font.PLAIN, 14));
				ccButton.setForeground(new Color(52, 172, 255));
				ccButton.setBackground(Color.WHITE);
				ppButton.setForeground(new Color(52, 172, 255));
				ppButton.setBackground(Color.WHITE);
				radioButtons.add(ccButton);
				radioButtons.add(ppButton);
				cpRow1.add(ccButton);
				cpRow1.add(visaLabel);
				cpRow1.add(mcLabel);
				cpRow1.add(ppButton);
				cpRow1.add(ppLabel);

			// center panel, row 2
			centerPanel.add(cpRow2);	
			cpRow2.setBackground(Color.WHITE);
			cardNumLabel.setFont(new Font("Arial", Font.PLAIN, 14));
			cardNumLabel.setForeground(new Color(52, 172, 255));
			cpRow2.add(cardNumLabel);
			cpRow2.add(cardNumField);
			cardNumField.setPreferredSize(new Dimension(0, 30));
			cardNumField.setFont(new Font("Arial", Font.PLAIN, 14));
			
			// center panel, row 3
			centerPanel.add(cpRow3);
			cpRow3.setLayout(new GridLayout(1,2));
			cpRow3.setBackground(Color.WHITE);
			
				cpRow3.add(cpLeft);
				expLabel.setFont(new Font("Arial", Font.PLAIN, 14));
				expLabel.setForeground(new Color(52, 172, 255));
				cpLeft.setBackground(Color.WHITE);
				cpLeft.add(cpLeftTop);
				cpLeft.add(cpLeftBottom);
				cpLeftTop.add(expLabel);
				cpLeftBottom.add(monthBox);
				cpLeftBottom.add(slash);
				cpLeftBottom.add(yearBox);
			
				cpRight.setLayout(new GridLayout(2,1));
				cpRow3.add(cpRight);	
				cpRight.add(cpRightTop);
				cpRight.add(cpRightBottom);
				cvvLabel.setFont(new Font("Arial", Font.PLAIN, 14));
				cvvLabel.setForeground(new Color(52, 172, 255));
				cvvField.setPreferredSize(new Dimension(0, 30));
				cvvField.setFont(new Font("Arial", Font.PLAIN, 14));
				cpRightTop.add(cvvLabel);
				cpRightBottom.add(cvvField);
				cpLeftTop.setBackground(Color.WHITE);
				cpLeftBottom.setBackground(Color.WHITE);
				cpRightTop.setBackground(Color.WHITE);
				cpRightBottom.setBackground(Color.WHITE);

			// center panel, row 4
			centerPanel.add(cpRow4);	
			nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
			nameLabel.setForeground(new Color(52, 172, 255));
			nameField.setPreferredSize(new Dimension(0, 30));
			nameField.setFont(new Font("Arial", Font.PLAIN, 14));
			cpRow4.add(nameLabel);
			cpRow4.add(nameField);
			cpRow4.setBackground(Color.WHITE);

		// payment window, bottom panel
		bottomPanel.setBackground(new Color(220, 220, 220));
		billingLabel.setFont(new Font("Arial", Font.BOLD, 14));
		billingLabel.setForeground(new Color(52, 172, 255));
		bottomPanel.add(billingLabel);
		purchase.setBackground(Color.ORANGE);
		purchase.setForeground(Color.WHITE);
		purchase.setFont(new Font("Arial", Font.BOLD, 16));
		purchase.setBorder(border2);
		purchase.setPreferredSize(new Dimension(150, 30));
		bottomPanel.add(purchase);
		
		
		
	// CONFIRMATION WINDOW
		
		confirmPanel.setLayout(new GridLayout(4,1));
		confirmPanel.setBackground(Color.WHITE);
		
		//	panels
			topConfirmPanel.setPreferredSize(new Dimension(400,50));
			topConfirmPanel.setBackground(Color.WHITE);
			
			midConfirmPanel.setPreferredSize(new Dimension(400,400));
			midConfirmPanel.setBackground(Color.WHITE);
			
			midConfirmPanel2.setPreferredSize(new Dimension(400,400));
			midConfirmPanel2.setBackground(Color.WHITE);
			
			bottomConfirmPanel.setPreferredSize(new Dimension(400,100));
			bottomConfirmPanel.setBackground(Color.WHITE);			
				
			
			
			
	panel = new JPanel(new CardLayout());
	panel.add(outerPanel, "Main");
	panel.add(confirmPanel, "Confirm");
	getContentPane().add(panel);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == purchase)
		{
			
			// store text data from text field as strings to be combined into a label
			cardText = cardNumField.getText();
			nameText = nameField.getText();

			// set strings from text fields to labels
			name = new JLabel(nameText);
			cardNum = new JLabel(cardText);
			
			name.setFont(new Font("Arial", Font.PLAIN, 16));
			cardNum.setFont(new Font("Arial", Font.PLAIN, 16));
			
			// parse credit card string input to a long type
			cardNumber = Long.parseLong(cardText);
			
			// variable to store return value from Luhn Algorithm
			boolean valid = CheckCardNum.isValid(cardNumber);
			
			if(valid == true)
			{
				setLastFour(cardNumber);
				JLabel billing = new JLabel("BILLING INFORMATION:");			
				JLabel orderName = new JLabel("Name: " + nameText);
				JLabel payment = new JLabel("Payment: Card ending in " + getLastFour());
				
				thankYou.setFont(new Font("Arial", Font.BOLD, 20));
				billing.setFont(new Font("Arial", Font.BOLD, 20));
				orderName.setFont(new Font("Arial", Font.BOLD, 20));
				payment.setFont(new Font("Arial", Font.BOLD, 20));
				confirmText.setFont(new Font("Arial", Font.BOLD, 24));
				
				confirmPanel.add(topConfirmPanel);
				confirmPanel.add(midConfirmPanel);
				confirmPanel.add(midConfirmPanel2);
				confirmPanel.add(bottomConfirmPanel);
				
				
				// add items to panels
				topConfirmPanel.add(confirmText);
				midConfirmPanel.add(billing);
				midConfirmPanel.add(orderName);
				midConfirmPanel.add(payment);
				bottomConfirmPanel.add(thankYou);
				
				CardLayout cardLayout = (CardLayout) panel.getLayout();
				cardLayout.show(panel, "Confirm");
			}
			else
			{
				//bottomPanel.add(xLabel);
				cpRow2.setBackground(Color.RED);
				
			}
		}		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			// store text data from text field as strings to be combined into a label
			cardText = cardNumField.getText();
			nameText = nameField.getText();

			// set strings from text fields to labels
			name = new JLabel(nameText);
			cardNum = new JLabel(cardText);
			
			name.setFont(new Font("Arial", Font.PLAIN, 16));
			cardNum.setFont(new Font("Arial", Font.PLAIN, 16));
			
			// parse credit card string input to a long type
			cardNumber = Long.parseLong(cardText);
			
			CheckCardNum checkNum = new CheckCardNum();
			
			// variable to store return value from Luhn Algorithm
			boolean valid = checkNum.isValid(cardNumber);
			
			if(valid == true)
			{

				JLabel billing = new JLabel("BILLING INFORMATION:");			
				JLabel orderName = new JLabel("Name: Adam Gemperline" );
				JLabel payment = new JLabel("Payment: Card ending in 1007" );
				
				thankYou.setFont(new Font("Arial", Font.BOLD, 20));
				billing.setFont(new Font("Arial", Font.BOLD, 20));
				orderName.setFont(new Font("Arial", Font.BOLD, 20));
				payment.setFont(new Font("Arial", Font.BOLD, 20));
				confirmText.setFont(new Font("Arial", Font.BOLD, 24));
				
				confirmPanel.add(topConfirmPanel);
				confirmPanel.add(midConfirmPanel);
				confirmPanel.add(midConfirmPanel2);
				confirmPanel.add(bottomConfirmPanel);
				
				
				// add items to panels
				topConfirmPanel.add(confirmText);
				midConfirmPanel.add(billing);
				midConfirmPanel.add(orderName);
				midConfirmPanel.add(payment);
				bottomConfirmPanel.add(thankYou);
				
				CardLayout cardLayout = (CardLayout) panel.getLayout();
				cardLayout.show(panel, "Confirm");
			}
			else
			{
				//bottomPanel.add(xLabel);
				cpRow2.setBackground(Color.RED);
				
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



}

