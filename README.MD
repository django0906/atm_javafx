** JavaFX ATM **

* JavaFX로 ATM을 구축. 알고리즘 자체는 매우 단순하니 코딩로직을 참조.
* JavaFX의 문법을 익히는데 주요 목적을 두었다.

---

##24th. Dec 2017

1. 기본적으로 짠 요소들은 냅둠
2. 첫 커밋.

---

##MainApp: 메인앱

* model: Account 모델
* util: DB접속 유틸리티
* view:
1-1 AccountDetailController: 계좌정보 상세를 보여주는 컨트롤러
1-2 AccountDetailDialog: 계좌정보 상세 다이얼로그

2-1 CreateAccountController: 계좌등록 컨트롤러
2-2 CreateAccountDialog: 계좌등록 다이얼로그

3-1 LoginController: 로그인 컨트롤러
3-2 LoginDialog: 로그인 다이얼로그

4-1 TermProjectMainController: 메인화면 컨트롤러
4-2 TermProjectMain: 메인화면 다이얼로그