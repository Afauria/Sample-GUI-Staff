package com.afauria.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.afauria.entity.Staff;

public class StaffTableModel extends AbstractTableModel {
	private List<Staff> list;
	private String[] columnNames = { "用户名", "密码", "名称", "手机号", "部门", "性别", "管理员" };

	public StaffTableModel(List<Staff> list) {
		super();
		this.list = list;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Staff staff = list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return staff.getUsername();
		case 1:
			return staff.getPasswd();
		case 2:
			return staff.getName();
		case 3:
			return staff.getPhone();
		case 4:
			return staff.getDepartment();
		case 5:
			return staff.getGender() == 0 ? "男" : "女";
		case 6:
			return staff.isManager() ? "是" : "否";
		}
		return "";
	}

}
