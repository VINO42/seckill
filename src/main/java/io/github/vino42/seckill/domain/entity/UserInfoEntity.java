package io.github.vino42.seckill.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * =====================================================================================
 *
 * @Created :   2021/09/24 22:27:20
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Copyright : vino
 * @Decription : 用户表
 * =====================================================================================
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_info")
public class UserInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户表
     */
    @NotEmpty(message = "id不能为空")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 手机号
     */
    @NotEmpty(message = "手机号不能为空")
    private Integer phone;

    /**
     * 用户名称
     */
    private String name;


}
