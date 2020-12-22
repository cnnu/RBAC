<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>角色管理</title>
    <jsp:include page="/WEB-INF/views/common/link.jsp"/>

    <script>
        $(function () {

            // 去重
            var allPermissions = $(".allPermissions option");
            var selfPermissions = $(".selfPermissions option");
            // 提取selfPermissions的value值
            var selfPermissionIds = $.map(selfPermissions, function (ele) {
                    return ele.value;
                })
            // 遍历所有的权限，删除重复的权限
            $.each(allPermissions, function (index, ele) {
                if ($.inArray(ele.value, selfPermissionIds) >= 0) {
                    ele.remove();
                }
            })


            $("#btn-submit").click(function () {
                //选中所有的自己的权限
                 $(".selfPermissions option").prop("selected",true);
                 $("#editForm").submit();
            })

        })

        function moveAll(src, target) {
            $("." + src + " option").appendTo($("." + target));
        }

        function moveSelected(src, target) {
            $("." + src + " option:selected").appendTo($("." + target));

        }
    </script>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="/WEB-INF/views/common/navbar.jsp" %>
    <!--菜单回显-->
    <c:set var="currentMenu" value="permission"/>
    <%@include file="/WEB-INF/views/common/menu.jsp" %>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>角色编辑</h1>
        </section>
        <section class="content">
            <div class="box" style="padding: 10px;">
                <form class="form-horizontal" action="/role/saveOrUpdate" method="post" id="editForm">
                    <input type="hidden" name="id" value="${entity.id}">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-2 control-label">角色名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name" value="${entity.name}"
                                   placeholder="请输入角色名称">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="sn" class="col-sm-2 control-label">角色编码：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="sn" name="sn" value="${entity.sn}"
                                   placeholder="请输入角色编码">
                        </div>
                    </div>

                    <div class="form-group " id="permission">
                        <label for="permission" class="col-sm-2 control-label">分配权限：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allPermissions" size="15">
                                    <c:forEach items="${permissions}" var="p">
                                        <option value="${p.id}">${p.name}</option>
                                    </c:forEach>

                                </select>
                            </div>

                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>

                                    <a type="button" class="btn btn-primary  " style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allPermissions', 'selfPermissions')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfPermissions', 'allPermissions')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allPermissions', 'selfPermissions')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfPermissions', 'allPermissions')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>

                            <div class="col-sm-2">
                                <select multiple class="form-control selfPermissions" size="15" name="ids">
                                    <c:forEach items="${entity.permissionList}" var="p">
                                        <option value="${p.id}">${p.name}</option>
                                    </c:forEach>

                                </select>
                            </div>

                        </div>

                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button id="btn-submit" type="button" class="btn btn-primary">保存</button>
                            <button type="reset" class="btn btn-danger">重置</button>
                        </div>
                    </div>


                </form>

            </div>

        </section>
    </div>
    <%@include file="/WEB-INF/views/common/footer.jsp" %>
</div>
</body>
</html>