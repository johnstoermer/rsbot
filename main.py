import time
import random
import pyautogui

USERNAME = ''
PASSWORD = ''

def login():
    try:
        existinguser_button = pyautogui.locateOnScreen('existinguser.png', grayscale = False)
        pyautogui.moveTo(existinguser_button[0] + existinguser_button[2]/2 + random.randint(-2, 2), existinguser_button[1] + existinguser_button[3]/2 + random.randint(-2, 2), duration=random.randint(100,200)*0.01)
        pyautogui.click()
        time.sleep(random.randint(50,100)*0.01)
        pyautogui.typewrite(USERNAME, interval=random.randint(15,25)*0.01)
        pyautogui.press('tab')
        pyautogui.typewrite(PASSWORD, interval=random.randint(15,25)*0.01)
        pyautogui.press('enter')
        while True:
            time.sleep(random.randint(100,200)*0.01)
            play_button = pyautogui.locateOnScreen('play.png', grayscale = False)
            if play_button != None:
                pyautogui.moveTo(play_button[0] + play_button[2]/2 + random.randint(-2, 2), play_button[1] + play_button[3]/2 + random.randint(-2, 2), duration=random.randint(25,50)*0.01)
                pyautogui.click()
                break
        time.sleep(random.randint(50,100)*0.01)
        compass_icon = pyautogui.locateOnScreen('compass.png', grayscale = False, confidence = 0.6)
        pyautogui.moveTo(compass_icon[0] + compass_icon[2]/2 + random.randint(-2, 2), compass_icon[1] + compass_icon[3]/2 + random.randint(-2, 2), duration=random.randint(100,200)*0.01)
    except:
        print('Cant Detect Login Screen!')

def enterPortal():
    count = 0
    while count < 100:
        count += 1
        nice = pyautogui.locateOnScreen('portal.png', grayscale = False, confidence = 0.6)
        print(nice)
        if nice != None:
            pyautogui.moveTo(nice[0] + nice[2]/2, nice[1] + nice[3]/2, duration=random.randint(100,200)*0.01)
            pyautogui.click()
            print('Found Portal')
            break
        else:
            print('Finding Portal...')
        random.randint(10,20)*0.01
    print('Portal Not Found.')

def checkInGame():
    while True:
        nice = pyautogui.locateOnScreen('sidebar.png', grayscale = False)
        if nice != None:
            break
        time.sleep(random.randint(1000,2000)*0.01)

def getOut():
    count = 0
    while count < 100:
        count += 1
        sara = pyautogui.locateOnScreen('sara.png', grayscale = False, confidence = 0.7)
        zammy = pyautogui.locateOnScreen('zammy.png', grayscale = False, confidence = 0.7)
        if sara != None:
            pyautogui.moveTo(sara[0] + sara[2]/2, sara[1] + sara[3]/2, duration=random.randint(100,200)*0.01)
            pyautogui.click()
            print('Found Sara Door')
            break
        elif zammy != None:
            pyautogui.moveTo(zammy[0] + zammy[2]/2, zammy[1] + zammy[3]/2, duration=random.randint(100,200)*0.01)
            pyautogui.click()
            print('Found Zammy Door')
            break
        else:
            print('Finding Door...')
        random.randint(10,20)*0.01
    print('Door Not Found.')

def Idle():
    while True:
        compass_icon = pyautogui.locateOnScreen('compass.png', grayscale = False, confidence = 0.6)
        pyautogui.moveTo(compass_icon[0] + compass_icon[2]/2 + random.randint(-2, 2), compass_icon[1] + compass_icon[3]/2 + random.randint(-2, 2), duration=random.randint(100,200)*0.01)
        pyautogui.click()
        #check if out of game
        nice = pyautogui.locateOnScreen('sidebar.png', grayscale = False)
        if nice == None:
            break
        time.sleep(random.randint(10000,20000)*0.01)

if __name__ == "__main__":
    login()
    while True:
        enterPortal()
        time.sleep(random.randint(100,200)*0.01)
        checkInGame()
        time.sleep(random.randint(100,200)*0.01)
        getOut()
        time.sleep(random.randint(100,200)*0.01)
        Idle()
