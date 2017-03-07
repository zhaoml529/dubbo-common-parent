package com.zml.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.zml.core.dao.impl.BaseDaoImpl;
import com.zml.user.dao.IResourceDao;
import com.zml.user.entity.Resource;

@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements IResourceDao {


}
