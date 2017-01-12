sum:https://yongjhih.gitbooks.io/feed/content/RxJava.html?utm_campaign=CodeTengu&utm_medium=web&utm_source=CodeTengu_10
1.程序开启了Incremental Dex :增长DEX 增量构建,仅仅在输入的文件已经被修改后重构它的输出.它非常有效的降低了构建时间.只有被修改的dexfile会被重新计算和重新打包进APK文件.
    更多gradle配置请参阅:http://tools.android.com/tech-docs/new-build-system/user-guide#TOC-Introduction
2.程序配置了两个key,debug_panda.jks,release_panda.jks.Alias为panda,密码为123456
3.buildType设置为两个分别对应debug,release
4.程序使用java8 lamdba表达式
    更多参阅: Stanley B.Lippman Josee Lajoie．《C++ Primer》．北京：电子工业出版社，2013：346
    1.使用mavenCentral()仓库源
    2.添加插件me.tatarka:gradle-retrolambda:3.3.0-beta4
    3.配置apply plugin: 'me.tatarka.retrolambda'本例插件为2016-09-08时期的最新插件.
        eg:findViewById(R.id.tv).setOnClickListener(v-> Toast.makeText(MainActivity.this,"Lamdba",Toast.LENGTH_SHORT).show());
5.日志记录采用的是slf4j:module引用
    compile 'org.slf4j:slf4j-api:1.7.21'
    compile 'com.github.tony19:logback-android-core:1.1.1-6'
    compile 'com.github.tony19:logback-android-classic:1.1.1-6'
    配置文件:logback.xml
    更多参数请参阅:http://www.slf4j.org/
        eg:LoggerFactory.getLogger(StartActivity.class).debug("Hello Start Activity!");
6.数据库操作采用GreedDao
    更多参阅:http://greenrobot.org/greendao/   使用mavenCentral()管理代码
    (1).配置插件
        classpath 'org.greenrobot:greendao-gradle-plugin:3.1.1'//greendao 数据库orm
        apply plugin: 'org.greenrobot.greendao'
        greendao {
            schemaVersion 1
            daoPackage 'com.watermelon.luomi.panda.greendao'//生成的实例存在此包内
            targetGenDir 'src/main/java'//生成实用目标文件夹
        }
    (2).引入相关依赖
        compile 'org.greenrobot:greendao:3.1.1'//当前版本(2016-9-9)
        compile 'org.greenrobot:greendao-generator:3.0.0'//更新以前版本使用
    (3).使用注解
        @Entity
        @Id
        @Property...
    (4).加密解密
        compile 'net.zetetic:android-database-sqlcipher:3.5.3'//加密解密
        new DaoMaster.DevOpenHelper(this,"name").getEncryptedWritableDb(PWD);
        new DaoMaster(a.getEncryptedWritableDb(PWD)).newSession().getUserDao().loadAll();
    (5)增加,删除,查找,更改
            //database
            User user = new User();
            user.setName("luomi");
            UserDao userDao = ((PandaApplication) getApplication()).getDaoSession().getUserDao();
            long insert = userDao.insert(user);
            List<User> users;
            users = userDao.loadAll();

            user.setName("luomi2");
            userDao.update(user);
            users = userDao.loadAll();

            userDao.delete(user);
            users = userDao.loadAll();
7.网络访问框架采用Retrofit
    Retrofit是Square公司开发的一款针对Android网络请求的框架，Retrofit2底层基于OkHttp实现的，OkHttp现在已经得到Google官方认可，大量的app都采用OkHttp做网络请求，其源码详见OkHttp Github。
    更多参阅:   http://square.github.io/retrofit/
    更多参阅:   http://square.github.io/okhttp/
    添加依赖:   compile 'com.squareup.okhttp3:okhttp:3.4.1'
    java.net.SocketException: socket failed: EACCES (Permission denied) -:
         eg. POST提交键值对:
8.Rxjava&Rxandroid响应式编程
    https://github.com/ReactiveX/RxAndroid/
    更多参阅:  《Learning RxJava (for Android) by example》
9.图片加载策略：Picasso
    更多参阅: http://square.github.io/picasso/
10.下拉刷新,上滑加载更多 RecyclerViewManager
    更多参阅:https://github.com/Syehunter/RecyclerViewManager
11.轮播:Rollviewpager
    更多参阅:https://github.com/Jude95/RollViewPager
12.底部切换按钮:BottomNavigationBar
    更多参阅:https://github.com/Ashok-Varma/BottomNavigation
13.数据展示采用databinding方式
    更多参阅:google android 手册

extra:项目机构及代码数量说明
    activity        活动
        	base  基础activity 需要集成相关的用户收集sdk,活动生命周期管理...   9
        	comments  评论 74
        	comments adapter 评论适配器(网络请求,刷新,加载,不同类别布局适配,图片加载采用picasso) 131
        	login 登录 64
        	main 主页 包含新闻,社区,我 tab切换,以及相关item的事件处理(集中处理item的逻辑) 156
        	news detail 新闻详情 44
        	publish 发布 31

        	fragment
        	    	forum 114 社区
                	forumadapter 202 社区适配器(网络请求,刷新,加载,不同类别布局适配,图片加载采用picasso)
                	my 126 我的
                	settingadapter 69 设置适配器(网络请求,刷新,加载,不同类别布局适配,图片加载采用picasso)
                	news 156 新闻
                	newsadapter 152 设置适配器(网络请求,刷新,加载,不同类别布局适配,图片加载采用picasso)
    net             网络
            apiservice 封装网络请求
            commentResponse
            netservice retrofit网络请求
            newsdetailResponse
            newsResponse
            requestContent 测试请求内容
            responseContent 测试响应内容
            versionResponse
    utils           工具
            bindingUtils databinding绑定数据
            commonutils 通用工具
            devicetool 设备信息获取
            md5tool 加密
    view            自定义视图
            customdialog

    mainconstant 常量
    pandaapplication 应用