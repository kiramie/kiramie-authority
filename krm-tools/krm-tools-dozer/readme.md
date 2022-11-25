## 6. pd-tools-dozer

pd-tools-dozer模块定位为对象转换，其本质就是一个Spring Boot starter，其他模块可以直接导入此模块就可以直接完成对象转换了。

### 6.1 dozer介绍

Dozer是Java Bean到Java Bean映射器，它以递归方式将数据从一个对象复制到另一个对象。 dozer是用来对两个对象之间属性转换的工具，有了这个工具之后，我们将一个对象的所有属性值转给另一个对象时，就不需要再去写重复的调用set和get方法了。dozer其实是对我们熟知的beanutils的封装。

dozer的maven坐标：

~~~xml
<dependency>
    <groupId>com.github.dozermapper</groupId>
    <artifactId>dozer-core</artifactId>
    <version>6.5.0</version>
</dependency>
~~~

为了简化使用方式，dozer还提供了starter，其maven坐标为：

~~~xml
<dependency>
    <groupId>com.github.dozermapper</groupId>
    <artifactId>dozer-spring-boot-starter</artifactId>
    <version>6.5.0</version>
</dependency>
~~~

### 6.2 dozer入门案例

第一步：创建maven工程dozer_demo并配置pom.xml文件

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.2.RELEASE</version>
        <relativePath/>
    </parent>
    <groupId>cn.itcast</groupId>
    <artifactId>dozer_demo</artifactId>
    <version>1.0-SNAPSHOT</version>
    <dependencies>
        <dependency>
            <groupId>com.github.dozermapper</groupId>
            <artifactId>dozer-spring-boot-starter</artifactId>
            <version>6.5.0</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
    </dependencies>
</project>
~~~

第二步：创建UserDTO和UserEntity

~~~java
package com.itheima.dto;
import lombok.Data;
@Data
public class UserDTO {
    private String userId;
    private String userName;
    private int userAge;
    private String address;
    private String birthday;
}
~~~

~~~java
package com.itheima.entity;
import lombok.Data;
import java.util.Date;
@Data
public class UserEntity {
    private String id;
    private String name;
    private int age;
    private String address;
    private Date birthday;
}
~~~

第三步：在resources/dozer/目录下创建dozer的全局配置文件global.dozer.xml

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<mappings xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns="http://dozermapper.github.io/schema/bean-mapping"
          xsi:schemaLocation="http://dozermapper.github.io/schema/bean-mapping 
                              http://dozermapper.github.io/schema/bean-mapping.xsd">
    <!--
    全局配置:
    <date-format>表示日期格式
     -->
    <configuration>
        <date-format>yyyy-MM-dd</date-format>
    </configuration>
</mappings>
~~~

注：全局配置文件名称可以任意

第四步：在resources/dozer/目录下创建dozer的映射文件biz.dozer.xml

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<mappings xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns="http://dozermapper.github.io/schema/bean-mapping"
          xsi:schemaLocation="http://dozermapper.github.io/schema/bean-mapping
                             http://dozermapper.github.io/schema/bean-mapping.xsd">
    <!--描述两个类中属性的对应关系，对于两个类中同名的属性可以不映射-->
    <mapping date-format="yyyy-MM-dd">
        <class-a>com.itheima.entity.UserEntity</class-a>
        <class-b>com.itheima.dto.UserDTO</class-b>
        <field>
            <a>id</a>
            <b>userId</b>
        </field>
        <field>
            <a>name</a>
            <b>userName</b>
        </field>
        <field>
            <a>age</a>
            <b>userAge</b>
        </field>
    </mapping>
	<!--
	可以使用map-id指定映射的标识，在程序中通过此标识来确定使用当前这个映射关系
	-->
    <mapping date-format="yyyy-MM-dd" map-id="user">
        <class-a>com.itheima.entity.UserEntity</class-a>
        <class-b>com.itheima.dto.UserDTO</class-b>
        <field>
            <a>id</a>
            <b>userId</b>
        </field>
        <field>
            <a>name</a>
            <b>userName</b>
        </field>
        <field>
            <a>age</a>
            <b>userAge</b>
        </field>
    </mapping>
</mappings>
~~~

注：映射文件名称可以任意

第五步：编写application.yml文件

~~~yaml
dozer:
  mappingFiles:
    - classpath:dozer/global.dozer.xml
    - classpath:dozer/biz.dozer.xml
~~~

第六步：编写启动类DozerApp

~~~java
package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DozerApp {
    public static void main(String[] args) {
        SpringApplication.run(DozerApp.class,args);
    }
}
~~~

第七步：编写单元测试DozerTest

~~~java
package cn.itcast.test;

import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.metadata.MappingMetadata;
import com.itheima.DozerApp;
import com.itheima.dto.UserDTO;
import com.itheima.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DozerApp.class)
public class DozerTest {
    @Autowired
    private Mapper mapper;
    @Test
    public void testDozer1(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId("100");
        userDTO.setUserName("itcast");
        userDTO.setUserAge(20);
        userDTO.setAddress("bj");
        userDTO.setBirthday("2010-11-20");

        UserEntity user = mapper.map(userDTO, UserEntity.class);
        System.out.println(user);
    }

    @Test
    public void testDozer2(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId("100");
        userDTO.setUserName("itcast");
        userDTO.setUserAge(20);
        userDTO.setAddress("bj");
        userDTO.setBirthday("2010-11-20");

        UserEntity user = new UserEntity();
        user.setId("200");
        System.out.println(user);
        mapper.map(userDTO,user);
        System.out.println(user);
    }

    @Test
    public void testDozer3(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId("100");
        userDTO.setUserName("itcast");
        userDTO.setUserAge(20);
        userDTO.setAddress("bj");

        UserEntity user = new UserEntity();
        System.out.println(user);
        mapper.map(userDTO,user,"user");
        System.out.println(user);
    }
}