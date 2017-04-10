package com.zml.common.web.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zml.common.constant.CacheConstant;
import com.zml.common.utils.cache.redis.RedisUtil;
import com.zml.user.entity.DataPermissions;
import com.zml.user.service.IDataPermissionService;
/**
 * 初始化数据权限
 * @author zhaomingliang
 * @date 2017年4月10日
 */
@Component
public class InitDataPermissions {
	
	private  static final Logger logger = LoggerFactory.getLogger(InitDataPermissions.class);
	
	@Autowired
	private RedisUtil<Object> redisUtil;
	
	@Autowired
	private IDataPermissionService dataPermissionService;
	
	@PostConstruct
	public void init() {
		Map<Object, Object> dpmap = this.redisUtil.getCacheMap(CacheConstant.DATA_PERMISSION_KEY);
		//List<DataPermissions> list = this.redisUtil.lrange(CacheConstant.DATA_PERMISSION_KEY, 0, -1);
		if(dpmap == null || dpmap.isEmpty()) {
			List<DataPermissions> list = this.dataPermissionService.getDataPermissionList();
			if(!CollectionUtils.isEmpty(list)) {
				Map<String, Object> map = new HashMap<String, Object>();
				for(DataPermissions dp : list) {
					map.put(dp.getId().toString(), dp);
				}
				this.redisUtil.setCacheMap(CacheConstant.DATA_PERMISSION_KEY, map);
				this.redisUtil.persist(CacheConstant.DATA_PERMISSION_KEY);
				logger.info("==================初始化数据权限列表完成==================");
			} else {
				logger.info("******************没有找到数据权限数据信息******************");
			}
		}
	}
}
