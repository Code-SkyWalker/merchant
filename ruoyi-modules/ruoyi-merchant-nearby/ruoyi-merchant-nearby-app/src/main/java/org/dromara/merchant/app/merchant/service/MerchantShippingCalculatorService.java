//package org.dromara.merchant.app.merchant.service;
//
//import lombok.RequiredArgsConstructor;
//import org.dromara.merchant.domain.merchant.model.BillingMethod;
//import org.dromara.merchant.domain.merchant.model.MerchantShippingTemplate;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//
///**
// * @Description 商户运费计算器服务
// * @Author Code Skywalker
// * @Date 2025/12/3 15:30
// */
//@Service
//@RequiredArgsConstructor
//public class MerchantShippingCalculatorService {
//
//    /**
//     * 计算运费
//     *
//     * @param template 运费模板
//     * @param weight   重量(kg)
//     * @param quantity 数量(件)
//     * @param volume   体积(m³)
//     * @return 运费金额
//     */
//    public BigDecimal calculateShippingFee(MerchantShippingTemplate template, BigDecimal weight, Integer quantity, BigDecimal volume) {
//        // 检查是否满足包邮条件
//        if (Boolean.TRUE.equals(template.getFreeShipping())) {
//            // 检查包邮金额条件
//            if (template.getFreeShippingAmount() != null &&
//                template.getFreeShippingAmount().compareTo(BigDecimal.ZERO) > 0) {
//                // 这里应该传入订单金额进行比较，暂时返回null表示需要外部提供订单金额
//                // 在实际使用中，需要传入订单金额参数
//                return BigDecimal.ZERO;
//            }
//
//            // 检查包邮件数条件
//            if (template.getFreeShippingQuantity() != null &&
//                template.getFreeShippingQuantity() > 0 &&
//                quantity != null &&
//                quantity >= template.getFreeShippingQuantity()) {
//                return BigDecimal.ZERO;
//            }
//        }
//
//        // 根据计费方式计算运费
//        BillingMethod billingMethod = template.getBillingMethod();
//        if (billingMethod == null) {
//            throw new IllegalArgumentException("计费方式不能为空");
//        }
//
//        BigDecimal fee = BigDecimal.ZERO;
//
//        switch (billingMethod) {
//            case WEIGHT:
//                if (weight == null || weight.compareTo(BigDecimal.ZERO) <= 0) {
//                    throw new IllegalArgumentException("按重量计费时重量不能为空且必须大于0");
//                }
//                fee = calculateFeeByWeight(template, weight);
//                break;
//
//            case QUANTITY:
//                if (quantity == null || quantity <= 0) {
//                    throw new IllegalArgumentException("按数量计费时数量不能为空且必须大于0");
//                }
//                fee = calculateFeeByQuantity(template, quantity);
//                break;
//
//            case VOLUME:
//                if (volume == null || volume.compareTo(BigDecimal.ZERO) <= 0) {
//                    throw new IllegalArgumentException("按体积计费时体积不能为空且必须大于0");
//                }
//                fee = calculateFeeByVolume(template, volume);
//                break;
//
//            default:
//                throw new IllegalArgumentException("不支持的计费方式: " + billingMethod);
//        }
//
//        return fee;
//    }
//
//    /**
//     * 按重量计算运费
//     */
//    private BigDecimal calculateFeeByWeight(MerchantShippingTemplate template, BigDecimal weight) {
//        BigDecimal baseWeight = template.getBaseWeightQuantityVolume();
//        BigDecimal baseFee = template.getBaseFee();
//        BigDecimal additionalWeight = template.getAdditionalWeightQuantityVolume();
//        BigDecimal additionalFee = template.getAdditionalFee();
//
//        if (baseWeight == null || baseWeight.compareTo(BigDecimal.ZERO) <= 0) {
//            throw new IllegalArgumentException("首重不能小于等于0");
//        }
//
//        if (weight.compareTo(baseWeight) <= 0) {
//            // 未超过首重
//            return baseFee;
//        } else {
//            // 超过首重，需要计算续重费用
//            BigDecimal excessWeight = weight.subtract(baseWeight);
//            BigDecimal additionalCount = excessWeight.divide(additionalWeight, 0, BigDecimal.ROUND_UP);
//            return baseFee.add(additionalFee.multiply(additionalCount));
//        }
//    }
//
//    /**
//     * 按数量计算运费
//     */
//    private BigDecimal calculateFeeByQuantity(MerchantShippingTemplate template, Integer quantity) {
//        BigDecimal baseQuantity = template.getBaseWeightQuantityVolume();
//        BigDecimal baseFee = template.getBaseFee();
//        BigDecimal additionalQuantity = template.getAdditionalWeightQuantityVolume();
//        BigDecimal additionalFee = template.getAdditionalFee();
//
//        if (baseQuantity == null || baseQuantity.compareTo(BigDecimal.ZERO) <= 0) {
//            throw new IllegalArgumentException("首件不能小于等于0");
//        }
//
//        if (quantity <= baseQuantity.intValue()) {
//            // 未超过首件
//            return baseFee;
//        } else {
//            // 超过首件，需要计算续件费用
//            BigDecimal excessQuantity = BigDecimal.valueOf(quantity).subtract(baseQuantity);
//            BigDecimal additionalCount = excessQuantity.divide(additionalQuantity, 0, BigDecimal.ROUND_UP);
//            return baseFee.add(additionalFee.multiply(additionalCount));
//        }
//    }
//
//    /**
//     * 按体积计算运费
//     */
//    private BigDecimal calculateFeeByVolume(MerchantShippingTemplate template, BigDecimal volume) {
//        BigDecimal baseVolume = template.getBaseWeightQuantityVolume();
//        BigDecimal baseFee = template.getBaseFee();
//        BigDecimal additionalVolume = template.getAdditionalWeightQuantityVolume();
//        BigDecimal additionalFee = template.getAdditionalFee();
//
//        if (baseVolume == null || baseVolume.compareTo(BigDecimal.ZERO) <= 0) {
//            throw new IllegalArgumentException("首体积不能小于等于0");
//        }
//
//        if (volume.compareTo(baseVolume) <= 0) {
//            // 未超过首体积
//            return baseFee;
//        } else {
//            // 超过首体积，需要计算续体积费用
//            BigDecimal excessVolume = volume.subtract(baseVolume);
//            BigDecimal additionalCount = excessVolume.divide(additionalVolume, 0, BigDecimal.ROUND_UP);
//            return baseFee.add(additionalFee.multiply(additionalCount));
//        }
//    }
//}
