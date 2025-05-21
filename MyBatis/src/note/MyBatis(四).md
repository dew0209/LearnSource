# Mapper配置

## select

查询数据库的元素

### id

官方定义：在命名空间中唯一的标识符，可以被用来引用这条语句。

就是在mapper文件中的方法名。

例如：

```java
<select id="selectById" resultType="mapperconfig.entity.User" parameterType="string">
   select * from user where id = #{id}
</select>
```

其实就是

```java
User selectById(@Param("id") String id);
```

[示例](../main/java/mapperconfig/mapper/UserMapper.java) 中的 方法名字 （selectById），[对应](../main/resources/mapperconfig/mapper/UserMapper.xml)selectById的id属性（selectById）。

[测试](../test/java/mapperconfig/MapperConfigTest.java)中的run01

### parameterType

传入参数的类型

[示例](../main/java/mapperconfig/mapper/UserMapper.java) 中的 selectByDTO和selectByDTOUseParamType ，[对应](../main/resources/mapperconfig/mapper/UserMapper.xml)中配置，一个我加了`parameterType="QueryUserDTO"`一个我没加，其实对于结果来说，并没有什么影响。

[测试](../test/java/mapperconfig/MapperConfigTest.java)中的run02

如果在interface中**加了**`@Param("xxx")`，那么在`#{xx}`的时候，如果传进来是实体类，那么需要`#{xx.xx}`，非实体类则直接`#{xx}`即可。

如果在interface中**不加**`@Param("xxx")`，如果传进来的是实体类，可以直接`#{xx}`其中的属性即可。非实体类直接`#{xx}`即可。

如果**不加**`@Param("xxx")`，那么MyBatis默认的参数名字为`[arg1, arg0, param1, param2]` map的时候好像是没有这个默认的参数名字的，map直接用key就行了（不加@Param的时候）。

### resultMap

对元素`resultMap` 的命名引用

注意：` resultType 和 resultMap 之间只能同时使用一个`。

### resultType

期望从这条语句中返回结果的类全限定名或别名。 注意，如果返回的是集合，那应该设置为集合包含的类型，而不是集合本身的类型。

[示例](../main/java/mapperconfig/mapper/UserMapper.java) 中的 方法`selectById`设置为`resultType="mapperconfig.entity.User"`。这是查询一个。

[示例](../main/java/mapperconfig/mapper/UserMapper.java) 中的 方法`selectByDTO`设置为`resultType="mapperconfig.entity.User"`。这是查询多个。

查询一个和查询多个其实都是一样的逻辑，放返回的实体对象，而非集合本身。而是集合里面包含的元素的类型即可。

注意：` resultType 和 resultMap 之间只能同时使用一个`。

### 总结

#### **自动映射**

- 前提：SQL列名和JavaBean的属性是一致的；

- 自动映射等级autoMappingBehavior设置为PARTIAL，需要谨慎使用FULL；

- 使用resultType；

- 如果列名和JavaBean不一致，但列名符合单词下划线分割，Java是驼峰命名法，

  则mapUnderscoreToCamelCase可设置为true；

#### 传递多个查询入参

- 使用map传递参数；可读性差，导致可维护性和可扩展性差，杜绝使用；

- 使用注解传递参数；直观明了，当参数较少一般小于5个的时候，建议使用；

- 使用Java Bean的方式传递参数；当参数大于5个的时候，建议使用；

  [测试](../test/java/mapperconfig/MapperConfigTest.java)中的run03

## resultMap

结果集映射

### constructor

用于在实例化类时，注入结果到构造方法中。简单来说，如果你不写的话，就默认使用无参构造。

```xml
<resultMap id="BaseResultMap" type="mapperconfig.entity.User">
   <constructor>
      <idArg column="id" javaType="string"/>
      <arg column="mail" javaType="string"/>
   </constructor>
</resultMap>
```

```java
public User(String id, String mail) {
    this.id = id;
    this.mail = mail;
}
```

[示例](../main/java/mapperconfig/mapper/UserMapper.java) 中的selectByIdUserResultMapArg

其中，idArg必须是第一个（官方说可以提升性能，我暂时不清楚。）。我一般都用来在主键上使用。

如果修改下位置，那么

```xml
<resultMap id="BaseResultMap2" type="mapperconfig.entity.User">
   <constructor>
      <arg column="mail" javaType="string"/>
      <arg column="id" javaType="string"/>
   </constructor>
</resultMap>
```

mail的值将会对应到bean的id上去。也就是说，**必须按照构造函数的顺序来一一书写**

如果想改顺序，可以参考以下代码：

```xml
<resultMap id="BaseResultMap3" type="mapperconfig.entity.User">
   <constructor>
      <arg column="mail" name="mail"/>
      <arg column="id" name="id"/>
   </constructor>
</resultMap>
```

```java
public User(@Param("id") String id, @Param("mail") String mail) {
    this.id = id;
    this.mail = mail;
}
```

注意：

- `name`，`@Param(xx)`和`String xx`三者必须一样（本地测出来），因此并不需要改名的功能。
- 没有在`constructor`中体现的属性，其规则和普通的 id 和 result 元素是一样的。

### id

sql中的字段和bean中属性做映射使用（一般用在主键上），官方说可以提升性能，我暂时不清楚。

```xml
<id column="name" property="name" />

column：sql中的字段名
property：bean中的属性名
typeHandler：类型转换器
javaType：bean属性类型
jdbcType：sql中的字段类型
```

### result

sql中的字段和bean中属性做映射使用（一般用在非主键上）

```xml
<result column="type" property="type"/>

column：sql中的字段名
property：bean中的属性名
typeHandler：类型转换器
javaType：bean属性类型
jdbcType：sql中的字段类型
```

并不需要把所有的映射关系全部写出来，没有写的将做自动映射。但是推荐写，因为这样bean和mapper文件解耦。