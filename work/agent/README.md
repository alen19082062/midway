##  ** 功能描述  **

【agent01】 模块，使用 idea build project 打包
javaagent , 打印函数运行时间    
运行指令  
java -jar -javaagent:agent01.jar hello_jar.jar 
参考文章 
https://blog.csdn.net/ljz2016/article/details/84137908 

\META-INF\MAINIFEST.MF 存放在根目录下，系统生成后，再修改

内容 
```
Manifest-Version: 1.0
Premain-Class: com.gg.agent.Agent
Agent-Class: com.gg.agent.Agent
Can-Redefine-Classes: true
Can-Retransform-Classes: true
```

【agent02】 使用 maven 打包 
分为jar 包含依赖包 或者不包含， jar 包含依赖包方便一些 
java -javaagent:agent02-1.0.jar -jar hello_jar.jar
java -cp lib\asm-7.1.jar;lib\asm-commons-7.1.jar;lib\asm-analysis-7.1.jar;lib\asm-tree-7.1.jar -javaagent:agent02-1.0.jar -jar hello_jar.jar



