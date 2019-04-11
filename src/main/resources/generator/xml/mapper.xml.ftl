<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${entity}Mapper">
  <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
    <#list table.commonFields as field >
        <#if field.type?contains("(")>
      <id column="${field.name}" jdbcType="${field.type ?replace((field.type ?substring(field.type?index_of('('),field.type?index_of(')') + 1)),'')?upper_case}" property="${field.propertyName}" />
        <#else>
      <id column="${field.name}" jdbcType="${field.type ?upper_case}" property="${field.propertyName}" />
      </#if>
    </#list>
      <#list table.fields as field >
          <#if field.type?contains("(")>
              <id column="${field.name}" jdbcType="${field.type ?replace((field.type ?substring(field.type?index_of('('),field.type?index_of(')') + 1)),'')?upper_case}" property="${field.propertyName}" />
          <#else>
              <id column="${field.name}" jdbcType="${field.type ?upper_case}" property="${field.propertyName}" />
          </#if>
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

<!--
 <select id="selectByPrimaryKey" parameterType="java.lang.Long" resultMap="BaseResultMap">
    select
    <include refid="Base_Column_List" />
    from ${table.name}
    <#list table.commonFields as field >
        <#if field.keyFlag>
         <#if field.type?contains("(")>
        where ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?replace((field.type ?substring(field.type?index_of('('),field.type?index_of(')') + 1)),'')?upper_case}}
        <#else>
       where ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?upper_case}}
        </#if>
    </#if>
    </#list>
  </select>

  <delete id="deleteByPrimaryKey" parameterType="java.lang.Long">
    delete from  ${table.name}
     <#list table.commonFields as field >
        <#if field.keyFlag>
     <#if field.type?contains("(")>
        where ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?replace((field.type ?substring(field.type?index_of('('),field.type?index_of(')') + 1)),'')?upper_case} }
        <#else>
       where ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?upper_case} }
        </#if>
    </#if>
    </#list>
  </delete>
  <insert id="insert" parameterType="${package.Entity}.${entity}">
    insert into ${table.name} (${table.fieldNames})
    values (
    <#list table.fields as field >
     <#if !field_has_next>
     <#if field.type?contains("(")>
         ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?replace((field.type ?substring(field.type?index_of('('),field.type?index_of(')') + 1)),'')?upper_case}}
       <#else>
         ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?upper_case}}
        </#if>
    <#else>
       <#if field.type?contains("(")>
         ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?replace((field.type ?substring(field.type?index_of('('),field.type?index_of(')') + 1)),'')?upper_case}},
       <#else>
         ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?upper_case}},
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
     <#list table.fields as field >
      <if test="${field.propertyName} != null">
      <#if field.type?contains("(")>
         ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?replace((field.type ?substring(field.type?index_of('('),field.type?index_of(')') + 1)),'')?upper_case}},
       <#else>
         ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?upper_case}},
        </#if>
      </if>
    </#list>
    </trim>
  </insert>
  <update id="updateByPrimaryKeySelective" parameterType="${package.Entity}.${entity}">
    update ${table.name}
    <set>
      <#list table.fields as field >
      <if test="${field.propertyName} != null">
       <#if field.type?contains("(")>
         ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?replace((field.type ?substring(field.type?index_of('('),field.type?index_of(')') + 1)),'')?upper_case}},
       <#else>
         ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?upper_case}},
        </#if>
      </if>
     </#list>
    </set>
    <#list table.commonFields as field >
        <#if field.keyFlag>
     <#if field.type?contains("(")>
        where ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?replace((field.type ?substring(field.type?index_of('('),field.type?index_of(')') + 1)),'')?upper_case} }
        <#else>
       where ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?upper_case} }
        </#if>

    </#if>
    </#list>
  </update>
  <update id="updateByPrimaryKey" parameterType="${package.Entity}.${entity}">
    update ${table.name}
    set
    <#list table.fields as field >
       <#if field.type?contains("(")>
         ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?replace((field.type ?substring(field.type?index_of('('),field.type?index_of(')') + 1)),'')?upper_case}},
       <#else>
         ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?upper_case}},
        </#if>
    </#list>

    <#list table.commonFields as field >
        <#if field.keyFlag>
     <#if field.type?contains("(")>
        where ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?replace((field.type ?substring(field.type?index_of('('),field.type?index_of(')') + 1)),'')?upper_case} }
        <#else>
       where ${field.name} = <#noparse>#</#noparse>{${field.propertyName},jdbcType=${field.type ?upper_case} }
        </#if>
    </#if>
    </#list>
  </update>
-->
</mapper>