<img src="https://github.com/PiLab-CAU/OpenSourceProject-2401/assets/74255823/6e3fbecc-573f-4ca2-acbc-d82bd2713937" width="40%">

### [링크]
http://43.202.126.252:8080/

### [사용 설명]
ChatDa 서비스 이용 방법은 다음과 같습니다.

0. 메인 페이지에 접속하면 이름을 입력하고 대화를 시작할 수 있습니다.
1. 대화를 마치고 지금까지의 대화 내역을 바탕으로 생성된 일기를 확인할 수 있습니다.
2. 대화는 유저가 종료 버튼을 누르거나 일정 길이 이상으로 대화 기록이 쌓이면 종료됩니다.
3. 메인 페이지에서 캘린더 링크에 접속하여 일기가 저장될 캘린더 페이지를 확인할 수 있습니다.

아래의 예시 사진을 참고해 주세요!
---

### [예시 사진]
**0. 메인 화면**

<img src="https://github.com/OpenSource-SW-Project/ChatDa/assets/74255823/58700b5a-4664-42c9-88ce-5f62edc933da" width="60%">

<img src="https://github.com/OpenSource-SW-Project/ChatDa/assets/74255823/51aba597-1948-44b7-9f60-7307a07f3b09" width="60%">

---

**1. ChatDa 대화 화면**
<img src="https://github.com/PiLab-CAU/OpenSourceProject-2401/assets/74255823/30b867d6-872e-4a64-b26b-661484e9e55a" width="100%">

<img src="https://github.com/OpenSource-SW-Project/ChatDa/assets/74255823/2d9135c7-b3f2-416d-b203-3310e1a8489d" width="50%">

---
**2. 데모 버전 대화 제한**   [To be erased]

<img src="https://github.com/PiLab-CAU/OpenSourceProject-2401/assets/74255823/760d0763-8831-4c7f-a241-a4d6a1c44536" width="70%">

- 데모 버전에서는 최대 3개의 화제에 대해 10회 정도의 채팅으로 구성되어있습니다.
- 현 버전은 초기 버전으로 최종 구현에서는 더 다양한 화제에 대해 긴 대화를 나눌 것 입니다.
- 또한 사용자 특화된 대화는 생성되지 않습니다.   



---
**3. 일기 생성**
- 챗다와의 대화를 마친 후 일기 생성을 하기 위해서는 오른쪽의 **[대화를 마치고 일기 생성하기]** 버튼을 누르면 됩니다! 
<img src="https://github.com/PiLab-CAU/OpenSourceProject-2401/assets/74255823/b089d23e-8d45-4402-b74a-7ccf457b5994" width="50%">

- **"일기를 작성중입니다. 잠시만 기다려 주세요(30~40초)"** 문구의 팝업이 뜨면 [**확인**]을 누르고 30초 정도 기다려 주세요!
<img src="https://github.com/PiLab-CAU/OpenSourceProject-2401/assets/74255823/062a84ba-e1fb-4ab8-b3de-1024ec08865c" width="50%">

- 아래는 챗다와의 대화 후 생성된 일기의 예시입니다. 
- ~~일기의 문체와 문장 간의 연결 부분의 부자연스러움은 아직 보완이 필요해 보이며, 이는 추후에 보완될 예정입니다.~~

<img src="https://github.com/PiLab-CAU/OpenSourceProject-2401/assets/74255823/07419fce-578e-41c2-80ba-87e2d7035d57" width="95%">

---
**4. 일기 조회**

<img src="https://github.com/PiLab-CAU/OpenSourceProject-2401/assets/74255823/b1cdb950-a44d-438a-a1ad-6c105d3dae4a" width="50%">

- **[Diary]** 버튼을 누르면 캘린더 페이지로 이동합니다! 

<img src="https://github.com/PiLab-CAU/OpenSourceProject-2401/assets/74255823/f55fea84-1ff5-4d00-84f8-c2344a7f04b7" width="80%">

- 아직은 캘린더에서 챗다와의 대화를 통해 생성된 일기를 조회할 수 **없습니다**!!!     [To be erased]
- 생성된 일기 조회는 로그인 기능이 추가된 이후에 가능합니다!       [To be erased]

- Demo 캘린더에서 날짜 옆에 위치한 이모지를 클릭하면 예시 일기를 확인할 수 있습니다.
<img src="https://github.com/PiLab-CAU/OpenSourceProject-2401/assets/74255823/e0d05379-a016-4005-8154-1dd2ee166266" width="100%">
<img src="https://github.com/PiLab-CAU/OpenSourceProject-2401/assets/74255823/6ebc3841-fe1e-4b5a-b441-eb456b022a8a" width="100%">

---
### 구현 상황     [To be modified]
챗다 대화 알고리즘 ver1을 적용하였습니다.  

생성된 일기는 대화 종료 후 확인 할 수 있으며 이후 다시 확인하는 기능은 현재 구현하지 않았습니다.

최종 버전에서는 캘린더 페이지에서 사용자 별로 자신이 작성한 일기를 모두 확인 할 수 있습니다.
