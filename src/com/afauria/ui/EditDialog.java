package com.afauria.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.afauria.dao.StaffDao;
import com.afauria.entity.Staff;

public class EditDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Staff staff;
	private boolean isAdd = true;
	private ManageFrame manageFrame;

	public void setManageFrame(ManageFrame manageFrame) {
		this.manageFrame = manageFrame;
	}

	public static void main(String[] args) {
		try {
			EditDialog dialog = new EditDialog(new Staff(), true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EditDialog(Staff staff, boolean isAdd) {
		this.isAdd = isAdd;
		this.staff = staff;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StaffDao dao = new StaffDao();
				// TODO 对数据做校验
				staff.setUsername(usernameText.getText());
				staff.setPasswd(passwdText.getText());
				staff.setName(nameText.getText());
				staff.setPhone(phoneText.getText());
				staff.setDepartment(departmentText.getText());
				staff.setGender(maleButton.isSelected() ? 0 : 1);
				boolean success = false;
				if (isAdd) {
					success = dao.insertStaff(staff);
				} else {
					success = dao.updateStaff(staff);
				}
				if (success) {
					if (manageFrame != null) {
						// 正常需要使用回调通知，这里简化，直接传入Frame
						manageFrame.updateTable();
					}
					dispose();
				}
			}
		});
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(okButton);
		buttonPane.add(cancelButton);
		init();
	}

	private JLabel usernameLabel = new JLabel("用户名");
	private JLabel passwdLabel = new JLabel("密码名");
	private JLabel nameLabel = new JLabel("名称");
	private JLabel phoneLabel = new JLabel("手机号");
	private JLabel departmentLabel = new JLabel("部门");
	private JLabel genderLabel = new JLabel("性别");
	private JLabel managerLabel = new JLabel("管理员");

	private JTextField usernameText = new JTextField();
	private JTextField passwdText = new JTextField();
	private JTextField nameText = new JTextField();
	private JTextField phoneText = new JTextField();
	private JTextField departmentText = new JTextField();
	private JRadioButton maleButton = new JRadioButton("男");
	private JRadioButton femaleButton = new JRadioButton("女");
	private JCheckBox managerCheckBox = new JCheckBox("管理员");

	private void init() {
		usernameText.setText(staff.getUsername());
		passwdText.setText(staff.getPasswd());
		nameText.setText(staff.getName());
		phoneText.setText(staff.getPhone());
		departmentText.setText(staff.getDepartment());
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(maleButton);
		btnGroup.add(femaleButton);
		if (staff.getGender() == 1) {
			femaleButton.setSelected(true);
		} else {
			maleButton.setSelected(true);
		}
		managerCheckBox.setSelected(staff.isManager());

		JPanel usernamePanel = new JPanel();
		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameText);
		usernameText.setPreferredSize(new Dimension(100, 40));

		JPanel passwdPanel = new JPanel();
		passwdPanel.add(passwdLabel);
		passwdPanel.add(passwdText);
		passwdText.setPreferredSize(new Dimension(100, 40));

		JPanel namePanel = new JPanel();
		namePanel.add(nameLabel);
		namePanel.add(nameText);
		nameText.setPreferredSize(new Dimension(100, 40));

		JPanel phonePanel = new JPanel();
		phonePanel.add(phoneLabel);
		phonePanel.add(phoneText);
		phoneText.setPreferredSize(new Dimension(100, 40));

		JPanel departmentPanel = new JPanel();
		departmentPanel.add(departmentLabel);
		departmentPanel.add(departmentText);
		departmentText.setPreferredSize(new Dimension(100, 40));

		JPanel genderPanel = new JPanel();
		genderPanel.add(genderLabel);
		genderPanel.add(maleButton);
		genderPanel.add(femaleButton);

		JPanel managerPanel = new JPanel();
		managerPanel.add(managerLabel);
		managerPanel.add(managerCheckBox);

		contentPanel.add(usernamePanel);
		contentPanel.add(passwdPanel);
		contentPanel.add(namePanel);
		contentPanel.add(phonePanel);
		contentPanel.add(departmentPanel);
		contentPanel.add(genderPanel);
		contentPanel.add(managerPanel);
	}

}
