package com.jemmmedia.organise.mybatis.db.mybatis.model;

import java.util.Date;

public class FeedEntriesAttachments {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feed_entries_attachments.attachment_id
     *
     * @mbggenerated Wed Aug 22 22:11:02 BST 2012
     */
    private Long attachmentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feed_entries_attachments.feed_entry_id
     *
     * @mbggenerated Wed Aug 22 22:11:02 BST 2012
     */
    private Long feedEntryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feed_entries_attachments.created_at
     *
     * @mbggenerated Wed Aug 22 22:11:02 BST 2012
     */
    private Date createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feed_entries_attachments.updated_at
     *
     * @mbggenerated Wed Aug 22 22:11:02 BST 2012
     */
    private Date updatedAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feed_entries_attachments.attachment_id
     *
     * @return the value of feed_entries_attachments.attachment_id
     *
     * @mbggenerated Wed Aug 22 22:11:02 BST 2012
     */
    public Long getAttachmentId() {
        return attachmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feed_entries_attachments.attachment_id
     *
     * @param attachmentId the value for feed_entries_attachments.attachment_id
     *
     * @mbggenerated Wed Aug 22 22:11:02 BST 2012
     */
    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feed_entries_attachments.feed_entry_id
     *
     * @return the value of feed_entries_attachments.feed_entry_id
     *
     * @mbggenerated Wed Aug 22 22:11:02 BST 2012
     */
    public Long getFeedEntryId() {
        return feedEntryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feed_entries_attachments.feed_entry_id
     *
     * @param feedEntryId the value for feed_entries_attachments.feed_entry_id
     *
     * @mbggenerated Wed Aug 22 22:11:02 BST 2012
     */
    public void setFeedEntryId(Long feedEntryId) {
        this.feedEntryId = feedEntryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feed_entries_attachments.created_at
     *
     * @return the value of feed_entries_attachments.created_at
     *
     * @mbggenerated Wed Aug 22 22:11:02 BST 2012
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feed_entries_attachments.created_at
     *
     * @param createdAt the value for feed_entries_attachments.created_at
     *
     * @mbggenerated Wed Aug 22 22:11:02 BST 2012
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feed_entries_attachments.updated_at
     *
     * @return the value of feed_entries_attachments.updated_at
     *
     * @mbggenerated Wed Aug 22 22:11:02 BST 2012
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feed_entries_attachments.updated_at
     *
     * @param updatedAt the value for feed_entries_attachments.updated_at
     *
     * @mbggenerated Wed Aug 22 22:11:02 BST 2012
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}