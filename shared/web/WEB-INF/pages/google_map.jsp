<%--
  Created by IntelliJ IDEA.
  User: gokcan
  Date: 01.12.2015
  Time: 01:12
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
<style>
    html, body {
        margin: 0;
        padding: 0;
        height: 100%;
    }
    #map{
        height: 80%;
        width: 60%;
    }
</style>

<script src="https://maps.googleapis.com/maps/api/js?callback=initMap" async defer></script>
<script type="text/javascript">
    var markers = [];
    function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 10,
            center: {lat: 40.731, lng: -73.997}
        });
        var geocoder = new google.maps.Geocoder;
        var infowindow = new google.maps.InfoWindow;

        google.maps.event.addListener(map, 'click', function(event) {
            addMarker(event.latLng, map);
            geocodeLatLng(geocoder, map, infowindow, event.latLng);
        });
    }

    function geocodeLatLng(geocoder, map, infowindow, latlng) {
        geocoder.geocode({'location': latlng}, function(results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                if (results[1]) {
                    //map.setZoom(11);

                    //infowindow.setContent(results[1].formatted_address);
                    //infowindow.open(map, marker);
                    console.log(results);
                    document.getElementById("latlng").value = latlng.toString();
                    var placeName;
                    level = results.length - Number(document.getElementById("infoLevel").value);
                    if(level < 0){
                        level = 0;
                    }
                    placeName = results[level].formatted_address;
                    document.getElementById("place_name").value = placeName;
                } else {
                    window.alert('No results found');
                }
            } else {
                window.alert('Geocoder failed due to: ' + status);
            }
        });
    }

    function addMarker(location, map) {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        markers = [];
        var marker = new google.maps.Marker({
            position: location,
            map: map
        });
        markers.push(marker);

    }
</script>
</head>
<body>

<div class="wrapper">
    <div id="map"></div>
    <div id="floating-panel">
        Geo location:<input style="width:250px;" disabled id="latlng" type="text" value="40.714224,-73.961452">
        &nbsp;
        Location Info Level
        <select id="infoLevel">
            <option value="1">Country</option>
            <option value="2">City</option>
            <option value="3">Country</option>
            <option value="4">District</option>
            <option value="5">Region</option>
            <option value="6">Road</option>
        </select> <span style="color: red;">Note: Not every location has detailed info such as road or region</span>
        <br>
        Location Description:
        <input id="place_name" type="text">
        <button id="submit" type="button"
                onclick="window.opener['place'] = document.getElementById('place_name').value;
                         window.opener.fillPlaceFromGoogleMap();
                         window.close();">
            Submit as Place
        </button>
    </div>
</div>
</body>
</html>
