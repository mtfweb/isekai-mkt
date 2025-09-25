-- DB 생성
CREATE DATABASE IF NOT EXISTS bookshop01
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_0900_ai_ci;

USE bookshop01;

-- ======================
-- 회원 테이블
-- ======================
DROP TABLE IF EXISTS t_shopping_member;
CREATE TABLE t_shopping_member (
    member_id      VARCHAR(50) NOT NULL PRIMARY KEY,
    member_pw      VARCHAR(100) NOT NULL,
    member_name    VARCHAR(100) NOT NULL,
    member_email   VARCHAR(100),
    joinDate       DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 샘플 회원
INSERT INTO t_shopping_member (member_id, member_pw, member_name, member_email)
VALUES
('admin', '1234', '관리자', 'admin@shop.com'),
('user1', '1111', '홍길동', 'hong@test.com');

-- ======================
-- 상품 테이블
-- ======================
DROP TABLE IF EXISTS t_shopping_goods;
CREATE TABLE t_shopping_goods (
    goods_id          INT AUTO_INCREMENT PRIMARY KEY,
    goods_sort        VARCHAR(50) NOT NULL,
    goods_title       VARCHAR(200) NOT NULL,
    goods_price       INT NOT NULL,
    goods_sales_price INT NOT NULL,
    goods_status      VARCHAR(20) NOT NULL,
    goods_intro       VARCHAR(500),
    goods_credate     DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 샘플 상품
INSERT INTO t_shopping_goods (goods_sort, goods_title, goods_price, goods_sales_price, goods_status, goods_intro)
VALUES
('식품', '코카콜라 1.5L', 2000, 1800, 'bestseller', '시원한 코카콜라'),
('도서', '이세계 마켓 개발 교재', 30000, 27000, 'newbook', '개발자 필수 교재'),
('패션', '베이직 반팔 티셔츠', 15000, 12000, 'steadyseller', '편하게 입는 기본 티셔츠');

-- ======================
-- 상품 상세 이미지
-- ======================
DROP TABLE IF EXISTS t_goods_detail_image;
CREATE TABLE t_goods_detail_image (
    image_id   INT AUTO_INCREMENT PRIMARY KEY,
    goods_id   INT NOT NULL,
    fileName   VARCHAR(200),
    FOREIGN KEY (goods_id) REFERENCES t_shopping_goods(goods_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 샘플 이미지
INSERT INTO t_goods_detail_image (goods_id, fileName)
VALUES
(1, 'coke.jpg'),
(2, 'book.jpg'),
(3, 'tshirt.jpg');

-- ======================
-- 장바구니
-- ======================
DROP TABLE IF EXISTS t_shopping_cart;
CREATE TABLE t_shopping_cart (
    cart_id    INT AUTO_INCREMENT PRIMARY KEY,
    member_id  VARCHAR(50) NOT NULL,
    goods_id   INT NOT NULL,
    cart_qty   INT DEFAULT 1,
    regDate    DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES t_shopping_member(member_id) ON DELETE CASCADE,
    FOREIGN KEY (goods_id) REFERENCES t_shopping_goods(goods_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 샘플 장바구니
INSERT INTO t_shopping_cart (member_id, goods_id, cart_qty)
VALUES
('user1', 1, 2),
('user1', 2, 1);

-- ======================
-- 주문
-- ======================
DROP TABLE IF EXISTS t_shopping_order;
CREATE TABLE t_shopping_order (
    order_id     INT AUTO_INCREMENT PRIMARY KEY,
    member_id    VARCHAR(50) NOT NULL,
    goods_id     INT NOT NULL,
    order_qty    INT NOT NULL,
    order_price  INT NOT NULL,
    orderDate    DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES t_shopping_member(member_id) ON DELETE CASCADE,
    FOREIGN KEY (goods_id) REFERENCES t_shopping_goods(goods_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 샘플 주문
INSERT INTO t_shopping_order (member_id, goods_id, order_qty, order_price)
VALUES
('user1', 1, 2, 3600),
('user1', 2, 1, 27000);
