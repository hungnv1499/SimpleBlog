
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--header-->
<c:if test="${sessionScope.ROLE ne 'Admin'}">
    <jsp:forward page="../login.jsp"/>
</c:if>
<header style="padding-bottom: 100px;">
    <nav class="main-nav menu-dark menu-transparent nav-transparent active">
        <div class="col-md-12">
            <div class="navbar">
                <div class="brand-logo">
                    <a href="#" class="navbar-brand">
                        <img src="img/logo.png" alt="" />
                    </a>
                </div>
                <!-- brand-logo -->

                <div class="navbar-header">
                    <div class="inner-nav right-nav">
                        <ul class="rightnav-links">

                            <c:if test="${sessionScope.USER ne null}" var="checkUser">
                                <li>
                                    <a href="#" class="dropdown-toggle" >Welcome, ${sessionScope.USERNAME}</a>
                                </li>
                                <c:url var="logoutLink" value="../MainController">
                                    <c:param name="action" value="Logout"/>
                                </c:url>
                                <li>
                                    <a href="${logoutLink}">Logout</a>
                                </li>
                            </c:if>
                            <c:if test="${!checkUser}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/registration.jsp">
                                        Registry
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/login.jsp">
                                        Login
                                    </a>
                                </li>

                            </c:if>



                        </ul>
                    </div>
                    <!-- /right-nav -->
                </div>
                <!-- navbar-header -->

                <div class="custom-collapse navbar-collapse collapse inner-nav margin-left-100">
                    <ul class="nav navbar-nav nav-links">
                        <li class="dropdown classic-dropdown"><a href="${pageContext.request.contextPath}/index.jsp" class="dropdown-toggle" >HOME</a>

                        </li>
                        <c:if test="${sessionScope.ROLE eq 'User'}">
                            <li class="dropdown classic-dropdown"><a href="${pageContext.request.contextPath}/user/createArticle.jsp" class="dropdown-toggle" >POST</a>

                            </li>
                        </c:if>

                        <!-- /dropdown -->
                        <c:if test="${sessionScope.ROLE eq 'Admin'}">
                            <li class="dropdown classic-dropdown"><a href="${pageContext.request.contextPath}/admin/index.jsp" class="dropdown-toggle" >Blog Process</a>

                            </li>
                        </c:if>
                        <!-- /dropdown -->


                        <!-- /dropdown -->
                    </ul>
                    <!-- /nav -->
                </div>
                <!-- /collapse -->
            </div>
            <!-- /navbar -->


        </div>
        <!-- /container -->
    </nav>
    <!-- /nav -->
</header>
<!--end header-->
