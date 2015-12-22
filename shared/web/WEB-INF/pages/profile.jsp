<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/pages/header.jsp" %>

<c:set var="member" value="${allContent.member}"/>
<c:set var="medias" value="${allContent.medias}"/>
<c:set var="allTags" value="${allContent.allTags}"/>

<script>
    var tags = [];
    <c:forEach var="tag" items="${allTags}">
        <c:choose>
            <c:when test="${tag.tagContext == null}">
                tags.push("${tag.tagText}");
            </c:when>
            <c:otherwise>
                tags.push("${tag.tagText}(${tag.tagContext})");
            </c:otherwise>
        </c:choose>
    </c:forEach>

    function edit_bio_click(){
        currentBio = $("#biography").html();
        $("#edit_biography").html(currentBio);
        $("#edit_biography").css('display', '');
        $("#biography").css('display', 'none');
        $("#edit_bio_button").attr("disabled", "disabled");
    }

    function choose_file(){
        $("#profilePicture").click();
    }

    function followHeritage(heritageId){
        $.ajax({
            url: "${contextPath}/followHeritage/" + heritageId,
            type: "POST",
            success: function(response) {
                if(response == -1){
                    $.notify("You are already following this heritage", "warn", {position:"top center"});
                }
                else{
                    $.notify("You are now following this heritage", "success", {position:"top center"});
                }
            }
        });
    }

    $(document).ready(function(){
        $("#edit_biography").change(function(){
            $("#edit_bio_button").removeAttr('disabled');
            var updatedBio = $("#edit_biography").val();

            $.ajax({
                url: "${contextPath}/updateBiography",
                data:{biography: updatedBio},
                type: "POST",
                success: function(response) {
                    $("#biography").html(updatedBio);
                    $("#edit_biography").css('display', 'none');
                    $("#biography").css('display', '');
                    $("#edit_bio_button").removeAttr('disabled');
                }
            });
        });

        $("#profilePicture").change(function(){
            var formData = new FormData();
            formData.append('picture', $('#profilePicture')[0].files[0]);
            console.log(formData);
            $.ajax({
                url: "${contextPath}/uploadProfilePicture",
                type: "POST",
                data: formData,
                enctype: 'multipart/form-data',
                processData: false,  // tell jQuery not to process the data
                contentType: false,   // tell jQuery not to set contentType
                success: function(response){
                    window.location.reload();
                },
                error: function(response){
                    window.location.reload();
                }
            });
        });

        $("#tokenfield").tokenfield({
            autocomplete: {
                source: tags,
                delay: 100
            }
        });

        $("#tagbutton").click(function(){
            var interestedTags = $("#tokenfield").val().split(", ");
            $.ajax({
                url: "${contextPath}/followTag",
                data:{tags: interestedTags},
                type: "POST",
                success: function(response) {
                    for(var i = 0; i < response.length; i++){
                        var tag = response[i];
                        if($("#interestedInTags").html().indexOf(tag) == -1)
                            $("#interestedInTags").append("<a href='${contextPath}/searchHeritageByTag/" + tag + "'>" + tag + "</a>&nbsp;");
                    }
                }
            });
        });
    });

</script>

<div class="page-content container">

    <div class="row">
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <c:choose>
                    <c:when test="${member.profilePicture == null || member.profilePicture == ''}">
                        <img id="picture" src="${contextPath}/static/img/minion.jpg">
                    </c:when>
                    <c:otherwise>
                        <img id="picture" src="${member.profilePicture}">
                    </c:otherwise>
                </c:choose>

                <div class="caption">
                    <h3>${member.username}</h3>
                    <i>Member Biography</i>
                    <p id="biography">${member.biography}</p>
                    <textarea style="display: none; width: 100%;" type='text' id='edit_biography'></textarea>
                    Interested in:
                    <p id="interestedInTags">
                        <c:forEach var="tag" items="${member.followedTags}">
                            <a href="${contextPath}/searchHeritageByTag/${tag.tagText}<c:if test="${tag.tagContext != null}">(${tag.tagContext})</c:if>">
                                ${tag.tagText}<c:if test="${tag.tagContext != null}">(${tag.tagContext})</c:if>&nbsp;
                            </a>
                        </c:forEach>
                    </p>

                    <c:if test="${principal.username == member.username}">
                        <p> <input onclick="choose_file();" class="btn btn-primary" type="button" value="Change Profile Picture"/>
                            <a id="edit_bio_button" onclick="edit_bio_click();" class="btn btn-default" role="button">Edit Biography</a>
                            <input id="profilePicture" type="file" style="display: none;"/>
                        </p>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="col-sm-6 col-md-4">
            <h2>Followers(${fn:length(member.followers)})</h2>
            <c:forEach var="follower" items="${member.followers}">
                <a href="${contextPath}/profile/${follower.username}">${follower.username}</a>
                <br>
            </c:forEach>

        </div>

        <div class="col-sm-6 col-md-4">
            <h2>Followings(${fn:length(member.followedMembers)})</h2>
            <c:forEach var="followee" items="${member.followedMembers}">
               <a href="${contextPath}/profile/${followee.username}">${followee.username}</a>
                <br>
            </c:forEach>
        </div>

        <c:if test="${principal.username == member.username}">
            <div class="col-sm-offset-1 col-sm-5" role="group" style="margin-top:25%;">
                <button style="float:right;" type="button" class="btn btn-success tagbutton" id="tagbutton">Add</button>
                <input style="width:85%;" type="text" class="form-control tokenfield" id="tokenfield" placeholder="Add interested areas as tags..." />
            </div>
        </c:if>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">Subscribed Heritages</div>
        <div class="panel-body">
            <p> ${member.username} follows these heritages </p>
        </div>

        <c:forEach items="${member.followedHeritages}" var="heritage">
            <div class="row">
                <div class="col-xs-12" style="height:20px;"></div>
            </div>
            <div class="panel panel-success">
                <div class="panel-heading">
                    <div class="row">
                        <b>
                            <h class="panel-title" style="margin-left:0.5% " name="name" id="name">${heritage.name}</h>
                            <c:if test="${principal.username != member.username}">
                            <button style="float:right; margin-right:1%" type="button" class="btn btn-success followbutton"
                                    onclick="followHeritage(${heritage.id})" id="followbutton_${heritage.id}">Follow</button>
                            </c:if>
                        </b>
                    </div>
                </div>

                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-12">
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
                                <label for="postDate" class="col-sm-2 control-label">Date</label>

                                <div class="col-sm-10">
                                    <p name="postDate" id="postDate">
                                            ${heritage.postDate}
                                    </p>
                                </div>
                            </div>

                            <c:forEach var="media" items="${medias}">
                                <c:if test="${media.postOrHeritageId == heritage.id && media.postOrHeritage==true}">
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
                    </div>
                    <div class="row">
                        <label for="tags_${heritage.id}" class="col-sm-2 control-label">Tags</label>

                        <div class="col-sm-4" role="group">
                            <p id="tags_${heritage.id}">
                                <c:forEach items="${heritage.tags}" var="tag">
                                    <a href="${contextPath}/searchHeritageByTag/${tag.tagText}<c:if test="${tag.tagContext != null}">(${tag.tagContext})</c:if>">
                                        &lt;${tag.tagText}<c:if
                                            test="${tag.tagContext != null}">(${tag.tagContext})</c:if>&gt;
                                    </a>
                                </c:forEach>
                            </p>
                        </div>
                    </div>
                </div>


                <div class="panel-footer">
                    <div class="row">

                        <button type="button" style="float:right; margin-right: 0.5%;"
                                class="btn btn-default"
                                onclick="window.location.href='${contextPath}/show_posts/${heritage.id}'">
                            See Posts
                        </button>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>

    <div class="row">
        <div class="col-xs-12" style="height:40px;"></div>
    </div>
<%@ include file="/WEB-INF/pages/footer.jsp" %>