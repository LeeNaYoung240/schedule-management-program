# 📅 일정 관리 앱 서버

해당 프로그램은 **일정 관리 앱 서버**입니다.<br/>

이 프로그램은 본인이 일정 작성(등록), 조회, 수정, 삭제의 기능을 할 수 있는 프로그램입니다. 

해당 앱 서버는 IntelliJ를 사용하여 개발되었으며, 백엔드 데이터베이스로 MySQL을 사용합니다. 
각 기능은 RESTful API를 통해 구현되어 있으며, 클라이언트는 HTTP 요청을 통해 서버와 상호작용합니다. 데이터의 보안을 위해 일정 수정 및 삭제 시 비밀번호 인증을 도입하여, 사용자만이 자신의 일정을 수정하거나 삭제할 수 있도록 설계되었습니다.

<br>

## 🗳 Tech Stack
-   **언어**  : Java
-   **버전** : JDK17
-   **Tools** : GitHub, Git
-   **IDE** : IntelliJ IDEA
-   **DB**: MySQL 8.0.37
-   **Framework** : SpringBoot 3.2.5

<br>

## 🌠 Features

1. **일정 작성(등록)**

    - 사용자는 제목, 내용, 담당자, 비밀번호, 작성일을 입력하여 새로운 일정을 생성할 수 있습니다. 생성된 일정 정보는 비밀번호를 제외하고 반환되며, 이를 통해 사용자에게 일정이 성공적으로 등록되었음을 확인할 수 있습니다.

2. **선택한 일정 조회**

    - 사용자는 특정 일정을 선택하여 해당 일정의 상세 정보를 조회할 수 있습니다. 이 기능을 통해 사용자는 등록된 일정의 내용을 확인할 수 있습니다.
  
3. **일정 목록 조회**

    - 사용자는 등록된 전체 일정을 조회할 수 있습니다. 이 기능은 작성일 기준 내림차순으로 정렬된 일정 목록을 반환하여, 사용자가 최신 일정을 쉽게 확인할 수 있도록 합니다.
  
4. **선택한 일정 수정**

    - 사용자는 특정 일정의 할일 제목, 할일 내용, 담당자를 수정할 수 있습니다. 수정 요청 시 일정의 비밀번호를 함께 보내야 하며, 서버는 비밀번호가 일치하는 경우에만 수정을 허용합니다. 수정이 완료되면 업데이트된 일정 정보가 비밀번호를 제외하고 반환됩니다.
  
5. **선택한 일정 삭제**

     - 사용자는 특정 일정을 삭제할 수 있습니다. 삭제 요청 시에도 일정의 비밀번호를 함께 보내야 하며, 비밀번호가 일치하는 경우에만 삭제가 가능합니다.     
<br>


## 🖼 Use Case Diagram

![image](https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/c471c73f-4449-4c0f-91e5-46df6a3898f3)


## 🧬 ERD DIAGRAM

![image](https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/1fd0153f-de24-4475-be91-fdd47c5a474f)



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

</div>
</details>

<details>
<summary>선택한 일정 수정</summary>
<div markdown="1">
  

https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/967e31d6-58ae-4afb-9a08-893b3429e2f3



</div>
</details>

</div>
</details>

<details>
<summary>선택한 일정 삭제</summary>
<div markdown="1">
  


https://github.com/LeeNaYoung240/schedule-management-program/assets/107848521/ae18a9cd-3dd8-4a88-8474-49f1a9a835ee




</div>
</details>



