create database library;

use library;

create table book (
	id int auto_increment primary key,
    title varchar(63) not null,
    author varchar(63),
    status double
);
insert into book values 
	(null, 'Война и мир', 'Лев Толстой', true),
	(null, '1984', 'Джордж Оруэлл', false),
	(null, 'Улисс', 'Джеймс Джойс', true),
	(null, 'Лолита', 'Вла­ди­мир Набо­ков', true),
	(null, 'Мид­дл­марч', 'Джордж Элиот', false),
	(null, 'Шум и ярость', 'Уильям Фолк­нер', true),
	(null, 'Неви­дим­ка', 'Ральф Элли­сон', true);