-- 코드를 작성해주세요
select c.ID
from ecoli_data a
left join ecoli_data b
    on a.id = b.parent_id
    left join ecoli_data c 
        on b.id = c.parent_id
where a.parent_id is null
    and c.id is not null
order by id asc;
