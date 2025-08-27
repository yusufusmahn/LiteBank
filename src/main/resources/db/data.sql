truncate table account cascade;
truncate table transaction cascade;

insert into account (id, account_number, username) values(
    '12345', '0123456789', 'becaw65821@chaublog.com');


insert into transaction(id, amount, account_number, transaction_type) values
        ('100',20000,'0123456789',0),
        ('101', 20000,'0123456789', 1),
        ('102', 30000,'0123456789', 1),
        ('103', 200000,'0123456789', 0),
        ('104', 200000,'0123456789', 0),
        ('105', 200000,'0123456787', 1),
        ('106', 200000,'0123456786', 0);