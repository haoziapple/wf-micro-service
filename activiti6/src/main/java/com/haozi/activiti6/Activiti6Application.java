package com.haozi.activiti6;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.Map;

@EnableSwagger2
@SpringBootApplication
public class Activiti6Application {

    public static void main(String[] args) {
        SpringApplication.run(Activiti6Application.class, args);
    }


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.activiti.rest.service.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot中整合activiti-rest构建RESTful APIs")
                //描述
                .description("更多swagger2相关文章请访问官网：http://swagger.io/")
                //创建人
                .contact(new Contact("Wang Hao", "", "haozixiaowang@163.com"))
                //版本
                .version("1.0")
                .build();
    }

    /**
     * 服务启动后，开始一个流程
     *
     * @param repositoryService
     * @param runtimeService
     * @param taskService
     * @return
     */
    @Bean
    CommandLineRunner init(final RepositoryService repositoryService,
                           final RuntimeService runtimeService,
                           final TaskService taskService) {
        return strings -> {
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("applicantName", "John Doe");
            variables.put("email", "john.doe@activiti.com");
            variables.put("phoneNumber", "123456789");
            runtimeService.startProcessInstanceByKey("hireProcess", variables);
        };
    }

    /**
     * 初始化用户
     *
     * @param identityService
     * @return
     */
    @Bean
    InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {
        return () -> {
            Group group = identityService.newGroup("user");
            group.setName("users");
            group.setType("security-role");
            identityService.saveGroup(group);

            User admin = identityService.newUser("admin");
            admin.setPassword("admin");
            identityService.saveUser(admin);
        };
    }
}
