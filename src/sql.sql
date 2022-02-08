create database BMS

create table usr(
userid int not null primary key identity(1,1),
uname varchar(50) not null,
user_type int not null, --1 for admin 2 for front-desk  3 for Reader or data encoder
account_status int default 1 , --1 for active 0 for inactive
passwd varchar(50)
)

create procedure insert_usr @nameh varchar(50), @uty int, @passh varchar(50) 
as
insert into usr(uname,user_type,passwd)
values(@nameh,@uty,@passh)


create procedure ver_usr @nameh varchar(50), @uty int, @passh varchar(50) 
as
select * from usr 
where uname = @nameh and
user_type = @uty and
passwd = @passh


create table customer(
id int NOT NULL  PRIMARY KEY Identity(1,1),
uname varchar(50) unique not null,
full_name varchar(50) NOT NULL,
phone_num varchar(50) NOT NULL,
passwd varchar(50),
account_status int default 1 --1 for active 0 for inactive
)


create procedure insert_cust @nameh varchar(50),@fnameh varchar(50),@phn varchar(50),@pss varchar(50)
as
insert into customer(uname,full_name,phone_num,passwd)
values(@nameh,@fnameh,@phn,@pss)

create procedure ver_cust @nameh varchar(50), @passh varchar(50)
as
select * from customer 
where uname = @nameh and
passwd = @passh

create procedure deac @idd int
as
update customer
set account_status = 0
where id = @idd;

create procedure acti @idd int
as
update customer
set account_status = 1
where id = @idd


create table bill(
id int NOT NULL foreign key references customer(id)  on delete cascade on update cascade,
bill_id int NOT NULL PRIMARY KEY IDENTITY(1,1),
service_type int NOT NULL,--1 for electricity 2 for water 3 for landline
pmonth int not null,
usage float not null,
rate float,
amount float,
)



create procedure insbill @id int,@sty int,@mnth int,@usg float,@rate float,@amnt float
as
insert into bill(id,service_type,pmonth,usage,rate,amount)
values(@id,@sty,@mnth,@usg,@rate,@amnt)

create table gen_bill(
id int not null foreign key references customer(id)  on delete cascade on update cascade ,
genbill_id int not null primary key identity(1,1),
p_date date not null default current_timestamp,
paid bit default 0, -- 1 for true 0 for false --
pmon int,
totalamount float
)

create procedure pmst @id int
as
update gen_bill
set paid = 1
where id = @id 

create procedure insgenb @id int,@mn int ,@ttam float
as
insert into gen_bill(id,pmon,totalamount)
values(@id,@mn,@ttam)

create Procedure calsi @id int, @st int, @pmo int
as	
select amount from bill
where id = @id and
service_type = @st and
pmonth = @pmo;


create procedure frfd @idd int
as
select customer.full_name,customer.id,gen_bill.totalamount,gen_bill.paid
from customer
cross join gen_bill
where customer.id = @idd and gen_bill.id= @idd



