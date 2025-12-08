package org.dromara.merchant.app.commodity.executor;

import lombok.RequiredArgsConstructor;
import org.dromara.merchant.client.commodity.dto.data.command.CategoryCreateCmd;
import org.dromara.merchant.domain.commodity.gateway.ICategoryGateway;
import org.dromara.merchant.infrastructure.commodity.converter.CategoryConvertor;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Code Skywalker
 * @Date 2025/12/8 15:38
 */
@Component
@RequiredArgsConstructor
public class CategoryCreateExe {

    private final ICategoryGateway categoryGateway;
    private final CategoryConvertor categoryConvertor;

    public boolean execute(CategoryCreateCmd cmd) {
        return categoryGateway.save(categoryConvertor.toEntity(cmd));
    }

}
