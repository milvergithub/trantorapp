/* **********************************************
 * SEQUENCES                                    *
 ************************************************/
CREATE SEQUENCE user_seq                START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE message_seq             START WITH 1 INCREMENT BY 1;

/*==============================================================*/
/* Table: users                                                 */
/*                                                              */
/*==============================================================*/
CREATE TABLE users
(
    usr_uid                 BIGINT          NOT NULL,
    usr_username            VARCHAR(50)     NOT NULL,
    usr_password            VARCHAR(100)    NOT NULL,
    usr_enabled             BOOLEAN         NOT NULL DEFAULT TRUE,
    usr_first_name          VARCHAR(70)     NULL,
    usr_last_name           VARCHAR(70)     NULL,
    usr_phone_number        VARCHAR(20)     NULL,
    usr_address             VARCHAR(250)    NULL,
    usr_account_expired     BOOLEAN         NOT NULL DEFAULT FALSE,
    usr_account_locked      BOOLEAN         NOT NULL DEFAULT FALSE,
    usr_credentials_expired BOOLEAN         NOT NULL DEFAULT FALSE,
    usr_created_date        TIMESTAMP       NOT NULL,
    usr_updated_date        TIMESTAMP       NOT NULL
);

ALTER TABLE users
    ALTER COLUMN usr_uid SET DEFAULT nextval('user_seq') ,
    ALTER COLUMN usr_created_date      	SET DEFAULT NOW(),
    ALTER COLUMN usr_updated_date 		SET DEFAULT NOW(),
    ADD CONSTRAINT pk_uid               PRIMARY KEY (usr_uid);

/*==============================================================*/
/* Table: oauth_client_details                                  */
/*                                                              */
/*==============================================================*/
CREATE TABLE oauth_client_details
(
    client_id               VARCHAR(255) PRIMARY KEY,
    resource_ids            VARCHAR(255),
    client_secret           VARCHAR(255),
    scope                   VARCHAR(255),
    authorized_grant_types  VARCHAR(255),
    web_server_redirect_uri VARCHAR(255),
    authorities             VARCHAR(255),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4096),
    autoapprove             VARCHAR(255)
);

/*==============================================================*/
/* Table: oauth_access_token                                    */
/*                                                              */
/*==============================================================*/
CREATE TABLE oauth_access_token
(
    token_id          VARCHAR(255),
    token             BYTEA,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name         VARCHAR(255),
    client_id         VARCHAR(255),
    authentication    BYTEA,
    refresh_token     VARCHAR(255)
);

/*==============================================================*/
/* Table: oauth_refresh_token                                   */
/*                                                              */
/*==============================================================*/
CREATE TABLE oauth_refresh_token
(
    token_id       VARCHAR(255),
    token          BYTEA,
    authentication BYTEA
);

/*==============================================================*/
/* Table: messages                                              */
/*                                                              */
/*==============================================================*/
CREATE TABLE messages
(
    msg_uid          BIGINT         NOT NULL,
    msg_usr_uid_from BIGINT         NOT NULL,
    msg_usr_uid_to   BIGINT         NOT NULL,
    msg_read         BOOLEAN        NOT NULL,
    msg_body         VARCHAR(1000)  NOT NULL,
    msg_created_date TIMESTAMP      NOT NULL,
    msg_updated_date TIMESTAMP      NOT NULL
);

ALTER TABLE messages
    ALTER COLUMN msg_uid SET DEFAULT nextval('message_seq') ,
    ALTER COLUMN msg_read 		        SET DEFAULT FALSE,
    ALTER COLUMN msg_created_date      	SET DEFAULT NOW(),
    ALTER COLUMN msg_updated_date 		SET DEFAULT NOW(),
    ADD CONSTRAINT pk_messages_uid      PRIMARY KEY (msg_uid),
    ADD CONSTRAINT fk_msg_usr_uid_from  FOREIGN KEY (msg_usr_uid_from)
        REFERENCES users(usr_uid) ON UPDATE CASCADE ON DELETE CASCADE,
    ADD CONSTRAINT fk_msg_usr_uid_to FOREIGN KEY (msg_usr_uid_to)
        REFERENCES users(usr_uid) ON UPDATE CASCADE ON DELETE CASCADE;