create table member(
    name varchar(10) not null,
    nickname varchar(10) not null,
    email varchar(50) not null,
    gender char(1) not null check(gender in('M','F')),
    id varchar(20) primary key,
    pw char(60) not null,
    point int default 0,
    ranked char(10) default '소식가' check(ranked in('소식가','미식가','식신','관리자')),
    reg_dt datetime default now()
);

create table board(
    no int unsigned auto_increment primary key,
    store varchar(30) not null,
    title varchar(30) not null,
    content varchar(1000) not null,
    id varchar(20) not null,
    nickname varchar(10) default '익명',
    readcount int default 0,
    picture varchar(1000),
    reg_dt datetime default now(),
    star int(1) not null,
    category varchar(4) not null,
    mapX decimal(20,16) not null,
    mapy double(20,16) not null,
	foreign key(id) references member(id) on delete cascade
);

create table favorite(
    no int unsigned,
    id varchar(20),
    reg_dt datetime default now(),
    primary key(id, no),
    foreign key(no) references board(no),
    foreign key(id) references member(id)
);

create table log(
    no int unsigned auto_increment primary key,
    id varchar(20),
    log varchar(10) not null check(log in ('로그인','로그아웃')),
    reg_dt datetime default now(),
    attendance boolean default 0,
    foreign key(id) references member(id) on delete cascade
);

create table reple(
    no int unsigned auto_increment primary key,
    boardno int unsigned,
    id varchar(20),
    nickname varchar(10) not null,
    reple varchar(500) not null,
    reg_dt datetime default now(),
    star int(1),
    foreign key(boardno) references board(no) on delete cascade,
    foreign key(id) references member(id)
);

create table manager(
    code char(8) not null unique
);
insert into manager values(left(uuid(),8));