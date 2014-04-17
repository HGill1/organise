/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.dao.AnnouncementsMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.dao.RegionsAnnouncementsMapper;
import com.jemmmedia.organise.mybatis.db.mybatis.model.Announcements;
import com.jemmmedia.organise.mybatis.db.mybatis.model.RegionsAnnouncementsKey;
import com.jemmmedia.organise.service.impl.dao.AnnouncementDao;

/**
 * @author harjinder
 *
 */
@Service("announcementDao")
public class AnnouncementDaoImpl implements AnnouncementDao {

	@Resource
	AnnouncementsMapper announcementsMapper;
	
	@Resource
	RegionsAnnouncementsMapper regionsAnnouncementsMapper;
	
	
	/* (non-Javadoc)
	 * @see com.jemmmedia.organise.service.impl.dao.AnnouncementDao#insertSelective(com.jemmmedia.organise.service.impl.db.mybatis.model.Announcements)
	 */
	@Override
	public int insertSelective(Announcements announcement) {
		return announcementsMapper.insertSelective(announcement);

	}
	
	@Override
	public void insertOrUpdateSelective(RegionsAnnouncementsKey key) {
		regionsAnnouncementsMapper.insertOrUpdateSelective(key);

	}
	
	@Override
	public List<Announcements> selectAnnouncementsBetweenDates(long groupId, long regionId) {
		return announcementsMapper.selectAnnouncementsBetweenDates(groupId,regionId);

	}
	
	

}
