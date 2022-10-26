package akshisms;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends BasicFrame
{
	private static final long serialVersionUID = 1L;
	public Login()
	{
		makeVisible();
		Connection con=databases.Connect.getConnection();
		if(con==null)
		{
			JOptionPane.showMessageDialog(this,"No Database found! Login as Admin and setup Database to use the Application.");
		}
		
	}
	public void addMainSection()
	{
		JPanel main_section=new JPanel();
		main_section.setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
		main_section.setLayout(new BoxLayout(main_section,BoxLayout.Y_AXIS));
				
				//Main Heading
				JPanel main_heading_panel=new JPanel();
				main_heading_panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
					JTextField main_heading=new JTextField("USER LOGIN");
					main_heading.setFont(new Font(Font.MONOSPACED,Font.BOLD,30));
					main_heading.setForeground(Color.DARK_GRAY);
					main_heading.setBackground(main_section.getBackground());
					main_heading.setBorder(BorderFactory.createEmptyBorder());
					main_heading_panel.add(main_heading);
				main_heading_panel.setVisible(true);
				main_section.add(main_heading_panel);
				//Main Heading End
				
				//Main Para
				JPanel main_para_panel=new JPanel();
				main_para_panel.setLayout(new BoxLayout(main_para_panel,BoxLayout.Y_AXIS));
					JPanel username_panel=new JPanel();
					username_panel.setLayout(new FlowLayout());
						JLabel username=new JLabel("Username: ");
						username.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,24));
						username.setForeground(Color.GRAY);
						username_panel.add(username);
						JTextField user_input=new JTextField(20);
						user_input.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,24));
						user_input.setForeground(Color.GRAY);
						username_panel.add(user_input);
					username_panel.setVisible(true);
					main_para_panel.add(username_panel);
					
					JPanel password_panel=new JPanel();
					password_panel.setLayout(new FlowLayout());
						JLabel password=new JLabel("Password: ");
						password.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,24));
						password.setForeground(Color.GRAY);
						password_panel.add(password);
						JPasswordField password_input=new JPasswordField(20);
						password_input.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,24));
						password_input.setForeground(Color.GRAY);
						password_panel.add(password_input);
					password_panel.setVisible(true);
					main_para_panel.add(password_panel);
					
					JPanel usertype_panel=new JPanel();
					usertype_panel.setLayout(new FlowLayout(FlowLayout.CENTER,25,0));
						JLabel usertype=new JLabel("Login As: ");
						usertype.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,24));
						usertype.setForeground(Color.DARK_GRAY);
						usertype_panel.add(usertype);
						
						ButtonGroup bg=new ButtonGroup();
						JRadioButton r1=new JRadioButton("User",true);	//this button is selected by default
						r1.setFont(usertype.getFont());
						r1.setForeground(usertype.getForeground());
						JRadioButton r2=new JRadioButton("Admin");
						r2.setFont(usertype.getFont());
						r2.setForeground(usertype.getForeground());
						bg.add(r1);bg.add(r2);
						usertype_panel.add(r1);
						usertype_panel.add(r2);
						
					usertype_panel.setVisible(true);
					main_para_panel.add(usertype_panel);
					
					
					JPanel button_panel=new JPanel();
					button_panel.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
						JButton main_button=new JButton("Login");
						main_button.setFont(new Font(Font.MONOSPACED,Font.BOLD,24));
						main_button.setForeground(main_heading.getForeground());
							
							//When Button is Pressed
							main_button.addActionListener(new ActionListener()
									{
										public void actionPerformed(ActionEvent e)
										{
											String pw=String.valueOf(password_input.getPassword());
											String un=user_input.getText();
											if(un.length()>=5 && pw.length()>=5 && un.length()<=20 && pw.length()<=20)
											{
												if(r1.isSelected())	//User Login
												{
													if(databases.ValidateLogin.authenticateUser(un,pw))
													{
														new SelectOperation();
														dispose();
													}
													else
													{
														JOptionPane.showMessageDialog(main_section,"Incorrect Username or Password!");
													}
												}
												else	//Admin Login
												{
													if(databases.ValidateLogin.authenticateAdmin(un,pw))
													{
														new AdminPage();
														dispose();
													}
													else
													{
														JOptionPane.showMessageDialog(main_section,"Incorrect Username or Password!");
													}
												}
											}
											else
											{
												JOptionPane.showMessageDialog(main_section,"Username and Password must be between 5 to 20 characters.");
											}
										}
									});
						
					button_panel.add(main_button);
					button_panel.setVisible(true);
					main_para_panel.add(button_panel);
				main_para_panel.setVisible(true);
				main_section.add(main_para_panel);
				//Main Para End
				
				//Footer Button
				JPanel footer_button_panel=new JPanel();
				footer_button_panel.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
					JButton footer_button=new JButton("Exit Application");
					footer_button.setFont(new Font(Font.MONOSPACED,Font.BOLD,24));
					footer_button.setBackground(Color.WHITE);
					footer_button.setForeground(main_heading.getForeground());
						
						//When Button is Pressed
						footer_button.addActionListener(new ActionListener()
								{
									public void actionPerformed(ActionEvent e)
									{
										new Welcome();
										disposeWindow();
									}
								});
					
				footer_button_panel.add(footer_button);
				footer_button_panel.setVisible(true);
				main_section.add(footer_button_panel);
				//Footer Button End
				
		main_section.setVisible(true);
		main_panel.add(main_section);
	}
}
