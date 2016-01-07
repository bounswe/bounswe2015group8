<%@ include file="/WEB-INF/pages/header.jsp" %>
<script type="text/javascript">
    function fillPlaceFromGoogleMap(){
        $("#place").val(window['place']);
    }
    function isASCII(str) {
        if(typeof(str)!=='string'){
            return false;
        }
        for(var i=0;i<str.length;i++){
            if(str.charCodeAt(i)>127){
                return false;
            }
        }
        return true;
    }

    $(document).ready(function(){
        $("#postForm").submit(function(e){
           if(!isASCII($("#titlePost").val())){
               $.notify("Please do not enter non-ASCII characters for title", "error");
               e.preventDefault();
           }
           else if(!isASCII($("#place").val())){
               $.notify("Please do not enter non-ASCII characters for place", "error");
               e.preventDefault();
           }
           else if(!isASCII($("textarea#content").val())){
               $.notify("Please do not enter non-ASCII characters for content ", "error");
               e.preventDefault();
           }
        });

        $("#media").change(function(){
            if($("#media").val() != ""){
                var fileNameIndex = $("#media").val().lastIndexOf("\\")+1;
                var fileName = $("#media").val().substring(fileNameIndex);
                $("#mediaName").html(fileName);
                $("#mediaName").css("color", "green");
            }
            else{
                $("#mediaName").html("No file chosen");
                $("#mediaName").css("color", "red");
            }
        })
    })
</script>

<div class="page-content container">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h>Add a story</h>
        </div>
        <div class="panel-body">

            <form class="form-horizontal"
              id="postForm"
              action="${contextPath}/upload_post"
              method="post"
              enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" name="heritageId" style="display:none;" value="${heritageId}"/>
                </div>
                <div class="form-group">
                    <label for="title" class="col-sm-1 control-label">Title</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="title" id="titlePost" placeholder="Title">
                    </div>
                </div>
                <div class="form-group">
                    <label for="place" class="col-sm-1 control-label">Place</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="place" id="place" placeholder="Place">
                    </div>
                    <button class="btn btn-default" type="button" style="float:right; margin-right:1.7%; background-color: #8a4ce2; color: #8a4ce2" onclick="window.open('${contextPath}/google_map');">
                        Choose
                        <span style="float:left; font-size: 20px;" class="col-sm-1 glyphicon glyphicon-map-marker"></span>
                    </button>
                </div>
                <div class="form-group">
                    <label for="content" class="col-sm-1 control-label">Content</label>
                    <div class="col-sm-9">
                        <textarea class="form-control" rows="3" name="content" id="content" placeholder="Content"
                                  style="width: 100%; resize: vertical"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="media" class="col-sm-1 control-label">Media</label>
                    <div class="col-sm-1">
                        <label for="media" class="btn btn-lg"><i class="glyphicon glyphicon-paperclip"></i></label>
                        <input type="file" name="media" id="media" style="display:none"/>
                    </div>
                    <div class="col-sm-8">
                        <div style="color:red;" id="mediaName">No file chosen</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-1" style="float:right">
                        <button type="submit" class="btn btn-default">Post</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
<%@ include file="/WEB-INF/pages/footer.jsp" %>
