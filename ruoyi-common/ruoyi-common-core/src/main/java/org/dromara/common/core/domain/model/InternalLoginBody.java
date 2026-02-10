package org.dromara.common.core.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.enums.UserType;
import org.dromara.common.core.validate.AddGroup;

/**
 * @Description 内部应用登录对象
 * @Author Code Skywalker
 * @Date 2025/12/19 13:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InternalLoginBody extends LoginBody {

    /**
     * 用户登录类型 2-商户管理员 1-租户管理员 0-普通用户
     */
    @NotNull(message = "用户登录类型不能为空", groups = {AddGroup.class})
    private Integer loginUserType;

    /**
     * 用户类型
     * SYS_USER:后台系统用户
     * APP_USER:移动客户端用户
     */
    @NotNull(message = "用户类型不能为空", groups = {AddGroup.class})
    private UserType userType;

    /**
     * 联系人姓名
     */
    @NotBlank(message = "联系人不能为空", groups = {AddGroup.class})
    private String contactUserName;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系人不能为空", groups = {AddGroup.class})
    private String contactPhone;

    /**
     * 企业名称
     */
    @NotBlank(message = "企业名称不能为空", groups = {AddGroup.class})
    private String companyName;

    /**
     * 用户名（创建系统用户）
     */
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class})
    private String username;

    /**
     * 密码（创建系统用户）
     */
    @NotBlank(message = "密码不能为空", groups = {AddGroup.class})
    private String password;

}
