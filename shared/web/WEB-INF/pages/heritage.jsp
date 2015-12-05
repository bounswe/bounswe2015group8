<%@ include file="/WEB-INF/pages/header.jsp" %>
<div class="page-content container">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h>Add a cultural heritage</h>
        </div>
        <div class="panel-body">

            <form class="form-horizontal"
                  id="heritageForm"
                  action="${contextPath}/upload_heritage"
                  method="post"
                  enctype="multipart/form-data">
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="name" id="name" placeholder="Name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="place" class="col-sm-2 control-label">Place</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="place" id="place" placeholder="Place">
                    </div>
                </div>
                <div class="form-group">
                    <label for="place" class="col-sm-2 control-label">Description</label>

                    <div class="col-sm-10">
            <textarea class="form-control" rows="3" name="description" id="description" placeholder="Description"
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
                    <div class="col-sm-offset-2 col-sm-1" style="float:right">
                        <button type="submit" class="btn btn-default btn-block">Post</button>
                    </div>
                </div>
            </form>

        </div>
    </div>




<%@ include file="/WEB-INF/pages/footer.jsp" %>




