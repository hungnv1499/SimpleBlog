<%-- 
    Document   : registration
    Created on : Jan 14, 2020, 12:56:42 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png" type="image/png" />

        <!-- CSS -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/media.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/normalize.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.theme.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.transitions.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.bxslider.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/elegant-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/magnific-popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flexslider.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flexslider-set.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id="back-top">
            <a href="#" class="scroll">
                <i class="arrow_carrot-up"></i>
            </a>
        </div>
        <div class="container text-center padding-vertical-50">
            <div class="row justify-content-md-center padding-bottom-30">
                <input type="button" name="btn_actived" id="btn_actived" value="Active Selected" class="btn btn-dark padding-vertical-10 margin-right-20"/>
                <input type="button" name="btn_deleted" id="btn_deleted" value="Delete Selected" class="btn btn-danger padding-vertical-10"/>
            </div>
            <h3 class="padding-bottom-30" id="status_article">Status: ${requestScope.ARTICLE.status}</h3>
            <h2 class="double-line"><span>${requestScope.ARTICLE.title}</span></h2>
            <div class="container padding-bottom-100">
                <div class="row">
                    <div class="col-md-12">

                        <input type="text" name="id_article" value="${requestScope.ARTICLE.id}" hidden="true"/>

                        <div class="row justify-content-md-center">
                            <div class="col-md-8">
                                <div class="blog-item">
                                    <div class="blog-short-description padding-bottom-40">
                                        <div class="post-info padding-top-5">
                                            <p><span>${requestScope.ARTICLE.dateCreated} </span>by <a href="#">${requestScope.ARTICLE.author.name}</a> 
                                            </p>
                                        </div>
                                        <blockquote class="margin-vertical-50">
                                            <p>${requestScope.ARTICLE.shortDescription}</p>
                                        </blockquote>
                                        <!-- /post-info -->
                                        <div class="post-paragraph padding-top-25 padding-bottom-40">
                                            <p style="font-weight: bold">${requestScope.ARTICLE.contents}
                                            </p>
                                        </div>
                                        <!-- /post-paragraph -->
                                    </div>
                                </div>


                                <div class="blog-comment bd-bottom padding-vertical-20" id="comment-article">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <table border="1" class="table tab-content" id="table-content">
                                                <thead>
                                                    <tr class="row">
                                                        <th class="col-md-12 col-sm-12 padding-bottom-30" ></th>
                                                    </tr>
                                                </thead>
                                                <tbody id="comment-table">


                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <!-- /post-comment -->
                                </div>  


                            </div>
                        </div>

                        <!--product list-->



                    </div>
                    <!-- /tabs -->
                </div>
                <!-- /column -->
            </div>
            <!-- /row -->
        </div>
        <!--search-->
    </div>
    <script src="${pageContext.request.contextPath}/js/lib/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/vendors/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/lib/jquery.waypoints.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/lib/moderniz.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
    <script src="${pageContext.request.contextPath}/js/vendors/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/vendors/jquery.bxslider.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/vendors/jquery.magnific-popup.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/vendors/jquery.flexslider-min.js"></script>
    <script src="${pageContext.request.contextPath}/js/vendors/smoothscroll.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery_01.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            getComments();
        });
        var comment_htmlz = '<div class="post-comment">' +
                '<a href="#"><img src="http://placehold.it/86x79" alt="" />  </a>' +
                '<div class="comment-body bd-bottom padding-bottom-30 text-left">' +
                '<div class="comment-head">' +
                '<p>Artical by <a href="#">Erentheme</a><span> August 02, 2016</span> </p>' +
                ' </div>' +
                '<div class="leave-reply pull-right">' +
                '<a href="#"><i class="fa fa-share fa-flip-vertical"></i>Leave reply</a>' +
                '</div>' +
                '<p class="comment margin-top-15"></p>' +
                '</div>' +
                '</div>';
        function getComments() {
            var id = getId();
            $.ajax({
                type: 'GET',
                url: "${pageContext.request.contextPath}/MainController",
                data: {action: "GetComments", id: id},
                headers: {
                    Accept: "application/json; charset=utf-8",
                    "Content-Type": "application/json; charset=utf-8"
                },
                success: function (data, textStatus, jqXHR) {
                    var list = JSON.parse(data);
                    document.getElementById("comment-table").innerHTML += "";
                    $.each(list, function (i, item) {
                        var comment_html =
                                '<tr class="row"> <td class="col-md-12 col-sm-12">' +
                                '<div class="post-comment">' +
                                '<a href="#"><img src="http://placehold.it/86x79" alt="" />' +
                                '<div class="comment-body bd-bottom padding-bottom-30 text-left">' +
                                '<div class="comment-head">' +
                                '<p>Comment by <a href="#">' + item.username + '</a><span>' + item.dateCreated + '</span> </p>' +
                                ' </div>' +
                                '<div class="leave-reply pull-right">' +
                                '<a href="#"><i class="fa fa-share fa-flip-vertical"></i>Leave reply</a>' +
                                '</div>' +
                                '<p class="comment margin-top-15">' + item.contents + '</p>' +
                                '</div>' +
                                '</div>' +
                                '</td></tr>';
                        document.getElementById("comment-table").innerHTML += comment_html;
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Some thing wrong, try again");
                }
            });
        }
        function getId() {
            return $('input[name=id_article]').val();
        }


        $("#btn_actived").click(function () {
            changeStatus("Actived");
        });
        $("#btn_deleted").click(function () {
            changeStatus("Deleted");
        });
        function changeStatus(status) {
            var input = $('input[name=id_article]').val();
            $.ajax({
                type: 'GET',
                url: "${pageContext.request.contextPath}/MainController",
                data: {input: input, action: "ChangeStatus", status: status},
                headers: {
                    Accept: "application/json; charset=utf-8",
                    "Content-Type": "application/json; charset=utf-8"
                },
                success: function (data, textStatus, jqXHR) {
                    var result = JSON.parse(data);
                    if (result == "successful") {
                        document.getElementById("status_article").innerHTML = "Status:" + status;
                    }

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Some thing wrong, try again");
                }

            });
        }
    </script>
</body>
</html>
