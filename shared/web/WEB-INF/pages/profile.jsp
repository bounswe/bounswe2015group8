<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/header.jsp" %>

<c:set var="member" value="${member}"/>


<div class="page-content container">

    <div class="row">
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="http://vignette1.wikia.nocookie.net/minion/images/1/1b/Check-in-minion.jpg/revision/latest?cb=20140516030342">

                <div class="caption">
                    <h3>${member.username}</h3>

                    <p> Member Biography </p>

                    <p><a href="#" class="btn btn-primary" role="button">Change Profile Picture</a> <a href="#"
                                                                                                       class="btn btn-default"
                                                                                                       role="button">Edit
                        Biography</a></p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <h2>Followers</h2>
        </div>

        <div class="col-sm-6 col-md-4">
            <h2>Followings</h2>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">Subscribed Heritages</div>
        <div class="panel-body">
            <p> ${member.username} follows these heritages </p>
        </div>


        <div class="row">
            <div class="col-xs-12" style="height:20px;"></div>
        </div>
        <div class="panel panel-success">
            <div class="panel-heading">
                <div class="row">
                    <b>
                        <h class="panel-title" style="margin-left:0.5% " name="name" id="name">heritage 1</h>
                    </b>

                    <button style="float:right; margin-right:1%" type="button" class="btn btn-success followbutton"
                            onclick="followHeritage(heritageID)" id="followbutton_${heritage.id}">Follow
                    </button>

                </div>
            </div>

            <div class="panel-body">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="row">
                            <label for="place" class="col-sm-2 control-label">Place</label>

                            <div class="col-sm-10">
                                <p name="place" id="place">
                                    heritage-place
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <label for="description" class="col-sm-2 control-label">Description</label>

                            <div class="col-sm-10">
                                <p name="description" id="description">
                                    heritage description
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <label for="postDate" class="col-sm-2 control-label">Date</label>

                            <div class="col-sm-10">
                                <p name="postDate" id="postDate">
                                    heritage-posts
                                </p>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row">
                    <label for="tags_heritageID" class="col-sm-2 control-label">Tags</label>

                    <div class="col-sm-4" role="group">
                        <p id="tags_HeritageID"></p>
                    </div>
                </div>
            </div>


            <div class="panel-footer">
                <div class="row">
                    <button type="button" style="float:right; margin-right: 0.5%;"
                            class="btn btn-default"
                            onclick="window.location.href='${contextPath}/show_posts/1'">
                        See Posts
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12" style="height:40px;"></div>
    </div>
<%@ include file="/WEB-INF/pages/footer.jsp" %>