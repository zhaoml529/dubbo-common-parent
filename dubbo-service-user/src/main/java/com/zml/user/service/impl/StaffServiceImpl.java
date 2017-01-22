package com.zml.user.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zml.user.dao.IStaffDao;
import com.zml.user.entity.Staff;
import com.zml.user.exceptions.StaffServiceException;
import com.zml.user.service.IStaffService;

@Service("staffService")
public class StaffServiceImpl implements IStaffService {

	@Autowired
	private IStaffDao staffDao;
	
	public Long addStaff(Staff staff) throws StaffServiceException {
		return this.staffDao.insert(staff);
	}

	public Long updateStaff(Staff staff) throws StaffServiceException {
		return this.staffDao.update(staff);
	}

	public List<Staff> findAll(Map<String, Object> map)
			throws StaffServiceException {
		List<Staff> list = this.staffDao.getList(map);
		if(list != null) {
			return list;
		} else {
			return Collections.emptyList();
		}
	}

}
