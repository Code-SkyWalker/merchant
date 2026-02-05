package org.dromara.merchant.client.marketing.dto.data.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 满折满减模式
 * @Author Code Skywalker
 * @Date 2025/12/26 11:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleMultiUnit implements Rule {

    /**
     * 优惠条件
     */
    private Requirement requirement;

    /**
     * 优惠内容
     */
    private Content content;

    /**
     * 优惠方式
     */
    private Way way;

    /**
     * 具体优惠信息
     */
    private List<Condition> discounts;

    /**
     * 优惠内容枚举
     */
    @Getter
    private enum Content {
        AMOUNT("AMOUNT", "满元"),
        QUANTITY("QUANTITY", "满件"),
        ;

        private final String code;
        private final String info;

        Content(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public static Content getByCode(String code) {
            for (Content content : values()) {
                if (content.getCode().equals(code)) {
                    return content;
                }
            }
            return null;
        }
    }

    /**
     * 优惠条件枚举
     */
    @Getter
    private enum Requirement {
        PRICE("PRICE", "减钱"),
        DISCOUNT("DISCOUNT", "打折"),
        ;

        private final String code;
        private final String info;

        Requirement(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public static Requirement getByCode(String code) {
            for (Requirement requirement : values()) {
                if (requirement.getCode().equals(code)) {
                    return requirement;
                }
            }
            return null;
        }
    }

    /**
     * 优惠方式枚举
     */
    @Getter
    private enum Way {
        TIERED("TIERED", "阶梯优惠"),       // 满100减20 满200减30
        LOOP("LOOP", "循环优惠"),           // "每" 满100减10
        ;

        private final String code;
        private final String info;

        Way(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public static Way getByCode(String code) {
            for (Way way : values()) {
                if (way.getCode().equals(code)) {
                    return way;
                }
            }
            return null;
        }
    }

    /**
     * 阶梯式优惠信息
     */
    @Data
    private static class Condition {

        /**
         * 满金额/满件
         */
        private BigDecimal amount;

        /**
         * 优惠金额/折扣
         */
        private BigDecimal discount;
    }
}
