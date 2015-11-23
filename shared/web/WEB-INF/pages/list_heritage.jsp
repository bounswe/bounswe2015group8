<%@ include file="/WEB-INF/pages/header.jsp" %>

<div class="page-content container">
<c:set var="allHeritages" value="${allContent.heritages}"/>
<c:set var="medias" value="${allContent.medias}"/>

<script type="text/javascript">
    console.log("${allHeritages}");
</script>

<div class="well">
    <div class="row">
        <div class="col-xs-12">
            Why don't you add a new cultural heritage?
        </div>
        <div class="col-xs-12" style="height:20px;"></div>
        <div class="col-sm-12">
            <div class="btn-group pull-right" role="group">
                <button type="button"
                        class="btn btn-default"
                        onclick="window.location.href='${contextPath}/heritage'">
                    Add a cultural heritage!
                </button>
            </div>
        </div>
    </div>
</div>

<c:forEach items="${allHeritages}" var="heritage">
    <div class="row">
        <div class="col-xs-12" style="height:20px;"></div>
    </div>
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title" name="name" id="name">${heritage.name}</h3>
        </div>

        <div class="panel-body">
            <div class="row">
                <div class="col-sm-12">
                    <div class="row">
                        <label for="place" class="col-sm-2 control-label">Place</label>

                        <div class="col-sm-10">
                            <p name="place" id="place">
                                    ${heritage.place}
                            </p>
                        </div>
                    </div>
                    <div class="row">
                        <label for="description" class="col-sm-2 control-label">Description</label>

                        <div class="col-sm-10">
                            <p name="description" id="description">
                                    ${heritage.description}
                            </p>
                        </div>
                    </div>
                    <div class="row">
                        <label for="postDate" class="col-sm-2 control-label">Date</label>

                        <div class="col-sm-10">
                            <p name="postDate" id="postDate">
                                    ${heritage.postDate}
                            </p>
                        </div>
                    </div>

                    <c:forEach var="media" items="${medias}">
                        <c:if test="${media.postOrHeritageId == heritage.id && media.postOrHeritage==true}">
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
                </div>
            </div>
        </div>

        <div class="panel-footer">
            <div class="row">
                <div class="col-sm-offset-8 col-sm-4" role="group">
                    <button type="button"
                            class="btn btn-default"
                            onclick="window.location.href='${contextPath}/post/${heritage.id}'">
                        Add Post
                    </button>
                    <button type="button"
                            class="btn btn-default"
                            onclick="window.location.href='${contextPath}/show_posts/${heritage.id}'">
                        See Posts
                    </button>
                </div>
            </div>
        </div>


    </div>
</c:forEach>
    <div class="row">
        <div class="col-xs-12" style="height:40px;"></div>
    </div>

<%@ include file="/WEB-INF/pages/footer.jsp" %>
