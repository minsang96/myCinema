
# 개요

2019년 하반기에 대학 동기들과 함께 독학하면서 진행한 첫 안드로이드 프로젝트이다. 백지 상태에서 결과물을 만들어 냈다는 점에 의의를 두고 있지만, 아쉬운 점이 있다면 개발 초기에는 사용자가 자주 검색해본 카테고리 위주의 영화를 추천해 주는 기능도 넣으려고 했지만 시간적 여유가 없어 단순히 좋아하는 장르로 선택한 장르의 영화를 랜덤으로 추천해주는 기능으로 변경했다는 점이 있고, 안드로이드 스튜디오를 다루는 것만 으로도 벅찬 나머지 영화 데이터 API를 사용하지 못하고 웹 크롤링으로 데이터를 끌어온 점도 많이 아쉬운 프로젝트였다.

This is a my first Android project that I processed while studying with my college classmates. It meant a lot to me that we could made a first Andriod project, but there is some regrets at the development. First is we wanted to add a function that recommends movies based on categories that users frequently searched for, but we couldnt develop that because we had no time, so we replaced that with recommending movies at random and user's features. Plus, it was our first Android project, so using Android Studio is so difficult to us, so we coudln't use movie data API and we used web crawling for using movie data. 

# 주요기능

## 구글 로그인 연동
[code](https://github.com/minsang96/myCinema/blob/master/app/src/main/java/com/example/swproject/MainActivity.java)

## Firebase를 활용한 Database 구축
[code](https://github.com/minsang96/myCinema/blob/master/app/src/main/java/com/example/swproject/SelectGenre.java)

## Google Map을 활용한 주변 영화관 위치 안내
[code](https://github.com/minsang96/myCinema/blob/master/app/src/main/java/com/example/swproject/MapsActivity.java)

## 영화의 상세정보 출력
[code](https://github.com/minsang96/myCinema/blob/master/app/src/main/java/com/example/swproject/DetailView.java)

## 영화 검색 기능
[code1](https://github.com/minsang96/myCinema/blob/master/app/src/main/java/com/example/swproject/Search_Result.java)
[code2](https://github.com/minsang96/myCinema/blob/master/app/src/main/java/com/example/swproject/SearchAdapter.java)

# Screenshots
<div>
<img width="250" src="https://user-images.githubusercontent.com/69743476/92450589-960e7480-f1f6-11ea-9d98-2dd76525c45a.jpg">
<img width="250" src="https://user-images.githubusercontent.com/69743476/92450741-c1915f00-f1f6-11ea-9bb5-d1a006533c65.jpg">
<img width="250" src="https://user-images.githubusercontent.com/69743476/92450633-a58dbd80-f1f6-11ea-944d-8cf06e2f6a19.jpg">
</div>
<div>
<img width="250" src="https://user-images.githubusercontent.com/69743476/92450647-a7f01780-f1f6-11ea-8890-fedced95018e.jpg">
<img width="250" src="https://user-images.githubusercontent.com/69743476/92450658-aaeb0800-f1f6-11ea-9528-92f6ec5d06e0.jpg">  
<img width="250" src="https://user-images.githubusercontent.com/69743476/92450660-ab839e80-f1f6-11ea-97f5-15cd6ad7aa09.jpg">
</div>
<div>  
<img width="250" src="https://user-images.githubusercontent.com/69743476/92450663-ab839e80-f1f6-11ea-8a65-29806f7104bb.jpg">  
<img width="250" src="https://user-images.githubusercontent.com/69743476/92450719-bb02e780-f1f6-11ea-856f-8cdb7ad761ef.jpg">
<img width="250" src="https://user-images.githubusercontent.com/69743476/92450655-a9b9db00-f1f6-11ea-805c-25562d4e8d75.jpg">
</div>
