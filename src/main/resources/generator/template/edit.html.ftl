<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="_meta :: header">
    <title>编辑</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="form-${entity?uncap_first}-edit" action="#"
          th:action="@{/${package.ModuleName}/${entity?uncap_first}/edit}" th:object="${r"$"}{${entity?uncap_first}}">
        <input type="hidden" name="id" th:value="${r"$"}{${entity?uncap_first}.id}"/>
        <#list table.fields as field >
            <#if (field.propertyName != "id" && field.propertyName != "createTime" && field.propertyName != "updateTime") >
                <div class="row cl">
                    <label class="form-label col-xs-2 col-sm-2"><span
                                class="c-red">*</span>${field.comment?split("#")[0]}：</label>
                        <#if field.type == 'datetime'>
                            <div class="formControls col-xs-10 col-sm-10">
                                <input type="input-text" class="input-text timeSpace" readonly="readonly" autocomplete="off" th:value="${r"$"}{#temporals.format(${entity?uncap_first}.${field.propertyName}, 'yyyy-MM-dd HH:mm:ss')}" name="${field.propertyName}" placeholder="请选择${field.comment?split("#")[0]}"/>
                            </div>
                        <#elseif field.type == 'tinyint' ||field.type == 'smallint' ||field.type == 'mediumint'||field.type == 'int'||field.type == 'bigint'>
                            <div class="formControls col-xs-10 col-sm-10">
                                <input type="number" class="input-text" th:value="${r"$"}{${entity?uncap_first}.${field.propertyName}}" name="${field.propertyName}" placeholder="修改${field.comment?split("#")[0]}" id="${field.propertyName}"/>
                            </div>
                        <#elseif field.type == 'float' ||field.type == 'double'||field.type == 'real'||field.type == 'decimal' >
                            <div class="formControls col-xs-10 col-sm-10">
                                <input type="number" step="0.001" class="input-text" th:value="${r"$"}{${entity?uncap_first}.${field.propertyName}}" name="${field.propertyName}" placeholder="修改${field.comment?split("#")[0]}" id="${field.propertyName}"/>
                            </div>
                       <#elseif field.type == 'text'>
                            <div class="formControls col-xs-10 col-sm-10">
                                <script id="editor" th:text="${r"$"}{${entity?uncap_first}.${field.propertyName}}" name="${field.propertyName}" autofocus type="text/plain" style="height: 500px">
                                </script>
                            </div>
                        <#else>
                            <div class="formControls col-xs-10 col-sm-10">
                                <input type="input-text" class="input-text" th:value="${r"$"}{${entity?uncap_first}.${field.propertyName}}" name="${field.propertyName}"  placeholder="修改${field.comment?split("#")[0]}" id="${field.propertyName}"/>
                            </div>
                        </#if>
                    </div>
                </div>
            </#if>
        </#list>
        <div class="row cl">
            <div class="col-xs-3 col-sm-3 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" id="subbtn" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"/>
                <input class="btn radius" id="reset" type="reset" value="&nbsp;&nbsp;重置&nbsp;&nbsp;"/>
            </div>
        </div>
    </form>
</article>
<div th:replace="_ueditor :: ueditor"></div>
<!--_footer 作为公共模版分离出去-->
<div th:replace="_footer :: footerjs"></div>
<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script>
    $(function () {
        $("#form-${entity?uncap_first}-edit").validate({
            rules: {
        <#list table.fields as field >
        <#if (field.propertyName != "id" && field.propertyName != "createTime" && field.propertyName != "updateTime") >
        ${field.propertyName}:
        {
            required: true,
        },
        </#if>
        </#list>
    },
        onkeyup:false,
            debug
    :
        true,
            success
    :
        "valid",
            submitHandler
    :

        function (form) {
            $(form).ajaxSubmit({
                type: 'POST',
                url: "/${package.ModuleName}/${entity?uncap_first}/edit",
                success: function (data) {
                    if (data.code == "0") {
                        layer.designMsg('编辑成功!', 1, function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.location.reload();
                            parent.layer.close(index);
                        });
                    } else {
                        layer.designMsg('编辑失败!');
                    }
                },
                error: function () {
                    layer.designMsg('编辑异常!', 5);
                }
            });
        }
    })
        ;
    });

    lay('.timeSpace').each(function(){
        laydate.render({
            elem: this
            ,trigger: 'click'
        });
    });

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>