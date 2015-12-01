
<%@ include file="/WEB-INF/pages/header.jsp" %>
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
    <button class="col-sm-2" type="button" style="background-color: #8a4ce2;" onclick="window.open('/google_map');">
      Choose from Google Maps
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
    <label for="media" class="col-sm-1 control-label">Media</label>
    <div class="col-sm-9">
      <label for="media" class="btn btn-lg"><i class="glyphicon glyphicon-paperclip"></i></label>
      <input type="file" name="media" id="media" style="display:none"/>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-1 col-sm-9">
      <button type="submit" class="btn btn-default btn-block">Post</button>
    </div>
  </div>
</form>
<%@ include file="/WEB-INF/pages/footer.jsp" %>
