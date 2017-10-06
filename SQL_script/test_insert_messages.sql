/*******************************************************************************
  Test Script: Insert messages
  Project: Staging Messaging Portal (SMP)
  Description: Tests inserting messages into MESSAGE_TABLE
  DB Server: staging-smp.cvoui7q38caj.us-east-2.rds.amazonaws.com:1521:ORCL
  Author: Richard C. Smith
  Company: Revature LLC
  Date of creation: 2017/09/15
  Version: 2017.09.19
  License: Copyright 2017 Revature
*******************************************************************************/
SET serveroutput ON;
/*******************************************************************************
   Create 'tester' in USER_TABLE if one does not exist
*******************************************************************************/
select systimestamp from dual;
/*******************************************************************************
   Insert single message records into MESSAGE_TABLE
*******************************************************************************/
INSERT INTO suppliers (supplier_id, supplier_name)
VALUES (5000, 'Apple');

INSERT INTO USER_TABLE (user_id, username, passwd, firstname, lastname,
    email, location_id, status_id, role_id)
  VALUES (SEQ_USER.NEXTVAL, 'pickleric', 'test', 'pickle', 'ric',
    'pickle.ric@toomanypickles.com', 1, 1, 2);

UPDATE USER_TABLE SET active = 'y' WHERE username = 'pickleric';
UPDATE USER_TABLE SET active = 'n' WHERE username = 'pickleric';

INSERT INTO MESSAGE_TABLE (message_id, room_id, username,
    message_text, message_time)
  VALUES(SEQ_MESSAGE.NEXTVAL, 16,
    'Revature', 
    'Welcome to your private channel with the staging manager.', 
    SYSTIMESTAMP);
    
    
INSERT INTO USER_TABLE (user_id, username, passwd, firstname, lastname,
    email, location_id, status_id, role_id)
  VALUES (SEQ_USER.NEXTVAL, 'bobdoyle', 'bobdoyle', 'bob', 'doyle',
    'bob@doyle.org', 1, 1, 2);

UPDATE USER_TABLE SET active = 'y' WHERE username = 'bobdoyle';
/*******************************************************************************
   Verify message records exist in MESSAGE_TABLE
*******************************************************************************/

SET serveroutput OFF;