-- MySQL dump 10.13  Distrib 5.5.28, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: ciescogetonit
-- ------------------------------------------------------
-- Server version	5.5.28-0ubuntu0.12.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `announcements`
--

DROP TABLE IF EXISTS `announcements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` text CHARACTER SET latin1,
  `starts_at` datetime DEFAULT NULL,
  `ends_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `updater_id` int(11) DEFAULT NULL,
  `internal` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcements`
--

LOCK TABLES `announcements` WRITE;
/*!40000 ALTER TABLE `announcements` DISABLE KEYS */;
/*!40000 ALTER TABLE `announcements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed_entries`
--

DROP TABLE IF EXISTS `feed_entries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feed_entries` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `feed_source_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `summary` text,
  `url` varchar(255) DEFAULT NULL,
  `published_at` datetime DEFAULT NULL,
  `guid` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `main_status` tinyint(1) DEFAULT '0',
  `status` int(2) DEFAULT '0',
  `reply_id` bigint(20) DEFAULT '0',
  `assign_to` bigint(20) DEFAULT '0',
  `deadline` date DEFAULT NULL,
  `follower` int(11) DEFAULT '0',
  `urgent_status` tinyint(1) DEFAULT '0',
  `last_view_date` datetime DEFAULT NULL,
  `viewer` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `index_feed_entries_on_feed_source_id` (`feed_source_id`),
  KEY `index_feed_entries_on_user_id` (`user_id`),
  KEY `index_feed_entries_on_published_at` (`published_at`),
  KEY `index_feed_entries_on_status` (`status`),
  KEY `index_feed_entries_on_published_at_and_feed_source_id` (`published_at`,`feed_source_id`),
  KEY `index_feed_entries_on_published_at_and_feed_source_id_and_status` (`published_at`,`feed_source_id`,`status`),
  KEY `index_feed_entries_on_st_source_id_user_id_created_at_assign_to` (`user_id`,`feed_source_id`,`status`,`created_at`,`assign_to`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed_entries`
--

LOCK TABLES `feed_entries` WRITE;
/*!40000 ALTER TABLE `feed_entries` DISABLE KEYS */;
/*!40000 ALTER TABLE `feed_entries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed_entries_attachments`
--

DROP TABLE IF EXISTS `feed_entries_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feed_entries_attachments` (
  `attachment_id` bigint(20) DEFAULT NULL,
  `feed_entry_id` bigint(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed_entries_attachments`
--

LOCK TABLES `feed_entries_attachments` WRITE;
/*!40000 ALTER TABLE `feed_entries_attachments` DISABLE KEYS */;
/*!40000 ALTER TABLE `feed_entries_attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed_entry_child`
--

DROP TABLE IF EXISTS `feed_entry_child`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feed_entry_child` (
  `parent_id` bigint(20) DEFAULT NULL,
  `feed_id` bigint(20) DEFAULT NULL,
  KEY `index_feed_id_parent_id` (`parent_id`,`feed_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed_entry_child`
--

LOCK TABLES `feed_entry_child` WRITE;
/*!40000 ALTER TABLE `feed_entry_child` DISABLE KEYS */;
/*!40000 ALTER TABLE `feed_entry_child` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed_sources`
--

DROP TABLE IF EXISTS `feed_sources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feed_sources` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `url` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `colour` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `source_type` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed_sources`
--

LOCK TABLES `feed_sources` WRITE;
/*!40000 ALTER TABLE `feed_sources` DISABLE KEYS */;
/*!40000 ALTER TABLE `feed_sources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed_sources_users`
--

DROP TABLE IF EXISTS `feed_sources_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feed_sources_users` (
  `feed_source_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed_sources_users`
--

LOCK TABLES `feed_sources_users` WRITE;
/*!40000 ALTER TABLE `feed_sources_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `feed_sources_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed_types`
--

DROP TABLE IF EXISTS `feed_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feed_types` (
  `id` tinyint(4) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed_types`
--

LOCK TABLES `feed_types` WRITE;
/*!40000 ALTER TABLE `feed_types` DISABLE KEYS */;
INSERT INTO `feed_types` VALUES (1,'rss'),(2,'custom'),(3,'auto');
/*!40000 ALTER TABLE `feed_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feeds_users`
--

DROP TABLE IF EXISTS `feeds_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feeds_users` (
  `user_id` int(11) NOT NULL DEFAULT '0',
  `cat_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`cat_id`),
  UNIQUE KEY `feeds_users_ix1` (`user_id`,`cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feeds_users`
--

LOCK TABLES `feeds_users` WRITE;
/*!40000 ALTER TABLE `feeds_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `feeds_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file_attachments`
--

DROP TABLE IF EXISTS `file_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file_attachments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `file_extension` varchar(10) DEFAULT NULL,
  `attachable_id` bigint(20) DEFAULT NULL,
  `attachable_type` varchar(255) DEFAULT NULL,
  `attachment_file_name` varchar(255) DEFAULT NULL,
  `attachment_content_type` varchar(255) DEFAULT NULL,
  `attachment_file_size` int(11) DEFAULT NULL,
  `attachment_updated_at` datetime DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL,
  `updater_id` bigint(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_attachments`
--

LOCK TABLES `file_attachments` WRITE;
/*!40000 ALTER TABLE `file_attachments` DISABLE KEYS */;
/*!40000 ALTER TABLE `file_attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regions`
--

DROP TABLE IF EXISTS `regions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regions` (
  `id` tinyint(4) NOT NULL,
  `region_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regions`
--

LOCK TABLES `regions` WRITE;
/*!40000 ALTER TABLE `regions` DISABLE KEYS */;
/*!40000 ALTER TABLE `regions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_users`
--

DROP TABLE IF EXISTS `roles_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_users` (
  `user_id` int(11) NOT NULL DEFAULT '0',
  `role_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_users`
--

LOCK TABLES `roles_users` WRITE;
/*!40000 ALTER TABLE `roles_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `id` tinyint(4) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Urgent'),(2,'On It'),(3,'Pending'),(4,'Done');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `profileImage` varchar(255) DEFAULT NULL,
  `crypted_password` varchar(255) NOT NULL,
  `password_salt` varchar(255) NOT NULL,
  `persistence_token` varchar(255) NOT NULL,
  `region_id` tinyint(4) NOT NULL,
  `job_title` varchar(255) DEFAULT NULL,
  `login_count` int(11) NOT NULL DEFAULT '0',
  `failed_login_count` int(11) NOT NULL DEFAULT '0',
  `last_request_at` datetime DEFAULT NULL,
  `current_login_at` datetime DEFAULT NULL,
  `last_login_at` datetime DEFAULT NULL,
  `current_login_ip` varchar(255) DEFAULT NULL,
  `last_login_ip` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `updater_id` int(11) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `user_logged_in` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-12-10 13:00:42
