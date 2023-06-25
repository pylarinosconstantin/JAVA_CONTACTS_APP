function initMap2() {
    const myLatLng = { lat: 43.7102, lng: 7.2620 };
    
    map = new google.maps.Map(document.getElementById("map"), {
      zoom: 12,
      center: myLatLng,
    });
  
    putpoint();
  }
  


  function putpoint() {
    const myLatLng = { lat: 43.7102, lng: 7.2620 };
    m = new google.maps.Marker({
      position: myLatLng,
      map: map,
    });
  
    $("#latitude").val(myLatLng.lat);
    $("#longitude").val(myLatLng.lng);

   
  
    map.addListener("click", (e) => {
      m.setMap(null);
      m = new google.maps.Marker({
        position: e.latLng,
        map: map,
      });
      $("#latitude").val(e.latLng.lat());
      $("#longitude").val(e.latLng.lng());
    });
  }
  


