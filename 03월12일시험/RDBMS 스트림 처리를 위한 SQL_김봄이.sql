-- 1
CREATE TABLE MY_MEMBER(
	MEMBER_CODE INT AUTO_INCREMENT PRIMARY KEY
	, MEMBER_ID VARCHAR(10) NOT NULL
	, MEMBER_PW VARCHAR(20) NOT NULL
	, MEMBER_NAME VARCHAR(10) NOT NULL
	, MEMBER_AGE INT
	, MEMBER_EMAIL VARCHAR(20)
);

-- 2
INSERT INTO my_member (
	MEMBER_CODE
	, MEMBER_ID
	, MEMBER_PW
	, MEMBER_NAME
) VALUES (
	1
	, 'SQL'
	, '1'
	, '선새니'
);

DELETE FROM my_member;

-- 3
UPDATE my_member
SET
	MEMBER_NAME = '김자바'
	, MEMBER_ID = 'KIMJAVA'
WHERE MEMBER_CODE = 1;

-- 4
SELECT
	EMPNO
	, ENAME
	, SAL
	, COMM
FROM emp
WHERE SAL >= 500 AND SAL <= 3000 AND COMM IS NOT NULL;

-- 5
SELECT
	EMPNO
	, ENAME
	, HIREDATE
FROM emp
WHERE ENAME LIKE '__기' OR ENAME LIKE '%김%'
ORDER BY EMPNO DESC;

-- 6
SELECT
	EMPNO
	, ENAME
	, DEPTNO
	, CASE
		WHEN DEPTNO = 10 THEN '인사부'
		WHEN DEPTNO = 20 THEN '영업부'
		WHEN DEPTNO = 30 THEN '개발부'
		ELSE '생산부'
		END AS DNAME
FROM emp;

-- 7
SELECT
	EMPNO
	, ENAME
	, HIREDATE
	, IFNULL(COMM, 0)
FROM emp
WHERE DATE_FORMAT(HIREDATE, '%m') = '01'
ORDER BY HIREDATE;

-- 8
SELECT
	DEPTNO
	, SUM(SAL)
	, AVG(SAL)
	, AVG(COMM)
FROM emp
GROUP BY DEPTNO
ORDER BY SUM(SAL) DESC;

-- 9
SELECT
	EMPNO
	, ENAME
	, HIREDATE
	, SAL
	, DEPTNO
	, (SELECT DNAME FROM dept WHERE DEPTNO = emp.DEPTNO) AS DNAME
FROM emp
WHERE (SELECT DEPTNO FROM dept WHERE DNAME = '인사부');

-- 10
SELECT
	EMPNO
	, ENAME
	, HIREDATE
	, SAL
	, E.DEPTNO
	, DNAME
FROM emp E
INNER JOIN dept D
ON E.DEPTNO = D.DEPTNO
WHERE DNAME != '인사부' AND SAL >= 500
ORDER BY EMPNO DESC, ENAME;