#product table의 thumb과 bgImg가 longtext로 바꼇습니다 (이유 - base64형식으로 사진받으니 length가 96000임 ㅈㅈ)
#쿼리문 다시 손좀볼께요


#추가 2020-08-27 1:01(새벽) 현재기준 sql 딱히 바뀐거없음 걍 여기있는거나 잘보고 사용하시면 됩니다.

```


use areuareu;

CREATE TABLE product (
	id int auto_increment primary key,
    title varchar(100) not null, #제목
    thumb longtext not null, #썸네일
    price int, #원가
    disc varchar(100), #설명
    ad boolean not null, #광고 #메인에 걸꺼냐 안걸꺼냐
    discounted  int, #할인
    content longtext, #내용
    categoryId int, #1이면 개별상품 2면 세트상품
    bgImg longtext not null #메인 ㅈㄴ큰 사진 경로
) engine=InnoDB default charset=utf8;
select * from product;
drop table product;
CREATE TABLE product_status  (
	id int auto_increment primary key,
    sale boolean, #여긴 일단 null안해놧음 sale 없을수도있으니까
    newly  boolean, #신상
    best  boolean, #잘팔림
    productId int
) engine=InnoDB default charset=utf8;
select * from product_status;
drop table product;
select * from product;
CREATE TABLE review   (
	id int auto_increment primary key,
    productId  int,
    content   varchar(500),
    userId  int,
    createDate timestamp,
    star int, #난 예전에 별개수를 int로 바꿔서 넣어서 일단 int로 했음
    img varchar(1500), #경로
    existenceOfImg boolean, #이미지 올린놈인지아닌지
    orderId int #결제방법때문에필요
) engine=InnoDB default charset=utf8;
select * from review;

CREATE TABLE user   (
	id int auto_increment primary key,
    username varchar(300),
    password  varchar(1500),
    email  varchar(1500),
    name   varchar(200),
    gender  varchar(100), #boolean 인가? 헷갈리는데 varchar함
    phone varchar(1500),
    address varchar(2000),
    detail_address varchar(1500),
	provider varchar(2000), #이건 구글로 로그인하는사람 땜시 필요합니다.
    providerId varchar(2000),#이것도 구글로 로그인하는사람 땜시 필요합니다.
    birthday timestamp, #2020-12-21식으로넣을꺼 생각해서 varchar함
    total_amount int,
    role varchar(150),
    cancel boolean, #취소 헷갈려서 varchar함 boolean 같은데.. 탈퇴 체크 같아서 boolean으로 바꿨다
    profile varchar(4500),
    createDate timestamp
) engine=InnoDB default charset=utf8;

CREATE TABLE recomment   (
	id int auto_increment primary key,
    reviewId  int,
    comment   varchar(2000),
    createDate  timestamp
) engine=InnoDB default charset=utf8;
select * from recomment;

CREATE TABLE notice   (
	id int auto_increment primary key,
    title  varchar(1500),
    content   varchar(1000),
    createDate  timestamp,
    readCount int, #개수니까 int겟지? 맞다
    thumb varchar(2000)
) engine=InnoDB default charset=utf8;

CREATE TABLE qna   (
	id int auto_increment primary key,
    title  varchar(1500),
    content   varchar(1000),
    createDate  timestamp,
    userId int,
    answer boolean ,
    productId int
) engine=InnoDB default charset=utf8;
select * from qna;
CREATE TABLE orders   (
	id int auto_increment primary key,
    userId  int,
    orderDate   timestamp,
    totalPrice  int,
    howToPay varchar(300) #결제수단넣는건가 varchar인가? 맞다

) engine=InnoDB default charset=utf8;
select * from orders;
CREATE TABLE orders_detail   (
	id int auto_increment primary key,
    orderId  int,
    productId int,#2022 없어서 넣었다
    quantity   int,  #몇개냐 이말
    price  int,
    stats varchar(200), # 상태니까 boolean 해봄 헷갈림 ㅠ
    fee int #택배값 3000고정 할건데 뭐지
) engine=InnoDB default charset=utf8;

CREATE TABLE wishlist   (
	id int auto_increment primary key,
    userId  int,
    productId   int
  ) engine=InnoDB default charset=utf8;

CREATE TABLE coupon   (
	id int auto_increment primary key,
    userId  int,
    code   varchar(1000),
    validityStart  date, #쿠폰 입력가능시작 validity붙임
    validityEnd date,#쿠폰 끝나는 유효기간 validity붙임
    availability boolean # boolean 으로해봄 유효성 헷갈림
) engine=InnoDB default charset=utf8;
select * from coupon;
CREATE TABLE point   (
	id int auto_increment primary key,
    userId  int,
    historyDate date, #date예약어라서 바꿈
    reason   varchar(500), #이유라서 varchar함
    point int,
    totalPoint int
) engine=InnoDB default charset=utf8;
select * from point;

CREATE TABLE related_product   ( #관련상품 2022 N:N 이런거 생각하면 복잡함
	id int auto_increment primary key,
    parentProductId  int, #detail로 올때 누른 상품
    relatedProductId int #연관된 상품 1-2 , 1-3
) engine=InnoDB default charset=utf8;
select * from related_product;

CREATE TABLE category   (
	id int auto_increment primary key,
    type  varchar(200), #type 헷갈려서 여긴 varchar함,
    parentTypeId int #2022 확장성을 위함같은데
) engine=InnoDB default charset=utf8;

CREATE TABLE cart(# 2022 종이에는 product_status에 따라서 다르다는데 모르겠음
	id int auto_increment primary key,
    productId int,
    userId int,
    quantity int,
    price int
) engine=InnoDB default charset=utf8;
#join과 test를 위한 데이터
#user 더미 데이터
select * from user;
insert into user(username,password,email,name,gender,phone,address,detail_address,birthday,total_amount,role,cancel,profile,provider,providerId,createDate)
values('cos','cos1234','tjdtnsla0321@nate.com','최주호','남','010-2222-0000','부산광역시 진구','서면 파이널센터 4층','1985-02-01',120000,'ROLE_USER',false,'프로필입니다.','직접가입함','직접가입함',now());

insert into user(username,password,email,name,gender,phone,address,detail_address,birthday,total_amount,role,cancel,profile,provider,providerId,createDate)
values('tjdtn','tjdtn123','tjdd@nate.com','김성수','남','010-0000-0000','경북 포항시','북구 학잠동','1993-09-11',200000,'ROLE_USER',false,'프로필입니다.','직접가입함','직접가입함',now());

insert into user(username,password,email,name,gender,phone,address,detail_address,birthday,total_amount,role,cancel,profile,provider,providerId,createDate)
values('tjsgus','tjsgus123','tjsgus@nate.com','문선현','여','010-1111-1111','부산광역시 어딘가','남포동 아파트','1993-12-31',50000,'ROLE_USER',false,'프로필입니다.','직접가입함','직접가입함',now());

insert into user(username,password,email,name,gender,phone,address,detail_address,birthday,total_amount,role,cancel,profile,provider,providerId,createDate)
values('dnjswo','dnjswo1234','dnjswop321@nate.com','이원재','남','010-2322-7440','부산광역시 연산','연산역 집','1992-01-01',2000,'ROLE_USER',false,'프로필입니다.','직접가입함','직접가입함',now());

#product 더미데이터
insert into product(title,thumb,price,disc,ad,discounted,content,categoryId,bgImg)
values ('프레시 런드리 디터전트','thumb의 경로1',28000,'베이비 런드리 디터전트(무향)1L*1ea',true,27000,'content1',1,'bgImg의 경로1');
insert into product(title,thumb,price,disc,ad,discounted,content,categoryId,bgImg)
values ('패밀리 키친 세트','thumb의 경로2',40000,'프레시 디시 앤 프루트 워시 (오렌지) 500ml * 1ea 베이비 보틀 앤 토이 워시 (무향) 500ml * 1ea',true,34000,'content2',2,'bgImg의 경로2');
insert into product(title,thumb,price,disc,ad,discounted,content,categoryId,bgImg)
values ('기프트 세트','thumb의 경로3',63000,'OPTION. 구성품 택1 (키친 세트,리빙케어 세트, 런드미 세트중) OPTION 2. 박스 색상 택1 (프레시 네이버,베이비 민트 중)',false,39000,'content3',2,'bgImg의 경로3');
select * from user;
commit;
#product_status 더미데이터
insert into product_status(id,sale,newly, best,productId)
values(1,false,true,false,1);
insert into product_status(id,sale,newly, best,productId)
values(2,true,true,true,2);
insert into product_status(id,sale,newly, best,productId)
values(3,true,false,false,3);
select * from product_status;

#review 더미데이터
insert into review(productId,content,userId,createDate,star,img,existenceOfImg,orderId)
values(1,'너무비싸서 놀랫네요..',1,now(),5,'img의 경로1',true,1);
insert into review(productId,content,userId,createDate,star,img,existenceOfImg,orderId)
values(2,'아르아르 믿을수가없다',2,now(),1,'img의 경로2',true,1);
insert into review(productId,content,userId,createDate,star,img,existenceOfImg,orderId)
values(1,'너무비싸서 놀랫네요..',1,now(),3,'img의 경로1',true,1);

#recomment 더미데이터
insert into recomment(reviewId,comment,createDate)
values (1,'고객님 너무비싸서 죄송합니다 ㅠ^ㅠ 그래도 사세요 ㅠ',now());
insert into recomment(reviewId,comment,createDate)
values (2,'사랑합니다 고객님 ^^',now());
insert into recomment(reviewId,comment,createDate)
values (3,'참신한 아르아르가 되겟습니다',now());

#notice 더미데이터

insert into notice(title,content,createDate,readcount,thumb)
values ('★경★아르아르 폐쇠★축★','2020년09월10일 아르아르가 드디어 역사속으로 사라집니다.',now(),1,'thumb의 경로1');
insert into notice(title,content,createDate,readcount,thumb)
values ('[EVENT]아르아르 우한점 오픈기념 코로나 1+1 이벤트','중국 우한점 오픈기념으로인한 제품구매시 증정품은 코로나를 1+1으로 2개를 드립니다',now(),1,'thumb의 경로2');

#qna 더미데이터
insert into qna(title,content,createDate,userId,answer,productid)
values ('배송문의','신청한지 1년이넘었는데 죽기전 까진오겟죠?',now(),1,true,1);
insert into qna(title,content,createDate,userId,answer,productid)
values ('품질에 이상이있어요..','비누에서 거품이나요..',now(),2,true,1);
insert into qna(title,content,createDate,userId,answer,productid)
values ('품질에 이상이있어요..','비누에서 거품이나요..',now(),2,true,2);
insert into qna(title,content,createDate,userId,answer,productid)
values ('품질에 이상이있어요..','비누에서 거품이나요..',now(),2,true,3);
insert into qna(title,content,createDate,userId,answer,productid)
values ('품질에 이상이있어요..','비누에서 거품이나요..',now(),1,true,2);

#orders 더미데이터
insert into orders(userId,orderDate,totalPrice,howToPay)
values(1,now(),68000,'카카오페이');

#orders_detail 더미데이터
insert into orders_detail(orderId,productId,quantity,price,stats,fee)
values (1,1,1,28000,'배송중',3000);
insert into orders_detail(orderId,productId,quantity,price,stats,fee)
values (1,1,2,40000,'배송중',3000);
select * from orders_detail;

#wishlist 더미데이터
insert into wishlist(userid,productId)values(1,1);
insert into wishlist(userid,productId)values(1,2);
insert into wishlist(userid,productId)values(1,3);

#coupon 더미데이터
insert into coupon(userId,code,validityStart,validityEnd,availability)
values(1,'1212-2222-3333-4444',now(),DATE_ADD(now(), INTERVAL 1 MONTH),true);
insert into coupon(userId,code,validityStart,validityEnd,availability)
values(2,'1212-2222-3333-4444',now(),DATE_ADD(now(), INTERVAL 1 MONTH),true);
insert into coupon(userId,code,validityStart,validityEnd,availability)
values(3,'1212-2222-3333-4444',now(),DATE_ADD(now(), INTERVAL 1 MONTH),true);

#point 더미데이터
insert into point(userId,historyDate,reason,point,totalPoint)
values(1,now(),'썻으니까',2000,50000);
insert into point(userId,historyDate,reason,point,totalPoint)
values(2,now(),'사용했으니까',1500,20000);
insert into point(userId,historyDate,reason,point,totalPoint)
values(3,now(),'출금했으니까',1000,5200);

#related_product 더미데이터

insert into related_product(parentProductId,relatedProductId)
values (1,3);
insert into related_product(parentProductId,relatedProductId)
values (2,1);
insert into related_product(parentProductId,relatedProductId)
values (3,3);
insert into related_product(parentProductId,relatedProductId)
values (1,2);

#########################조인##########################


##INDEX 페이지 입니다.

#대문 dto
SELECT id,bgimg
FROM product
where ad=true;


#REVIEW dto
SELECT *
FROM review r INNER JOIN product p
ON p.id = r.productId;

#PRODUCTS 카르셀 dto
SELECT title,price,discounted,sale,newly,best
FROM product p INNER JOIN product_status pst
ON p.id = pst.productId;

################################################################################

SELECT r.content,us.name,r.img,p.title,p.title,p.price,p.discounted,pst.sale,pst.newly,pst.best
FROM review r INNER JOIN product p inner join user us inner join product_status pst
ON p.id = r.productId and r.userid = us.id and p.id = pst.productId;

select * from review;
select * from user;
select * from product;



#####################################################################
##SHOP 페이지입니다. 이거를 dto를 만들던지 react에서 categoryId로 분리합시다.
SELECT p.id,thumb,title,price,discounted,sale,newly,best,categoryId
FROM product p INNER JOIN product_status pst
ON p.id = pst.productId
WHERE p.categoryId = 1;

SELECT p.id,thumb,title,price,discounted,sale,newly,best,categoryId
FROM product p INNER JOIN product_status pst
ON p.id = pst.productId
WHERE p.categoryId = 2;

##REVIEW 페이지 입니다.
#r.id는 해당 리뷰를 modal 못할때를 대비해서 게시판형식처럼 detail갈때 씁니다.
#r.star는(별점) 전문가 한테 상의
SELECT p.id, r.id , p.title, r.content, u.username, r.createDate, r.star
FROM review r INNER JOIN product p INNER JOIN user u
ON p.id = r.productId AND u.id = r.userId;


##NOTICE 페이지 입니다.
#해당 페이지는 게시판(공지) 입니다. 댓글은 원래 존재하나 안쓸예정
SELECT * FROM NOTICE;

##MyPage 입니다.

## (기본 조인) 숫자 1 은 동적으로 바꿔야 한다.
SELECT u.username, u.profile, u.total_amount, (SELECT COUNT(*) FROM coupon where coupon.id = 1)
FROM user u INNER JOIN coupon c
ON u.id = c.userId
WHERE u.id = 1;

#주문 조회 distinct안쓰고 싶은데.. 불안한 join
SELECT distinct o.id, o.orderDate, o.totalPrice, p.title
FROM orders o INNER JOIN orders_detail odt INNER JOIN product p INNER JOIN user u
ON p.id = odt.productId AND o.id = odt.orderId AND u.id = o.userId
WHERE u.id = 1;

#주문 디테일
SELECT o.id, o.orderDate, p.title, odt.price
FROM orders o INNER JOIN orders_detail odt INNER JOIN product p INNER JOIN user u
ON p.id = odt.productId AND o.id = odt.orderId AND u.id = o.userId
WHERE u.id = 1;

#위시 리스트

SELECT p.id, p.title, p.price, p.discounted, p.thumb, pst.newly,pst.sale, pst.best
FROM wishList w INNER JOIN product p INNER JOIN user u
ON p.id = w.productId AND w.userId = u.id
INNER JOIN product_status pst
ON p.id = pst.productId
where u.id = 1;

#취소/교환/반품

SELECT odt.id , p.title,p.price,odt.stats
FROM orders o INNER JOIN orders_detail odt INNER JOIN user u
ON o.id = odt.orderId AND u.id = o.userId
INNER JOIN product p
ON p.id = odt.productId
WHERE u.id = 1;

#쿠폰

SELECT c.id, c.code, c.validityStart, c.validityEnd, c.availability
FROM user u INNER JOIN coupon c
ON u.id = c.userId
WHERE u.id = 1;

#포인트

SELECT p.point, p.reason, p.historyDate
FROM user u INNER JOIN point p
ON u.id = p.userId
WHERE u.id = 1;

#1:1문의

SELECT q.title, q.content, q.createDate
FROM qna q INNER JOIN user u
ON u.id = q.userId
WHERE u.id = 1;

#정보 수정 -> 비밀번호 제외

SELECT * FROM USER u
WHERE u.id = 1;


##Cart 입니다.
SELECT p.id, p.title, c.quantity, p.price
FROM cart c INNER JOIN product p INNER JOIN user u
ON c.userId = u.id AND p.id = c.productId
WHERE u.id = 1;


##payment 입니다.
SELECT p.title, p.price
FROM user u INNER JOIN cart c INNER JOIN product p
ON u.id = c.userId AND p.id = c.productId
WHERE u.id = 1;


#category 더미데이터
insert into category(type,parentTypeId)
values ("싱글상품",1);
insert into category(type,parentTypeId)
values ("세트상품",2);

#cart 더미데이터
insert into cart(productId,userId,quantity,price)
values (1,1,2,28000);

insert into cart(productId,userId,quantity,price)
values (2,1,1,40000);

insert into cart(productId,userId,quantity,price)
values (1,2,2,28000);


##################관리자페이지#############################
##상품 CRUD
#category
#product
#product_status
#relatedproduct


##주문 CRUD
#orders
#orders_detail

#cart

#user

#notice

#review

#recomment

#qna

#wishlist

#coupon

#point

select * from user;
```
