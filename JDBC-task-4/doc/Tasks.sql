-- task 1
select * from pc where price < 500;

-- task 2
select distinct maker from pc inner join product on product.model = pc.model where pc.speed >= 750 
and product.maker in (select maker from laptop inner join product on product.model = laptop.model where laptop.speed >= 750);

-- task 3
select speed, avg(price) from pc group by speed;

-- task 4
select maker from product where type = 'printer' group by maker;


 

