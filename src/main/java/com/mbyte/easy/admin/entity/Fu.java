package com.mbyte.easy.admin.entity;

import com.mbyte.easy.common.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 张伟晨
 * @since 2019-03-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Fu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 名字#not null
     */
    private String name;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 密码#not null
     */
    private String password;

    /**
     * 最终登录时间#not null
     */
    private LocalDateTime loginTime;

    /**
     * 描述#not null
     */
    private String content;


}
