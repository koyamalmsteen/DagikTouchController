
#include "Keyboard.h"

#define stopDagik 3
#define startDagik 4
#define stopCCV 5
#define startCCV 6
#define subtractBG 7
#define stopDTC 8
#define startDTC 9

void setup() {
  Keyboard.begin();
  pinMode(stopDagik, INPUT_PULLUP);
  pinMode(startDagik, INPUT_PULLUP);
  pinMode(stopCCV, INPUT_PULLUP);
  pinMode(startCCV, INPUT_PULLUP);
  pinMode(subtractBG, INPUT_PULLUP);
  pinMode(stopDTC, INPUT_PULLUP);
  pinMode(startDTC, INPUT_PULLUP);
}

void loop() {

  //
  // stopDagik
  //
  if(digitalRead(stopDagik) == LOW){
    Keyboard.press(KEY_LEFT_GUI);
    Keyboard.press('d');
    delay(100);
    Keyboard.releaseAll();
    
    Keyboard.press(KEY_LEFT_CTRL);
    Keyboard.press(KEY_LEFT_ALT);
    Keyboard.press('1');
    delay(100);
    Keyboard.releaseAll();

    while(digitalRead(stopDagik) == LOW);
  }

  //
  // startDagik
  //
  if(digitalRead(startDagik) == LOW){
    Keyboard.press(KEY_LEFT_GUI);
    Keyboard.press('d');
    delay(100);
    Keyboard.releaseAll();
    
    Keyboard.press(KEY_LEFT_CTRL);
    Keyboard.press(KEY_LEFT_ALT);
    Keyboard.press('2');
    delay(100);
    Keyboard.releaseAll();    

    while(digitalRead(startDagik) == LOW);
  }

  //
  // stopCCV
  // 
  if(digitalRead(stopCCV) == LOW){ 
    Keyboard.press(KEY_LEFT_GUI);
    Keyboard.press('d');
    delay(100);
    Keyboard.releaseAll();
    
    Keyboard.press(KEY_LEFT_CTRL);
    Keyboard.press(KEY_LEFT_ALT);
    Keyboard.press('q');
    delay(100);
    Keyboard.releaseAll();

    while(digitalRead(stopCCV) == LOW);
  }

  //
  // startCCV
  //
  if(digitalRead(startCCV) == LOW){
    Keyboard.press(KEY_LEFT_GUI);
    Keyboard.press('d');
    delay(100);
    Keyboard.releaseAll();
    
    Keyboard.press(KEY_LEFT_CTRL);
    Keyboard.press(KEY_LEFT_ALT);
    Keyboard.press('w');
    delay(100);
    Keyboard.releaseAll();    

    while(digitalRead(startCCV) == LOW);
  }

  //
  // subtractBG
  //
  if(digitalRead(subtractBG) == LOW){   
    Keyboard.press(KEY_LEFT_CTRL);
    Keyboard.press(KEY_LEFT_ALT);
    Keyboard.press('a');
    delay(100);
    Keyboard.releaseAll();

    while(digitalRead(subtractBG) == LOW);
   }

 //
 // stopDTC
 //
 if(digitalRead(stopDTC) == LOW){
    Keyboard.press(KEY_LEFT_GUI);
    Keyboard.press('d');
    delay(100);
    Keyboard.releaseAll();
    
    Keyboard.press(KEY_LEFT_CTRL);
    Keyboard.press(KEY_LEFT_ALT);
    Keyboard.press('z');
    delay(100);
    Keyboard.releaseAll();    

    while(digitalRead(stopDTC) == LOW);
  }

  //
  // startDTC
  //
  if(digitalRead(startDTC) == LOW){
    Keyboard.press(KEY_LEFT_GUI);
    Keyboard.press('d');
    delay(100);
    Keyboard.releaseAll();
    
    Keyboard.press(KEY_LEFT_CTRL);
    Keyboard.press(KEY_LEFT_ALT);
    Keyboard.press('x');
    delay(100);
    Keyboard.releaseAll();

    while(digitalRead(startDTC) == LOW);
  }

  delay(100);
}
