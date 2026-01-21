-Notes Master
notes-master는 여러 노트 관련 프로젝트를 모아둔 멀티 노트 프로젝트 레포지토리입니다.

이 저장소에는 여러 노트 앱 소스 코드, 테스트 파일, 그리고 학습용 코드가 포함되어 있습니다.

-포함된 프로젝트
이 저장소에는 다음과 같은 주요 구성 요소가 있습니다:

notes/

노트 앱 관련 소스 코드가 들어 있는 폴더입니다.

React 기반 또는 다른 스택으로 구현된 노트 앱이 포함될 수 있습니다.

secure-notes-react/

-보안 중심 노트 앱 프로젝트 (별도 React 앱)

암호화/보안 기능을 가진 노트 저장 기능 구현

테스트 파일 및 샘플

JWT 테스트 컬렉션 (JWT 테스트.postman_collection.json)

백엔드 테스트용 Postman 파일

-기술 스택 (프로젝트별로 상이 가능)
각 노트 앱 프로젝트는 서로 다른 기술 스택을 사용할 수 있습니다:

프로젝트	기술 스택
notes	React, JavaScript
secure-notes-react	React, 암호화 라이브러리
backend	Java 또는 Node.js 기반백엔드 코드
(실제 패키지 구성에 따라 추가/변경해주세요)

- 시작하기
1. 저장소 클론
git clone https://github.com/seongjaebak2/notes-master.git
cd notes-master
2. 개별 프로젝트 실행
각 프로젝트 폴더마다 아래와 같은 방식으로 설치 및 실행할 수 있습니다.

- 예: notes 앱
cd notes
npm install
npm start
브라우저에서 http://localhost:3000 으로 확인하세요.

- 예: secure-notes-react 앱
cd secure-notes-react
npm install
npm start
이렇게 각 디렉터리에서 실행합니다.

- 스크립트 요약
각 프로젝트는 Create React App 또는 자체 스크립트를 사용합니다.

npm start — 개발 서버 실행

npm test — 테스트 실행

npm run build — 프로덕션 빌드

npm run eject — 설정 추출 (CRA 한정)

- 활용 팁
프로젝트별 README가 추가될 예정입니다.

각 노트 앱의 기능/설계/스크린샷을 추가하면 더 명확한 설명이 됩니다.

Github Pages, Netlify, Vercel 등을 통해 배포 가능합니다.

- 예시 폴더 구조
실제 구성은 다를 수 있습니다. 필요하면 자동으로 맞춰드립니다.

notes-master/
├─ notes/
│   ├─ src/
│   ├─ public/
│   └─ package.json
├─ secure-notes-react/
│   ├─ src/
│   ├─ public/
│   └─ package.json
├─ JWT 테스트.postman_collection.json
├─ 벡엔드 테스트.postman_collection.json
└─ README.md
