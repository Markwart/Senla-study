-- task 1
select model, speed, hd from pc where price < 500;

-- task 2
select maker from product where type = 'printer' group by maker;

-- task 3
select model, ram, screen from laptop where price > 1000;

-- task 4
select * from printer where color = 'y';

-- task 5
select model, speed, hd from pc where (cd = '12x' or cd='24x') and price < 600;

-- task 6
select distinct product.maker, laptop.speed from laptop inner join product on product.model = laptop.model where laptop.hd >= 100;

-- task 7
select pc.model, pc.price from pc inner join product on product.model = pc.model where product.maker = 'Sony' 
union select laptop.model, laptop.price from laptop inner join product on product.model = laptop.model where product.maker = 'Sony'
union select printer.model, printer.price from printer inner join product on product.model = printer.model where product.maker = 'Sony';

-- task 8
select distinct maker from product where type = 'pc' and maker not in (select maker from product where type = 'laptop'); 

-- task 9
select distinct maker from pc inner join product on pc.model = product.model where pc.speed >= 450;

-- task 10
select model, price from printer where price = (select max(price) from printer);

-- task 11
select avg(speed) from pc;

-- task 12
select avg(speed) from laptop where price > 1000;

-- task 13
select avg(pc.speed) from pc inner join product on product.model = pc.model where product.maker = 'Asus';

-- task 14
select speed, avg(price) from pc group by speed;

-- task 15
select hd from pc where hd group by(hd) having count(model) >= 2;

-- task 16
select distinct m1.model, m2.model, m1.speed, m1.ram from pc m1, pc m2 where m1.speed = m2.speed and m1.ram = m2.ram and m1.model > m2.model;

-- task 17
select product.type, product.model, laptop.speed from laptop inner join product on product.model = laptop.model 
where laptop.speed < (select min(speed) from pc);

-- task 18
select distinct product.maker, printer.price from printer inner join product on product.model = printer.model 
where printer.color = 'y' and printer.price = ( select min(price) from printer where color = 'y');

-- task 19
select product.maker, avg(laptop.screen) from laptop inner join product on product.model = laptop.model group by product.maker;  

-- task 20
select maker, count(model) from product where type = 'pc' group by maker having count(distinct model) >= 3;

-- task 21
select product.maker, max(pc.price) from pc inner join product on product.model = pc.model group by product.maker;

-- task 22
select speed, avg(price) from pc where speed > 600 group by speed;

-- task 23
select distinct maker from pc inner join product on product.model = pc.model where pc.speed >= 750 
and product.maker in (select maker from laptop inner join product on product.model = laptop.model where laptop.speed >= 750);

-- task 24
select model from (select model, price from pc union select model, price from laptop union select model, price from printer) as m 
where price = (select max(price) from (select price from pc union select price from laptop union select price from printer) as pr);

-- task 25
select distinct maker from pc inner join product on product.model = pc.model 
where maker in (select maker from product where type = 'printer') 
and pc.ram = (select min(ram) from pc) 
and pc.speed = (select max(speed) from pc where ram = (select min(ram) from pc));  

 

