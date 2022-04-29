CREATE SCHEMA IF NOT EXISTS `wordoccurences`;

use `wordoccurences`;

CREATE TABLE IF NOT EXISTS `wordoccurences`.`word` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `word` VARCHAR(15),
        PRIMARY KEY(`id`)
    );

insert into `wordoccurences`.`word` (word) VALUES ('the');
insert into `wordoccurences`.`word` (word) VALUES ('test');
insert into `wordoccurences`.`word` (word) VALUES ('this');
insert into `wordoccurences`.`word` (word) VALUES ('a');

select * from `wordoccurences`.`word`;

