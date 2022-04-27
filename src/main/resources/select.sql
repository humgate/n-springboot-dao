select product_name, date, amount, name, surname from orders o
join customers c on c.id = o.customer_id
where c.name ~* 'alexey'