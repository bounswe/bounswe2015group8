<%@ include file="/WEB-INF/pages/header.jsp" %>
<c:set var="allHeritages" value="${allContent.heritages}"/>
<c:set var="medias" value="${allContent.medias}"/>
<c:set var="allTags" value="${allContent.allTags}"/>

<script type="text/javascript">
    $(document).ready(function(){
        var tags = [];
        <c:forEach var="tag" items="${allTags}">
            tags.push("${tag.tagText}");
        </c:forEach>
        $(".tokenfield").tokenfield({
            autocomplete: {
                source: tags,
                delay: 100
            }
        });
        $(".tokenfield").on('keypress', function (e) {
            if(e.which == 41){ // If it is a closing paranthesis ")"
                var inputField = $(this).find("input.token-input");
                var newToken = $(inputField).val() + ")";
                $(inputField).parent().find("input.tokenfield").tokenfield('createToken', newToken);
                $(inputField).val("");
                e.preventDefault();
            }
        });
        $(".tagbutton").click(function(){
            var heritageId = $(this).attr("id").split("_")[1];
            var heritageTags = $("#tokenfield_" + heritageId).val().split(", ");
            $.ajax({
                url: "${contextPath}/tag_heritage/" + heritageId,
                data:{tagTexts: heritageTags},
                type: "POST",
                success: function(response) {
                    $("#tags_" + heritageId).html("");
                    for(var i = 0; i < response.length; i++){
                        var tag = response[i];
                        $("#tags_" + heritageId).append("<a href='${contextPath}/search/" + tag + "'>&lt;" + tag + "&gt;</a> ");
                    }
                }
            });
        });
    });
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
    <div class="well">
        <div class="row">
            <div class="col-sm-12">
                <div class="row">
                    <label for="name" class="col-sm-2 control-label">Name</label>

                    <div class="col-sm-10">
                        <p name="name" id="name">
                                ${heritage.name}
                        </p>
                    </div>
                </div>
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
                    <label for="postDate" class="col-sm-2 control-label">Description</label>

                    <div class="col-sm-10">
                        <p name="postDate" id="postDate">
                                ${heritage.postDate}
                        </p>
                    </div>
                </div>
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
                <div class="row">
                    <label for="tags_${heritage.id}" class="col-sm-2 control-label">Tags:</label>
                    <div class="col-sm-4" role="group">
                        <p id="tags_${heritage.id}">
                            <c:forEach items="${heritage.tags}" var="tag">
                                <a href="${contextPath}/search/${tag.tagText}">&lt;${tag.tagText}&gt;</a>
                            </c:forEach>
                        </p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-offset-2 col-sm-5" role="group">
                        <input style="width:80%;" type="text" class="form-control tokenfield" id="tokenfield_${heritage.id}" placeholder="Add tags..." />
                        <button style="float:right;" type="button" class="btn btn-success tagbutton" id="tagbutton_${heritage.id}">
                            Add Tags
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>

<%@ include file="/WEB-INF/pages/footer.jsp" %>
