# Ruoyi-Merchant-Nearby 技术文档

## 1. 项目概述

### 1.1 项目简介
`ruoyi-merchant-nearby` 是若依框架下的商户附近服务模块，采用 COLA 架构模式设计，实现了基于地理位置的商户管理、商品管理、订单处理、营销活动等功能。

### 1.2 核心功能
- 📍 基于地理位置的商户发现和搜索
- 🛒 商品分类、品牌、规格管理
- 📦 运费模板和配送配置
- 🎯 营销活动和优惠券管理
- 📋 订单全流程管理
- 🏪 商户入驻和认证流程

### 1.3 技术栈
- **Java 版本**: JDK 17
- **架构模式**: COLA (Clean Object-oriented and Layered Architecture)
- **ORM 框架**: MyBatis-Plus
- **对象映射**: MapStruct 1.6.3
- **数据库**: MySQL
- **缓存**: Redis
- **地理定位**: 高德地图 API

## 2. 架构设计

### 2.1 整体架构图
```
┌─────────────────────────────────────────────────────────────┐
│                        Adapter Layer                         │
│  (Web Controllers - 对外暴露的 REST API 接口)               │
├─────────────────────────────────────────────────────────────┤
│                          App Layer                           │
│  (Application Services - 应用服务层，编排业务逻辑)          │
├─────────────────────────────────────────────────────────────┤
│                        Domain Layer                          │
│  (Domain Models & Gateways - 领域模型和网关接口)            │
├─────────────────────────────────────────────────────────────┤
│                   Infrastructure Layer                      │
│  (Gateways Implementation & Persistence - 网关实现和持久化) │
└─────────────────────────────────────────────────────────────┘
```

### 2.2 模块划分

#### 2.2.1 ruoyi-merchant-nearby-client (客户端层)
包含对外提供的 DTO 和命令对象：
- **DTO**: 数据传输对象
- **Command**: 命令对象，用于接收外部请求
- **ClientObject**: 客户端对象，用于响应外部请求

#### 2.2.2 ruoyi-merchant-nearby-adapter (适配器层)
负责对外提供 RESTful API 接口：
- 控制器实现
- 请求参数校验
- 响应格式统一处理

#### 2.2.3 ruoyi-merchant-nearby-app (应用层)
核心业务逻辑编排层：
- Service 接口定义和实现
- Executor 执行器模式
- 业务流程协调

#### 2.2.4 ruoyi-merchant-nearby-domain (领域层)
核心领域模型：
- 领域实体 (Entity)
- 领域服务 (Domain Service)
- 网关接口 (Gateway Interface)
- 领域事件

#### 2.2.5 ruoyi-merchant-nearby-infrastructure (基础设施层)
技术实现细节：
- 网关具体实现
- 数据访问对象 (DAO/Repository)
- 第三方服务集成
- 对象转换器 (Converter)

#### 2.2.6 ruoyi-merchant-nearby-starter (启动器)
自动配置和启动相关组件。

## 3. 核心领域模型

### 3.1 商户模型 (Merchant)
```java
public class Merchant extends BaseEntity {
    private Long merchantId;           // 商户ID
    private String merchantCode;       // 商户编码
    private String merchantName;       // 商户名称
    private MerchantType merchantType; // 商户类型
    private MerchantStatus status;     // 商户状态
    private String preciseLocation;    // 经纬度坐标
    private String address;            // 详细地址
    private Boolean certified;         // 认证状态
    // ... 其他属性
}
```

**商户状态枚举**:
- `ACTIVE`: 活跃状态
- `DISABLED`: 禁用状态

**商户类型枚举**:
- `INDIVIDUAL`: 个体商户
- `ENTERPRISE`: 企业商户

### 3.2 商品相关模型

#### 3.2.1 品牌 (Brand)
```java
public class Brand extends TenantEntity {
    private Integer id;        // 品牌ID
    private String name;       // 品牌名称
    private String image;      // 品牌图片
    private String letter;     // 首字母
    private Integer seq;       // 排序
    private Long merchantId;   // 商户ID
}
```

#### 3.2.2 商品分类 (Category)
```java
public class Category {
    private Integer id;        // 分类ID
    private String name;       // 分类名称
    private Integer goodsNum;  // 商品数量
    private String isShow;     // 是否显示
    private Integer seq;       // 排序
    private Integer parentId;  // 上级ID
    private Long merchantId;   // 商户ID
}
```

#### 3.2.3 SPU (Standard Product Unit)
```java
public class Spu extends TenantEntity {
    private Long id;           // SPU ID
    private String name;       // 商品名称
    private String caption;    // 副标题
    private Long brandId;      // 品牌ID
    private Long categoryId;   // 分类ID
    private String images;     // 图片URL列表
    private String introduction;// 商品介绍
    private String specItems;  // 规格列表
    private String paraItems;  // 参数列表
    private Integer saleable;  // 是否上架
    private Integer isValid;   // 是否有效
}
```

### 3.3 订单模型 (Order)
```java
public class Order extends BaseEntity {
    private Long orderId;              // 订单ID
    private String orderNo;            // 订单编号
    private Long userId;               // 用户ID
    private Long merchantId;           // 商户ID
    private OrderStatus status;        // 订单状态
    private BigDecimal goodsAmount;    // 商品总金额
    private BigDecimal freightAmount;  // 运费金额
    private BigDecimal discountAmount; // 优惠金额
    private BigDecimal payableAmount;  // 应付金额
    private String paymentMethod;      // 支付方式
    private LocalDateTime paymentTime; // 支付时间
    private DeliveryMethod deliveryMethod; // 配送方式
    private List<OrderItem> orderItems;    // 订单项列表
}
```

### 3.4 营销模型

#### 3.4.1 优惠券 (Coupon)
```java
public class Coupon {
    private Long couponId;             // 优惠券ID
    private String couponName;         // 优惠券名称
    private CouponType type;           // 优惠券类型
    private BigDecimal amount;         // 优惠金额
    private BigDecimal conditionAmount;// 使用条件金额
    private Integer totalCount;        // 总数量
    private Integer issuedCount;       // 已发放数量
    private LocalDateTime startTime;   // 开始时间
    private LocalDateTime endTime;     // 结束时间
    private CouponStatus status;       // 状态
}
```

#### 3.4.2 营销活动 (Marketing)
```java
public class Marketing {
    private Long marketingId;          // 营销活动ID
    private String marketingName;      // 活动名称
    private MarketingType type;        // 活动类型
    private BigDecimal discountRate;   // 折扣率
    private BigDecimal fixedAmount;    // 固定金额
    private LocalDateTime startTime;   // 开始时间
    private LocalDateTime endTime;     // 结束时间
    private MarketingStatus status;    // 状态
}
```

## 4. 核心业务流程

### 4.1 商户入驻流程
```
1. 商户提交入驻申请
2. 系统生成商户编码
3. 商户完善基本信息
4. 提交认证材料
5. 平台审核认证
6. 审核通过后激活商户
7. 商户可开始经营
```

### 4.2 商品发布流程
```
1. 创建商品模板
2. 配置商品规格参数
3. 创建SPU信息
4. 创建SKU库存
5. 设置商品价格
6. 商品上架销售
```

### 4.3 订单处理流程
```
1. 用户下单
2. 系统检查库存
3. 计算运费和优惠
4. 生成订单
5. 用户支付
6. 商户接单备货
7. 发货配送
8. 用户确认收货
9. 订单完成
```

### 4.4 附近商户搜索流程
```
1. 获取用户当前位置坐标
2. 在Redis中搜索附近商户
3. 根据距离排序
4. 返回商户列表
5. 支持按分类筛选
```

## 5. 关键技术实现

### 5.1 地理位置搜索优化

#### 5.1.1 Redis GEO 实现
```java
@Service
public class MerchantGeoCacheService {
    
    /**
     * 搜索附近商户
     * @param longitude 经度
     * @param latitude 纬度  
     * @param radius 搜索半径(公里)
     * @return 商户ID列表
     */
    public List<Long> searchNearbyMerchants(Double longitude, Double latitude, Double radius) {
        // 使用Redis GEO命令搜索附近商户
        return redisTemplate.opsForGeo()
            .radius(MERCHANT_GEO_KEY, new Point(longitude, latitude), 
                   radius, RedisGeoCommands.DistanceUnit.KILOMETERS)
            .getContent().stream()
            .map(GeoResult::getContent)
            .map(GeoLocation::getName)
            .map(name -> Long.valueOf(name.toString()))
            .collect(Collectors.toList());
    }
}
```

#### 5.1.2 启动预热机制
```java
@Component
public class MerchantGeoWarmupRunner implements ApplicationRunner {
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 系统启动时预热地理位置缓存
        List<Merchant> merchants = merchantGateway.queryAllValidMerchants();
        merchants.forEach(merchant -> {
            String[] coordinates = merchant.getPreciseLocation().split(",");
            geoService.addMerchantLocation(merchant.getMerchantId(), 
                                         Double.parseDouble(coordinates[0]), 
                                         Double.parseDouble(coordinates[1]));
        });
    }
}
```

### 5.2 价格计算引擎

#### 5.2.1 价格计算流程
```java
@Service
public class PricingCalculateService {
    
    public CalculationResult calculatePrice(PriceCalculationCmd cmd) {
        // 1. 计算商品基础价格
        BigDecimal basePrice = calculateBasePrice(cmd.getProducts());
        
        // 2. 应用优惠券优惠
        BigDecimal couponDiscount = applyCoupons(basePrice, cmd.getCoupons());
        
        // 3. 应用营销活动优惠
        BigDecimal marketingDiscount = applyMarketings(basePrice, cmd.getMarketings());
        
        // 4. 计算积分抵扣
        BigDecimal integralDiscount = calculateIntegralDiscount(cmd.getIntegralDiscountAmount());
        
        // 5. 计算运费
        BigDecimal shippingFee = calculateShippingFee(cmd);
        
        // 6. 综合计算最终价格
        BigDecimal finalPrice = basePrice
            .subtract(couponDiscount)
            .subtract(marketingDiscount)
            .subtract(integralDiscount)
            .add(shippingFee);
            
        return CalculationResult.builder()
            .basePrice(basePrice)
            .couponDiscount(couponDiscount)
            .marketingDiscount(marketingDiscount)
            .integralDiscount(integralDiscount)
            .shippingFee(shippingFee)
            .finalPrice(finalPrice)
            .build();
    }
}
```

### 5.3 配送费用计算

#### 5.3.1 快递配送
```java
@Service
public class BusinessExpressOrderService implements IExpressOrderService {
    
    public BigDecimal queryDeliveryFee(List<Product> products, Merchant merchant, Address customAddress) {
        // 1. 获取运费模板配置
        ExpressTemplate template = expressTemplateService.getDefaultTemplate(merchant.getMerchantId());
        
        // 2. 计算商品总重量/体积
        BigDecimal totalWeight = calculateTotalWeight(products);
        
        // 3. 根据配送区域计算运费
        return calculateFreightByArea(template, customAddress, totalWeight);
    }
}
```

#### 5.3.2 同城配送
```java
@Service
public class CityDeliveryExpressOrderService implements IExpressOrderService {
    
    public BigDecimal queryDeliveryFee(List<Product> products, Merchant merchant, Address customAddress) {
        // 调用第三方同城配送API计算费用
        BsamecityOrderReq params = buildDeliveryParams(products, merchant, customAddress);
        return cityDeliveryOrder.queryPrice(params, config);
    }
}
```

## 6. 数据库设计

### 6.1 核心表结构

#### 6.1.1 商户表 (tb_merchant)
```sql
CREATE TABLE tb_merchant (
    merchant_id BIGINT PRIMARY KEY COMMENT '商户ID',
    tenant_id VARCHAR(32) COMMENT '租户ID',
    merchant_code VARCHAR(64) UNIQUE COMMENT '商户编码',
    merchant_name VARCHAR(255) NOT NULL COMMENT '商户名称',
    merchant_type VARCHAR(32) COMMENT '商户类型',
    status VARCHAR(32) DEFAULT 'ACTIVE' COMMENT '商户状态',
    legal_person VARCHAR(100) COMMENT '法人姓名',
    contact_phone VARCHAR(20) COMMENT '联系电话',
    precise_location VARCHAR(100) COMMENT '经纬度坐标',
    address TEXT COMMENT '商户地址',
    certified TINYINT(1) DEFAULT 0 COMMENT '认证状态',
    certified_time DATETIME COMMENT '认证时间',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) COMMENT '商户表';
```

#### 6.1.2 商品表 (tb_spu)
```sql
CREATE TABLE tb_spu (
    id BIGINT PRIMARY KEY COMMENT 'SPU ID',
    tenant_id VARCHAR(32) COMMENT '租户ID',
    name VARCHAR(255) NOT NULL COMMENT '商品名称',
    caption VARCHAR(255) COMMENT '副标题',
    brand_id BIGINT COMMENT '品牌ID',
    category_id BIGINT COMMENT '分类ID',
    images TEXT COMMENT '图片URL列表',
    introduction LONGTEXT COMMENT '商品介绍',
    spec_items TEXT COMMENT '规格列表',
    para_items TEXT COMMENT '参数列表',
    saleable TINYINT(1) DEFAULT 1 COMMENT '是否上架',
    is_valid TINYINT(1) DEFAULT 1 COMMENT '是否有效'
) COMMENT '商品SPU表';
```

#### 6.1.3 订单表 (tb_order)
```sql
CREATE TABLE tb_order (
    order_id BIGINT PRIMARY KEY COMMENT '订单ID',
    order_no VARCHAR(64) UNIQUE COMMENT '订单编号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    merchant_id BIGINT NOT NULL COMMENT '商户ID',
    status VARCHAR(32) COMMENT '订单状态',
    goods_amount DECIMAL(10,2) COMMENT '商品总金额',
    freight_amount DECIMAL(10,2) COMMENT '运费金额',
    discount_amount DECIMAL(10,2) COMMENT '优惠金额',
    payable_amount DECIMAL(10,2) COMMENT '应付金额',
    payment_method VARCHAR(32) COMMENT '支付方式',
    payment_time DATETIME COMMENT '支付时间',
    delivery_method VARCHAR(32) COMMENT '配送方式',
    receiver_name VARCHAR(100) COMMENT '收货人姓名',
    receiver_phone VARCHAR(20) COMMENT '收货人电话',
    receiver_address TEXT COMMENT '收货人地址'
) COMMENT '订单表';
```

## 7. API 接口设计

### 7.1 商户相关接口

#### 7.1.1 获取商户详情
```
GET /merchant/{merchantId}
```

#### 7.1.2 商户列表分页查询
```
GET /merchant/pages
参数: page, size, merchantName, categoryId
```

#### 7.1.3 附近商户搜索
```
GET /merchant/frontend/nearby
参数: longitude, latitude, radius, categoryId
```

### 7.2 商品相关接口

#### 7.2.1 商品分类查询
```
GET /merchant/frontend/{categoryId}/pages
```

#### 7.2.2 品牌管理接口
```
POST   /brand    # 创建品牌
PUT    /brand    # 修改品牌
DELETE /brand/{id} # 删除品牌
GET    /brand/pages # 品牌分页查询
```

### 7.3 订单相关接口

#### 7.3.1 订单创建
```
POST /order
参数: OrderCreateCmd
```

#### 7.3.2 订单查询
```
GET /order/{orderId}
GET /order/pages
```

## 8. 部署与运维

### 8.1 环境要求
- JDK 17+
- MySQL 8.0+
- Redis 6.0+
- Maven 3.8+

### 8.2 配置文件
```yaml
# application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ry_vue?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
    username: root
    password: password
    
  redis:
    host: localhost
    port: 6379
    database: 0

# 高德地图配置
amap:
  api-key: your_api_key
  security-key: your_security_key
```

### 8.3 启动方式
```bash
# 编译打包
mvn clean package -DskipTests

# 启动应用
java -jar ruoyi-merchant-nearby.jar
```

## 9. 性能优化

### 9.1 缓存策略
- 商户地理位置信息缓存到 Redis GEO
- 商品分类树形结构缓存
- 热门商品列表缓存

### 9.2 数据库优化
- 合理建立索引
- 分页查询优化
- 连接池配置调优

### 9.3 接口性能
- 异步处理非核心业务
- 批量操作减少数据库交互
- 响应数据字段精简

## 10. 安全考虑

### 10.1 数据安全
- 敏感信息加密存储
- SQL 注入防护
- XSS 攻击防范

### 10.2 接口安全
- JWT Token 认证
- 接口限流控制
- 权限校验

### 10.3 业务安全
- 商户资质审核
- 订单防刷机制
- 支付安全校验

## 11. 测试策略

### 11.1 单元测试
```java
@Test
public void testCalculateWithoutDiscounts() {
    // 测试无折扣情况下的价格计算
    List<Product> products = Arrays.asList(
        new Product(1L, 2),
        new Product(2L, 1)
    );
    
    PriceCalculationCmd cmd = new PriceCalculationCmd()
        .setProducts(products);
        
    CalculationResult result = pricingEngine.calculate(cmd);
    
    assertEquals(new BigDecimal("300.00"), result.getFinalPrice());
}
```

### 11.2 集成测试
- 数据库集成测试
- Redis 缓存测试
- 第三方接口 Mock 测试

## 12. 监控与日志

### 12.1 日志配置
```xml
<!-- logback-spring.xml -->
<configuration>
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/merchant-nearby.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>logs/merchant-nearby.%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <maxFileSize>100MB</maxFileSize>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
</configuration>
```

### 12.2 监控指标
- 接口响应时间
- 数据库连接数
- Redis 缓存命中率
- JVM 内存使用情况

## 13. 版本更新记录

### v1.0.0 (2024-01-01)
- 初始版本发布
- 实现基础商户管理功能
- 完成商品管理模块
- 集成地理位置搜索功能

### v1.1.0 (2024-02-01)
- 新增订单管理系统
- 完善营销活动功能
- 优化配送费用计算
- 增强系统性能

---

*本文档最后更新时间: 2024年2月*