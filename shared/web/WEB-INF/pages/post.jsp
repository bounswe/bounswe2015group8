<%@ include file="/WEB-INF/pages/header.jsp" %>
<div class="page-content container">
<form class="form-horizontal"
      id="postForm"
      action="${contextPath}/upload_post"
      method="post"
      enctype="multipart/form-data">
    <div class="form-group">
        <input type="text" name="heritageId" style="display:none;" value="${heritageId}"/>
    </div>
    <div class="form-group">
        <label for="title" class="col-sm-2 control-label">Title</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="title" id="title" placeholder="Title">
        </div>
    </div>
    <div class="form-group">
        <label for="content" class="col-sm-2 control-label">Content</label>
        <div class="col-sm-10">
            <textarea class="form-control" rows="3" name="content" id="content" placeholder="Content"
                      style="width: 100%; resize: vertical"></textarea>
        </div>
    </div>
    <div class="form-group">
        <label for="media" class="col-sm-2 control-label">Media</label>
        <div class="col-sm-10">
            <label for="media" class="btn btn-lg"><i class="glyphicon glyphicon-paperclip"></i></label>
            <input type="file" name="media" id="media" style="display:none"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default btn-block">Post</button>
        </div>
    </div>
</form>
<%@ include file="/WEB-INF/pages/footer.jsp" %>
