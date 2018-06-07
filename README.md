# wf-micro-service
使用activiti的工作流微服务

- src/main/resources/stencilset.json: bpmn标准里面各种组件的json定义，editor以import使用。
- src/main/resources/static/editor-app：目录中包含设计器里面所有的资源：angular.js、oryx.js以及配套的插件及css
- src/main/resources/static/modeler.html：设计器的主页面，用来引入各种web资源
- 修改editor-app/app-cfg.js文件的contextRoot属性为自己的应用名称，例如/kft-activiti-demo/service