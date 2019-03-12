<!DOCTYPE HTML>
<html  xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head th:include="_meta :: header">
<title>列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>XX管理 <span class="c-gray en">&gt;</span>XX模块<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
<form action="#" th:action="@{/admin/${table.name}}"  method="post">
	<div class="layui-body">
		<!-- 内容主体区域 -->
			<fieldset class="layui-elem-field">
				<legend>XXX列表</legend>
				<div class="layui-field-box">

					<hr style="margin: 0">
					<div class="cl pd-5 bg-1 bk-gray">
						<span class="l">
						<a href="javascript:;" onclick="deleteAll('/admin/${table.name}/deleteAll')"  class="layui-btn  layui-btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
						<a href="javascript:;" onclick="layer_show('添加','/admin/${table.name}/addBefore',800,300)" class="layui-btn  layui-btn-normal radius"><i class="Hui-iconfont">&#xe600;</i> 添加</a>
						</span>
						<span class="r wei-height">共有数据：<strong th:text="${r'${pageInfo.total}'}"></strong> 条</span>
					</div>
					<hr>
					<table class="layui-table text-c">
						<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""/></th>
							<th width="25">序号</th>
                           <#list table.fields as field >
                            <th width="40">${field.comment}</th>
                           </#list>
							<th width="100">操作</th>
						</tr>
						</thead>
						<tbody>
						<tr class="text-c" th:if="${r"${pageInfo.list.size()"} == 0}"><td colspan="15"><strong>暂无数据</strong></td></tr>
						<tr class="text-c" th:each="${table.name},count:${r"${pageInfo.list}"}">
							<td><input type="checkbox" value="1" th:value="${r"${"}${table.name}.id}" name="id"/></td>
							<td th:text="${r"${count.count}"}"></td>
							<#list table.fields as field >
                                <#if field.type == 'datetime'>
                                    <td th:text="${r"$"}{#dates.format(${table.name}.${field.propertyName}, 'yyyy-MM-dd HH:mm:ss')}"></td>
                                <#else>
                                    <td th:text="${r"$"}{${table.name}.${field.propertyName}}"></td>
                                </#if>
							</#list>
							<td class="td-manage">
								<a title="编辑" href="javascript:;" th:onclick="'javascript:layer_show(\'编辑\',\'/admin/'+'${table.name}'+'/editBefore/'+${r"${"}test${r".id}"}+'\',800,300)'" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
								<a title="删除"    href="javascript:;" th:onclick="'javascript:deleteById(\'/admin/'+'${table.name}'+'/delete/'+${r"${"}test${r".id}"}+'\')'" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</fieldset>
		</div>
	<div th:include="_pagination :: page"></div>
	</form>
</div>
<!--_footer 作为公共模版分离出去-->
<div th:replace="_footer :: footerjs">
</div>
<!--/_footer 作为公共模版分离出去-->
</body>
</html>