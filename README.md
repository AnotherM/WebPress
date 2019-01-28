# WebPress
## 使用Spring Boot + Thymeleaf + Spring Data Jpa + Bootstrap编写的一个简单博客
### 命名规范

***以下适用于除index以外所有内容，命名方法为：小驼峰命名法，并且object不加s***

* 视图层
    * 跳转到控制层请求为/objectDo，如跳转到用户添加控制层为/userAdd
    * Thymeleaf模板内的Spring EL表达式需要与实体层对应，如${posts}对应数据库posts表，${posts.pid}或者*{pid}对应数据库表的pid
* 控制层
    * 跳转到视图层方法为/doObject，如跳转到用户添加视图层为/addUser
    * 统一顶级请求为objectList，如@RequestMapping("/userList")，返回到视图层
* 实体层
    * 与数据库表一一对应
* 持久层
    * extend继承自JpaRepository，根据需求编写方法
* 业务层
    * 与持久层编写的方法名一致
    
    