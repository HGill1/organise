package com.jemmmedia.organise.service.impl.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.FeedTypesMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.FeedTypes;
import com.jemmmedia.organise.service.impl.dao.FeedTypesDao;

@Service("feedTypesDao")
public class FeedTypesDaoImpl implements FeedTypesDao {

	@Resource
	FeedTypesMapper feedMapper;
	
	@Override
	public List<FeedTypes> selectByEample() {
		return feedMapper.selectByExample(null);
	}

}
