create table mall_category
(
	id int auto_increment,
	parent_id int not null,
	category varchar(64) not null,
	sort_order int default 1 not null,
	status int default 1 not null,
	create_time datetime default CURRENT_TIMESTAMP null,
	update_time datetime default CURRENT_TIMESTAMP null,
	constraint mall_category_category_uindex
		unique (category),
	constraint mall_category_id_uindex
		unique (id)
);

alter table mall_category
	add primary key (id);

create definer = root@localhost trigger mall_category_trigger
	before update
	on mall_category
	for each row
	begin 
        set New.update_time = Now();
    end;

create table mall_dial
(
	id int auto_increment,
	color varchar(45) not null,
	create_time datetime default CURRENT_TIMESTAMP null,
	update_time datetime default CURRENT_TIMESTAMP null,
	constraint color_UNIQUE
		unique (color),
	constraint id_UNIQUE
		unique (id)
);

alter table mall_dial
	add primary key (id);

create definer = root@localhost trigger mall_dial_BEFORE_UPDATE
	before update
	on mall_dial
	for each row
	BEGIN
	Set NEW.update_time = NOW();
END;

create table mall_feature
(
	id int auto_increment,
	feature varchar(255) not null,
	create_time datetime default CURRENT_TIMESTAMP null,
	update_time datetime default CURRENT_TIMESTAMP null,
	constraint function_UNIQUE
		unique (feature),
	constraint id_UNIQUE
		unique (id)
);

alter table mall_feature
	add primary key (id);

create definer = root@localhost trigger mall_function_BEFORE_UPDATE
	before update
	on mall_feature
	for each row
	BEGIN
	Set New.update_time = Now();
END;

create table mall_product
(
	id int auto_increment
		primary key,
	category int not null,
	serial_num varchar(45) not null,
	price decimal(20,2) default 999999.99 not null,
	main_img varchar(255) not null,
	sub_img text not null,
	size decimal(3,1) not null,
	dial_color int not null,
	movement varchar(45) not null,
	product_desc text not null,
	status int default 1 not null,
	stock int not null,
	create_time datetime null,
	update_time datetime null,
	constraint serial_num_UNIQUE
		unique (serial_num),
	constraint mall_product_category_fk
		foreign key (category) references mall_category (id),
	constraint mall_product_dial_color_fk
		foreign key (dial_color) references mall_dial (id)
);

create index mall_product_dial_color_fk_idx
	on mall_product (dial_color);

create definer = root@localhost trigger mall_product_BEFORE_INSERT
	before insert
	on mall_product
	for each row
	BEGIN
    Set New.create_time = Now();
	Set New.update_time = Now();
END;

create definer = root@localhost trigger mall_product_BEFORE_UPDATE
	before update
	on mall_product
	for each row
	BEGIN
	Set New.update_time = Now();
END;

create table mall_product_feature
(
	id int auto_increment,
	product_id int not null,
	feature_id int not null,
	create_time datetime default CURRENT_TIMESTAMP null,
	update_time datetime default CURRENT_TIMESTAMP null,
	constraint id_UNIQUE
		unique (id),
	constraint mall_product_function_function_fk
		foreign key (feature_id) references mall_feature (id),
	constraint mall_product_function_product_fk
		foreign key (product_id) references mall_product (id)
);

create index mall_product_function_product_dk_idx
	on mall_product_feature (product_id);

create index mall_produdct_function_function_dk_idx
	on mall_product_feature (feature_id);

alter table mall_product_feature
	add primary key (id);

create definer = root@localhost trigger mall_product_function_BEFORE_UPDATE
	before update
	on mall_product_feature
	for each row
	BEGIN
	Set New.update_time = Now();
END;

create table mall_user
(
	id int auto_increment,
	username varchar(45) not null,
	email varchar(45) not null,
	password varchar(50) not null,
	phone varchar(20) null,
	role int default 0 not null,
	create_time datetime null,
	update_time datetime null,
	constraint email_UNIQUE
		unique (email),
	constraint id_UNIQUE
		unique (id)
);

alter table mall_user
	add primary key (id);

create table mall_shipping
(
	id int auto_increment
		primary key,
	user_id int not null,
	receiver_name varchar(20) not null,
	receiver_mobile varchar(20) not null,
	receiver_state varchar(20) not null,
	receiver_city varchar(20) not null,
	receiver_addr varchar(255) not null,
	receiver_apt varchar(20) null,
	receiver_zip varchar(5) not null,
	create_time datetime null,
	update_time varchar(45) null,
	constraint mall_shipping_user_id_fk
		foreign key (user_id) references mall_user (id)
);

create table mall_order
(
	id int auto_increment
		primary key,
	order_num bigint not null,
	user_id int not null,
	shipping_id int not null,
	payment decimal(20,2) default 0.00 not null,
	postage int default 0 not null,
	payment_time datetime null,
	shipped_time datetime null,
	received_time datetime null,
	status int default 10 null,
	create_time datetime default CURRENT_TIMESTAMP not null,
	update_time datetime default CURRENT_TIMESTAMP not null,
	closed_time datetime null,
	constraint order_num_UNIQUE
		unique (order_num),
	constraint mall_order_shipping_fk
		foreign key (shipping_id) references mall_shipping (id),
	constraint mall_order_user_fk
		foreign key (user_id) references mall_user (id)
);

create index mall_order_shipping_fk_idx
	on mall_order (shipping_id);

create index mall_order_user_fk_idx
	on mall_order (user_id);

create definer = root@localhost trigger mall_order_BEFORE_INSERT
	before insert
	on mall_order
	for each row
	BEGIN
	SET NEW.create_time = Now();
    SET NEW.update_time = Now();
END;

create definer = root@localhost trigger mall_order_BEFORE_UPDATE
	before update
	on mall_order
	for each row
	BEGIN
	SET NEW.update_time = Now();
END;

create table mall_order_item
(
	id int auto_increment
		primary key,
	user_id int not null,
	order_num bigint not null,
	product_id int not null,
	product_desc text null,
	main_img varchar(255) null,
	unit_price decimal(20,2) not null,
	quantity int default 1 not null,
	total_price decimal(20,2) default 0.00 not null,
	create_time datetime null,
	update_time datetime null,
	constraint mall_order_item_orderNum_fk
		foreign key (order_num) references mall_order (order_num),
	constraint mall_order_item_productId_fk
		foreign key (product_id) references mall_product (id),
	constraint mall_order_item_user_id_fk
		foreign key (user_id) references mall_user (id)
);

create definer = root@localhost trigger mall_order_item_BEFORE_INSERT
	before insert
	on mall_order_item
	for each row
	BEGIN
	SET New.create_time = Now();
	SET New.update_time = Now();
    SET New.total_price = New.quantity * New.unit_price;
END;

create definer = root@localhost trigger mall_order_item_BEFORE_UPDATE
	before update
	on mall_order_item
	for each row
	BEGIN
	SET NEW.update_time = Now();
    SET New.total_price = New.quantity * New.unit_price;
END;

create table mall_payment
(
	id int auto_increment
		primary key,
	user_id int not null,
	order_num bigint not null,
	amount decimal(20,2) not null,
	create_time datetime null,
	update_time datetime default CURRENT_TIMESTAMP null,
	constraint mall_payment_order_fk
		foreign key (order_num) references mall_order (order_num),
	constraint mall_payment_user_fk
		foreign key (user_id) references mall_user (id)
);

create index mall_payment_order_fk_idx
	on mall_payment (order_num);

create index mall_payment_user_fk_idx
	on mall_payment (user_id);

create definer = root@localhost trigger mall_payment_BEFORE_UPDATE
	before update
	on mall_payment
	for each row
	BEGIN
	Set NEW.update_time = Now();
END;

create index mall_shipping_user_id_fk_idx
	on mall_shipping (user_id);

create definer = root@localhost trigger mall_shipping_AFTER_UPDATE
	before update
	on mall_shipping
	for each row
	BEGIN
	Set NEW.update_time = Now();
END;

create definer = root@localhost trigger mall_shipping_BEFORE_INSERT
	before insert
	on mall_shipping
	for each row
	BEGIN
	Set New.create_time = Now();
    Set New.update_time = Now();
END;

create definer = root@localhost trigger mall_user_BEFORE_INSERT
	before insert
	on mall_user
	for each row
	BEGIN
	Set New.create_time = Now();
    Set New.update_time = Now();
    Set New.username = upper(New.username);
	Set New.password = upper(New.password);
END;

create definer = root@localhost trigger mall_user_BEFORE_UPDATE
	before update
	on mall_user
	for each row
	BEGIN
	Set NEW.update_time = Now();
	Set New.password = upper(New.password);
	Set New.username = upper(New.username);
END;

