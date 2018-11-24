## 멀티미디어

### MediaPlayer Class

- 오디오/ 비디오 재생을 담당한다.
- 데이터 소스 지정 방법
  - URL 지정
  - 프로젝트에 포함한 후 위치 지정
  - SD카드에 넣은 후 위치 지정
- 오디오 파일을 재생하는 과정
  1. setDataSource() 메소드를 이용하여 대상 파일의 위치를 지정 
  2. prepare() 메소드를 호출하여 재생을 준비 - 몇 프레임을 미리 읽어 들이고 정보를 확인
  3. start() 메소드를 호출하여 재생
-  비디오 파일을 재생하는 과정
  1. XML 레이아웃에 VideoView 태그를 추가
  2. setVideoURI() 메소드를 이용하여 동영상 재생
  3. 동영상 제어는 MediaController 객체를 setMediaController() 메소드로 설정 가능



### MediaRecorder Class

- 오디오 녹음/ 비디오 녹화를 담당한다.

- 오디오 녹음 과정

  1. MediaRecorder 객체 생성

  2. 오디오 입력 및 출력 형식 설정 - 

     - setAudioSource(...MIC) : 마이크를 통하여 입력
     - setOutputFormat(...MPGE_4) : MPGE4 포맷으로 지정

  3. 오디오 파일을 만들 때 필요한 인코더와 파일 이름 지정 

     - setAudioEncoder(...DEFAULT) : 인코더 설정
     - setOutputFile() : 결과물 파일을 설정(경로, 파일명)

  4. 녹음 시작 - 바이트 스트림 저장

     - prepare(), start() 메소드로 녹음 시작

     - stop(), release() 메소드로 녹음 중지 및 MediaRecorder 리소스 해제
     -  완성된 녹음 파일의 정보를 ContentValues 객체에 put(), insert()하여 추가

  5. 매니페스트에 권한 설정 - RECORD_AUDIO, WRITE(READ)_EXTERNAL_STORAGE

- 동영상 녹화 과정

  1. MediaRecorder 객체 생성
  2. SurfaceView, SurfaceHolder  객체를 생성 : 미리보기 및 제어 구현
  3. 비디오/오디오 입출력 형식 & 인코더 설정 (오디오 녹음 과정에 비디오 설정도 추가)
     - setVideoSource(), setVideoEncoder() 메소드 이용
  4. 녹화 및 저장
  5. 매니페스트에 권한 설정 - (오디오 녹음) + CAMERA