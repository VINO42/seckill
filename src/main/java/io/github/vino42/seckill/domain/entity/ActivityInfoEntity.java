package io.github.vino42.seckill.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Decription : 秒杀活动信息表
 * =====================================================================================
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("activity_info")
public class ActivityInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动渠道
     */
    private String channel;

    /**
     * 活动对象
     */
    private String target;

    /**
     * 前端路径
     */
    private String frontUrl;

    /**
     * 模板
     */
    private String template;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 商品id
     */
    private Long activityGoodId;

    /**
     * 秒杀保留时间
     */
    private Integer secKillRemainTime;

    /**
     * 每用户可购买次数
     */
    private Integer buyTimes;

    /**
     * 每用户可购买数量
     */
    private Integer limitCount;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

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
