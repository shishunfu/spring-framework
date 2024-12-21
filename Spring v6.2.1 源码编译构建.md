# Spring v6.2.1 源码编译构建

# 环境准备

```
>java -version

java version "17.0.11" 2024-04-16 LTS
Java(TM) SE Runtime Environment (build 17.0.11+7-LTS-207)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.11+7-LTS-207, mixed mode, sharing)

>gradle -v

------------------------------------------------------------
Gradle 8.10.2
------------------------------------------------------------

Build time:    2024-09-23 21:28:39 UTC
Revision:      415adb9e06a516c44b391edff552fd42139443f7

Kotlin:        1.9.24
Groovy:        3.0.22
Ant:           Apache Ant(TM) version 1.10.14 compiled on August 16 2023
Launcher JVM:  17.0.11 (Oracle Corporation 17.0.11+7-LTS-207)
Daemon JVM:    C:\MyDevTools\Java\jdk-17.0.11 (no JDK specified, using current Java home)
OS:            Windows 10 10.0 amd64
```

## Spring 源码下载

```bash
git clone https://github.com/spring-projects/spring-framework.git
```

## 修改配置

### build.gradle

修改 build.gradle 文件下的 repositories，加上我们国内的阿里云maven仓库

```
repositories {
	maven { url 'https://maven.aliyun.com/nexus/content/groups/public/' }
	maven { url 'https://maven.aliyun.com/nexus/content/repositories/jcenter'}
}
```

### gradle.properties

修改 gradle.properties 文件, 

```
version=6.2.2-SNAPSHOT

org.gradle.caching=true
org.gradle.jvmargs=-Xmx2048m
org.gradle.parallel=true

kotlinVersion=1.9.25

kotlin.jvm.target.validation.mode=ignore
kotlin.stdlib.default.dependency=false

## 启用新的孵化模式
org.gradle.configureondemand=true
## 开启守护进程 通过开启守护进程，下一次构建的时候，将会连接这个守护进程进行构建，而不是重新fork一个gradle构建进程
org.gradle.daemon=true
```

## 源码编译

根据 import-into-idea.md 中写到的要求，我们需要先进行两次预编译

进入到项目根目录

### 预编译`spring-oxm`

```
gradle :spring-oxm:compileTestJava
```

### 预编译`spring-core`

```
gradle :spring-core:compileTestJava
```

## 新建工程测试验证

### 新建 module

### 修改`build.gradle`文件

```
plugins {
    id 'java'
}

group = 'org.springframework'
version = '6.2.2-SNAPSHOT'

repositories {
    mavenLocal()
    maven { url 'https://maven.aliyun.com/nexus/content/groups/public/' }
    maven { url 'https://maven.aliyun.com/nexus/content/repositories/jcenter'}
    mavenCentral()
}

dependencies {
    implementation(project(":spring-context"))
    testImplementation platform('org.junit:junit-bom:5.10.0')
    testImplementation 'org.junit.jupiter:junit-jupiter'
}

test {
    useJUnitPlatform()
}
```

### 编写业务测试类

