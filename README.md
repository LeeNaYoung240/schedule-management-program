# 📅 투두 앱 백엔드 서버

해당 프로그램은 **일정 관리 앱 백엔드 서버**입니다.<br/>

이 프로그램은 **회원가입(User/Admin)** 과 **로그인**을 진행한 후 일정을 작성(등록), 조회, 수정, 삭제할 수 있으며, 선택한 일정에 댓글을 등록, 수정, 삭제할 수 있는 기능을 제공합니다.

해당 앱 서버는 IntelliJ를 사용하여 개발되었으며, 백엔드 데이터베이스로 MySQL을 사용합니다. 

각 기능은 RESTful API를 통해 구현되어 있으며, 클라이언트는 HTTP 요청을 통해 서버와 상호작용합니다. 데이터의 보안을 위해 일정 수정 및 삭제 시 비밀번호 인증을 도입하여, 사용자만이 자신의 일정을 수정하거나 삭제할 수 있도록 설계되었습니다. 또한 JWT를 이용한 인증/인가가 완료된 후에만 게시글과 댓글을 작성할 수 있습니다.

<br>

## 🗳 Tech Stack
-   **언어**  : Java
-   **버전** : JDK17
-   **Tools** : GitHub, Git
-   **IDE** : IntelliJ IDEA
-   **DB**: MySQL 8.0.28
-   **Framework** : SpringBoot 3.1.11

<br>

## 🌠 Features

- 모든 기능은 회원가입 후 로그인을 진행한 뒤 인증이 완료되어야 진행이 가능합니다. **jwt token** 을 사용하였으며 로그인 후 반한된 Headers의 **Key : Authorization, Value : token** 값을 요청 Headers의 **Authorization**을 추가한 뒤 토큰을 입력해야 정상 작동합니다.

1. **일정 작성(등록)** ✏

    - 사용자는 제목, 내용, 담당자, 비밀번호, 작성일을 입력하여 새로운 일정을 생성할 수 있습니다. 생성된 일정 정보는 비밀번호를 제외하고 반환되며, 이를 통해 사용자에게 일정이 성공적으로 등록되었음을 확인할 수 있습니다.

2. **선택한 일정 조회** 📑

    - 사용자는 특정 일정을 선택하여 해당 일정의 상세 정보를 조회할 수 있습니다. 이 기능을 통해 사용자는 등록된 일정의 내용을 확인할 수 있습니다.
  
3. **일정 목록 조회** 🧾

    - 사용자는 등록된 전체 일정을 조회할 수 있습니다. 이 기능은 작성일 기준 내림차순으로 정렬된 일정 목록을 반환하여, 사용자가 최신 일정을 쉽게 확인할 수 있도록 합니다.
  
4. **선택한 일정 수정** 🔨

    - 사용자는 특정 일정의 할일 제목, 할일 내용, 담당자를 수정할 수 있습니다. 수정 요청 시 일정의 비밀번호를 함께 보내야 하며, 서버는 비밀번호가 일치하는 경우에만 수정을 허용합니다. 수정이 완료되면 업데이트된 일정 정보가 비밀번호를 제외하고 반환됩니다.
  
5. **선택한 일정 삭제** 💣

     - 사용자는 특정 일정을 삭제할 수 있습니다. 삭제 요청 시에도 일정의 비밀번호를 함께 보내야 하며, 비밀번호가 일치하는 경우에만 삭제가 가능합니다.
  
6. **댓글 작성(등록)** ✏

     - 사용자가 선택한 일정이 있다면 댓글 내용, 사용자 이름를 입력하여 댓글을 등록할 수 있습니다. 생성된 댓글 정보는 고유번호, 댓글 내용, 사용자 이름, 날짜를 반환하며 이를 통해 사용자에게 댓글이 성공적으로 동륵되었음을 확인할 수 있습니다.
  
7. **댓글 수정** 🔨

    - 사용자는 선택한 일정의 댓글을 수정할 수 있습니다. 댓글 내용만 수정이 가능하며 수정한 댓글 정보는 고유번호, 댓글 내용, 사용자 이름, 날짜를 반환하며 이를 통해 사용자에게 댓글이 성공적으로 동륵되었음을 확인할 수 있습니다.

8. **댓글 삭제** 💣

    - 사용자는 선택한 일정의 댓글을 삭제할 수 있습니다. 댓글 삭제 성공 시 msg와 statusCode를 같이 반환하여 사용자가 정상적으로 삭제되었음을 확인할 수 있습니다.
    
<br>

<details>
<summary>(입문) - Commits on May 20, 2024 </summary>
<div markdown="1">
  
## 🖼 Use Case Diagram

![image](https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/c471c73f-4449-4c0f-91e5-46df6a3898f3)

<br>

## 🧬 ERD DIAGRAM

![image](https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/1fd0153f-de24-4475-be91-fdd47c5a474f)

<br>

## 🔨 API 명세서

![image](https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/e4799c98-48ef-4cff-a594-8b063be60f24)

<br>

## 📸 video

<details>
<summary>일정 작성(등록)</summary>
<div markdown="1">
  
https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/b09d7d76-4c25-43c7-8124-f28d7bad530a

</div>
</details>

<details>
<summary>선택한 일정 조회</summary>
<div markdown="1">
  
https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/1e171676-0bf2-49ae-8445-4e78741c79aa

</div>
</details>

<details>
<summary>일정 목록 조회</summary>
<div markdown="1">
  
https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/f9d26eb7-ac5c-427b-a035-92408f4ed496

</div>
</details>

<details>
<summary>선택한 일정 수정</summary>
<div markdown="1">
  
https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/967e31d6-58ae-4afb-9a08-893b3429e2f3

</div>
</details>

<details>
<summary>선택한 일정 삭제</summary>
<div markdown="1">
  
https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/ae18a9cd-3dd8-4a88-8474-49f1a9a835ee

</div>
</details>

<br>

## 🩹 개선 사항

<details>
<summary>개선 사항</summary>
<div markdown="1">

![image](https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/3b38de04-20ae-4fc9-9b47-ad3a749ea8f7)

- JPA Auditing 적용하여 date 자동으로 저장되게 변환

</div>
</details>

</div>
</details>

<details>
<summary>(숙련) - Commits on May 30, 2024 </summary>
<div markdown="1">

## 🖼 Use Case Diagram

![image](https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/560a5181-5df6-4b28-a0a2-3e91ce14d436)

<br>

## 🧬 ERD DIAGRAM

![image](https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/aaa9ad03-7978-4867-a2a1-06fa9f239c19)

<br>

## 🔨 API 명세서
<details>
<summary>회원가입</summary>
<div markdown="1">
    
 ![image](https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/2e0dfc00-6699-45fc-bd99-1ba64fab481b)

</div>
</details>

<details>
<summary>로그인</summary>
<div markdown="1">
    
![image](https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/579d60ce-4581-4670-98cd-b0e39f096589)


</div>
</details>

<details>
<summary>게시글(일정)</summary>
<div markdown="1">
    
![image](https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/455640a4-8ac7-4e25-a141-2e298bfef356)

</div>
</details>

<details>
<summary>댓글</summary>
<div markdown="1">
    
![image](https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/47375d87-e7a8-418a-b6e0-73248283f0c3)

</div>
</details>

<br>

## 📸 video

<details>
<summary>회원가입</summary>
<div markdown="1">
  

https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/21e82819-083a-4167-b3ef-ebe7b9fed0f4


</div>
</details>

<details>
<summary>로그인</summary>
<div markdown="1">
  

https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/7718d34c-ec51-48e7-97af-ad4c6e5f9f92


</div>
</details>

<details>
<summary>인증</summary>
<div markdown="1">
  

https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/6b47de35-2a93-4f82-8b4e-56320f7dade7


</div>
</details>

<details>
<summary>일정 등록</summary>
<div markdown="1">
  

https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/b08d53e0-ba0f-43f5-a96f-a7629695a6fd


</div>
</details>

<details>
<summary>댓글 작성(등록)</summary>
<div markdown="1">
  

https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/0615b40d-4ebc-48ce-9829-ad69e5483042


</div>
</details>

<details>
<summary>댓글 수정</summary>
<div markdown="1">
  

https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/83a35e30-57f3-4ed7-a968-1142a03a24c2


</div>
</details>

<details>
<summary>댓글 삭제</summary>
<div markdown="1">
  


https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/2f78d5b9-6808-4b66-b085-fb2a6e50667f



</div>
</details>


