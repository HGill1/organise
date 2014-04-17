/**
 * 
 */
package com.jemmmedia.organise.service.impl.dao;

import java.util.List;

import com.jemmmedia.organise.mybatis.db.mybatis.model.Announcements;
import com.jemmmedia.organise.mybatis.db.mybatis.model.RegionsAnnouncementsKey;

/**
 * @author harjinder
 *
 */
public interface AnnouncementDao {
	
	int insertSelective(Announcements announcement);

	void insertOrUpdateSelective(RegionsAnnouncementsKey key);

	List<Announcements> selectAnnouncementsBetweenDates(long groupId,
			long regionId);

}
