IOC容器负责实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。交由Spring容器统一进行管理，从而实现松耦合
实现原理 
当web容器启动的时候，spring的全局bean的管理器会去xml配置文件中扫描的包下面获取到所有的类，
并根据你使用的注解，进行对应的封装，封装到全局的bean容器中进行管理，一旦容器初始化完毕，
beanID以及bean实例化的类对象信息就全部存在了，现在我们需要在某个service里面调用另一个bean的某个方法的时候，
我们只需要依赖注入进来另一个bean的Id即可，调用的时候，spring会去初始化完成的bean容器中获取即可，
如果存在就把依赖的bean的类的实例化对象返回给你，你就可以调用依赖的bean的相关方法或属性等；