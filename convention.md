### Git Commit Convention

Git commit convention, 즉 Git 커밋 메시지 규약은 협업 개발에서 커밋 메시지를 일관되게 작성하기 위한 규칙입니다. 이러한 규약을 따르면 커밋 메시지의 가독성과 이해도가 높아지며, 자동화 도구와
통합하기도 용이해집니다. 여러 가지 규약이 있지만, 가장 널리 사용되는 규약 중 하나는 [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/)
입니다.

Conventional Commits 규약의 기본 구조는 다음과 같습니다:

```
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

각 요소의 의미는 다음과 같습니다

1. **type**: 커밋의 종류를 나타냅니다. 보통 `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`, `build` 등으로 구분됩니다.
2. **optional scope**: 변경 사항이 적용된 범위를 나타냅니다. 예를 들어, 특정 모듈이나 컴포넌트를 지정할 수 있습니다.
3. **description**: 변경 사항을 간결하게 설명합니다.
4. **optional body**: 변경 사항에 대한 추가 설명을 제공합니다. 여러 줄로 작성할 수 있습니다.
5. **optional footer(s)**: 이슈 번호나 BREAKING CHANGE와 같은 중요한 추가 정보를 포함할 수 있습니다.

# Gitmoji Commit Guide

Gitmoji는 커밋 메시지에 이모지를 사용하여 변경 사항의 종류를 시각적으로 명확히 나타내는 방법입니다. 아래는 다양한 커밋 메시지에 사용할 수 있는 이모지와 그 의미입니다:

| 이모지 |              코드               |          설명           |
|:---:|:-----------------------------:|:---------------------:|
| 🎨  |            `:art:`            |      코드 구조/형식 개선      |
| 🐛  |            `:bug:`            |         버그 수정         |
| 🚑  |         `:ambulance:`         |        중요 핫픽스         |
|  ✨  |         `:sparkles:`          |       새로운 기능 도입       |
| 📝  |           `:memo:`            |       문서 추가/수정        |
| 🚀  |          `:rocket:`           |       배포 관련 작업        |
| 💄  |         `:lipstick:`          |       UI/스타일 수정       |
|  ✅  |     `:white_check_mark:`      |        테스트 추가         |
| 🔒  |           `:lock:`            |       보안 관련 작업        |
| 🚧  |       `:construction:`        |     작업 진행 중 (WIP)     |
| 💚  |        `:green_heart:`        |       CI 빌드 수정        |
| ⬇️  |        `:arrow_down:`         |      의존성 다운그레이드       |
| ⬆️  |         `:arrow_up:`          |       의존성 업그레이드       |
| 👷  |    `:construction_worker:`    |         CI 설정         |
| ♻️  |          `:recycle:`          |         리팩토링          |
|  ➕  |      `:heavy_plus_sign:`      |        의존성 추가         |
|  ➖  |     `:heavy_minus_sign:`      |        의존성 제거         |
| 🔧  |          `:wrench:`           |       설정 파일 수정        |
| 🔨  |          `:hammer:`           |      개발 스크립트/자동화      |
| 🌐  |   `:globe_with_meridians:`    |        국제화/지역화        |
| ✏️  |          `:pencil2:`          |        타이포 수정         |
| 💩  |           `:poop:`            |      나쁜 코드 (임시)       |
|  ⏪  |          `:rewind:`           |       변경 내용 되돌림       |
| 🔀  | `:twisted_rightwards_arrows:` |        브랜치 병합         |
| 🗑️ |        `:wastebasket:`        |   사용되지 않는 코드/파일 제거    |
| 💥  |           `:boom:`            |   대규모 변경 (비호환성 포함)    |
| 🍱  |           `:bento:`           | 에셋 추가/변경 (이미지, 아이콘 등) |
| 💡  |           `:bulb:`            |      주석 추가/업데이트       |
| 🍻  |           `:beers:`           |   협업 축하 (예: 머지 완료)    |
| 💬  |      `:speech_balloon:`       |     텍스트/문자열 추가/수정     |

세분화 된 emoji는 다음과 같습니다.

| 이모지 |              코드              |       설명        |
|:---:|:----------------------------:|:---------------:|
| 📌  |         `:pushpin:`          |    특정 버전 고정     |
| 🔖  |         `:bookmark:`         |      버전 태그      |
| 🎉  |           `:tada:`           |      초기 설정      |
| ⚡️  |           `:zap:`            |      성능 개선      |
| 🔥  |           `:fire:`           |    코드/파일 삭제     |
| 🚨  |      `:rotating_light:`      |    린트 경고 수정     |
| 📈  | `:chart_with_upwards_trend:` |   분석/추적 코드 추가   |
| 👽  |          `:alien:`           | 외부 API와의 호환성 수정 |
| 🚚  |          `:truck:`           |    파일/폴더 이동     |
|  ♿  |        `:wheelchair:`        |     접근성 향상      |
| 🛂  |     `:passport_control:`     |   권한/역할 관련 작업   |
| 🩹  |     `:adhesive_bandage:`     |     간단한 수정      |
| 🧐  |       `:monocle_face:`       |    코드 검사/검토     |

### Git 커밋 메시지 예시

1. **feat(auth): add JWT authentication**
    - 사용자의 인증을 JWT를 통해 처리하도록 기능 추가

2. **fix(api): correct user data endpoint response**
    - 사용자 데이터 엔드포인트의 응답을 수정

3. **docs(readme): update installation instructions**
    - README 파일의 설치 지침을 업데이트

4. **style(button): improve button styling**
    - 버튼의 스타일을 개선

5. **refactor(core): clean up code in main module**
    - 메인 모듈의 코드 정리

6. **test(auth): add unit tests for login function**
    - 로그인 함수에 대한 유닛 테스트 추가

7. **chore(deps): update dependency versions**
    - 의존성 버전 업데이트

8. **build(webpack): add support for CSS modules**
    - CSS 모듈을 지원하도록 Webpack 설정 변경

9. **fix(ui): resolve layout issue on mobile devices**
    - 모바일 장치에서의 레이아웃 문제 해결

10. **feat(payment): integrate Stripe payment gateway**
    - Stripe 결제 게이트웨이 통합

11. **docs(api): document new user endpoint**
    - 새로운 사용자 엔드포인트 문서화

12. **style(header): fix header alignment**
    - 헤더 정렬 문제 수정

13. **refactor(auth): extract authentication middleware**
    - 인증 미들웨어를 추출하여 개선

14. **test(ui): add end-to-end tests for dashboard**
    - 대시보드에 대한 엔드 투 엔드 테스트 추가

15. **chore(ci): configure GitHub Actions for CI**
    - CI를 위한 GitHub Actions 설정

16. **build(webpack): optimize build performance**
    - 빌드 성능 최적화

17. **fix(auth): handle expired tokens**
    - 만료된 토큰 처리 수정

18. **feat(user): implement user profile management**
    - 사용자 프로필 관리 기능 구현

19. **docs(changelog): update changelog for version 1.2.0**
    - 1.2.0 버전의 변경 로그 업데이트

20. **style(forms): improve form field spacing**
    - 폼 필드 간격 개선

21. **refactor(api): simplify request handling**
    - 요청 처리 로직 단순화

22. **test(api): mock API responses for testing**
    - 테스트를 위한 API 응답 모킹

23. **chore(lint): add linting rules for TypeScript**
    - TypeScript에 대한 린팅 규칙 추가

24. **build(docker): create Dockerfile for production**
    - 프로덕션을 위한 Dockerfile 생성

25. **fix(notification): correct notification icon display**
    - 알림 아이콘 표시 수정

26. **feat(search): add full-text search capability**
    - 전체 텍스트 검색 기능 추가

27. **docs(contributing): update contribution guidelines**
    - 기여 가이드라인 업데이트

28. **style(nav): adjust navigation bar colors**
    - 내비게이션 바 색상 조정

29. **refactor(auth): improve password hashing**
    - 비밀번호 해싱 개선

30. **chore(release): prepare for release 1.3.0**
    - 1.3.0 버전 릴리스를 준비
