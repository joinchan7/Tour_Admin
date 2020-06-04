<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
                     class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>
                    <security:authentication property="principal.username"/>
                </p>
                <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>
            <li id="admin-index">
                <a href="${pageContext.request.contextPath}/index.jsp">
                    <i class="fa fa-dashboard"></i> <span>首页</span>
                </a>
            </li>

            <li class="treeview">
                <a href="javascript:"> <i class="fa fa-cogs"></i>
                    <span>系统管理</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>

                <ul class="treeview-menu">
                    <li>
                        <security:authorize access="hasRole('ADMIN')">
                            <a href="${pageContext.request.contextPath}/users">
                                <i class="fa fa-circle-o"></i> 用户管理
                            </a>
                        </security:authorize>
                    </li>
                    <li>
                        <security:authorize access="hasRole('ADMIN')">
                            <a href="${pageContext.request.contextPath}/roles">
                                <i class="fa fa-circle-o"></i> 角色管理
                            </a>
                        </security:authorize>
                    </li>
                    <li>
                        <security:authorize access="hasRole('ADMIN')">
                            <a href="${pageContext.request.contextPath}/permissions">
                                <i class="fa fa-circle-o"></i> 资源权限管理
                            </a>
                        </security:authorize>
                    </li>
                    <li>
                        <security:authorize access="hasAnyRole('USER','ADMIN')">
                            <a href="${pageContext.request.contextPath}/sysLogs">
                                <i class="fa fa-circle-o"></i> 访问日志
                            </a>
                        </security:authorize>
                    </li>
                </ul>

            </li>
            <li class="treeview">
                <a href="javascript:"> <i class="fa fa-cube"></i>
                    <span>基础数据</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li>
                        <a href="${pageContext.request.contextPath}/products">
                            <i class="fa fa-circle-o"></i> 产品管理
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/orders">
                            <i class="fa fa-circle-o"></i> 订单管理
                        </a>
                    </li>
                </ul>
            </li>

        </ul>
    </section>
</aside>
