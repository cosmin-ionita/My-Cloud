package Cloud;

import Cloud.Storage.*;
import Cloud.Storage.MachineId;
import FileSystem.Directory;
import FileSystem.FileSystem;
import FileSystem.File;
import Interfaces.Repository;
import Utils.ParametersManager;

/**
 * Created by Ionita Cosmin on 12/30/2015.
 */
public class CloudService {

    private static StoreStationA stationA = StoreStationA.getInstance();
    private static StoreStationB stationB = StoreStationB.getInstance();
    private static StoreStationC stationC = StoreStationC.getInstance();

    private static int stations;
    private static Repository deliverNode;
    private static String deliverNodeName;
    private static boolean machineIdSet = false;

    public static void executeUpload() {
        Directory node = (Directory) FileSystem.getFileSystem().getSystemNode(ParametersManager.getParameters());

        upload(node);
    }

    private static void upload(Directory directory) {

        stations = directory.getDimension() / 10 + 1; // 10 == 10 KB

        if (stations == 1) {
            stationA.store(new Directory(directory));
        }
        if (stations == 2) {
            Directory clonedDirectory = new Directory(directory);

            setMachineId(clonedDirectory, directory.getDimension());

            stationA.store(clonedDirectory);

            System.out.println("Am stoacat + " + clonedDirectory);
            System.out.println("Continut + " + clonedDirectory.getContent());

            stationB.store(deliverNode);
        }
    }

    private static void setMachineId(Directory directory, int dimension) {

        String content = directory.getContent();
        System.out.println("machine id content = " + content);
        for (String node : content.split(" ")) {
            Repository systemNode = directory.getNode(node);

            if (systemNode instanceof Directory && !machineIdSet) {
                handleDirectory(directory, dimension, node, (Directory) systemNode);
            }

            if (systemNode instanceof File && !machineIdSet) {
                handleFile(directory, dimension, node, (File) systemNode);
            }
        }

        if (!machineIdSet) {
            System.out.println("enter here? ");
            for (String node : content.split(" ")) {
                Repository systemNode = directory.getNode(node);

                if (systemNode instanceof Directory)
                    setMachineId((Directory) systemNode, dimension);
            }
        }
    }

    private static void handleDirectory(Directory directory, int dimension, String node, Directory systemNode) {
        Directory dir = systemNode;

        if (dimension - dir.getDimension() < 10) {
            deliverNode = new Directory(dir);
            deliverNodeName = dir.toString(); // the name of the directory

            directory.deleteNode(node);
            directory.addNode(stationB.getMachineId());

            machineIdSet = true;
        }
    }

    private static void handleFile(Directory directory, int dimension, String node, File systemNode) {
        File file = systemNode;

        if (dimension - file.getDimension() < 10) {
            deliverNode = new File(file);
            deliverNodeName = file.toString(); // the name of the file

            directory.deleteNode(node);
            directory.addNode(new MachineId());

            machineIdSet = true;
        }
    }

    public static void executeSync() {
        FileSystem.getFileSystem().currentDirectory.deleteNode(ParametersManager.getParameters());

        sync(ParametersManager.getParameters());
    }

    private static void sync(String directoryName) {
        if (stations == 1) {
            FileSystem.getFileSystem().currentDirectory.addNode(stationA.search(directoryName));
        } else if (stations == 2) {
            Directory directory = (Directory) stationA.search(directoryName);

            recreateDirectory(directory);

            FileSystem.getFileSystem().currentDirectory.addNode(directory);
        }
    }

    private static void recreateDirectory(Directory directory) {

        if (!directory.isEmpty()) {
            String content = directory.getContent();

            for (String node : content.split(" ")) {

                Repository systemNode = directory.getNode(node);

                if (systemNode instanceof MachineId) {
                    directory.deleteNode(node);
                    directory.addNode(stationB.search(deliverNodeName));
                }

                if (systemNode instanceof Directory)
                    recreateDirectory((Directory) systemNode);
            }
        }
    }
}
