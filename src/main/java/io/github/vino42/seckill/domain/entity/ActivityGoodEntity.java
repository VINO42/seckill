package io.github.vino42.seckill.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * =====================================================================================
 *
 * @Created :   2021/09/24 21:31:22
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Copyright :
 * @Decription : 秒杀商品表
 * =====================================================================================
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("activity_good")
public class ActivityGoodEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 秒杀商品id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 商品id 对应真实商品的id
     */
    private Long goodsId;

    /**
     * 秒杀商品价格 分单位
     */
    private Integer seckillPrice;

    /**
     * 库存
     */
    private Integer store;

    /**
     * 商品图片
     */
    private String pic;

    /**
     * 创建者id
     */
    private Long creatorId;

    /**
     * 创建者名称
     */
    private String creatorName;

    /**
     * 更新者id
     */
    private Long updatorId;

    /**
     * 更新者名称
     */
    private String updatorName;


}
