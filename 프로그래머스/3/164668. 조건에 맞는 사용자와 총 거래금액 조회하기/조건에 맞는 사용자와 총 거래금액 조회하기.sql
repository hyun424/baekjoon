-- 코드를 입력하세요
SELECT
    u.USER_ID,
    u.NICKNAME,
    sum(b.price) as TOTAL_SALES
FROM USED_GOODS_USER U
join USED_GOODS_BOARD B
    on u.user_id = b.writer_id
WHERE STATUS = 'DONE'
GROUP BY b.WRITER_ID 
HAVING SUM(b.PRICE) >= 700000
order by total_sales asc;
