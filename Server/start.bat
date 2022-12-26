:start
@echo off
copy "F:\Prison Plugin\out\artifacts\Prison_Plugin_jar\Prison Plugin.jar" "./plugins"
java -Xms6G -Xmx6G -XX:+UseG1GC -jar spigot.jar nogui
GOTO start