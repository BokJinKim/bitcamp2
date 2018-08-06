/*
※ 파라미터의 조합에 따라 아래와 같이 표현이 가능합니다.
- 지역별 관광정보 : 지역정보(필수) > 타입정보(선택) > 분류정보(선택) > 관광정보 목록
- 타입별 관광정보 : 타입정보(필수) > 지역정보(선택) > 분류정보(선택) > 관광정보 목록
- 분류별 관광정보 : 타입정보(필수) > 분류정보(선택) > 지역정보(선택) > 관광정보 목록
- 통합(키워드) 검색 : 지역정보(선택) > 타입정보(선택) > 분류정보(선택) > 검색된 정보 목록
- 내주변 관광정보 : 타입정보(선택) > 관광정보 목록
- 날짜별 행사축제 : 지역정보(선택) > 행사공연축제 목록
- 베니키아, 한옥, 굿스테이 숙박 검색 : 지역정보(선택) > 각 숙박 정보 목록
*/
$(function () {
    common.getMonth();
    draw.init();
    draw.elements();
})
var parsing = {
    test: function (data) {
        console.log(data)
    },
    dataParsing: function (data) {
        var list = data.response.body.items.item
        if (Array.isArray(list)) {
            if (list != undefined) {
                $.each(list, function (i, item) {
                    var InfoElements =
                    "<div class='box'>" +
                    "<div class='img_area'>" +
                    "<a href='view.html?id=" + item.contentid + "&item=" + item.contenttypeid + "&mapx="+ item.mapx +  "&mapy=" + item.mapy + "'>" +
                    "<span class='category'>축제</span>" +
                    "<img class=" + "'img-responsive'" + "src=" + "'" + item.firstimage + "'" + "onError=" + "this.onerror=null;this.src='../../img/common/no-image-icon.jpg';" + ">" +
        
                    "</a>" +
        
                    "</div>" +
        
        
                    "<div class='box_cont_area'>" +
                    "<a href='view.html?id=" + item.contentid + "&item=" + item.contenttypeid + "&mapx="+ item.mapx +  "&mapy=" + item.mapy + "'>" +
        
                    "<b>" + item.title + "</b>" +
        
        
        
                    "</a>" +
        
                    "<p class='location_category'>" + item.addr1 + "</p>" +
                    "<p class='hashtag'>" +
                    "<a href='#'>#전복김밥</a>" +
                    "<a href='#'>#통전복주먹밥</a>" +
                    "<a href='#'>#미역국</a>" +
        
                    "</p>" +
                    "<div class='icon_area'>" +
                    "<ul class='clear'>" +
                    "<li class='icon like_icon'>" +
                    "<a href='#'>" +
                    "<span>좋아요</span>" +
                    "<span class='like_count'>60</span>" +
                    "</a>" +
                    "</li>" +
                    "<li class='icon zzim_icon'>" +
                    "<a href='#'>" +
                    "<span>찜하기</span>" +
                    "<span class='zzim_count'>883</span>" +
                    "</a>" +
                    "</li>" +
                    "<li class='icon review_icon'>" +
                    "<a href='#'>" +
                    "<span>리뷰</span>" +
                    "<span class='review_count'>921</span>" +
        
                    "</a>" +
        
                    "</li>" +
                    "</ul>" +
                    "</div>" +
                    "</div>" +
        
                    "</div>"
                    $("#travelContents").append(InfoElements)
                })
            } else {
                $(".text-right").css("display", "none");
            }
        } else if (data.response.body.items === '') {
            $(".text-right").css("display", "none");
        } else {
            console.log(data)
            var infoElements =
                "<div class='col-xs-6 col-sm-6 col-md-4 col-lg-3'>" +
                "<a href='../cmm/detail.html?id=" + list.contentid + "&item=" + list.contenttypeid + "'" + " target='_blank'>" +
                "<div class='thumbnail'>" +
                "<img class=" + "'img-responsive'" + "src=" + "'" + list.firstimage + "'" + "onError=" + "this.onerror=null;this.src='../../img/common/no-image-icon.jpg';" + ">" +
                "<div class='caption text-center'>" +
                "<h5>" + list.title + "</h5>" +
                "<h6>" + list.addr1 + "</h6>" +
                "</div>" +
                "</div>" +
                "</a>" +
                "</div>"
            $("#travelContents").append(infoElements)

        }
    }
}

var draw = {

    elementCount: 0,
    areaCode: 0,
    sigunguCode: 0,

    init: function () {
        this.elementCount = 1;
        this.areaCode = '39';
        this.sigunguCode = '';
    },
    elements: function () {
        var date = common.getDate()
        common.getInfo('get', 'searchFestival', 'areaCode=' + this.areaCode + '&sigunguCode=' + this.sigunguCode + '&listYN=Y&MobileOS=ETC&MobileApp=AppTest&arrange=P&numOfRows=12&eventStartDate=' + date + '&pageNo=' + this.elementCount, parsing.dataParsing);
    },
    areaSigunguCodeGet: function () {
        common.getInfo('get', 'areaCode', 'numOfRows=50&MobileOS=ETC&MobileApp=test&areaCode=' + this.areaCode, common.areaDetailCodeParsing);
    }
}