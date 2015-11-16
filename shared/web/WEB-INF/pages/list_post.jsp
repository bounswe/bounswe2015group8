<%@ include file="/WEB-INF/pages/header.jsp" %>
<c:set var="posts" value="${allContent.posts}"/>
<c:set var="medias" value="${allContent.medias}"/>
<c:set var="comments" value="${allContent.comments}"/>

<script>
    $(document).ready(function(){
        $("#upvote").click(function(){
            var postId = $(this).attr("name");
            $.ajax({
                url: "${contextPath}/vote_post/" + postId,
                data:{voteType: true},
                type: "POST",
                success: function(response) {
                    console.log(response);
                    console.log('#votecount_' + postId);
                    $('#votecount_' + postId).text(response);
                }
            });
        });
        $("#downvote").click(function(){
            var postId = $(this).attr("name");
            $.ajax({
                url: "${contextPath}/vote_post/" + postId,
                data:{voteType: false},
                type: "POST",
                success: function(response) {
                    console.log(response);
                    console.log('#votecount_' + postId);
                    $('#votecount_' + postId).text(response);
                }
            });
        });
    });
</script>

<c:forEach items="${posts}" var="post">
    <div class="row">
        <div class="col-xs-12" style="height:20px;"></div>
    </div>
    <div class="well">
        <div class="row">
            <div class="col-sm-2">
                <div class="row">
                    <div class="col-sm-12 form-group">
                        <label for="upvote" class="btn btn-lg"><i class="glyphicon glyphicon-triangle-top"></i></label>
                        <input type="button" name="${post.id}" id="upvote" style="display:none"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 form-group" id="votecount_${post.id}">
                        
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 form-group">
                        <label for="downvote" class="btn btn-lg"><i class="glyphicon glyphicon-triangle-bottom"></i></label>
                        <input type="button" name="${post.id}" id="downvote" style="display:none"/>
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
                                            <img src="${contextPath}/static/${media.mediaLink}" height="100%" width="100%">
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

    </div>
</c:forEach>
<%@ include file="/WEB-INF/pages/footer.jsp" %>