@echo off
echo Stopping Tomcat if running...
taskkill /F /IM java.exe 2>nul

echo Waiting for Tomcat to stop...
timeout /t 3 /nobreak >nul

echo Removing old ROOT folder...
rmdir /s /q "C:\apache-tomcat-11.0.13\webapps\ROOT" 2>nul

echo Copying new WAR file...
copy /Y "C:\Users\DELL\codecuaquan\lap trinh web\SQLGatewayApp\target\ROOT.war" "C:\apache-tomcat-11.0.13\webapps\"

echo Done! Now start Tomcat in NetBeans
echo Then access: http://localhost:8080/
pause
