/*******************************************************************************
   Oracle Database Script
   Project: Staging Messaging Portal (SMP)
   Description: Creates DB Schema for SMP
   DB Server: staging-smp.cvoui7q38caj.us-east-2.rds.amazonaws.com:1521:ORCL
   Author: Richard C. Smith
   Company: Revature LLC
   Date of creation: 2017/08/27
   License: Copyright 2017 Revature
*******************************************************************************/

/*******************************************************************************
   Drop Tables - Before Creation
*******************************************************************************/
DROP TABLE USER_MESSAGE_JUNC;
DROP TABLE USER_TABLE;
DROP TABLE MESSAGE_TABLE;
DROP TABLE MESSAGE_CACHE;
DROP TABLE MESSAGE_ROOM;
DROP TABLE USER_ROLE;
DROP TABLE USER_LOCATION;
DROP TABLE USER_STATUS;
DROP TABLE LOGS;

/*******************************************************************************
   Create Tables
*******************************************************************************/
CREATE TABLE USER_TABLE
(
  user_id NUMBER(38,0),
  username VARCHAR2(20),
  passwd VARCHAR2(20),
  firstname VARCHAR2(20),
  lastname VARCHAR2(20),
  email VARCHAR2(50),
  location_id NUMBER(38,0),
  status_id NUMBER(38,0),
  role_id NUMBER(38,0),
  logged VARCHAR2(1),
  active VARCHAR2(1),
  use_temp VARCHAR2(1),
  created DATE,
  CONSTRAINT PK_USER_ID PRIMARY KEY (user_id)
);

CREATE TABLE USER_ROLE
(
  role_id NUMBER(38,0),
  role_name VARCHAR2(20),
  CONSTRAINT PK_ROLE_ID PRIMARY KEY (role_id)
);

CREATE TABLE USER_LOCATION
(
  location_id NUMBER(38,0),
  location_name VARCHAR2(20),
  CONSTRAINT PK_LOCATION_ID PRIMARY KEY (location_id)
);

CREATE TABLE USER_STATUS
(
  status_id NUMBER(38,0),
  status_name VARCHAR2(20),
  CONSTRAINT PK_STATUS_ID PRIMARY KEY (status_id)
);

CREATE TABLE MESSAGE_ROOM
(
  room_id NUMBER(38,0),
  room_name VARCHAR2(50),
  CONSTRAINT PK_ROOM_ID PRIMARY KEY (room_id)
);

CREATE TABLE MESSAGE_TABLE
(
  message_id NUMBER(38,0),
  room_id NUMBER(38,0),
  username VARCHAR2(20),
  message_text VARCHAR2(1000),
  message_time NUMBER(15,0),
  CONSTRAINT PK_MESSAGE_ID PRIMARY KEY (message_id)
);

CREATE TABLE MESSAGE_CACHE
(
  cache_id NUMBER(38,0),
  room_id NUMBER(38,0),
  message_clob CLOB,
  created DATE,
  CONSTRAINT PK_CACHE_ID PRIMARY KEY (cache_id)
);

CREATE TABLE USER_MESSAGE_JUNC
(
  user_id NUMBER(38,0),
  room_id NUMBER(38,0),
  CONSTRAINT CK_USERMESSAGE_ID PRIMARY KEY (user_id, room_id)
);

CREATE TABLE LOGS
(
  log_id NUMBER,
  log_type varchar2(10),
  log_text varchar2(255),
  created DATE,
  CONSTRAINT PK_LOG_ID PRIMARY KEY (log_id)
);

/*******************************************************************************
   Create Foreign Keys
*******************************************************************************/
ALTER TABLE USER_TABLE ADD CONSTRAINT FK_USER_LOCATION
    FOREIGN KEY (location_id) REFERENCES USER_LOCATION (location_id);
    
ALTER TABLE USER_TABLE ADD CONSTRAINT FK_USER_STATUS
    FOREIGN KEY (status_id) REFERENCES USER_STATUS (status_id);
    
ALTER TABLE USER_TABLE ADD CONSTRAINT FK_USER_ROLE
    FOREIGN KEY (role_id) REFERENCES USER_ROLE (role_id);
    
ALTER TABLE MESSAGE_TABLE ADD CONSTRAINT FK_MSG_ROOM
    FOREIGN KEY (room_id) REFERENCES MESSAGE_ROOM (room_id);
    
ALTER TABLE MESSAGE_CACHE ADD CONSTRAINT FK_CACHE_ROOM
    FOREIGN KEY (room_id) REFERENCES MESSAGE_ROOM (room_id);

/*******************************************************************************
   Drop Sequences
*******************************************************************************/
DROP SEQUENCE SEQ_CACHE;
DROP SEQUENCE SEQ_LOGS;

/*******************************************************************************
   Create Sequences
*******************************************************************************/
CREATE SEQUENCE SEQ_CACHE
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 15;
  
CREATE SEQUENCE SEQ_LOGS
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 15;
  
/*******************************************************************************
   Create Triggers
*******************************************************************************/
CREATE OR REPLACE TRIGGER TRIG_CACHE
AFTER INSERT ON MESSAGE_TABLE
FOR EACH ROW
DECLARE
  msg_cnt NUMBER;
  err_user_exists EXCEPTION;
BEGIN
  SELECT COUNT(*) INTO msg_cnt FROM MESSAGE_TABLE 
    WHERE MESSAGE_TABLE.room_id = :new.room_id;
    
  IF( msg_cnt >= 25 ) THEN  
    message_pkg.create_message_cache_v1(:new.room_id);
  END IF;
END;
/  

/*******************************************************************************
   PACKAGES
*******************************************************************************/
--MESSAGE LOGIC PACKAGE DECLARATION
CREATE OR REPLACE PACKAGE message_pkg
  IS
  --FUNCTION SIGNATURES
  
  --PROCEDURE SIGNATURES
  PROCEDURE create_message_cache_v1(in_room_id IN NUMBER);

END message_pkg;
/

--MESSAGE PACKAGE BODY
CREATE OR REPLACE PACKAGE BODY message_pkg
  IS
  -- FUNCTION BODIES ------------------------------

  -- PROCEDURE BODIES ------------------------------
  PROCEDURE create_message_cache_v1(in_room_id IN NUMBER)
  IS
    this_message_id MESSAGE_TABLE.message_id%type;  
    this_room_id MESSAGE_TABLE.room_id%type;  
    this_username MESSAGE_TABLE.username%type; 
    this_message_text MESSAGE_TABLE.message_text%type;
    this_message_time MESSAGE_TABLE.message_time%type;
    
    msg_record VARCHAR2(2000);
    msg_arr VARCHAR2(30000);
    err_cache_fail EXCEPTION;
    
    CURSOR msg_cur
    IS
    SELECT *
    FROM MESSAGE_TABLE
    WHERE MESSAGE_TABLE.room_id = in_room_id;
  
  BEGIN
    OPEN msg_cur;  
    LOOP  
      FETCH msg_cur INTO this_message_id, this_room_id, this_username,
        this_message_text, this_message_time;  
      EXIT WHEN msg_cur%NOTFOUND;  
      
      msg_record := this_message_id + '#*&' + this_room_id + '#*&' + this_username 
        + '#*&' + this_message_text + '#*&' + this_message_time + '%@$';
        
      msg_arr := CONCAT(msg_arr, msg_record); 
        
    END LOOP;  
    CLOSE msg_cur;  
    
    INSERT INTO MESSAGE_CACHE(cache_id, room_id, message_clob, created )
      VALUES(SEQ_CACHE.NEXTVAL, in_room_id, TO_CLOB(msg_arr), SYSTIMESTAMP);
    
    IF SQL%NOTFOUND THEN
      RAISE err_cache_fail;
    END IF;
  
    EXCEPTION
      WHEN err_cache_fail THEN
        INSERT INTO LOGS(log_id, log_type, log_text, created) 
          VALUES(SEQ_CACHE.NEXTVAL, 'err', 'CREATE CACHE FAILED', SYSTIMESTAMP);
      
      WHEN OTHERS THEN
        INSERT INTO LOGS(log_id, log_type, log_text, created) 
          VALUES(SEQ_CACHE.NEXTVAL, 'err', 'OTHER CACHE FAILURE', SYSTIMESTAMP);
    
  END create_message_cache_v1;

END; --END PACKAGE BODY
/

/*******************************************************************************
   Commit Changes and Exit
*******************************************************************************/
COMMIT;
EXIT;
