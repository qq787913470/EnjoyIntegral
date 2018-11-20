---------------------------------------------
-- Export file for user scott               --
-- Created by bocsz on 2018-11-9, 12:31:45 --
---------------------------------------------

spool 1.log

prompt
prompt Creating table ACTIVITY
prompt =======================
prompt
create table scott.ACTIVITY
(
  ACTIVITY_ID   VARCHAR2(32) not null,
  ACTIVITY_NAME NVARCHAR2(64),
  START_DATE    VARCHAR2(14),
  END_DATE      VARCHAR2(14),
  GIFT_COUNT    VARCHAR2(10),
  CLIENT_PROMPT VARCHAR2(255),
  STATE         CHAR(1),
  REMARK        VARCHAR2(2000),
  DETAIL        BLOB,
  ORG_ID        VARCHAR2(6),
  REVIEW_FLAG   CHAR(1),
  CLOSE_REASON  VARCHAR2(255)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table scott.ACTIVITY
  is '活动';
comment on column scott.ACTIVITY.ACTIVITY_ID
  is '活动ID';
comment on column scott.ACTIVITY.ACTIVITY_NAME
  is '活动名称';
comment on column scott.ACTIVITY.START_DATE
  is '活动开启日期';
comment on column scott.ACTIVITY.END_DATE
  is '活动结束日期';
comment on column scott.ACTIVITY.GIFT_COUNT
  is '可兑换礼品种类,表示一个客户可以选择的礼品种类数';
comment on column scott.ACTIVITY.CLIENT_PROMPT
  is '客户提示';
comment on column scott.ACTIVITY.STATE
  is '活动状态';
comment on column scott.ACTIVITY.REMARK
  is '活动简介';
comment on column scott.ACTIVITY.DETAIL
  is '活动详情';
comment on column scott.ACTIVITY.ORG_ID
  is '机构ID';
comment on column scott.ACTIVITY.REVIEW_FLAG
  is '符合表示1未复核，0是复核';
comment on column scott.ACTIVITY.CLOSE_REASON
  is '活动关闭原因';
alter table scott.ACTIVITY
  add constraint PK_ACTIVITY primary key (ACTIVITY_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table ACTIVITY_PRODUCT_ENJOY
prompt =====================================
prompt
create table scott.ACTIVITY_PRODUCT_ENJOY
(
  ID          VARCHAR2(32) not null,
  ACTIVITY_ID VARCHAR2(32) not null,
  PRODUCT_ID  VARCHAR2(32) not null,
  LIMIT_COUNT VARCHAR2(10),
  GIFT_PROMPT VARCHAR2(255),
  GIFT_COUNT  VARCHAR2(10)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table scott.ACTIVITY_PRODUCT_ENJOY
  is '活动产品表';
comment on column scott.ACTIVITY_PRODUCT_ENJOY.ID
  is '活动商品ID';
comment on column scott.ACTIVITY_PRODUCT_ENJOY.ACTIVITY_ID
  is '活动ID';
comment on column scott.ACTIVITY_PRODUCT_ENJOY.PRODUCT_ID
  is '商品ID';
comment on column scott.ACTIVITY_PRODUCT_ENJOY.LIMIT_COUNT
  is '每位客户兑换礼品的限制数量';
comment on column scott.ACTIVITY_PRODUCT_ENJOY.GIFT_PROMPT
  is '礼品提示';
comment on column scott.ACTIVITY_PRODUCT_ENJOY.GIFT_COUNT
  is '礼品总数';
alter table scott.ACTIVITY_PRODUCT_ENJOY
  add constraint PK_ACTIVITY_PRODUCT_ENJOY primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CUSTOMER_INFO
prompt ============================
prompt
create table scott.CUSTOMER_INFO
(
  CERTIFICATE_NO   VARCHAR2(20) not null,
  CERTIFICATE_TYPE CHAR(1) not null,
  CUSTOMER_NO      VARCHAR2(20),
  NAME             VARCHAR2(64)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column scott.CUSTOMER_INFO.CERTIFICATE_NO
  is '证件号';
comment on column scott.CUSTOMER_INFO.CERTIFICATE_TYPE
  is '证件类型';
comment on column scott.CUSTOMER_INFO.CUSTOMER_NO
  is '客户号';
comment on column scott.CUSTOMER_INFO.NAME
  is '客户姓名';
alter table scott.CUSTOMER_INFO
  add constraint PK_CUSTOMER_INFO primary key (CERTIFICATE_NO, CERTIFICATE_TYPE)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CUSTOMER_ACTIVITY
prompt ================================
prompt
create table scott.CUSTOMER_ACTIVITY
(
  CERTIFICATE_NO   VARCHAR2(20) not null,
  CERTIFICATE_TYPE CHAR(1) not null,
  ACTIVITY_ID      VARCHAR2(32) not null,
  IS_JOIN          CHAR(1)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column scott.CUSTOMER_ACTIVITY.CERTIFICATE_NO
  is '证件号';
comment on column scott.CUSTOMER_ACTIVITY.CERTIFICATE_TYPE
  is '证件类型';
comment on column scott.CUSTOMER_ACTIVITY.ACTIVITY_ID
  is '活动ID';
comment on column scott.CUSTOMER_ACTIVITY.IS_JOIN
  is '是否参加过活动，1表示未参加，0表示已参加';
alter table scott.CUSTOMER_ACTIVITY
  add constraint PK_CUSTOMER_ACTIVITY primary key (CERTIFICATE_NO, CERTIFICATE_TYPE, ACTIVITY_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table scott.CUSTOMER_ACTIVITY
  add constraint FK_ACTIVITY_CUSTOMER foreign key (ACTIVITY_ID)
  references scott.ACTIVITY (ACTIVITY_ID);
alter table scott.CUSTOMER_ACTIVITY
  add constraint FK_CUSTOMER_ACTIVITY foreign key (CERTIFICATE_NO, CERTIFICATE_TYPE)
  references scott.CUSTOMER_INFO (CERTIFICATE_NO, CERTIFICATE_TYPE);

prompt
prompt Creating table CUSTOMER_PRODUCT
prompt ===============================
prompt
create table scott.CUSTOMER_PRODUCT
(
  CUSTOMER_NO VARCHAR2(20),
  PRODUCT_ID  VARCHAR2(32)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
comment on column scott.CUSTOMER_PRODUCT.CUSTOMER_NO
  is '客户号';
comment on column scott.CUSTOMER_PRODUCT.PRODUCT_ID
  is '商品ID';

prompt
prompt Creating table MERCHANT_BASE
prompt ============================
prompt
create table scott.MERCHANT_BASE
(
  MER_NO           CHAR(15) not null,
  MER_NAME         NVARCHAR2(64),
  ACCOUNT_NO       VARCHAR2(25),
  ACCOUNT_NAME     NVARCHAR2(64),
  OPENING_BANK     NVARCHAR2(64),
  OPENING_BANK_NO  VARCHAR2(25),
  CONTAC_PERSON    NVARCHAR2(64),
  CONTACT_ADDR     NVARCHAR2(128),
  SERVICE_PHONE    VARCHAR2(20),
  ORG_ID           VARCHAR2(6),
  REVIEW_FLAG      CHAR(1),
  END_SERVICE_DATE VARCHAR2(8),
  SERVICE_STARE    CHAR(1),
  MER_DETAIL       VARCHAR2(255),
  CLOSE_REASON     VARCHAR2(255)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table scott.MERCHANT_BASE
  is '普通积分商户';
comment on column scott.MERCHANT_BASE.MER_NO
  is '商户号';
comment on column scott.MERCHANT_BASE.MER_NAME
  is '商户名称';
comment on column scott.MERCHANT_BASE.ACCOUNT_NO
  is '账号';
comment on column scott.MERCHANT_BASE.ACCOUNT_NAME
  is '账号名称';
comment on column scott.MERCHANT_BASE.OPENING_BANK
  is '开户行';
comment on column scott.MERCHANT_BASE.OPENING_BANK_NO
  is '开户行号';
comment on column scott.MERCHANT_BASE.CONTAC_PERSON
  is '联系人';
comment on column scott.MERCHANT_BASE.CONTACT_ADDR
  is '联系地址';
comment on column scott.MERCHANT_BASE.SERVICE_PHONE
  is '客服电话';
comment on column scott.MERCHANT_BASE.ORG_ID
  is '机构ID';
comment on column scott.MERCHANT_BASE.REVIEW_FLAG
  is '符合表示1未复核，0是复核';
comment on column scott.MERCHANT_BASE.END_SERVICE_DATE
  is '结束服务时间';
comment on column scott.MERCHANT_BASE.SERVICE_STARE
  is '开启/关闭服务开启/关闭服务1关闭 0是开启';
comment on column scott.MERCHANT_BASE.MER_DETAIL
  is '商户描述';
comment on column scott.MERCHANT_BASE.CLOSE_REASON
  is '普通商户关闭原因';
alter table scott.MERCHANT_BASE
  add constraint PK_MERCHANT_BASE primary key (MER_NO)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table MERCHANT_ENJOY
prompt =============================
prompt
create table scott.MERCHANT_ENJOY
(
  MER_NO           CHAR(15) not null,
  MER_NAME         NVARCHAR2(64),
  ACCOUNT_NO       VARCHAR2(25),
  ACCOUNT_NAME     NVARCHAR2(64),
  OPENING_BANK     NVARCHAR2(64),
  OPENING_BANK_NO  VARCHAR2(25),
  CONTAC_PERSON    NVARCHAR2(64),
  CONTACT_ADDR     NVARCHAR2(64),
  SERVICE_PHONE    VARCHAR2(20),
  ORG_ID           VARCHAR2(6),
  REVIEW_FLAG      CHAR(1),
  END_SERVICE_DATE VARCHAR2(8),
  SERVICE_STARE    CHAR(1),
  MER_DETAIL       VARCHAR2(255),
  CLOSE_REASON     VARCHAR2(255)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table scott.MERCHANT_ENJOY
  is '普通积分商户';
comment on column scott.MERCHANT_ENJOY.MER_NO
  is '商户号';
comment on column scott.MERCHANT_ENJOY.MER_NAME
  is '商户名称';
comment on column scott.MERCHANT_ENJOY.ACCOUNT_NO
  is '账号';
comment on column scott.MERCHANT_ENJOY.ACCOUNT_NAME
  is '账号名称';
comment on column scott.MERCHANT_ENJOY.OPENING_BANK
  is '开户行';
comment on column scott.MERCHANT_ENJOY.OPENING_BANK_NO
  is '开户行号';
comment on column scott.MERCHANT_ENJOY.CONTAC_PERSON
  is '联系人';
comment on column scott.MERCHANT_ENJOY.CONTACT_ADDR
  is '联系地址';
comment on column scott.MERCHANT_ENJOY.SERVICE_PHONE
  is '客服电话';
comment on column scott.MERCHANT_ENJOY.ORG_ID
  is '机构ID';
comment on column scott.MERCHANT_ENJOY.REVIEW_FLAG
  is '符合表示1未复核，0是复核';
comment on column scott.MERCHANT_ENJOY.END_SERVICE_DATE
  is '结束服务时间';
comment on column scott.MERCHANT_ENJOY.SERVICE_STARE
  is '开启/关闭服务1关闭 0是开启';
comment on column scott.MERCHANT_ENJOY.MER_DETAIL
  is '商户描述';
comment on column scott.MERCHANT_ENJOY.CLOSE_REASON
  is '尊享商户关闭原因';
alter table scott.MERCHANT_ENJOY
  add constraint PK_MERCHANT_ENJOY primary key (MER_NO)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PRODUCT_BASE
prompt ===========================
prompt
create table scott.PRODUCT_BASE
(
  PRODUCT_ID     VARCHAR2(32) not null,
  PRODUCT_NAME   NVARCHAR2(64),
  PRICE          VARCHAR2(20),
  REMARK         NVARCHAR2(255),
  MER_NO         CHAR(15),
  INTEGRAL_VALUE VARCHAR2(20),
  IS_GROUND      CHAR(1),
  DETAIL         BLOB,
  GIFT_COUNT     NUMBER,
  PIC_PATH       VARCHAR2(256),
  START_DATE     VARCHAR2(8),
  END_DATE       VARCHAR2(8),
  REVIEW_FLAG    CHAR(1),
  CLOSE_REASON   VARCHAR2(255)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table scott.PRODUCT_BASE
  is '普通积分商品';
comment on column scott.PRODUCT_BASE.PRODUCT_ID
  is '商品ID';
comment on column scott.PRODUCT_BASE.PRODUCT_NAME
  is '商品名称';
comment on column scott.PRODUCT_BASE.PRICE
  is '单价';
comment on column scott.PRODUCT_BASE.REMARK
  is '产品说明';
comment on column scott.PRODUCT_BASE.MER_NO
  is '商户号';
comment on column scott.PRODUCT_BASE.INTEGRAL_VALUE
  is '积分值';
comment on column scott.PRODUCT_BASE.IS_GROUND
  is '商品状态,0表示已上架，1表示已下架';
comment on column scott.PRODUCT_BASE.DETAIL
  is '商品简介';
comment on column scott.PRODUCT_BASE.GIFT_COUNT
  is '礼品数量';
comment on column scott.PRODUCT_BASE.PIC_PATH
  is '商品图片名称';
comment on column scott.PRODUCT_BASE.START_DATE
  is '商品开始日期';
comment on column scott.PRODUCT_BASE.END_DATE
  is '商品结束日期';
comment on column scott.PRODUCT_BASE.REVIEW_FLAG
  is '符合表示1未复核，0是复核';
comment on column scott.PRODUCT_BASE.CLOSE_REASON
  is '商品下架原因原因';
alter table scott.PRODUCT_BASE
  add constraint PK_PRODUCT primary key (PRODUCT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table scott.PRODUCT_BASE
  add constraint FK_MERCHANT_BASE foreign key (MER_NO)
  references scott.MERCHANT_BASE (MER_NO);

prompt
prompt Creating table PRODUCT_CART
prompt ===========================
prompt
create table scott.PRODUCT_CART
(
  PRODUCT_CART_ID  VARCHAR2(64) not null,
  CERTIFICATE_TYPE CHAR(1),
  CERTIFICATE_NO   VARCHAR2(20),
  PRODUCT_ID       VARCHAR2(32),
  COUNT            NUMBER,
  INTEGRAL_VALUE   VARCHAR2(10),
  PRODUCT_NAME     NVARCHAR2(64),
  IS_PRINT         CHAR(1),
  PRICE            VARCHAR2(10),
  ACTIVITY_NAME    NVARCHAR2(64),
  ACTIVITY_ID      VARCHAR2(32),
  PRODUCT_REMARK   NVARCHAR2(2000),
  ACTIVITY_REMARK  NVARCHAR2(2000),
  MER_NO           CHAR(15),
  MER_NAME         NVARCHAR2(64),
  PRODUCT_DETAIL   BLOB,
  ACTIVITY_DETAIL  BLOB,
  PRODUCT_TYPE     CHAR(1),
  MER_DETAIL       VARCHAR2(255),
  MER_PHONE        VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column scott.PRODUCT_CART.PRODUCT_CART_ID
  is '购物车id';
comment on column scott.PRODUCT_CART.CERTIFICATE_TYPE
  is '证件类型';
comment on column scott.PRODUCT_CART.CERTIFICATE_NO
  is '证件号';
comment on column scott.PRODUCT_CART.PRODUCT_ID
  is '产品id';
comment on column scott.PRODUCT_CART.COUNT
  is '产品数量';
comment on column scott.PRODUCT_CART.INTEGRAL_VALUE
  is '积分值';
comment on column scott.PRODUCT_CART.PRODUCT_NAME
  is '产品名称';
comment on column scott.PRODUCT_CART.IS_PRINT
  is '是否已打印(1:已打印 0:未打印)';
comment on column scott.PRODUCT_CART.PRICE
  is '产品价格';
comment on column scott.PRODUCT_CART.ACTIVITY_NAME
  is '活动名称（普通商品填写nomal）';
comment on column scott.PRODUCT_CART.ACTIVITY_ID
  is '活动id（普通商品填写nomal）';
comment on column scott.PRODUCT_CART.PRODUCT_REMARK
  is '产品说明';
comment on column scott.PRODUCT_CART.ACTIVITY_REMARK
  is '活动说明';
comment on column scott.PRODUCT_CART.MER_NO
  is '商户号';
comment on column scott.PRODUCT_CART.MER_NAME
  is '商户名称';
comment on column scott.PRODUCT_CART.PRODUCT_DETAIL
  is '商品简介';
comment on column scott.PRODUCT_CART.ACTIVITY_DETAIL
  is '活动简介';
comment on column scott.PRODUCT_CART.PRODUCT_TYPE
  is '商品类型,0为普通商品，1为尊享商品';
comment on column scott.PRODUCT_CART.MER_DETAIL
  is '商户描述';
comment on column scott.PRODUCT_CART.MER_PHONE
  is '商户电话';
alter table scott.PRODUCT_CART
  add constraint PK_PRODUCT_CART_ID primary key (PRODUCT_CART_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PRODUCT_ENJOY
prompt ============================
prompt
create table scott.PRODUCT_ENJOY
(
  PRODUCT_ID     VARCHAR2(32) not null,
  PRODUCT_NAME   NVARCHAR2(64),
  PRICE          VARCHAR2(20),
  REMARK         NVARCHAR2(255),
  MER_NO         CHAR(15),
  INTEGRAL_VALUE VARCHAR2(20),
  IS_GROUND      CHAR(1),
  GIFT_COUNT     NUMBER,
  DETAIL         BLOB,
  PIC_PATH       VARCHAR2(60),
  START_DATE     VARCHAR2(8),
  END_DATE       VARCHAR2(8),
  REVIEW_FLAG    CHAR(1),
  CLOSE_REASON   VARCHAR2(255)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table scott.PRODUCT_ENJOY
  is '普通积分商品';
comment on column scott.PRODUCT_ENJOY.PRODUCT_ID
  is '商品ID';
comment on column scott.PRODUCT_ENJOY.PRODUCT_NAME
  is '商品名称';
comment on column scott.PRODUCT_ENJOY.PRICE
  is '单价';
comment on column scott.PRODUCT_ENJOY.REMARK
  is '产品说明';
comment on column scott.PRODUCT_ENJOY.MER_NO
  is '商户号';
comment on column scott.PRODUCT_ENJOY.INTEGRAL_VALUE
  is '积分值';
comment on column scott.PRODUCT_ENJOY.IS_GROUND
  is '状态';
comment on column scott.PRODUCT_ENJOY.GIFT_COUNT
  is '礼品数量';
comment on column scott.PRODUCT_ENJOY.DETAIL
  is '商品简介';
comment on column scott.PRODUCT_ENJOY.PIC_PATH
  is '商品图片';
comment on column scott.PRODUCT_ENJOY.START_DATE
  is '商品开始日期';
comment on column scott.PRODUCT_ENJOY.END_DATE
  is '商品结束日期';
comment on column scott.PRODUCT_ENJOY.REVIEW_FLAG
  is '符合表示1未复核，0是复核';
comment on column scott.PRODUCT_ENJOY.CLOSE_REASON
  is '商品下架原因原因';
alter table scott.PRODUCT_ENJOY
  add constraint PK_PRODUCT_ENJOY primary key (PRODUCT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table scott.PRODUCT_ENJOY
  add constraint FK_MERCHANT_ENJOY foreign key (MER_NO)
  references scott.MERCHANT_ENJOY (MER_NO);

prompt
prompt Creating table TRAN_LIST
prompt ========================
prompt
create table scott.TRAN_LIST
(
  PRODUCT_ID       VARCHAR2(32) not null,
  PRODUCT_NAME     NVARCHAR2(64),
  MER_NO           CHAR(15),
  MER_NAME         NVARCHAR2(64),
  ACTIVITY_ID      VARCHAR2(32),
  ACTIVITY_NAME    NVARCHAR2(64),
  INTEGRAL_VALUE   NUMBER,
  ORG_ID           VARCHAR2(16),
  P_ORG_ID         VARCHAR2(16),
  ORG_NAME         NVARCHAR2(32),
  P_ORG_NAME       NVARCHAR2(32),
  CUSTOMER_NAME    VARCHAR2(64),
  CUSTOMER_PHONE   VARCHAR2(20),
  USER_ID          VARCHAR2(15),
  USER_NAME        VARCHAR2(32),
  CUSTOMER_CARD_NO VARCHAR2(19),
  MER_NO_POS       CHAR(15),
  MER_NAME_POS     NVARCHAR2(64),
  TRAN_DATE        VARCHAR2(8),
  TRAN_TIME        VARCHAR2(6),
  TERMINAL_NO      VARCHAR2(8),
  TRAN_STATE       VARCHAR2(2),
  BANK_NAME        VARCHAR2(20),
  CARD_TYPE        VARCHAR2(2),
  CART_VALIDITY    VARCHAR2(4),
  SYS_REFERENCE    VARCHAR2(12),
  AUTH_NO          VARCHAR2(6),
  TRACE_NO         VARCHAR2(6),
  BILL_NO          VARCHAR2(6),
  BATCH_NO         VARCHAR2(6),
  TRAN_WAY         VARCHAR2(1),
  CASH_NO          VARCHAR2(8),
  STATE            VARCHAR2(20),
  TRAN_COUNT       NUMBER,
  CERTIFICATE_NO   VARCHAR2(20),
  CERTIFICATE_TYPE CHAR(1),
  TRAN_ID          VARCHAR2(32) default sys_guid() not null,
  OLD_TRAN_ID      VARCHAR2(32)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column scott.TRAN_LIST.PRODUCT_ID
  is '商品ID';
comment on column scott.TRAN_LIST.PRODUCT_NAME
  is '商品名称';
comment on column scott.TRAN_LIST.MER_NO
  is '商户号';
comment on column scott.TRAN_LIST.MER_NAME
  is '商户名称';
comment on column scott.TRAN_LIST.ACTIVITY_ID
  is '活动ID';
comment on column scott.TRAN_LIST.ACTIVITY_NAME
  is '活动名称';
comment on column scott.TRAN_LIST.INTEGRAL_VALUE
  is '积分消费数';
comment on column scott.TRAN_LIST.ORG_ID
  is '兑换网点机构号';
comment on column scott.TRAN_LIST.P_ORG_ID
  is '管辖支行机构号';
comment on column scott.TRAN_LIST.ORG_NAME
  is '兑换网点名称';
comment on column scott.TRAN_LIST.P_ORG_NAME
  is '管辖支行';
comment on column scott.TRAN_LIST.CUSTOMER_NAME
  is '兑换客户姓名';
comment on column scott.TRAN_LIST.CUSTOMER_PHONE
  is '服务短信接收手机号';
comment on column scott.TRAN_LIST.USER_ID
  is '操作员号';
comment on column scott.TRAN_LIST.USER_NAME
  is '操作员名称';
comment on column scott.TRAN_LIST.CUSTOMER_CARD_NO
  is '兑换客户卡号(以下字段都是POS返回)';
comment on column scott.TRAN_LIST.MER_NO_POS
  is '供应商商户号';
comment on column scott.TRAN_LIST.MER_NAME_POS
  is '供应商名称';
comment on column scott.TRAN_LIST.TRAN_DATE
  is '交易日期';
comment on column scott.TRAN_LIST.TRAN_TIME
  is '交易时间';
comment on column scott.TRAN_LIST.TERMINAL_NO
  is '终端号';
comment on column scott.TRAN_LIST.TRAN_STATE
  is '表示pos的交易状态，00表示成功，其他的表示失败';
comment on column scott.TRAN_LIST.BANK_NAME
  is '发卡行名称';
comment on column scott.TRAN_LIST.CARD_TYPE
  is '卡类型';
comment on column scott.TRAN_LIST.CART_VALIDITY
  is '卡有效期';
comment on column scott.TRAN_LIST.SYS_REFERENCE
  is '系统参考号';
comment on column scott.TRAN_LIST.AUTH_NO
  is '授权号';
comment on column scott.TRAN_LIST.TRACE_NO
  is '流水号';
comment on column scott.TRAN_LIST.BILL_NO
  is '票据号';
comment on column scott.TRAN_LIST.BATCH_NO
  is '批次号';
comment on column scott.TRAN_LIST.TRAN_WAY
  is '交易方式';
comment on column scott.TRAN_LIST.CASH_NO
  is '款台号';
comment on column scott.TRAN_LIST.STATE
  is '流水状态:web自己的交易标识,00表示消费交易，01表撤销交易，02表已撤销';
comment on column scott.TRAN_LIST.TRAN_COUNT
  is '交易数量';
comment on column scott.TRAN_LIST.CERTIFICATE_NO
  is '证件号';
comment on column scott.TRAN_LIST.CERTIFICATE_TYPE
  is '证件类型';
comment on column scott.TRAN_LIST.TRAN_ID
  is '流水编号';
comment on column scott.TRAN_LIST.OLD_TRAN_ID
  is '原消费流水编号,仅积分撤销时才有';
alter table scott.TRAN_LIST
  add constraint TRAN_LIST_TRANID_PRODUCTID primary key (TRAN_ID, PRODUCT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_B_ORG
prompt ======================
prompt
create table scott.T_B_ORG
(
  ORG_ID      VARCHAR2(16) not null,
  P_ORG_ID    VARCHAR2(16),
  ORG_LEV     NUMBER(2),
  CUP_ID      VARCHAR2(11),
  ZONE_CODE   CHAR(4),
  BANK_NO     CHAR(10),
  NAME        NVARCHAR2(32),
  CONTACT     NVARCHAR2(8),
  TEL         VARCHAR2(32),
  FAX         VARCHAR2(32),
  ADDR        NVARCHAR2(64),
  STATUS      CHAR(1),
  ONLINE_FLAG CHAR(1),
  ACQ_ORG_ID  CHAR(5),
  ACQ_BANK_NO CHAR(5),
  EACQ_ORG_ID VARCHAR2(16)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table scott.T_B_ORG
  is '机构表
目前使用固话机构表';
comment on column scott.T_B_ORG.ORG_ID
  is '机构ID';
comment on column scott.T_B_ORG.P_ORG_ID
  is '上级机构编码';
comment on column scott.T_B_ORG.ORG_LEV
  is '机构级别';
comment on column scott.T_B_ORG.CUP_ID
  is '银联联行号';
comment on column scott.T_B_ORG.ZONE_CODE
  is '区域代码, 老4位行号';
comment on column scott.T_B_ORG.BANK_NO
  is '行号, 新eacq行号';
comment on column scott.T_B_ORG.NAME
  is '机构名称';
comment on column scott.T_B_ORG.CONTACT
  is '联系人';
comment on column scott.T_B_ORG.TEL
  is '联系电话';
comment on column scott.T_B_ORG.FAX
  is '传真号码';
comment on column scott.T_B_ORG.ADDR
  is '机构地址';
comment on column scott.T_B_ORG.STATUS
  is '状态 (0 关闭、1 正常、2 暂停、3 未启用)';
comment on column scott.T_B_ORG.ONLINE_FLAG
  is '新线系统标志, 新旧线标识 1上线 0 不上线';
comment on column scott.T_B_ORG.ACQ_ORG_ID
  is '核心网点机构号';
comment on column scott.T_B_ORG.ACQ_BANK_NO
  is '核心省行机构号';
comment on column scott.T_B_ORG.EACQ_ORG_ID
  is 'EACQ机构号(10位BRANCH_ID)';
alter table scott.T_B_ORG
  add constraint PK_T_B_ORG primary key (ORG_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_P_SHIRO_ACTION
prompt ===============================
prompt
create table scott.T_P_SHIRO_ACTION
(
  ACTION_ID    NUMBER(15) not null,
  ACTION_NAME  VARCHAR2(255) not null,
  ACTION_VALUE VARCHAR2(255),
  ACTION_ORDER NUMBER(4) not null,
  REMARK       VARCHAR2(128)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table scott.T_P_SHIRO_ACTION
  is '操作';
comment on column scott.T_P_SHIRO_ACTION.ACTION_ID
  is '动作ID';
comment on column scott.T_P_SHIRO_ACTION.ACTION_NAME
  is '名称';
comment on column scott.T_P_SHIRO_ACTION.ACTION_VALUE
  is '值';
comment on column scott.T_P_SHIRO_ACTION.ACTION_ORDER
  is '显示顺序';
comment on column scott.T_P_SHIRO_ACTION.REMARK
  is '描述';
alter table scott.T_P_SHIRO_ACTION
  add constraint PK_T_P_SHIRO_ACTION primary key (ACTION_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_P_SHIRO_FILTER_TYPE
prompt ====================================
prompt
create table scott.T_P_SHIRO_FILTER_TYPE
(
  TYPE_ID   NUMBER(4) not null,
  TYPE_NAME VARCHAR2(64) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table scott.T_P_SHIRO_FILTER_TYPE
  is '控制类型';
comment on column scott.T_P_SHIRO_FILTER_TYPE.TYPE_ID
  is '类型ID';
comment on column scott.T_P_SHIRO_FILTER_TYPE.TYPE_NAME
  is '名称';
alter table scott.T_P_SHIRO_FILTER_TYPE
  add constraint PK_T_P_SHIRO_FILTER_TYPE primary key (TYPE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_P_SHIRO_PERMIT
prompt ===============================
prompt
create table scott.T_P_SHIRO_PERMIT
(
  PERMIT_ID    NUMBER(15) not null,
  MENU_RES_ID  NUMBER(15),
  ACTION_ID    NUMBER(15),
  TYPE_ID      NUMBER(4),
  PERMIT_ORDER NUMBER(8)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table scott.T_P_SHIRO_PERMIT
  is '权限';
comment on column scott.T_P_SHIRO_PERMIT.PERMIT_ID
  is '权限ID';
comment on column scott.T_P_SHIRO_PERMIT.MENU_RES_ID
  is '菜单ID';
comment on column scott.T_P_SHIRO_PERMIT.ACTION_ID
  is '动作ID';
comment on column scott.T_P_SHIRO_PERMIT.TYPE_ID
  is '类型ID';
comment on column scott.T_P_SHIRO_PERMIT.PERMIT_ORDER
  is '加载顺序';
alter table scott.T_P_SHIRO_PERMIT
  add constraint PK_T_P_SHIRO_PERMIT primary key (PERMIT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_P_SHIRO_RES_TYPE
prompt =================================
prompt
create table scott.T_P_SHIRO_RES_TYPE
(
  TYPE_ID   NUMBER(4) not null,
  TYPE_NAME VARCHAR2(128) not null,
  REMARK    VARCHAR2(128)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column scott.T_P_SHIRO_RES_TYPE.TYPE_ID
  is '类型ID';
comment on column scott.T_P_SHIRO_RES_TYPE.TYPE_NAME
  is '名称';
comment on column scott.T_P_SHIRO_RES_TYPE.REMARK
  is '描述';
alter table scott.T_P_SHIRO_RES_TYPE
  add constraint PK_T_P_SHIRO_RES_TYPE primary key (TYPE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_P_SHIRO_RES
prompt ============================
prompt
create table scott.T_P_SHIRO_RES
(
  MENU_RES_ID   NUMBER(15) not null,
  MENU_NAME     NVARCHAR2(64),
  ACTION_NAME   VARCHAR2(128),
  P_MENU_RES_ID NUMBER(15),
  TYPE_ID       NUMBER(4),
  ORDER_ID      NUMBER(15),
  DSC           NVARCHAR2(64)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table scott.T_P_SHIRO_RES
  is '资源模型';
comment on column scott.T_P_SHIRO_RES.MENU_RES_ID
  is '菜单ID';
comment on column scott.T_P_SHIRO_RES.MENU_NAME
  is '菜单名称';
comment on column scott.T_P_SHIRO_RES.ACTION_NAME
  is '模型URL';
comment on column scott.T_P_SHIRO_RES.P_MENU_RES_ID
  is '父菜单ID';
comment on column scott.T_P_SHIRO_RES.TYPE_ID
  is '类型ID';
comment on column scott.T_P_SHIRO_RES.ORDER_ID
  is '序号';
comment on column scott.T_P_SHIRO_RES.DSC
  is '描述';
alter table scott.T_P_SHIRO_RES
  add constraint PK_P_MENU_RES_ primary key (MENU_RES_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table scott.T_P_SHIRO_RES
  add constraint FK_P_MENU_RES_REF_SELF_ foreign key (P_MENU_RES_ID)
  references scott.T_P_SHIRO_RES (MENU_RES_ID)
  disable;
alter table scott.T_P_SHIRO_RES
  add constraint FK_T_P_SHIRO_RES_REF_T_RES foreign key (TYPE_ID)
  references scott.T_P_SHIRO_RES_TYPE (TYPE_ID);

prompt
prompt Creating table T_P_SHIRO_RESTYPE_ACTION
prompt =======================================
prompt
create table scott.T_P_SHIRO_RESTYPE_ACTION
(
  TYPE_ID   NUMBER(4) not null,
  ACTION_ID NUMBER(15) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table scott.T_P_SHIRO_RESTYPE_ACTION
  is '资源类型操作';
comment on column scott.T_P_SHIRO_RESTYPE_ACTION.TYPE_ID
  is '类型ID';
comment on column scott.T_P_SHIRO_RESTYPE_ACTION.ACTION_ID
  is '动作ID';
alter table scott.T_P_SHIRO_RESTYPE_ACTION
  add constraint PK_P_TYPEID primary key (TYPE_ID, ACTION_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table scott.T_P_SHIRO_RESTYPE_ACTION
  add constraint FK_P_SHIRO_T_REST_REF_ACT foreign key (ACTION_ID)
  references scott.T_P_SHIRO_ACTION (ACTION_ID) on delete cascade;
alter table scott.T_P_SHIRO_RESTYPE_ACTION
  add constraint FK_P_SHIRO_T_REST_REF_T_RES foreign key (TYPE_ID)
  references scott.T_P_SHIRO_RES_TYPE (TYPE_ID);

prompt
prompt Creating table T_P_SHIRO_ROLE
prompt =============================
prompt
create table scott.T_P_SHIRO_ROLE
(
  ROLE_ID   NUMBER(15) not null,
  ROLE_NAME NVARCHAR2(64),
  DSC       NVARCHAR2(64)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column scott.T_P_SHIRO_ROLE.ROLE_ID
  is '角色ID';
comment on column scott.T_P_SHIRO_ROLE.ROLE_NAME
  is '名称';
comment on column scott.T_P_SHIRO_ROLE.DSC
  is '描述';
alter table scott.T_P_SHIRO_ROLE
  add constraint PK_T_P_SHIRO_ROLE primary key (ROLE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_P_SHIRO_ROLE_PERMIT
prompt ====================================
prompt
create table scott.T_P_SHIRO_ROLE_PERMIT
(
  ROLE_PERMIT_ID NUMBER(15) not null,
  PERMIT_ID      NUMBER(15),
  ROLE_ID        NUMBER(15),
  SORT           NUMBER(15)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column scott.T_P_SHIRO_ROLE_PERMIT.ROLE_PERMIT_ID
  is 'ID主键';
comment on column scott.T_P_SHIRO_ROLE_PERMIT.PERMIT_ID
  is '权限ID';
comment on column scott.T_P_SHIRO_ROLE_PERMIT.ROLE_ID
  is '角色ID';
comment on column scott.T_P_SHIRO_ROLE_PERMIT.SORT
  is '正(1)/逆(0)序';
alter table scott.T_P_SHIRO_ROLE_PERMIT
  add constraint PK_T_P_SHIRO_ROLE_PERMIT primary key (ROLE_PERMIT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table scott.T_P_SHIRO_ROLE_PERMIT
  add constraint FK_P_SHIRO_ROLE_REF_ROLE foreign key (ROLE_ID)
  references scott.T_P_SHIRO_ROLE (ROLE_ID);
alter table scott.T_P_SHIRO_ROLE_PERMIT
  add constraint FK_P_SHIRO_ROLE_REF_T_PERM foreign key (PERMIT_ID)
  references scott.T_P_SHIRO_PERMIT (PERMIT_ID);

prompt
prompt Creating table T_P_SHIRO_ROLE_RES
prompt =================================
prompt
create table scott.T_P_SHIRO_ROLE_RES
(
  RES_ROLE_ID NUMBER(15) not null,
  ROLE_ID     NUMBER(15),
  MENU_RES_ID NUMBER(15)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
comment on table scott.T_P_SHIRO_ROLE_RES
  is '角色-资源';
comment on column scott.T_P_SHIRO_ROLE_RES.RES_ROLE_ID
  is '主键ID';
comment on column scott.T_P_SHIRO_ROLE_RES.ROLE_ID
  is '角色ID';
comment on column scott.T_P_SHIRO_ROLE_RES.MENU_RES_ID
  is '菜单ID';
alter table scott.T_P_SHIRO_ROLE_RES
  add constraint PK_T_P_SHIRO_ROLE_RES primary key (RES_ROLE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
alter table scott.T_P_SHIRO_ROLE_RES
  add constraint FK_P_SHIRO_ROLE_REF_ROLE_RES foreign key (ROLE_ID)
  references scott.T_P_SHIRO_ROLE (ROLE_ID);
alter table scott.T_P_SHIRO_ROLE_RES
  add constraint FK_P_SHIRO_ROLE_REF_T_RES foreign key (MENU_RES_ID)
  references scott.T_P_SHIRO_RES (MENU_RES_ID);

prompt
prompt Creating table T_P_SHIRO_USER_GRP
prompt =================================
prompt
create table scott.T_P_SHIRO_USER_GRP
(
  USER_GRP_ID NUMBER(15) not null,
  NAME        NVARCHAR2(64) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column scott.T_P_SHIRO_USER_GRP.USER_GRP_ID
  is '用户分组ID';
comment on column scott.T_P_SHIRO_USER_GRP.NAME
  is '名称';
alter table scott.T_P_SHIRO_USER_GRP
  add constraint PK_T_P_SHIRO_USER_GRP primary key (USER_GRP_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_P_SHIRO_USER
prompt =============================
prompt
create table scott.T_P_SHIRO_USER
(
  USER_ID     VARCHAR2(15) not null,
  USER_GRP_ID NUMBER(15),
  LOGIN_NAME  VARCHAR2(32),
  PWD         VARCHAR2(32),
  USER_NAME   VARCHAR2(32)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column scott.T_P_SHIRO_USER.USER_ID
  is '用户ID';
comment on column scott.T_P_SHIRO_USER.USER_GRP_ID
  is '分组ID';
comment on column scott.T_P_SHIRO_USER.LOGIN_NAME
  is '登录名';
comment on column scott.T_P_SHIRO_USER.PWD
  is '密码';
comment on column scott.T_P_SHIRO_USER.USER_NAME
  is '用户姓名';
alter table scott.T_P_SHIRO_USER
  add constraint PK_T_P_SHIRO_USER primary key (USER_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table scott.T_P_SHIRO_USER
  add constraint UK_T_P_SHIRO_USER unique (LOGIN_NAME)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table scott.T_P_SHIRO_USER
  add constraint FK_T_P_SHIRO_USER_REF_USER_GRP foreign key (USER_GRP_ID)
  references scott.T_P_SHIRO_USER_GRP (USER_GRP_ID)
  disable;

prompt
prompt Creating table T_P_SHIRO_USER_EXT
prompt =================================
prompt
create table scott.T_P_SHIRO_USER_EXT
(
  USER_ID                     VARCHAR2(15) not null,
  ORG_ID                      VARCHAR2(16),
  STAFF_ID                    INTEGER,
  LAST_LOGIN_IP_ADDR          VARCHAR2(15),
  LAST_LOGIN_DATE_TIME        CHAR(14),
  LAST_LOGOUT_DATE_TIME       CHAR(14),
  LOGIN_CNT                   INTEGER,
  LAST_CHG_PWD_DATE_TIME      CHAR(14),
  CREATE_DATE_TIME            CHAR(14),
  CREATE_USER_ID              INTEGER,
  STATUS                      CHAR(1),
  LOGIN_FAILED_CNT            INTEGER,
  LAST_LOGIN_FAILED_DATE_TIME CHAR(14)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column scott.T_P_SHIRO_USER_EXT.USER_ID
  is '用户ID';
comment on column scott.T_P_SHIRO_USER_EXT.ORG_ID
  is '机构';
comment on column scott.T_P_SHIRO_USER_EXT.LAST_LOGIN_IP_ADDR
  is '最后登录IP';
comment on column scott.T_P_SHIRO_USER_EXT.LAST_LOGIN_DATE_TIME
  is '最后登录时间';
comment on column scott.T_P_SHIRO_USER_EXT.LAST_LOGOUT_DATE_TIME
  is '最后登出时间';
comment on column scott.T_P_SHIRO_USER_EXT.LOGIN_CNT
  is '登录总次数';
comment on column scott.T_P_SHIRO_USER_EXT.LAST_CHG_PWD_DATE_TIME
  is '最后修改密码时间';
comment on column scott.T_P_SHIRO_USER_EXT.CREATE_DATE_TIME
  is '创建时间';
comment on column scott.T_P_SHIRO_USER_EXT.CREATE_USER_ID
  is '创建用户';
comment on column scott.T_P_SHIRO_USER_EXT.STATUS
  is '用户状态(0:启用,1停用)';
comment on column scott.T_P_SHIRO_USER_EXT.LOGIN_FAILED_CNT
  is '用户登录失败次数';
comment on column scott.T_P_SHIRO_USER_EXT.LAST_LOGIN_FAILED_DATE_TIME
  is '最后登录失败时间';
alter table scott.T_P_SHIRO_USER_EXT
  add constraint PK_T_P_SHIRO_USER_EXT primary key (USER_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table scott.T_P_SHIRO_USER_EXT
  add constraint FK_P_SHIRO_U_REF_USER foreign key (USER_ID)
  references scott.T_P_SHIRO_USER (USER_ID);

prompt
prompt Creating table T_P_SHIRO_USER_GRP_EXT
prompt =====================================
prompt
create table scott.T_P_SHIRO_USER_GRP_EXT
(
  USER_GRP_ID        NUMBER(15) not null,
  ORG_ID             VARCHAR2(16) not null,
  RUNTIME_LIMIT_FLAG CHAR(1) not null,
  IHT_FLAG           CHAR(1) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column scott.T_P_SHIRO_USER_GRP_EXT.USER_GRP_ID
  is '用户分组ID';
comment on column scott.T_P_SHIRO_USER_GRP_EXT.ORG_ID
  is '机构ID';
comment on column scott.T_P_SHIRO_USER_GRP_EXT.RUNTIME_LIMIT_FLAG
  is '工作日期限制标志(0:限制1:不限制)';
comment on column scott.T_P_SHIRO_USER_GRP_EXT.IHT_FLAG
  is '继承标志(0:可以继承1:不可继承)';
alter table scott.T_P_SHIRO_USER_GRP_EXT
  add constraint PK_T_P_SHIRO_USER_GRP_EXT primary key (USER_GRP_ID)
  disable;

prompt
prompt Creating table T_P_SHIRO_USER_GRP_ROLE_IDX
prompt ==========================================
prompt
create table scott.T_P_SHIRO_USER_GRP_ROLE_IDX
(
  ROLE_ID     NUMBER(15) not null,
  USER_GRP_ID NUMBER(15) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column scott.T_P_SHIRO_USER_GRP_ROLE_IDX.ROLE_ID
  is '角色ID';
comment on column scott.T_P_SHIRO_USER_GRP_ROLE_IDX.USER_GRP_ID
  is '用户分组ID';
alter table scott.T_P_SHIRO_USER_GRP_ROLE_IDX
  add constraint PK_T_P_SHIRO_USER_GRP_ROLE_IDX primary key (ROLE_ID, USER_GRP_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table scott.T_P_SHIRO_USER_GRP_ROLE_IDX
  add constraint FK_T_P_SHIRO_UGRI_REF_P_ROLE foreign key (ROLE_ID)
  references scott.T_P_SHIRO_ROLE (ROLE_ID)
  disable;
alter table scott.T_P_SHIRO_USER_GRP_ROLE_IDX
  add constraint FK_T_P_SHIRO_UR_REF_USER_GRP foreign key (USER_GRP_ID)
  references scott.T_P_SHIRO_USER_GRP (USER_GRP_ID)
  disable;

prompt
prompt Creating table T_P_S_DICT_ITEM
prompt ==============================
prompt
create table scott.T_P_S_DICT_ITEM
(
  DICTTYPE        VARCHAR2(30) not null,
  DICTCODE        VARCHAR2(20) not null,
  DICTCODENAME    VARCHAR2(100),
  DICTPINYIN      VARCHAR2(50),
  DICTORDER       VARCHAR2(4),
  DICTFLAG        VARCHAR2(1),
  DICTCODE_PARENT VARCHAR2(15)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column scott.T_P_S_DICT_ITEM.DICTTYPE
  is '字典类别';
comment on column scott.T_P_S_DICT_ITEM.DICTCODE
  is '字典项代码';
comment on column scott.T_P_S_DICT_ITEM.DICTCODENAME
  is '字典项名称';
comment on column scott.T_P_S_DICT_ITEM.DICTPINYIN
  is '拼音';
comment on column scott.T_P_S_DICT_ITEM.DICTORDER
  is '顺序';
comment on column scott.T_P_S_DICT_ITEM.DICTFLAG
  is '标志';
comment on column scott.T_P_S_DICT_ITEM.DICTCODE_PARENT
  is '字典项父项';
alter table scott.T_P_S_DICT_ITEM
  add constraint PK_DICT_ITEM_TYPE_CODE primary key (DICTTYPE, DICTCODE)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index scott.PS_DICT_CON on scott.T_P_S_DICT_ITEM (DICTCODENAME)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index scott.PS_DICT_DTNAME on scott.T_P_S_DICT_ITEM (DICTTYPE)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index scott.PS_DICT_PY on scott.T_P_S_DICT_ITEM (DICTPINYIN)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index scott.PS_DICT_PY_NAME on scott.T_P_S_DICT_ITEM (DICTTYPE, DICTPINYIN)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_P_S_DICT_LIST
prompt ==============================
prompt
create table scott.T_P_S_DICT_LIST
(
  DICTTYPE        VARCHAR2(40) not null,
  DICTTYPENAME    VARCHAR2(40),
  DICTFLAG        VARCHAR2(1),
  DICTTABLE_SQL   VARCHAR2(200),
  DICTTABLE_ORDER VARCHAR2(200)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column scott.T_P_S_DICT_LIST.DICTTYPE
  is '字典类别';
comment on column scott.T_P_S_DICT_LIST.DICTTYPENAME
  is '字典类别名称';
comment on column scott.T_P_S_DICT_LIST.DICTFLAG
  is '1：取自字典 2：取自表';
comment on column scott.T_P_S_DICT_LIST.DICTTABLE_SQL
  is '2：取自表的SQL';
comment on column scott.T_P_S_DICT_LIST.DICTTABLE_ORDER
  is '2：取自表的SQL顺序';
alter table scott.T_P_S_DICT_LIST
  add constraint PK_DICT_LIST_DICTTYPE primary key (DICTTYPE)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_WEB_P_OPERATION_LOGINFO
prompt ========================================
prompt
create table scott.T_WEB_P_OPERATION_LOGINFO
(
  ID                 VARCHAR2(32) not null,
  MODULE_NAME        VARCHAR2(30),
  METHOD_DESCRIPTION VARCHAR2(100),
  ORG_ID             VARCHAR2(16),
  USER_NAME          VARCHAR2(20),
  OPERATOR_TIME      VARCHAR2(14),
  SYSFLAG            CHAR(1),
  MER_NO             CHAR(15),
  IP                 VARCHAR2(32)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table scott.T_WEB_P_OPERATION_LOGINFO
  is '集合日志';
comment on column scott.T_WEB_P_OPERATION_LOGINFO.MODULE_NAME
  is '模块名称';
comment on column scott.T_WEB_P_OPERATION_LOGINFO.METHOD_DESCRIPTION
  is '方法说明';
comment on column scott.T_WEB_P_OPERATION_LOGINFO.ORG_ID
  is '机构名称';
comment on column scott.T_WEB_P_OPERATION_LOGINFO.USER_NAME
  is '操作用户';
comment on column scott.T_WEB_P_OPERATION_LOGINFO.OPERATOR_TIME
  is '操作时间';
comment on column scott.T_WEB_P_OPERATION_LOGINFO.SYSFLAG
  is '操作系统(0 行内系统, 1 商户服务系统)';
comment on column scott.T_WEB_P_OPERATION_LOGINFO.MER_NO
  is '商户号';
comment on column scott.T_WEB_P_OPERATION_LOGINFO.IP
  is 'IP';
alter table scott.T_WEB_P_OPERATION_LOGINFO
  add constraint PK_OPER_LOGINFO primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index scott.INDX_WEB_P_OPERATION_ORG_ID on scott.T_WEB_P_OPERATION_LOGINFO (ORG_ID)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index scott.INDX_WEB_P_OPERATION_USER_NAME on scott.T_WEB_P_OPERATION_LOGINFO (USER_NAME)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index scott.INDX_WEB_P_OP_OPERATOR_TIME on scott.T_WEB_P_OPERATION_LOGINFO (OPERATOR_TIME)
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_WEB_P_OPERATION_LOGINFO_EXT
prompt ============================================
prompt
create table scott.T_WEB_P_OPERATION_LOGINFO_EXT
(
  ID         VARCHAR2(32) not null,
  OPERATION  VARCHAR2(32),
  O_VALUES   VARCHAR2(4000),
  C_VALUES   VARCHAR2(4000),
  TABLE_NAME VARCHAR2(256),
  LOGINFO_ID VARCHAR2(32) not null,
  DEC        VARCHAR2(256)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table scott.T_WEB_P_OPERATION_LOGINFO_EXT
  is '集合日志辅助表';
comment on column scott.T_WEB_P_OPERATION_LOGINFO_EXT.OPERATION
  is '操作的Action';
comment on column scott.T_WEB_P_OPERATION_LOGINFO_EXT.O_VALUES
  is '操作前的值';
comment on column scott.T_WEB_P_OPERATION_LOGINFO_EXT.C_VALUES
  is '操作后的值';
comment on column scott.T_WEB_P_OPERATION_LOGINFO_EXT.TABLE_NAME
  is '操作的表';
comment on column scott.T_WEB_P_OPERATION_LOGINFO_EXT.LOGINFO_ID
  is '集合表外键';
comment on column scott.T_WEB_P_OPERATION_LOGINFO_EXT.DEC
  is '备注';
alter table scott.T_WEB_P_OPERATION_LOGINFO_EXT
  add constraint PK_OPERATION_LOGINFO primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table scott.T_WEB_P_OPERATION_LOGINFO_EXT
  add constraint FK_OPERATION_LOGINFO_EXT foreign key (LOGINFO_ID)
  references scott.T_WEB_P_OPERATION_LOGINFO (ID) on delete cascade
  disable;

prompt
prompt Creating sequence SEQ_T_WEB_OPERTION_LOGINFO
prompt ============================================
prompt
create sequence scott.SEQ_T_WEB_OPERTION_LOGINFO
minvalue 0
maxvalue 999999999999999999
start with 26242
increment by 1
nocache;

prompt
prompt Creating sequence SEQ_T_WEB_OPERTION_LOGINFO_EXT
prompt ================================================
prompt
create sequence scott.SEQ_T_WEB_OPERTION_LOGINFO_EXT
minvalue 0
maxvalue 9999999999999999999
start with 24080
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_ACTIVITY
prompt ================================
prompt
create sequence scott.T_SEQ_ACTIVITY
minvalue 1
maxvalue 9999999999999999999999999999
start with 10000000562
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_ACTIVITY_PRODUCT_ENJOY
prompt ==============================================
prompt
create sequence scott.T_SEQ_ACTIVITY_PRODUCT_ENJOY
minvalue 1
maxvalue 9999999999999999999999999999
start with 10000000682
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_PRODUCT_BASE
prompt ====================================
prompt
create sequence scott.T_SEQ_PRODUCT_BASE
minvalue 1
maxvalue 9999999999999999999999999999
start with 10000000020
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_PRODUCT_ENJOY
prompt =====================================
prompt
create sequence scott.T_SEQ_PRODUCT_ENJOY
minvalue 1
maxvalue 9999999999999999999999999999
start with 100000040
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_P_SHIRO_ACTION
prompt ======================================
prompt
create sequence scott.T_SEQ_P_SHIRO_ACTION
minvalue 1
maxvalue 999999999999999999999
start with 2000000
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_P_SHIRO_FITLER_TYPE
prompt ===========================================
prompt
create sequence scott.T_SEQ_P_SHIRO_FITLER_TYPE
minvalue 1
maxvalue 9999999999999999999999999999
start with 20000000020
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_P_SHIRO_PERMIT
prompt ======================================
prompt
create sequence scott.T_SEQ_P_SHIRO_PERMIT
minvalue 1
maxvalue 9999999999999999999999999999
start with 420000960
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_P_SHIRO_RES
prompt ===================================
prompt
create sequence scott.T_SEQ_P_SHIRO_RES
minvalue 1
maxvalue 9999999999999999999999999999
start with 250840
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_P_SHIRO_RES_TYPE
prompt ========================================
prompt
create sequence scott.T_SEQ_P_SHIRO_RES_TYPE
minvalue 1
maxvalue 9999999999999999999999999999
start with 20000020
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_P_SHIRO_ROLE
prompt ====================================
prompt
create sequence scott.T_SEQ_P_SHIRO_ROLE
minvalue 1
maxvalue 9999999999999999999999999999
start with 60100
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_P_SHIRO_ROLE_PERMIT
prompt ===========================================
prompt
create sequence scott.T_SEQ_P_SHIRO_ROLE_PERMIT
minvalue 1
maxvalue 9999999999999999999999999999
start with 3202500
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_P_SHIRO_USER
prompt ====================================
prompt
create sequence scott.T_SEQ_P_SHIRO_USER
minvalue 1
maxvalue 9999999999999999999999999999
start with 6000360
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_P_SHIRO_USER_GRP
prompt ========================================
prompt
create sequence scott.T_SEQ_P_SHIRO_USER_GRP
minvalue 1
maxvalue 9999999999999999999999999999
start with 6000160
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_SHIRO_ACTION
prompt ====================================
prompt
create sequence scott.T_SEQ_SHIRO_ACTION
minvalue 1
maxvalue 999999999999999999999
start with 60
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_SHIRO_FITLER_TYPE
prompt =========================================
prompt
create sequence scott.T_SEQ_SHIRO_FITLER_TYPE
minvalue 1
maxvalue 9999999999999999999999999999
start with 60
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_SHIRO_PERMIT
prompt ====================================
prompt
create sequence scott.T_SEQ_SHIRO_PERMIT
minvalue 1
maxvalue 9999999999999999999999999999
start with 900
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_SHIRO_RES
prompt =================================
prompt
create sequence scott.T_SEQ_SHIRO_RES
minvalue 1
maxvalue 9999999999999999999999999999
start with 700
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_SHIRO_RES_TYPE
prompt ======================================
prompt
create sequence scott.T_SEQ_SHIRO_RES_TYPE
minvalue 1
maxvalue 9999999999999999999999999999
start with 50
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_SHIRO_ROLE
prompt ==================================
prompt
create sequence scott.T_SEQ_SHIRO_ROLE
minvalue 1
maxvalue 9999999999999999999999999999
start with 60
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_SHIRO_ROLE_PERMIT
prompt =========================================
prompt
create sequence scott.T_SEQ_SHIRO_ROLE_PERMIT
minvalue 1
maxvalue 9999999999999999999999999999
start with 900
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_SHIRO_USER
prompt ==================================
prompt
create sequence scott.T_SEQ_SHIRO_USER
minvalue 1
maxvalue 999999999999999999999
start with 60
increment by 1
cache 20;

prompt
prompt Creating sequence T_SEQ_SHIRO_USER_GRP
prompt ======================================
prompt
create sequence scott.T_SEQ_SHIRO_USER_GRP
minvalue 1
maxvalue 9999999999999999999999999999
start with 60
increment by 1
cache 20;

prompt
prompt Creating view MERHCANT_ALL
prompt ==========================
prompt
create or replace view scott.merhcant_all as
select mer_no from merchant_base
union all
select mer_no from merchant_enjoy;

prompt
prompt Creating view T_V_TRAN_LIST
prompt ===========================
prompt
create or replace view scott.t_v_tran_list as
select p.p_org_id,
       p.p_org_Name,
       p.org_id,
       p.org_name,
       p.mer_no,
       p.mer_name,
       p.product_name,
       p.tran_date,
       sum(p.active_sum) as active_sum,
       sum(p.tran_count) as tran_count,
       sum(p.nomal_sum) as nomal_sum
 from (select t1.p_org_id,
       t1.p_Org_Name,
       t1.org_id,
       t1.org_name,
       t1.mer_no,
       t1.mer_name,
       t1.product_name,
       t1.tran_date,
       sum(t1.integral_value) as nomal_sum,
       sum(t1.tran_count) as tran_count,
       0 as active_sum
 from tran_list t1
  where t1.activity_id = 'nomal'
   and t1.tran_state = '00'
   and t1.state = '00'
  group by t1.p_org_id,
       t1.p_Org_Name,
       t1.org_id,
       t1.org_name,
       t1.mer_no,
       t1.mer_name,
       t1.product_name,
       t1.tran_date
union all
select t2.p_org_id,
       t2.p_Org_Name,
       t2.org_id,
       t2.org_name,
       t2.mer_no,
       t2.mer_name,
       t2.product_name,
       t2.tran_date,
       0 as nomal_sum,
       sum(t2.tran_count) as tran_count,
       sum(t2.integral_value) as active_sum
  from tran_list t2
  where t2.activity_id <> 'nomal'
         and t2.tran_state = '00'
         and t2.state = '00'
  group by t2.p_org_id,
       t2.p_Org_Name,
       t2.org_id,
       t2.org_name,
       t2.mer_no,
       t2.mer_name,
       t2.product_name,
       t2.tran_date) p
   group by p.p_org_id,
       p.p_Org_Name,
       p.org_id,
       p.org_name,
       p.mer_no,
       p.mer_name,
       p.product_name,
       p.tran_date;


spool off
