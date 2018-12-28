CREATE TABLE BankUser(
    UserId NUMBER Primary Key,
    FirstName VARCHAR(20) NOT NULL,
    LastName VARCHAR(20) NOT NULL,
    UserName VARCHAR(20) NOT NULL,
    PasswordUser VARCHAR(20) NOT NULL
);

CREATE TABLE UserAccount(
    AccountId NUMBER PRIMARY KEY,
    AccountType NUMBER,
    OwnerAccount NUMBER,
    Balance NUMBER,
    FOREIGN KEY (OwnerAccount) REFERENCES BankUser (UserId),
    FOREIGN KEY (AccountType) REFERENCES AccountType(TypeId)
);

CREATE TABLE AccountType(
    TypeId NUMBER PRIMARY KEY,
    BankType VARCHAR(15) NOT NULL
);

CREATE SEQUENCE UserId_SEQ
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE AccountId_SEQ
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER BankUserTrig
BEFORE INSERT ON BankUser
FOR EACH ROW
BEGIN
SELECT UserId_SEQ.NEXTVAL INTO :NEW.UserId FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER UserAccountTrig
BEFORE INSERT ON UserAccount
FOR EACH ROW
BEGIN
SELECT UserId_SEQ.NEXTVAL INTO :NEW.AccountId FROM DUAL;
END;
/


create or replace procedure get_all_users(user_cursor OUT SYS_REFCURSOR)
AS
BEGIN
OPEN user_cursor FOR select * from BankUser;
  end;
  /


insert into accounttype (typeid, banktype) values(1, 'Credit');
insert into accounttype (typeid, banktype) values(2, 'Savings');
insert into accounttype (typeid, banktype) values(3, 'Checking');


select * from accounttype;
select * from bankuser;
select *  from UserAccount, (select UserId from BankUser) where OwnerAccount = UserId;