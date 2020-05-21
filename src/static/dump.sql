-- MySQL dump 10.13  Distrib 5.7.28, for macos10.14 (x86_64)
--
-- Host: 106.52.158.96    Database: actional_blog
-- ------------------------------------------------------
-- Server version	5.7.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `action_articles`
--

DROP TABLE IF EXISTS `action_articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action_articles` (
  `article_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章主键',
  `article_title` text NOT NULL COMMENT '文章标题',
  `article_flag` varchar(20) DEFAULT '原创' COMMENT '标记：转载，原创，翻译',
  `article_date` date NOT NULL COMMENT '发表时间',
  `recommend` tinyint(1) NOT NULL COMMENT '是否推荐',
  `del` tinyint(1) NOT NULL COMMENT '是否删除',
  `article_status` tinyint(1) NOT NULL COMMENT '0-草稿1-发布',
  `category_id` bigint(20) NOT NULL,
  PRIMARY KEY (`article_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `action_articles_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `action_categories` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action_articles`
--

LOCK TABLES `action_articles` WRITE;
/*!40000 ALTER TABLE `action_articles` DISABLE KEYS */;
INSERT INTO `action_articles` VALUES (10,'centos7安装mysql5.7','原创','2020-04-30',0,0,1,4),(11,'vue在iview组件中使用点击事件不起作用','原创','2019-04-30',0,0,1,3),(12,'vue钩子','转载','2020-03-30',0,0,1,3),(13,'java用@RequestParam接收vue的post请求参数','原创','2020-05-04',0,0,1,3),(14,'vue+element ui起步','转载','2020-05-04',0,0,1,3),(15,'vue插槽slot','原创','2020-05-04',0,0,1,3),(16,'mac安装 iTerm2   和  zsh','原创','2020-05-04',0,0,1,5),(17,'centos7安装mysql','原创','2020-05-04',0,1,1,4),(18,'centos7安装nginx','原创','2020-05-04',0,0,1,4),(19,'centos7安装redis','原创','2020-05-04',0,0,1,4),(20,'centos7联网','原创','2020-05-04',0,0,1,4),(21,'centos7 安装zsh','原创','2020-05-04',0,0,1,4),(22,'linux安装jdk8','原创','2020-05-04',0,0,1,4),(23,'git常用命令','原创','2020-05-04',0,0,1,4);
/*!40000 ALTER TABLE `action_articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `action_articles_labels`
--

DROP TABLE IF EXISTS `action_articles_labels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action_articles_labels` (
  `article_id` bigint(20) NOT NULL COMMENT '文章id',
  `label_id` bigint(20) NOT NULL COMMENT '标签id',
  UNIQUE KEY `article_id_2` (`article_id`,`label_id`),
  KEY `article_id` (`article_id`),
  KEY `label_id` (`label_id`),
  CONSTRAINT `action_articles_labels_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `action_articles` (`article_id`),
  CONSTRAINT `action_articles_labels_ibfk_2` FOREIGN KEY (`label_id`) REFERENCES `action_labels` (`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action_articles_labels`
--

LOCK TABLES `action_articles_labels` WRITE;
/*!40000 ALTER TABLE `action_articles_labels` DISABLE KEYS */;
INSERT INTO `action_articles_labels` VALUES (10,13),(11,7),(12,7),(13,1),(13,7),(14,7),(15,7),(16,15),(17,13),(17,16),(18,13),(18,16),(19,13),(19,16),(20,13),(20,16),(21,13),(21,16),(22,13),(22,16),(23,16);
/*!40000 ALTER TABLE `action_articles_labels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `action_articles_param`
--

DROP TABLE IF EXISTS `action_articles_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action_articles_param` (
  `article_id` bigint(20) NOT NULL COMMENT '文章主键',
  `article_comment_count` bigint(20) NOT NULL COMMENT '评论总数',
  `article_thumb_up` bigint(20) NOT NULL COMMENT '点赞数',
  `article_views` bigint(20) NOT NULL COMMENT '浏览量',
  `article_content` longtext NOT NULL COMMENT '文章内容',
  PRIMARY KEY (`article_id`),
  CONSTRAINT `action_articles_param_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `action_articles` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action_articles_param`
--

LOCK TABLES `action_articles_param` WRITE;
/*!40000 ALTER TABLE `action_articles_param` DISABLE KEYS */;
INSERT INTO `action_articles_param` VALUES (10,0,0,0,'# 一、下载mysql离线安装包tar文件\n\n使用华为镜像下载：[https://mirrors.huaweicloud.com/mysql/Downloads/MySQL-5.7/mysql-5.7.25-linux-glibc2.12-x86_64.tar.gz](https://mirrors.huaweicloud.com/mysql/Downloads/MySQL-5.7/mysql-5.7.25-linux-glibc2.12-x86_64.tar.gz)\n\n安装文件上传工具\n\n```shell\n[root@centos1--1- ~]# yum install lrzsz\n[root@centos1--1- ~]# cd /usr/local/\n[root@centos1--1- local]# rz			#上传文件到linux\nrz waiting to receive.\nStarting zmodem transfer.  Press Ctrl+C to cancel.\nTransferring mysql-5.7.25-linux-glibc2.12-x86_64.tar.gz...\n  100%  629748 KB    12348 KB/sec    00:00:51       0 Errors  \n[root@centos1--1- local]# tar -zxvf mysql-5.7.25-linux-glibc2.12-x86_64.tar.gz \n[root@centos1--1- local]# mv mysql-5.7.25-linux-glibc2.12-x86_64/ mysql\n```\n\n# 二、创建用户和组\n\n```shell\n#添加用户组mysql\n[root@centos1--1- local]# groupadd mysql\n#添加mysql用户，并归属于mysql组\n[root@centos1--1- local]# useradd -g mysql mysql\n#给mysql用户改密码\n[root@centos1--1- local]# passwd mysql\nChanging password for user mysql.\nNew password: \nBAD PASSWORD: The password is shorter than 8 characters\nRetype new password: \npasswd: all authentication tokens updated successfully.\n#给已有的用户增加工作组\n[root@centos1--1- local]# gpasswd -a mysql mysql\nAdding user mysql to group mysql\n```\n\n# 三、卸载centos7自带mariadb\n\n```shell\n#查看系统自带的mariadb\n[root@centos1--1- local]# rpm -qa|grep mariadb\nmariadb-libs-5.5.64-1.el7.x86_64\n#卸载mariadb\n[root@centos1--1- local]# rpm -e --nodeps mariadb-libs-5.5.64-1.el7.x86_64\n```\n\n# 四、检查mysql是否存在\n\n```shell\n#检查mysql是否存在,若存在	rpm -e --nodeps mysql\n[root@centos1--1- local]# rpm -qa|grep mysql\n[root@centos1--1- local]#\n```\n\n# 五、更改所属的组和用户\n\n```shell\n[root@centos1--1- local]# pwd		#查看当前位置\n/usr/local\n[root@centos1--1- local]# chown -R mysql mysql\n[root@centos1--1- local]# chgrp -R mysql mysql/\n[root@centos1--1- local]# cd mysql/\n[root@centos1--1- mysql]# mkdir data\n[root@centos1--1- mysql]# chown -R mysql:mysql data\n[root@centos1--1- local]# ll\ndrwxr-xr-x. 10 mysql mysql 141 Apr  3 00:11 mysql\n```\n\n# 六、创建my.cnf文件\n\n```shell\n[root@centos1--1- mysql]# pwd\n/usr/local/mysql\n[root@centos1--1- mysql]# vim my.cnf\n#复制以下代码\n\n[mysql]\nsocket=/var/lib/mysql/mysql.sock\n# set mysql client default chararter\ndefault-character-set=utf8\n\n[mysqld]\nsocket=/var/lib/mysql/mysql.sock\n# set mysql server port  \nport = 3306 \n# set mysql install base dir\nbasedir=/usr/local/mysql\n# set the data store dir\ndatadir=/usr/local/mysql/data\n# set the number of allow max connnection\nmax_connections=200\n# set server charactre default encoding\ncharacter-set-server=utf8\n# the storage engine\ndefault-storage-engine=INNODB\nlower_case_table_names=1\nmax_allowed_packet=16M\nexplicit_defaults_for_timestamp=true\n\n[mysql.server]\nuser=mysql\nbasedir=/usr/local/mysql\n```\n\n# 七、安装mysql\n\n```shell\n[root@centos1--1- mysql]# pwd\n/usr/local/mysql\n[root@centos1--1- mysql]# ./bin/mysqld --initialize --user=mysql --basedir=/usr/local/mysql/ --datadir=/usr/local/mysql/data/\n```\n\n\n\n```shell\n# 原因是缺少依赖 libnuma.so.1  通过yum安装\n[root@centos1--1- mysql]# yum -y install numactl.x86_64\nerror while loading shared libraries: libnuma.so.1: cannot open shared object file: No such file or directory\n```\n\n\n\n```shell\n##输出这样表示成功\n\n2020-04-02T16:19:01.160728Z 0 [Warning] TIMESTAMP with implicit DEFAULT value is deprecated. Please use --explicit_defaults_for_timestamp server option (see documentation for more details).\n2020-04-02T16:19:01.346712Z 0 [Warning] InnoDB: New log files created, LSN=45790\n2020-04-02T16:19:01.377032Z 0 [Warning] InnoDB: Creating foreign key constraint system tables.\n2020-04-02T16:19:01.433491Z 0 [Warning] No existing UUID has been found, so we assume that this is the first time that this server has been started. Generating a new UUID: a9e82d4e-74fd-11ea-b824-001c42010173.\n2020-04-02T16:19:01.434736Z 0 [Warning] Gtid table is not ready to be used. Table \'mysql.gtid_executed\' cannot be opened.\n2020-04-02T16:19:01.435331Z 1 [Note] A temporary password is generated for root@localhost: Ypx=pPA_G8Dh\n```\n\nYpx=pPA_G8Dh	系统随机生成的密码，用于第一次登陆\n\n设置文件及目录权限:\n\n```shell\n[root@centos1--1- mysql]# cp ./support-files/mysql.server /etc/init.d/mysqld\n[root@centos1--1- mysql]# chmod 777 my.cnf \n[root@centos1--1- mysql]# chmod +x /etc/init.d/mysqld\n```\n\n\n\n# 八、启动mysql\n\n```shell\n[root@centos1--1- mysql]# /etc/init.d/mysqld start\n#输出如下	\nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\n ERROR! MySQL server PID file could not be found!\nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\nStarting MySQL.my_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\nLogging to \'/usr/local/mysql/data/centos1--1-.shared.err\'.\n SUCCESS! \n```\n\n\n\n# 九、设置开机启动\n\n```shell\n[root@centos1--1- mysql]# chkconfig --level 35 mysqld on\n[root@centos1--1- mysql]# chkconfig --list mysqld\nNote: This output shows SysV services only and does not include native\n      systemd services. SysV configuration data might be overridden by native\n      systemd configuration.\n\n      If you want to list systemd services use \'systemctl list-unit-files\'.\n      To see services enabled on particular target use\n      \'systemctl list-dependencies [target]\'.\n\nmysqld          0:off   1:off   2:on    3:on    4:on    5:on    6:off\n[root@centos1--1- mysql]# chmod +x /etc/rc.d/init.d/mysqld\n[root@centos1--1- mysql]# chkconfig --add mysqld\n[root@centos1--1- mysql]# chkconfig --add mysqld\n[root@centos1--1- mysql]# service mysqld status\nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\n SUCCESS! MySQL running (10070)\n```\n\n\n\n# 十、环境变量\n\n```shell\n[root@centos1--1- mysql]# vim /etc/profile\n#在最后一行添加一下内容\n#mysql\nexport PATH=$PATH:/usr/local/mysql/bin\n#使配置生效\n[root@centos1--1- mysql]# source /etc/profile\n```\n\n\n\n\n\n# 十一、登陆mysql和重置密码\n\n```shell\n#-pYpx=pPA_G8Dh    -p 后面是安装mysql时生成的密码\n[root@centos1--1- mysql]# mysql -uroot -pYpx=pPA_G8Dh\nmysql: [Warning] Using a password on the command line interface can be insecure.\nWelcome to the MySQL monitor.  Commands end with ; or \\g.\nYour MySQL connection id is 2\nServer version: 5.7.25\n\nCopyright (c) 2000, 2019, Oracle and/or its affiliates. All rights reserved.\n\nOracle is a registered trademark of Oracle Corporation and/or its\naffiliates. Other names may be trademarks of their respective\nowners.\n\nType \'help;\' or \'\\h\' for help. Type \'\\c\' to clear the current input statement.\n\nmysql> set PASSWORD = PASSWORD(\'root\');				#\'root\'为设置的密码\nQuery OK, 0 rows affected, 1 warning (0.00 sec)\n\nmysql> flush privileges;\nQuery OK, 0 rows affected (0.00 sec)\n\nmysql> exit\n\n\n\n# 使用新密码登陆mysql\n[root@centos1--1- mysql]# mysql -uroot -proot\nmysql: [Warning] Using a password on the command line interface can be insecure.\nWelcome to the MySQL monitor.  Commands end with ; or \\g.\nYour MySQL connection id is 3\nServer version: 5.7.25 MySQL Community Server (GPL)\n\nCopyright (c) 2000, 2019, Oracle and/or its affiliates. All rights reserved.\n\nOracle is a registered trademark of Oracle Corporation and/or its\naffiliates. Other names may be trademarks of their respective\nowners.\n\nType \'help;\' or \'\\h\' for help. Type \'\\c\' to clear the current input statement.\n\nmysql> show databases;\n+--------------------+\n| Database           |\n+--------------------+\n| information_schema |\n| mysql              |\n| performance_schema |\n| sys                |\n+--------------------+\n4 rows in set (0.00 sec)\n\nmysql> \n```\n\n\n\n# 十二、添加远程访问权限\n\n```shell\nmysql> use mysql;\nReading table information for completion of table and column names\nYou can turn off this feature to get a quicker startup with -A\n\nDatabase changed\nmysql> update user set host=\'%\' where user = \'root\';\nQuery OK, 1 row affected (0.00 sec)\nRows matched: 1  Changed: 1  Warnings: 0\n\nmysql> select host,user from user;\n+-----------+---------------+\n| host      | user          |\n+-----------+---------------+\n| %         | root          |\n| localhost | mysql.session |\n| localhost | mysql.sys     |\n+-----------+---------------+\n3 rows in set (0.00 sec)\n\nmysql> exit\n\n#重启mysql生效\n[root@centos1--1- mysql]# /etc/init.d/mysqld restart\nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\nShutting down MySQL.. SUCCESS! \nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\nStarting MySQL.my_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\n SUCCESS! \n```\n\n'),(11,0,0,0,'```vue\n<!-- 不起作用的原因是该组件不支持原生事件 -->\n<template>\n  <div>\n    <Submenu name=\"1\">\n    <template slot=\"title\">\n    <Icon type=\"ios-pricetags\"></Icon>\n    <span>zhang</span>\n    </template>\n    <MenuItem name=\"1-1\" to=\"/userList\">账户中心</MenuItem>\n    <MenuItem name=\"1-2\" @click=\"logout\">退出</MenuItem>\n  </div>\n</template>\n\n<!-- @click 写成  @click.native -->\n```'),(12,0,0,0,'### 钩子函数\n\n![image.png](https://actional-1301755467.cos.ap-guangzhou.myqcloud.com/blog/2020/4/8/0cc41188-ac16-4a2c-a497-bf57657afc78.png)\n\n```vue\n#钩子加axios  demo\n<div id=\"app\">\n	<div>{{info.name}}</div>\n  <a v-bind:href=\"info.url\">点我</a>\n</div>\n\n<script>\n   var vue = new Vue({\n     el: \"#app\",\n     data() {\n       return{\n         //请求返回的参数名，必须跟json字符串一样\n         info: {\n           name: null,     //和json的键名一致才能接收到，不需要的参数可以不写\n           page: null,\n           url: null\n         }\n       }\n     },\n     mounted() {     //钩子函数\n       axios.get(\'../data.json\').then(    //请求成功执行\n         res => (this.info=res.data)\n       ).catch(        //请求失败或响应码非200~300+执行\n\n       );\n     }\n   });\n</script>\n```\n\n\n\n'),(13,0,0,0,'\n\n方法一：\n\n使用qs对象的qs.stringify() 将请求参数转换成 查询字符串类型 :\n\n\"username=111&password=111\"\n\n\n\n```\n@PostMapping(\"login\")\n    public ResponseEntity<User> login(\n            @RequestParam(\"username\") String username,\n            @RequestParam(\"password\") String password\n            \n) {\n```\n\n\n\nqs的使用方法\n\n\n\n```shell\n#安装 qs\nnpm install qs --save\n```\n\n\n\n```javascript\n//全局引入  在main.js中\nimport qs from \'qs\'\nVue.prototype.$qs = qs;\n\n\nthis.$qs.stringify()		//转换成查询字符串\nthis.$qs.parse()				//转换成json对象\n```\n\n\n\n\n\n方法二：\n\ncontroller中使用\n\n\n\n```java\n@PostMapping(\"login\")\npublic ResponseEntity<User> login(@RequestBody User user) {\n  #使用@RequestBody + 对象  接收参数\n}\n```\n\n'),(14,0,0,0,'\n\n\n\n```shell\nvue init webpack manage_view\n#一路  n 为了体验安装过程 \ncd manage_view\n#安装 vue-router\nnpm install vue-router --save -dev\n#安装 element-ui\nnpm i element-ui -S\n#安装依赖\nnpm install\n\n# 出现 run `npm audit fix` to fix them, or `npm audit` for details\n# 按照提示执行 \nnpm audit fix\n# 安装SASS加载器\nnpm install sass-loader node-sass --save-dev\n#启动测试\nnpm run dev\n```\n\n'),(15,0,0,0,'## Vue 插槽slot的用法实例,和组件之间参数传递和方法传递的方式\n\n\n\n### 1、slot的用法和参数传递\n\n```vue\n<div id=\"app\">\n\n    <todo>\n        <todo-title slot=\"todo-title\" :title=\"title\"></todo-title>\n        <todo-items slot=\"todo-items\" v-for=\"item in todoItems\" :item=\"item\"></todo-items>\n    </todo>\n\n</div>\n\n<script src=\"https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.min.js\"></script>\n<script>\n\n//slot:插槽\nVue.component(\"todo\",{\n    template: \'<div> \\\n              <slot name=\"todo-title\"></slot> \\\n              <ul> \\\n              <slot name=\"todo-items\"></slot> \\\n                </ul> \\\n                </div>\'\n   });\n\n  Vue.component(\"todo-title\",{\n    props: [\'title\'],\n    template: \'<div>{{title}}</div>\'\n  });\n\n  Vue.component(\"todo-items\",{\n    props: [\'item\'],\n    template: \'<li>{{item}}</li>\'\n  });\n\n\n  var vue = new Vue({\n    el: \"#app\",\n    data: {\n      title: \"列表\" ,\n      todoItems: [\'Java\',\'前端\',\'Linux\']\n    }\n  })\n</script>\n```\n\n\n\n\n\n## 2、自定义事件分发：不同组件之间方法传递的方式\n\n\n\n```vue\n<div id=\"app\">\n\n  <todo>\n    <todo-title slot=\"todo-title\" :title=\"title\"></todo-title>\n    <todo-items slot=\"todo-items\" v-for=\"(item,index) in todoItems\"\n                :item=\"item\" :index=\"index\" v-on:remove=\"removeItems(index)\"></todo-items>\n    						<!--\n									v-on:remove=\"removeItems(index)\" 以为在vue对象中，所以可以将对象的方法绑定事件，\n									在自定义组件标签中可以将vue对象的方法通过方法绑定的方式，传递给组件<todo-items \n									的remove方法\n								-->\n  </todo>\n\n</div>\n\n\n\n<script src=\"https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.min.js\"></script>\n<script>\n\n  //slot:插槽\n  Vue.component(\"todo\",{\n    template: \'<div> \\\n<slot name=\"todo-title\"></slot> \\\n<ul> \\\n<slot name=\"todo-items\"></slot> \\\n  </ul> \\\n  </div>\'\n  });\n\n  Vue.component(\"todo-title\",{\n    props: [\'title\'],\n    template: \'<div>{{title}}</div>\'\n  });\n\n  Vue.component(\"todo-items\",{\n    props: [\'item\',\'index\'],\n    //只能绑定当前组件的方法	@click=\"remove\"	,需要调用vue对象的方法删除vue对象的参数\n    template: \'<li>{{index}}---{{item}}<button @click=\"remove\">删除</button></li>\',\n    methods: {\n      remove: function(index) {\n        //this.$emit   自定义事件分发，不同组件之间传递方法需要用到这个方法，\n        // 第一个参数是 v-on:remove 的名字，第二个是传递的参数\n        this.$emit(\'remove\',index);\n      }\n    }\n  });\n\n  var vue = new Vue({\n    el: \"#app\",\n    data: {\n      title: \"列表\" ,\n      todoItems: [\'Java\',\'前端\',\'Linux\']\n    },\n    methods: {\n      removeItems: function (index) {\n        console.log(\"删除了\"+this.todoItems[index] + \"ok\");\n        this.todoItems.splice(index,1);     //一次删除一个元素\n      }\n    }\n  })\n</script>\n```\n\n'),(16,0,0,0,'mac安装 iTerm2   和  zsh\n\n\n\n\n\n下载  iTerm2  \n\n链接: https://pan.baidu.com/s/1b4uZvs52er0wQ_yov9Th8g  密码: o2nm\n\n查看zsh版本\n\n```shell\nMacBook-Pro:~ actional$ zsh --version\nzsh 5.3 (x86_64-apple-darwin17.0)\n\n#没有安装\nbrew install zsh zsh-completions\n```\n\n\n\n切换zsh\n\n```shell\nMacBook-Pro:~ actional$ chsh -s /bin/zsh\nChanging shell for actional.\nPassword for actional:		输入密码\n```\n\n\n\n安装oh-my-zsh\n\n```shell\nMacBook-Pro:~ actional$ sudo sh -c \"$(curl -fsSL https://raw.github.com/robbyrussell/oh-my-zsh/master/tools/install.sh)\"\n```\n\n\n\n修改主题\n\n```shell\nMacBook-Pro:~ actional$ git clone https://github.com/bhilburn/powerlevel9k.git ~/.oh-my-zsh/custom/themes/powerlevel9k\nMacBook-Pro:~ actional$ vim .zshrc\nZSH_THEME=\"powerlevel9k/powerlevel9k\"\nMacBook-Pro:~ actional$ source .zshrc\nMacBook-Pro:~ actional$ \nMacBook-Pro:~ actional$ \n```\n\n\n\n\n\n语法高亮\n\n```shell\nMacBook-Pro:~ actional$ git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-syntax-highlighting\n\nMacBook-Pro:~ actional$ vim .zshrc\n\nplugins=(git\nzsh-syntax-highlighting\n)\n\n```\n\n\n\n字体\n\n```shell\nMacBook-Pro:~ actional$ git clone https://github.com/powerline/fonts.git --depth=1\nMacBook-Pro:~ actional$ cd fonts\nMacBook-Pro:~ actional$ ./install.sh\nMacBook-Pro:~ actional$ cd ..\nMacBook-Pro:~ actional$ rm -rf fonts/\n\n```\n\n\n\n#### zsh-autosuggestions\n\n```shell\nMacBook-Pro:~ actional$ git clone https://github.com/zsh-users/zsh-autosuggestions ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-autosuggestions\nMacBook-Pro:~ actional$ vim .zshrc\nplugins=(git\nzsh-syntax-highlighting\nzsh-autosuggestions\n)\nMacBook-Pro:~ actional$ source .zshrc\n```\n\n'),(17,0,0,0,'\n\n# 一、下载mysql离线安装包tar文件\n\n使用华为镜像下载：[https://mirrors.huaweicloud.com/mysql/Downloads/MySQL-5.7/mysql-5.7.25-linux-glibc2.12-x86_64.tar.gz](https://mirrors.huaweicloud.com/mysql/Downloads/MySQL-5.7/mysql-5.7.25-linux-glibc2.12-x86_64.tar.gz)\n\n安装文件上传工具\n\n```\n[root@centos1--1- ~]# yum install lrzsz\n[root@centos1--1- ~]# cd /usr/local/\n[root@centos1--1- local]# rz			#上传文件到linux\nrz waiting to receive.\nStarting zmodem transfer.  Press Ctrl+C to cancel.\nTransferring mysql-5.7.25-linux-glibc2.12-x86_64.tar.gz...\n  100%  629748 KB    12348 KB/sec    00:00:51       0 Errors  \n[root@centos1--1- local]# tar -zxvf mysql-5.7.25-linux-glibc2.12-x86_64.tar.gz \n[root@centos1--1- local]# mv mysql-5.7.25-linux-glibc2.12-x86_64/ mysql\n```\n\n# 二、创建用户和组\n\n```\n#添加用户组mysql\n[root@centos1--1- local]# groupadd mysql\n#添加mysql用户，并归属于mysql组\n[root@centos1--1- local]# useradd -g mysql mysql\n#给mysql用户改密码\n[root@centos1--1- local]# passwd mysql\nChanging password for user mysql.\nNew password: \nBAD PASSWORD: The password is shorter than 8 characters\nRetype new password: \npasswd: all authentication tokens updated successfully.\n#给已有的用户增加工作组\n[root@centos1--1- local]# gpasswd -a mysql mysql\nAdding user mysql to group mysql\n```\n\n# 三、卸载centos7自带mariadb\n\n```\n#查看系统自带的mariadb\n[root@centos1--1- local]# rpm -qa|grep mariadb\nmariadb-libs-5.5.64-1.el7.x86_64\n#卸载mariadb\n[root@centos1--1- local]# rpm -e --nodeps mariadb-libs-5.5.64-1.el7.x86_64\n```\n\n# 四、检查mysql是否存在\n\n```\n#检查mysql是否存在,若存在	rpm -e --nodeps mysql\n[root@centos1--1- local]# rpm -qa|grep mysql\n[root@centos1--1- local]#\n```\n\n# 五、更改所属的组和用户\n\n```\n[root@centos1--1- local]# pwd		#查看当前位置\n/usr/local\n[root@centos1--1- local]# chown -R mysql mysql\n[root@centos1--1- local]# chgrp -R mysql mysql/\n[root@centos1--1- local]# cd mysql/\n[root@centos1--1- mysql]# mkdir data\n[root@centos1--1- mysql]# chown -R mysql:mysql data\n[root@centos1--1- local]# ll\ndrwxr-xr-x. 10 mysql mysql 141 Apr  3 00:11 mysql\n```\n\n# 六、创建my.cnf文件\n\n```\n[root@centos1--1- mysql]# pwd\n/usr/local/mysql\n[root@centos1--1- mysql]# vim my.cnf\n#复制以下代码\n\n[mysql]\nsocket=/var/lib/mysql/mysql.sock\n# set mysql client default chararter\ndefault-character-set=utf8\n\n[mysqld]\nsocket=/var/lib/mysql/mysql.sock\n# set mysql server port  \nport = 3306 \n# set mysql install base dir\nbasedir=/usr/local/mysql\n# set the data store dir\ndatadir=/usr/local/mysql/data\n# set the number of allow max connnection\nmax_connections=200\n# set server charactre default encoding\ncharacter-set-server=utf8\n# the storage engine\ndefault-storage-engine=INNODB\nlower_case_table_names=1\nmax_allowed_packet=16M\nexplicit_defaults_for_timestamp=true\n\n[mysql.server]\nuser=mysql\nbasedir=/usr/local/mysql\n```\n\n# 七、安装mysql\n\n```\n[root@centos1--1- mysql]# pwd\n/usr/local/mysql\n[root@centos1--1- mysql]# ./bin/mysqld --initialize --user=mysql --basedir=/usr/local/mysql/ --datadir=/usr/local/mysql/data/\n```\n\n\n\n```\n# 原因是缺少依赖 libnuma.so.1  通过yum安装\n[root@centos1--1- mysql]# yum -y install numactl.x86_64\nerror while loading shared libraries: libnuma.so.1: cannot open shared object file: No such file or directory\n```\n\n\n\n```\n##输出这样表示成功\n\n2020-04-02T16:19:01.160728Z 0 [Warning] TIMESTAMP with implicit DEFAULT value is deprecated. Please use --explicit_defaults_for_timestamp server option (see documentation for more details).\n2020-04-02T16:19:01.346712Z 0 [Warning] InnoDB: New log files created, LSN=45790\n2020-04-02T16:19:01.377032Z 0 [Warning] InnoDB: Creating foreign key constraint system tables.\n2020-04-02T16:19:01.433491Z 0 [Warning] No existing UUID has been found, so we assume that this is the first time that this server has been started. Generating a new UUID: a9e82d4e-74fd-11ea-b824-001c42010173.\n2020-04-02T16:19:01.434736Z 0 [Warning] Gtid table is not ready to be used. Table \'mysql.gtid_executed\' cannot be opened.\n2020-04-02T16:19:01.435331Z 1 [Note] A temporary password is generated for root@localhost: Ypx=pPA_G8Dh\n```\n\nYpx=pPA_G8Dh	系统随机生成的密码，用于第一次登陆\n\n设置文件及目录权限:\n\n```\n[root@centos1--1- mysql]# cp ./support-files/mysql.server /etc/init.d/mysqld\n[root@centos1--1- mysql]# chmod 777 my.cnf \n[root@centos1--1- mysql]# chmod +x /etc/init.d/mysqld\n```\n\n\n\n# 八、启动mysql\n\n```\n[root@centos1--1- mysql]# /etc/init.d/mysqld start\n#输出如下	\nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\n ERROR! MySQL server PID file could not be found!\nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\nStarting MySQL.my_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\nLogging to \'/usr/local/mysql/data/centos1--1-.shared.err\'.\n SUCCESS! \n```\n\n\n\n# 九、设置开机启动\n\n```\n[root@centos1--1- mysql]# chkconfig --level 35 mysqld on\n[root@centos1--1- mysql]# chkconfig --list mysqld\nNote: This output shows SysV services only and does not include native\n      systemd services. SysV configuration data might be overridden by native\n      systemd configuration.\n\n      If you want to list systemd services use \'systemctl list-unit-files\'.\n      To see services enabled on particular target use\n      \'systemctl list-dependencies [target]\'.\n\nmysqld          0:off   1:off   2:on    3:on    4:on    5:on    6:off\n[root@centos1--1- mysql]# chmod +x /etc/rc.d/init.d/mysqld\n[root@centos1--1- mysql]# chkconfig --add mysqld\n[root@centos1--1- mysql]# chkconfig --add mysqld\n[root@centos1--1- mysql]# service mysqld status\nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\n SUCCESS! MySQL running (10070)\n```\n\n\n\n# 十、环境变量\n\n```\n[root@centos1--1- mysql]# vim /etc/profile\n#在最后一行添加一下内容\n#mysql\nexport PATH=$PATH:/usr/local/mysql/bin\n#使配置生效\n[root@centos1--1- mysql]# source /etc/profile\n```\n\n\n\n\n\n# 十一、登陆mysql和重置密码\n\n```\n#-pYpx=pPA_G8Dh    -p 后面是安装mysql时生成的密码\n[root@centos1--1- mysql]# mysql -uroot -pYpx=pPA_G8Dh\nmysql: [Warning] Using a password on the command line interface can be insecure.\nWelcome to the MySQL monitor.  Commands end with ; or \\g.\nYour MySQL connection id is 2\nServer version: 5.7.25\n\nCopyright (c) 2000, 2019, Oracle and/or its affiliates. All rights reserved.\n\nOracle is a registered trademark of Oracle Corporation and/or its\naffiliates. Other names may be trademarks of their respective\nowners.\n\nType \'help;\' or \'\\h\' for help. Type \'\\c\' to clear the current input statement.\n\nmysql> set PASSWORD = PASSWORD(\'root\');				#\'root\'为设置的密码\nQuery OK, 0 rows affected, 1 warning (0.00 sec)\n\nmysql> flush privileges;\nQuery OK, 0 rows affected (0.00 sec)\n\nmysql> exit\n\n\n\n# 使用新密码登陆mysql\n[root@centos1--1- mysql]# mysql -uroot -proot\nmysql: [Warning] Using a password on the command line interface can be insecure.\nWelcome to the MySQL monitor.  Commands end with ; or \\g.\nYour MySQL connection id is 3\nServer version: 5.7.25 MySQL Community Server (GPL)\n\nCopyright (c) 2000, 2019, Oracle and/or its affiliates. All rights reserved.\n\nOracle is a registered trademark of Oracle Corporation and/or its\naffiliates. Other names may be trademarks of their respective\nowners.\n\nType \'help;\' or \'\\h\' for help. Type \'\\c\' to clear the current input statement.\n\nmysql> show databases;\n+--------------------+\n| Database           |\n+--------------------+\n| information_schema |\n| mysql              |\n| performance_schema |\n| sys                |\n+--------------------+\n4 rows in set (0.00 sec)\n\nmysql> \n```\n\n\n\n# 十二、添加远程访问权限\n\n```\nmysql> use mysql;\nReading table information for completion of table and column names\nYou can turn off this feature to get a quicker startup with -A\n\nDatabase changed\nmysql> update user set host=\'%\' where user = \'root\';\nQuery OK, 1 row affected (0.00 sec)\nRows matched: 1  Changed: 1  Warnings: 0\n\nmysql> select host,user from user;\n+-----------+---------------+\n| host      | user          |\n+-----------+---------------+\n| %         | root          |\n| localhost | mysql.session |\n| localhost | mysql.sys     |\n+-----------+---------------+\n3 rows in set (0.00 sec)\n\nmysql> exit\n\n#重启mysql生效\n[root@centos1--1- mysql]# /etc/init.d/mysqld restart\nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\nShutting down MySQL.. SUCCESS! \nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\nStarting MySQL.my_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\nmy_print_defaults: [Warning] World-writable config file \'/usr/local/mysql/my.cnf\' is ignored.\n SUCCESS! \n```\n\n'),(18,0,0,0,'\n\n## 一、下载依赖\n\n```\n[root@centos1--1- ~]# yum install gcc-c++ -y\n[root@centos1--1- ~]# yum install -y pcre pcre-devel -y\n[root@centos1--1- ~]# yum install -y zlib zlib-devel -y\n[root@centos1--1- ~]# yum install -y openssl openssl-devel -y\n```\n\n### 二、下载、解压\n\n华为镜像：[https://mirrors.huaweicloud.com/nginx/nginx-1.8.0.tar.gz](https://mirrors.huaweicloud.com/nginx/nginx-1.8.0.tar.gz)\n\n```\n[root@centos1--1- ~]# cd /usr/local/\n[root@centos1--1- local]# wget https://mirrors.huaweicloud.com/nginx/nginx-1.8.0.tar.gz\n-bash: wget: command not found\n[root@centos1--1- local]# yum install wget -y\n#重新执行下载\n[root@centos1--1- local]# wget https://mirrors.huaweicloud.com/nginx/nginx-1.8.0.tar.gz\n[root@centos1--1- local]# tar -zxvf nginx-1.8.0.tar.gz\n```\n\n### 三、安装\n\n```\n[root@centos1--1- local]# cd nginx-1.8.0/\n# 生成Makefile\n[root@centos1--1- nginx-1.8.0]# ./configure\n\n#或者使用自定义配置，不建议\n./configure \\\n--prefix=/usr/local/nginx \\\n--pid-path=/var/run/nginx/nginx.pid \\\n--lock-path=/var/lock/nginx.lock \\\n--error-log-path=/var/log/nginx/error.log \\\n--http-log-path=/var/log/nginx/access.log \\\n--with-http_gzip_static_module \\\n--http-client-body-temp-path=/var/temp/nginx/client \\\n--http-proxy-temp-path=/var/temp/nginx/proxy \\\n--http-fastcgi-temp-path=/var/temp/nginx/fastcgi \\\n--http-uwsgi-temp-path=/var/temp/nginx/uwsgi \\\n--http-scgi-temp-path=/var/temp/nginx/scgi\n\n#编译安装\n[root@centos1--1- nginx-1.8.0]# make && make install\n[root@centos1--1- nginx-1.8.0]# cd ..\n[root@centos1--1- local]# ll\ntotal 0\ndrwxr-xr-x. 2 root root   6 Apr 11  2018 bin\ndrwxr-xr-x. 2 root root   6 Apr 11  2018 etc\ndrwxr-xr-x. 2 root root   6 Apr 11  2018 games\ndrwxr-xr-x. 2 root root   6 Apr 11  2018 include\ndrwxr-xr-x. 2 root root   6 Apr 11  2018 lib\ndrwxr-xr-x. 2 root root   6 Apr 11  2018 lib64\ndrwxr-xr-x. 2 root root   6 Apr 11  2018 libexec\ndrwxr-xr-x. 6 root root  54 Apr  3 19:27 nginx		#生成这个文件夹\ndrwxr-xr-x. 9 1001 1001 186 Apr  3 19:24 nginx-1.8.0\ndrwxr-xr-x. 2 root root   6 Apr 11  2018 sbin\ndrwxr-xr-x. 5 root root  49 Mar 17 00:45 share\ndrwxr-xr-x. 2 root root   6 Apr 11  2018 src\n[root@centos1--1- local]# cd nginx\n[root@centos1--1- nginx]# ll\ntotal 4\ndrwxr-xr-x. 2 root root 4096 Apr  3 19:27 conf\ndrwxr-xr-x. 2 root root   40 Apr  3 19:27 html\ndrwxr-xr-x. 2 root root    6 Apr  3 19:27 logs\ndrwxr-xr-x. 2 root root   19 Apr  3 19:27 sbin\n```\n\n### 四、启动\n\n```\n[root@centos1--1- nginx]# pwd\n/usr/local/nginx\n[root@centos1--1- nginx]# ./sbin/nginx \n#启动成功不会有提示，浏览器可以访问了\n```\n\n\n\n### 五、开放端口\n\n```\n[root@centos1--1- nginx]# firewall-cmd --permanent --add-port=80/tcp  #添加80端口\n[root@centos1--1- nginx]# systemctl restart firewalld.service  #重启防火墙\n[root@centos1--1- nginx]# firewall-cmd --list-ports  #查看开放的端口\n```\n\n### 六、命令\n\n```\n[root@centos1--1- sbin]# ./nginx 						#启动\n[root@centos1--1- sbin]# ./nginx -s stop		#停止\n[root@centos1--1- sbin]# ./nginx -s quit		#退出\n[root@centos1--1- sbin]# ./nginx -s reopen	#重新打开\n[root@centos1--1- sbin]# ./nginx -s reload	#重新加载配置\n```\n\n### 七、开启自动启动\n\n```\n[root@centos1--1- sbin]# vim /etc/init.d/nginx\n#复制如下代码\n\n#!/bin/sh\n#\n# nginx - this script starts and stops the nginx daemon\n#\n# chkconfig:   - 85 15\n# description:  NGINX is an HTTP(S) server, HTTP(S) reverse \\\n#               proxy and IMAP/POP3 proxy server\n# processname: nginx\n# config:      /etc/nginx/nginx.conf\n# config:      /etc/sysconfig/nginx\n# pidfile:     /var/run/nginx.pid\n\n# Source function library.\n. /etc/rc.d/init.d/functions\n\n# Source networking configuration.\n. /etc/sysconfig/network\n\n# Check that networking is up.\n[ \"$NETWORKING\" = \"no\" ] && exit 0\n\nnginx=\"/usr/local/nginx/sbin/nginx\"\nprog=$(basename $nginx)\n\nNGINX_CONF_FILE=\"/usr/local/nginx/conf/nginx.conf\"\n\n[ -f /etc/sysconfig/nginx ] && . /etc/sysconfig/nginx\n\nlockfile=/var/lock/subsys/nginx\n\nmake_dirs() {\n   # make required directories\n   user=`$nginx -V 2>&1 | grep \"configure arguments:.*--user=\" | sed \'s/[^*]*--user=\\([^ ]*\\).*/\\1/g\' -`\n   if [ -n \"$user\" ]; then\n      if [ -z \"`grep $user /etc/passwd`\" ]; then\n         useradd -M -s /bin/nologin $user\n      fi\n      options=`$nginx -V 2>&1 | grep \'configure arguments:\'`\n      for opt in $options; do\n          if [ `echo $opt | grep \'.*-temp-path\'` ]; then\n              value=`echo $opt | cut -d \"=\" -f 2`\n              if [ ! -d \"$value\" ]; then\n                  # echo \"creating\" $value\n                  mkdir -p $value && chown -R $user $value\n              fi\n          fi\n       done\n    fi\n}\n\nstart() {\n    [ -x $nginx ] || exit 5\n    [ -f $NGINX_CONF_FILE ] || exit 6\n    make_dirs\n    echo -n $\"Starting $prog: \"\n    daemon $nginx -c $NGINX_CONF_FILE\n    retval=$?\n    echo\n    [ $retval -eq 0 ] && touch $lockfile\n    return $retval\n}\n\nstop() {\n    echo -n $\"Stopping $prog: \"\n    killproc $prog -QUIT\n    retval=$?\n    echo\n    [ $retval -eq 0 ] && rm -f $lockfile\n    return $retval\n}\n\nrestart() {\n    configtest || return $?\n    stop\n    sleep 1\n    start\n}\n\nreload() {\n    configtest || return $?\n    echo -n $\"Reloading $prog: \"\n    killproc $prog -HUP\n    retval=$?\n    echo\n}\n\nforce_reload() {\n    restart\n}\n\nconfigtest() {\n  $nginx -t -c $NGINX_CONF_FILE\n}\n\nrh_status() {\n    status $prog\n}\n\nrh_status_q() {\n    rh_status >/dev/null 2>&1\n}\n\ncase \"$1\" in\n    start)\n        rh_status_q && exit 0\n        $1\n        ;;\n    stop)\n        rh_status_q || exit 0\n        $1\n        ;;\n    restart|configtest)\n        $1\n        ;;\n    reload)\n        rh_status_q || exit 7\n        $1\n        ;;\n    force-reload)\n        force_reload\n        ;;\n    status)\n        rh_status\n        ;;\n    condrestart|try-restart)\n        rh_status_q || exit 0\n            ;;\n     *)\n        echo $\"Usage: $0 {start|stop|status|restart|condrestart|try-restart|reload|force-reload|configtest}\"\n        exit 2\nesac                                       \n         \n         \n         \n#添加权限         \n[root@centos1--1- sbin]# chmod 755 /etc/init.d/nginx\n#重新启动nginx\n[root@centos1--1- sbin]# /etc/init.d/nginx restart\n#停止nginx\n[root@centos1--1- sbin]# /etc/init.d/nginx stop\n#启动nginx\n[root@centos1--1- sbin]# /etc/init.d/nginx start\n\n#添加到chkconfig管理列表\n[root@centos1--1- sbin]# chkconfig --add /etc/init.d/nginx \n#设置开机自动启动\n[root@centos1--1- sbin]# chkconfig nginx on\n```\n\n![image.png](https://actional-1301755467.cos.ap-guangzhou.myqcloud.com/blog/2020/4/8/1b0d0524-65dc-440e-a394-c5f1b30cd5e0.png)\n'),(19,0,0,0,'\n\n## 1.安装\n\n- 下载安装包:[https://mirrors.huaweicloud.com/redis/redis-4.0.9.tar.gz](https://mirrors.huaweicloud.com/redis/redis-4.0.9.tar.gz)\n  上传到/usr/local/\n\n- 解压\n\n```shell\n tar -xvf redis-4.0.9.tar.gz\n```\n\n- 编译安装\n\n```shell\n mv redis-4.0.9 redis\n cd redis\n make && make install\n```\n\n## 2.配置\n\n修改安装目录下的redis.conf文件\n\n```shell\nvim redis.conf\n```\n\n修改以下配置：\n\n```shell\n#bind 127.0.0.1 # 将这行代码注释，监听所有的ip地址，外网可以访问\nprotected-mode no # 把yes改成no，允许外网访问\ndaemonize yes # 把no改成yes，后台运行\n```\n\n\n\n## 3.启动或停止\n\nredis提供了服务端命令和客户端命令：\n\n- redis-server 服务端命令，可以包含以下参数：\n  start 启动\n  stop 停止\n- redis-cli 客户端控制台，包含参数：\n  -h xxx 指定服务端地址，缺省值是127.0.0.1\n  -p xxx 指定服务端端口，缺省值是6379\n\n\n\n## 4.设置开机启动\n\n1) 输入命令，新建文件\n\n```sh\nvim /etc/init.d/redis\n```\n\n输入下面内容：\n\n```sh\n#!/bin/sh\n# chkconfig:   2345 90 10\n# description:  Redis is a persistent key-value database\nPATH=/usr/local/bin:/sbin:/usr/bin:/bin\n\nREDISPORT=6379\nEXEC=/usr/local/bin/redis-server\nREDIS_CLI=/usr/local/bin/redis-cli\n\nPIDFILE=/var/run/redis.pid\n\nCONF=\"/usr/local/redis/redis.conf\"\n\ncase \"$1\" in  \n    start)  \n        if [ -f $PIDFILE ]  \n        then  \n                echo \"$PIDFILE exists, process is already running or crashed\"  \n        else  \n                echo \"Starting Redis server...\"  \n                $EXEC $CONF  \n        fi  \n        if [ \"$?\"=\"0\" ]   \n        then  \n              echo \"Redis is running...\"  \n        fi  \n        ;;  \n    stop)  \n        if [ ! -f $PIDFILE ]  \n        then  \n                echo \"$PIDFILE does not exist, process is not running\"  \n        else  \n                PID=$(cat $PIDFILE)  \n                echo \"Stopping ...\"  \n                $REDIS_CLI -p $REDISPORT SHUTDOWN  \n                while [ -x ${PIDFILE} ]  \n               do  \n                    echo \"Waiting for Redis to shutdown ...\"  \n                    sleep 1  \n                done  \n                echo \"Redis stopped\"  \n        fi  \n        ;;  \n   restart|force-reload)  \n        ${0} stop  \n        ${0} start  \n        ;;  \n  *)  \n    echo \"Usage: /etc/init.d/redis {start|stop|restart|force-reload}\" >&2  \n        exit 1  \nesac\n\n```\n\n然后保存退出\n\n注意：以下信息需要根据安装目录进行调整：\n\n> EXEC=/usr/local/bin/redis-server # 执行脚本的地址\n>\n> REDIS_CLI=/usr/local/bin/redis-cli # 客户端执行脚本的地址\n>\n> PIDFILE=/var/run/redis.pid # 进程id文件地址\n>\n> CONF=\"/usr/local/src/redis-3.0.2/redis.conf\" #配置文件地址\n\n2）设置权限\n\n```sh\nchmod 755 /etc/init.d/redis\n```\n\n\n\n3）启动测试\n\n```sh\n/etc/init.d/redis start\n```\n\n启动成功会提示如下信息：\n\n```\nStarting Redis server...\nRedis is running...\n```\n\n\n\n4）设置开机自启动\n\n```sh\nchkconfig --add /etc/init.d/redis\nchkconfig redis on\n```\n\n\n\n\n\n'),(20,0,0,0,'\n# 新安装的centos7无法上网\n\n## 1.设置ONBOOT为yes\n\n```\n[root@centos1--1- ~]# vi /etc/sysconfig/network-scripts/ifcfg-eth0 \nTYPE=Ethernet\nPROXY_METHOD=none\nBROWSER_ONLY=no\nBOOTPROTO=dhcp\nDEFROUTE=yes\nIPV4_FAILURE_FATAL=no\nIPV6INIT=yes\nIPV6_AUTOCONF=yes\nIPV6_DEFROUTE=yes\nIPV6_FAILURE_FATAL=no\nIPV6_ADDR_GEN_MODE=stable-privacy\nNAME=eth0\nUUID=e8ebd777-c58e-4e3a-b668-ea605aca6842\nDEVICE=eth0\n#将下面的no修改为yes\nONBOOT=yes\n\n\n\n按esc键   之后 :x   保存退出\n```\n\n重启network\n\n\n\n```\n[root@centos1--1- ~]# service network restart\nRestarting network (via systemctl):                        [  OK  ]\n```\n\n测试是否可以上网,ping  baidu.com\n\n\n\n```\n[root@centos1--1- ~]# ping baidu.com\nPING baidu.com (39.156.69.79) 56(84) bytes of data.\n64 bytes from 39.156.69.79 (39.156.69.79): icmp_seq=1 ttl=128 time=45.3 ms\n64 bytes from 39.156.69.79 (39.156.69.79): icmp_seq=2 ttl=128 time=45.3 ms\n\nctrl+z  退出\n```\n\n\n\n## 2.安装ifconfig命令\n\n```\nyum install net-tools -y\n[root@centos1--1- ~]# ifconfig\n#发现ifconfig可以使用了\n```\n\n'),(21,0,0,0,'\n\n## 查看系统当前的shell\n\n```shell\n[root@actional ~]# echo $SHELL\n/bin/bash\n[root@actional ~]# cat /etc/shells\n/bin/sh\n/bin/bash\n/usr/bin/sh\n/usr/bin/bash\n#安装zsh\n[root@actional ~]# yum -y install zsh\n#更改默认的shell为zsh\n[root@actional ~]# chsh -s /bin/zsh\nChanging shell for root.\nShell changed.\n```\n\n\n\n## 安装oh-my-zsh\n\n```shell\n[root@actional]~# sh -c \"$(curl -fsSL https://raw.github.com/robbyrussell/oh-my-zsh/master/tools/install.sh)\"\n```\n\n\n\n### 安装**autojump**\n\n```\n➜  ~ yum install autojump autojump-zsh -y\n```\n\n\n\n### 安装zsh-syntax-highlighting、zsh-autosuggestions和zsh-completions\n\n```shell\n➜  ~	cd ~/.oh-my-zsh/custom/plugins\n➜  ~  git clone git://github.com/zsh-users/zsh-syntax-highlighting.git\n➜  ~  git clone git://github.com/zsh-users/zsh-completions.git\n➜  ~  git clone https://github.com/zsh-users/zsh-autosuggestions.git\n➜  ~ vim ~/.zshrc\n#在这里添加\n#  plugins=(git\n#        zsh-syntax-highlighting\n#		zsh-completions\n#				 zsh-autosuggestions\n#  )\n#\n#		下面两句在文件最后添加即可\n#		source ~/.oh-my-zsh/custom/plugins/zsh-syntax-highlighting/zsh-syntax-highlighting.zsh\n#		source ~/.oh-my-zsh/custom/plugins/zsh-autosuggestions/zsh-autosuggestions.zsh\n#\n\n\n➜  ~ source ~/.zshrc\n```\n\n\n\n### 修改主题\n\n```shell\n➜  ~ vim ~/.zshrc\n更改字段:ZSH_THEME=\"robbyussell\" 为 ZSH_THEME=\"agnoster\"\n➜  ~ source ~/.zshrc\n```\n\n'),(22,0,0,0,'\n\n## 1.安装\n\n- 下载安装包\n\n华为镜像：[https://repo.huaweicloud.com/java/jdk/8u202-b08/jdk-8u202-linux-x64.tar.gz](https://repo.huaweicloud.com/java/jdk/8u202-b08/jdk-8u202-linux-x64.tar.gz)\n\n```\n[root@centos1--1- ~]# cd /usr/local/\n#可以直接wget下载\n[root@centos1--1- ~]# wget https://repo.huaweicloud.com/java/jdk/8u202-b08/jdk-8u202-linux-x64.tar.gz\n#没有wget 执行命令\n[root@centos1--1- ~]# yum install -y wget\n[root@centos1--1- local]# ll\n-rw-r--r--.  1 root  root  194042837 Dec 20  2018 jdk-8u202-linux-x64.tar.gz\n[root@centos1--1- local]# tar -xvf jdk-8u202-linux-x64.tar.gz\n[root@centos1--1- local]# mkdir java\n[root@centos1--1- local]# mv jdk1.8.0_202/ java/jdk\n```\n\n\n\n\n\n# 二.环境变量\n\n```\n[root@centos1--1- jdk]# vim /etc/profile\n#在文件最后添加如下代码\n\n#set java environment\nJAVA_HOME=/usr/local/java/jdk\nCLASSPATH=.:$JAVA_HOME/lib.tools.jar\nPATH=$JAVA_HOME/bin:$PATH\nexport JAVA_HOME CLASSPATH PATH\n\n#立即生效\n[root@centos1--1- jdk]# source /etc/profile\n\n[root@centos1--1- jdk]# java -version\njava version \"1.8.0_202\"\nJava(TM) SE Runtime Environment (build 1.8.0_202-b08)\nJava HotSpot(TM) 64-Bit Server VM (build 25.202-b08, mixed mode)\n\n```\n\n\n\n完成\n\n\n\n\n\n'),(23,0,0,0,'---\ntitle: git常用命令\ndate: 2020-04-03 18:14:39\ntags:\n\n---\n\n\n\n\n\n```\n #生成公钥	三次回车后即可生成\n ssh-keygen -t rsa -C \"xxxxx@xxxxx.com\"  \n  \n #查看公钥，并添加到码云(gitee.com)，github操作基本相同\n cat ~/.ssh/id_rsa.pub\n # ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC+m7WAQk+ntdvFx.....\n # 将输出全部复制\n # 打开gitee\n```\n\n\n\n页面右上角\n\n![image.png](https://actional-1301755467.cos.ap-guangzhou.myqcloud.com/blog/2020/4/8/460d2a69-0bef-467c-80a1-fae5354da439.png)\n\n\n\n\n![image.png](https://actional-1301755467.cos.ap-guangzhou.myqcloud.com/blog/2020/4/8/e22763dd-d502-4f48-a626-5c4443b6772a.png)\n\n\n\n![image.png](https://actional-1301755467.cos.ap-guangzhou.myqcloud.com/blog/2020/4/8/db47ccee-9ee7-4e48-86d8-5a23fe437bfc.png)\n\n\n\n之后会要求输入密码，完成之后可以看到自己添加的公钥信息\n\n![image.png](https://actional-1301755467.cos.ap-guangzhou.myqcloud.com/blog/2020/4/8/f45a19c0-3076-4444-b2cc-cddb1da8f4ae.png)\n\n\n\n```\n # 创建本地仓库与远程仓库的连接   其中gitee 可以修改成自己的，gitee后面填写远程仓库的url不用加<>\n git remote add gitee <gitee仓库ssh Url>\n #重新设置远程仓库地址\n git remote set-url gitee \"gitee仓库ssh Url 不用加冒号\"\n #查看建立的连接\n git remote -v\n\n #将gitee上的文件和本地库合并\n git pull --rebase gitee master\n #拉取远端到本地\n git pull gitee master\n \n \n #提交所有变化\n git add .				#后面的 \".\"表示所有，可修改成具体文件名\n \n #提交\n git commit -m \"message\" 		#message   备注信息\n \n #将本地仓库推送到远端\n git push gitee master\n```\n\n\n\n\n\n```\n #常用命令有   初始化本地仓库\n git init\n # 克隆远程仓库\n git clone	<url>\n \n # 隐藏本地文件修改，用于拉取远端时报	\n # error: Your local changes to the following files would be overwritten by merge: \n \n git stash\n```\n\n\n\n\n\n\n\n');
/*!40000 ALTER TABLE `action_articles_param` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `action_categories`
--

DROP TABLE IF EXISTS `action_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action_categories` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类主键',
  `category_name` varchar(255) NOT NULL COMMENT '分类名',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action_categories`
--

LOCK TABLES `action_categories` WRITE;
/*!40000 ALTER TABLE `action_categories` DISABLE KEYS */;
INSERT INTO `action_categories` VALUES (1,'Java'),(2,'Python'),(3,'前端'),(4,'Linux'),(5,'Mac');
/*!40000 ALTER TABLE `action_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `action_comments`
--

DROP TABLE IF EXISTS `action_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action_comments` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `submitter_id` bigint(20) NOT NULL COMMENT '发表评论id',
  `comment_like_count` bigint(20) NOT NULL COMMENT '点赞数',
  `comment_date` datetime DEFAULT NULL COMMENT '评论日期',
  `comment_content` text NOT NULL COMMENT '评论内容',
  `parent_comment_id` bigint(20) NOT NULL COMMENT '父评论ID',
  PRIMARY KEY (`comment_id`),
  KEY `reviewers_id` (`submitter_id`),
  KEY `parent_comment_id` (`parent_comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action_comments`
--

LOCK TABLES `action_comments` WRITE;
/*!40000 ALTER TABLE `action_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `action_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `action_config`
--

DROP TABLE IF EXISTS `action_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action_config` (
  `config_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `config_name` varchar(100) NOT NULL DEFAULT '' COMMENT '配置项的名称',
  `config_value` varchar(200) NOT NULL DEFAULT '' COMMENT '配置项的值',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action_config`
--

LOCK TABLES `action_config` WRITE;
/*!40000 ALTER TABLE `action_config` DISABLE KEYS */;
INSERT INTO `action_config` VALUES (1,'footerAbout','your personal blog. have fun.','2018-11-11 00:00:00'),(2,'footerCopyRight','Actional','2018-11-11 00:00:00'),(3,'footerICP','粤ICP备20039540号-1','2018-11-11 00:00:00'),(4,'footerPoweredBy','https://github.com/cxg83843652','2018-11-11 00:00:00'),(5,'footerPoweredByURL','https://github.com/cxg83843652','2018-11-11 00:00:00'),(6,'websiteDescription','springboot vue k8s springcloud','2018-11-11 00:00:00'),(7,'websiteIcon','https://action-app-1301755467.cos.ap-guangzhou.myqcloud.com/image/timg.jpeg','2018-11-11 00:00:00'),(8,'websiteLogo','https://action-app-1301755467.cos.ap-guangzhou.myqcloud.com/image/timg.jpeg','2018-11-11 00:00:00'),(9,'websiteName','个人学习编程历程','2018-11-11 00:00:00'),(10,'yourAvatar','https://action-app-1301755467.cos.ap-guangzhou.myqcloud.com/image/timg.jpeg','2018-11-11 00:00:00'),(11,'yourEmail','854356662@qq.com','2018-11-11 00:00:00'),(12,'yourName','actional','2018-11-11 00:00:00');
/*!40000 ALTER TABLE `action_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `action_labels`
--

DROP TABLE IF EXISTS `action_labels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action_labels` (
  `label_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标签主键',
  `label_name` varchar(20) NOT NULL COMMENT '标签名',
  PRIMARY KEY (`label_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action_labels`
--

LOCK TABLES `action_labels` WRITE;
/*!40000 ALTER TABLE `action_labels` DISABLE KEYS */;
INSERT INTO `action_labels` VALUES (1,'Java'),(2,'JavaScript'),(3,'Spring'),(4,'SpringBoot'),(5,'SpringMVC'),(6,'MyBatis'),(7,'Vue'),(12,'MyBatisPlus'),(13,'Centos7'),(15,'mac'),(16,'环境');
/*!40000 ALTER TABLE `action_labels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `action_link`
--

DROP TABLE IF EXISTS `action_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action_link` (
  `link_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '友链表主键id',
  `link_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '友链类别 0-友链 1-推荐 2-个人网站',
  `link_name` varchar(50) NOT NULL COMMENT '网站名称',
  `link_url` varchar(100) NOT NULL COMMENT '网站链接',
  `link_description` varchar(100) NOT NULL COMMENT '网站描述',
  `link_rank` int(11) NOT NULL DEFAULT '0' COMMENT '用于列表排序',
  `del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `link_logo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`link_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='友链图标';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action_link`
--

LOCK TABLES `action_link` WRITE;
/*!40000 ALTER TABLE `action_link` DISABLE KEYS */;
INSERT INTO `action_link` VALUES (20,0,'狂神说Java','https://space.bilibili.com/95256449/','很不错的免费Java教程',50,0,'2020-05-04 00:54:16','https://action-app-1301755467.cos.ap-guangzhou.myqcloud.com/image/83bb511365da513c55aa3d1958524f3b7db40684.jpg_64x64.jpg'),(21,0,'程序羊','https://space.bilibili.com/384068749/','分享Java或编程相关的见解',50,0,'2020-05-04 01:24:42','https://action-app-1301755467.cos.ap-guangzhou.myqcloud.com/image/569499c4cbabfa3c5a5a672e7c02a07d59b64bd1.jpg_64x64.jpg'),(22,1,'华为镜像站','https://mirrors.huaweicloud.com/','软件资源',20,0,'2020-05-05 13:16:34','https://action-app-1301755467.cos.ap-guangzhou.myqcloud.com/image/download.jpeg');
/*!40000 ALTER TABLE `action_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `action_user`
--

DROP TABLE IF EXISTS `action_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_nickname` varchar(20) NOT NULL COMMENT '昵称',
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `user_password` varchar(128) NOT NULL COMMENT '密码',
  `user_salt` varchar(128) NOT NULL COMMENT '加密盐',
  `user_email` varchar(20) NOT NULL COMMENT '邮件',
  `user_regis_time` date NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  KEY `user_email` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action_user`
--

LOCK TABLES `action_user` WRITE;
/*!40000 ALTER TABLE `action_user` DISABLE KEYS */;
INSERT INTO `action_user` VALUES (1,'action','admin','ed101462f6c4732369eceebcee452c03','edee5833daec442ab815ec804348d391','854356662@qq.com','2020-05-08');
/*!40000 ALTER TABLE `action_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-20 23:24:58
