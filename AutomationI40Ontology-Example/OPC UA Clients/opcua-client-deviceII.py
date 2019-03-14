import RPi.GPIO as GPIO
from threading import Thread
import sys
import json
sys.path.insert(0, "..")
import logging
import time
import random

GPIO.setmode(GPIO.BCM)

GPIO.setwarnings(False)

pins = [5,6,7,8]


def IO_event(channel):
    print("event in channel " + str(channel))
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
            nodeConvBelt_p1.set_value(True)
        else:
            nodeConvBelt_p1.set_value(False)
    if(channel == 7):
        if GPIO.input(channel):
            nodeConvBelt_p2.set_value(True)
        else:
            nodeConvBelt_p2.set_value(False)
    if(channel == 8):
        if GPIO.input(channel):
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


class SubHandler(object):

    """
    Subscription Handler. To receive events from server for a subscription
    data_change and event methods are called directly from receiving thread.
    Do not do expensive, slow or network operation there. Create another
    thread if you need to do such a thing
    """

    def datachange_notification(self, node, val, data):
        print("Python: New data change event", node, val)
        if((node == state1Node) or (node == state3Node) or (node == state5Node) and (val == True)):
            nodeConvBelt_output.set_value(True)
        elif ((node == state1Node) or (node == state3Node) or (node == state5Node) and (val == False)):
            nodeConvBelt_output.set_value(False)
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
            nodeConvBelt_mgate.set_value(False)



    def event_notification(self, event):
        print("Python: New event", event)


if __name__ == "__main__":
    logging.basicConfig(level=logging.WARN)
    #logger = logging.getLogger("KeepAlive")
    #logger.setLevel(logging.DEBUG)

    #Set according to the OPC UA network
    #client = Client("opc.tcp://127.0.0.1:12686/AI4.0-Ontology-Example")
    client = Client("opc.tcp://192.168.1.7:12686/AI4.0-Ontology-Example")
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
        sub = client.create_subscription(500, handler)
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

        time.sleep(0.1)

        # we can also subscribe to events from server
        sub.subscribe_events()
        # sub.unsubscribe(handle)
        # sub.delete()

        ### Input callbacks
        for pin in pins:
            GPIO.setup(pin, GPIO.IN,pull_up_down=GPIO.PUD_DOWN)
            GPIO.add_event_detect(pin, GPIO.BOTH, callback=IO_event)

        embed()
    finally:
        client.disconnect()
