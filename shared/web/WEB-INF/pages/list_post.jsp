<%@ include file="/WEB-INF/pages/header.jsp" %>
<c:set var="posts" value="${allContent.posts}"/>
<c:set var="medias" value="${allContent.medias}"/>
<c:set var="heritage" value="${allContent.heritages}"/>

<script>
    $(document).ready(function(){
        $(".upvote").click(function(){
            console.log($(this).attr("name"));
            var postId = $(this).attr("name");
            $.ajax({
                url: "${contextPath}/vote_post/" + postId,
                data:{voteType: true},
                type: "POST",
                success: function(response) {
                    console.log(response);
                    console.log('#votecount_' + postId);
                    $('#votecount_' + postId).text("Score: " + response);
                }
            });
        });
        $(".downvote").click(function(){
            console.log($(this).attr("name"));
            var postId = $(this).attr("name");
            $.ajax({
                url: "${contextPath}/vote_post/" + postId,
                data:{voteType: false},
                type: "POST",
                success: function(response) {
                    console.log(response);
                    console.log('#votecount_' + postId);
                    $('#votecount_' + postId).text("Score: " + response);
                }
            });
        });
    });
</script>

<c:if test="${allContent.heritages != null}">
    <div class="well">
        <div class="row">
            <div class="col-sm-12 form-group text-right" style="text-align:center; font-size:16px">
                <strong>Heritage Name: ${allContent.heritages[0].name}</strong>
            </div>
        </div>
    </div>
</c:if>

<c:forEach items="${posts}" var="post">
    <div class="row">
        <div class="col-xs-12" style="height:20px;"></div>
    </div>
    <div class="well">
        <div class="row">
            <div class="col-sm-2">
                <div class="row">
                    <div class="col-sm-12 form-group pull-right">
                        <label for="upvote_${post.id}" class="btn btn-lg"><i class="glyphicon glyphicon-triangle-top"></i></label>
                        <input id="upvote_${post.id}" type="button" name="${post.id}" class="upvote" style="display:none"/>
                    </div>
                </div>
                <div class="row">

                    <div class="col-sm-12 form-group text-right" id="votecount_${post.id}">
                        Score: ?
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 form-group pull-right">
                        <label for="downvote_${post.id}" class="btn btn-lg"><i class="glyphicon glyphicon-triangle-bottom"></i></label>
                        <input id="downvote_${post.id}" type="button" name="${post.id}" class="downvote" style="display:none"/>
                    </div>
                </div>
            </div>
            <div class="col-sm-10">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="row">
                            <label for="title_${post.id}" class="col-sm-2 control-label">Title</label>

                            <div class="col-sm-10">
                                <p name="title_${post.id}" id="title_${post.id}">
                                        ${post.title}
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <label for="content_${post.id}" class="col-sm-2 control-label">Content</label>

                            <div class="col-sm-10">
                                <p name="content_${post.id}" id="content_${post.id}">
                                        ${post.content}
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <label for="owner_${post.id}" class="col-sm-2 control-label">By</label>

                            <div class="col-sm-10">
                                <p name="owner_${post.id}" id="owner_${post.id}">
                                        ${post.owner.username}
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <label for="date_${post.id}" class="col-sm-2 control-label">Date posted</label>

                            <div class="col-sm-10">
                                <p name="date_${post.id}" id="date_${post.id}">
                                        ${post.postDate}
                                </p>
                            </div>
                        </div>
                        <c:forEach var="media" items="${medias}">
                            <c:if test="${media.postOrHeritageId == post.id && media.postOrHeritage==false}">
                                <div class="row">
                                    <label class="col-sm-2 control-label">Media</label>

                                    <div class="media col-sm-10">
                                        <div class="media-left">
                                            <img src="${contextPath}/static/${media.mediaLink}" height="240px;" width="360px;">
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                        <div class="row">
                            <div class="col-sm-offset-8 col-sm-4" role="group">
                                <button type="button"
                                        class="btn btn-default"
                                        onclick="window.location.href='${contextPath}/comment/${post.id}'">
                                    Comment
                                </button>
                                <button type="button"
                                        class="btn btn-default"
                                        onclick="window.location.href='${contextPath}/edit_post/${post.id}'">
                                    Edit Post
                                </button>
                            </div>
                        </div>
                        <c:forEach var="comment" items="${post.comments}">
                            <div class="row">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <blockquote>
                                        <p><strong>by ${comment.owner.username}</strong></p>
                                        <p>"${comment.content}"</p>
                                        <footer>${comment.lastEditedDate}</footer>
                                    </blockquote>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

    </div>
</c:forEach>
<%@ include file="/WEB-INF/pages/footer.jsp" %>