<%--
  Created by IntelliJ IDEA.
  User: gokcan
  Date: 06.12.2015
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/pages/header.jsp" %>
<c:set var="allHeritages" value="${allContent.heritages}"/>
<c:set var="post" value="${allContent.posts[0]}"/>
<script>
    $(document).ready(function(){
        heritageTitles = [];
        <c:forEach var="heritage" items="${allHeritages}">
            heritageTitles.push("${heritage.name}");
        </c:forEach>

        $("#heritageName").autocomplete({
            source: heritageTitles
        });

        $("#link").click(function(){
            var heritageName = $("#heritageName").val();
            if(heritageName == ""){
                $.notify("You have to enter a heritage name", "error");
                return false;
            }
            $.ajax({
                url: "${contextPath}/linkPostWithHeritage/" + heritageName,
                data:{postId: ${post.id}},
                type: "POST",
                success: function(response) {
                    if(response == -2){
                        $.notify("Such a heritage does not exist", "error");
                    }
                    else if(response == -1){
                        $.notify("This post is already associated with this heritage", "warn");
                    }
                    else{
                        $.notify("This post is successfully linked to the heritage " + heritageName, "success");
                    }
                }
            });
        });
    });

</script>

<div class="form-group" style="margin-top: 5%;">
    <label style="text-align: right; margin-top:2%;" for="title" class="col-sm-2  col-sm-offset-2 control-label">Title of the Post</label>
    <div class="col-sm-2">
        <textarea disabled class="form-control" rows="3" name="title" id="title"
                      style="width: 100%; resize: vertical">${post.title}</textarea>
    </div>
    <div class="col-sm-1">
        <button style="margin-top: 25%;" id="link" type="button" class="btn btn-default btn-block">Link</button>
    </div>
    <div class="col-sm-2">
        <textarea class="form-control" rows="3" name="heritageName" id="heritageName" placeholder="Heritage Name"
                      style="width: 100%; resize: vertical"></textarea>
    </div>

</div>

<%@ include file="/WEB-INF/pages/footer.jsp" %>