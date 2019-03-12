package com.mbyte.easy.admin.entity;

import com.mbyte.easy.common.entity.BaseEntity;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author 王震
 * @since 2019-03-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Test extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


}
