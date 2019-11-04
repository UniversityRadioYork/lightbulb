  _      _____ _____ _    _ _______ ____  _    _ _      ____  
 | |    |_   _/ ____| |  | |__   __|  _ \| |  | | |    |  _ \ 
 | |      | || |  __| |__| |  | |  | |_) | |  | | |    | |_) |
 | |      | || | |_ |  __  |  | |  |  _ <| |  | | |    |  _ < 
 | |____ _| || |__| | |  | |  | |  | |_) | |__| | |____| |_) |
 |______|_____\_____|_|  |_|  |_|  |____/ \____/|______|____/ 
                                                              
                                                              
LED's Immersiv Glowing However Totally Bodged with Unattractive but Loved Box

Third (I think) attempt at the LEDs in Studio Red

Written by Michael Grace, URY Computing, October/November 2019


+++ HARDWARE +++

Code runs on Raspberry Pi in the box, in the corner of Studio Red. GPIO Pin 18 (PWM) plugs into the Arduino, input pin 12, and immediately outputed through pin 2. The Arduino acts as a level-up from the RPi's 3.3V to the LED's 5V.

Don't ask me too much else about the wiring in the box. Its not mine.


+++ SOFTWARE +++

Main file, uryled2.java. This runs in a loop, working through functions to determine what colour to do.
Once a colour has been decided, it continues to the next loop iteration. The out.updateLights file adds pauses of a few seconds in.
The uryled2 file calls functions from modules in the dataIn directory, including connecting to the APIs to get data. uryled2 sends its output to updateLights.java in the out directory, which writes the value to the ledData.txt file, which the Python script leds.py picks up, because the module was available for Python. This sends signals to the GPIO pin.


+++ WEB SERVER +++

There is a node file to run, which should interface with the plugin in SIS, allowing the presenters to pick the colour they want during the show. The html files are here too. frametest.html isn't anything important, just as a reminder of the implementation


+++ REMARK +++

Should the whole thing have been done in Node? - Yes
Was it? - No. Clearly. Deal with It.

-Michael
