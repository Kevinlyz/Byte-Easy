<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${entity}Mapper">
  <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
    <#list cfg.mybatisFields as field >
      <id column="${field.column}" jdbcType="${field.jdbcType}" property="${field.property}" />
    </#list>

  </resultMap>
  <sql id="Base_Column_List">
      <#list table.commonFields as field >
          <#if !field_has_next>
              ${field.name},
          </#if>
      </#list>
      ${table.fieldNames}
  </sql>

<!--注意:在打开下面注释的时候，在执行添加和更新操作时，一定要添加事物，否则会造成无法添加和更新-->
<!--
 <select id="selectByPrimaryKey" parameterType="java.lang.Long" resultMap="BaseResultMap">
    select
    <include refid="Base_Column_List" />
    from ${table.name}
    <#list cfg.mybatisFields as field >
        <#if field.keyFlag>
       where ${field.column} = <#noparse>#</#noparse>{${field.property},jdbcType=${field.jdbcType}}
        </#if>
    </#list>
  </select>

  <delete id="deleteByPrimaryKey" parameterType="java.lang.Long">
    delete from  ${table.name}
     <#list cfg.mybatisFields as field >
        <#if field.keyFlag>
       where ${field.column} = <#noparse>#</#noparse>{${field.property},jdbcType=${field.jdbcType}}
        </#if>
    </#list>
  </delete>
  <insert id="insert" parameterType="${package.Entity}.${entity}">
    insert into ${table.name} (${table.fieldNames})
    values (
    <#list cfg.mybatisFields as field >
    <#if !field.keyFlag>
    <#if !field_has_next>
         ${field.column} = <#noparse>#</#noparse>{${field.property},jdbcType=${field.jdbcType}}
    <#else>
         ${field.column} = <#noparse>#</#noparse>{${field.property},jdbcType=${field.jdbcType}},
    </#if>
    </#if>
    </#list>
    )
  </insert>

  <insert id="insertSelective" parameterType="${package.Entity}.${entity}">
    insert into ${table.name}
    <trim prefix="(" suffix=")" suffixOverrides=",">
     <#list table.fields as field >
      <if test="${field.propertyName} != null">
        ${field.name},
      </if>
    </#list>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
     <#list  cfg.mybatisFields as field >
     <#if !field.keyFlag>
      <if test="${field.property} != null">
        ${field.column} = <#noparse>#</#noparse>{${field.property},jdbcType=${field.jdbcType}},
      </if>
     </#if>

    </#list>
    </trim>
  </insert>
  <update id="updateByPrimaryKeySelective" parameterType="${package.Entity}.${entity}">
    update ${table.name}
    <set>
      <#list cfg.mybatisFields as field >
      <#if !field.keyFlag>
       <if test="${field.property} != null">
         ${field.column} = <#noparse>#</#noparse>{${field.property},jdbcType=${field.jdbcType}},
      </if>
      </#if>
     </#list>
    </set>
     <#list cfg.mybatisFields as field >
        <#if field.keyFlag>
       where ${field.column} = <#noparse>#</#noparse>{${field.property},jdbcType=${field.jdbcType}}
        </#if>
    </#list>
  </update>
  <update id="updateByPrimaryKey" parameterType="${package.Entity}.${entity}">
    update ${table.name}
    set
     <#list cfg.mybatisFields as field >
      <#if !field.keyFlag>
        ${field.column} = <#noparse>#</#noparse>{${field.property},jdbcType=${field.jdbcType}},
      </#if>
     </#list>

    <#list cfg.mybatisFields as field >
        <#if field.keyFlag>
       where ${field.column} = <#noparse>#</#noparse>{${field.property},jdbcType=${field.jdbcType}}
        </#if>
    </#list>
  </update>
-->
</mapper>