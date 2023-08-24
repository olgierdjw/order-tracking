### Create MySQL database
```bash
#!/bin/bash


sudo apt update
sudo apt install mysql-server

sudo mysql_secure_installation

sudo systemctl start mysql_secure_installation # (not enabling on boot)
echo "MySQL started."

echo "Creating MySQL user and database..."
sudo mysql -e "CREATE DATABASE db;"
sudo mysql -e "CREATE USER 'db_user'@'localhost' IDENTIFIED BY '1234';"
sudo mysql -e "GRANT ALL PRIVILEGES ON db.* TO 'db_user'@'localhost';"
sudo mysql -e "FLUSH PRIVILEGES"

echo "MySQL setup complete."
```

### Backend start
```bash
./gradlew bootRun
```

### Frontend start
_in progress_