CREATE DATABASE IF NOT EXISTS nsdra;

USE nsdra;

CREATE TABLE IF NOT EXISTS nsdra_submission(
	submission_id mediumint unsigned NOT NULL AUTO_INCREMENT, 
	submission_uuid VARCHAR(48) NOT NULL,
	submission_url VARCHAR(1024) NOT NULL,
	submission_timestamp VARCHAR(48) NOT NULL,
	submission_scrape_type VARCHAR(48) NOT NULL,
	submission_status VARCHAR(48) NOT NULL,
	PRIMARY KEY (`submission_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS nsdra_mi_assessment(
	mi_assessment_id mediumint unsigned NOT NULL AUTO_INCREMENT, 
	mi_id VARCHAR(256) NOT NULL,
	mi_url VARCHAR(1024) NOT NULL,
	mi_title VARCHAR(256) NOT NULL,
	mi_variable VARCHAR(256) NOT NULL,
	mi_comment VARCHAR(256),
	mi_status VARCHAR(256) NOT NULL,
	mi_list_id VARCHAR(256) NOT NULL,
	mi_list_title VARCHAR(256) NOT NULL,
	mi_list_reference_url VARCHAR(256) NOT NULL,
	submission_id mediumint unsigned NOT NULL,
	PRIMARY KEY (`mi_assessment_id`),
	FOREIGN KEY (`submission_id`) REFERENCES `nsdra_submission`(`submission_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



