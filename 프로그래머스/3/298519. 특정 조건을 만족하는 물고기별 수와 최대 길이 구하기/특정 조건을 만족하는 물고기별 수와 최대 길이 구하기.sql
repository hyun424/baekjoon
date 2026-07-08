-- 코드를 작성해주세요
SELECT 
    count(*) as FISH_COUNT,
    max(f.length) as MAX_LENGTH,
    f.FISH_TYPE
FROM FISH_INFO F
group by f.fish_type
having avg(ifnull(length, 10)) >= 33
order by fish_type asc;