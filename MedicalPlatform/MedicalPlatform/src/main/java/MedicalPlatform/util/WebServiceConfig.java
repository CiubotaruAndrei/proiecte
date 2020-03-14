package MedicalPlatform.util;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/soap/*");
    }

    @Bean(name = "history")
    public DefaultWsdl11Definition historyService(XsdSchema historySchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("HistoryPort");
        wsdl11Definition.setLocationUri("/soap");
        wsdl11Definition.setTargetNamespace("http://springsoap");
        wsdl11Definition.setSchema(historySchema);
        return wsdl11Definition;
    }

    @Bean(name = "meds")
    public DefaultWsdl11Definition dailyMedsService(XsdSchema dailyMedsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("MedsPort");
        wsdl11Definition.setLocationUri("/soap");
        wsdl11Definition.setTargetNamespace("http://springsoap");
        wsdl11Definition.setSchema(dailyMedsSchema);
        return wsdl11Definition;
    }

    @Bean(name = "recommend")
    public DefaultWsdl11Definition addRecommendService(XsdSchema recommendSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("RecommendPort");
        wsdl11Definition.setLocationUri("/soap");
        wsdl11Definition.setTargetNamespace("http://springsoap");
        wsdl11Definition.setSchema(recommendSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema historySchema() {
        return new SimpleXsdSchema(new ClassPathResource("/soap/history.xsd"));
    }

    @Bean
    public XsdSchema dailyMedsSchema() {return new SimpleXsdSchema(new ClassPathResource("/soap/dailymeds.xsd"));
    }

    @Bean
    public XsdSchema recommendSchema() {return new SimpleXsdSchema(new ClassPathResource("/soap/recommendation.xsd"));
    }
}
