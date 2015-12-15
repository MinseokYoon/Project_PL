<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert title here</title>
</head>
<body>

   <div id="map" style="width: 100%; height: 250px;"></div>
   <br>
   <input type="text" name="text" id = "text" value = "군산시">
      <button onclick="getInfo()">지도 정보 보기</button>    <p id="infoDiv"></p>
      <br>
      
   <script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=82f964d04e941b6d7b36a70e2244f11d&libraries=services"></script>
      <script>
         var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
         mapOption = {
            center : new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level : 3
         };
               
         var map = new daum.maps.Map(mapContainer, mapOption);
         
         var geocoder = new daum.maps.services.Geocoder();
   
         var mapTypeControl = new daum.maps.MapTypeControl();
   
         map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);
   
         geocoder.addr2coord('군산시', function(status, result) {
            
            if (status === daum.maps.services.Status.OK) {
   
               var coords = new daum.maps.LatLng(result.addr[0].lat,
                     result.addr[0].lng);
   
               // 결과값으로 받은 위치를 마커로 표시합니다
               var marker = new daum.maps.Marker({
                  map : map,
                  position : coords
               });
   
               // 인포윈도우로 장소에 대한 설명을 표시합니다
               var infowindow = new daum.maps.InfoWindow({
                  content : '<div style="padding:5px;">우리회사</div>'
               });
               infowindow.open(map, marker);
            }
         });
      </script>
      <script type="text/javascript">
         function getInfo() {
            // 지도의 현재 중심좌표를 얻어옵니다 
            var center = map.getCenter();
      
            // 지도의 현재 영역을 얻어옵니다 
            var bounds = map.getBounds();
      
            var message = '위도는' + center.getLat() + '이고<br>';
            message += '경도는' + center.getLng() + '입니다. <br>';
      
            var infoDiv = document.getElementById('infoDiv');
            infoDiv.innerHTML = message;
         }
      </script>
</body>
</head>
</html>