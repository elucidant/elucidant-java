-- SCAN_TYPE Table rows
INSERT INTO SCAN_TYPE("ID", "TYPE", DATE_CREATED, DATE_MODIFIED, CREATED_BY, MODIFIED_BY)
VALUES (1, 'NMAP', SYSDATE, SYSDATE, sys_context('userenv', 'session_user'), sys_context('userenv', 'session_user'));

INSERT INTO SCAN_TYPE("ID", "TYPE", DATE_CREATED, DATE_MODIFIED, CREATED_BY, MODIFIED_BY)
VALUES (2, 'NESSUS', SYSDATE, SYSDATE, sys_context('userenv', 'session_user'), sys_context('userenv', 'session_user'));

-- ADDRESS_TYPE Table rows
INSERT INTO ADDRESS_TYPE("ID", "TYPE", DATE_CREATED, DATE_MODIFIED, CREATED_BY, MODIFIED_BY)
VALUES (1, 'IPV4', SYSDATE, SYSDATE, sys_context('userenv', 'session_user'), sys_context('userenv', 'session_user'));

INSERT INTO ADDRESS_TYPE("ID", "TYPE", DATE_CREATED, DATE_MODIFIED, CREATED_BY, MODIFIED_BY)
VALUES (2, 'IPV6', SYSDATE, SYSDATE, sys_context('userenv', 'session_user'), sys_context('userenv', 'session_user'));

INSERT INTO ADDRESS_TYPE("ID", "TYPE", DATE_CREATED, DATE_MODIFIED, CREATED_BY, MODIFIED_BY)
VALUES (3, 'MAC', SYSDATE, SYSDATE, sys_context('userenv', 'session_user'), sys_context('userenv', 'session_user'));
