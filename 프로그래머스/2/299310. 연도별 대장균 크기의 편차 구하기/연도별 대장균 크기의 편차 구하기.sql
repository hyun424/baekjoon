SELECT
  YEAR(e.differentiation_date) AS YEAR,
  (y.max_size - e.size_of_colony) AS YEAR_DEV,
  e.id AS ID
FROM ecoli_data e
JOIN (
  SELECT
    YEAR(differentiation_date) AS y,
    MAX(size_of_colony) AS max_size
  FROM ecoli_data
  GROUP BY YEAR(differentiation_date)
) y
  ON y.y = YEAR(e.differentiation_date)
ORDER BY
  YEAR ASC,
  YEAR_DEV ASC;
