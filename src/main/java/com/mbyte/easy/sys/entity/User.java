package com.mbyte.easy.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @author 王震
 * @since 2019-03-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private LocalDateTime createtime;

    private String password;

    private LocalDateTime updatetime;

    private String username;

    private Boolean available;

    private String email;

    private String tel;

    /**
     * 性别(0.男,1.女)
     */
    private Integer sexType;


}
