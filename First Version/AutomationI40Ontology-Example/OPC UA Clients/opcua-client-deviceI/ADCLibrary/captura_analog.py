import sys
from ADS1256_definitions import *
from pipyadc import ADS1256
import requests


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

###Porcentaje de banda muerta
PORCENTAJE_DB=5.0
BANDA_CAMBIO=0.2

## Valores anteriores para la banda muerta
in0_vAnt=0
in1_vAnt=0
in2_vAnt=0
in3_vAnt=0
corrienteSensor_vAnt=0

def do_measurement():
    ### STEP 1: Initialise ADC object:
    ads = ADS1256()

    ### STEP 2: Gain and offset self-calibration:
    ads.cal_self()

    while True:
        ### STEP 3: Get data:
        global in0_vAnt
        global in1_vAnt
        global in2_vAnt
        global in3_vAnt
        global corrienteSensor_vAnt
        raw_channels = ads.read_sequence(CH_SEQUENCE)
        voltages     = [i * ads.v_per_digit for i in raw_channels]

        ### STEP 4: DONE. Have fun!
        #nice_output(raw_channels, voltages)
        #print('Voltajes ')
        #print(voltages)
        ### Almacenar la informacion de los datos en el servidor:
        almacenarVariable(voltages[0],in0_vAnt,'voltaje_potenciometro','nodo_prueba_ADC')
        almacenarVariable(voltages[1],in1_vAnt,'voltaje_LDR','nodo_prueba_ADC')
        almacenarVariable(voltages[2],in2_vAnt,'voltaje_potenciometro_externo','nodo_prueba_ADC')
        almacenarVariableBM(voltages[3],in3_vAnt,'voltaje_sensor_corriente','nodo_prueba_ADC',0.01)
        corrienteSensor=voltages[3]*12.0-30.0
        almacenarVariableBM(corrienteSensor,corrienteSensor_vAnt,'corriente','sensor_corriente_1',0.02)
        
        ### Valores anteriores para la banda muerta
        in0_vAnt=voltages[0]
        in1_vAnt=voltages[1]
        in2_vAnt=voltages[2]
        in3_vAnt=voltages[3]
        corrienteSensor_vAnt=corrienteSensor


#############################################################################
# Format nice looking text output:


def nice_output(digits, volts):
    sys.stdout.write(
          "\0337" # Store cursor position
        +
"""
These are the raw sample values for the channels:
Poti_CH0,  LDR_CH1,     AIN2,     AIN3,     AIN4,     AIN7, Poti NEG, Short 0V
"""
        + ", ".join(["{: 8d}".format(i) for i in digits])
        +
"""

These are the sample values converted to voltage in V for the channels:
Poti_CH0,  LDR_CH1,     AIN2,     AIN3,     AIN4,     AIN7, Poti NEG, Short 0V
"""
        + ", ".join(["{: 8.3f}".format(i) for i in volts])
        + "\n\033[J\0338" # Restore cursor position etc.
    )


def almacenarVariable(vNuevo,vAnt,variable,equipo):
    #bandaSup=vAnt*((100.0+PORCENTAJE_DB)/100.0)
    bandaSup=vAnt+BANDA_CAMBIO
    #bandaInf=vAnt*((100.0-PORCENTAJE_DB)/100.0)
    bandaInf=vAnt-BANDA_CAMBIO
    if(vNuevo>=bandaSup or vNuevo<=bandaInf):
        vNuevo=round(vNuevo,3)
        r = requests.post('http://192.168.137.100:5000/eiot', data = {'valor':vNuevo,'id_disp':equipo,'var':variable})
        #urlStr='curl http://192.168.137.100:5000/eiot -d "valor=27.6&id_disp=sensor_corriente_1&var=corriente" -X POST -v'
        print('Registro almacenado: Valor=' + str(vNuevo) + ', dispositivo=' + str(equipo) + ', variable=' + str(variable))
    
def almacenarVariableBM(vNuevo,vAnt,variable,equipo,banda_muerta):
    #bandaSup=vAnt*((100.0+banda_muerta)/100.0)
    bandaSup=vAnt+banda_muerta
    #bandaInf=vAnt*((100.0-banda_muerta)/100.0)
    bandaInf=vAnt-banda_muerta
    if(vNuevo>=bandaSup or vNuevo<=bandaInf):
        vNuevo=round(vNuevo,3)
        r = requests.post('http://192.168.137.100:5000/eiot', data = {'valor':vNuevo,'id_disp':equipo,'var':variable})
        #urlStr='curl http://192.168.137.100:5000/eiot -d "valor=27.6&id_disp=sensor_corriente_1&var=corriente" -X POST -v'
        print('Registro almacenado: Valor=' + str(vNuevo) + ', dispositivo=' + str(equipo) + ', variable=' + str(variable))

# Start data acquisition
try:
    print("\033[2J\033[H") # Clear screen
    print(__doc__)
    print("\nPress CTRL-C to exit.")
    do_measurement()

except (KeyboardInterrupt):
    print("\n"*8 + "User exit.\n")
    sys.exit(0)

    
