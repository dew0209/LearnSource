# 关联查询

## 一对一

**association**：一对一关系

关联方式

嵌套结果:使用嵌套结果映射来处理重复的联合结果的子集

使用嵌套结果，自动映射会失效，需要手动指定==autoMapping="true"==或者手动指定`result`关系

```xml
<resultMap id="BaseResultMap" type="association.entity.User" autoMapping="true">
    <association property="type" columnPrefix="type_" javaType="type" >
        <id property="id" column="id" />
        <result property="descM" column="desc_m"/>
    </association>
</resultMap>

<select id="selectAllOper1" resultMap="BaseResultMap">
        select u.*,t.id as type_id,t.descM as type_desc_m
            from user u left join type t on u.type = t.id
</select>


association标签 嵌套结果方式 常用属性：
	property ：对应实体类中的属性名，必填项。
	javaType ： 属性对应的 Java 类型 。
	resultMap ： 可以直接使用现有的 resultMap ，而不需要在这里配置映射关系。
	columnPrefix ：查询列的前缀，配置前缀后，在子标签配置 result 的 column 时可以省略前缀。防止产生重复的列名
	autoMapping：如果设置这个属性，MyBatis 将会为本结果映射开启或者关闭自动映射。默认值：未设置（unset）。
```

[示例](../main/java/association/mapper/UserMapper.java) 中的selectAllOper1

[测试](../test/java/association/AssociationTest.java)中的run01

嵌套查询:通过执行另外一个 SQL 映射语句来返回预期的复杂类型

```xml
<resultMap id="BaseResultMap2" type="association.entity.User" autoMapping="true">
    <association fetchType="lazy" property="type" column="type" select="association.mapper.TypeMapper.getByPrimaryKey"/>
</resultMap>

<select id="selectAllOper2" resultMap="BaseResultMap2">
    select * from user
</select>

association标签 嵌套查询方式 常用属性：
	select ：另 一个映射查询的 id, MyBatis 会额外执行这个查询获取嵌套对象的结果 。
	column ：列名（或别名），将主查询中列的结果作为嵌套查询的 参数。
	fetchType ：数据加载方式，可选值为 lazy 和 eager，分别为延迟加载和积极加载 ，这个配置会覆盖全局的 lazyLoadingEnabled 配置；
```

[示例](../main/java/association/mapper/UserMapper.java) 中的selectAllOper2

[测试](../test/java/association/AssociationTest.java)中的run02

为什么使用延迟加载：N + 1问题

```xml
概括地讲,N+1 查询问题可以是这样引起的:
	你执行了一个单独的 SQL 语句来获取结果列表(就是“+1”)。
	对返回的每条记录,你执行了一个查询语句来为每个加载细节(就是“N”)。
```

延迟加载的两种方式：

```txt
 1.全局配置
 <settings>
        <setting name="aggressiveLazyLoading" value="false"/>
        <setting name="lazyLoadingEnabled" value="true"/>
</settings>

2.mapper配置文件association的属性配置
fetchType="lazy"
```

```txt
Tips:
1. resultMap可以通过使用extends实现继承关系，简化很多配置工作量；
2. 关联的表查询的类添加前缀是编程的好习惯；
3. 通过添加完整的命名空间，可以引用其他xml文件的resultMap；
```

