#!/bin/bash

# 编译 me.fangfeng.client 包下的类
javac com/gg/instrument/client/Main.java com/gg/instrument/client/Rand.java
jar cvfm app.jar MANIFEST-app.MF com/gg/instrument/client/Main.java com/gg/instrument/client/Rand.java

# 编译 me.fangfeng.javaagent 包下的类
javac -cp F:\git_code\19082062\midway\springboot\instrument\lib\asm-7.1.jar -cp app.jar com/gg/javaagent/Agent.java com/gg/javaagent/ClassTimer.java com/gg/javaagent/MyClassWriter.java com/gg/javaagent/MyMethodWriter.java com/gg/javaagent/StaticTimer.java

# 打包 me.fangfeng.javaagent 的 .class -> agent.jar
jar cvfm agent.jar MANIFEST-agent.MF com/gg/javaagent/Agent.class com/gg/javaagent/ClassTimer.class com/gg/javaagent/MyClassWriter.class com/gg/javaagent/MyMethodWriter.class com/gg/javaagent/StaticTimer.class 


# 以 me.fangfeng.client.Main 作为主类启动
java -javaagent:agent.jar com.gg.proxy.instrument.client.Main

# jar cvfm agent.jar META-INF/MANIFEST.MF me/fangfeng/javaagent/Agent.class me/fangfeng/javaagent/ClassLogger.class me/fangfeng/javaagent/MyClassWriter.class me/fangfeng/javaagent/MyMethodWriter.class

# jar cvfm app.jar manifast4app me/fangfeng/client/Code.class me/fangfeng/client/Example.class me/fangfeng/client/StaticTimer.class

#java -javaagent:agent.jar -jar app.jar
# java -javaagent:agent.jar me/fangfeng/client/Example
