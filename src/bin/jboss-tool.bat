@echo off

set CLASS_PATH=%~dp0..\lib\*
set MAIN_CLASS=org.jboss.tool.Main

java -cp %CLASS_PATH% %MAIN_CLASS%