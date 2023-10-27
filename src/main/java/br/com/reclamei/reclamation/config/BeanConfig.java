package br.com.reclamei.reclamation.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.util.List;

@Configuration
public class BeanConfig {

    private static final List<String> ENTITIES_FILTER = List.of("Domain", "Dto", "Request", "Response", "Builder", "Exception", "Utils",
        "Producer", "Body", "Message", "Entity", "RepositoryImpl", "Error", "Enum", "Type");

    @Bean
    BeanFactoryPostProcessor beanFactoryPostProcessor(ApplicationContext beanRegistry) {
        if (beanRegistry instanceof AnnotationConfigServletWebServerApplicationContext) {
            return beanFactory -> genericApplicationContext(
                (BeanDefinitionRegistry) ((AnnotationConfigServletWebServerApplicationContext) beanRegistry).getBeanFactory());
        } else if (beanRegistry instanceof AnnotationConfigApplicationContext) {
            return beanFactory -> genericApplicationContext(
                (BeanDefinitionRegistry) ((AnnotationConfigApplicationContext) beanRegistry).getBeanFactory());
        }
        return null;
    }

    void genericApplicationContext(BeanDefinitionRegistry beanRegistry) {
        ClassPathBeanDefinitionScanner beanDefinitionScanner = new ClassPathBeanDefinitionScanner(beanRegistry);
        beanDefinitionScanner.addIncludeFilter(createTypeFilter());
        beanDefinitionScanner.scan("br.com.reclamei.reclamation.dataprovider", "br.com.reclamei.reclamation.core");
    }

    static TypeFilter createTypeFilter() {
        return (MetadataReader mr, MetadataReaderFactory mrf) ->
            ENTITIES_FILTER.stream()
                .noneMatch(e -> mr.getClassMetadata().getClassName().endsWith(e));
    }

}
