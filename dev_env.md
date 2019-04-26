### 一、开发环境   

分为 Linux ， Windows 两个环境 

#### windows 

+ 安装目录   

  根目录  d:\git_dev 

  应用目录(未解压)  d:\package    

+ 环境变量   

### 二、开发工具 

#### JDK 1.8  

+ 安装目录   

  d:\git_dev\jdk18_031 

+ 配置修改   

  无  

#### maven 3.6  

- 安装目录   

  d:\git_dev\maven36 

- 配置修改   

  修改  settings.xml

  本地仓库   

    <localRepository>d:/dev/maven36/repository</localRepository> 

  镜像库 

  	<mirrors>
  		<mirror>
  	      <id>alimaven</id>
  	      <name>aliyun maven</name>
  	      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
  	      <mirrorOf>central</mirrorOf>        
  	    </mirror> 
  	</mirrors>



#### zookerper 3.5.4

- 安装目录   

  d:\git_dev\zookeeper35 

- 配置修改   

  修改  conf\zoo.cfg ，创建目录 

  ```dataDir=d:\\log\\zk35 ```    

#### nacos 0.8

- 安装目录   

  d:\git_dev\nacos08  

- 配置修改   



