<%@ include file="/WEB-INF/pages/header.jsp" %>
<c:set var="posts" value="${allContent.posts}"/>
<c:set var="medias" value="${allContent.medias}"/>
<c:set var="comments" value="${allContent.comments}"/>

<c:forEach items="${posts}" var="post">
    <div class="row">
        <div class="col-xs-12" style="height:20px;"></div>
    </div>
    <div class="well">
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
            </div>
        </div>
    </div>
</c:forEach>
<%@ include file="/WEB-INF/pages/footer.jsp" %>