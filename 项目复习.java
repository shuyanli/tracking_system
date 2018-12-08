第一节基础课:

基础概念:

==========
1. jar 是什么:
==========
jar包就是一堆打包好的, 别人写的class, 跨平台, 可以执行.
jar包是封装好的javaclass类
可以在自己项目里引入别人写好的jar, 从而调用里面的东西
当我们开发了一个程序以后，程序中有很多的类，如果需要提供给别人使用,发给对方一堆的源文件是非常不好的，
通常需要把这些类打包成一个 JAR 包,把这个 JAR 包提供给别人使用,只需要别人在CLASSPATH 环境变量中添加这个 JAR 包,
则 Java 虚拟机就可以在内存中解析这个 JAR 包了，这个 JAR 包就是一个路径,就像我们电脑访问普通文件一样, Java 虚拟机会根据路径查找相应的文件
了解:
如何创建jar: 创建manifest(清单)文件, 在里面写好头文件(main-class), 然后jar cmf ...打包
如何执行jar: java -jar xxx

==========
2. lib vs jar:
==========
lib是一堆jar的集合

==========
3. Maven:
==========
一个对Maven比较正式的定义是这么说的：
Maven是一个项目管理工具，它包含了一个项目对象模型 (POM：Project Object Model)，一组标准集合，
一个项目生命周期(Project Lifecycle)，一个依赖管理系统(Dependency Management System)，
和用来运行定义在生命周期阶段(phase)中插件(plugin)目标(goal)的逻辑。

Maven到底是什么，能做什么，可以用更通俗的方式来说明。我们知道，项目开发不仅仅是写写代码而已，期间会伴随着各种必不可少的事情要做，下面列举几个感受一下：
1、我们需要引用各种jar包，尤其是比较大的工程，引用的jar包往往有几十个乃至上百个， 每用到一种jar包，都需要手动引入工程目录，而且经常遇到各种让人抓狂的jar包冲突，版本冲突
2、我们辛辛苦苦写好了Java文件，可是只懂0和1的白痴电脑却完全读不懂，需要将它编译成二进制字节码。好歹现在这项工作可以由各种集成开发工具帮我们完成，
Eclipse、IDEA等都可以将代码即时编译。当然，如果你嫌生命漫长，何不铺张，也可以用记事本来敲代码，然后用javac命令一个个地去编译，逗电脑玩。
3、世界上没有不存在bug的代码，正如世界上没有不喜欢美女的男人一样。写完了代码，我们还要写一些单元测试，然后一个个的运行来检验代码质量。
4、再优雅的代码也是要出来卖的。我们后面还需要把代码与各种配置文件、资源整合到一起，定型打包，如果是web项目，还需要将之发布到服务器，供人蹂躏。

试想，如果现在有一种工具，可以把你从上面的繁琐工作中解放出来，能帮你构建工程，管理jar包，编译代码，
还能帮你自动运行单元测试，打包，生成报表，甚至能帮你部署项目，生成Web站点，你会心动吗？傻子才不会。

负责任的告诉你，以上的一切Maven都可以办到。概括地说，Maven可以简化和标准化项目建设过程。处理编译，分配，文档，团队协作和其他任务的无缝连接。 
最简单的例子，你要开发S2SH项目。你是不是要去找struts2 hibernate spring的jar包 去官网下一堆 然后百度搜索哪些是必须的，在放到你的项目里；麻烦不？
用了maven 在pom里把依赖一配置，说你要用struts2的xxx版本，xxx家出的，ok 你说一嘴就行，maven插件会从中央仓库拿的；至于struts2依赖了什么？你不用操心……

====================
4. Spring:
====================
Spring is a lightweight framework. It can be thought of as a framework of frameworks because it provides support to various frameworks 
such as Struts, Hibernate, Tapestry, EJB, JSF etc. The framework, in broader sense, can be defined as a structure 
where we find solution of the various technical problems.

The Spring framework comprises several modules such as IOC, AOP, DAO, Context, ORM, WEB MVC etc.
主要组成:
a. Test
一般是Junit作为test

b. Spring Core Container
The Spring Core container contains core, beans, context and expression language (EL) modules.
	Core and Beans
	These modules provide IOC and Dependency Injection features.

c. Data Access / Integration
This group comprises of JDBC, ORM, OXM, JMS and Transaction modules. 
These modules basically provide support to interact with the database.

Web
This group comprises of Web, Web-Servlet, Web-Struts and Web-Portlet. 
These modules provide support to create web application.
==================================================
5. IOC(inverse of control), Dependency Injection
==================================================
IOC是一种design pattern, DI是实现它的一种方式
主要作用: loosely couple
In Spring framework, IOC container is responsible to inject the dependency.
We provide metadata to the IOC container either by XML file or annotation


================================
Java Persistence API(JPA), ORM
================================
JPA: JAVA与数据库交互的工具
java: object model, 数据库: relational object, tabular model
JPA解决了这两种model转换的问题

ORM:
Object Relational Mapping is a programming ability to covert data from object type to relational type and vice versa.
The main feature of ORM is mapping or binding an object to its data in the database. 
While mapping we have to consider the data, type of data and its relations with its self-entity or entity in any other table.

Hibernate是什么？
it is a JPA provider:

它是连接java应用程序与关系数据库的中间件；
它对JDBC API进行了封装，负责Java对象的持久化, 简化了原始JDBC的通信麻烦
在分层软件框架中它位于持久化层，封装了所有数据访问细节，使业务逻辑层可以专注实现业务逻辑；
它是一种ORM映射工具，能够建立面向对象模型与关系数据模型的映射。

object relational mapping(ORM):
分三步:
object data phase -> mapping phase -> relational data phase
特别注意:
only when the business component commit the data, it is stored into the database physically. 
Until then the modified data is stored in a #cache memory# as a grid format. Same is the process for obtaining data 

通过定义 mapping.xml文件, 规定entity

================================
Java Data JPA
================================
Spring way of doing JPA
通过repository object里面自己定义的interface/funciton, 来实现数据库交互
interface/funciton 名字的定义是遵循一定命名规则的
比如:
1.  简单的query:
List<Person> findPeopleDistinctByLastnameOrFirstname(String lastname, String firstname){
	spring data 会把这个方程的名字翻译成query语句
	然后将数据库返回的value转化会java 的object, 作为return value
}

2. 复杂的query:
query annotation @Query

如: 
@Query("select u from User as u where u.firstname like %?1")
List<Person> findByFirstnameEndWith(String firstname){ //方程名字就可以随意来了

}


============================================================
Java Bean| Spring bean container | Bean facory
============================================================
约定俗成的许多java object明明习惯
比如getXXX(), setXXX(), isXXX()
在许多frameword都会默认大家都是用这种方法获得/修改field的

spring通过bean container的方式管理java bean
管理所有bean的生命周期, 从创建到回收 

一般使用@Autowire或者@inject的方法, 把object(bean)注入容器
一般会使用object的构造函数来@Autowire, 方便未来做单元测试


Bean facory:
interface, 用来启动bean container
然后取找用annotation标记的bean对象
同时管理bean的生命周期

使得配置bean变得很容易
===============
TomCat
===============
Java web framework
相当于一个容器, 一个server
旧的java需要把代码打包成war放进tomcat来运行

===============
Spring MVC
===============
新版的springmvc省掉了许多繁琐的步骤
通过annotation可以标记一个bean为controller, 以及它的path, 内部函数的path

==============================
Spring vs Spring boot
==============================
Spring Boot: 基于spring的微服务框架, 比spring更加完善
spring boot = spring framework + spring web mvc + auto configuration + many ready feature

比spring更加完善, 更加简单
优点:
1. 几乎不用手动configuration
2. fast development and deployment
3. POM file使得进入资源变得容易
4. built-in application metrix, health check...
5. no requirement for XML configuration 


===============
FAT JAR
===============
原来的java项目,如果要部署, 要自己打包成WAR file, 然后放进tomcat, 然后配置tomcat, 然后运行,很麻烦还很容易出错
现在spring boot会自动把项目打包成一个fat jar(tomcat + jar)
然后直接 java -jar就能运行

==============================
POM| Starter POM
==============================
每个项目基本都要导入的东西:
1.spring-boot-starter-web
这个东西会把tomcat, restful的相关, web mvc都加载到项目中
2.mvn plugin
导入maven需要的dependency


==============================
Spring 项目结构
==============================

com
	+-example(groupID)
		+-myProject(artifect ID)
			+-Application.java(自己创建的项目入口函数)
			|
			+-domain (所有的object, data object都放在这里)
			| +-Customer.java. (定义customer object的一下特征, 模板等)
			| +-CustomerRepository.java(加上repository一般就是这个object和数据库通信的class)
			|
			+-service (所有的业务逻辑都放在这里)
			|
			| +-CustomerService.java
			|
			|
			+-web (controller放在这里 || 与api交互相关的放在这)
			| +-CustomerController.java




























