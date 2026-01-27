# PricingEngine 测试用例说明

## 概述
本测试用例用于验证 `PricingEngine` 类的功能，该类负责处理商品价格计算，包括优惠券、营销活动和运费的计算。

## 测试场景
1. **无折扣计算** - 验证没有优惠券或营销活动时的基础价格计算
2. **优惠券计算** - 验证优惠券对价格的影响
3. **营销活动计算** - 验证营销活动对价格的影响
4. **组合计算** - 验证优惠券和营销活动同时应用的情况
5. **运费计算** - 验证运费对最终价格的影响

## 依赖关系
- `PricingEngine` - 主要测试目标
- `Coupon` - 优惠券模型
- `Marketing` - 营销活动模型
- `Product` - 商品模型
- `CalculationResult` - 计算结果模型

## 测试方法说明
- `testCalculateWithoutDiscounts()` - 测试基础价格计算
- `testCalculateWithCoupon()` - 测试优惠券计算
- `testCalculateWithMarketing()` - 测试营销活动计算
- `testCalculateWithCouponAndMarketing()` - 测试组合优惠计算
- `testCalculateWithShippingFee()` - 测试运费计算


## 注意事项
由于这是一个复杂的领域模型，某些测试可能需要额外的配置或依赖项才能完全运行。