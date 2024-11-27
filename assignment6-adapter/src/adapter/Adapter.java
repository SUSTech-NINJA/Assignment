package adapter;

import java.io.File;
import java.util.List;

public class Adapter implements FileOperateInterfaceV2 {
    private FileOperateInterfaceV1 adaptee;
    private ManageStaffInterface manageStaff;

    public Adapter(FileOperateInterfaceV1 adaptee, ManageStaffInterface manageStaff) {
        this.adaptee = adaptee;
        this.manageStaff = manageStaff;
    }

    @Override
    public List<StaffModel> readAllStaff() {
        return adaptee.readStaffFile();
    }

    @Override
    public void listAllStaff(List<StaffModel> list) {
        adaptee.printStaffFile(list);
    }

    @Override
    public void writeByName(List<StaffModel> list) {
        list.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        adaptee.writeStaffFile(list);
    }

    @Override
    public void writeByRoom(List<StaffModel> list) {
        list.sort((o1, o2) -> o1.getRoom().compareTo(o2.getRoom()));
        adaptee.writeStaffFile(list);
    }

    @Override
    public void addNewStaff(List<StaffModel> list) {
        manageStaff.addingStaff(list);
    }

    @Override
    public void removeStaffByName(List<StaffModel> list) {
        manageStaff.removeStaff(list);
    }
}
