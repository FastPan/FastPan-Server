
package com.stu.fastpan;


import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean工厂
 * 
 * @author weizhansong
 * @version Id: BeanFactory.java, v 0.1
 */
public final class BeanFactory {
    private static final String SPRING_FILE = "classpath:spring/spring-demo.xml";
    /**
     * 从classpath加载配置文件
     */
    private static ClassPathXmlApplicationContext context;

    /**
     * 加载配置文件
     */
    public final static void initContext() {
        initContext(SPRING_FILE);
    }

    /**
     * 加载Spring配置文件
     *
     * @param fileName 相对路径文件名
     */
    public final static void initContext(String fileName) {
        context = new ClassPathXmlApplicationContext(fileName);
        context.start();
        context.registerShutdownHook();
    }

    public final static void stop() {
        context.stop();
    }

    /**
     * 获取指定名称的Bean对象
     *
     * @param beanName
     * @return Bean对象
     */
    public final static <T> T getBean(String beanName) {
        if (null == context) {
            initContext();
        }
        return (T) context.getBean(beanName);
    }

}
