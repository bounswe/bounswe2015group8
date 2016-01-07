<%@ include file="/WEB-INF/pages/header.jsp" %>
<script type="text/javascript">
    function fillPlaceFromGoogleMap() {
        $("#place").val(window['place']);
    }
    function choose_file(){
        $("#addMedia").click();
    }

    $(document).ready(function(){
        $("#addMedia").change(function(){
            var formData = new FormData();
            formData.append('media', $('#addMedia')[0].files[0]);
            $("#addMediaButton").css('display', 'none');
            $.ajax({
                url: "${contextPath}/addMediaToPost/"+${postId},
                type: "POST",
                data: formData,
                enctype: 'multipart/form-data',
                processData: false,  // tell jQuery not to process the data
                contentType: false,  // tell jQuery not to set contentType
                success: function(response){
                    window.location.reload();
                },
                error: function(response){
                    window.location.reload();
                }
            });
        });
    });
</script>


<div class="page-content container">
    <div class="panel panel-success">
        <div class="panel-heading"><h>Edit your post</h></div>
        <div class="panel-body">
            <form class="form-horizontal"
                  id="postForm"
                  action="${contextPath}/update_post"
                  method="post"
                  enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" name="postId" style="display:none;" value="${postId}"/>
                </div>
                <div class="form-group">
                    <label for="title" class="col-sm-1 control-label">Title</label>

                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="title" id="title" placeholder="Title" value="${title}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="place" class="col-sm-1 control-label">Place</label>

                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="place" id="place" placeholder="Place" value="${place}">
                    </div>
                    <button class="btn btn-default" type="button" style="float:right; margin-right:1.2%; color: #8a4ce2; background-color: #8a4ce2;" onclick="window.open('${contextPath}/google_map');">
                        Choose
                        <span style="float:left; font-size: 20px;" class="col-sm-1 glyphicon glyphicon-map-marker"></span>
                    </button>
                </div>
                <div class="form-group">
                    <label for="content" class="col-sm-1 control-label">Content</label>

                    <div class="col-sm-9">
                    <textarea class="form-control" rows="3" name="content" id="content" placeholder="Content"
                              style="width: 100%; resize: vertical">${content}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <c:forEach var="media" items="${medias}">
                        <c:if test="${media.postOrHeritageId == postId && media.postOrHeritage!=true}">
                            <c:if test="${media.mediaType == 0}">
                                <img src="${media.mediaLink}" height="240px;" width="360px;">
                            </c:if>
                            <c:if test="${media.mediaType == 1 || media.mediaType == 2}">
                                <div id="container"></div>
                                <script type="text/javascript">
                                    jwplayer("container").setup({
                                        file: "${media.mediaLink}",
                                        height: 300,
                                        width: 520,
                                        autostart: false
                                    });
                                </script>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </div>
                <div class="form-group">
                    <button type="submit" style="float:right; margin-right: 1.2%;" class="btn btn-default">Post</button>
                </div>
            </form>
            <input id="addMediaButton" onclick="choose_file();" class="btn btn-primary" type="button" value="Add Media"/>
            <input style="display:none;" type="file" id="addMedia" />
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/pages/footer.jsp" %>
