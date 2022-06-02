--- INSERT INTO USERS
INSERT INTO users(usr_username, usr_password) VALUES('user1', /*user1*/'$2a$12$.i8sYL/6LiiKa7/fvYMix.2nCWCUso0wakGXpAnNV4og5CIxZV/Pq');
INSERT INTO users(usr_username, usr_password) VALUES('user2', /*user2*/'$2a$12$2zJRu1RCPfw5vd66BM.dyOngNiYcJ2FLZW0LtJ9OWnJAwZNCbfNNO');

--- INSET INTO OAUTH_CLIENTS_DETAILS
INSERT INTO oauth_client_details (client_id,resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove) VALUES
    ('users','api',/*users*/'$2a$12$Xufp.PuvyavZiKesUYrq2.eBICWcRM9pLligF1fvGn.yQm1qTtYAq','read,write','password,client_credentials,refresh_token,implicit',NULL,'USER',3600,3600,NULL,true);
INSERT INTO oauth_client_details (client_id,resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove) VALUES
    ('trantor','api',/*trantor*/'$2a$12$AlK.pqWJKCiY4W9gSnlrQO9D3MeTHfskiPVX.89NpYH8.Cje9udxW','read,write','password,client_credentials,refresh_token,implicit',NULL,'USER',-1,-1,NULL,true);