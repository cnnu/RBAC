<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>部门管理</title>
    <jsp:include page="/WEB-INF/views/common/link.jsp"/>

    <script>
        $(function () {

            //初始化超级管理员按钮
            // $("#role").toggle(!$("#admin").attr("checked"));
            //
            // //超级管理员的按钮事件
            // $("#admin").change(function () {
            //     console.log(this.checked);
            //     $("#role").toggle(!this.checked);
            //
            // })

            //使用上面的隐藏的方式，存在一个问题，就是即使隐藏的角色，角色的id 还是会提交
            var role_bak;
            //判断角色是否显示

            if ($("#admin").attr("checked")) {
                // 表示超管
                role_bak = $("#role").detach();
            }

            $("#admin").change(function () {
                if (this.checked) {
                    // 表示超管
                    role_bak = $("#role").detach();
                } else {
                    // 表示普通员工
                    $(this).closest(".form-group").after(role_bak);
                }
            })


            //角色去重
            //获取所有的角色
            var allRoles = $(".allRoles option");
            //获取自己拥有的角色
            var selfRoles = $(".selfRoles option");

            //通过map函数,把角色对象中的id进行提取,返回新的集合
            var selfRoleIds = $.map(selfRoles, function (ele) {
                return ele.value;
            })

            //遍历所有的角色，只要所有角色的value值在自己的角色Array里面存在，
            //则删除从所有角色中该角色，达到去重的目的
            $.each(allRoles,function (index,ele) {
                  if ($.inArray(ele.value , selfRoleIds)>=0){
                      ele.remove();
                  }
            });

            // 保存员工信息
             $("#submitBtn").click(function () {
                    //设置自己角色全部选中
                $(".selfRoles option").prop("selected", true);
                 // 提交表单
                 $("#editForm").submit();

             })
        })

        //全部移动
        function moveAll(src, target) {
            $("." + src + " option").appendTo($("." + target));
        }

        //部分移动
        function moveSelected(src, target) {
            $("." + src + " option:selected").appendTo($("." + target));
        }

    </script>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="/WEB-INF/views/common/navbar.jsp" %>
    <!--菜单回显-->
    <c:set var="currentMenu" value="employee"/>
    <%@include file="/WEB-INF/views/common/menu.jsp" %>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>用户编辑</h1>
        </section>
        <section class="content">
            <div class="box" style="padding: 10px;">
                <form class="form-horizontal" action="/employee/saveOrUpdate" method="post" id="editForm">
                    <input type="hidden" value="${entity.id}" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-2 control-label">用户名：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name" value="${entity.name}"
                                   placeholder="请输入用户名">
                        </div>
                    </div>

                    <c:if test="${empty entity.admin}">

                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码：</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="请输入密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="repassword" class="col-sm-2 control-label">验证密码：</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="repassword" name="repassword"
                                       placeholder="再输入一遍密码">
                            </div>
                        </div>

                    </c:if>

                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">电子邮箱：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="email" name="email" value="${entity.email}"
                                   placeholder="请输入邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label">年龄：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="age" name="age" value="${entity.age}"
                                   placeholder="请输入年龄">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept" class="col-sm-2 control-label">部门：</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="dept" name="dept.id">
                                <c:forEach items="${depts}" var="d">
                                    <option value="${d.id}">${d.name}</option>
                                </c:forEach>

                            </select>
                        </div>
                    </div>
                    <script>
                        $("#dept").val(${entity.dept.id})
                    </script>
                    <div class="form-group">
                        <label for="admin" class="col-sm-2 control-label">超级管理员：</label>
                        <div class="col-sm-6" style="margin-left: 15px;margin-top: 10px">
                            <input type="checkbox" id="admin" name="admin" ${entity.admin==true?'checked':''}
                                   class="checkbox">
                        </div>
                    </div>

                    <div class="form-group " id="role">
                        <label for="role" class="col-sm-2 control-label">分配角色：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allRoles" size="15">
                                    <c:forEach items="${roles}" var="r">
                                        <option value="${r.id}">${r.name}</option>
                                    </c:forEach>

                                </select>
                            </div>

                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>

                                    <a type="button" class="btn btn-primary  " style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>

                            <div class="col-sm-2">
                                <select multiple class="form-control selfRoles" size="15" name="ids">
                                    <c:forEach items="${entity.roles}" var="sr">
                                        <option value="${sr.id}">${sr.name}</option>
                                    </c:forEach>

                                </select>
                            </div>

                        </div>

                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button id="submitBtn" type="button" class="btn btn-primary">保存</button>
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