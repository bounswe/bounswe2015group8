<%@ include file="/WEB-INF/pages/header.jsp" %>
<div class="page-content container">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h>Add a comment</h>
        </div>
        <div class="panel-body">

<form class="form-horizontal"
      id="postForm"
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