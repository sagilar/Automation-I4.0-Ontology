import RPi.GPIO as GPIO
import threading
from threading import Thread
import queue
import sys
import json
sys.path.insert(0, "..")
import logging
import time
import random

GPIO.setmode(GPIO.BCM)

GPIO.setwarnings(False)

pins = [5,6,7,8]
pins_out = [9,10,11,17,22,23,27]


def IO_event(channel):
    #print("event in channel " + str(channel))
    if(channel == 5):
        print("Running Perform function")
        
        if GPIO.input(channel):
            #time.sleep(1)
            nodeDeviceII_performOk.set_value(True)
        else:
            #time.sleep(1)
            nodeDeviceII_performOk.set_value(False)
    if(channel == 6):
        if GPIO.input(channel):
            #nodeConvBelt_p1.set_value(not nodeConvBelt_p1.get_value())
            nodeConvBelt_p1.set_value(True)
        else:
            nodeConvBelt_p1.set_value(False)
    if(channel == 7):
        if GPIO.input(channel):
            #nodeConvBelt_p2.set_value(not nodeConvBelt_p2.get_value())
            nodeConvBelt_p2.set_value(True)
        else:
            nodeConvBelt_p2.set_value(False)
    if(channel == 8):
        if GPIO.input(channel):
            #nodeConvBelt_pf.set_value(not nodeConvBelt_pf.get_value())
            nodeConvBelt_pf.set_value(True)
        else:
            nodeConvBelt_pf.set_value(False)
    
            

try:
    from IPython import embed
except ImportError:
    import code

    def embed():
        vars = globals()
        vars.update(locals())
        shell = code.InteractiveConsole(vars)
        shell.interact()


from opcua import Client
from opcua import ua

## callbacks for states
def state_change_event(state_num,state_val):
    #pins_out = [9,10,11,17,22,23,27]
    if(state_num == 0):
        if(state_val):
            GPIO.output(9, True)
            #print("State 0 active")
        else:
            GPIO.output(9, False)
            #print("State 0 not active")
    elif(state_num == 1):
        if(state_val):
            GPIO.output(10, True)
            #print("State 1 active")
        else:
            GPIO.output(10, False)
            #print("State 1 not active")
    elif(state_num == 2):
        if(state_val):
            GPIO.output(11, True)
            #print("State 2 active")
        else:
            GPIO.output(11, False)
            #print("State 2 not active")
    elif(state_num == 3):
        if(state_val):
            GPIO.output(17, True)
            #print("State 3 active")
        else:
            GPIO.output(17, False)
            #print("State 3 not active")
    elif(state_num == 4):
        if(state_val):
            GPIO.output(22, True)
            #print("State 4 active")
            devI_pmd_value = nodeDeviceI_pieceMaterialData.get_value()
            #print(devI_pmd_value)
            try:
                dict_pmd = json.loads(devI_pmd_value)
                print('---------------------------------')
                print(dict_pmd)
                metal = dict_pmd['metallic']
                print('metal: ' + str(metal))
                print('---------------------------------')
                if metal == 'true':
                    output_activation(nodeConvBelt_mgate,True)
                else:
                    output_activation(nodeConvBelt_nmgate,True)
            except:
                pass
            nodeConvBelt_nmgate.set_value(False)
            nodeConvBelt_mgate.set_value(False)
        else:
            GPIO.output(22, False)
            output_activation(nodeConvBelt_mgate,False)
            output_activation(nodeConvBelt_nmgate,False)
            #print("State 4 not active")
    elif(state_num == 5):
        if(state_val):
            GPIO.output(23, True)
            #print("State 5 active")
        else:
            GPIO.output(23, False)
            #print("State 5 not active")
    elif(state_num == 6):
        if(state_val):
            GPIO.output(27, True)
            #print("States 6 & 7 active")
        else:
            GPIO.output(27, False)
            #print("States 6 & 7 not active")

def output_activation(node,value):
    node.set_value(value)
## Ideal but not implemented
'''class UpdateProcessThread(Thread):
    def __init__(self):
        Thread.__init__(self)
        
        
    def update_process(self):
        ## CB output
        if((state1Node.get_value() == True) or (state3Node.get_value() == True) or (state5Node.get_value() == True)):
            output_activation(nodeConvBelt_output,True)
        else:
            output_activation(nodeConvBelt_output,False)
            
        ## states
        state_change_event(0,state0Node.get_value())
        state_change_event(1,state1Node.get_value())
        state_change_event(2,state2Node.get_value())
        state_change_event(3,state3Node.get_value())
        state_change_event(4,state4Node.get_value())
        state_change_event(5,state5Node.get_value())
        state_change_event(6,state6Node.get_value())'''
    
def update_process(delay):
    while True:
        ## CB output
        if((state1Node.get_value() == True) or (state3Node.get_value() == True) or (state5Node.get_value() == True)):
            output_activation(nodeConvBelt_output,True)
        else:
            output_activation(nodeConvBelt_output,False)
            
        ## states
        state_change_event(0,state0Node.get_value())
        state_change_event(1,state1Node.get_value())
        state_change_event(2,state2Node.get_value())
        state_change_event(3,state3Node.get_value())
        state_change_event(4,state4Node.get_value())
        state_change_event(5,state5Node.get_value())
        state_change_event(6,state6Node.get_value())
        time.sleep(delay)
    
class SubHandler(object):

    """
    Subscription Handler. To receive events from server for a subscription
    data_change and event methods are called directly from receiving thread.
    Do not do expensive, slow or network operation there. Create another
    thread if you need to do such a thing
    """

    def datachange_notification(self, node, val, data):
        print("Python: New data change event", node, val)
        '''if ((node == state0Node) or (node == state1Node) or (node == state2Node) or (node == state3Node) or (node == state4Node) or (node == state5Node) or (node == state6Node)):
            update_process_thread.update_process()
        else:
            return
        #print(node)
        print(val)
        #print(type(val))
        if((node == state1Node) or (node == state3Node) or (node == state5Node) and (val == True)):
            print("State 1,3 or 5 active - Conveyor Belt ON")
            #nodeConvBelt_output.set_value(True)
            #output_activation(nodeConvBelt_output,True)
        elif ((node == state1Node) or (node == state3Node) or (node == state5Node) and (val == False)):
            #nodeConvBelt_output.set_value(False)
            print("State 1,3 or 5 active - Conveyor Belt OFF")
            #output_activation(nodeConvBelt_output,False)
            
        if((node == state4Node) and (val == True)):
            devI_pmd_value = nodeDeviceI_pieceMaterialData.get_value()
            print(devI_pmd_value)
            try:
                dict_pmd = json.loads(devI_pmd_value)
                print(dict_pmd)
                metal = dict_pmd['metallic']
                print(metal)
                if metal == 'true':
                    nodeConvBelt_mgate.set_value(True)
                else:
                    nodeConvBelt_nmgate.set_value(True)
            except:
                pass
        elif ((node == state4Node) and (val == False)):
            nodeConvBelt_nmgate.set_value(False)
            nodeConvBelt_mgate.set_value(False)'''
            
        #### Turning on/off leds (states) ### pins_out = [9,10,11,17,22,27]
        #state 0
        '''if ((node == state0Node) and (val == False)):
            GPIO.output(9, False)
            print("State 0 not active")
        elif ((node == state0Node) and (val == True)):
            GPIO.output(9, True)
            print("State 0 active")
        #state 1
        if ((node == state1Node) and (val == False)):
            GPIO.output(10, False)
            print("State 1 not active")
        elif ((node == state1Node) and (val == True)):
            GPIO.output(10, True)
            print("State 1 active")
        #state 2
        if ((node == state2Node) and (val == False)):
            GPIO.output(11, False)
        elif ((node == state2Node) and (val == True)):
            GPIO.output(11, True)
        #state 3
        if ((node == state3Node) and (val == False)):
            GPIO.output(17, False)
        elif ((node == state3Node) and (val == True)):
            GPIO.output(17, True)
        #state 4
        if ((node == state4Node) and (val == False)):
            GPIO.output(22, False)
            nodeConvBelt_nmgate.set_value(False)
            nodeConvBelt_mgate.set_value(False)
        elif ((node == state4Node) and (val == True)):
            GPIO.output(22, True)
            devI_pmd_value = nodeDeviceI_pieceMaterialData.get_value()
            print(devI_pmd_value)
            try:
                dict_pmd = json.loads(devI_pmd_value)
                print(dict_pmd)
                metal = dict_pmd['metallic']
                print(metal)
                if metal == 'true':
                    nodeConvBelt_mgate.set_value(True)
                else:
                    nodeConvBelt_nmgate.set_value(True)
            except:
                pass
        #state 5
        if ((node == state5Node) and (val == False)):
            GPIO.output(23, False)
        elif ((node == state5Node) and (val == True)):
            GPIO.output(23, True)
        #state 6 and 7
        if ((node == state6Node) and (val == False)):
            GPIO.output(27, False)
        elif ((node == state6Node) and (val == True)):
            GPIO.output(27, True)
        
        #state 0
        if (node == state0Node):
            #state_change_event(0,val)
            pass
        #state 1
        elif (node == state1Node):
            #state_change_event(1,val)
            pass
        #state 2
        elif (node == state2Node):
            #state_change_event(2,val)
            pass
        #state 3
        elif (node == state3Node):
            #state_change_event(3,val)
            pass
        #state 4
        elif (node == state4Node):
            #state_change_event(4,val)
            pass
        #state 5
        elif (node == state5Node):
            #state_change_event(5,val)
            pass
        #state 6 and 7
        elif (node == state6Node):
            #state_change_event(6,val)
            pass'''
            



    def event_notification(self, event):
        print("Python: New event", event)


if __name__ == "__main__":
    logging.basicConfig(level=logging.WARN)
    #logger = logging.getLogger("KeepAlive")
    #logger.setLevel(logging.DEBUG)

    #Set according to the OPC UA network
    #client = Client("opc.tcp://127.0.0.1:12686/AI4.0-Ontology-Example")
    client = Client("opc.tcp://192.168.1.11:12686/AI4.0-Ontology-Example")
    try:
        client.connect()
        client.load_type_definitions()  # load definition of server specific structures/extension objects

        # Client has a few methods to get proxy to UA nodes that should always be in address space such as Root or Objects
        root = client.get_root_node()
        print("Root node is: ", root)
        objects = client.get_objects_node()
        print("Objects node is: ", objects)

        # Node objects have methods to read and write node attributes as well as browse or populate address space
        print("Children of root are: ", root.get_children())

        ###States
        state0Node = client.get_node("ns=2;s=A-I4.0-Ontology/Metal-Separation-Process/States/State 0")
        state0_value = state0Node.get_value()
        state1Node = client.get_node("ns=2;s=A-I4.0-Ontology/Metal-Separation-Process/States/State 1")
        state1_value = state1Node.get_value()
        state2Node = client.get_node("ns=2;s=A-I4.0-Ontology/Metal-Separation-Process/States/State 2")
        state2_value = state2Node.get_value()
        state3Node = client.get_node("ns=2;s=A-I4.0-Ontology/Metal-Separation-Process/States/State 3")
        state3_value = state3Node.get_value()
        state4Node = client.get_node("ns=2;s=A-I4.0-Ontology/Metal-Separation-Process/States/State 4")
        state4_value = state4Node.get_value()
        state5Node = client.get_node("ns=2;s=A-I4.0-Ontology/Metal-Separation-Process/States/State 5")
        state5_value = state5Node.get_value()
        state6Node = client.get_node("ns=2;s=A-I4.0-Ontology/Metal-Separation-Process/States/State 6")
        state6_value = state6Node.get_value()
        state7Node = client.get_node("ns=2;s=A-I4.0-Ontology/Metal-Separation-Process/States/State 7")
        state7_value = state7Node.get_value()



        ### Variables of Device I
        nodeDeviceI_readOk = client.get_node("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/DeviceI.ReadOk")
        devI_rok_value = nodeDeviceI_readOk.get_value()
        nodeDeviceI_pieceMaterialData = client.get_node("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/DeviceI.PieceMaterialData")
        devI_pmd_value = nodeDeviceI_pieceMaterialData.get_value()
        ###Methods
        nodeDeviceI_sendMaterialDataService = client.get_node("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/DeviceI.sendPieceMaterialData()")

        ### Variables of Device II
        nodeDeviceII_performOk = client.get_node("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/DeviceII.PerformOk")
        devII_pok_value = nodeDeviceII_performOk.get_value()
        ###Methods
        nodeDeviceII_requestMaterialData = client.get_node("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/DeviceII.requestMaterialData()")

        ### Variables of Server I
        nodeServerI_saveOk = client.get_node("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/ServerI.SaveOk")
        svrI_sok_value = nodeServerI_saveOk.get_value()
        nodeServerI_serialData = client.get_node("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/ServerI.SerialData")
        svrI_sd_value = nodeServerI_serialData.get_value()
        ## Methods
        nodeServerI_savePieceMaterialProcessData = client.get_node("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/ServerI.savePieceMaterialProcessData()")

        ### Variables of Conveyor Belt
        nodeConvBelt_p1 = client.get_node("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/ConveyorBelt.Position1Detector")
        ConvBelt_p1_value = nodeConvBelt_p1.get_value()
        nodeConvBelt_p2 = client.get_node("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/ConveyorBelt.Position2Detector")
        ConvBelt_p2_value = nodeConvBelt_p2.get_value()
        nodeConvBelt_pf = client.get_node("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/ConveyorBelt.PositionFinalDetector")
        ConvBelt_pf_value = nodeConvBelt_pf.get_value()
        nodeConvBelt_output = client.get_node("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/ConveyorBelt.ConveyorBeltOutput")
        nodeConvBelt_nmgate = client.get_node("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/ConveyorBelt.NonMetallicGate")
        nodeConvBelt_mgate = client.get_node("ns=2;s=A-I4.0-Ontology/A-I4.0-Architecture/ConveyorBelt.MetallicGate")
        
        

        # subscribing to a variable node
        handler = SubHandler()
        sub = client.create_subscription(1000, handler)
        handle = sub.subscribe_data_change(state0Node)
        handle = sub.subscribe_data_change(state1Node)
        handle = sub.subscribe_data_change(state2Node)
        handle = sub.subscribe_data_change(state3Node)
        handle = sub.subscribe_data_change(state4Node)
        handle = sub.subscribe_data_change(state5Node)
        handle = sub.subscribe_data_change(state6Node)
        handle = sub.subscribe_data_change(state7Node)
        handle = sub.subscribe_data_change(nodeDeviceII_performOk)
        handle = sub.subscribe_data_change(nodeConvBelt_p1)
        handle = sub.subscribe_data_change(nodeConvBelt_p2)
        handle = sub.subscribe_data_change(nodeConvBelt_pf)
        handle = sub.subscribe_data_change(nodeServerI_saveOk)
        handle = sub.subscribe_data_change(nodeServerI_serialData)

        #time.sleep(0.1)
        ### Input callbacks
        for pin in pins:
            GPIO.setup(pin, GPIO.IN,pull_up_down=GPIO.PUD_DOWN)
            GPIO.add_event_detect(pin, GPIO.BOTH, callback=IO_event)
        
        ### Output callbacks
        for pin_o in pins_out:
            GPIO.setup(pin_o, GPIO.OUT)
            GPIO.output(pin_o,False)
        
        ## Creating thread to update process
        update_process_thread = Thread(target = update_process, args = (1.0, ))
        update_process_thread.start()
        #update_process_thread = UpdateProcessThread()
        #update_process_thread.start()
        
        # we can also subscribe to events from server
        sub.subscribe_events()
        # sub.unsubscribe(handle)
        # sub.delete()
        
        
        embed()
    finally:
        update_process_thread.join()
        client.disconnect()
