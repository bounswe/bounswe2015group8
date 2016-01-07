<%@ include file="/WEB-INF/pages/header.jsp" %>
<script>
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
        $("#commentForm").submit(function(e){
            if(!isASCII($("textarea#content").val())){
                $.notify("Please do not enter non-ASCII characters for content", "error");
                e.preventDefault();
            }
        });
    })
</script>
<div class="page-content container">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h>Add a comment</h>
        </div>
        <div class="panel-body">

<form class="form-horizontal"
      id="commentForm"
      action="${contextPath}/post_comment"
      method="post"
      enctype="multipart/form-data">
    <div class="form-group">
        <input type="text" name="postId" style="display:none;" value="${postId}"/>
    </div>
    <div class="form-group">

        <div class="col-sm-12">
            <textarea class="form-control" rows="3" name="content" id="content" placeholder="Content"
                      style="width: 100%; resize: vertical"></textarea>
        </div>
    </div>
    <div class="form-group">
            <button type="submit" class="btn btn-default" style="float:right; margin-right:1.2%">Add</button>
    </div>
</form>
        </div>
    </div>


<%@ include file="/WEB-INF/pages/footer.jsp" %>