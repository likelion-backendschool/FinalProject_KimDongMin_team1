# 🦁 멋사 백엔드 스쿨 1기 최종 프로젝트

```markdown
💡 종합 프로젝트를 통해서 만들어볼 서비스는 “멋북스”입니다.  
본 서비스는 eBook 마켓과 eBook리더로 이루어진 서비스입니다.

종합 프로젝트를 진행하며 아래와 같은 목표를 가지고 진행하시면 더더욱 도움이 됩니다. 

- 주어진 제약사항 내에서 최선의 기술적 판단을 합니다.
- 학습 했던 내용을 요구사항 기반으로 깊이 있게 개발해보며 복습 및 활용해봅니다.
- 유지보수와 작업효율을 고려하여 아키텍쳐 설계 및 라이브러리 선택을 진행해봅니다.
- 실제 백엔드와 프론트간의 상호작용이 어떻게 되는지 파악합니다.
```

## 🗂 레포지토리 구조 
- `[규칙1]` 깃허브 레포에는 프로젝트 디렉토리와 각 주차별 Mission Record 디렉토리 총 2개가 있어야 한다.
    - **프로젝트 디렉토리**
        - 디렉토리 이름 : `NWeek_Mission`
    - **미션 레코드 디렉토리** 
        - 디렉토리 이름 : `NWeek_Record`

- `[규칙2]` Mission Record는 아래 템플릿을 복사, 붙여넣기 하여 기록한다.
    
    **(클릭! ▼ ) Mission Record Template**
    
    [NWeek_OOO.md](https://www.notion.so/NWeek_OOO-md-9cdf4c2f01be4feb9594265f86fdf6c1)
    

**참고: [Git issue 와 commit 활용]**

- 매 주 구현해야하는 기능을 분리하여 이슈 단위로 관리한다.
- 개발 유형에 따라 commit 메시지에 본인이 어떤 작업을 진행하였는지를 포함하여 작성하는 형태로 프로젝트에 push 한다.
    - 기능 개발, 오류 수정, 코드 리팩토링과 같이 개발 유형을 분류하여 관리한다.

## ✨ Git 컨벤션

### Git 커밋 컨벤션
- [git 커밋 컨벤션](https://velog.io/@shin6403/Git-git-%EC%BB%A4%EB%B0%8B-%EC%BB%A8%EB%B2%A4%EC%85%98-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0)

#### 1. Commit 메세지 작성 방법
- **타입은 태그와 제목으로 구성되고, 태그는 영어로 쓰되 첫 문자는 대문자로 한다.** <br>
- **깃모지를 사용해서 어떤 유형의 작업인지 직관적으로 확인할 수 있게 작성한다.**

`태그: 제목` 형식으로 작성한다.

`Feat` : 새로운 기능 추가 <br> 
`Fix` : 버그 수정 <br>
`Docs` : 문서 수정 <br>
`Style` : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우 <br>
`Refactor` : 코드 리펙토링 <br>
`Test` : 테스트 코드, 리펙토링 테스트 코드 추가 <br>
`Chore` : 빌드 업무 수정, 패키지 매니저 수정 <br>

#### 😗 Git Emoji
- [Gitmoji 사용법 정리](https://inpa.tistory.com/m/entry/GIT-%E2%9A%A1%EF%B8%8F-Gitmoji-%EC%82%AC%EC%9A%A9%EB%B2%95-Gitmoji-cli)

|이모지|코드|설명|
|:-:|:-:|:-:| 
|✨|`:sparkles:`|새 기능|
|🎨|`:art:`|코드의 구조/형태 개선|
|🔥|`:fire:`|코드/파일 삭제|
|📝|`:memo:`|문서 추가/수정|
|💡|`:bulb:`|주석 추가/수정|
|🙈|`:see_no_evil:`|.gitignore 추가/수정|
|➕|`:heavy_plus_sign:`|의존성 추가|
|➖|`:heavy_minus_sign:`|의존성 제거|
|🔀|`:twisted_rightwards_arrows:`|브랜치 병합|
|💄|`:lipstick:`|UI/스타일 파일 추가/수정|
|🐛|`:bug:`|버그 수정|
|✅|`:white_check_mark:`|테스트 추가/수정|
|♻️|`:recycle:`|코드 리팩토링|

### Git 브랜치 전략
#### 1. 브랜치 이름 작성 방법
- 브랜치 이름은 작업 유형에 맞는 이름을 골라서 생성한다. <br>
  ex ) 회원 기능 개발 브랜치 → `feature/member`
#### 2. git-flow 전략을 사용해서 브랜치를 관리
[`우아한 형제들 - git-flow 전략 정리글`](https://techblog.woowahan.com/2553/)

`master` : 제품으로 출시될 수 있는 브랜치 <br>
`develop` : 다음 출시 버전을 개발하는 브랜치 <br>
`feature` : 기능을 개발하는 브랜치 <br>
`release` : 이번 출시 버전을 준비하는 브랜치 <br>
`hotfix` : 출시 버전에서 발생한 버그를 수정 하는 브랜치 <br>
