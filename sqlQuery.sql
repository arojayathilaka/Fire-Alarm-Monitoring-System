create database restdb;
use restdb;
create table sensor(id int primary key, isActive bool, location char(4), smokeLvl int, CO2Lvl int);

select * from restdb.sensor;
delete from restdb.sensor where id=3;
update  restdb.sensor set location='F2-R4' where id=2;


insert into sensor(id,location) values(20, 'F4-R4');

create table email(sendEmail bool primary key);
insert into email values(true);
select * from restdb.email;
update email set sendEmail=true;