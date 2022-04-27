/*список продуктов, который покупал пользователь с именем переданным в параметре*/
select distinct product_name from orders o
join customers c on c.id = o.customer_id
where c.name ~* :name