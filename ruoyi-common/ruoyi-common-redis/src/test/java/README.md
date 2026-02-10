# Redis GEO 工具类测试说明

## 概述

本项目提供了两个测试类用于验证GeoUtils工具类的功能：

1. `GeoUtilsSpringTest.java` - Spring Boot集成测试（需要Redis服务器）
2. `GeoUtilsJUnitTest.java` - 纯JUnit测试（需要Redis服务器）

## 前置条件

### Redis服务器
测试需要连接到Redis服务器。请确保：
- Redis服务器正在运行（默认端口6379）
- 可以通过localhost:6379访问
- 如果使用密码认证，请在测试配置中相应修改

### 环境配置
测试使用以下默认配置：
```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.database=0
redisson.singleServerConfig.clientName=GeoUtilsTest
```

## 运行测试

### 方法1：使用Maven命令
```bash
# 运行所有测试
mvn test -pl ruoyi-common/ruoyi-common-redis -DskipTests=false

# 运行特定测试类
mvn test -pl ruoyi-common/ruoyi-common-redis -Dtest=GeoUtilsJUnitTest -DskipTests=false

# 运行特定测试方法
mvn test -pl ruoyi-common/ruoyi-common-redis -Dtest=GeoUtilsJUnitTest#testAddSingleLocation -DskipTests=false
```

### 方法2：在IDE中运行
1. 在IntelliJ IDEA或Eclipse中打开测试类
2. 右键点击类名或方法名
3. 选择"Run"或"Debug"

## 测试功能覆盖

测试类验证了以下GeoUtils功能：

### 基础操作
- ✅ 添加单个地理位置
- ✅ 批量添加地理位置
- ✅ 获取地理位置信息
- ✅ 删除地理位置
- ✅ 清空所有数据

### 距离计算
- ✅ 计算两个地点之间的距离
- ✅ 使用Haversine公式直接计算距离

### 搜索功能
- ✅ 搜索附近地点
- ✅ 搜索附近地点（带距离信息）
- ✅ 限制返回数量的搜索
- ✅ 按距离排序的附近地点
- ✅ 矩形范围搜索

### 查询功能
- ✅ 检查成员是否存在
- ✅ 获取所有成员
- ✅ 获取成员数量
- ✅ 获取Hash值

## 常见问题

### 1. 测试无法连接到Redis
**问题**：测试运行时报连接错误
**解决方案**：
- 确保Redis服务器正在运行
- 检查Redis配置是否正确
- 验证网络连接

### 2. 测试被跳过
**问题**：显示"Tests run: 0"
**解决方案**：
- 检查是否添加了`-DskipTests=false`参数
- 确认测试类命名符合约定（以Test结尾）
- 验证JUnit依赖是否正确

### 3. 编译错误
**问题**：找不到JUnit相关类
**解决方案**：
- 确保pom.xml中包含JUnit依赖
- 执行`mvn clean compile test-compile`
- 检查IDE的Maven配置

## 测试配置说明

### Spring Boot测试配置
```java
@SpringBootTest
@TestPropertySource(properties = {
    "spring.data.redis.host=localhost",
    "spring.data.redis.port=6379",
    "spring.data.redis.database=0",
    "redisson.singleServerConfig.clientName=GeoUtilsTest"
})
```

### 测试生命周期
- `@Before`：每个测试方法执行前清空测试数据
- `@After`：每个测试方法执行后清空测试数据
- 确保测试之间相互独立

## 示例输出

成功运行的测试应该显示类似以下输出：
```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running org.dromara.common.redis.example.GeoUtilsJUnitTest
Tests run: 15, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.345 sec

Results :

Tests run: 15, Failures: 0, Errors: 0, Skipped: 0
```

## 注意事项

1. **数据清理**：测试会自动清理测试数据，但建议在生产环境中谨慎使用
2. **性能考虑**：大量测试数据可能影响Redis性能
3. **并发测试**：避免多个测试同时使用相同的GEO键
4. **网络延迟**：远程Redis服务器可能影响测试执行时间

## 扩展测试

可以根据需要添加更多测试场景：
- 边界条件测试
- 异常情况处理
- 性能基准测试
- 并发访问测试