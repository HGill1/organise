/**
 * 
 */
package com.jemmmedia.organise.service.impl.service;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Announcements;
import com.jemmmedia.organise.mybatis.db.mybatis.model.RegionsAnnouncementsKey;

/**
 * @author harjinder
 *
 */
 public interface AnnouncementService {
	int insertSelective(Announcements announcement);

	void insertOrUpdateSelective(RegionsAnnouncementsKey key);

	String selectAnnouncementsBetweenDates(long groupId, long regionId);

}
