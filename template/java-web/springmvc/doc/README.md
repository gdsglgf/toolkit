mvn jetty:run

http://localhost:8090/today

国际化支持(基于Session和@RequestMapping注解)
http://localhost:8090/?language=en_US
http://localhost:8090/?language=zh_CN

home.jsp并不能实现语言的切换
http://localhost:8090/home.jsp?language=en_US
http://localhost:8090/home.jsp?language=zh_CN

ContextLoaderListener和DispatcherServlet加载的配置文件应该分开来比较好。