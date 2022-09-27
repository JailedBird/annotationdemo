## 注解处理器 Arouter

### 参考文献

1、 关于增联 编译：https://jiyang.site/posts/2020-03-24-%E8%AE%A9annotation-processor-%E6%94%AF%E6%8C%81%E5%A2%9E%E9%87%8F%E7%BC%96%E8%AF%91/

改动Arouter `@Route` 的注解处理器 （[源代码](https://github.com/alibaba/ARouter/blob/develop/arouter-compiler/src/main/java/com/alibaba/android/arouter/compiler/processor/RouteProcessor.java)）: 添加如下日志打印

```

    /**
     * {@inheritDoc}
     *
     * @param annotations
     * @param roundEnv
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (CollectionUtils.isNotEmpty(annotations)) {
            logger.info("DEBUG PROCESSOR\n");
            logger.info(roundEnv.toString());
            logger.info("DEBUG PROCESSOR END\n");
            Set<? extends Element> routeElements = roundEnv.getElementsAnnotatedWith(Route.class);
            try {
                logger.info(">>> Found routes, start... <<<");
                this.parseRoutes(routeElements);

            } catch (Exception e) {
                logger.error(e);
            }
            return true;
        }

        return false;
    }
```



生成的输出日志如下：

```
> Task :arouter-api:generateDebugRFile

> Task :arouter-api:compileDebugJavaWithJavac
ע: ARouter::Compiler The user has configuration the module name, it was [arouterapi]
ע: ARouter::Compiler >>> AutowiredProcessor init. <<<
ע: ARouter::Compiler The user has configuration the module name, it was [arouterapi]
ע: ARouter::Compiler >>> InterceptorProcessor init. <<<
ע: ARouter::Compiler The user has configuration the module name, it was [arouterapi]
ע: ARouter::Compiler >>> RouteProcessor init. <<<
ע: ARouter::Compiler >>> Found routes, start... <<<
ע: ARouter::Compiler >>> Found routes, size is 2 <<<
ע: ARouter::Compiler >>> Found provider route: com.alibaba.android.arouter.core.AutowiredServiceImpl <<<
ע: ARouter::Compiler >>> Start categories, group = arouter, path = /arouter/service/autowired <<<
ע: ARouter::Compiler >>> Found provider route: com.alibaba.android.arouter.core.InterceptorServiceImpl <<<
ע: ARouter::Compiler >>> Start categories, group = arouter, path = /arouter/service/interceptor <<<
ע: ARouter::Compiler >>> Generated group: arouter<<<
ע: ARouter::Compiler >>> Generated provider map, name is ARouter$$Providers$$arouterapi <<<
ע: ARouter::Compiler >>> Generated root, name is ARouter$$Root$$arouterapi <<<


> Task :arouter-api:bundleLibResDebug NO-SOURCE
> Task :module-java-export:generateDebugRFile
> Task :arouter-api:bundleLibRuntimeToJarDebug
> Task :module-kotlin:generateDebugRFile
> Task :arouter-api:bundleLibCompileToJarDebug

> Task :module-java-export:compileDebugJavaWithJavac
ע: ARouter::Compiler The user has configuration the module name, it was [modulejavaexport]
ע: ARouter::Compiler >>> AutowiredProcessor init. <<<
ע: ARouter::Compiler The user has configuration the module name, it was [modulejavaexport]
ע: ARouter::Compiler >>> InterceptorProcessor init. <<<
ע: ARouter::Compiler The user has configuration the module name, it was [modulejavaexport]
ע: ARouter::Compiler >>> RouteProcessor init. <<<

> Task :module-kotlin:kaptGenerateStubsDebugKotlin
> Task :module-java-export:bundleLibResDebug NO-SOURCE
> Task :module-java-export:bundleLibCompileToJarDebug
> Task :module-java:generateDebugRFile
> Task :app:processDebugResources

> Task :module-java:compileDebugJavaWithJavac
ע: ARouter::Compiler The user has configuration the module name, it was [modulejava]
ע: ARouter::Compiler >>> AutowiredProcessor init. <<<
ע: ARouter::Compiler >>> Found autowired field, start... <<<
ע: ARouter::Compiler categories finished.
ע: ARouter::Compiler >>> Start process 12 field in BlankFragment ... <<<
ע: ARouter::Compiler >>> BlankFragment has been processed, BlankFragment$$ARouter$$Autowired has been generated. <<<
ע: ARouter::Compiler >>> Start process 1 field in BaseActivity ... <<<
ע: ARouter::Compiler >>> BaseActivity has been processed, BaseActivity$$ARouter$$Autowired has been generated. <<<
ע: ARouter::Compiler >>> Start process 13 field in Test1Activity ... <<<
ע: ARouter::Compiler >>> Test1Activity has been processed, Test1Activity$$ARouter$$Autowired has been generated. <<<
ע: ARouter::Compiler >>> Start process 3 field in Test3Activity ... <<<
ע: ARouter::Compiler >>> Test3Activity has been processed, Test3Activity$$ARouter$$Autowired has been generated. <<<
ע: ARouter::Compiler >>> Start process 1 field in Test2Activity ... <<<
ע: ARouter::Compiler >>> Test2Activity has been processed, Test2Activity$$ARouter$$Autowired has been generated. <<<
ע: ARouter::Compiler >>> Autowired processor stop. <<<
ע: ARouter::Compiler The user has configuration the module name, it was [modulejava]
ע: ARouter::Compiler >>> InterceptorProcessor init. <<<
ע: ARouter::Compiler >>> Found interceptors, size is 2 <<<
ע: ARouter::Compiler A interceptor verify over, its com.alibaba.android.arouter.demo.module1.testinterceptor.Test1Interceptor
ע: ARouter::Compiler A interceptor verify over, its com.alibaba.android.arouter.demo.module1.TestInterceptor90
ע: ARouter::Compiler >>> Interceptor group write over. <<<
ע: ARouter::Compiler The user has configuration the module name, it was [modulejava]
ע: ARouter::Compiler >>> RouteProcessor init. <<<
ע: ARouter::Compiler DEBUG PROCESSOR
ע: ARouter::Compiler [errorRaised=false, rootElements=[com.alibaba.android.arouter.demo.module1.BlankFragment, com.alibaba.android.arouter.demo.module1.MainLooper, com.alibaba.android.arouter.demo.module1.testactivity.BaseActivity, com.alibaba.android.arouter.demo.module1.testactivity.Test1Activity, com.alibaba.android.arouter.demo.module1.testactivity.Test2Activity, com.alibaba.android.arouter.demo.module1.testactivity.Test3Activity, com.alibaba.android.arouter.demo.module1.testactivity.Test4Activity, com.alibaba.android.arouter.demo.module1.testactivity.TestDynamicActivity, com.alibaba.android.arouter.demo.module1.testinterceptor.Test1Interceptor, com.alibaba.android.arouter.demo.module1.TestInterceptor90, com.alibaba.android.arouter.demo.module1.TestModule2Activity, com.alibaba.android.arouter.demo.module1.TestModuleActivity, com.alibaba.android.arouter.demo.module1.testservice.HelloServiceImpl, com.alibaba.android.arouter.demo.module1.testservice.JsonServiceImpl, com.alibaba.android.arouter.demo.module1.testservice.SingleService, com.alibaba.android.arouter.demo.module1.TestWebview, com.alibaba.android.arouter.demo.module1.BuildConfig], processingOver=false]
ע: ARouter::Compiler DEBUG PROCESSOR END
ע: ARouter::Compiler >>> Found routes, start... <<<
ע: ARouter::Compiler >>> Found routes, size is 11 <<<
ע: ARouter::Compiler >>> Found fragment route: com.alibaba.android.arouter.demo.module1.BlankFragment <<<
ע: ARouter::Compiler >>> Start categories, group = test, path = /test/fragment <<<
ע: ARouter::Compiler >>> Found activity route: com.alibaba.android.arouter.demo.module1.testactivity.Test1Activity <<<
ע: ARouter::Compiler >>> Start categories, group = test, path = /test/activity1 <<<
ע: ARouter::Compiler >>> Found activity route: com.alibaba.android.arouter.demo.module1.testactivity.Test2Activity <<<
ע: ARouter::Compiler >>> Start categories, group = test, path = /test/activity2 <<<
ע: ARouter::Compiler >>> Found activity route: com.alibaba.android.arouter.demo.module1.testactivity.Test3Activity <<<
ע: ARouter::Compiler >>> Start categories, group = test, path = /test/activity3 <<<
ע: ARouter::Compiler >>> Found activity route: com.alibaba.android.arouter.demo.module1.testactivity.Test4Activity <<<
ע: ARouter::Compiler >>> Start categories, group = test, path = /test/activity4 <<<
ע: ARouter::Compiler >>> Found activity route: com.alibaba.android.arouter.demo.module1.TestModule2Activity <<<
ע: ARouter::Compiler >>> Start categories, group = m2, path = /module/2 <<<
ע: ARouter::Compiler >>> Found activity route: com.alibaba.android.arouter.demo.module1.TestModuleActivity <<<
ע: ARouter::Compiler >>> Start categories, group = module, path = /module/1 <<<
ע: ARouter::Compiler >>> Found provider route: com.alibaba.android.arouter.demo.module1.testservice.HelloServiceImpl <<<
ע: ARouter::Compiler >>> Start categories, group = yourservicegroupname, path = /yourservicegroupname/hello <<<
ע: ARouter::Compiler >>> Found provider route: com.alibaba.android.arouter.demo.module1.testservice.JsonServiceImpl <<<
ע: ARouter::Compiler >>> Start categories, group = yourservicegroupname, path = /yourservicegroupname/json <<<
ע: ARouter::Compiler >>> Found provider route: com.alibaba.android.arouter.demo.module1.testservice.SingleService <<<
ע: ARouter::Compiler >>> Start categories, group = yourservicegroupname, path = /yourservicegroupname/single <<<
ע: ARouter::Compiler >>> Found activity route: com.alibaba.android.arouter.demo.module1.TestWebview <<<
ע: ARouter::Compiler >>> Start categories, group = test, path = /test/webview <<<
ע: ARouter::Compiler >>> Generated group: test<<<
ע: ARouter::Compiler >>> Generated group: m2<<<
ע: ARouter::Compiler >>> Generated group: yourservicegroupname<<<
ע: ARouter::Compiler >>> Generated group: module<<<
ע: ARouter::Compiler >>> Generated provider map, name is ARouter$$Providers$$modulejava <<<
ע: ARouter::Compiler >>> Generated root, name is ARouter$$Root$$modulejava <<<


> Task :module-java:bundleLibResDebug NO-SOURCE
> Task :module-java-export:bundleLibRuntimeToJarDebug

> Task :module-kotlin:kaptDebugKotlin
ע: ARouter::Compiler The user has configuration the module name, it was [modulekotlin]ע: 
ARouter::Compiler >>> AutowiredProcessor init. <<<ע: 
ARouter::Compiler >>> Found autowired field, start... <<<ע: ARouter::Compiler categories finished.ע: 
ARouter::Compiler >>> Start process 2 field in KotlinTestActivity ... <<<ע: 
ARouter::Compiler >>> KotlinTestActivity has been processed, KotlinTestActivity$$ARouter$$Autowired has been generated. <<<ע: 
ARouter::Compiler >>> Autowired processor stop. <<<ע: ARouter::Compiler The user has configuration the module name, it was [modulekotlin]ע: 
ARouter::Compiler >>> InterceptorProcessor init. <<<ע: ARouter::Compiler The user has configuration the module name, it was [modulekotlin]ע: 
ARouter::Compiler >>> RouteProcessor init. <<<ע: 
ARouter::Compiler DEBUG PROCESSORע: 
ARouter::Compiler [errorRaised=false, rootElements=[com.alibaba.android.arouter.demo.kotlin.KotlinTestActivity, error.NonExistentClass, com.alibaba.android.arouter.demo.kotlin.BuildConfig, com.alibaba.android.arouter.demo.kotlin.TestNormalActivity], processingOver=false]ע: 
ARouter::Compiler DEBUG PROCESSOR ENDע: 
ARouter::Compiler >>> Found routes, start... <<<ע: 
ARouter::Compiler >>> Found routes, size is 2 <<<ע: 
ARouter::Compiler >>> Found activity route: com.alibaba.android.arouter.demo.kotlin.KotlinTestActivity <<<ע: 
ARouter::Compiler >>> Start categories, group = kotlin, path = /kotlin/test <<<ע: 
ARouter::Compiler >>> Found activity route: com.alibaba.android.arouter.demo.kotlin.TestNormalActivity <<<ע: 
ARouter::Compiler >>> Start categories, group = kotlin, path = /kotlin/java <<<ע: 
ARouter::Compiler >>> Generated group: kotlin<<<ע: 
ARouter::Compiler >>> Generated provider map, name is ARouter$$Providers$$modulekotlin <<<ע: 
ARouter::Compiler >>> Generated root, name is ARouter$$Root$$modulekotlin <<<
> Task :module-kotlin:compileDebugKotlin
> Task :module-kotlin:compileDebugJavaWithJavac
> Task :module-kotlin:bundleLibResDebug
> Task :module-kotlin:bundleLibCompileToJarDebug
> Task :module-java:bundleLibRuntimeToJarDebug
> Task :module-java:bundleLibCompileToJarDebug

> Task :app:compileDebugJavaWithJavac

> Task :app:compileDebugSources
> Task :module-kotlin:bundleLibRuntimeToJarDebug
> Task :app:transformClassesWithCom.alibaba.arouterForDebug
> Task :app:dexBuilderDebug
> Task :app:mergeDebugJavaResource
> Task :app:mergeExtDexDebug
> Task :app:mergeDebugNativeLibs
> Task :app:stripDebugDebugSymbols NO-SOURCE
> Task :app:mergeDexDebug
> Task :app:packageDebug
> Task :xxx...
```



**可以看到每个模块的APT代码生成都是单独进行的，即是多少个模块（Arouter源码中是`module-kotlin`和`module-java` 2个模块）需要 `@Route` 注解生成代码， 就需要运行多少次（有效）process， 需要注意的， 每个模块处理process的参数RoundEnvironment时， 都只能访问自己模块的所有信息， 无法访问其他模块，（这个元素基本都是类这一级别的）**

注意：process会多次调用，但是只有再annotations不为空是才有实际的意义（这个只会调用一次）， 避免多次生成文件，导致报错 。这一点可以参见这里：[【错误记录】Android 编译时技术报错 ( 注解处理器 process 方法多次调用问题 )](https://blog.csdn.net/shulianghan/article/details/117161589)





### Arouter生成规则

要彻底搞懂Arouter的文件生成规则， 就需要先了解 代码生成在那个位置：大体这样子的 `module-jname\build\intermediates\javac\debug\classes\com\alibaba\android\arouter\routes\ARouter$$Group$$m2.class`， 注意他们拥有相同的包名: `com.alibaba.android.arouter.routes`  



#### Root 模块级别， 

一个模块只能存在一个`Root` （固定前置加模块名）, 包含`Set<group, Group>` ， 其中的 `Group` （固定前缀加组名）保存若干组路径映射（路由路径到实际要跳转到的类Class实例）， 相同group的路径搞在一起！默认其实不需要显示指定group，一个模块使用相同的路径前缀就只会存在一个Group

Arouter demo中的module-java的内容如下：

```
 C:\Android\ARouter\module-java\build\intermediates\javac\debug\classes\com\alibaba\android\arouter\routes 的目录

2022/09/21  20:02    <DIR>          .
2022/09/21  20:02    <DIR>          ..
2022/09/21  20:02             1,385 ARouter$$Group$$m2.class
2022/09/21  20:02             1,400 ARouter$$Group$$module.class
2022/09/21  20:02             1,181 ARouter$$Group$$test$1.class
2022/09/21  20:02               928 ARouter$$Group$$test$2.class
2022/09/21  20:02               968 ARouter$$Group$$test$3.class
2022/09/21  20:02             1,160 ARouter$$Group$$test$4.class
2022/09/21  20:02             2,434 ARouter$$Group$$test.class
2022/09/21  20:02             1,754 ARouter$$Group$$yourservicegroupname.class
2022/09/21  20:02             1,236 ARouter$$Interceptors$$modulejava.class
2022/09/21  20:02             1,943 ARouter$$Providers$$modulejava.class
2022/09/21  20:02             1,324 ARouter$$Root$$modulejava.class
              11 个文件         15,713 字节
               2 个目录 144,768,147,456 可用字节
```



内容如下：

```
public class ARouter$$Root$$modulejava implements IRouteRoot {
    public ARouter$$Root$$modulejava() {
    }

    public void loadInto(Map<String, Class<? extends IRouteGroup>> routes) {
        routes.put("m2", m2.class);
        routes.put("module", module.class);
        routes.put("test", test.class);
        routes.put("yourservicegroupname", yourservicegroupname.class);
    }
}


public class ARouter$$Group$$m2 implements IRouteGroup {
    public ARouter$$Group$$m2() {
    }

    public void loadInto(Map<String, RouteMeta> atlas) {
        atlas.put("/module/2", RouteMeta.build(RouteType.ACTIVITY, TestModule2Activity.class, "/module/2", "m2", (Map)null, -1, -2147483648));
    }
}

public class ARouter$$Group$$yourservicegroupname implements IRouteGroup {
    public ARouter$$Group$$yourservicegroupname() {
    }

    public void loadInto(Map<String, RouteMeta> atlas) {
        atlas.put("/yourservicegroupname/hello", RouteMeta.build(RouteType.PROVIDER, HelloServiceImpl.class, "/yourservicegroupname/hello", "yourservicegroupname", (Map)null, -1, -2147483648));
        atlas.put("/yourservicegroupname/json", RouteMeta.build(RouteType.PROVIDER, JsonServiceImpl.class, "/yourservicegroupname/json", "yourservicegroupname", (Map)null, -1, -2147483648));
        atlas.put("/yourservicegroupname/single", RouteMeta.build(RouteType.PROVIDER, SingleService.class, "/yourservicegroupname/single", "yourservicegroupname", (Map)null, -1, -2147483648));
    }
}

```



最后复杂的就是初始化那一部分， 待完善！



### 初始化

谈论初始化的时候， 必须要注意： 不同的包下不能存在相同的group， 原因在于每个模块生成Group的时候不会带上包名(路由是全局的、包名是局部的， 使用的时候不需要包名参与导航)， 因此 如果module-kotlin和module-kotlin 都存在m2， 在合并代码的时候（mergeDex）就会生成冲突： 这一点也保证了， 路由表组名对应的分表（仅存在一个模块中）是唯一的！

![](https://zhaojunchen-1259455842.cos.ap-nanjing.myqcloud.com/img/20220922100858.png)