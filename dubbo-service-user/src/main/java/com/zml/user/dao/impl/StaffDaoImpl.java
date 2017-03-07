package com.zml.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.zml.core.dao.impl.BaseDaoImpl;
import com.zml.user.dao.IStaffDao;
import com.zml.user.entity.Staff;

@Repository("staffDao")
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements IStaffDao {


}
