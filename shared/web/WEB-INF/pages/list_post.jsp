<%@ include file="/WEB-INF/pages/header.jsp" %>
<div class="page-content container">
<c:set var="posts" value="${allContent.posts}"/>
<c:set var="medias" value="${allContent.medias}"/>
<c:set var="allTags" value="${allContent.allTags}"/>

<script>
    $(document).ready(function(){
        var tags = [];
        <c:forEach var="tag" items="${allTags}">
            <c:choose>
                <c:when test="${tag.tagContext == null}">
                    tags.push("${tag.tagText}");
                </c:when>
                <c:otherwise>
                    tags.push("${tag.tagText}(${tag.tagContext})");
                </c:otherwise>
            </c:choose>
        </c:forEach>
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
        $(".upvote-comment").click(function(){
            console.log($(this).attr("name"));
            var commentId = $(this).attr("name");
            $.ajax({
                url: "${contextPath}/vote_comment/" + commentId,
                data:{voteType: true},
                type: "POST",
                success: function(response) {
                    console.log(response);
                    console.log('#votecount_comment_' + commentId);
                    $('#votecount_comment_' + commentId).text("Score: " + response);
                }
            });
        });
        $(".downvote-comment").click(function(){
            console.log($(this).attr("name"));
            var commentId = $(this).attr("name");
            $.ajax({
                url: "${contextPath}/vote_comment/" + commentId,
                data:{voteType: false},
                type: "POST",
                success: function(response) {
                    console.log(response);
                    console.log('#votecount_comment_' + commentId);
                    $('#votecount_comment_' + commentId).text("Score: " + response);
                }
            });
        });
        $(".tokenfield").tokenfield({
            autocomplete: {
                source: tags,
                delay: 100
            }
        });

        $(".tagbutton").click(function(){
            var postId = $(this).attr("id").split("_")[1];
            var postTags = $("#tokenfield_" + postId).val().split(", ");
            $.ajax({
                url: "${contextPath}/tag_post/" + postId,
                data:{tagTexts: postTags},
                type: "POST",
                success: function(response) {
                    $("#tags_" + postId).html("");
                    for(var i = 0; i < response.length; i++){
                        var tag = response[i];
                        $("#tags_" + postId).append("<a href='${contextPath}/searchByTag/" + tag + "'>&lt;" + tag + "&gt;</a> ");
                    }
                    $(".token").remove();
                }
            });
        });

        $("#feed_type").change(function(){
           if($(this).val() == "heritage"){
               window.location.href = "${contextPath}/feed";
           }
        });

        if(window.location.href.indexOf("feed") > -1){
            $("#feed_type_div").css('display', '');
        }
    });

    function follow(memberId, memberName){
        $.ajax({
            url: "${contextPath}/follow/" + memberId,
            type: "POST",
            success: function(response) {
                if(response == -1){
                    $.notify("You are already following " + memberName, "warn");
                }
                else{
                    $.notify("You are now following " + memberName, "success");
                }
            }
        });
    }

    function unfollow(memberId, memberName){
        $.ajax({
            url: "${contextPath}/unfollow/" + memberId,
            type: "POST",
            success: function(response) {
                if(response == -1){
                    $.notify("You are already not following " + memberName, "warn");
                }
                else{
                    $.notify("You have unfollowed " + memberName, "success");
                }
            }
        });
    }
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

<c:if test="${allContent.wholeTags != null}">
    <div class="well">
        <div class="row">
            <div class="col-sm-12 form-group text-right" style="text-align:center; font-size:16px">
                <strong>Searched Tag: ${allContent.wholeTags[0]}</strong>
            </div>
        </div>
    </div>
</c:if>
    <p id="feed_type_div" style="display:none;">
        FEED TYPE:
        <select id="feed_type">
            <option value="post" selected>POST</option>
            <option value="heritage">HERITAGE</option>
        </select>
    </p>

<c:forEach items="${posts}" var="post">
    <div class="row">
        <div class="col-xs-12" style="height:20px;"></div>
    </div>
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">${post.title}</h3>
        </div>

        <div class="panel-body">
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
                            Score: ${post.totalVote}
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
                                <label for="content_${post.id}" class="col-sm-2 control-label">Content</label>

                                <div class="col-sm-10">
                                    <p style="white-space: pre-wrap;" name="content_${post.id}" id="content_${post.id}">${post.content}</p>
                                </div>
                            </div>
                            <div class="row">
                                <label for="owner_${post.id}" class="col-sm-2 control-label">By</label>

                                <div class="col-sm-4">
                                    <span name="owner_${post.id}" id="owner_${post.id}">
                                            <a href="${contextPath}/profile/${post.owner.username}">${post.owner.username}</a>
                                    </span>
                                </div>
                                <c:if test="${isAuthorized}">
                                    <c:if test="${principal.username != post.owner.username}">
                                        <button class="btn btn-success followUserButton" style="margin-left: 1.2%" onclick="follow('${post.owner.id}', '${post.owner.username}');"
                                                type="button" class="follow" id="followUserButton_${post.owner.id}">Follow</button>
                                        <button class="btn btn-danger followUserButton" style="margin-left: 1.2%" onclick="unfollow('${post.owner.id}', '${post.owner.username}');"
                                                type="button" class="unfollow" id="unfollowUserButton_${post.owner.id}">Unfollow</button>
                                    </c:if>
                                </c:if>
                            </div>
                            <div class="row">
                                <label for="place_${post.id}" class="col-sm-2 control-label">Place</label>

                                <div class="col-sm-10">
                                    <p name="place_${post.id}" id="place_${post.id}">
                                            ${post.place}
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

                            <div class="row">
                                <label for="tags_${post.id}" class="col-sm-2 control-label">Tags</label>
                                <div class="col-sm-4" role="group">
                                    <p id="tags_${post.id}">
                                        <c:forEach items="${post.tags}" var="tag">
                                            <a href="${contextPath}/searchByTag/${tag.tagText}<c:if test="${tag.tagContext != null}">(${tag.tagContext})</c:if>">
                                                &lt;${tag.tagText}<c:if test="${tag.tagContext != null}">(${tag.tagContext})</c:if>&gt;
                                            </a>
                                        </c:forEach>
                                    </p>
                                </div>
                            </div>
                            <c:if test="${isAuthorized}">
                                <div class="row">
                                    <div class="col-sm-offset-2 col-sm-5" role="group">
                                        <button style="float:right;" type="button" class="btn btn-success tagbutton" id="tagbutton_${post.id}">Add</button>
                                        <input style="width:85%;" type="text" class="form-control tokenfield" id="tokenfield_${post.id}" placeholder="Add tags..." />
                                    </div>
                                </div>
                            </c:if>
                        </div>
                        <c:forEach var="media" items="${medias}">
                            <c:if test="${media.postOrHeritageId == post.id && media.postOrHeritage!=true}">
                                <c:if test="${media.mediaType == 0}">
                                    <img src="${media.mediaLink}" height="240px;" width="360px;">
                                </c:if>
                                <c:if test="${media.mediaType == 1 || media.mediaType == 2}">
                                    <div id="container_${media.id}"></div>
                                    <script type="text/javascript">
                                        jwplayer("container_${media.id}").setup({
                                            file: "${media.mediaLink}",
                                            height: 300,
                                            width: 520,
                                            autostart: false
                                        });
                                    </script>
                                </c:if>
                            </c:if>
                        </c:forEach>

                        <c:forEach var="comment" items="${post.comments}">
                            <div class="row">
                                <div class="col-sm-offset-1 col-sm-1">
                                    <div class="row">
                                        <div class="col-sm-12 form-group pull-right">

                                        </div>
                                    </div>
                                    <div class="row">

                                        <div class="col-sm-13 form-group text-right" id="votecount_comment_${comment.id}">
                                            Score: ${comment.totalVote}
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12 form-group pull-right">

                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-10">
                                    <blockquote>
                                        <p><strong>by <a href="${contextPath}/profile/${comment.owner.username}">${comment.owner.username}</a></strong>
                                            <c:if test="${isAuthorized}">
                                                <c:if test="${principal.username != comment.owner.username}">
                                                    <button class="btn btn-success followUserButton" style="margin-left: 1.2%" onclick="follow('${comment.owner.id}', '${comment.owner.username}');"
                                                            type="button" id="followUserButton_${comment.owner.username}">Follow</button>
                                                    <button class="btn btn-danger followUserButton" style="margin-left: 1.2%" onclick="unfollow('${comment.owner.id}', '${comment.owner.username}');"
                                                            type="button" id="unfollowUserButton_${comment.owner.username}">Unfollow</button>
                                                </c:if>
                                            </c:if>
                                        </p>
                                        <p>"${comment.content}"</p>
                                        <footer>${comment.lastEditedDate}</footer>
                                        <label for="upvote_comment_${comment.id}" class="btn btn-lg"><i class="glyphicon glyphicon-thumbs-up"></i></label>
                                        <input id="upvote_comment_${comment.id}" type="button" name="${comment.id}" class="upvote-comment" style="display:none"/>
                                        <label for="downvote_comment_${comment.id}" class="btn btn-lg"><i class="glyphicon glyphicon-thumbs-down"></i></label>
                                        <input id="downvote_comment_${comment.id}" type="button" name="${comment.id}" class="downvote-comment" style="display:none"/>

                                    </blockquote>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

        </div>

        <c:if test="${isAuthorized}">
            <div class="panel-footer">
            <div class="row">
                <div class="col-sm-offset-8 col-sm-4" role="group">
                    <button type="button" style="float:right"
                            class="btn btn-default"
                            onclick="window.location.href='${contextPath}/comment/${post.id}'">
                        Comment
                    </button>
                    <c:if test="${principal.username == post.owner.username}">
                        <button type="button" style="float:right; margin-right: 4%;"
                                class="btn btn-default"
                                onclick="window.location.href='${contextPath}/edit_post/${post.id}'">
                            Edit
                        </button>
                    </c:if>
                </div>
            </div>
        </div>
        </c:if>
    </div>
</c:forEach>
    <div class="row">
        <div class="col-xs-12" style="height:40px;"></div>
    </div>
<%@ include file="/WEB-INF/pages/footer.jsp" %>