import time
from rpi_ws281x import *

strip = PixelStrip(1, 18, 80000, 10, False, 255, 0)
strip.begin()

strip.setPixelColor(1, Color(255, 255, 0))
strip.show()

time.sleep(3)

strip.setPixelColor(1, Color(255, 0, 0))
strip.show()

time.sleep(3)

strip.setPixelColor(1, Color(0, 0, 255))
strip.show()

while True:
    f = open("ledData.txt")
    toDo = int(f.read())

    print(toDo)

    if toDo == 1:
        strip.setPixelColor(1, Color(0,0,255))
    elif toDo == 2:
        strip.setPixelColor(1, Color(0,255,0))
    elif toDo == 3:
        strip.setPixelColor(1, Color(0,255,255))
    elif toDo == 4:
        strip.setPixelColor(1, Color(255,0,0))
    elif toDo == 5:
        strip.setPixelColor(1, Color(255,0,255))
    elif toDo == 6:
        strip.setPixelColor(1, Color(255,255,0))
    elif toDo == 7:
        strip.setPixelColor(1, Color(255,255,255))
    elif toDo == 0:
        strip.setPixelColor(1, Color(0,0,0))
    else:
        strip.setPixelColor(1,Color(255,0,0))

    f.close()
