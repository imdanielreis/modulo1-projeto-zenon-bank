create table transactions (
      id bigint auto_increment primary key,
      step int not null,
      type varchar(30) not null,
      amount decimal(20,2) not null,
      name_orig varchar(50) not null,
      old_balance_org decimal(20,2) not null,
      new_balance_orig decimal(20,2) not null,
      name_dest varchar(50) not null,
      old_balance_dest decimal(20,2) not null,
      new_balance_dest decimal(20,2) not null,
      is_fraud tinyint(1) default 0,
      is_flagged_fraud tinyint(1) default 0
);