/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */
package opcua;

import methods.SendMessageMethod;
import methods.VoidMethod;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.google.common.collect.Lists;
import org.eclipse.milo.examples.server.methods.SqrtMethod;
import org.eclipse.milo.examples.server.types.CustomDataType;
import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRank;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.AccessContext;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.MethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.api.Namespace;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.AnalogItemNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.NodeFactory;
import org.eclipse.milo.opcua.sdk.server.nodes.ServerNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaDataTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaFolderNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegate;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegateChain;
import org.eclipse.milo.opcua.sdk.server.util.AnnotationBasedInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.util.SubscriptionModel;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.OpcUaBinaryDataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteValue;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class ExampleNamespace implements Namespace {

    public static final String NAMESPACE_URI = "urn:eclipse:milo:AI40Ontology";

    private static final Object[][] STATIC_SCALAR_NODES = new Object[][]{
        {"Boolean", Identifiers.Boolean, new Variant(false)},
        {"Byte", Identifiers.Byte, new Variant(ubyte(0x00))},
        {"SByte", Identifiers.SByte, new Variant((byte) 0x00)},
        {"Integer", Identifiers.Integer, new Variant(32)},
        {"Int16", Identifiers.Int16, new Variant((short) 16)},
        {"Int32", Identifiers.Int32, new Variant(32)},
        {"Int64", Identifiers.Int64, new Variant(64L)},
        {"UInteger", Identifiers.UInteger, new Variant(uint(32))},
        {"UInt16", Identifiers.UInt16, new Variant(ushort(16))},
        {"UInt32", Identifiers.UInt32, new Variant(uint(32))},
        {"UInt64", Identifiers.UInt64, new Variant(ulong(64L))},
        {"Float", Identifiers.Float, new Variant(3.14f)},
        {"Double", Identifiers.Double, new Variant(3.14d)},
        {"String", Identifiers.String, new Variant("string value")},
        {"DateTime", Identifiers.DateTime, new Variant(DateTime.now())},
        {"Guid", Identifiers.Guid, new Variant(UUID.randomUUID())},
        {"ByteString", Identifiers.ByteString, new Variant(new ByteString(new byte[]{0x01, 0x02, 0x03, 0x04}))},
        {"XmlElement", Identifiers.XmlElement, new Variant(new XmlElement("<a>hello</a>"))},
        {"LocalizedText", Identifiers.LocalizedText, new Variant(LocalizedText.english("localized text"))},
        {"QualifiedName", Identifiers.QualifiedName, new Variant(new QualifiedName(1234, "defg"))},
        {"NodeId", Identifiers.NodeId, new Variant(new NodeId(1234, "abcd"))},
        {"Duration", Identifiers.Duration, new Variant(1.0)},
        {"UtcTime", Identifiers.UtcTime, new Variant(DateTime.now())},};

    private static final Object[][] STATIC_ARRAY_NODES = new Object[][]{
        {"BooleanArray", Identifiers.Boolean, false},
        {"ByteArray", Identifiers.Byte, ubyte(0)},
        {"SByteArray", Identifiers.SByte, (byte) 0x00},
        {"Int16Array", Identifiers.Int16, (short) 16},
        {"Int32Array", Identifiers.Int32, 32},
        {"Int64Array", Identifiers.Int64, 64L},
        {"UInt16Array", Identifiers.UInt16, ushort(16)},
        {"UInt32Array", Identifiers.UInt32, uint(32)},
        {"UInt64Array", Identifiers.UInt64, ulong(64L)},
        {"FloatArray", Identifiers.Float, 3.14f},
        {"DoubleArray", Identifiers.Double, 3.14d},
        {"StringArray", Identifiers.String, "string value"},
        {"DateTimeArray", Identifiers.DateTime, DateTime.now()},
        {"GuidArray", Identifiers.Guid, UUID.randomUUID()},
        {"ByteStringArray", Identifiers.ByteString, new ByteString(new byte[]{0x01, 0x02, 0x03, 0x04})},
        {"XmlElementArray", Identifiers.XmlElement, new XmlElement("<a>hello</a>")},
        {"LocalizedTextArray", Identifiers.LocalizedText, LocalizedText.english("localized text")},
        {"QualifiedNameArray", Identifiers.QualifiedName, new QualifiedName(1234, "defg")},
        {"NodeIdArray", Identifiers.NodeId, new NodeId(1234, "abcd")}
    };

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Random random = new Random();

    private final SubscriptionModel subscriptionModel;

    private final NodeFactory nodeFactory;

    private final OpcUaServer server;
    private final UShort namespaceIndex;

    NodeId ontologyNodeId;
    NodeId architectureNodeId;
    NodeId processNodeId;
    UaFolderNode ontologyFolderNode;
    UaFolderNode architectureFolderNode;
    UaFolderNode processFolderNode;

    public ExampleNamespace(OpcUaServer server, UShort namespaceIndex) {
        this.server = server;
        this.namespaceIndex = namespaceIndex;

        subscriptionModel = new SubscriptionModel(server, this);

        nodeFactory = new NodeFactory(
                server.getNodeMap(),
                server.getObjectTypeManager(),
                server.getVariableTypeManager()
        );

        try {

            ontologyNodeId = new NodeId(namespaceIndex, "A-I4.0-Ontology");

            ontologyFolderNode = new UaFolderNode(
                    server.getNodeMap(),
                    ontologyNodeId,
                    new QualifiedName(namespaceIndex, "A-I4.0-Ontology"),
                    LocalizedText.english("A-I4.0-Ontology")
            );

            server.getNodeMap().addNode(ontologyFolderNode);

            server.getUaNamespace().addReference(
                    Identifiers.ObjectsFolder,
                    Identifiers.Organizes,
                    true,
                    ontologyNodeId.expanded(),
                    NodeClass.Object
            );

            architectureNodeId = new NodeId(namespaceIndex, "A-I4.0-Ontology/A-I4.0-Architecture");

            architectureFolderNode = new UaFolderNode(
                    ontologyFolderNode.getNodeMap(),
                    architectureNodeId,
                    new QualifiedName(namespaceIndex, "A-I4.0-Ontology/A-I4.0-Architecture"),
                    LocalizedText.english("A-I4.0-Architecture")
            );

            processNodeId = new NodeId(namespaceIndex, "A-I4.0-Ontology/Metal-Separation-Process");

            processFolderNode = new UaFolderNode(
                    ontologyFolderNode.getNodeMap(),
                    processNodeId,
                    new QualifiedName(namespaceIndex, "A-I4.0-Ontology/Metal-Separation-Process"),
                    LocalizedText.english("Metal-Separation-Process")
            );

            ontologyFolderNode.getNodeMap().addNode(architectureFolderNode);
            ontologyFolderNode.getNodeMap().addNode(processFolderNode);

            ontologyFolderNode.addReference(new Reference(
                    ontologyFolderNode.getNodeId(),
                    Identifiers.HasComponent,
                    architectureFolderNode.getNodeId().expanded(),
                    architectureFolderNode.getNodeClass(),
                    true
            ));

            ontologyFolderNode.addReference(new Reference(
                    ontologyFolderNode.getNodeId(),
                    Identifiers.HasComponent,
                    processFolderNode.getNodeId().expanded(),
                    processFolderNode.getNodeClass(),
                    true
            ));

            //ontologyFolderNode.addReference(new Reference(ontologyNodeId, architectureNodeId, architectureNodeId.expanded(), NodeClass.Object, true));
            // Add the rest of the nodes
            addCustomActorITypeAndInstance(architectureFolderNode);
            addCustomActorIITypeAndInstance(architectureFolderNode);
            addCustomActorIIITypeAndInstance(architectureFolderNode);
            addCustomEntityITypeAndInstance(architectureFolderNode);
            addCustomStatesTypeAndInstance(processFolderNode);

        } catch (UaException e) {
            logger.error("Error adding nodes: {}", e.getMessage(), e);
        }
    }

    @Override
    public UShort getNamespaceIndex() {
        return namespaceIndex;
    }

    @Override
    public String getNamespaceUri() {
        return NAMESPACE_URI;
    }

    private void addCustomActorITypeAndInstance(UaFolderNode rootFolder) throws UaException {
        // Define a new ObjectType called "MyObjectType".
        UaObjectTypeNode objectTypeNode = UaObjectTypeNode.builder(server.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "ActorTypes/DeviceI-Type"))
                .setBrowseName(new QualifiedName(namespaceIndex, "ActorTypes/DeviceI-Type"))
                .setDisplayName(LocalizedText.english("DeviceI-Type"))
                .setIsAbstract(false)
                .build();

        // "Foo" and "Bar" are members. These nodes are what are called "instance declarations" by the spec.
        UaVariableNode readOk = UaVariableNode.builder(architectureFolderNode.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "ActorTypes/DeviceI-Type.ReadOk"))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, "ReadOk"))
                .setDisplayName(LocalizedText.english("ReadOk"))
                .setDataType(Identifiers.Boolean)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

        readOk.setValue(new DataValue(new Variant(false)));
        objectTypeNode.addComponent(readOk);

        UaVariableNode materialData = UaVariableNode.builder(architectureFolderNode.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "ActorTypes/DeviceI-Type.PieceMaterialData"))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, "PieceMaterialData"))
                .setDisplayName(LocalizedText.english("PieceMaterialData"))
                .setDataType(Identifiers.String)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

        materialData.setValue(new DataValue(new Variant("{}")));
        objectTypeNode.addComponent(materialData);

        // Tell the ObjectTypeManager about our new type.
        // This let's us use NodeFactory to instantiate instances of the type.
        server.getObjectTypeManager().registerObjectType(
                objectTypeNode.getNodeId(),
                UaObjectNode.class,
                UaObjectNode::new
        );

        // Add our ObjectTypeNode as a subtype of BaseObjectType.
        server.getUaNamespace().addReference(
                Identifiers.BaseObjectType,
                Identifiers.HasSubtype,
                true,
                objectTypeNode.getNodeId().expanded(),
                NodeClass.ObjectType
        );

        // Add the inverse SubtypeOf relationship.
        objectTypeNode.addReference(new Reference(
                objectTypeNode.getNodeId(),
                Identifiers.HasSubtype,
                Identifiers.BaseObjectType.expanded(),
                NodeClass.ObjectType,
                false
        ));

        // Add it into the address space.
        server.getNodeMap().addNode(objectTypeNode);

        // Use NodeFactory to create instance of MyObjectType called "MyObject".
        // NodeFactory takes care of recursively instantiating MyObject member nodes
        // as well as adding all nodes to the address space.
        UaObjectNode deviceI = nodeFactory.createObject(
                new NodeId(namespaceIndex, "A-I4.0-Ontology/A-I4.0-Architecture/DeviceI"),
                new QualifiedName(namespaceIndex, "DeviceI"),
                LocalizedText.english("DeviceI"),
                objectTypeNode.getNodeId()
        );

        // Add forward and inverse references from the root folder.
        rootFolder.addOrganizes(deviceI);

        deviceI.addReference(new Reference(
                deviceI.getNodeId(),
                Identifiers.Organizes,
                rootFolder.getNodeId().expanded(),
                rootFolder.getNodeClass(),
                false
        ));

        UaMethodNode sendDataServiceMethodNode = UaMethodNode.builder(deviceI.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "A-I4.0-Ontology/A-I4.0-Architecture/DeviceI.sendPieceMaterialData()"))
                .setUserExecutable(true)
                .setExecutable(true)
                .setBrowseName(new QualifiedName(namespaceIndex, "sendPieceMaterialData()"))
                .setDisplayName(new LocalizedText(null, "sendPieceMaterialData()"))
                .setDescription(
                        LocalizedText.english("Returns the data of the element: metallic/non-metallic and weight."))
                .build();

        try {
            AnnotationBasedInvocationHandler invocationHandler
                    = AnnotationBasedInvocationHandler.fromAnnotatedObject(
                            deviceI.getNodeMap(), new SendMessageMethod());

            sendDataServiceMethodNode.setProperty(UaMethodNode.InputArguments, invocationHandler.getInputArguments());
            sendDataServiceMethodNode.setProperty(UaMethodNode.OutputArguments, invocationHandler.getOutputArguments());
            sendDataServiceMethodNode.setInvocationHandler(invocationHandler);

            deviceI.getNodeMap().addNode(sendDataServiceMethodNode);

            deviceI.addReference(new Reference(
                    deviceI.getNodeId(),
                    Identifiers.HasComponent,
                    sendDataServiceMethodNode.getNodeId().expanded(),
                    sendDataServiceMethodNode.getNodeClass(),
                    true
            ));

            deviceI.addReference(new Reference(
                    deviceI.getNodeId(),
                    Identifiers.HasComponent,
                    sendDataServiceMethodNode.getNodeId().expanded(),
                    sendDataServiceMethodNode.getNodeClass(),
                    false
            ));
        } catch (Exception e) {
            logger.error("Error creating sendMessage() method.", e);
        }

    }

    private void addCustomActorIITypeAndInstance(UaFolderNode rootFolder) throws UaException {
        // Define a new ObjectType called "MyObjectType".
        UaObjectTypeNode objectTypeNode = UaObjectTypeNode.builder(server.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "ActorTypes/DeviceII-Type"))
                .setBrowseName(new QualifiedName(namespaceIndex, "ActorTypes/DeviceII-Type"))
                .setDisplayName(LocalizedText.english("DeviceII-Type"))
                .setIsAbstract(false)
                .build();

        // "Foo" and "Bar" are members. These nodes are what are called "instance declarations" by the spec.
        UaVariableNode performOk = UaVariableNode.builder(architectureFolderNode.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "ActorTypes/DeviceII-Type.PerformOk"))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, "PerformOk"))
                .setDisplayName(LocalizedText.english("PerformOk"))
                .setDataType(Identifiers.Boolean)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

        performOk.setValue(new DataValue(new Variant(false)));
        objectTypeNode.addComponent(performOk);

        // Tell the ObjectTypeManager about our new type.
        // This let's us use NodeFactory to instantiate instances of the type.
        server.getObjectTypeManager().registerObjectType(
                objectTypeNode.getNodeId(),
                UaObjectNode.class,
                UaObjectNode::new
        );

        // Add our ObjectTypeNode as a subtype of BaseObjectType.
        server.getUaNamespace().addReference(
                Identifiers.BaseObjectType,
                Identifiers.HasSubtype,
                true,
                objectTypeNode.getNodeId().expanded(),
                NodeClass.ObjectType
        );

        // Add the inverse SubtypeOf relationship.
        objectTypeNode.addReference(new Reference(
                objectTypeNode.getNodeId(),
                Identifiers.HasSubtype,
                Identifiers.BaseObjectType.expanded(),
                NodeClass.ObjectType,
                false
        ));

        // Add it into the address space.
        server.getNodeMap().addNode(objectTypeNode);

        // Use NodeFactory to create instance of MyObjectType called "MyObject".
        // NodeFactory takes care of recursively instantiating MyObject member nodes
        // as well as adding all nodes to the address space.
        UaObjectNode deviceII = nodeFactory.createObject(
                new NodeId(namespaceIndex, "A-I4.0-Ontology/A-I4.0-Architecture/DeviceII"),
                new QualifiedName(namespaceIndex, "DeviceII"),
                LocalizedText.english("DeviceII"),
                objectTypeNode.getNodeId()
        );

        // Add forward and inverse references from the root folder.
        rootFolder.addOrganizes(deviceII);

        deviceII.addReference(new Reference(
                deviceII.getNodeId(),
                Identifiers.Organizes,
                rootFolder.getNodeId().expanded(),
                rootFolder.getNodeClass(),
                false
        ));

        UaMethodNode requestDataServiceMethodNode = UaMethodNode.builder(deviceII.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "A-I4.0-Ontology/A-I4.0-Architecture/DeviceII.requestMaterialData()"))
                .setUserExecutable(true)
                .setExecutable(true)
                .setBrowseName(new QualifiedName(namespaceIndex, "requestMaterialData()"))
                .setDisplayName(new LocalizedText(null, "requestMaterialData()"))
                .setDescription(
                        LocalizedText.english("Requests the material data and weight to the Device I."))
                .build();

        try {
            AnnotationBasedInvocationHandler invocationHandler
                    = AnnotationBasedInvocationHandler.fromAnnotatedObject(
                            deviceII.getNodeMap(), new SendMessageMethod());

            requestDataServiceMethodNode.setProperty(UaMethodNode.InputArguments, invocationHandler.getInputArguments());
            requestDataServiceMethodNode.setProperty(UaMethodNode.OutputArguments, invocationHandler.getOutputArguments());
            requestDataServiceMethodNode.setInvocationHandler(invocationHandler);

            deviceII.getNodeMap().addNode(requestDataServiceMethodNode);

            deviceII.addReference(new Reference(
                    deviceII.getNodeId(),
                    Identifiers.HasComponent,
                    requestDataServiceMethodNode.getNodeId().expanded(),
                    requestDataServiceMethodNode.getNodeClass(),
                    true
            ));

            deviceII.addReference(new Reference(
                    deviceII.getNodeId(),
                    Identifiers.HasComponent,
                    requestDataServiceMethodNode.getNodeId().expanded(),
                    requestDataServiceMethodNode.getNodeClass(),
                    false
            ));
        } catch (Exception e) {
            logger.error("Error creating sendMessage() method.", e);
        }

    }

    private void addCustomActorIIITypeAndInstance(UaFolderNode rootFolder) throws UaException {
        // Define a new ObjectType called "MyObjectType".
        UaObjectTypeNode objectTypeNode = UaObjectTypeNode.builder(server.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "ActorTypes/ServerI-Type"))
                .setBrowseName(new QualifiedName(namespaceIndex, "ActorTypes/ServerI-Type"))
                .setDisplayName(LocalizedText.english("ServerI-Type"))
                .setIsAbstract(false)
                .build();

        // "Foo" and "Bar" are members. These nodes are what are called "instance declarations" by the spec.
        UaVariableNode serialData = UaVariableNode.builder(architectureFolderNode.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "ActorTypes/ServerI-Type.SerialData"))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, "SerialData"))
                .setDisplayName(LocalizedText.english("SerialData"))
                .setDataType(Identifiers.String)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

        serialData.setValue(new DataValue(new Variant("")));
        objectTypeNode.addComponent(serialData);

        UaVariableNode saveOk = UaVariableNode.builder(architectureFolderNode.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "ActorTypes/ServerI-Type.SaveOk"))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, "SaveOk"))
                .setDisplayName(LocalizedText.english("SaveOk"))
                .setDataType(Identifiers.Boolean)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

        saveOk.setValue(new DataValue(new Variant(false)));
        objectTypeNode.addComponent(saveOk);

        // Tell the ObjectTypeManager about our new type.
        // This let's us use NodeFactory to instantiate instances of the type.
        server.getObjectTypeManager().registerObjectType(
                objectTypeNode.getNodeId(),
                UaObjectNode.class,
                UaObjectNode::new
        );

        // Add our ObjectTypeNode as a subtype of BaseObjectType.
        server.getUaNamespace().addReference(
                Identifiers.BaseObjectType,
                Identifiers.HasSubtype,
                true,
                objectTypeNode.getNodeId().expanded(),
                NodeClass.ObjectType
        );

        // Add the inverse SubtypeOf relationship.
        objectTypeNode.addReference(new Reference(
                objectTypeNode.getNodeId(),
                Identifiers.HasSubtype,
                Identifiers.BaseObjectType.expanded(),
                NodeClass.ObjectType,
                false
        ));

        // Add it into the address space.
        server.getNodeMap().addNode(objectTypeNode);

        // Use NodeFactory to create instance of MyObjectType called "MyObject".
        // NodeFactory takes care of recursively instantiating MyObject member nodes
        // as well as adding all nodes to the address space.
        UaObjectNode serverI = nodeFactory.createObject(
                new NodeId(namespaceIndex, "A-I4.0-Ontology/A-I4.0-Architecture/ServerI"),
                new QualifiedName(namespaceIndex, "ServerI"),
                LocalizedText.english("ServerI"),
                objectTypeNode.getNodeId()
        );

        // Add forward and inverse references from the root folder.
        rootFolder.addOrganizes(serverI);

        serverI.addReference(new Reference(
                serverI.getNodeId(),
                Identifiers.Organizes,
                rootFolder.getNodeId().expanded(),
                rootFolder.getNodeClass(),
                false
        ));

        UaMethodNode saveDataServiceMethodNode = UaMethodNode.builder(serverI.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "A-I4.0-Ontology/A-I4.0-Architecture/ServerI.savePieceMaterialProcessData()"))
                .setUserExecutable(true)
                .setExecutable(true)
                .setBrowseName(new QualifiedName(namespaceIndex, "savePieceMaterialProcessData()"))
                .setDisplayName(new LocalizedText(null, "savePieceMaterialProcessData()"))
                .setDescription(
                        LocalizedText.english("Saves the data into the database."))
                .build();

        try {
            AnnotationBasedInvocationHandler invocationHandler
                    = AnnotationBasedInvocationHandler.fromAnnotatedObject(
                            serverI.getNodeMap(), new VoidMethod());

            saveDataServiceMethodNode.setProperty(UaMethodNode.InputArguments, invocationHandler.getInputArguments());
            saveDataServiceMethodNode.setProperty(UaMethodNode.OutputArguments, invocationHandler.getOutputArguments());
            saveDataServiceMethodNode.setInvocationHandler(invocationHandler);

            serverI.getNodeMap().addNode(saveDataServiceMethodNode);

            serverI.addReference(new Reference(
                    serverI.getNodeId(),
                    Identifiers.HasComponent,
                    saveDataServiceMethodNode.getNodeId().expanded(),
                    saveDataServiceMethodNode.getNodeClass(),
                    true
            ));

            serverI.addReference(new Reference(
                    serverI.getNodeId(),
                    Identifiers.HasComponent,
                    saveDataServiceMethodNode.getNodeId().expanded(),
                    saveDataServiceMethodNode.getNodeClass(),
                    false
            ));
        } catch (Exception e) {
            logger.error("Error creating voidMethod() method.", e);
        }

    }
    
    private void addCustomEntityITypeAndInstance(UaFolderNode rootFolder) throws UaException {
        UaObjectTypeNode objectTypeNode = UaObjectTypeNode.builder(server.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "EntityTypes/ConveyorBelt-Type"))
                .setBrowseName(new QualifiedName(namespaceIndex, "EntityTypes/ConveyorBelt-Type"))
                .setDisplayName(LocalizedText.english("ConveyorBelt-Type"))
                .setIsAbstract(false)
                .build();

        UaVariableNode position1Detector = UaVariableNode.builder(architectureFolderNode.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "EntityTypes/ConveyorBelt-Type.Position1Detector"))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, "Position1Detector"))
                .setDisplayName(LocalizedText.english("Position1Detector"))
                .setDataType(Identifiers.Boolean)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

        position1Detector.setValue(new DataValue(new Variant(false)));
        objectTypeNode.addComponent(position1Detector);

        UaVariableNode position2Detector = UaVariableNode.builder(architectureFolderNode.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "EntityTypes/ConveyorBelt-Type.Position2Detector"))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, "Position2Detector"))
                .setDisplayName(LocalizedText.english("Position2Detector"))
                .setDataType(Identifiers.Boolean)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

        position2Detector.setValue(new DataValue(new Variant(false)));
        objectTypeNode.addComponent(position2Detector);
        
        UaVariableNode positionFinalDetector = UaVariableNode.builder(architectureFolderNode.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "EntityTypes/ConveyorBelt-Type.PositionFinalDetector"))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, "PositionFinalDetector"))
                .setDisplayName(LocalizedText.english("PositionFinalDetector"))
                .setDataType(Identifiers.Boolean)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

        positionFinalDetector.setValue(new DataValue(new Variant(false)));
        objectTypeNode.addComponent(positionFinalDetector);
        
        UaVariableNode conveyorBeltOutput = UaVariableNode.builder(architectureFolderNode.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "EntityTypes/ConveyorBelt-Type.ConveyorBeltOutput"))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, "ConveyorBeltOutput"))
                .setDisplayName(LocalizedText.english("ConveyorBeltOutput"))
                .setDataType(Identifiers.Boolean)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

        conveyorBeltOutput.setValue(new DataValue(new Variant(false)));
        objectTypeNode.addComponent(conveyorBeltOutput);
        
        UaVariableNode metallicGate = UaVariableNode.builder(architectureFolderNode.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "EntityTypes/ConveyorBelt-Type.MetallicGate"))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, "MetallicGate"))
                .setDisplayName(LocalizedText.english("MetallicGate"))
                .setDataType(Identifiers.Boolean)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

        metallicGate.setValue(new DataValue(new Variant(false)));
        objectTypeNode.addComponent(metallicGate);
        
        UaVariableNode nonMetallicGate = UaVariableNode.builder(architectureFolderNode.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "EntityTypes/ConveyorBelt-Type.NonMetallicGate"))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, "NonMetallicGate"))
                .setDisplayName(LocalizedText.english("NonMetallicGate"))
                .setDataType(Identifiers.Boolean)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

        nonMetallicGate.setValue(new DataValue(new Variant(false)));
        objectTypeNode.addComponent(nonMetallicGate);

        // Tell the ObjectTypeManager about our new type.
        // This let's us use NodeFactory to instantiate instances of the type.
        server.getObjectTypeManager().registerObjectType(
                objectTypeNode.getNodeId(),
                UaObjectNode.class,
                UaObjectNode::new
        );

        // Add our ObjectTypeNode as a subtype of BaseObjectType.
        server.getUaNamespace().addReference(
                Identifiers.BaseObjectType,
                Identifiers.HasSubtype,
                true,
                objectTypeNode.getNodeId().expanded(),
                NodeClass.ObjectType
        );

        // Add the inverse SubtypeOf relationship.
        objectTypeNode.addReference(new Reference(
                objectTypeNode.getNodeId(),
                Identifiers.HasSubtype,
                Identifiers.BaseObjectType.expanded(),
                NodeClass.ObjectType,
                false
        ));

        // Add it into the address space.
        server.getNodeMap().addNode(objectTypeNode);

        // Use NodeFactory to create instance of MyObjectType called "MyObject".
        // NodeFactory takes care of recursively instantiating MyObject member nodes
        // as well as adding all nodes to the address space.
        UaObjectNode conveyorBelt = nodeFactory.createObject(
                new NodeId(namespaceIndex, "A-I4.0-Ontology/A-I4.0-Architecture/ConveyorBelt"),
                new QualifiedName(namespaceIndex, "ConveyorBelt"),
                LocalizedText.english("ConveyorBelt"),
                objectTypeNode.getNodeId()
        );

        // Add forward and inverse references from the root folder.
        rootFolder.addOrganizes(conveyorBelt);

        conveyorBelt.addReference(new Reference(
                conveyorBelt.getNodeId(),
                Identifiers.Organizes,
                rootFolder.getNodeId().expanded(),
                rootFolder.getNodeClass(),
                false
        ));

    }

    private void addCustomStatesTypeAndInstance(UaFolderNode rootFolder) throws UaException {
        UaFolderNode statesFolder = new UaFolderNode(
                server.getNodeMap(),
                new NodeId(namespaceIndex, "A-I4.0-Ontology/Metal-Separation-Process/States"),
                new QualifiedName(namespaceIndex, "States"),
                LocalizedText.english("States")
        );

        server.getNodeMap().addNode(statesFolder);
        rootFolder.addOrganizes(statesFolder);

        // State 0 Boolean
        {
            String name = "State 0";
            NodeId typeId = Identifiers.Boolean;
            Variant variant = new Variant(false);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
                    .setNodeId(new NodeId(namespaceIndex, "A-I4.0-Ontology/Metal-Separation-Process/States/" + name))
                    .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setBrowseName(new QualifiedName(namespaceIndex, name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();

            node.setValue(new DataValue(variant));

            statesFolder.getNodeMap().addNode(node);
            statesFolder.addOrganizes(node);
        }

        {
            String name = "State 1";
            NodeId typeId = Identifiers.Boolean;
            Variant variant = new Variant(false);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
                    .setNodeId(new NodeId(namespaceIndex, "A-I4.0-Ontology/Metal-Separation-Process/States/" + name))
                    .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setBrowseName(new QualifiedName(namespaceIndex, name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();

            node.setValue(new DataValue(variant));

            statesFolder.getNodeMap().addNode(node);
            statesFolder.addOrganizes(node);
        }

        {
            String name = "State 2";
            NodeId typeId = Identifiers.Boolean;
            Variant variant = new Variant(false);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
                    .setNodeId(new NodeId(namespaceIndex, "A-I4.0-Ontology/Metal-Separation-Process/States/" + name))
                    .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setBrowseName(new QualifiedName(namespaceIndex, name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();

            node.setValue(new DataValue(variant));

            statesFolder.getNodeMap().addNode(node);
            statesFolder.addOrganizes(node);
        }

        {
            String name = "State 3";
            NodeId typeId = Identifiers.Boolean;
            Variant variant = new Variant(false);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
                    .setNodeId(new NodeId(namespaceIndex, "A-I4.0-Ontology/Metal-Separation-Process/States/" + name))
                    .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setBrowseName(new QualifiedName(namespaceIndex, name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();

            node.setValue(new DataValue(variant));
            statesFolder.getNodeMap().addNode(node);
            statesFolder.addOrganizes(node);
        }

        {
            String name = "State 4";
            NodeId typeId = Identifiers.Boolean;
            Variant variant = new Variant(false);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
                    .setNodeId(new NodeId(namespaceIndex, "A-I4.0-Ontology/Metal-Separation-Process/States/" + name))
                    .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setBrowseName(new QualifiedName(namespaceIndex, name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();

            node.setValue(new DataValue(variant));
            statesFolder.getNodeMap().addNode(node);
            statesFolder.addOrganizes(node);
        }

        {
            String name = "State 5";
            NodeId typeId = Identifiers.Boolean;
            Variant variant = new Variant(false);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
                    .setNodeId(new NodeId(namespaceIndex, "A-I4.0-Ontology/Metal-Separation-Process/States/" + name))
                    .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setBrowseName(new QualifiedName(namespaceIndex, name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();

            node.setValue(new DataValue(variant));
            statesFolder.getNodeMap().addNode(node);
            statesFolder.addOrganizes(node);
        }

        {
            String name = "State 6";
            NodeId typeId = Identifiers.Boolean;
            Variant variant = new Variant(false);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
                    .setNodeId(new NodeId(namespaceIndex, "A-I4.0-Ontology/Metal-Separation-Process/States/" + name))
                    .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setBrowseName(new QualifiedName(namespaceIndex, name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(Identifiers.Boolean)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();

            node.setValue(new DataValue(variant));
            statesFolder.getNodeMap().addNode(node);
            statesFolder.addOrganizes(node);
        }

        {
            String name = "State 7";
            NodeId typeId = Identifiers.Boolean;
            Variant variant = new Variant(false);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
                    .setNodeId(new NodeId(namespaceIndex, "A-I4.0-Ontology/Metal-Separation-Process/States/" + name))
                    .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setBrowseName(new QualifiedName(namespaceIndex, name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();

            node.setValue(new DataValue(variant));
            statesFolder.getNodeMap().addNode(node);
            statesFolder.addOrganizes(node);
        }

    }

    private void addVariableNodes(UaFolderNode rootNode) {
        addArrayNodes(rootNode);
        addScalarNodes(rootNode);
        addAdminReadableNodes(rootNode);
        addAdminWritableNodes(rootNode);
        addDynamicNodes(rootNode);
        addDataAccessNodes(rootNode);
        addWriteOnlyNodes(rootNode);
    }

    private void addArrayNodes(UaFolderNode rootNode) {
        UaFolderNode arrayTypesFolder = new UaFolderNode(
                server.getNodeMap(),
                new NodeId(namespaceIndex, "HelloWorld/ArrayTypes"),
                new QualifiedName(namespaceIndex, "ArrayTypes"),
                LocalizedText.english("ArrayTypes")
        );

        server.getNodeMap().addNode(arrayTypesFolder);
        rootNode.addOrganizes(arrayTypesFolder);

        for (Object[] os : STATIC_ARRAY_NODES) {
            String name = (String) os[0];
            NodeId typeId = (NodeId) os[1];
            Object value = os[2];
            Object array = Array.newInstance(value.getClass(), 5);
            for (int i = 0; i < 5; i++) {
                Array.set(array, i, value);
            }
            Variant variant = new Variant(array);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
                    .setNodeId(new NodeId(namespaceIndex, "HelloWorld/ArrayTypes/" + name))
                    .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setBrowseName(new QualifiedName(namespaceIndex, name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .setValueRank(ValueRank.OneDimension.getValue())
                    .setArrayDimensions(new UInteger[]{uint(0)})
                    .build();

            node.setValue(new DataValue(variant));

            node.setAttributeDelegate(new ValueLoggingDelegate());

            server.getNodeMap().addNode(node);
            arrayTypesFolder.addOrganizes(node);
        }
    }

    private void addScalarNodes(UaFolderNode rootNode) {
        UaFolderNode scalarTypesFolder = new UaFolderNode(
                server.getNodeMap(),
                new NodeId(namespaceIndex, "HelloWorld/ScalarTypes"),
                new QualifiedName(namespaceIndex, "ScalarTypes"),
                LocalizedText.english("ScalarTypes")
        );

        server.getNodeMap().addNode(scalarTypesFolder);
        rootNode.addOrganizes(scalarTypesFolder);

        for (Object[] os : STATIC_SCALAR_NODES) {
            String name = (String) os[0];
            NodeId typeId = (NodeId) os[1];
            Variant variant = (Variant) os[2];

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
                    .setNodeId(new NodeId(namespaceIndex, "HelloWorld/ScalarTypes/" + name))
                    .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setBrowseName(new QualifiedName(namespaceIndex, name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();

            node.setValue(new DataValue(variant));

            node.setAttributeDelegate(new ValueLoggingDelegate());

            server.getNodeMap().addNode(node);
            scalarTypesFolder.addOrganizes(node);
        }
    }

    private void addWriteOnlyNodes(UaFolderNode rootNode) {
        UaFolderNode writeOnlyFolder = new UaFolderNode(
                server.getNodeMap(),
                new NodeId(namespaceIndex, "HelloWorld/WriteOnly"),
                new QualifiedName(namespaceIndex, "WriteOnly"),
                LocalizedText.english("WriteOnly")
        );

        server.getNodeMap().addNode(writeOnlyFolder);
        rootNode.addOrganizes(writeOnlyFolder);

        String name = "String";
        UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "HelloWorld/WriteOnly/" + name))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.WRITE_ONLY)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.WRITE_ONLY)))
                .setBrowseName(new QualifiedName(namespaceIndex, name))
                .setDisplayName(LocalizedText.english(name))
                .setDataType(Identifiers.String)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

        node.setValue(new DataValue(new Variant("can't read this")));

        server.getNodeMap().addNode(node);
        writeOnlyFolder.addOrganizes(node);
    }

    private void addAdminReadableNodes(UaFolderNode rootNode) {
        UaFolderNode adminFolder = new UaFolderNode(
                server.getNodeMap(),
                new NodeId(namespaceIndex, "HelloWorld/OnlyAdminCanRead"),
                new QualifiedName(namespaceIndex, "OnlyAdminCanRead"),
                LocalizedText.english("OnlyAdminCanRead")
        );

        server.getNodeMap().addNode(adminFolder);
        rootNode.addOrganizes(adminFolder);

        String name = "String";
        UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "HelloWorld/OnlyAdminCanRead/" + name))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, name))
                .setDisplayName(LocalizedText.english(name))
                .setDataType(Identifiers.String)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

        node.setValue(new DataValue(new Variant("shh... don't tell the lusers")));

        node.setAttributeDelegate(new RestrictedAccessDelegate(identity -> {
            if ("admin".equals(identity)) {
                return AccessLevel.READ_WRITE;
            } else {
                return AccessLevel.NONE;
            }
        }));

        server.getNodeMap().addNode(node);
        adminFolder.addOrganizes(node);
    }

    private void addAdminWritableNodes(UaFolderNode rootNode) {
        UaFolderNode adminFolder = new UaFolderNode(
                server.getNodeMap(),
                new NodeId(namespaceIndex, "HelloWorld/OnlyAdminCanWrite"),
                new QualifiedName(namespaceIndex, "OnlyAdminCanWrite"),
                LocalizedText.english("OnlyAdminCanWrite")
        );

        server.getNodeMap().addNode(adminFolder);
        rootNode.addOrganizes(adminFolder);

        String name = "String";
        UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "HelloWorld/OnlyAdminCanWrite/" + name))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, name))
                .setDisplayName(LocalizedText.english(name))
                .setDataType(Identifiers.String)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

        node.setValue(new DataValue(new Variant("admin was here")));

        node.setAttributeDelegate(new RestrictedAccessDelegate(identity -> {
            if ("admin".equals(identity)) {
                return AccessLevel.READ_WRITE;
            } else {
                return AccessLevel.READ_ONLY;
            }
        }));

        server.getNodeMap().addNode(node);
        adminFolder.addOrganizes(node);
    }

    private void addDynamicNodes(UaFolderNode rootNode) {
        UaFolderNode dynamicFolder = new UaFolderNode(
                server.getNodeMap(),
                new NodeId(namespaceIndex, "HelloWorld/Dynamic"),
                new QualifiedName(namespaceIndex, "Dynamic"),
                LocalizedText.english("Dynamic")
        );

        server.getNodeMap().addNode(dynamicFolder);
        rootNode.addOrganizes(dynamicFolder);

        // Dynamic Boolean
        {
            String name = "Boolean";
            NodeId typeId = Identifiers.Boolean;
            Variant variant = new Variant(false);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
                    .setNodeId(new NodeId(namespaceIndex, "HelloWorld/Dynamic/" + name))
                    .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setBrowseName(new QualifiedName(namespaceIndex, name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();

            node.setValue(new DataValue(variant));

            AttributeDelegate delegate = AttributeDelegateChain.create(
                    new AttributeDelegate() {
                @Override
                public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
                    return new DataValue(new Variant(random.nextBoolean()));
                }
            },
                    ValueLoggingDelegate::new
            );

            node.setAttributeDelegate(delegate);

            server.getNodeMap().addNode(node);
            dynamicFolder.addOrganizes(node);
        }

        // Dynamic Int32
        {
            String name = "Int32";
            NodeId typeId = Identifiers.Int32;
            Variant variant = new Variant(0);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
                    .setNodeId(new NodeId(namespaceIndex, "HelloWorld/Dynamic/" + name))
                    .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setBrowseName(new QualifiedName(namespaceIndex, name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();

            node.setValue(new DataValue(variant));

            AttributeDelegate delegate = AttributeDelegateChain.create(
                    new AttributeDelegate() {
                @Override
                public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
                    return new DataValue(new Variant(random.nextInt()));
                }
            },
                    ValueLoggingDelegate::new
            );

            node.setAttributeDelegate(delegate);

            server.getNodeMap().addNode(node);
            dynamicFolder.addOrganizes(node);
        }

        // Dynamic Double
        {
            String name = "Double";
            NodeId typeId = Identifiers.Double;
            Variant variant = new Variant(0.0);

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
                    .setNodeId(new NodeId(namespaceIndex, "HelloWorld/Dynamic/" + name))
                    .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                    .setBrowseName(new QualifiedName(namespaceIndex, name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();

            node.setValue(new DataValue(variant));

            AttributeDelegate delegate = AttributeDelegateChain.create(
                    new AttributeDelegate() {
                @Override
                public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
                    return new DataValue(new Variant(random.nextDouble()));
                }
            },
                    ValueLoggingDelegate::new
            );

            node.setAttributeDelegate(delegate);

            server.getNodeMap().addNode(node);
            dynamicFolder.addOrganizes(node);
        }
    }

    private void addDataAccessNodes(UaFolderNode rootNode) {
        // DataAccess folder
        UaFolderNode dataAccessFolder = new UaFolderNode(
                server.getNodeMap(),
                new NodeId(namespaceIndex, "HelloWorld/DataAccess"),
                new QualifiedName(namespaceIndex, "DataAccess"),
                LocalizedText.english("DataAccess")
        );

        server.getNodeMap().addNode(dataAccessFolder);
        rootNode.addOrganizes(dataAccessFolder);

        // AnalogItemType node
        AnalogItemNode node = nodeFactory.createVariable(
                new NodeId(namespaceIndex, "HelloWorld/DataAccess/AnalogValue"),
                new QualifiedName(namespaceIndex, "AnalogValue"),
                LocalizedText.english("AnalogValue"),
                Identifiers.AnalogItemType,
                AnalogItemNode.class
        );

        node.setDataType(Identifiers.Double);
        node.setValue(new DataValue(new Variant(3.14d)));

        node.setEURange(new Range(0.0, 100.0));

        server.getNodeMap().addNode(node);
        dataAccessFolder.addOrganizes(node);
    }

    private void addMethodNode(UaFolderNode folderNode) {
        UaMethodNode methodNode = UaMethodNode.builder(server.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "HelloWorld/sqrt(x)"))
                .setBrowseName(new QualifiedName(namespaceIndex, "sqrt(x)"))
                .setDisplayName(new LocalizedText(null, "sqrt(x)"))
                .setDescription(
                        LocalizedText.english("Returns the correctly rounded positive square root of a double value."))
                .build();

        try {
            AnnotationBasedInvocationHandler invocationHandler
                    = AnnotationBasedInvocationHandler.fromAnnotatedObject(
                            server.getNodeMap(), new SqrtMethod());

            methodNode.setProperty(UaMethodNode.InputArguments, invocationHandler.getInputArguments());
            methodNode.setProperty(UaMethodNode.OutputArguments, invocationHandler.getOutputArguments());
            methodNode.setInvocationHandler(invocationHandler);

            server.getNodeMap().addNode(methodNode);

            folderNode.addReference(new Reference(
                    folderNode.getNodeId(),
                    Identifiers.HasComponent,
                    methodNode.getNodeId().expanded(),
                    methodNode.getNodeClass(),
                    true
            ));

            methodNode.addReference(new Reference(
                    methodNode.getNodeId(),
                    Identifiers.HasComponent,
                    folderNode.getNodeId().expanded(),
                    folderNode.getNodeClass(),
                    false
            ));
        } catch (Exception e) {
            logger.error("Error creating sqrt() method.", e);
        }
    }

    private void addCustomObjectTypeAndInstance(UaFolderNode rootFolder) throws UaException {
        // Define a new ObjectType called "MyObjectType".
        UaObjectTypeNode objectTypeNode = UaObjectTypeNode.builder(server.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "ObjectTypes/MyObjectType"))
                .setBrowseName(new QualifiedName(namespaceIndex, "MyObjectType"))
                .setDisplayName(LocalizedText.english("MyObjectType"))
                .setIsAbstract(false)
                .build();

        // "Foo" and "Bar" are members. These nodes are what are called "instance declarations" by the spec.
        UaVariableNode foo = UaVariableNode.builder(server.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "ObjectTypes/MyObjectType.Foo"))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, "Foo"))
                .setDisplayName(LocalizedText.english("Foo"))
                .setDataType(Identifiers.Int16)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

        foo.setValue(new DataValue(new Variant(0)));
        objectTypeNode.addComponent(foo);

        UaVariableNode bar = UaVariableNode.builder(server.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "ObjectTypes/MyObjectType.Bar"))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, "Bar"))
                .setDisplayName(LocalizedText.english("Bar"))
                .setDataType(Identifiers.String)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

        bar.setValue(new DataValue(new Variant("bar")));
        objectTypeNode.addComponent(bar);

        // Tell the ObjectTypeManager about our new type.
        // This let's us use NodeFactory to instantiate instances of the type.
        server.getObjectTypeManager().registerObjectType(
                objectTypeNode.getNodeId(),
                UaObjectNode.class,
                UaObjectNode::new
        );

        // Add our ObjectTypeNode as a subtype of BaseObjectType.
        server.getUaNamespace().addReference(
                Identifiers.BaseObjectType,
                Identifiers.HasSubtype,
                true,
                objectTypeNode.getNodeId().expanded(),
                NodeClass.ObjectType
        );

        // Add the inverse SubtypeOf relationship.
        objectTypeNode.addReference(new Reference(
                objectTypeNode.getNodeId(),
                Identifiers.HasSubtype,
                Identifiers.BaseObjectType.expanded(),
                NodeClass.ObjectType,
                false
        ));

        // Add it into the address space.
        server.getNodeMap().addNode(objectTypeNode);

        // Use NodeFactory to create instance of MyObjectType called "MyObject".
        // NodeFactory takes care of recursively instantiating MyObject member nodes
        // as well as adding all nodes to the address space.
        UaObjectNode myObject = nodeFactory.createObject(
                new NodeId(namespaceIndex, "HelloWorld/MyObject"),
                new QualifiedName(namespaceIndex, "MyObject"),
                LocalizedText.english("MyObject"),
                objectTypeNode.getNodeId()
        );

        // Add forward and inverse references from the root folder.
        rootFolder.addOrganizes(myObject);

        myObject.addReference(new Reference(
                myObject.getNodeId(),
                Identifiers.Organizes,
                rootFolder.getNodeId().expanded(),
                rootFolder.getNodeClass(),
                false
        ));
    }

    private void addCustomDataTypeVariable(UaFolderNode rootFolder) {
        // add a custom DataTypeNode as a subtype of the built-in Structure DataTypeNode
        NodeId dataTypeId = new NodeId(namespaceIndex, "DataType.CustomDataType");

        UaDataTypeNode dataTypeNode = new UaDataTypeNode(
                server.getNodeMap(),
                dataTypeId,
                new QualifiedName(namespaceIndex, "CustomDataType"),
                LocalizedText.english("CustomDataType"),
                LocalizedText.english("CustomDataType"),
                uint(0),
                uint(0),
                false
        );

        // Inverse ref to Structure
        dataTypeNode.addReference(new Reference(
                dataTypeId,
                Identifiers.HasSubtype,
                Identifiers.Structure.expanded(),
                NodeClass.DataType,
                false
        ));

        // Forward ref from Structure
        Optional<UaDataTypeNode> structureDataTypeNode = server.getNodeMap()
                .getNode(Identifiers.Structure)
                .map(UaDataTypeNode.class::cast);

        structureDataTypeNode.ifPresent(node
                -> node.addReference(new Reference(
                        node.getNodeId(),
                        Identifiers.HasSubtype,
                        dataTypeId.expanded(),
                        NodeClass.DataType,
                        true
                ))
        );

        // Create a dictionary, binaryEncodingId, and register the codec under that id
        OpcUaBinaryDataTypeDictionary dictionary = new OpcUaBinaryDataTypeDictionary(
                "urn:eclipse:milo:example:custom-data-type"
        );

        NodeId binaryEncodingId = new NodeId(namespaceIndex, "DataType.CustomDataType.BinaryEncoding");

        dictionary.registerStructCodec(
                new CustomDataType.Codec().asBinaryCodec(),
                "CustomDataType",
                binaryEncodingId
        );

        // Register dictionary with the shared DataTypeManager instance
        OpcUaDataTypeManager.getInstance().registerTypeDictionary(dictionary);

        UaVariableNode customDataTypeVariable = UaVariableNode.builder(server.getNodeMap())
                .setNodeId(new NodeId(namespaceIndex, "HelloWorld/CustomDataTypeVariable"))
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
                .setBrowseName(new QualifiedName(namespaceIndex, "CustomDataTypeVariable"))
                .setDisplayName(LocalizedText.english("CustomDataTypeVariable"))
                .setDataType(dataTypeId)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

        CustomDataType value = new CustomDataType(
                "foo",
                uint(42),
                true
        );

        ExtensionObject xo = ExtensionObject.encode(value, binaryEncodingId);

        customDataTypeVariable.setValue(new DataValue(new Variant(xo)));

        rootFolder.addOrganizes(customDataTypeVariable);

        customDataTypeVariable.addReference(new Reference(
                customDataTypeVariable.getNodeId(),
                Identifiers.Organizes,
                rootFolder.getNodeId().expanded(),
                rootFolder.getNodeClass(),
                false
        ));
    }

    @Override
    public CompletableFuture<List<Reference>> browse(AccessContext context, NodeId nodeId) {
        ServerNode node = server.getNodeMap().get(nodeId);

        if (node != null) {
            return CompletableFuture.completedFuture(node.getReferences());
        } else {
            return FutureUtils.failedFuture(new UaException(StatusCodes.Bad_NodeIdUnknown));
        }
    }

    @Override
    public void read(
            ReadContext context,
            Double maxAge,
            TimestampsToReturn timestamps,
            List<ReadValueId> readValueIds) {

        List<DataValue> results = Lists.newArrayListWithCapacity(readValueIds.size());

        for (ReadValueId readValueId : readValueIds) {
            ServerNode node = server.getNodeMap().get(readValueId.getNodeId());

            if (node != null) {
                DataValue value = node.readAttribute(
                        new AttributeContext(context),
                        readValueId.getAttributeId(),
                        timestamps,
                        readValueId.getIndexRange(),
                        readValueId.getDataEncoding()
                );

                results.add(value);
            } else {
                results.add(new DataValue(StatusCodes.Bad_NodeIdUnknown));
            }
        }

        context.complete(results);
    }

    @Override
    public void write(WriteContext context, List<WriteValue> writeValues) {
        List<StatusCode> results = Lists.newArrayListWithCapacity(writeValues.size());

        for (WriteValue writeValue : writeValues) {
            ServerNode node = server.getNodeMap().get(writeValue.getNodeId());

            if (node != null) {
                try {
                    node.writeAttribute(
                            new AttributeContext(context),
                            writeValue.getAttributeId(),
                            writeValue.getValue(),
                            writeValue.getIndexRange()
                    );

                    results.add(StatusCode.GOOD);

                    logger.info(
                            "Wrote value {} to {} attribute of {}",
                            writeValue.getValue().getValue(),
                            AttributeId.from(writeValue.getAttributeId()).map(Object::toString).orElse("unknown"),
                            node.getNodeId());
                } catch (UaException e) {
                    logger.error("Unable to write value={}", writeValue.getValue(), e);
                    results.add(e.getStatusCode());
                }
            } else {
                results.add(new StatusCode(StatusCodes.Bad_NodeIdUnknown));
            }
        }

        context.complete(results);
    }

    @Override
    public void onDataItemsCreated(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsCreated(dataItems);
    }

    @Override
    public void onDataItemsModified(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsModified(dataItems);
    }

    @Override
    public void onDataItemsDeleted(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsDeleted(dataItems);
    }

    @Override
    public void onMonitoringModeChanged(List<MonitoredItem> monitoredItems) {
        subscriptionModel.onMonitoringModeChanged(monitoredItems);
    }

    @Override
    public Optional<MethodInvocationHandler> getInvocationHandler(NodeId methodId) {
        Optional<ServerNode> node = server.getNodeMap().getNode(methodId);

        return node.flatMap(n -> {
            if (n instanceof UaMethodNode) {
                return ((UaMethodNode) n).getInvocationHandler();
            } else {
                return Optional.empty();
            }
        });
    }

}
