-- 코드를 입력하세요
SELECT BOOK_ID, 
    DATE_FORMAT(PUBLISHED_DATE, '%Y-%m-%d') AS PUBLISHED_DATE
from book
where category like '인문'
and year(published_date) = 2021
order by published_date asc;