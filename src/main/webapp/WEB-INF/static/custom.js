function clickBtn() {
    window.navigator.geolocation.getCurrentPosition(function (position) { //OK
            let lat = position.coords.latitude;
            let lnt = position.coords.longitude;

            document.getElementById('lat').value = lat.toFixed(7);
            document.getElementById('lnt').value = lnt.toFixed(7);
        },

        function (error) { //error
            let str = "오류 검출";
            switch (error.code) {
                case error.PERMISSION_DENIED:
                    str = "사용자 거부";
                    break;
                case error.POSITION_UNAVAILABLE:
                    str = "지리정보 없음";
                    break;
                case error.TIMEOUT:
                    str = "시간 초과";
                    break;
                case error.UNKNOWN_ERROR:
                    str = "알수없는 에러";
                    break;
            }
            document.getElementById('lat').innerHTML = str;
        });
}