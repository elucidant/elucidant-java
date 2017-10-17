-- Generated by Oracle SQL Developer Data Modeler 17.2.0.188.1104
--   at:        2017-10-17 14:56:24 PDT
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g
/
CREATE TABLE address_type (
    id              NUMBER NOT NULL,
    type            VARCHAR2(10) NOT NULL,
    date_created    DATE,
    date_modified   DATE,
    created_by      VARCHAR2(50),
    modified_by     VARCHAR2(50)
);
/
ALTER TABLE address_type ADD CONSTRAINT address_type_pk PRIMARY KEY ( id );
/
ALTER TABLE address_type ADD CONSTRAINT address_type__un UNIQUE ( type );
/
CREATE TABLE ip_address (
    id                   NUMBER NOT NULL,
    ip_address           VARCHAR2(50) NOT NULL,
    ip_address_type_id   NUMBER NOT NULL,
    date_created         DATE,
    date_modified        DATE,
    created_by           VARCHAR2(50),
    modified_by          VARCHAR2(50)
);
/
ALTER TABLE ip_address ADD CONSTRAINT ip_address_pk PRIMARY KEY ( id );
/
ALTER TABLE ip_address ADD CONSTRAINT ip_address__un UNIQUE ( ip_address );
/
CREATE TABLE ip_address_to_system (
    id              NUMBER NOT NULL,
    ip_address_id   NUMBER NOT NULL,
    system_id       NUMBER NOT NULL,
    date_created    DATE,
    date_modified   DATE,
    created_by      VARCHAR2(50),
    modified_by     VARCHAR2(50)
);
/
ALTER TABLE ip_address_to_system ADD CONSTRAINT ip_address_to_system_pk PRIMARY KEY ( id );
/
ALTER TABLE ip_address_to_system ADD CONSTRAINT ip_address_to_system__un UNIQUE ( ip_address_id,system_id );
/
CREATE TABLE nmap_host (
    id                 NUMBER NOT NULL,
    nmapscan_id        NUMBER NOT NULL,
    system_status_id   NUMBER NOT NULL,
    scan_start_time    DATE,
    scan_end_time      DATE,
    date_created       DATE,
    date_modified      DATE,
    created_by         VARCHAR2(50),
    modified_by        VARCHAR2(50)
);
/
ALTER TABLE nmap_host ADD CONSTRAINT nmap_host_pk PRIMARY KEY ( id );
/
CREATE TABLE nmap_host_script (
    id               NUMBER NOT NULL,
    nmap_host_id     NUMBER NOT NULL,
    nmap_script_id   NUMBER NOT NULL,
    output           VARCHAR2(4000),
    date_created     DATE,
    date_modified    DATE,
    created_by       VARCHAR2(50),
    modified_by      VARCHAR2(50)
);
/
ALTER TABLE nmap_host_script ADD CONSTRAINT host_script_pk PRIMARY KEY ( id );
/
CREATE TABLE nmap_port (
    id              NUMBER NOT NULL,
    nmapscan_id     NUMBER NOT NULL,
    nmap_host_id    NUMBER NOT NULL,
    port_id         NUMBER NOT NULL,
    state           VARCHAR2(10),
    service         VARCHAR2(20),
    reason          VARCHAR2(20),
    date_created    DATE,
    date_modified   DATE,
    created_by      VARCHAR2(50),
    modified_by     VARCHAR2(50)
);
/
ALTER TABLE nmap_port ADD CONSTRAINT nmap_port_pk PRIMARY KEY ( id );
/
CREATE TABLE nmap_port_script (
    id               NUMBER NOT NULL,
    nmap_port_id     NUMBER NOT NULL,
    nmap_script_id   NUMBER NOT NULL,
    output           VARCHAR2(4000),
    date_created     DATE,
    date_modified    DATE,
    created_by       VARCHAR2(50),
    modified_by      VARCHAR2(50)
);
/
ALTER TABLE nmap_port_script ADD CONSTRAINT nmap_port_script_pk PRIMARY KEY ( id );
/
CREATE TABLE nmap_post_script (
    id               NUMBER NOT NULL,
    nmapscan_id      NUMBER NOT NULL,
    nmap_script_id   NUMBER NOT NULL,
    output           VARCHAR2(4000),
    date_created     DATE,
    date_modified    DATE,
    created_by       VARCHAR2(50),
    modified_by      VARCHAR2(50)
);
/
ALTER TABLE nmap_post_script ADD CONSTRAINT post_script_pk PRIMARY KEY ( id );
/
CREATE TABLE nmap_pre_script (
    id               NUMBER NOT NULL,
    nmapscan_id      NUMBER NOT NULL,
    output           VARCHAR2(4000),
    date_created     DATE,
    date_modified    DATE,
    created_by       VARCHAR2(50),
    modified_by      VARCHAR2(50),
    nmap_script_id   NUMBER NOT NULL
);
/
ALTER TABLE nmap_pre_script ADD CONSTRAINT pre_script_pk PRIMARY KEY ( id );
/
CREATE TABLE nmap_script (
    id              NUMBER NOT NULL,
    name            VARCHAR2(50),
    date_created    DATE,
    date_modified   DATE,
    created_by      VARCHAR2(50),
    modified_by     VARCHAR2(50)
);
/
ALTER TABLE nmap_script ADD CONSTRAINT nmap_script_pk PRIMARY KEY ( id );
/
CREATE TABLE nmap_ssl_cipher (
    id                         NUMBER NOT NULL,
    name                       VARCHAR2(50),
    nmap_ssl_tls_protocol_id   NUMBER NOT NULL,
    ssl_tls_cipher_id          NUMBER NOT NULL,
    date_created               DATE,
    date_modified              DATE,
    created_by                 VARCHAR2(50),
    modified_by                VARCHAR2(50)
);
/
ALTER TABLE nmap_ssl_cipher ADD CONSTRAINT nmap_ssl_cipher_pk PRIMARY KEY ( id );
/
CREATE TABLE nmap_ssl_enum_ciphers (
    id               NUMBER NOT NULL,
    nmap_port_id     NUMBER NOT NULL,
    nmap_script_id   NUMBER NOT NULL,
    date_created     DATE,
    date_modified    DATE,
    created_by       VARCHAR2(50),
    modified_by      VARCHAR2(50)
);
/
ALTER TABLE nmap_ssl_enum_ciphers ADD CONSTRAINT nmap_ssl_enum_ciphers_pk PRIMARY KEY ( id );
/
CREATE TABLE nmap_ssl_tls_protocol (
    id                         NUMBER NOT NULL,
    nmap_ssl_enum_ciphers_id   NUMBER NOT NULL,
    ssl_tls_protocol_id        NUMBER NOT NULL,
    compressors                VARCHAR2(50),
    cipher_preference          VARCHAR2(50),
    warnings                   VARCHAR2(1000),
    date_created               DATE,
    date_modified              DATE,
    created_by                 VARCHAR2(50),
    modified_by                VARCHAR2(50)
);
/
ALTER TABLE nmap_ssl_tls_protocol ADD CONSTRAINT nmap_ssl_tls_protocol_pk PRIMARY KEY ( id );
/
CREATE TABLE nmapscan (
    id                NUMBER NOT NULL,
    scan_id           NUMBER NOT NULL,
    sequence          NUMBER NOT NULL,
    scan_start_time   DATE,
    scan_end_time     DATE,
    arguments         VARCHAR2(1000),
    target            VARCHAR2(100),
    summary           VARCHAR2(1000),
    hosts_up          NUMBER,
    hosts_down        NUMBER,
    scan_results      CLOB,
    date_created      DATE,
    date_modified     DATE,
    created_by        VARCHAR2(50),
    modified_by       VARCHAR2(50)
);
/
ALTER TABLE nmapscan ADD CONSTRAINT nmapscan_pk PRIMARY KEY ( id );
/
ALTER TABLE nmapscan ADD CONSTRAINT nmapscan__un UNIQUE ( id,sequence );
/
CREATE TABLE port (
    id              NUMBER NOT NULL,
    system_id       NUMBER NOT NULL,
    port            NUMBER NOT NULL,
    date_created    DATE,
    date_modified   DATE,
    created_by      VARCHAR2(50),
    modified_by     VARCHAR2(50)
);
/
ALTER TABLE port ADD CONSTRAINT port_pk PRIMARY KEY ( id );
/
ALTER TABLE port ADD CONSTRAINT port__un UNIQUE ( system_id,port );
/
CREATE TABLE scan (
    id               NUMBER NOT NULL,
    scan_type_id     NUMBER NOT NULL,
    "START"          DATE NOT NULL,
    sequence_count   NUMBER,
    date_created     DATE,
    date_modified    DATE,
    created_by       VARCHAR2(50),
    modified_by      VARCHAR2(50)
);
/
ALTER TABLE scan ADD CONSTRAINT nmapscan_summary_pk PRIMARY KEY ( id );
/
CREATE TABLE scan_type (
    id              NUMBER NOT NULL,
    type            VARCHAR2(10) NOT NULL,
    date_created    DATE,
    date_modified   DATE,
    created_by      VARCHAR2(50),
    modified_by     VARCHAR2(50)
);
/
ALTER TABLE scan_type ADD CONSTRAINT scan_type_pk PRIMARY KEY ( id );
/
CREATE TABLE ssl_tls_cipher (
    id              NUMBER NOT NULL,
    strength        CHAR(1),
    key_info        VARCHAR2(20),
    date_created    DATE,
    date_modified   DATE,
    created_by      VARCHAR2(50),
    modified_by     VARCHAR2(50)
);
/
ALTER TABLE ssl_tls_cipher ADD CONSTRAINT ssl_tls_cipher_pk PRIMARY KEY ( id );
/
CREATE TABLE ssl_tls_protocol (
    id              NUMBER NOT NULL,
    protocol        VARCHAR2(20),
    date_created    DATE,
    date_modified   DATE,
    created_by      VARCHAR2(50),
    modified_by     VARCHAR2(50)
);
/
ALTER TABLE ssl_tls_protocol ADD CONSTRAINT ssl_tls_protocol_pk PRIMARY KEY ( id );
/
CREATE TABLE system (
    id              NUMBER NOT NULL,
    mac_address     VARCHAR2(16),
    name            VARCHAR2(100) NOT NULL,
    date_created    DATE,
    date_modified   DATE,
    created_by      VARCHAR2(50),
    modified_by     VARCHAR2(50)
);
/
ALTER TABLE system ADD CONSTRAINT system_pk PRIMARY KEY ( id );
/
ALTER TABLE system ADD CONSTRAINT system__un UNIQUE ( name,mac_address );
/
CREATE TABLE system_status (
    id                        NUMBER NOT NULL,
    ip_address_to_system_id   NUMBER NOT NULL,
    system_uptime             VARCHAR2(100),
    state                     VARCHAR2(10),
    reason                    VARCHAR2(20),
    lastboot                  DATE,
    date_created              DATE,
    date_modified             DATE,
    created_by                VARCHAR2(50),
    modified_by               VARCHAR2(50)
);
/
ALTER TABLE system_status ADD CONSTRAINT system_status_pk PRIMARY KEY ( id );
/
ALTER TABLE ip_address
    ADD CONSTRAINT address_type_fk FOREIGN KEY ( ip_address_type_id )
        REFERENCES address_type ( id );
/
ALTER TABLE ip_address_to_system
    ADD CONSTRAINT ip_address_fk FOREIGN KEY ( ip_address_id )
        REFERENCES ip_address ( id );
/
ALTER TABLE nmap_port
    ADD CONSTRAINT nmap_host_fk FOREIGN KEY ( nmap_host_id )
        REFERENCES nmap_host ( id );
/
ALTER TABLE nmap_host
    ADD CONSTRAINT nmap_host_system_status_fk FOREIGN KEY ( system_status_id )
        REFERENCES system_status ( id );
/
ALTER TABLE nmap_host_script
    ADD CONSTRAINT nmap_hs_nmap_host_fk FOREIGN KEY ( nmap_host_id )
        REFERENCES nmap_host ( id );
/
ALTER TABLE nmap_host_script
    ADD CONSTRAINT nmap_hs_nmap_script_fk FOREIGN KEY ( nmap_script_id )
        REFERENCES nmap_script ( id );
/
ALTER TABLE nmap_port_script
    ADD CONSTRAINT nmap_port_fk FOREIGN KEY ( nmap_port_id )
        REFERENCES nmap_port ( id );
/
ALTER TABLE nmap_port
    ADD CONSTRAINT nmap_port_nmapscan_fk FOREIGN KEY ( nmapscan_id )
        REFERENCES nmapscan ( id );
/
ALTER TABLE nmap_post_script
    ADD CONSTRAINT nmap_posts_nmap_script_fk FOREIGN KEY ( nmap_script_id )
        REFERENCES nmap_script ( id );
/
ALTER TABLE nmap_post_script
    ADD CONSTRAINT nmap_posts_nmapscan_fk FOREIGN KEY ( nmapscan_id )
        REFERENCES nmapscan ( id );
/
ALTER TABLE nmap_pre_script
    ADD CONSTRAINT nmap_pres_nmap_script_fk FOREIGN KEY ( nmap_script_id )
        REFERENCES nmap_script ( id );
/
ALTER TABLE nmap_pre_script
    ADD CONSTRAINT nmap_pres_nmapscan_fk FOREIGN KEY ( nmapscan_id )
        REFERENCES nmapscan ( id );
/
ALTER TABLE nmap_port_script
    ADD CONSTRAINT nmap_script_fk FOREIGN KEY ( nmap_script_id )
        REFERENCES nmap_script ( id );
/
ALTER TABLE nmap_ssl_enum_ciphers
    ADD CONSTRAINT nmap_sec_nmap_port_fk FOREIGN KEY ( nmap_port_id )
        REFERENCES nmap_port ( id );
/
ALTER TABLE nmap_ssl_enum_ciphers
    ADD CONSTRAINT nmap_sec_nmap_script_fk FOREIGN KEY ( nmap_script_id )
        REFERENCES nmap_script ( id );
/
ALTER TABLE nmap_ssl_tls_protocol
    ADD CONSTRAINT nmap_ssl_enum_ciphers_fk FOREIGN KEY ( nmap_ssl_enum_ciphers_id )
        REFERENCES nmap_ssl_enum_ciphers ( id );
/
ALTER TABLE nmap_ssl_cipher
    ADD CONSTRAINT nmap_ssl_tls_protocol_fk FOREIGN KEY ( nmap_ssl_tls_protocol_id )
        REFERENCES nmap_ssl_tls_protocol ( id );
/
ALTER TABLE nmap_host
    ADD CONSTRAINT nmapscan_fk FOREIGN KEY ( nmapscan_id )
        REFERENCES nmapscan ( id );
/
ALTER TABLE nmap_port
    ADD CONSTRAINT port_fk FOREIGN KEY ( port_id )
        REFERENCES port ( id );
/
ALTER TABLE port
    ADD CONSTRAINT port_system_fk FOREIGN KEY ( system_id )
        REFERENCES system ( id );
/
ALTER TABLE nmapscan
    ADD CONSTRAINT scan_fk FOREIGN KEY ( scan_id )
        REFERENCES scan ( id );
/
ALTER TABLE scan
    ADD CONSTRAINT scan_scan_type_fk FOREIGN KEY ( scan_type_id )
        REFERENCES scan_type ( id );
/
ALTER TABLE system_status
    ADD CONSTRAINT ss_ip_address_to_system_fk FOREIGN KEY ( ip_address_to_system_id )
        REFERENCES ip_address_to_system ( id );
/
ALTER TABLE nmap_ssl_cipher
    ADD CONSTRAINT ssl_tls_cipher_fk FOREIGN KEY ( ssl_tls_cipher_id )
        REFERENCES ssl_tls_cipher ( id );
/
ALTER TABLE nmap_ssl_tls_protocol
    ADD CONSTRAINT ssl_tls_protocol_fk FOREIGN KEY ( ssl_tls_protocol_id )
        REFERENCES ssl_tls_protocol ( id );
/
ALTER TABLE ip_address_to_system
    ADD CONSTRAINT system_fk FOREIGN KEY ( system_id )
        REFERENCES system ( id );
/
CREATE SEQUENCE address_type_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE ip_address_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE ip_address_to_system_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE nmap_host_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE nmap_host_script_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE nmap_port_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE nmap_port_script_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE nmap_post_script_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE nmap_pre_script_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE nmap_script_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE nmap_ssl_cipher_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE nmap_ssl_enum_ciphers_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE nmap_ssl_tls_protocol_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE nmapscan_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE port_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE scan_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE scan_type_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE ssl_tls_cipher_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE ssl_tls_protocol_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE system_id_seq START WITH 1 NOCACHE ORDER;
/
CREATE SEQUENCE system_status_id_seq START WITH 1 NOCACHE ORDER;
/
-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                            20
-- CREATE INDEX                             0
-- ALTER TABLE                             51
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                         20
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
