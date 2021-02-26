import sys
from ADS1256_definitions import *
from pipyadc import ADS1256

# coding=utf-8

POTI = POS_AIN0|NEG_AINCOM
# Light dependant resistor of the same board:
LDR  = POS_AIN1|NEG_AINCOM
# The other external input screw terminals of the Waveshare board:
EXT2, EXT3, EXT4 = POS_AIN2|NEG_AINCOM, POS_AIN3|NEG_AINCOM, POS_AIN4|NEG_AINCOM
EXT5, EXT6, EXT7 = POS_AIN5|NEG_AINCOM, POS_AIN6|NEG_AINCOM, POS_AIN7|NEG_AINCOM

# You can connect any pin as well to the positive as to the negative ADC input.
# The following reads the voltage of the potentiometer with negative polarity.
# The ADC reading should be identical to that of the POTI channel, but negative.
POTI_INVERTED = POS_AINCOM|NEG_AIN0

# For fun, connect both ADC inputs to the same physical input pin.
# The ADC should always read a value close to zero for this.
SHORT_CIRCUIT = POS_AIN0|NEG_AIN0

# Specify here an arbitrary length list (tuple) of arbitrary input channel pair
# eight-bit code values to scan sequentially from index 0 to last.
# Eight channels fit on the screen nicely for this example..
CH_SEQUENCE = (POTI, LDR, EXT2, EXT3, EXT4, EXT7, POTI_INVERTED, SHORT_CIRCUIT)
################################################################################

def do_measurement():
    ### STEP 1: Initialise ADC object:
    ads = ADS1256()

    ### STEP 2: Gain and offset self-calibration:
    ads.cal_self()
    ### STEP 3: Get data:
    raw_channels = ads.read_sequence(CH_SEQUENCE)
    voltages     = [i * ads.v_per_digit for i in raw_channels]

    ### STEP 4: DONE. Have fun!
    #print('Voltajes ')
    #print(voltages)
    return voltages[0],voltages[1]
   
# Start data acquisition
'''try:
    print("\033[2J\033[H") # Clear screen
    print(__doc__)
    print("\nPress CTRL-C to exit.")
    do_measurement()

except (KeyboardInterrupt):
    print("\n"*8 + "User exit.\n")
    sys.exit(0)'''