/*******************************************************************************
  Test Script: Insert messages
  Project: Staging Messaging Portal (SMP)
  Description: Tests inserting messages into MESSAGE_TABLE
  DB Server: staging-smp.cvoui7q38caj.us-east-2.rds.amazonaws.com:1521:ORCL
  Author: Richard C. Smith
  Company: Revature LLC
  Date of creation: 2017/09/15
  Version: 2017.09.15:01
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


/*******************************************************************************
   Verify message records exist in MESSAGE_TABLE
*******************************************************************************/

SET serveroutput OFF;