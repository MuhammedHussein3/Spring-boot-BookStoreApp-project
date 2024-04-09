create table order_item (
   id bigint not null auto_increment,
   quantity integer not null,
   order_id bigint,
   product_id bigint,
   primary key (id)
);

create table orders (
   id bigint not null auto_increment,
   primary key (id)
);

alter table order_item
    add constraint FKt4dc2r9nbvbujrljv3e23iibt
        foreign key (order_id)
            references orders (id);
alter table order_item
    add constraint FKc5uhmwioq5kscilyuchp4w49o
        foreign key (product_id)
            references products (id)