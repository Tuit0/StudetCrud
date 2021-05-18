import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test {
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		JPanel panel = new JPanel();
		
		JTextField num1=new JTextField(5);
		JLabel label1 = new JLabel("First Number:");
		JPanel pNum1 = new JPanel();
		pNum1.add(label1);
		pNum1.add(num1);

		JTextField num2=new JTextField(5);
		JLabel label2 = new JLabel("Second Number:");
		JPanel pNum2 = new JPanel();
		pNum2.add(label2);
		pNum2.add(num2);

		panel.add(pNum1);
		panel.add(pNum2);
	
		JButton add = new JButton("Add");
		JButton sub = new JButton("Subtract");
		JButton mult = new JButton("Multiply");
		JButton divide = new JButton("Divide");
		
		add.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){ 
				try{
					JOptionPane.showMessageDialog(null,Integer.parseInt(num1.getText())+Integer.parseInt(num2.getText()));  
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Something went wrong!");  
				}
			}  
		});

		sub.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){ 
				try{
					JOptionPane.showMessageDialog(null,Integer.parseInt(num1.getText())-Integer.parseInt(num2.getText()));  
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Something went wrong!");  
				}
			}  
		});

		mult.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){ 
				try{
					JOptionPane.showMessageDialog(null,Integer.parseInt(num1.getText())*Integer.parseInt(num2.getText()));  
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Something went wrong!");  
				}
			}  
		});

		divide.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){ 
				try{
					JOptionPane.showMessageDialog(null,Double.parseDouble(num1.getText())/Integer.parseInt(num2.getText()));  
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Something went wrong!");  
				}
			}  
		});
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(add);		
		buttonPanel.add(sub);		
		buttonPanel.add(mult);		
		buttonPanel.add(divide);		

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(panel,"North");
		mainPanel.add(buttonPanel, "Center");
		

		frame.add(mainPanel);
		frame.setBounds(430,200,500,300);
		frame.setVisible(true);
	}
}