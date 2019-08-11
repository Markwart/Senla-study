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
select distinct product.maker, laptop.speed from product, laptop where laptop.hd >= 100;

-- task 7
select product.model, pc.price from product, pc where product.model = pc.model and product.maker like 'S%' 
union select product.model, laptop.price from product, laptop where product.model = laptop.model and product.maker like 'S%'
union select product.model, printer.price from product, printer where product.model = printer.model and product.maker like 'S%';

-- task 8
select distinct maker from product where type not in (select type from product where type != 'laptop'); 

-- task 9
select distinct product.maker from product, pc where pc.model = product.model and pc.speed >= 450; 

-- task 10
select model, price from printer where price = (select max(price) from printer);

-- task 11
select avg(speed) from pc;

-- task 12
select avg(speed) from laptop where price > 1000;

-- task 13
select avg(pc.speed) from product, pc where product.model = pc.model and product.maker like 'A%';

-- task 14
select speed, avg(price) from pc group by speed;

-- task 15
select hd from pc where hd group by(hd) having count(model) >= 2;

-- task 16
select distinct m1.model, m2.model, m1.speed, m1.ram from pc m1, pc m2 where m1.speed = m2.speed and m1.ram = m2.ram and m1.model > m2.model;

-- task 17
select product.type, product.model, laptop.speed from product, laptop where product.model = laptop.model 
and laptop.speed < (select min(speed) from pc);

-- task 18
select distinct product.maker, printer.price from product, printer where product.model = printer.model and printer.color = 'y' 
and printer.price = ( select min(price) from printer where color = 'y');

-- task 19
select product.maker, avg(laptop.screen) from product, laptop where product.model = laptop.model group by product.maker; 

-- task 20
select maker, count(model) from product where type = 'pc' group by maker having count(distinct model) >= 3;

-- task 21
select product.maker, max(pc.price) from product, pc where product.model = pc.model group by product.maker;

-- task 22
select speed, avg(price) from pc where speed > 600 group by speed;

-- task 23
select distinct maker from product, pc, laptop where pc.speed >= 750 
and product.maker in (select maker from product where product.model = laptop.model and laptop.speed >= 750);

-- task 24
select model from (select model, price from pc union select model, price from laptop union select model, price from printer) as m 
where price = (select max(price) from (select price from pc union select price from laptop union select price from printer) as pr);

-- task 25
select distinct maker from product, pc where product.model = pc.model 
and maker in (select maker from product where type = 'printer') 
and pc.ram = (select min(ram) from pc) 
and pc.speed = (select max(speed) from pc where ram = (select min(ram) from pc)); 

 

