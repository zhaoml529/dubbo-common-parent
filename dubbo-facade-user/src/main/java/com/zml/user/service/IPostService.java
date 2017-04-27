package com.zml.user.service;

import com.zml.common.page.Page;
import com.zml.common.page.Parameter;
import com.zml.user.entity.Post;
import com.zml.user.exceptions.PostServiceException;

/**
 * 
 * 
 * @author zml
 * @email zhaoml529@gmail.com
 * @date 2017-04-18 08:38:39
 */
public interface IPostService {
	
	public Post getById(Long id) throws PostServiceException;
	
	public Page getListPage(Parameter<Post> param) throws PostServiceException;
	
	public Long save(Post post) throws PostServiceException;
	
	public void update(Post post) throws PostServiceException;
	
	public void delete(Long id) throws PostServiceException;
	
}
