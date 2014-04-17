/**
 * 
 */
package com.jemmmedia.organise.service.impl.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Announcements;
import com.jemmmedia.organise.mybatis.db.mybatis.model.RegionsAnnouncementsKey;
import com.jemmmedia.organise.service.impl.dao.AnnouncementDao;
import com.jemmmedia.organise.service.impl.service.AnnouncementService;

/**
 * @author harjinder
 * 
 */
@Service("announcementService")
public class AnnouncementServiceImpl implements AnnouncementService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jemmmedia.organise.service.impl.service.AnnouncementService#insertSelective(com
	 * .jemmmedia.journal2.db.mybatis.model.Announcements)
	 */
	@Resource(name = "announcementDao")
	AnnouncementDao announcementDao;

	@Override
	public int insertSelective(Announcements announcement) {
		int count = 0;
		Date date = new Date();
		try {
			announcement.setCreatedAt(date);
			count = announcementDao.insertSelective(announcement);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;

	}
	
	@Override
	public void insertOrUpdateSelective(RegionsAnnouncementsKey key) {
		try {
			announcementDao.insertOrUpdateSelective(key);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String selectAnnouncementsBetweenDates(long groupId,long regionId) {
		List<Announcements> announcements = null;
		try {
			announcements = announcementDao
					.selectAnnouncementsBetweenDates(groupId,regionId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		StringBuilder msg = new StringBuilder();
		if (announcements != null) {
			for (Announcements a : announcements) {
				msg.append(a.getMessage() + " *** ");
			}
		}
		return msg.toString();
	}

}
