package com.zml.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.zml.core.dao.impl.BaseDaoImpl;
import com.zml.user.dao.IPostDao;
import com.zml.user.entity.Post;

@Repository("postDao")
public class PostDaoImpl extends BaseDaoImpl<Post> implements IPostDao {


}
