:start
@echo off
copy "C:\Users\Zak\IdeaProjects\Stupid-Prison\out\artifacts\stupidprison_jar\stupidprison.jar" "./plugins"
java -Xms1G -Xmx4G -XX:+UseG1GC -jar spigot.jar nogui
GOTO start