# MyBatis配置



## properties

### 入门

==设置配置项==

基本格式

```xml
<!--
    resource：从项目下的resources目录下开始搜索
    注意：不要写成/settings/db.properties，这样是获取不到配置的
-->
<properties resource="settings/db.properties"/>

或

<!-- url：统一资源定位符 用的少 -->
<properties url="file:///D:/workspace/workspaceideaj/LearnSource/MyBatis/src/main/resources/settings/db.properties"/>
```

<p>
    <img src="img/properties中resource理解.png" alt="properties中resource理解" style="display: block;
width:804px;height:400px;margin-left: 0; margin-right: auto;">
</p>

[示例](../main/resources/settings/mybatis-config.xml)

[测试](../test/java/settings/SettingsTest.java)

### 配置顺序

```txt
结论：通过方法参数传递的属性具有最高优先级，resource/url 属性中指定的配置文件次之，最低优先级的则是 properties 元素中指定的属性。
```

[示例](../main/resources/settings/mybatis-config2.xml)

[测试](../test/java/settings/SettingsTest.java)

## settings

会在笔记三介绍

## typeAliases

### 使用

可以给Java类起个别名

将UserMapper中的resultType改为User

<p>
    <img src="img/typeAliases不设置的错误演示.png" alt="typeAliases不设置的错误演示" style="display: block;
width:804px;height:400px;margin-left: 0; margin-right: auto;">
</p>

运行[测试](../test/java/settings/SettingsTest.java) 中run03报错

添加配置

```xml
<typeAliases>
    <package name="settings.entity"/>
</typeAliases>

<!-- 
	package以包为单位 
	在实体类上，可以使用@Alias("UserEx") 指定具体的别名。
	如果@Alias没有指明具体的值，则以该类的首字母小写作为类型，User就是user
-->

或

<typeAliases>
    <typeAlias type="settings.entity.User"/>
</typeAliases>

<!-- typeAlias以类为单位 -->
```

运行[测试](../test/java/settings/SettingsTest.java) 中run03通过

注意：别名是==**不区分大小写的**==，例如别名@Alias("UserEx")使用userex也是可以的

### 内建的类型别名

| 别名                      | 映射的类型   |
| ------------------------- | ------------ |
| _byte                     | byte         |
| _char (since 3.5.10)      | char         |
| _character (since 3.5.10) | char         |
| _long                     | long         |
| _short                    | short        |
| _int                      | int          |
| _integer                  | int          |
| _double                   | double       |
| _float                    | float        |
| _boolean                  | boolean      |
| string                    | String       |
| byte                      | Byte         |
| char (since 3.5.10)       | Character    |
| character (since 3.5.10)  | Character    |
| long                      | Long         |
| short                     | Short        |
| int                       | Integer      |
| integer                   | Integer      |
| double                    | Double       |
| float                     | Float        |
| boolean                   | Boolean      |
| date                      | Date         |
| decimal                   | BigDecimal   |
| bigdecimal                | BigDecimal   |
| biginteger                | BigInteger   |
| object                    | Object       |
| date[]                    | Date[]       |
| decimal[]                 | BigDecimal[] |
| bigdecimal[]              | BigDecimal[] |
| biginteger[]              | BigInteger[] |
| object[]                  | Object[]     |
| map                       | Map          |
| hashmap                   | HashMap      |
| list                      | List         |
| arraylist                 | ArrayList    |
| collection                | Collection   |
| iterator                  | Iterator     |

## TypeHandle

```txt
通过类型处理器的泛型，MyBatis 可以得知该类型处理器处理的 Java 类型，不过这种行为可以通过两种方法改变：
	1.在类型处理器的配置元素（typeHandler 元素）上增加一个 javaType 属性（比如：javaType="String"）；
	2.在类型处理器的类上增加一个 @MappedTypes 注解指定与其关联的 Java 类型列表。 如果在 javaType 属性中也同时指定，则注解上的配置将被忽略。
可以通过两种方式来指定关联的 JDBC 类型：
	1.在类型处理器的配置元素上增加一个 jdbcType 属性（比如：jdbcType="VARCHAR"）；
	2.在类型处理器的类上增加一个 @MappedJdbcTypes 注解指定与其关联的 JDBC 类型列表。 如果在 jdbcType 属性中也同时指定，则注解上的配置将被忽略。	
```

[示例](../main/resources/settings/mybatis-config3.xml)

[测试](../test/java/settings/SettingsTest.java)

当在 `ResultMap` 中决定使用哪种类型处理器时，此时 Java 类型是已知的（从结果类型中获得），但是 JDBC 类型是未知的。 因此 Mybatis 使用 `javaType=[Java 类型], jdbcType=null` 的组合来选择一个类型处理器。 这意味着使用 `@MappedJdbcTypes` 注解可以*限制*类型处理器的作用范围，并且可以确保，除非显式地设置，否则类型处理器在 `ResultMap` 中将不会生效。 如果希望能在 `ResultMap` 中隐式地使用类型处理器，那么设置 `@MappedJdbcTypes` 注解的 `includeNullJdbcType=true` 即可。 然而从 Mybatis 3.4.0 开始，如果某个 Java 类型**只有一个**注册的类型处理器，即使没有设置 `includeNullJdbcType=true`，那么这个类型处理器也会是 `ResultMap` 使用 Java 类型时的默认处理器。

注意：

```xml
1.多个JavaType JdbcType类型处理器，看配置，谁最后配置，取谁的。

2.JavaType JdbcType设置优先级
<typeHandler handler="settings.typehandle.TypeHandleForMailSplit2" jdbcType="VARCHAR" javaType="Email"/>

extends BaseTypeHandler<Email>
  

@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes({String.class})
  
三者优先级如下
```

<p>
    <img src="img/typeHandle指定类型的优先级.png" alt="typeHandle指定类型的优先级" style="display: block;
width:804px;height:400px;margin-left: 0; margin-right: auto;">
</p>