package project2;
import java.sql.*;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
public class PRoject2 {
	private JFrame frame;	
	private JTextField txtpname;
	private JTextField txtvaccines;
	private JTextField txtage;
	private JTable table;
	private JTextField txtpid;
	/**
	 * Launch the application.
	 */	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PRoject2 window = new PRoject2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	  public void table_load()
	     {
	      try 
		      
	      {
	     pst = con.prepareStatement("select * from patient_table");
	     rs = pst.executeQuery();
	     table.setModel(DbUtils.resultSetToTableModel(rs));
	 } 
	      catch (SQLException e) 
	      {
	      e.printStackTrace();
		      
	   } 
	     }
	/**
	 * Create the application.
	 */
	public PRoject2() {
		initialize();
		Connect();
		table_load();
	}
Connection con;
 PreparedStatement pst;
 ResultSet rs;
 private JTextField txtWelcomeToOur;
 private JTextField textpname;	
	
	public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
		
            con = DriverManager.getConnection("jdbc:mysql://localhost/patient", "root","");
        }
        catch (ClassNotFoundException ex) 
        {
          ex.printStackTrace();
		
        }
        catch (SQLException ex) 
        {
        	   ex.printStackTrace();
        }

    }		
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 381);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		JPanel panel = new JPanel();		
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Regis", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 58, 209, 135);
		frame.getContentPane().add(panel);
		panel.setLayout(null);	
		JLabel lblNewLabel_1 = new JLabel("Name/id");
		lblNewLabel_1.setForeground(Color.MAGENTA);
		lblNewLabel_1.setBounds(10, 27, 46, 14);
		panel.add(lblNewLabel_1);
		JLabel lblNewLabel_1_1 = new JLabel("Vaccines");
		lblNewLabel_1_1.setForeground(Color.BLUE);
		lblNewLabel_1_1.setBounds(10, 52, 70, 14);
		panel.add(lblNewLabel_1_1);
		JLabel lblNewLabel_1_2 = new JLabel("Age");		
		lblNewLabel_1_2.setForeground(Color.RED);
		lblNewLabel_1_2.setBounds(10, 72, 46, 14);
		panel.add(lblNewLabel_1_2);
		txtpname = new JTextField();
		txtpname.setBounds(100, 24, 86, 20);
		panel.add(txtpname);	
		txtpname.setColumns(10);
		txtvaccines = new JTextField();
		txtvaccines.setBounds(100, 49, 86, 20);
		panel.add(txtvaccines);
		txtvaccines.setColumns(10);	
		txtage = new JTextField();
		txtage.setForeground(Color.RED);
		txtage.setBounds(100, 69, 86, 20);
		panel.add(txtage);
		txtage.setColumns(10);
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setForeground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname,vac,agee;
				bname=txtpname.getText();
				vac=txtvaccines.getText();
				agee=txtage.getText();
				 try {
				 pst = con.prepareStatement("insert into patient_table(name,vaccines,age)values(?,?,?)");
				 pst.setString(1, bname);
				 pst.setString(2, vac);
				 pst.setString(3, agee);
					 
				 pst.executeUpdate();
				 JOptionPane.showMessageDialog(null, "Record is inserted.");
				 table_load();
				            
				 txtpname.setText("");
				 txtvaccines.setText("");
				 txtage.setText("");
				 txtpname.requestFocus();	 
				    }
				 catch (SQLException e1) 
				        {
				 e1.printStackTrace();
				 }	
			}
		});
		btnNewButton.setBounds(10, 204, 73, 23);
		frame.getContentPane().add(btnNewButton);
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(Color.MAGENTA);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpname.setText("");
				 txtvaccines.setText("");
				 txtage.setText("");
				 txtpname.requestFocus();
			}
		});
		btnClear.setBounds(162, 204, 68, 23);
		frame.getContentPane().add(btnClear);
		JButton btnExit = new JButton("Exit");
		
		btnExit.setForeground(Color.BLUE);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				System.exit(0);
			}
		});
		btnExit.setBounds(93, 204, 57, 23);
		frame.getContentPane().add(btnExit);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(229, 55, 179, 135);
		frame.getContentPane().add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(32, 252, 190, 57);
		frame.getContentPane().add(panel_1);
		JLabel lblNewLabel_1_1_1 = new JLabel("Id");
		lblNewLabel_1_1_1.setForeground(Color.RED);
		panel_1.add(lblNewLabel_1_1_1);
		txtpid = new JTextField();
		txtpid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
try {
             String id = txtpid.getText();
                 pst = con.prepareStatement("select name,vaccines,age from patient_table where id = ?");
                 pst.setString(1, id);
                 ResultSet rs = pst.executeQuery();
             if(rs.next()==true)
             {
                 String name = rs.getString(1);
                 String vac = rs.getString(2);
                 String agee = rs.getString(3);
                 txtpname.setText(name);
                 txtvaccines.setText(vac);
                 txtage.setText(agee);                   
             }   
             else
             {
		     
              txtpname.setText("");
              txtvaccines.setText("");
		     
                 txtage.setText("");
             }
         } 
 catch (SQLException ex) {     
         }
			}
		});
		txtpid.setColumns(10);
		panel_1.add(txtpid);
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.MAGENTA);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname,vac,agee,pid;
				bname=txtpname.getText();
				vac=txtvaccines.getText();
				agee=txtage.getText();
				pid=txtpid.getText();
				 try {
				 pst = con.prepareStatement("update patient_table set name= ?,vaccines=?,age=? where id =?");
				 pst.setString(1, bname);
				 pst.setString(2, vac);
				 pst.setString(3, agee);
				 pst.setString(4, pid);
				 pst.executeUpdate();
					 
				 JOptionPane.showMessageDialog(null, "Record is updated.");
				 table_load();            
				 txtpname.setText("");
				 txtvaccines.setText("");
				 txtage.setText("");
				 txtpname.requestFocus();
				    }
				 catch (SQLException e1) 
				        {
				 e1.printStackTrace();
				 }
			}
		});
		btnUpdate.setBounds(240, 204, 97, 23);
		frame.getContentPane().add(btnUpdate);
		JButton btnDelete = new JButton("Delete");
		
		btnDelete.setForeground(Color.RED);
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid;
				pid=txtpid.getText();
				 try {
				 pst = con.prepareStatement("delete from patient_table where id =?");
				 pst.setString(1, pid);
				 pst.executeUpdate();
				 JOptionPane.showMessageDialog(null, "Record is deleted.");
				 table_load();           
				 txtpname.setText("");
				 txtvaccines.setText("");
				 txtage.setText("");
				 txtpname.requestFocus();
				    }
				
				 catch (SQLException e1) 
				        {
				 e1.printStackTrace();
				 }				
			}
		});
		btnDelete.setBounds(347, 204, 77, 23);
		frame.getContentPane().add(btnDelete);
		txtWelcomeToOur = new JTextField();
		txtWelcomeToOur.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtWelcomeToOur.setForeground(Color.RED);
		txtWelcomeToOur.setText("Welcome to our system.");
		txtWelcomeToOur.setBounds(42, 11, 326, 36);
		frame.getContentPane().add(txtWelcomeToOur);
		txtWelcomeToOur.setColumns(10);
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBounds(234, 252, 190, 57);
		frame.getContentPane().add(panel_1_1);
		JLabel lblNewLabel_1_1_1_1 = new JLabel("name");
		lblNewLabel_1_1_1_1.setForeground(Color.RED);
		
		panel_1_1.add(lblNewLabel_1_1_1_1);
		textpname = new JTextField();
		textpname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
try {
             String name = textpname.getText();
                 pst = con.prepareStatement("select id,vaccines,age from patient_table where name = ?");
                 pst.setString(1, name);
                 ResultSet rs = pst.executeQuery();
	
             if(rs.next()==true)
             {
		     
                 String id = rs.getString(1);
		     
                 String vac = rs.getString(2);
                 String agee = rs.getString(3);
                 txtpname.setText(id);
                 txtvaccines.setText(vac);
                 txtage.setText(agee);
             }   
             else
             {
              txtpname.setText("");
              txtvaccines.setText("");
                 txtage.setText("");
             }
         } 
 catch (SQLException ex) {            
         }
			}
		});
		textpname.setColumns(10);
		panel_1_1.add(textpname);
		
	}
}
