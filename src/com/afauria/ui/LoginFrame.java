package com.afauria.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.afauria.dao.StaffDao;
import com.afauria.entity.Staff;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private StaffDao mDao = new StaffDao();

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		init();
	}

	private JLabel usernameLabel = new JLabel("用户名");
	private JLabel passwordLabel = new JLabel("密码");
	private JTextField usernameText = new JTextField();
	private JTextField passwordText = new JTextField();
	private JButton loginBtn = new JButton("登录");
	private JButton registBtn = new JButton("注册");

	private void init() {
		JPanel btnPanel = new JPanel();
		btnPanel.add(registBtn);
		btnPanel.add(loginBtn);
		JPanel textPanel = new JPanel();
		textPanel.setLayout(null);
		usernameLabel.setBounds(100, 50, 100, 40);
		passwordLabel.setBounds(100, 100, 100, 40);
		usernameText.setBounds(150, 50, 100, 40);
		passwordText.setBounds(150, 100, 100, 40);
		textPanel.add(usernameLabel);
		textPanel.add(usernameText);
		textPanel.add(passwordLabel);
		textPanel.add(passwordText);
		contentPane.add(textPanel, BorderLayout.CENTER);
		contentPane.add(btnPanel, BorderLayout.SOUTH);

		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean success = mDao.checkLogin(usernameText.getText(), passwordText.getText());
				if(success) {
					ManageFrame manageFrame = new ManageFrame();
					manageFrame.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(LoginFrame.this, "账号或密码错误！");
				}
			}
		});
		registBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EditDialog dialog = new EditDialog(new Staff(), true);
				dialog.setVisible(true);
			}
		});
	}

}
