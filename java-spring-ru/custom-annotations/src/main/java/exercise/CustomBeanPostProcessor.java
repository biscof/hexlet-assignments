package exercise;

import exercise.calculator.CalculatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    private static final Map<Object, String> BEANS_WITH_LEVELS = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            String level = bean.getClass().getAnnotation(Inspect.class).level();
            BEANS_WITH_LEVELS.put(beanName, level);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (BEANS_WITH_LEVELS.containsKey(beanName)) {
            return Proxy.newProxyInstance(
                    CalculatorImpl.class.getClassLoader(),
                    CalculatorImpl.class.getInterfaces(),
                    (proxy, method, args) -> {
                        if (BEANS_WITH_LEVELS.get(beanName).equals("debug")) {
                            LOGGER.debug("Was called method: {}() with arguments: {}", method.getName(), args);
                        } else if (BEANS_WITH_LEVELS.get(beanName).equals("info")) {
                            LOGGER.info("Was called method: {}() with arguments: {}", method.getName(), args);
                        } else {
                            throw new UnsupportedOperationException(
                                    "Unsupported method: " + method.getName());
                        }
                        return method.invoke(bean, args);
                    }
                    );
        }
        return bean;
    }
}
// END
