[ ! -z "$PG_HOST_FILE" ] && { PG_HOST=`cat $PG_HOST_FILE`; export PG_HOST; }
[ ! -z "$PG_PORT_FILE" ] && { PG_PORT=`cat $PG_PORT_FILE`; export PG_PORT; }
[ ! -z "$PG_USERNAME_FILE" ] && { PG_USERNAME=`cat $PG_USERNAME_FILE`; export PG_USERNAME; }
[ ! -z "$PG_PASSWORD_FILE" ] && { PG_PASSWORD=`cat $PG_PASSWORD_FILE`; export PG_PASSWORD; }
[ ! -z "$RSA_PUBLIC_KEY_FILE" ] && { RSA_PUBLIC_KEY=`cat $RSA_PUBLIC_KEY_FILE`; export RSA_PUBLIC_KEY; }
[ ! -z "$RSA_PRIVATE_KEY_FILE" ] && { RSA_PRIVATE_KEY=`cat $RSA_PRIVATE_KEY_FILE`; export RSA_PRIVATE_KEY; }

java -Djava.security.egd=file:/dev/./urandom -jar crud-latest.jar