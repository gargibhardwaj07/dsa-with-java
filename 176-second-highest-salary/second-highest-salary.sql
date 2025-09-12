# Write your MySQL query statement below
SELECT(Select DISTINCT salary 
from Employee 
ORDER BY salary DESC
LIMIT 1 OFFSET 1) AS SecondHighestSalary;
