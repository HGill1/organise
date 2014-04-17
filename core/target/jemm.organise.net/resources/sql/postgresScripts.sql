CREATE TABLE IF NOT EXISTS announcements (
  id serial NOT NULL,
  message text,
  starts_at timestamp DEFAULT NULL,
  ends_at timestamp DEFAULT NULL,
  created_at timestamp DEFAULT NULL,
  updated_at timestamp DEFAULT NULL,
  creator_id bigint DEFAULT NULL,
  updater_id bigint DEFAULT NULL,
  internal boolean DEFAULT true,
  group_id bigint DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS feed_entries (
  id serial NOT NULL,
  feed_source_id bigint DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  author varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  summary text,
  url varchar(255) DEFAULT NULL,
  published_at timestamp DEFAULT NULL,
  guid varchar(255) DEFAULT NULL,
  created_at timestamp DEFAULT NULL,
  updated_at timestamp DEFAULT NULL,
  main_status boolean DEFAULT false,
  status int DEFAULT '0',
  reply_id bigint DEFAULT '0',
  assign_to bigint DEFAULT '0',
  last_view_date timestamp DEFAULT NULL,
  deadline timestamp DEFAULT NULL,
  follower int DEFAULT NULL,
  urgent_status boolean DEFAULT false,
  viewer int DEFAULT '0',
  feed_type int DEFAULT '0',
  updated_by bigint DEFAULT NULL,
  PRIMARY KEY (id)
);


	  CREATE INDEX index_feed_entries_on_feed_source_id
	   ON feed_entries (feed_source_id);
	  CREATE INDEX index_feed_entries_on_user_id
	   ON feed_entries (user_id); 
	  CREATE INDEX index_feed_entries_on_published_at
	   ON feed_entries (published_at);
	  CREATE INDEX index_feed_entries_on_status
	   ON feed_entries (status); 
	  CREATE INDEX index_feed_entries_on_published_at_and_feed_source_id
	   ON feed_entries (published_at,feed_source_id); 
	  CREATE INDEX index_feed_entries_on_published_at_and_feed_source_id_and_status
	   ON feed_entries (published_at,feed_source_id,status); 
	  CREATE INDEX index_feed_entries_on_st_source_id_user_id_created_at_assign_to
	   ON feed_entries (user_id,feed_source_id,status,created_at,assign_to); 
	  CREATE INDEX index_feed_entries_on_st_s_id_u_id_cd_at_assign_to_deadline
	   ON feed_entries (user_id,feed_source_id,status,created_at,assign_to,deadline); 
	   
	CREATE TABLE feed_entries_attachments (
	  attachment_id bigint DEFAULT NULL,
	  feed_entry_id bigint DEFAULT NULL,
	  created_at timestamp DEFAULT NULL,
	  updated_at timestamp DEFAULT NULL
	);
	
	CREATE TABLE feed_entry_child (
 	 parent_id bigint DEFAULT NULL,
  	feed_id bigint DEFAULT NULL
	);
	
	
CREATE TABLE feed_sources (
  id serial NOT NULL ,
  name varchar(255) DEFAULT NULL,
  url varchar(255) DEFAULT NULL,
  colour varchar(255) DEFAULT NULL,
  source_type int DEFAULT '0',
  created_at timestamp DEFAULT NULL,
  updated_at timestamp DEFAULT NULL,
  token varchar(255) DEFAULT NULL,
  group_id bigint DEFAULT NULL,
  parent_id bigint DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE feed_sources_users (
  feed_source_id int NOT NULL DEFAULT '0',
  user_id bigint NOT NULL DEFAULT '0',
  created_at timestamp DEFAULT NULL,
  updated_at timestamp DEFAULT NULL,
  PRIMARY KEY (feed_source_id,user_id)
);

CREATE TABLE feed_types (
  id int NOT NULL,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE feeds (
  id serial NOT NULL,
  name varchar(255) DEFAULT NULL,
  url varchar(255) DEFAULT NULL,
  colour varchar(255) DEFAULT NULL,
  source_type varchar(255) DEFAULT NULL,
  created_at timestamp DEFAULT NULL,
  updated_at timestamp DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE feeds_users (
  user_id int NOT NULL DEFAULT '0',
  cat_id int NOT NULL DEFAULT '0',
  PRIMARY KEY (user_id,cat_id)
 );
 
 CREATE TABLE file_attachments (
  id serial NOT NULL,
  name varchar(255) DEFAULT NULL,
  file_extension varchar(255) DEFAULT NULL,
  attachable_id bigint DEFAULT NULL,
  attachable_type varchar(255) DEFAULT NULL,
  attachment_file_name varchar(255) DEFAULT NULL,
  attachment_content_type varchar(255) DEFAULT NULL,
  attachment_file_size int DEFAULT NULL,
  attachment_updated_at timestamp DEFAULT NULL,
  creator_id bigint DEFAULT NULL,
  updater_id bigint DEFAULT NULL,
  created_at timestamp DEFAULT NULL,
  updated_at timestamp DEFAULT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE groups (
  id serial NOT NULL ,
  name varchar(255) DEFAULT NULL,
  created_by varchar(255) DEFAULT NULL,
  updated_by varchar(255) DEFAULT NULL,
  created_at timestamp DEFAULT NULL,
  updated_at timestamp DEFAULT NULL,
  package_id bigint DEFAULT '1',
  subscr_date timestamp DEFAULT NULL,
  PRIMARY KEY (id)
);
 
CREATE TABLE orders (
  id serial NOT NULL,
  amount varchar NOT NULL,
  item_name varchar(255) NOT NULL,
  item_number varchar(255) DEFAULT NULL,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  payer_email varchar(255) NOT NULL,
  payer_id varchar(255) NOT NULL,
  payer_status varchar(255) NOT NULL,
  period varchar(255) DEFAULT NULL,
  subscr_date timestamp DEFAULT NULL,
  subscr_id varchar(255) DEFAULT NULL,
  txn_type varchar(255) DEFAULT NULL,
  auth varchar(255) NOT NULL,
  group_id bigint NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE packages_platinum (
  id serial NOT NULL ,
  group_id bigint NOT NULL,
  users_allowed int DEFAULT '0',
  storage_allowed int DEFAULT '0',
  support_type int DEFAULT '0',
  created_at timestamp DEFAULT NULL,
  updated_at timestamp DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE packages_pricing (
  id serial NOT NULL ,
  package_name varchar(255) DEFAULT NULL,
  price int DEFAULT '0',
  users_allowed int DEFAULT '0',
  storage_allowed int DEFAULT '0',
  support_type int DEFAULT '0',
  created_at timestamp DEFAULT NULL,
  updated_at timestamp DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE packages_support (
  id serial NOT NULL ,
  support_desc varchar(255) DEFAULT NULL,
  created_at timestamp DEFAULT NULL,
  updated_at timestamp DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE persistent_logins (
  username varchar(255) DEFAULT NULL,
  series varchar(255) NOT NULL DEFAULT '',
  token varchar(255) DEFAULT NULL,
  last_used timestamp DEFAULT NULL,
  PRIMARY KEY (series)
);

CREATE TABLE regions (
  id serial NOT NULL ,
  region_name varchar(255) NOT NULL,
  group_id bigint DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE regions_announcements (
  region_id bigint NOT NULL DEFAULT '0',
  announcement_id bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (region_id,announcement_id)
);

CREATE TABLE roles (
  id serial NOT NULL ,
  name varchar(255) DEFAULT NULL,
  created_at timestamp DEFAULT NULL,
  updated_at timestamp DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE roles_users (
  user_id bigint NOT NULL DEFAULT '0',
  role_id int DEFAULT NULL,
  created_at timestamp DEFAULT NULL,
  updated_at timestamp DEFAULT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE status (
  id int NOT NULL,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users (
  id serial NOT NULL ,
  username varchar(255) DEFAULT NULL,
  email varchar(255) NOT NULL,
  profileImage varchar(255) DEFAULT NULL,
  crypted_password varchar(255) NOT NULL,
  password_salt varchar(255) NOT NULL,
  persistence_token varchar(255) NOT NULL,
  region_id bigint DEFAULT NULL,
  job_title varchar(255) DEFAULT NULL,
  login_count int NOT NULL DEFAULT '0',
  failed_login_count int NOT NULL DEFAULT '0',
  last_request_at timestamp DEFAULT NULL,
  current_login_at timestamp DEFAULT NULL,
  last_login_at timestamp DEFAULT NULL,
  current_login_ip varchar(255) DEFAULT NULL,
  last_login_ip varchar(255) DEFAULT NULL,
  created_at timestamp DEFAULT NULL,
  updated_at timestamp DEFAULT NULL,
  creator_id int DEFAULT NULL,
  updater_id int DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  user_logged_in int DEFAULT NULL,
  user_type bigint DEFAULT '0',
  group_id bigint DEFAULT NULL,
  task_add_email boolean DEFAULT true,
  task_update_email boolean DEFAULT true,
  PRIMARY KEY (id)
);

CREATE INDEX email_on_region_id
	   ON users (email,region_id);
CREATE INDEX email_on_region_id_group_id
	   ON users (email,region_id,group_id);
CREATE INDEX email_on_user_type
	   ON users (email,user_type);



