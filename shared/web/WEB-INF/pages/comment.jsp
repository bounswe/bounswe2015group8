<%@ include file="/WEB-INF/pages/header.jsp" %>
<form class="form-horizontal"
      id="postForm"
      action="${contextPath}/post_comment"
      method="post"
      enctype="multipart/form-data">
    <div class="form-group">
        <input type="text" name="postId" style="display:none;" value="${postId}"/>
    </div>
    <div class="form-group">
        <label for="content" class="col-sm-2 control-label">Content</label>
        <div class="col-sm-10">
            <textarea class="form-control" rows="3" name="content" id="content" placeholder="Content"
                      style="width: 100%; resize: vertical"></textarea>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default btn-block">Post</button>
        </div>
    </div>
</form>
<%@ include file="/WEB-INF/pages/footer.jsp" %>