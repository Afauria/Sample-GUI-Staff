package com.afauria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.afauria.DBHelper;
import com.afauria.entity.Staff;

public class StaffDao {
	public boolean checkLogin(String username, String passwd) {
		Connection conn = DBHelper.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select * from tb_staff where username=? and passwd=?");
			pst.setString(1, username);
			pst.setString(2, passwd);
			ResultSet result = pst.executeQuery();
			if (result.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Staff> getStaffs() {
		List<Staff> list = new ArrayList<>();
		Connection conn = DBHelper.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select * from tb_staff");
			ResultSet result = pst.executeQuery();
			while (result.next()) {
				Staff staff = new Staff(result.getInt("staff_id"), result.getString("username"),
						result.getString("passwd"), result.getString("name"), result.getString("phone"),
						result.getString("department"), result.getInt("gender"), result.getBoolean("is_manager"));
				list.add(staff);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Staff> getStaffsByName(String name) {
		List<Staff> list = new ArrayList<>();
		Connection conn = DBHelper.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("select * from tb_staff where name like ?");
			pst.setString(1, "%" + name + "%");
			ResultSet result = pst.executeQuery();
			while (result.next()) {
				Staff staff = new Staff(result.getInt("staff_id"), result.getString("username"),
						result.getString("passwd"), result.getString("name"), result.getString("phone"),
						result.getString("department"), result.getInt("gender"), result.getBoolean("is_manager"));
				list.add(staff);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean insertStaff(Staff staff) {
		Connection conn = DBHelper.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("INSERT INTO tb_staff(username, passwd, name, phone, department, gender, is_manager) VALUES(?, ?, ?, ?, ?, ?, ?)");
			pst.setString(1, staff.getUsername());
			pst.setString(2, staff.getPasswd());
			pst.setString(3, staff.getName());
			pst.setString(4, staff.getPhone());
			pst.setString(5, staff.getDepartment());
			pst.setInt(6, staff.getGender());
			pst.setBoolean(7, staff.isManager());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateStaff(Staff staff) {
		Connection conn = DBHelper.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("UPDATE tb_staff set passwd=?, name=?, phone=?, department=?, gender=?, is_manager=? where staff_id=?");
			pst.setString(1, staff.getPasswd());
			pst.setString(2, staff.getName());
			pst.setString(3, staff.getPhone());
			pst.setString(4, staff.getDepartment());
			pst.setInt(5, staff.getGender());
			pst.setBoolean(6, staff.isManager());
			pst.setInt(7, staff.getId());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteStaff(int id) {
		Connection conn = DBHelper.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement("delete from tb_staff where staff_id=?");
			pst.setInt(1, id);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
}
