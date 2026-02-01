## 🔥 프로젝트 설명

- 포트폴리오를 관리하기 위한 사이트 개발을 위한 backend Server
- 이력서, 포트폴리오를 회사별 관리를 지원

## 🧑‍🤝‍🧑 프로젝트 인원

1. 김동윤

2. 정혜인

3. 조현준
   * Fullstack
   * React 개발
     * 기본 프로젝트 설정
   * Spring 개발
     * Spring Boot 기본설정 및 Core 개발
     * Spring Boot Security 개발

## 🚀 Spring 적용내역


1. Swagger : OpenAPI를 이용한 Swagger를 적용하였다.
    * http://localhost:8080/swagger-ui/index.html
    * 해당 내용을 통해 RESTFul 웹 서비스를 만들 때 API 문서를 자동으로 만들어주고, 직접 테스트할 수 있는 UI를 제공한다.
<br/><br/>
2. Liquibase : 데이터베이스 형상관리를 위한 툴
    * Flyway와 Liquibase가 존재했으나, 사용하는 프로젝트의 수를 계산 후 정하게 되었다.
    * https://stackshare.io/stackups/flyway-vs-liquibase
<br/><br/>
3. Lombok : 롬복을 통한 getter, setter 자동화
<br/><br/>
4. log4j2 : 로그를 위한 설정 추가적용
<br/><br/>
5. PostgreSQL Database 적용

---

### 모든 커밋내역에서 민감정보 삭제 하였습니다.

1. git-filter-repo를 설치합니다.
   1. `brew install git-filter-repo `
2. 민감정보 내역을 입력합니다.
    1. sensitive.txt 파일을 만듭니다.
   2. 만든 파일 내 민감정보를 모두 입력합니다.
3. `git filter-repo --replace-text sensitive.txt`을 입력하여 민감정보 내역을 모두 삭제합니다.
4. git push origin --force --all 을 입력하여 해당 내용을 push합니다.
