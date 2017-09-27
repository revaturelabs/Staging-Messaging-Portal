/*******************************************************************************
   Oracle Database Script
   Project: Staging Messaging Portal (SMP)
   Description: Creates DB Schema for SMP
   DB Server: staging-smp.cvoui7q38caj.us-east-2.rds.amazonaws.com:1521:ORCL
   Author: Richard C. Smith
   Company: Revature LLC
   Date of creation: 2017/08/27
   Version: 2017.09.21
   License: Copyright 2017 Revature
*******************************************************************************/

/*******************************************************************************
   Drop Tables - Before Creation
*******************************************************************************/
DROP TABLE USER_MSGROOM_JUNC;
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
  status_id NUMBER(38,0) DEFAULT 1,
  role_id NUMBER(1,0) DEFAULT 2,
  loggedin VARCHAR2(1) DEFAULT 'n',
  active VARCHAR2(1) DEFAULT 'n',
  use_temp VARCHAR2(1) DEFAULT 'y',
  created DATE DEFAULT SYSTIMESTAMP,
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
  message_time DATE,
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

CREATE TABLE USER_MSGROOM_JUNC
(
  user_id NUMBER(38,0),
  room_id NUMBER(38,0),
  CONSTRAINT CK_USERMSGROOM_ID PRIMARY KEY (user_id, room_id)
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
DROP SEQUENCE SEQ_USER;
DROP SEQUENCE SEQ_MSGROOM;
DROP SEQUENCE SEQ_MESSAGE;
DROP SEQUENCE SEQ_CACHE;
DROP SEQUENCE SEQ_LOGS;

/*******************************************************************************
   Create Sequences
*******************************************************************************/
CREATE SEQUENCE SEQ_USER
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 15;

CREATE SEQUENCE SEQ_MSGROOM
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 15;
  
CREATE SEQUENCE SEQ_MESSAGE
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 15;

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
CREATE OR REPLACE TRIGGER TRIG_USER_UPDATE_ACTIVE
  AFTER UPDATE ON USER_TABLE
  FOR EACH ROW
  DECLARE
    room_cnt NUMBER;
  BEGIN
    IF LOWER(:old.active)='n' AND LOWER(:new.active)='y' THEN
      SELECT COUNT(*) INTO room_cnt FROM MESSAGE_ROOM
        WHERE MESSAGE_ROOM.room_name = CONCAT('priv_',:new.username);
        
      IF room_cnt < 1 THEN
        msg_pkg.create_private_room(:new.user_id, :new.username);
      END IF;
    END IF;
END;
/

/* DISABLING CACHE FOR NOW
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
      msg_pkg.create_message_cache_v1(:new.room_id);
    END IF;
END;
/  
*/

/*******************************************************************************
   PACKAGES
*******************************************************************************/
--MESSAGE LOGIC PACKAGE DECLARATION
CREATE OR REPLACE PACKAGE msg_pkg
  IS
  --FUNCTION SIGNATURES
  FUNCTION get_public_roomid RETURN NUMBER;
  
  --PROCEDURE SIGNATURES
  PROCEDURE create_private_room(in_user_id IN NUMBER, in_username IN VARCHAR2);
  PROCEDURE create_message_cache_v1(in_room_id IN NUMBER);
END msg_pkg;
/

CREATE OR REPLACE PACKAGE log_pkg
  IS
  --FUNCTION SIGNATURES
  
  --PROCEDURE SIGNATURES
  PROCEDURE log_dbms_file_v1(in_dirpath IN VARCHAR2, 
    in_filename IN VARCHAR2);
END log_pkg;
/
  
--MESSAGE PACKAGE BODY
CREATE OR REPLACE PACKAGE BODY msg_pkg
  IS
  -- FUNCTION BODIES ------------------------------
  FUNCTION get_public_roomid RETURN NUMBER 
  IS
    public_roomid NUMBER;
  BEGIN 
    SELECT MESSAGE_ROOM.room_id INTO public_roomid FROM MESSAGE_ROOM
      WHERE MESSAGE_ROOM.room_name = 'public';
      
    RETURN public_roomid;
    
    EXCEPTION 
      WHEN NO_DATA_FOUND THEN RETURN 0;
  END;
  
  -- PROCEDURE BODIES ------------------------------
  PROCEDURE create_private_room(in_user_id IN NUMBER, in_username IN VARCHAR2)
  IS
    this_room_id NUMBER;
    this_room_name VARCHAR2(50);
  BEGIN
    this_room_name := CONCAT('priv_',in_username);
  
    INSERT INTO MESSAGE_ROOM (room_id, room_name)
      VALUES (SEQ_MSGROOM.NEXTVAL, this_room_name);
      
    SELECT MESSAGE_ROOM.room_id INTO this_room_id FROM MESSAGE_ROOM
      WHERE MESSAGE_ROOM.room_name = this_room_name;
    
    INSERT INTO USER_MSGROOM_JUNC(user_id, room_id) 
      VALUES(in_user_id, 1);
      
    INSERT INTO USER_MSGROOM_JUNC(user_id, room_id) 
      VALUES(in_user_id, this_room_id);
      
    INSERT INTO MESSAGE_TABLE (message_id, room_id, username, message_text,
      message_time)
      VALUES (SEQ_MESSAGE.NEXTVAL, this_room_id, 'Revature',
        'Welcome to your private channel with the staging manager.',
        systimestamp);
  
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        INSERT INTO LOGS(log_id, log_type, log_text, created) 
          VALUES(SEQ_LOGS.NEXTVAL, 'err', 'ERROR FINDING ROOM ID', SYSTIMESTAMP);
  
  END create_private_room;
  
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
    SAVEPOINT before_process;
    OPEN msg_cur;  
    LOOP  
      FETCH msg_cur INTO this_message_id, this_room_id, this_username,
        this_message_text, this_message_time;  
      EXIT WHEN msg_cur%NOTFOUND;  
      
      msg_record := this_message_id + '#*&' + this_room_id + '#*&' + this_username 
        + '#*&' + this_message_text + '#*&' + this_message_time + '%@$';
        
      msg_arr := CONCAT(msg_arr, msg_record);
      
      --Commenting out this step so for the first iterations of the project
      --we will only be using MESSAGE_TABLE and logic to pull messages
      --DELETE FROM MESSAGE_TABLE WHERE MESSAGE_TABLE.message_id = this_message_id;
    END LOOP;  
    CLOSE msg_cur;
    
    --store records as clob into message cache table
    INSERT INTO MESSAGE_CACHE(cache_id, room_id, message_clob, created )
      VALUES(SEQ_CACHE.NEXTVAL, in_room_id, TO_CLOB(msg_arr), SYSTIMESTAMP);
    
    IF SQL%NOTFOUND THEN
      ROLLBACK TO SAVEPOINT before_process;
      RAISE err_cache_fail;
    END IF;
  
    EXCEPTION
      WHEN err_cache_fail THEN
        INSERT INTO LOGS(log_id, log_type, log_text, created) 
          VALUES(SEQ_LOGS.NEXTVAL, 'err', 'CREATE CACHE FAILED', SYSTIMESTAMP);
      
      WHEN OTHERS THEN
        INSERT INTO LOGS(log_id, log_type, log_text, created) 
          VALUES(SEQ_LOGS.NEXTVAL, 'err', 'OTHER CACHE FAILURE', SYSTIMESTAMP);
    
  END create_message_cache_v1;

END; --END message_pkg PACKAGE BODY
/

--LOG PACKAGE BODY
--CREATE OR REPLACE PACKAGE BODY log_pkg
--  IS
  -- FUNCTION BODIES ------------------------------

  -- PROCEDURE BODIES ------------------------------
--  PROCEDURE log_dbms_file_v1(in_dirpath IN VARCHAR2, 
--    in_filename IN VARCHAR2)
--  IS
   
--  BEGIN

--  END log_dbms_file_v1;
--END; --END log_pkg PACKAGE BODY
--/
/*******************************************************************************
   Insert static records for LOCATION, ROLE, STATUS via single INSERT ALL
*******************************************************************************/
INSERT ALL
  --insert locations
  INTO USER_LOCATION (location_id, location_name) VALUES (1, 'Virginia')
  INTO USER_LOCATION (location_id, location_name) VALUES (2, 'New York')
  INTO USER_LOCATION (location_id, location_name) VALUES (3, 'Florida')
  --insert roles  
  INTO USER_ROLE (role_id, role_name) VALUES (1, 'manager')
  INTO USER_ROLE (role_id, role_name) VALUES (2, 'associate')
  INTO USER_ROLE (role_id, role_name) VALUES (3, 'admin')
  --insert status
  INTO USER_STATUS (status_id, status_name) VALUES (1, 'staging')
  INTO USER_STATUS (status_id, status_name) VALUES (2, 'bench')
  INTO USER_STATUS (status_id, status_name) VALUES (3, 'project')
SELECT * FROM dual;

INSERT INTO MESSAGE_ROOM (room_id, room_name)
  VALUES (SEQ_MSGROOM.NEXTVAL, 'public');
  
INSERT INTO MESSAGE_TABLE (message_id, room_id, username,
    message_text, message_time)
  VALUES(SEQ_MESSAGE.NEXTVAL, msg_pkg.get_public_roomid(),
    'Revature', 'Welcome to the Public Staging Board!', SYSTIMESTAMP);

INSERT INTO USER_TABLE (user_id, username, passwd, firstname, lastname,
    email, location_id, status_id, role_id)
  VALUES (SEQ_USER.NEXTVAL, 'revadmin', 'revadmin', 'Felice', 'Sumargo',
    'palmer.calogrias@revature.com', 1, 1, 3);
    
INSERT INTO USER_TABLE (user_id, username, passwd, firstname, lastname,
    email, location_id, status_id, role_id)
  VALUES (SEQ_USER.NEXTVAL, 'stanleym', 'stanleym', 'Stanley', 'Medikonda',
    'stanleym@revature.com', 1, 1, 1);

/*******************************************************************************
   Commit Changes and Exit
*******************************************************************************/
COMMIT;
EXIT;
