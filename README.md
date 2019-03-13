## 简介

Arthas排查spring boot问题的案例。

为了Clone更快和演示更方便,对[@断岭](https://github.com/hengyunabc)原工程的示例代码进行了部分修改和整合。
* https://github.com/hengyunabc/spring-boot-inside/tree/master/demo-arthas-spring-boot

[PPT(TBD)]()

## 链接

* https://github.com/alibaba/arthas
* https://alibaba.github.io/arthas/trace.html

打个广告，Arthas正在征集使用者，您的使用是对我们最大的支持。
如果Arthas对您排找问题有帮助，请到这里来登记下，并在30分钟内成为Arthas Contributor：

https://github.com/alibaba/arthas/issues/395

## 命令列表

### 0. 安装Arthas
```bash
$ wget https://alibaba.github.io/arthas/arthas-boot.jar
$ java -jar arthas-boot.jar
```

### 1. Dashboard
```bash
$ dashboard
```

### 2. Thread
找出当前最繁忙的top3线程，取样间隔为1秒
```bash
$ thread -n 3 -i 1000

```

### 3. Classloader
展示Classloader信息

```bash
$ classloader -t
$ classloader -l
```

### 4. Sc
展示类加载信息
```bash
$ sc -d java.lang.String
$ sc -d -f -x 2 com.taobao.arthas.demo.web.UserController
```

### 5. Watch
监控方法调用人参/异常值
```bash
$ watch com.taobao.arthas.demo.web.UserController * '{params[0], throwExp}' 'true'
```

### 6. Trace
监控方法调用栈，统计调用时间
```bash
$ trace  com.taobao.arthas.demo.web.UserController findUserById2 > trace.log
```

### 7. Jad
反编译
```bash
$ jad --source-only com.taobao.arthas.demo.web.UserController
```

### 8. Mc
内存编译器
```bash
$ mc UserController.java
```

### 9. Redefine
热更新
```bash
$ redefine -c 18b4aac2 ${CLASS_PATH}
```

### 10. OGNL
-  [OGNL官方文档](https://commons.apache.org/proper/commons-ognl/index.html)
-  [Arthas-ognl教程](https://alibaba.github.io/arthas/ognl.html)

查看LOGGER实现类
```bash
$ ognl '@com.taobao.arthas.demo.web.UserController@LOGGER'
```

动态修改LOGGER LEVEL
```bash
$ ognl '@com.taobao.arthas.demo.web.UserController   @LOGGER.setLevel(@ch.qos.logback.classic.Level@INFO)'
```

获取加载的日志配置文件
```bash
$ ognl '#map1=@org.slf4j.LoggerFactory@getLogger("root").loggerContext.objectMap, #map1.get("CONFIGURATION_WATCH_LIST")'
```
