package com.zml.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.zml.core.dao.impl.BaseDaoImpl;
import com.zml.user.dao.IDataPermissionDao;
import com.zml.user.entity.DataPermissions;

@Repository("dataPermissionDao")
public class DataPermissionDaoImpl extends BaseDaoImpl<DataPermissions>
		implements IDataPermissionDao {

}
