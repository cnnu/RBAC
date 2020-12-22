<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
    <jsp:include page="/WEB-INF/views/common/link.jsp"/>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="/WEB-INF/views/common/navbar.jsp" %>
    <!--菜单回显-->
    <c:set var="currentMenu" value="employee"/>
    <%@include file="/WEB-INF/views/common/menu.jsp" %>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工管理</h1>
        </section>
        <section class="content">
            <div class="box" style="padding: 10px">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/employee/list" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                    <div class="form-group">
                        <label for="keyword">关键字:</label>
                        <input type="text" class="form-control" id="keyword" name="keyword" value="${qo.keyword}"
                               placeholder="请输入姓名/邮箱">
                    </div>
                    <div class="form-group">
                        <label for="dept">部门:</label>
                        <select class="form-control" id="dept" name="deptId">
                            <option value="-1">全部部门</option>
                            <c:forEach items="${depts}" var="dept">
                                <option value="${dept.id}">${dept.name}</option>
                            </c:forEach>
                        </select>

                    </div>
                    <script>
                         $("#dept").val(${qo.deptId})
                    </script>
                    <button id="btn_query" class="btn btn-default">查询</button>
                    <a href="/employee/input" class="btn btn-success">添加</a>
                </form>
                <!--编写内容-->
                <div class="box-body table-responsive no-padding ">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>名称</th>
                            <th>email</th>
                            <th>年龄</th>
                            <th>部门</th>
                            <th>操作</th>
                        </tr>
                        </thead>

                        <c:forEach items="${result.list}" var="emp" varStatus="vs">

                            <tr>
                                <td>${vs.count}</td>
                                <td>${emp.name}</td>
                                <td>${emp.email}</td>
                                <td>${emp.age}</td>
                                <td>${emp.dept.name}</td>

                                <td>
                                    <a class="btn btn-info btn-xs btn_redirect" href="/employee/input?id=${emp.id}">
                                        <span class="glyphicon glyphicon-pencil"></span> 编辑
                                    </a>
                                    <a href="javaScript:void();" data-url="/employee/delete?id=${emp.id}" class="btn btn-danger btn-xs btn-delete">
                                        <span class="glyphicon glyphicon-trash"></span> 删除
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                    </table>
                    <!--分页-->
                    <%@include file="/WEB-INF/views/common/page.jsp" %>
                </div>
            </div>
        </section>
    </div>
    <%@include file="/WEB-INF/views/common/footer.jsp" %>
</div>
</body>
</html>
