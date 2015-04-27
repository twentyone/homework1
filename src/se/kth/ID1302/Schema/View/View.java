package se.kth.ID1302.Schema.View;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

//import org.jdatepicker.impl.JDatePanelImpl;
//import org.jdatepicker.impl.JDatePickerImpl;
//import org.jdatepicker.impl.UtilDateModel;



import java.awt.Choice;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JCalendar;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.text.ParseException;

import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import se.kth.ID1302.Schema.Controller.Controller;

import se.kth.ID1302.Schema.Model.Model;


public class View{
	
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private String test; 
	
	private SwingAction exitHandler;
	private OKAction	okHandler;	

	private final JFileChooser fc = new JFileChooser(); 
	private final Controller controller = new Controller(); 
	private final Model model = new Model();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
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
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblNamn = new JLabel("Namn");
		springLayout.putConstraint(SpringLayout.NORTH, lblNamn, 2, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNamn, 135, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNamn);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 180, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, 448, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JLabel lblDeltagare = new JLabel("Deltagare");
		springLayout.putConstraint(SpringLayout.NORTH, lblDeltagare, 26, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblDeltagare, 104, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblDeltagare);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 24, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 180, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField_1, 448, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTid = new JLabel("Starttid");
		springLayout.putConstraint(SpringLayout.NORTH, lblTid, 50, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblTid, 120, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblTid);
		
		textField_2 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, 48, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField_2, 180, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField_2, 448, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Sluttid");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 74, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 128, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_3, 72, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField_3, 180, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField_3, 448, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblMteshd = new JLabel("Längd");
		springLayout.putConstraint(SpringLayout.NORTH, lblMteshd, 98, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblMteshd, 131, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblMteshd);
		
		textField_4 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_4, 96, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField_4, 180, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField_4, 448, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblJ = new JLabel("Max antal icke deltagare");
		springLayout.putConstraint(SpringLayout.NORTH, lblJ, 122, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblJ, 0, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblJ);
		
		textField_5 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_5, 120, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField_5, 180, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField_5, 448, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Startdag");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 144, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 56, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel);
		
	
		 
		
		
		JLabel lblNewLabel_1 = new JLabel("Slutdag");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 164, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 60, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel_1);
		
		JDateChooser dateChooser = new JDateChooser();
		springLayout.putConstraint(SpringLayout.WEST, dateChooser, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, dateChooser, 0, SpringLayout.SOUTH, lblNewLabel);
		frame.getContentPane().add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		springLayout.putConstraint(SpringLayout.WEST, dateChooser_1, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, dateChooser_1, 0, SpringLayout.SOUTH, lblNewLabel_1);
		frame.getContentPane().add(dateChooser_1);
		
		JCalendar calendar = new JCalendar();
		calendar.getDayChooser().getDayPanel().setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		calendar.getDayChooser().getDayPanel().setBackground(new Color(51, 102, 153));
		calendar.getDayChooser().getDayPanel().setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.NORTH, calendar, 21, SpringLayout.SOUTH, dateChooser_1);
		springLayout.putConstraint(SpringLayout.WEST, calendar, -445, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, calendar, 208, SpringLayout.SOUTH, dateChooser_1);
		springLayout.putConstraint(SpringLayout.EAST, calendar, -128, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(calendar);
		
		JButton btnNewButton = new JButton("Cancel\n");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 27, SpringLayout.SOUTH, calendar);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 115, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(btnNewButton);
		exitHandler = new SwingAction();
		btnNewButton.addActionListener(exitHandler);
		
		JButton btnOk = new JButton("OK");
		springLayout.putConstraint(SpringLayout.NORTH, btnOk, 0, SpringLayout.NORTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, btnOk, -68, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnOk);
		
		JButton btnImport = new JButton("Import");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String owner = JOptionPane.showInputDialog("Namn: ");
				int returnVal = fc.showOpenDialog(frame);
				
				String path = fc.getSelectedFile().getAbsolutePath();
				try {
					controller.importSchema(owner, path);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(frame, "Fel vid Parseing", "Error!" , JOptionPane.ERROR_MESSAGE);
				
				}
			}
			model.Algorithm
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnImport, 6, SpringLayout.SOUTH, textField_5);
		springLayout.putConstraint(SpringLayout.EAST, btnImport, 0, SpringLayout.EAST, textField);
		frame.getContentPane().add(btnImport);
		okHandler = new OKAction(); 
		btnOk.addActionListener(okHandler);
		
	}

	public String gettextField(){
	return textField.getText(); 
	}
	
	private class SwingAction implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	private class OKAction implements ActionListener{
			@Override
		public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(frame, View.this.gettextField());
			
//				String cmd = e.getActionCommand();
//			    JOptionPane.showMessageDialog(frame, "Command: " + cmd);
//				if(e.getSource() == textField){ 
//		            String contents = textField.getText();
//		            System.out.println("The field contained: "+contents);
				// Skicka med datat till Schemaläggaren
				
				//Placera datat på rätt ställe i GUIn
			
		}
		
		
	}
}

