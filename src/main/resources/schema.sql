DROP TABLE IF EXISTS url_short_event CASCADE;


CREATE TABLE url_short_event(
    url_id BIGINT(20)  UNSIGNED NOT NULL PRIMARY KEY,
    url_text varchar(400),
    shorturl varchar(255),
    insert_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    expiry_date  timestamp NOT NULL
);
--CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1000 INCREMENT BY 1;

--ALTER TABLE url_short_event AUTO_INCREMENT=1000;

 drop sequence if exists hibernate_sequence;

 create sequence hibernate_sequence start with 10000 increment by 1;

 CREATE INDEX `url_short_url_text_index` ON `url_short_event` (`url_text`);

CREATE INDEX `url_short_url_text_shorturl_index` ON `url_short_event` (`url_text`,`shorturl`);

CREATE INDEX `url_short_event_shorturl_index` ON `url_short_event` (`shorturl`);

--ALTER TABLE `url_short_event` ADD INDEX url_short_url_text_index (`url_text`);
--ALTER TABLE `url_short_event` ADD INDEX url_short_url_text_shorturl_index (`url_text`,`shorturl`);
--ALTER TABLE `url_short_event` ADD INDEX url_short_event_shorturl_index (`shorturl`);

--DELIMITER |
--CREATE EVENT ttl_delete
 --       ON SCHEDULE EVERY 10 SECOND STARTS '2020-09-30 12:00:00' DISABLE
  --      DO BEGIN
   --             DELETE FROM url_short_event WHERE insert_date < NOW() - INTERVAL 1 DAY;
    --    END |
--DELIMITER ;