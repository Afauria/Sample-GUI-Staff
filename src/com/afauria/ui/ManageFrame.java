package com.afauria.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.afauria.dao.StaffDao;
import com.afauria.entity.Staff;
import com.afauria.model.StaffTableModel;

public class ManageFrame extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageFrame frame = new ManageFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ManageFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		init();
	}

	private StaffDao mDao = new StaffDao();
	private JLabel searchLabel = new JLabel("根据名称模糊查询");
	private JTextField searchText = new JTextField();
	private JButton searchBtn = new JButton("查询");
	private JTable table = new JTable();
	private JButton addBtn = new JButton("添加");
	private JButton editBtn = new JButton("修改");
	private JButton deleteBtn = new JButton("删除");

	private List<Staff> list = new ArrayList<>();
	private StaffTableModel model = new StaffTableModel(list);

	private void init() {
		JPanel searchPanel = new JPanel();
		searchText.setPreferredSize(new Dimension(100, 30));
		searchPanel.add(searchLabel);
		searchPanel.add(searchText);
		searchPanel.add(searchBtn);

		JScrollPane scrollPane = new JScrollPane(table);

		JPanel btnPanel = new JPanel();
		btnPanel.add(addBtn);
		btnPanel.add(editBtn);
		btnPanel.add(deleteBtn);

		contentPane.add(searchPanel, BorderLayout.NORTH);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		contentPane.add(btnPanel, BorderLayout.SOUTH);

		table.setModel(model);
		table.setAutoCreateRowSorter(true);
		table.setRowHeight(40);

		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				list.clear();
				list.addAll(mDao.getStaffsByName(searchText.getText()));
				model.fireTableDataChanged();
			}
		});

		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditDialog editDialog = new EditDialog(new Staff(), true);
				editDialog.setVisible(true);
				editDialog.setManageFrame(ManageFrame.this);
			}
		});
		editBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(ManageFrame.this, "未选中行");
					return;
				}
				EditDialog editDialog = new EditDialog(list.get(selectedRow), false);
				editDialog.setVisible(true);
				editDialog.setManageFrame(ManageFrame.this);
			}
		});
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(ManageFrame.this, "未选中行");
					return;
				}
				int result = JOptionPane.showConfirmDialog(ManageFrame.this, "是否确认删除？");
				if (result == 0) {
					mDao.deleteStaff(list.get(selectedRow).getId());
					updateTable();
				}
			}
		});
		updateTable();
	}

	public void updateTable() {
		list.clear();
		list.addAll(mDao.getStaffs());
		model.fireTableDataChanged();
	}
}
