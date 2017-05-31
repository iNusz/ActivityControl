# ActivityControl
startActivityForResult - Activity 사이에서 값 주고받기

## startActivityForResult

이 함수로 Activity를 실행하면 실행된 Activity가 종료되면서 아래의 onActivityResult함수를 호출해준다 [소스코드전체보기](https://github.com/iNusz/ActivityControl/blob/master/app/src/main/java/com/seunghoshin/android/activitycontrol/MainActivity.java)

```java
// 액티비티를 실행하는 버튼을 구분하기 위한 플래그
final int BUTTON_START = 99;

Intent intent = new Intent(this, 서브.class);
startActivityForResult(intent, BUTTON_START);
```


## setResult

호출되는 서브.class 에 작성되는 코드       
```java

Intent intent = new Intent(); // 이미 생성되어 있는 Activity를 사용하기 때문에 Context를 필요로하지 않는다.
intent.putExtra("result", "결과값");

// RESULT_OK 는 부모Activity에 이미 정의되어 있는 플래그값으로 처리가 성공적이라는 것을 의미한다
// setResult 함수는 현재 Activity 에 Intent를 저장하기 때문에 18번줄에서 언급한바와 같이 Context 를 따로 필요로 하지 않는다.
setResult(RESULT_OK, intent);
```

## onActivityResult
```java
    // requestCode = startActivityForResult를 실행한 주체를 구분하기 위한 플래그
    // resultCode = 결과처리의 성공여부 | RESULT_OK = 성공
    // data = 돌려받은 값이 담겨있는 Intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case BUTTON_START:
                    Toast.makeText(this, "Start 버튼을 눌렀다가 돌아옴", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
```
