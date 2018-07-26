# wf-micro-service
使用activiti的工作流微服务
- activiti版本较旧，但仍然可以作为参考
- 參考博客：http://www.kafeitu.me/activiti/2015/12/27/integrate-new-activiti-modeler-and-rest.html
- src/main/resources/stencilset.json: bpmn标准里面各种组件的json定义，editor以import使用。
- src/main/resources/static/editor-app：目录中包含设计器里面所有的资源：angular.js、oryx.js以及配套的插件及css
- src/main/resources/static/modeler.html：设计器的主页面，用来引入各种web资源
- 修改editor-app/app-cfg.js文件的contextRoot属性为自己的应用名称，例如/kft-activiti-demo/service

## activiti6
尝试使用springboot整合activiti6
- 参考：http://spring.io/blog/2015/03/08/getting-started-with-activiti-and-spring-boot
- 使用activiti-springboot-starter:6.0.0 + springboot1.5.7(2.0版本的springboot会报错) 

## integration
- 尝试spring-integration
- 尝试使用基于redis的消息队列
- 尝试Groovy的模板引擎