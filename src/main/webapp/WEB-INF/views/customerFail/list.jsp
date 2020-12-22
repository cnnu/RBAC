<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>失败客户管理</title>
    <jsp:include page="/WEB-INF/views/common/link.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="/WEB-INF/views/common/navbar.jsp" %>
    <!--菜单回显-->
    <c:set var="currentMenu" value="customer_fail"/>
    <%@include file="/WEB-INF/views/common/menu.jsp" %>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>失败客户管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 10px;">
                    <!--高级查询--->
                    <form class="form-inline" id="searchForm" action="/customer/failList" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <div class="form-group">
                            <label for="keyword">关键字:</label>
                            <input type="text" class="form-control" id="keyword" name="keyword" value="${(qo.keyword)}"
                                   placeholder="请输入姓名/电话">
                        </div>
                        <div class="form-group">
                            <label for="seller">市场专员:</label>
                            <select class="form-control" id="seller" name="sellerId">
                                <option value="-1">全部</option>
                                    <c:forEach items="${emps}" var="emp" >
                                    <option value="${emp.id}">${emp.name}</option>
                                    </c:forEach>
                            </select>
                            <script>
                                $("#seller option[value='${qo.sellerId}']").prop("selected", true);
                            </script>
                        </div>
                        <button id="btn_query" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>
                            查询
                        </button>
                    </form>
                </div>


                <!--编写内容-->
                <div class="box-body table-responsive no-padding ">
                    <table class="table table-hover table-bordered" align="center">
                        <tr>
                            <th>编号</th>
                            <th>名称</th>
                            <td>性别</td>
                            <td>年龄</td>
                            <th>电话</th>
                            <th>QQ</th>
                            <th>职业</th>
                            <th>来源</th>
                            <th>销售员</th>
                            <th>状态</th>

                        </tr>

<c:forEach items="${result.list}" var="c" varStatus="vs">

                          <tr>
                                <td>${c_index +1}</td>
                                <td>${c.name}</td>
                                <td>${c.genderStr}</td>
                                <td>${c.age}</td>
                                <td>${c.tel}</td>
                                <td>${c.qq}</td>
                                <td>${c.job.title}</td>
                                <td>${c.source.title}</td>
                                <td>${(c.seller.name)}</td>
                                <td>${c.statusStr}</td>


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
