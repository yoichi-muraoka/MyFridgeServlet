-- DBの作成
CREATE DATABASE myfridge_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

-- テーブルの作成
USE myfridge_db;

CREATE TABLE items (
  id INT PRIMARY KEY AUTO_INCREMENT,
  exp_date DATE NOT NULL,
  name VARCHAR(30) NOT NULL
);

-- 初期データの投入
INSERT INTO items (exp_date, name) VALUES
('2021-09-27', '牛乳'),
('2021-10-04', 'チーズ'),
('2021-09-30', 'ヨーグルト'),
('2021-09-30', '納豆'),
('2021-09-29', 'もずく'),
('2021-10-03', '豚肉'),
('2021-09-30', 'たまご');

SELECT * FROM items ORDER BY exp_date;